import java.util.*;

/**
 * Margaret Connor
 * CSS430 P3
 */
public class Test3 extends Thread {
    private int repetition;
    
    //constructor
    public Test3(String[] input) {
        this.repetition = Integer.parseInt(input[0]);
    }
	
    public void run() {
        long startTime = new Date().getTime();
        //repeat computations and read/write
        for (int i = 0; i < this.repetition; i++) {
            SysLib.exec(SysLib.stringToArgs((String)"TestThread3a"));
            SysLib.exec(SysLib.stringToArgs((String)"TestThread3b"));
        }
        
        //waits for children
        for (int i = 0; i < this.repetition * 2; i++) {
            SysLib.join();
        }
        
        //computes execution
        long endTime = new Date().getTime();
        SysLib.cout((String)("Turn Around Time = " + (endTime - startTime) + " msec\n"));
        SysLib.exit();
    }
}
