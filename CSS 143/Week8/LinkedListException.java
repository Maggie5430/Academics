package week.pkg8;

/**
 * CSS 143 B, Winter 2018 LinkedLists
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class LinkedListException extends RuntimeException {

    /**
     * No-argument constructor for the LinkedListException class. Postcondition:
     * creates an instance of the LinkedListException class.
     */
    public LinkedListException() {
        super();
    }

    /**
     * Constructor for the LinkedListException class that takes a message as a
     * parameter. Postcondition: creates an instance of the LinkedListException 
     * class.
     *
     * @param msg String exception message
     */
    public LinkedListException(String msg) {
        super(msg);
    }
}
