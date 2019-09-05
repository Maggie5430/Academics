/**
 * CSS 430 Program 5: Inode.java
 *
 * Starting from the blocks after the superblock will be the inode blocks. Each
 * inode describes one file. Our inode is a simplified version of the Unix
 * inode. It includes 12 pointers of the index block. The first 11 of these
 * pointers point to direct blocks. The last pointer points to an indirect
 * block. In addition, each inode must include
 * (1) the length of the corresponding file,
 * (2) the number of file (structure) table entries that point to this inode
 * (3) the flag to indicate if it is unused (= 0), used(= 1), or in some other
 * status (= 2, 3, 4, ...). 16 inodes can be stored in one block.
 *
 * @author Margaret Connor
 * @author Manvir Singh
 * @version 1.1
 * @since 2019-06-07
 */
public class Inode {
    
    private final static int iNodeSize = 32;
    private final static int directSize = 11;
    
    public int length; //size of bytes
    public short count; //number of times opened
    public short flag; //[0,1][unused,used]
    public short direct[] = new short[directSize]; // direct pointers
    public short indirect; // a indirect pointer
    
    /**
     * Default Constructor
     */
    Inode() {
        length = 0;
        count = 0;
        flag = 1;
        for (int i = 0; i < directSize; i++) {
            direct[i] = -1;
        }
        indirect = -1;
    }
    
    /**
     * Constructor for the Inode class.
     *
     * @param - iNumber, index of Inode in block
     */
    Inode(short iNumber) { // retrieving inode from disk
        //read block from disk
        byte[] blockData = new byte[Disk.blockSize];
        SysLib.rawread(1 + iNumber / 16, blockData);
        //read in length, count, and flag
        int dest = (iNumber % 16) * 32; //destination, index of iNumber
        this.length = SysLib.bytes2int(blockData, dest);
        this.count = SysLib.bytes2short(blockData, dest + 4);
        this.flag = SysLib.bytes2short(blockData, dest + 6);
        //Read in direct pointer array
        dest += 8;
        for (int dcount = 0; dcount < this.directSize; dcount++) {
            short directBlock = SysLib.bytes2short(blockData, dest + dcount * 2);
            direct[dcount] = directBlock;
        }
        //Read in indirect pointer
        dest += this.directSize * 2;
        indirect = SysLib.bytes2short(blockData, dest);
    }
    
    /**
     * Saves the Inode to DISK.
     *
     * @param - iNumber, index of Inode in block
     */
    void toDisk(short iNumber) {
        //correct iNumber
        if (iNumber < 0) {
            return;
        }
        /* Inode is 32 bytes. length (4 bytes), count (2 bytes), flag (2 bytes)
         * dirrect array (22 bytes), indirect pointer (2 bytes)*/
        byte[] inodeData = new byte[32];
        int dest = 0;
        // write length, count, and flag to buff (8 bytes)
        SysLib.int2bytes(length, inodeData, dest);
        SysLib.short2bytes(count, inodeData, dest + 4);
        SysLib.short2bytes(flag, inodeData, dest + 6);
        // write direct pointer to buff (22 bytes)
        dest += 8;
        for (int directIndex = 0; directIndex < this.directSize; directIndex++) {
            SysLib.short2bytes(this.direct[directIndex], inodeData,
                               dest + directIndex * 2);
        }
        // write indirect pointer to buff (2 bytes)
        dest += this.directSize * 2;
        SysLib.short2bytes(this.indirect, inodeData, dest);
        // write block to disk
        int pointerIndex = 1 + iNumber / 16;
        byte[] tempData = new byte[Disk.blockSize];
        SysLib.rawread(pointerIndex, tempData);
        int offsetForInt = iNumber % 16 * iNodeSize;
        // Now write back the data into the disk given the location
        System.arraycopy(inodeData, 0, tempData, offsetForInt, iNodeSize);
        SysLib.rawwrite(pointerIndex, tempData);
        
    }
    
