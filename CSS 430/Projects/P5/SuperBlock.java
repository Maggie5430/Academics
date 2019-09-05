/**
 * CSS 430 Program 5: SuperBlock.java
 *
 * The first disk block, block 0, is called the superblock. It is used to
 * describe the number of disk blocks, the number of inodes, the block number of
 * the head block of the free list. It is the OS-managed block. No other
 * information must be recorded in and no user threads must be able to get
 * access to the superblock.
 *
 * @author Margaret Connor
 * @author Manvir Singh
 * @version 1.1
 * @since 2019-06-07
 */
class SuperBlock {
    private final int deafultInodeBlocks = 64;
    public int totalBlocks; // the number of disk blocks
    //public int inodeBlocks; // the number of inodes
    public int inodeBlocks; // the number of inodes
    public int freeList;    // the block number of the free list's head
    
    /**
     * Constructor for the SuperBlock class
     * @param - diskSize, the size of the disk
     */
    public SuperBlock( int diskSize ) {
        // read the superblock from disk
        byte[] superblock = new byte[Disk.blockSize];
        SysLib.rawread(0,superblock);
        totalBlocks = SysLib.bytes2int(superblock,0);
        inodeBlocks = SysLib.bytes2int(superblock,4);
        freeList = SysLib.bytes2int(superblock,8);
        
        if (totalBlocks == diskSize && inodeBlocks > 0 && freeList >= 2){
            //disk contents are valid
            return;
        } else {
            //need to format disk
            totalBlocks = diskSize;
            format(deafultInodeBlocks); //default inode blocks
        }
    }
    
    /**
     * Write back totalBlocks, toatalInodes, and freeList to disk.
     */
    public void sync(){
        byte[] superblock = new byte[Disk.blockSize];
        SysLib.int2bytes(totalBlocks,superblock,0);
        SysLib.int2bytes(inodeBlocks,superblock,4);
        SysLib.int2bytes(freeList,superblock,8);
        
        SysLib.rawwrite(0, superblock);
    }
    
    /**
     * Formats the disk and remaining nodes based on the given value.
     *
     * @param the number of blocks to format
     */
    public void format(int blocks){
        this.inodeBlocks = blocks;
        //format blocks
        for (int i = 0; i < this.inodeBlocks; i++) {
            Inode temp = new Inode();
            temp.flag = 0;
            temp.toDisk((short)i);
        }
        //rewrite to empty values
        this.freeList = 2 + this.inodeBlocks * 32 / 512;
        
        for (int i = freeList; i < this.totalBlocks; i++) {
            byte[] arr = new byte[512];
            for (int j = 0; j < 512; ++j) {
                arr[j] = 0;
            }
            SysLib.int2bytes(i + 1, arr, 0);
            SysLib.rawwrite(i, arr);
        }
        this.sync();
    }
    
    /**
     * Dequeue the top block from the free list.
     */
    public int getFreeBlock(){
        int firstFree = freeList;
        if (freeList > 0){
            if (freeList < totalBlocks){
                //read first free block
                byte[] block = new byte[Disk.blockSize];
                SysLib.rawread(freeList, block);
                //set free block as next free
                freeList = SysLib.bytes2int(block, 0);
                //puts 0 at first 2 bytes and writes back to disk
                SysLib.int2bytes(0, block, 0);
                SysLib.rawwrite(firstFree, block);
            }
        }
        return firstFree;
    }
    
    /**
     * Insert new free block at head.
     * @param block number to be returned 
     */
    public boolean returnBlock(int blockNumber){
        if (blockNumber >= 0){
            byte [] newFree = new byte[Disk.blockSize];
            SysLib.int2bytes(freeList, newFree, 0);
            SysLib.rawwrite(blockNumber, newFree);
            freeList = blockNumber;
            return true;
        }
        return false;
    }
}
