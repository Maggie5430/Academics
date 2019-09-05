import java.util.*;
/**
 * Margaret Connor
 * CSS430 P3
 */
public class QueueNode {

    // Vector of Integer
    private Vector<Integer> pidQueue;

    // constructor
    public QueueNode() {
		this.pidQueue = new Vector<>();
    }
    
    // uses Java wait()
    public synchronized int sleep() {
        try {
            wait();
        } catch (InterruptedException e) {
			SysLib.cout("Interrupted Exception in sleep");
        }
        return (int) pidQueue.remove(0); //return childID
    }
    
    // uses Java notify()
    public synchronized void wakeup(int pid) {
        pidQueue.add(pid);
        notify();
    }
}
