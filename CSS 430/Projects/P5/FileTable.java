
/**
 * CSS 430 Program 5: FileTable.java
 *
 * The file system maintains the file table shared among all user threads. When
 * a user thread opens a file, it follows the sequence listed below:
 * (1) The user thread allocates a new entry of the user file descriptor table
 * in its TCB. This entry number itself becomes a file descriptor number. The
 * entry maintains a reference to a file (structure) table entry.
 * (2) The user thread then requests the file system to allocate a new entry of
 * the system-maintained file (structure) table. This entry includes the seek
 * pointer of this file, a reference to the inode corresponding to the file,the
 * inode number, the count to maintain #threads sharing this file (structure)
 * table, and the access mode. The seek pointer is set to the front or the tail
 * of this file depending on the file access mode.
 * (3) The file system locates the corresponding inode and records it in this
 * file (structure) table entry.
 * (4) The user thread finally registers a reference to this file (structure)
 * table entry in its file descriptor table entry of the TCB.
 *
 * @author Margaret Connor
 * @author Manvir Singh
 * @version 1.1
 * @since 2019-06-07
 */
import java.util.Vector;

public class FileTable {
    
    private Vector table;         // the actual entity of this file table
    private Directory dir;        // the root directory
    
    public FileTable(Directory directory) { // constructor
        table = new Vector();     // instantiate a file (structure) table
        dir = directory;           // receive a reference to the Director
    }                             // from the file system
    
    /**
     * Allocates a FileTableEntry for the provided file.
     *
     * @param filename, file name
     * @param mode, open type ["w","r","+r", "a"]
     * @return allocated FileTableEntry
     */
    public synchronized FileTableEntry falloc(String filename, String mode) {
        // allocate a new file (structure) table entry for this file name
        // allocate/retrieve and register the corresponding inode using dir
        // increment this inode's count
        // immediately write back this inode to the disk
        // return a reference to this file (structure) table entry
        Inode myInode = null;
        short num;
        while (true) {
            if (filename.equals("/")) {
                num = 0;
            } else {
                num = this.dir.namei(filename);
            }
            if (num < 0) {
                if (mode.equals("r")) {
                    return null;
                }
            } else {
                //recieve the new inode
                myInode = new Inode(num);
                if (myInode.equals("r")) {
                    if ((myInode.flag != 0) && (myInode.flag != 1)) {
                        myInode.flag = 1;
                        break;
                    }
                }
                if (myInode.flag != 0 && myInode.flag != 3) {
                    if (myInode.flag == 1 || myInode.flag == 2) {
                        myInode.flag = (short) 4;
                        myInode.toDisk(num);
                    }
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                    }
                    continue;
                }
                
                myInode.flag = 2;
                break;
            }
            num = dir.ialloc(filename);
            myInode = new Inode();
            myInode.flag = 2;
            break;
        }
        //a new file is allocated in the directory.
        myInode.count++;
        myInode.toDisk(num);
        FileTableEntry newEntry = new FileTableEntry(myInode, num, mode);
        table.addElement(newEntry);
        return newEntry;
    }
    
    /**
     * Deallocate (frees) the corresponding file from the FileTable
     * @param ftEnt, file to be deallocated
     * @return sucess
     */
    public synchronized boolean ffree(FileTableEntry ftEnt) {
        // receive a file table entry reference
        // save the corresponding inode to the disk
        // free this file table entry.
        // return true if this file table entry found in my table
        if (this.table.removeElement(ftEnt)) {
            Inode myNode = ftEnt.inode;
            
            myNode.count--;
            int myNewFlag = ftEnt.inode.flag;
            
            if (myNewFlag == 1 || myNewFlag == 2) {
                ftEnt.inode.flag = 0;
            }
            if (myNewFlag == 4 || myNewFlag == 5) {
                ftEnt.inode.flag = 3;
            }
            myNode.toDisk(ftEnt.iNumber);
            notify();
            // return true if this file table entry found in my table
            return true;
        }
        return false;
    }
    
    /**
     * Checks if current FileTable is empty
     * @return true of empty else false
     */
    public synchronized boolean fempty() {
        return table.isEmpty();  // return if table is empty
    }                            // should be called before starting a format
}
