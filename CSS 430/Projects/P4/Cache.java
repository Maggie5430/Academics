/**
 * Margaret Connor
 * CSS430 Spring 2019 UW
 * P4 05/27/19
 */
import java.util.ArrayList;

public class Cache {
    
    private Block cache[];
    private ArrayList<Integer> clock; //Queue of block index (FIFO)
    
    /**
     * The constructor: allocates a cacheBlocks number of cache blocks, each
     * containing blockSize-byte data, on memory
     *
     * @param blockSize - Size of byte data
     * @param cacheBlocks - Number of blocks
     */
    public Cache(int blockSize, int cacheBlocks) {
        cache = new Block[cacheBlocks];
        clock = new ArrayList<>();
        for (int i = 0; i < cacheBlocks; i++) {
            cache[i] = new Block(blockSize);
        }
    }
    
    /**
     * reads into the buffer[ ] array the cache block specified by blockId from
     * the disk cache if it is in cache, otherwise reads the corresponding disk
     * block from the disk device. Upon an error, it should return false,
     * otherwise return true.
     *
     * @param blockId - Identity of block
     * @param buffer - Reads into this byte array
     * @return boolean - Upon error return false else true
     */
    public synchronized boolean read(int blockId, byte buffer[]) {
        if (blockId < 0) {
            SysLib.cerr((String) "Invalid blockId for cread\n");
            return false;
        }
        
        //Scan all the cache entries
        for (int i = 0; i < cache.length; ++i) {
            //if found read from cache
            if (cache[i].blockId == blockId) {
                byte[] tempBuffer = (byte[]) this.cache[i].page;
                System.arraycopy(tempBuffer, 0, buffer, 0,
                                 this.cache[i].page.length);
                this.cache[i].ref = true;
                return true;
            }
        }
        
        // If not in cache and free blocks exist
        if (clock.size() < cache.length) {
            //find free block
            for (int i = 0; i < cache.length; i++) {
                if (this.cache[i].blockId == -1) {
                    this.cache[i].writeBlockToDisk();
                    //read new page from disk
                    this.cache[i].readBlockFromDisk(blockId);
                    System.arraycopy(this.cache[i].page, 0, buffer, 0,
                                     this.cache[i].page.length);
                    //enqueue to clock
                    this.clock.add((Integer) i);
                    return true;
                }
            }
        }
        
        // If the cache is full and there is not a free cache block
        if (clock.size() >= cache.length) {
            //find victim
            int victimIndex = this.findVictim();
            //if dirty write back to disk
            this.cache[victimIndex].writeBlockToDisk();
            //remove victim from FIFO queue
            this.clock.remove((Integer) victimIndex);
            //read new page from disk
            this.cache[victimIndex].readBlockFromDisk(blockId);
            //enqueue new page
            this.clock.add((Integer) victimIndex);
            //return
            System.arraycopy(this.cache[victimIndex].page, 0, buffer, 0,
                             this.cache[victimIndex].page.length);
            return true;
        }
        return false;
    }
    
    /**
     * writes the buffer[]array contents to the cache block specified by blockId
     * from the disk cache if it is in cache, otherwise finds a free cache block
     * and writes the buffer [] contents on it. No write through. Upon an error,
     * it should return false, otherwise return true.
     *
     * @param blockID -
     * @param buffer -
     * @return boolean -
     */
    public synchronized boolean write(int blockId, byte buffer[]) {
        if (blockId < 0) {
            return false;
        }
        
        //Scan all the cache entries
        for (int i = 0; i < cache.length; ++i) {
            //if found write buffer to cache
            if (cache[i].blockId == blockId) {
                //write to block
                this.cache[i].writeBlock(blockId, buffer);
                return true;
            }
        }
        
        // If not in cache and free block
        if (clock.size() < cache.length) {
            //find free block
            for (int i = 0; i < cache.length; i++) {
                if (this.cache[i].blockId == -1) {
                    //write to empty block
                    this.cache[i].writeBlock(blockId, buffer);
                    //enqueue to clock
                    this.clock.add((Integer) i);
                    return true;
                }
            }
        }
        
        if (clock.size() >= cache.length) {
            //find victim
            int victimIndex = this.findVictim();
            //if dirty write back to disk
            this.cache[victimIndex].writeBlockToDisk();
            //remove victim from FIFO queue
            this.clock.remove((Integer) victimIndex);
            //write to victim block
            this.cache[victimIndex].writeBlock(blockId, buffer);
            //enqueue new page
            this.clock.add((Integer) victimIndex);
            //return
            return true;
        }
        
        //SysLib.rawwrite(blockId, buffer);
        return false;
    }
    
