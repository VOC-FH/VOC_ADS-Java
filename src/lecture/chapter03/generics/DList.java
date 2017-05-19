package lecture.chapter03.generics;

public class DList<T> {
    protected Node<T> head = null; // The head of the list.
    protected Node<T> tail = null; // The tail of the list.
    
    protected static class Node<T> { // Double chained nodes - a recursive data structure.
        public Node<T> next = null;  // Reference to the next node.
        public Node<T> prev = null;  // Reference to the previous node.
        public T data = null;        // Reference to the stored data set.
        
        public Node(Node<T> prev, T data, Node<T> next) { // Construct a new node by
            this.data = data;                             // storing the data set,
            this.next = next;                             // and refer the next and
            this.prev = prev;                             // previous node.
        }
    }

    public void prepend(T data) { // Store a data set at the beginning of the list.
        head = new Node<T>(null, data, head); // Do this by prepending a new node.
        if (tail == null) {                // If this is the first node in the list
            tail = head;                   // then this node is the tail as well.
        } else {                           // Are there already nodes in the list?
            head.next.prev = head;         // Yes, thus enchain them consistently!
        }
    }
    
    public void append(T data) { // Store a data set at the end of the list.
        tail = new Node<T>(tail, data, null); // Do this by appending a new node.
        if (head == null) {                // If this is the first node in the list
            head = tail;                   // then this node is the head as well.
        } else {                           // Are there already nodes in the list?
            tail.prev.next = tail;         // Yes, thus enchain them consistently!
        }
    }
    
    public T forwardSearch(IKey<T> key) { // Forward search for an object matching
        Node<T> current = head;               // a given key.
        
        while(current != null && !key.matches(current.data)) { // Iterate for all nodes 
                                    // in the list but interrupt if the object is found.
            current = current.next; // Not found! Jump to the next node.
        }                           // If found return the object otherwise return null.
        return (current != null) ? current.data : null;
    }

    public T reverseSearch(IKey<T> key) { // Reverse search for an object matching
        Node<T> current = tail;               // a given key.
        
        while(current != null && !key.matches(current.data)) { // Iterate for all nodes 
                                    // in the list but interrupt if the object is found.
            current = current.prev; // Not found! Jump to the previous node.
        }                           // If found return the object otherwise return null.        
        return (current != null) ? current.data : null;
    }
    
    private void removeNode(Node<T> toRemove) { // Remove the passed node.
        if (toRemove != null) {              // Is there a node to remove?
            if (toRemove == head) {          // Is the node to remove the first node?
                head = toRemove.next;        // Yes, thus let head refer the second node.
            } else {                         // Is there a node before the node to remove?
                toRemove.prev.next = toRemove.next; // Yes, thus enchain it consistently.
            }
            if (toRemove == tail) {          // Is the node to remove the last node?
                tail = toRemove.prev;        // Yes, thus let tail refer the last but one.
            } else {                         // Is there a node behind the node to remove?
                toRemove.next.prev = toRemove.prev; // Yes, thus enchain it consistently.
            }
        }
    }
    
    protected Node<T> forwardSearchNode(T data) { // Forward search for the specific
        Node<T> current = head;                // node referring the passed data object.
        
        while(current != null && data != current.data) { // Iterate for all nodes
            current = current.next; // but interrupt if the node has been found.
        }
        
        return current; // Return the found node or null otherwise.
    }
    
    public void forwardRemove(T data) { // Forward remove the node referring data.
        Node<T> toRemove = forwardSearchNode(data); // Search for the node to be removed.
        
        removeNode(toRemove); // Remove the desired node.
    }
    
    protected Node<T> reverseSearchNode(T data) { // Reverse search for the specific
        Node<T> current = tail;                // node referring the passed data object.
        
        while(current != null && data != current.data) { // Iterate for all nodes
            current = current.prev; // but interrupt if the node has been found.
        }
        
        return current; // Return the found node or null otherwise.
    }
    
    public void reverseRemove(T data) { // Reverse remove the node referring data.
        Node<T> toRemove = reverseSearchNode(data); // Search for the node to be removed.
        
        removeNode(toRemove); // Remove the desired node.
    }
    
    private static class DListIterator<T> extends IBIterator<T> { // An iterator
            // for retrieving all data sets of the list. It's a bidirectional iterator
            // because we can go forward and backward in a double linked list.
        Node<T> currentForward = null; // Forward reference to a specific current node.
        Node<T> currentReverse = null; // Reverse reference to a specific current node.
        
        public DListIterator(Node<T> start) { // Create a new Iterator-Object
            currentForward = currentReverse = start; // and initialize the start-node.
        }
        
        public boolean hasNext() {         // Is there a further data set in the list?
            return currentForward != null; // If yes return true else return false.
        }

        public boolean hasPrevious() {     // Is there a further data set in the list?
            return currentReverse != null; // If yes return true else return false.
        }
        
        public T next() {                    // Fetch the next data set.
            T data = currentForward.data;    // Retrieve the data set
            currentForward = currentForward.next; // and move on to the next node.
            // Update currentReverse in a way to stay consistent.
            currentReverse = (currentForward != null) ? currentForward : currentReverse;
            return data;
        }
        
        public T previous() {                // Fetch the previous data set.
            T data = currentReverse.data;    // Retrieve the data set
            currentReverse = currentReverse.prev; // and move on to the next node.
            // Update currentForward in a way to stay consistent.
            currentForward = (currentReverse != null) ? currentReverse: currentForward;
            return data;
        }
    }

    public IFIterator<T> fIterator() {     // Create a new forward iterator 
        return new DListIterator<T>(head); // and let it start from the tail of the list.
    }
    
    public IRIterator<T> rIterator() {     // Create a new reverse iterator 
        return new DListIterator<T>(tail); // and let it start from the tail of the list.
    }
}
