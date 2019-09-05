package week.pkg8;

/**
 * CSS 143 B, Winter 2018 LinkedLists
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class List {

    public static void main(String[] args) {
        List empty = new List();
        List one = new List();
        List multiple = new List();

        one.append(5);
        multiple.append(10);
        multiple.append(20);
        multiple.append(30);

        System.out.println("Empty:" + empty);     // ( note the implicit call to toString()! )
        System.out.println("One:" + one);
        System.out.println("Multiple:" + multiple);

        one.delete(0);
        multiple.delete(1);
        System.out.println("One (upon delete):" + one);
        System.out.println("Multiple (upon delete):" + multiple);

        //one.insert(600, 1);
        multiple.insert(400, 2);
        System.out.println("One (on insert):" + one);
        System.out.println("Multiple(on insert):" + multiple);

        System.out.println("\n--My tests--");
        List l1 = new List();
        List l2 = new List();
        l1.insert(1, 0);
        l1.insert(2, 1);
        l1.insert(3, 2);
        l1.insert(null, 3);
        l1.append(5);

        l2.append(3);
        l2.insert(2, 0);
        l2.insert(1, 0);
        l2.append(null);

        System.out.println("l1:" + l1.toString() + "\nl2:" + l2.toString());
        System.out.println("l1 == l2: " + l1.equals(l2));

        System.out.println("\nremoved:" + l1.remove(4));
        System.out.println("l1:" + l1.toString() + "\nl2:" + l2.toString());
        System.out.println("l1 == l2: " + l1.equals(l2));

        System.out.println("Index of null in l1: " + l1.indexOf(null));

        try {
            List l3 = new List();
            l3.remove(1);
        } catch (LinkedListException e) {
            System.out.println(e);
        }
        
        try {
            l2.remove(8);
        } catch (LinkedListException e) {
            System.out.println(e);
        }
    }

    private Node head;

    /**
     * No-argument constructor for the List class. Postcondition: creates an
     * instance of the List class.
     */
    public List() {
        this.head = null;
    }

    /**
     * Node class for use as links.
     */
    private class Node {

        private Object data;
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
        public Node(Object data, Node nextNode) {
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
    public void insert(Object next, int index) {
        if (index >= 0) {
            this.head = insert(next, index, this.head);
        } else {
            throw new LinkedListException("Index cannot be less than 0");
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
    private Node insert(Object next, int index, Node currentNode) {
        if (index == 0) {
            //inserts object when index has been reached 
            currentNode = new Node(next, currentNode);
        } else if (currentNode == null && index > 0) {
            //throws exception if index is out of bounds 
            throw new LinkedListException("Index out of bound:" + index);
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
    public Object remove(int index) {
        Node currentNode = this.head;
        Node previousNode = null;

        //test precondition
        if (index < 0) {
            throw new LinkedListException("Index can not be less than 0");
        } else if(this.isEmpty()){
            throw new LinkedListException("List is empty");
        }


        //moves to node at index
        while (currentNode != null && index != 0) {
            --index;
            previousNode = currentNode;
            currentNode = currentNode.nextNode;
        }

        //removes node or throws error 
        if (currentNode == null || index > 0) {
            throw new LinkedListException("Index out of bound:" + index);
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
    public void append(Object next) {
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
    public int indexOf(Object target) {
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
        if (other == null ||  !(other instanceof List)) {
            return false;
        }

        List otherList = (List) other;
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
}
