package data.structures;

/**
 * CSS 143 B, Winter 2018 Data Structures: Lists, Stacks, and Queues
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class Queue {

    Object[] baseQueue = new Object[0];
    int queueSize = 0;

    /**
     * Initializes a new instance of the Queue Class.
     */
    public Queue() {

    }

    /**
     * Precondition: None 
     * Postcondition: Adds an object to the queue
     *
     * @param object
     */
    public void enqueue(Object object) {
        if(object != null){
            queueSize++;
            Object[] newQueue = new Object[queueSize];

            // Copies the baseQueue to the expanded newQueue and adds the new object
            for (int i = 0; i < queueSize - 1; i++) {
                newQueue[i] = this.baseQueue[i];
            }
            newQueue[queueSize - 1] = object;

            //makes the new expanded queue the baseQueue
            this.baseQueue = newQueue;
        } else {
            System.out.println("No null objects");
        }
    }

    /**
     * Precondition: Queue is not empty 
     * Postcondition: Return and removes the next item in the queue
     *
     * @return object
     */
    public Object dequeue() {
        if (this.queueSize > 0) {
            this.queueSize--;
            Object tReturn = this.baseQueue[0];
            Object[] newQueue = new Object[queueSize];

            // Copies the baseQueue to the reduced newQueue & removes the new object
            for (int i = 0; i < queueSize; i++) {
                newQueue[i] = this.baseQueue[i + 1];
            }
            this.baseQueue = newQueue;

            //makes the new reduced queue the baseQueue
            return tReturn;
        } 
        return null;
    }

    /**
     * Precondition: None
     * Postcondition: Return the number of objects in the queue
     * 
     * @return size
     */
    public int size() {
        return queueSize;
    }

    /**
     * Precondition: Queue is not empty
     * Postcondition: Returns string form of queue with each object separated
     * 
     * @return String
     */
    @Override
    public String toString() {
        String tReturn = "";
        for (Object o : this.baseQueue) {
            tReturn += o + " ";
        }
        return tReturn;
    }

    /**
     * Precondition: None
     * Postcondition: Returns true if there are no objects in the array
     * 
     * @return boolean
     */
    public boolean isEmpty() {
        return queueSize == 0;
    }

    /**
     * Precondition: Two queues exist to be compared
     * Postcondition: Returns true if queues are equal
     *
     * @param otherQueue
     * @return boolean
     */
    public boolean equals(Queue otherQueue) {
        int eql = 0;

        if (this.queueSize == otherQueue.queueSize) {
            //counts the number of equal values and return true if equal to size
            for (int i = 0; i < this.queueSize; i++) {
                if (this.baseQueue[i].equals(otherQueue.baseQueue[i])) {
                    eql++;
                }
            }
            if (eql == queueSize) {
                return true;
            }
        }
        return false;
    }
}
