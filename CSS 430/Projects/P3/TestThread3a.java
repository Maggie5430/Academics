import java.util.*;
/**
 * Margaret Connor
 * CSS430 P3
 */

public class TestThread3a extends Thread{
    
    //constructor
	public TestThread3a() {
    }
	
    //CPU intensive computation
	public void run() {
		double results = 1;
        for(int i = 0; i < 5000; i ++){
			results = Math.pow(results,i);
		}
        
        //report and exit
        SysLib.cout((String)("comp finished...\n"));
        SysLib.exit();
    }
}
