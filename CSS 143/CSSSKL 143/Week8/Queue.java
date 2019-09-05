package week9lab;

/**
 * CSS 143 B, Winter 2018 Money, Bill, and Date Classes
 *
 * @author Margaret Connor
 * @param <E>
 */
public class Queue<E> {

    public static void main(String[] args) {
        Queue<Character> a = new Queue<>();

        a.enqueue('R');
        a.enqueue('a');
        a.enqueue('c');
        a.enqueue('e');
        //a.push('c');
        //a.push('a');
        a.enqueue('r');

        System.out.println("Size : " + a.size());

        while (!a.isEmpty()) {
            System.out.println(a.dequeue());
        }

    }

    Node head; //pointer

    /**
     * Default constructor for Queue class.
     */
    public Queue() {
        this.head = null;
    }

    /**
     * Adds a new Item to the queue. This method adds a the new element to the
     * end
     *
     * @param newData
     */
    public void enqueue(E newData) {
        Node currentNode = this.head;
        if (this.head == null) {
            this.head = new Node(newData, null);
        } else {
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = new Node(newData, null);
        }
    }

    /**
     * Returns and removes the oldest element in the queue. This method returns
     * the beginning element of the array, shifting any following objects up one
     * position.
     *
     * @return
     */
    public E dequeue() {
        Object toReturn = this.head.data;
        this.head = this.head.next;

        return (E) toReturn;
    }

    /**
     * Returns the size of the queue.
     *
     * @return
     */
    public int size() {
        int listSize = 0;
        Node currentNode = this.head;
        while (currentNode != null) {
            currentNode = currentNode.next;
            listSize++;
        }
        return listSize;
    }

    /**
     * Returns a string representation of the queue.
     *
     * @return
     */
    @Override
    public String toString() {
        String retValue = "";
        Node current = head;

        while (current != null) {
            retValue += current.data.toString() + " ";
            current = current.next;
        }
        return retValue;
    }

    /**
     * Returns true if the queue has no elements inside.
     * 
     * @return 
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Compares two objects and returns true if they are equal.
     *
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other) {
        if (other == null && other instanceof Queue) {
            return false;
        }

        Queue otherQueue = (Queue) other;
        if (otherQueue.size() == this.size()) {
            Node current = this.head;
            Node currentToCompare = otherQueue.head;
            while (current != null) {
                if (current.data != currentToCompare.data) {
                    return false;
                }
                current = current.next;
            }
        }
        return true;
    }

    /**
     * Node is a helping link item.
     */
    private class Node {

        private Object data;
        private Node next;

        /**
         * Constructor with no parameters for inner class
         */
        public Node() {
            this.data = null;
            this.next = null;
        }

        /**
         * Parametrized constructor for inner class
         *
         * @param newData
         * @param nextLink
         */
        public Node(Object newData, Node nextLink) {
            this.data = newData;
            this.next = nextLink;
        }
    }
}
