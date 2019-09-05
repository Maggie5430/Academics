import java.util.*;
/**
 * Margaret Connor
 * CSS430 P3
 */

public class TestThread3b extends Thread{
    //constructor
	public TestThread3b() {
    }
	
    //Read and Write thread
	public void run() {
        //read and write at random places 100 times
        for (int i = 0; i < 100; ++i) {
            byte[] testByte = new byte[512];
            SysLib.rawread((int)(Math.random() * 1000), testByte);
            SysLib.rawwrite((int)(Math.random() * 1000), testByte);
        }
        
        //report
        SysLib.cout((String)("disk finished...\n"));
        SysLib.exit();
    }
}