    /**
     * Returns the block number.
     *
     * @return block number
     */
    short getIndexBlockNumber() {
        return indirect;
        
    }
    
    /**
     * This method take in the block number and update it by writing it back to
     * the disk.
     *
     * @param - block, the block number
     * @return a boolean variable indicating sucess
     */
    Boolean setIndexBlock(short block) {
        //Checks if direct is valid
        for (int i = 0; i < this.directSize; i++) {
            if (direct[i] == -1) {
                return false;
            }
        }
        //Checks if indirect is valid
        if (indirect == -1) {
            return false;
        }
        //Set indirect block
        indirect = block;
        //Fill temp block with -1
        byte[] tempBlock = new byte[Disk.blockSize];
        for (int i = 0; i < 256; i++) {
            SysLib.int2bytes(-1, tempBlock, i * 2);
        }
        //Write temp block to disk
        SysLib.rawwrite(block, tempBlock);
        return true;
    }
    
    /**
     * The index of the block that the cursor is currently at.
     *
     * @param offset - the location of cursor
     * @return The index of the block that the cursor is at
     */
    int findTargetBlock(int cursor) {
        // read block from Inode
        int index = cursor / Disk.blockSize;
        if (index < this.directSize) {
            return direct[index];
        } else if (indirect == -1) {
            return -1;
        } else {
            // Read block from disk
            byte[] tempData = new byte[Disk.blockSize];
            SysLib.rawread(indirect, tempData);
            int dist = index - this.directSize;
            return SysLib.bytes2short(tempData, dist * 2);
        }
    }
    
    /**
     * Registers as new block given the provided pointer and block number.
     *
     * @param cursor The location that is pointed to by the seek pointer
     * @param block the block number
     * @return integer variable that indicate different status when we update
     * the block
     */
    public int registerTargetBlock(int cursor, short block) {
        //Find location in block
        int index = cursor / Disk.blockSize;
        
        //inside direct size
        if (index < this.directSize) {
            if (this.direct[index] >= 0) {
                return -1; //block already exists
            } else if (index > 0 && this.direct[index - 1] == -1) {
                return -2; //invalid previous position
            } else {
                direct[index] = block;
                return 0;
            }
        } else if (this.indirect < 0) {  // indirect is not available
            return -3;
        } else {
            // Read Block to indirect & define cursor
            byte[] tempData = new byte[Disk.blockSize];
            SysLib.rawread(indirect, tempData);
            int dist = index - this.directSize;
            // assign indirect
            if (SysLib.bytes2short(tempData, dist * 2) > 0) {
                return -1;
            } else {
                // Write new indirect to DISK
                SysLib.short2bytes(block, tempData, dist * 2);
                SysLib.rawwrite(indirect, tempData);
                return 0;
            }
        }
    }
    
    /**
     * This method take in the block number and update it by writing it back to
     * the disk
     *
     * @param block the block number that we need to update for the indirect
     * variable
     * @return a boolean variable indicating if we succeed or not
     */
    public boolean registerIndexBlock(short block) {
        //Checks if direct is invalid
        for (int i = 0; i < directSize; i++) {
            if (this.direct[i] == -1) {
                return false;
            }
        }
        //Checks if indirect is valid
        if (indirect != -1) {
            return false;
        } else {
            // Read block to indirect
            indirect = block;
            // Set indirect to default
            byte[] tempData = new byte[Disk.blockSize];
            for (int dist = 0; dist < Disk.blockSize / 2; ++dist) {
                SysLib.short2bytes((short) -1, tempData, dist * 2);
            }
            // Write it to the disk
            SysLib.rawwrite(block, tempData);
            return true;
        }
    }
    
    /**
     * Removes and returns the data of the indirect block.
     *
     * @return Data of indirect block
     */
    byte[] unregisterIndexBlock() {
        if (this.indirect >= 0) {
            //write indirect to temp
            byte[] temp = new byte[512];
            SysLib.rawread(this.indirect, temp);
            //writes over indirect
            this.indirect = (short) -1;
            //return temp
            return temp;
        }
        return null;
    }
}
