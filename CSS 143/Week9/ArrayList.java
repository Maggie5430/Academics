
import java.util.Iterator;

/**
 * CSS 143 B, Winter 2018 Classes & Interfaces (MoneyV2)
 *
 * @author Margaret Connor
 * @version 2.0
 * @param <E>
 */
public class ArrayList<E> implements Iterable<E> {

    private Node head;

    /**
     * No-argument constructor for the List class. Postcondition: creates an
     * instance of the List class.
     */
    public ArrayList() {
        this.head = null;
    }

    /**
     * Node class for use as links.
     */
    private class Node {

        private E data;
        private Node nextNode;

        /**
         * No-argument constructor for the Node class. Postcondition: creates an
         * instance of the List class.
         */
        public Node() {
            this.data = null;
            this.nextNode = null;
        }

        /**
         * Constructor for the Node class that takes a data type and linking
         * Node as parameters. Postcondition: creates an instance of the List
         * class.
         *
         * @param data Object to be stored
         * @param nextNode following node (if any)
         */
        public Node(E data, Node nextNode) {
            this.data = data;
            this.nextNode = nextNode;
        }

    }

    /**
     * Inserts the specified Object at the specified position in this list. Then
     * shifts the element currently at that position (if any) and any subsequent
     * elements to the right. Pre condition: The index is a non-negative
     * integer. Postcondition: Adds a new Object to the linked list at the
     * specified index.
     *
     * @param next Object to be inserted
     * @param index index at which the specific element is to be inserted
     */
    public void insert(E next, int index) {
        if (index >= 0) {
            this.head = insert(next, index, this.head);
        } else {
            throw new RuntimeException("Index cannot be less than 0");
        }
    }

    /**
     * Helper method that inserts the specified Object at the specified position
     * in this list. Then shifts the element currently at that position (if any)
     * and any subsequent elements to the right. Precondition: The node provided
     * is the first Node in the linked list (i.e., head) Postcondition: Adds a
     * new Object to the linked list at the specified index.
     *
     * @param next Object to be inserted
     * @param index index at which a specific element is to be inserted
     * @param currentNode Node to start at
     * @return Node that links the list to new changes
     */
    private Node insert(E next, int index, Node currentNode) {
        if (index == 0) {
            //inserts object when index has been reached 
            currentNode = new Node(next, currentNode);
        } else if (currentNode == null && index > 0) {
            //throws exception if index is out of bounds 
            throw new RuntimeException("Index out of bound:" + index);
        } else {
            //recursively calls down the list reducing index to count iterations
            currentNode.nextNode = insert(next, --index, currentNode.nextNode);
        }
        return currentNode;
    }

    /**
     * Removes and returns the Object at the specific index. Then shifts all
     * following Objects (if any) to the left. Precondition: provided index is a
     * non-negative integer. Postcondition: the Object at the index has been
     * removed from the linked list and returned.
     *
     * @param index index at which a specific element is to be inserted
     * @return the the object the was to remove
     */
    public E remove(int index) {
        Node currentNode = this.head;
        Node previousNode = null;

        //test precondition
        if (index < 0) {
            throw new RuntimeException("Index can not be less than 0");
        } else if (this.isEmpty()) {
            throw new RuntimeException("List is empty");
        }

        //moves to node at index
        while (currentNode != null && index != 0) {
            --index;
            previousNode = currentNode;
            currentNode = currentNode.nextNode;
        }

        //removes node or throws error 
        if (currentNode == null || index > 0) {
            throw new RuntimeException("Index out of bound:" + index);
        } else if (previousNode == null) {
            this.head = currentNode.nextNode;
        } else {
            previousNode.nextNode = currentNode.nextNode;
        }

        return currentNode.data;
    }

    /**
     * Inserts the specified Object at the end of the list. Pre condition: The
     * index is a non-negative integer. Postcondition: Adds a new Object to the
     * end of the linked list.
     *
     * @param next Object to be inserted
     */
    public void append(E next) {
        this.insert(next, this.size());
    }

    /**
     * Removes the Object at the specific index. Then shifts all following
     * Objects (if any) to the left. Precondition: provided index is a
     * non-negative integer. Postcondition: the Object at the index has been
     * removed from the linked list and returned.
     *
     * @param index
     */
    public void delete(int index) {
        this.remove(index);
    }

    /**
     * Returns the number of elements in this list. Postcondition: returns the
     * size of the linked list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        Node currentNode = this.head;
        int size = 0;

        while (currentNode != null) {
            currentNode = currentNode.nextNode;
            size++;
        }
        return size;
    }

    /**
     * Returns true is the linked list is empty. Postcondition: returns if list
     * is empty.
     *
     * @return true if empty
     */
    public boolean isEmpty() {
        return this.head == null;
    }

