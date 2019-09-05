/**
 * CSS 430 Program 5: FileSystem.java
 * This class is the main class for the file system.
 * It also carries 8 important system call that interact with
 * other classes. The class gives you the ablity to open,close
 * read and write and generally just acts as a directory.
 *
 * @author Margaret Connor
 * @author Manvir Singh
 * @version 1.1
 * @since 2019-06-07
 */
public class FileSystem{
    private SuperBlock superblock;
    private Directory directory;
    private FileTable fileTable;
    
    public FileSystem(int diskBlocks){
        superblock = new SuperBlock(diskBlocks);
        directory = new Directory(superblock.inodeBlocks);
        fileTable = new FileTable( directory );
        //read the "/" file from disk
        FileTableEntry dirEnt = open("/", "r");
        int dirSize = fsize(dirEnt);
        if(dirSize > 0){
            byte[] dirData = new byte[dirSize];
            read(dirEnt, dirData);
            directory.bytes2directory(dirData);
        }
        close(dirEnt);
    }
    /**
     * This method formats the disk.java's data
     *
     * @param files for specifying the max number
     * of files to be created
     * @return true or false based on its success.
     */
    public boolean format(int files){
        superblock.format(files);
        directory = new Directory(superblock.inodeBlocks);
        fileTable = new FileTable(directory);
        return true;
    }
    
    /**
     * This method opens a file in three modes.
     *"W"= write only
     *"W+"= read/write
     *"a"= append
     * @param: a file name
     * @param: a mode
     * @return: A FileTableEntry obj
     */
    public FileTableEntry open(String filename, String mode) {
        FileTableEntry myNewEntry = fileTable.falloc(filename,mode);
        //checks if the mode is write only mode
        if((mode == ("w")) &&(!deallocAllBlocks(myNewEntry))) {
            return null;
        }
        return myNewEntry;
    }
    
    /**
     * This method writes the contents of buffer to the
     * the file represented by ftEnt.
     * This method also overwrite existing data,or
     * append to the back of the file.
     *
     * @param: FileTableEntry file
     * @param: buffer
     * @return: number of bytes that have been written,
     * or a negative value if encountered an error
     */
    public int write(FileTableEntry ftEnt, byte[] buffer) {
        //Checks for read only mode, returns negative value
        if(ftEnt.mode == "r") {
            return -1;
        }
        int tracker =0;
        int buffSize = buffer.length;
        synchronized (ftEnt) {
            //writes until space in the buffer
            while(buffSize > 0) {

                int myBlockSize = ftEnt.inode.findTargetBlock(ftEnt.seekPtr);
                if(myBlockSize == -1) {
                    short openBlock = (short)superblock.getFreeBlock();
                    
                    int myCheckerFinal = ftEnt.inode.registerTargetBlock
                    (ftEnt.seekPtr, openBlock);
                    
                    if(myCheckerFinal == -2 || myCheckerFinal == -1 ) {
                        return -1;
                    }
                    //indirect block is unavailable
                    if(myCheckerFinal == -3) {
                        short openBlock2 = (short) superblock.getFreeBlock();
                        
                        if(!ftEnt.inode.registerIndexBlock(openBlock2)){
                            return -1;
                        }
                        if(ftEnt.inode.registerTargetBlock(ftEnt.seekPtr,openBlock) != 0 ) {
                            return -1;
                        }
                    }
                    //new position has been found
                    myBlockSize =openBlock;
                }
                
                byte[] myBArray = new byte[512];
                SysLib.rawread(myBlockSize, myBArray);
                //done to get block pointer
                int data2 = ftEnt.seekPtr % 512;
                int blocksRemaining2 = 512 - data2;
                int checkSmallest = Math.min(blocksRemaining2, buffSize);
                
                System.arraycopy(buffer, tracker, myBArray, data2,checkSmallest);//here
                SysLib.rawwrite(myBlockSize,myBArray );
                //updates the seek pointer
                ftEnt.seekPtr += checkSmallest;
                tracker += checkSmallest;
                buffSize -= checkSmallest;
                //updates length of inode, it cannot exceed
                if(ftEnt.seekPtr > ftEnt.inode.length) {
                    ftEnt.inode.length =ftEnt.seekPtr;
                }
            }
            ftEnt.inode.toDisk(ftEnt.iNumber);
            return tracker;
            
        }
    }
    
    /**
     * This method basically closes the files that
     * goes with the FileTableEntry f, it also unregisters
     * f from the user file descriptor table.
     *
     * @param: FileTableEntry that is closing
     * @return: true or false based on its success.
     */
    public boolean close(FileTableEntry f) {
        if(f == null) {
            return false;
        }
        synchronized(f) {
            f.count--;
            if(f.count <= 0) {
                //checks if no other threads are using the file,
                //makes it removable
                return fileTable.ffree(f);
            }
            return true;
        }
    }
    
