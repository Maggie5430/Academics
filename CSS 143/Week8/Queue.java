package week.pkg8;

/**
 * CSS 143 B, Winter 2018 LinkedLists
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class Queue extends List {

    public static void main(String[] args) {
        Queue empty = new Queue();
        Queue one = new Queue();
        Queue multiple = new Queue();

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
        Queue l1 = new Queue();
        Queue l2 = new Queue();
        l1.enqueue(null);
        l1.enqueue(1);
        l1.enqueue(2);
        l1.enqueue(3);
        l1.enqueue(null);

        l2.enqueue(1);
        l2.enqueue(2);
        l2.enqueue(3);
        l2.enqueue(null);

        System.out.println("l1:" + l1.toString() + "\nl2:" + l2.toString());
        System.out.println("l1 == l2: " + l1.equals(l2));

        //Demonstrates it dequeues from first added
        System.out.println("\nDequeue 1st element: " + l1.dequeue());
        System.out.println("l1:" + l1.toString() + "\nl2:" + l2.toString());
        System.out.println("l1 == l2: " + l1.equals(l2));

        System.out.println("Index of null in l1: " + l1.indexOf(null));

        System.out.println("\nDequeueing from empty list:");
        try {
            Queue l3 = new Queue();
            l3.dequeue();
        } catch (LinkedListException e) {
            System.out.println(e);
        }

    }

    /**
     * Returns true is the Queue is empty. Postcondition: returns if Queue is
     * empty.
     *
     * @return true if empty
     */
    @Override
    public boolean isEmpty() {
        if (super.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Inserts the specified Object at the beginning of the Queue. Precondition:
     * The index is a non-negative integer. Postcondition: Adds a new Object to
     * the Queue.
     *
     * @param next Object to be inserted
     */
    public void enqueue(Object next) {
        super.insert(next, 0);
    }

    /**
     * Removes and returns the Object at the end of the queue. Precondition:
     * provided index is a non-negative integer. Postcondition: Returns the last
     * (first object added) from the queue.
     *
     * @return The last (first object added) from the queue.
     */
    public Object dequeue() {
        if (this.isEmpty()) {
            throw new LinkedListException("Queue is empty");
        } else {
            return super.remove(super.size() - 1);
        }
    }

    /**
     * Inserts the specified Object at the beginning of the Queue. Precondition:
     * The index is a non-negative integer. Postcondition: Adds a new Object to
     * the end of the Queue.
     *
     * @param next Object to be inserted
     * @param index index is ignored
     */
    @Override
    public void insert(Object next, int index) {
        enqueue(next);
    }

    /**
     * Removes and returns the Object at the end of the queue. Precondition:
     * provided index is a non-negative integer. Postcondition: Returns the last
     * (first object added) from the queue.
     *
     * @return The last (first object added) from the queue.
     */
    @Override
    public Object remove(int index) {
        return dequeue();
    }

    /**
     * Inserts the specified Object at the beginning of the Queue. Precondition:
     * The index is a non-negative integer. Postcondition: Adds a new Object to
     * the end of the Queue.
     *
     * @param next Object to be inserted
     */
    @Override
    public void append(Object next) {
        enqueue(next);
    }

    /**
     * Removes and returns the Object at the end of the queue. Precondition:
     * provided index is a non-negative integer. Postcondition: Removes the
     * object from the queue
     *
     * @param index index is ignored
     */
    @Override
    public void delete(int index) {
        dequeue();
    }
}