    /**
     * Returns the first index of the specific Object. Postcondition: returns
     * index of target.
     *
     * @param target Object to be found
     * @return index of object or -1 if not found.
     */
    public int indexOf(E target) {
        Node currentNode = this.head;
        int i = 0;
        while (currentNode != null) {
            if (target == null) {
                if (currentNode.data == null) {
                    return i;
                }
                currentNode = currentNode.nextNode;

            } else {
                if (currentNode.data.equals(target)) {
                    return i;
                }
                currentNode = currentNode.nextNode;
            }
            i++;
        }
        return -1;

    }

    /**
     * Compares and returns true if two linked lists are equal to each other.
     * Postcondition: returns true if two linked lists are equal to each other.
     *
     * @param other returns true if two linked lists are equal to each other
     * @return
     */
    @Override
    public boolean equals(Object other) {
        //compares types (use instanceof becuase of how queue and stack inherit
        //from list)
        if (other == null || !(other instanceof ArrayList)) {
            return false;
        }

        ArrayList otherList = (ArrayList) other;
        Node otherNode = otherList.head;
        Node currentNode = this.head;

        //compares all data
        while (currentNode != null && otherNode != null) {
            if ((otherNode.data == null && currentNode.data != null)
                    || (otherNode.data != null && currentNode.data == null)) {
                return false;
            } else if ((otherNode.data == null && currentNode.data == null)
                    || (otherNode.data.equals(currentNode.data))) {
                currentNode = currentNode.nextNode;
                otherNode = otherNode.nextNode;
            } else {
                return false;
            }
        }

        //Test to see if there are no items remaining in either linked list
        if (currentNode == null && otherNode == null) {
            return true;
        }
        return false;
    }

    /**
     * Returns a string representation of this collection. The string consists
     * of all the elements in one line separated by a space.
     *
     * @return A string representation of this collection
     */
    @Override
    public String toString() {
        String toString = "";
        Node currentNode = this.head;

        //loops through all elements of an array
        while (currentNode != null) {
            toString += currentNode.data + " ";
            currentNode = currentNode.nextNode;
        }

        return toString;
    }

    /**
     * Returns the data object and the specified index. Precondition: Index is
     * non negative integer, and less than the array size. Postcondition:
     * returns the data object at the specific index.
     *
     * @param index
     * @return
     */
    public E get(int index) {
        if (index < 0 && index > this.size() - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Node currentNode = this.head;
        while (currentNode != null && index != 0) {
            currentNode = currentNode.nextNode;
            index--;
        }

        if (currentNode == null) {
            throw new RuntimeException("Index out of bounds");
        }
        return (E) currentNode.data;
    }

    /**
     * This method swaps the data objects of the two provided index.
     * Precondition: Index is non-negative and less than the array size.
     * Postcondition: swaps the data objects of the two provided index.
     *
     * @param idx1
     * @param idx2
     */
    public void swap(int idx1, int idx2) {
        if (idx1 < 0 || idx1 > this.size() - 1 || idx2 < 0
                || idx2 > this.size() - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Node currentNode1 = this.head;
        Node previousNode1 = null;
        Node currentNode2 = this.head;
        Node previousNode2 = null;

        //loops to index
        while (currentNode1 != null && idx1 != 0) {
            previousNode1 = currentNode1;
            currentNode1 = currentNode1.nextNode;

            idx1--;
        }
        while (currentNode2 != null && idx2 != 0) {
            previousNode2 = currentNode2;
            currentNode2 = currentNode2.nextNode;

            idx2--;
        }

        //re-assign based on location
        if (previousNode1 == null) {
            this.head = currentNode2;
            previousNode2.nextNode = currentNode1;
        } else if (previousNode2 == null) {
            this.head = currentNode1;
            previousNode1.nextNode = currentNode2;
        } else {
            previousNode1.nextNode = currentNode2;
            previousNode2.nextNode = currentNode1;
        }

        //re-assign links 
        Node hold = currentNode2.nextNode;
        currentNode2.nextNode = currentNode1.nextNode;
        currentNode1.nextNode = hold;
    }

    /**
     * Returns an iterator over a set of elements of type T. Postcondition:
     * Returns an iterator over a set of elements of type T.
     *
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        return new MyIterator<>(this.head);
    }

    /**
     * An iterator over a collection class.
     *
     * @param <T>
     */
    public class MyIterator<T> implements Iterator<T> {

        Node currentNode;

        /**
         * Constructor for the MyIterator class.
         *
         * @param pointer head link of link list
         */
        public MyIterator(Node pointer) {
            currentNode = pointer;
        }

        /**
         * Returns true if the iteration has more elements. (In other words,
         * returns true if next() would return an element rather than throwing
         * an exception.) Postcondition: Returns true if the iteration has more
         * elements.
         *
         * @return true if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            if (currentNode == null) {
                return false;
            } else {
                return true;
            }
        }

        /**
         * Returns the next element in the iteration. Postcondition: Returns the
         * next element in the iteration
         *
         * @return the next element in the iteration
         */
        @Override
        public T next() {
            Object toReturn = currentNode.data;
            currentNode = currentNode.nextNode;
            return (T) toReturn;
        }
    }
}
