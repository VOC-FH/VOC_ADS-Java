package kapitel_3.vl;

public abstract class Heap extends BTree {
	protected IComparator comparator = null;
	
	protected abstract int comparatorSign();
	
	protected int cs = comparatorSign();
	
	Node lastNode = null;
	
	public Heap(IComparator comparator) {
		this.comparator = comparator;
	}
	
	private void upHeap(Node node) {
		if (node.parent != null) {
			if (cs * comparator.compare(node.parent.data, node.data) == +1) {
				Object tmpData = node.parent.data;
				node.parent.data = node.data;
				node.data = tmpData;
				upHeap(node.parent);
			}
		}
	}

	private Node findNextParent() {
		Node nextParent = lastNode;
		
		if (lastNode != null) {
			if (lastNode.isLeftChild()) {
				nextParent = lastNode.parent;
			} else if (lastNode.isRightChild()) {
				nextParent = lastNode.parent;
				
				while (nextParent.isRightChild()) {
					nextParent = nextParent.parent;
				}
				if (nextParent.isLeftChild()) {
					nextParent = nextParent.parent.right;
				}
				while (nextParent.left != null) {
					nextParent = nextParent.left;
				}
			}
		}
		return nextParent;
	}
	
	protected void insert(Node newNode) {
		Node nextParent = findNextParent();
		
		if (nextParent == null) {
			root = newNode;
		} else if (nextParent.left == null) {
			nextParent.left = newNode;
		} else {
			nextParent.right = newNode;
		}
		newNode.parent = nextParent;
		
		lastNode = newNode;
		
		upHeap(lastNode);
	}
	
	public void insert(Object data) {
		Node newNode = new Node(null, data, null);
		
		insert(newNode);
	}

	private void downHeap(Node node) {
		Node exchangeChild = node.left;
		
		if (exchangeChild == null || node.right != null 
				&& cs * comparator.compare(exchangeChild.data, node.right.data) 
						== +1) {
			exchangeChild = node.right;
		}
		if (exchangeChild != null 
				&& cs * comparator.compare(node.data, exchangeChild.data) == +1) {
			exchangeDatasets(node, exchangeChild);
			downHeap(exchangeChild);
		}
	}
	
	private Node findLastButOne() {
		Node lastButOne = null;
		
		if (lastNode.isRightChild()) {
			lastButOne = lastNode.parent.left;
		} else if (lastNode.isLeftChild()) {
			lastButOne = lastNode.parent;
			
			while (lastButOne.isLeftChild()) {
				lastButOne = lastButOne.parent;
			}
			if (lastButOne.isRightChild()) {
				lastButOne = lastButOne.parent.left;
			}
			while (lastButOne.right != null) {
				lastButOne = lastButOne.right;
			}
		}
		return lastButOne;
	}
	
	protected void remove(Node toRemove) {
		if (toRemove != null) {
			Node lastButOne = findLastButOne();
			
			if (lastButOne != null) {
				Object data = toRemove.data;
				toRemove.data = lastNode.data;
				if (cs * comparator.compare(data, toRemove.data) == +1) {
					upHeap(toRemove);
				} else {
					downHeap(toRemove);
				}
			}
			removeLeaf(lastNode);
			
			lastNode = lastButOne;
		}
	}
	
	public boolean remove(IKey key) {
		Node toRemove = heapSearch(root, key);
		
		remove(toRemove);

		return toRemove != null;
	}
	
	public boolean remove(Object data) {
		Node toRemove = heapSearch(root, data);
		
		remove(toRemove);
		
		return toRemove != null;
	}
	
	public Object extractRoot() {
		Node toRemove = root;
		
		Object data = (toRemove != null) ? toRemove.data : null;

		remove(toRemove);
		
		return data;
	}
	
	protected Node heapSearch(Node currentRoot, IKey key) {
		Node found = null;
		
		if (currentRoot != null) {
			switch(cs * comparator.compare(currentRoot.data, key)) {
				case 0:
					found = currentRoot;
					break;
				case -1:
					found = heapSearch(currentRoot.left, key);
					if (found == null) {
						found = heapSearch(currentRoot.right, key);
					}
					break;
				case +1:
					break;
			}
		}
		return found;
	}
	
	protected Node heapSearch(Node currentRoot, Object data) {
		Node found = null;
		
		if (currentRoot != null) {
			switch(cs * comparator.compare(currentRoot.data, data)) {
				case 0:
					found = currentRoot;
					break;
				case -1:
					found = heapSearch(currentRoot.left, data);
					if (found == null) {
						found = heapSearch(currentRoot.right, data);
					}
					break;
			}
		}
		return found;
	}
	
	public Object search(IKey key) {
		Node found = heapSearch(root, key);
		return (found != null) ? found.data : null;
	}
	
	protected boolean isHeap(Node current) {
		boolean answer = true;
		
		if (current != null) {
			if (current.left != null &&
					cs * comparator.compare(current.data, current.left.data) > 0) {
				answer = false;
			}
			if (current.right != null &&
					cs * comparator.compare(current.data, current.right.data) > 0) {
				answer = false;
			}
			if (answer) {
				answer = isHeap(current.left);
				if (answer) {
					answer = isHeap(current.right);
				}
			}
		}
		return answer;
	}
	
	public boolean isHeap() {
		return isHeap(root);
	}
}
