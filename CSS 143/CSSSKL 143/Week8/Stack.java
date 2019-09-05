package week9lab;

/**
 * CSS 143 B, Winter 2018 Money, Bill, and Date Classes
 *
 * @author Margaret Connor
 * @param <E>
 */
public class Stack<E> {

    public static void main(String[] args) {
        Stack<Character> a = new Stack<>();

        a.push('R');
        a.push('a');
        a.push('c');
        a.push('e');
        //a.push('a');
        a.push('r');

        System.out.println("Size : " + a.size());

        while (!a.isEmpty()) {
            System.out.println(a.pop());
        }
    }

    Node head; //pointer

    /**
     * Default constructor for Stack class.
     */
    public Stack() {
        this.head = null;
    }

    /**
     * Adds a new Item to the Stack. This method adds a the new element to the
     * beginning of the stack.
     *
     * @param newData
     */
    public void push(E newData) {
        this.head = new Node(newData, this.head);
    }

    /**
     * Returns and removes the newest element in the stack. This method returns
     * the beginning element of the array, shifting any following objects up one
     * position.
     *
     * @return
     */
    public E pop() {
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
        if (other == null && other instanceof Stack) {
            return false;
        }

        Stack otherStack = (Stack) other;
        if (otherStack.size() == this.size()) {
            Node current = this.head;
            Node currentToCompare = otherStack.head;
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
