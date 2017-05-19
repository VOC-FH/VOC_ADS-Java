package exercises.exercise04;

import lecture.chapter03.generics.IKey;

public class UE4GenericsBTree<T> extends lecture.chapter03.generics.BTree<T> {
    private Node<T> current = null;

    protected Node<T> breadthFirstAppend(Node<T> newNode) {
        if (current == null) {
            root = newNode;
        } else {
            if (current.isLeftChild()) {
                current = current.parent;
                current.right = newNode;
            } else {
                while (current.isRightChild()) {
                    current = current.parent;
                }
                if (current.isLeftChild()) {
                    current = current.parent.right;
                }
                while (current.left != null) {
                    current = current.left;
                }
                current.left = newNode;
            }
        }
        
        newNode.parent = current;
        current = newNode;
        
        return newNode;
    }

    public void insert(T data) {
        breadthFirstAppend(new Node<T>(null, data, null));	
    }
    
    public void remove(IKey<T> key) {
        Node<T> toRemove = depthFirstPreOrderSearch(root, key);
        
        if (toRemove != null) {
            Node<T> current = toRemove;
            while (current.left != null) {
                current = current.left;
            }
            toRemove.data = current.data;
            this.removeLeaf(current);
        }
    }
    
    public void remove(T data) {
        class TKey implements IKey<T> {
            T data = null;
            
            TKey(T data) {
                this.data = data;
            }
            
            public boolean matches(T data) {
                return this.data == data;
            }
            
        }
        
        TKey key = new TKey(data);
        
        remove(key);
    }
}
