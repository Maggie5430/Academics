import java.util.*;
/**
 * Margaret Connor
 * CSS430 P3
 */
public class SyncQueue {

    private QueueNode[] queue; //queue

    //constructor: called with condMax = 10
    public SyncQueue() {
        queue = new QueueNode[10];
        for (int i = 0; i < queue.length ; i++){
            queue[i] = new QueueNode();
        }
    }
	
    // constructor
    public SyncQueue(int condMax) {
        queue = new QueueNode[condMax];
        for (int i = 0; i < queue.length ; i++){
            queue[i] = new QueueNode();
        }
    }
	
    // think: blockMe(myTID)
    int enqueueAndSleep(int condition) {
        //create new condition & add to queue
        queue[condition] = new QueueNode();
        //wait unil condition is satisdfied and return child ID
        return queue[condition].sleep();
    }

    // think: UnblockHim(hisTID)
    void dequeueAndWakeup(int condition) {
        queue[condition].wakeup(0);      
    }
    // think: UnblockHim(hisTID, myTID)   
    void dequeueAndWakeup(int condition, int pid) {
        queue[condition].wakeup(pid);
    }
}
