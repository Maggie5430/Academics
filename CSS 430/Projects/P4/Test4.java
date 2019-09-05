/**
 * Margaret Connor
 * CSS430 Spring 2019 UW
 * P4 05/27/19
 */
import java.util.Date;
import java.lang.Math;

class Test4
extends Thread {
    private long startTime;
    private long endTime;
    
    /**
     * Default constructor.
     */
    public Test4() {
    }
    
    /**
     * Main runnable method performs all tests and tracks the time to complete.
     */
    @Override
    public void run() {
        SysLib.flush();
        startTime = new Date().getTime();
        randomAccess();
        endTime = new Date().getTime();
        SysLib.cout((String)("Test random acess: " + (this.endTime - this.startTime) + "\n"));
        
        SysLib.flush();
        startTime = new Date().getTime();
        this.localizedAccess();
        this.endTime = new Date().getTime();
        SysLib.cout((String)("Test localized acess: " + (this.endTime - this.startTime) + "\n"));
        
        SysLib.flush();
        startTime = new Date().getTime();
        this.mixedAccess();
        this.endTime = new Date().getTime();
        SysLib.cout((String)("Test mixed access: " + (this.endTime - this.startTime) + "\n"));
        
        SysLib.flush();
        startTime = new Date().getTime();
        this.adversaryAccess();
        this.endTime = new Date().getTime();
        SysLib.cout((String)("Test adversary acess: " + (this.endTime - this.startTime) + "\n"));
        
        
        SysLib.exit();
    }
    
    /**
     * Read and write many blocks randomly across the disk. Verify the
     * correctness of your disk cache.
     */
    private void randomAccess() {
        int[] blocks = new int[400];
        byte[] writeBlock = new byte[512];
        byte[] readBlock = new byte[512];
        
        //randomly select 400 blocks
        for (int i = 0; i < 400; i++) {
            blocks[i] = (int)(Math.random() * 512);
        }
        
        //initialize values and write 400 blocks to disk
        long startTimeWrite = new Date().getTime();
        for (int i = 0; i < 400; i++) {
            for (int j = 0; j < 512; j++) {
                writeBlock[j] = (byte)j;
            }
            SysLib.cwrite((int)blocks[i], (byte[])writeBlock);
        }
        long endTimeWrite = new Date().getTime();
        SysLib.cout((String)("   Random Access average write time = " + (endTimeWrite - startTimeWrite)/400 + "\n"));
        
        //read and compare
        long startTimeRead = new Date().getTime();
        for (int i = 0; i < 400; i++) {
            SysLib.cread((int)blocks[i], (byte[])readBlock);
            for (int n = 0; n < 512; ++n) {
                if (readBlock[n] != writeBlock[n]){
                    SysLib.cerr((String)"ERROR\n");
                    SysLib.exit();
                }
            }
        }
        long endTimeRead = new Date().getTime();
        SysLib.cout((String)("   Random Access average read time = " + (endTimeRead - startTimeRead)/400 + "\n"));
    }
    
    /**
     * read and write a small selection of blocks many times to get a high ratio
     * of cache hits.
     */
    private void localizedAccess() {
        int[] blocks = new int[25];
        byte[] writeBlock = new byte[512];
        byte[] readBlock = new byte[512];
        long waitTimeWrite = 0;
        long waitTimeRead = 0;
        
        //400 times
        for(int k = 0; k < 16; k++){
            
            //randomly select 25 blocks
            for (int i = 0; i < 25; i++) {
                blocks[i] = (int)(Math.random() * 512);
            }
            
            //randomly initialize values and writes 25 blocks to disk
            long startTimeWrite = new Date().getTime();
            for (int i = 0; i < 25; i++) {
                for (int j = 0; j < 512; j++) {
                    writeBlock[j] = (byte)j;
                }
                
                SysLib.cwrite((int)blocks[i], (byte[])writeBlock);
            }
            long endTimeWrite = new Date().getTime();
            waitTimeWrite += endTimeWrite - startTimeWrite;
            
            //reads and compares 25 blocks
            long startTimeRead = new Date().getTime();
            for (int i = 0; i < 25; i++) {
                SysLib.cread((int)blocks[i], (byte[])readBlock);
                for (int n = 0; n < 512; ++n) {
                    if (readBlock[n] != writeBlock[n]){
                        SysLib.cerr((String)"ERROR\n");
                        SysLib.exit();
                    }
                }
            }
            long endTimeRead = new Date().getTime();
            waitTimeRead = endTimeRead - startTimeRead;
        }
        SysLib.cout((String)("   Localized acess average write time = " + (waitTimeWrite)/400 + "\n"));
        SysLib.cout((String)("   Localized acess average read time = " + (waitTimeRead)/400 + "\n"));
    }
    
    /**
     * 90% of the total disk operations should be localized accesses and 10%
     * should be random accesses.
     */
    private void mixedAccess() {
        int[] blocks = new int[400];
        byte[] writeBlock = new byte[512];
        byte[] readBlock = new byte[512];
        
        //Select 400 blocks in which 10% are random (every 10th block)
        for(int i = 0; i < 16; i++){
            for(int j = 0; j < 25; j ++){
                if((j + 25 * i)%10 == 0){
                    blocks [j] = (int)(Math.random() * 512);
                } else {
                    blocks[j] = j;
                }
            }
            
        }
        
        //initialize values and write 200 blocks to disk
        long startTimeWrite = new Date().getTime();
        for (int i = 0; i < 400; i++) {
            for (int j = 0; j < 512; j++) {
                writeBlock[j] = (byte)j;
            }
            SysLib.cwrite((int)blocks[i], (byte[])writeBlock);
        }
        long endTimeWrite = new Date().getTime();
        SysLib.cout((String)("   Mixed acess average write time = " + (endTimeWrite - startTimeWrite)/400 + "\n"));
        
        //read and compare
        long startTimeRead = new Date().getTime();
        for (int i = 0; i < 400; i++) {
            SysLib.cread((int)blocks[i], (byte[])readBlock);
            for (int n = 0; n < 512; ++n) {
                if (readBlock[n] != writeBlock[n]){
                    SysLib.cerr((String)"ERROR\n");
                    SysLib.exit();
                }
            }
        }
        long endTimeRead = new Date().getTime();
        SysLib.cout((String)("   Mixed acess average read time = " + (endTimeRead - startTimeRead)/400 + "\n"));
    }
    
    /**
     * generate disk accesses that do not make good use of the disk cache at
     * all.
     */
    private void adversaryAccess() {
        int[] blocks = new int[400];
        byte[] writeBlock = new byte[512];
        byte[] readBlock = new byte[512];
        
        //select 400 blocks in order
        for (int i = 0; i < 400; i++) {
            blocks[i] = (int)i;
        }
        
        //initialize values and write 500 blocks to disk
        long startTimeWrite = new Date().getTime();
        for (int i = 0; i < 400; i++) {
            for (int j = 0; j < 512; j++) {
                writeBlock[j] = (byte)j;
            }
            SysLib.cwrite((int)blocks[i], (byte[])writeBlock);
        }
        long endTimeWrite = new Date().getTime();
        SysLib.cout((String)("   Adversary acess average write time = " + (endTimeWrite - startTimeWrite)/400 + "\n"));
        
        //read and compare
        long startTimeRead = new Date().getTime();
        for (int i = 0; i < 400; i++) {
            SysLib.cread((int)blocks[i], (byte[])readBlock);
            for (int n = 0; n < 512; ++n) {
                if (readBlock[n] != writeBlock[n]){
                    SysLib.cerr((String)"ERROR\n");
                    SysLib.exit();
                }
            }
        }
        long endTimeRead = new Date().getTime();
        SysLib.cout((String)("   Adversary acess average read time = " + (endTimeRead - startTimeRead)/400 + "\n"));
    }
    
}