    /**
     * Writes all dirty blocks back to DISK file.
     */
    public synchronized void sync() {
        for (int i = 0; i < cache.length; i++) {
            if (cache[i].dirt) {
                cache[i].writeBlockToDisk();
            }
        }
    }
    
    /**
     * Invalidates all cached blocks, makes the cache empty.
     */
    public synchronized void flush() {
        this.sync(); //sync existing data
        
        for (int i = 0; i < cache.length; i++) {
            cache[i].flushBlock();
            clock.clear();
        }
    }
    
    /**
     * Find a victim using the enhanced second-chance algroithm if the
     * clockCache is full.
     *
     * @return cache index of victim
     */
    private synchronized int findVictim() {
        while (true) {
            for (int i = 0; i < clock.size(); i++) {
                if (!cache[clock.get(i)].ref) {
                    return clock.get(i);
                } else {
                    //its reference bit is cleared, and its arrival time is
                    //reset to the current time.
                    cache[clock.get(i)].ref = false;
                }
            }
        }
    }
}

class Block {
    
    int blockId; // block frame number. Set to -1 if this entry is not valid
    boolean ref; // reference bit, set to 1 if read or write recently
    boolean dirt; // dirty bit, set to 1 if contents does not match DISK
    byte page[]; // data
    
    /**
     * The constructor: allocates a block with an blockId, page, reference bit,
     * and new byte.
     * @param blockSize - Size of block
     */
    Block(int blockSize) {
        this.blockId = -1;
        this.ref = false;
        this.dirt = false;
        this.page = new byte[blockSize];
    }
    
    /**
     * Reads the given block ID from Disk into current block and updates
     * reference bit.
     *
     * @param blockId - Id number of block in memory
     */
    synchronized void readBlockFromDisk(int blockId) {
        this.blockId = blockId;
        this.ref = true;
        this.dirt = false;
        //copy from disk
        byte[] tempBuffer = new byte[this.page.length];
        SysLib.rawread((int) blockId, (byte[]) tempBuffer);
        System.arraycopy(tempBuffer, 0, this.page, 0, this.page.length);
    }
    
    /**
     * Writes given page to disk (syncs page).
     */
    synchronized void writeBlockToDisk() {
        if (blockId != -1 && this.dirt) {
            byte[] temp = page;
            SysLib.rawwrite(this.blockId, temp);
            this.dirt = false;
        }
    }
    
    /**
     * Writes given data to the cache.
     *
     * @param blockId - ID of given block
     * @param buffer - Data of given block
     */
    synchronized void writeBlock(int blockId, byte[] buffer) {
        this.blockId = blockId;
        byte[] tempBuffer = new byte[this.page.length];
        System.arraycopy(buffer, 0, tempBuffer, 0, this.page.length);
        System.arraycopy(tempBuffer, 0, this.page, 0, this.page.length);
        this.ref = true;
        this.dirt = true;
    }
    
    /**
     * Invalidates block.
     */
    synchronized void flushBlock() {
        this.blockId = -1;
        this.ref = false;
        this.dirt = false;
        this.page = new byte[page.length];
    }
}