    /**
     * This method reads upto buffer's length bytes
     * from the file passed in parameter.
     *
     * @param: File table entry file in whch to read from
     * @param: buffer
     * @return: # of bytes that's been read, or a
     * negative value if encountered an error
     */
    public int read(FileTableEntry ftEnt, byte[] buffer) {
        if (buffer == null){
            return -1;
        }
        //this checks for write or append mode
        if((ftEnt.mode == "w")||(ftEnt.mode == "a")) {
            return -1;
        }
        int tracker  = 0;
        int size = buffer.length; // buffers's size
        synchronized(ftEnt) {
            // a while loop to read the bytes
            while(size > 0 && ftEnt.seekPtr <fsize(ftEnt)) {
                int myblockNumber = ftEnt.inode.findTargetBlock(ftEnt.seekPtr);
                
                if(myblockNumber == -1) {
                    break;
                }
                byte[] myTempB = new byte[Disk.blockSize];
                //reading data to buffer
                SysLib.rawread(myblockNumber,myTempB);
                //updating seek pointer
                int data1 = ftEnt.seekPtr % 512;
                int blocksRemaining = 512 - data1;
                int fileRemaining = fsize(ftEnt) - ftEnt.seekPtr;
                // checks space to read
                int checkSmaller = Math.min(blocksRemaining,size);
                int readSizeRemain = Math.min(checkSmaller,fileRemaining);
                
                System.arraycopy(myTempB,data1,buffer,tracker,readSizeRemain);
                
                tracker  += readSizeRemain;
                ftEnt.seekPtr += readSizeRemain;
                size -= readSizeRemain;
            }
            return tracker;
        }
    }
    
    private final int SEEK_SET = 0;
    private final int SEEK_CUR = 1;
    private final int SEEK_END = 2;
    /**
     * This method updates the seeks pointer to file's
     * FileTableEntry.
     *
     * @param: fileTableEntry in which to seek data from
     * @param: offset, is the distance
     * @param: whence is the mode's #
     * @return: an number return the location of the seek value.
     */
    public int seek(FileTableEntry fd, int offset, int whence) {
        synchronized (fd) {
            //the file's seek pointer distance is set to beggining
            //of the file
            if(whence == SEEK_SET ) {
                if(offset <= fsize(fd) && offset >=0) {
                    fd.seekPtr = offset;
                }
            //the file's seek pointer distance is set to current
            //value and the distance.
            }else if(whence == SEEK_CUR ) {
                if(((fd.seekPtr + offset) >= 0) && fd.seekPtr + offset <= fsize(fd)) {
                    fd.seekPtr += offset;
                }
            }
            //the file's seek pointer distance is set to size
            //of the file and the distance (offset
            if(whence == SEEK_END ) {
                //allignning reading from end
                if ( fsize(fd) + offset <= fsize(fd) && fsize(fd) + offset >= 0) {
                    fd.seekPtr = fsize(fd) + offset;
                } else {
                    return -1;
                }
            }
            return fd.seekPtr;
        }
    }
 
    /**
     * This method deletes the file thats passes in the
     * parameter. Also frees the blocks used by the file.
     *
     * @param: a filename
     * @return: true or false based on its success.
     */
    boolean delete(String filename){
        FileTableEntry file = this.open(filename, "w");
        short inum = file.iNumber;
        return this.close(file) && this.directory.ifree(inum);
    }
    
    /**
     * This method returns the size, in bytes of the p
     * provided file.
     *
     * @param: file, provided file.
     * @return: size (in bytes)
     */
    int fsize(FileTableEntry fileEntry){
        FileTableEntry file = fileEntry;
        synchronized(file){
            return file.inode.length;
        }
    }
    
    /*
     *This method syncs dats from directory block
     */
    public void sync(){
        FileTableEntry myNewEntry2 = open("/", "w");
        byte[] myByte = directory.directory2bytes();
        //writes info back to disk from directory
        write(myNewEntry2, myByte);
        close(myNewEntry2);
        //writing back all info to disk
        superblock.sync();
    }
    
    
    /**
     * This method deallocates the block for the
     * requested file.
     *
     * @param: FileTableEntry to be deallcoated
     * @return: true or false based on its success.
     */
    private boolean deallocAllBlocks(FileTableEntry ftEnt){
        if(ftEnt.inode.count !=1) {
            return false;
        }
        byte [] newB = ftEnt.inode.unregisterIndexBlock();
        if(newB != null) {
            short blockNumber2;
            while ((blockNumber2 = SysLib.bytes2short(newB, 0)) != -1) {
                superblock.returnBlock(blockNumber2);
            }
        }
        for(int i=0; i< 11; i++) {
            if(ftEnt.inode.direct[i] != -1) {
                superblock.returnBlock(ftEnt.inode.direct[i]);
                ftEnt.inode.direct[i] = -1;
            }
        }
        //updates inode
        ftEnt.inode.toDisk(ftEnt.iNumber);
        return true;
    }
}
