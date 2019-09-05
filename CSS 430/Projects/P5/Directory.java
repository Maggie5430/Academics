/**
 * CSS 430 Program 5: Directory.java
 *
 * Directory maintains each file in a different directory entry that contains
 * its file name (maximum 30 characters; 60 bytes in Java) and the corresponding
 * inode number. The directory receives the maximum number of inodes to be
 * created, (i.e., thus the max. number of files to be created) and keeps track
 * of which inode numbers are in use.
 *
 * @author Margaret Connor
 * @author Manvir Singh
 * @version 1.1
 * @since 2019-06-07
 */
public class Directory {
    
    private static int maxChars = 30; // Max characters of each file name
    // Directory entries
    private int fsize[];        // element file size (length of filename).
    private char fnames[][];    // element file name.
    
    /**
     * Constructor for the Directory class
     *
     * @param - maxInumbers, maximim number of iNodes/Files to be created
     */
    public Directory(int maxInumber) {
        fsize = new int[maxInumber];
        fnames = new char[maxInumber][maxChars];
        //initialize all files to 0
        for (int i = 0; i < maxInumber; i++) {
            fsize[i] = 0;
        }
        //initilize root
        String root = "/";
        fsize[0] = root.length();
        root.getChars(0, fsize[0], fnames[0], 0);
    }
    
    /**
     * Assumes data[] received directory information from disk, initializes the
     * director instance with their data[]
     *
     * @param - data, directory info
     */
    public void bytes2directory(byte data[]) {
        //invalid parameter
        if (data == null || data.length == 0) {
            SysLib.cerr((String) "Invalid byte to directory parameter\n");
            return;
        }
        
        /* Each entry is 64 bytes. 4 bytes for file size (1 int), 60 bytes for
         * file name (30 chars). */
        //converts every 4 bytes to int (fsize)
        int dist = 0; //cursor index location in array
        for (int entry = 0; entry < this.fsize.length; entry++) {
            fsize[entry] = SysLib.bytes2int(data, dist + 4 * entry);
        }
        //converts every next 60 bytes to char[] (fname)
        dist = 4 * this.fsize.length;
        String fname; //file name
        for (int entry = 0; entry < this.fnames.length; entry++) {
            fname = new String(data, dist + 2 * maxChars * entry, maxChars * 2);
            fname.getChars(0, fsize[entry], fnames[entry], 0);
        }
        
    }
    
    /**
     * Converts and returns directory information into a plain byte array to be
     * written back to disk.
     *
     * @return directory info in byte array
     */
    public byte[] directory2bytes() {
        /* each entry is 64 bytes. 4 bytes for file size (1 int), 60 bytes for
         * file name (30 chars) */
        int entries = this.fsize.length;
        byte[] dirData = new byte[entries * 4 + entries * maxChars * 2];
        
        int dist = 0;//cursor index location in array
        
        //converts fsize (int) to bytes
        for (int entry = 0; entry < entries; entry++) {
            SysLib.int2bytes(this.fsize[0], dirData, dist + 4 * entry);
        }
        
        //converts fname (char) to string and string to bytes
        byte[] nameData; //for string to byte conversion
        dist = 4 * this.fsize.length;
        String fname; //file name
        for (int entry = 0; entry < entries; entry++) {
            fname = new String(this.fnames[entry], 0, this.fsize[entry]);
            nameData = fname.getBytes();
            System.arraycopy(nameData, 0, dirData, dist + 2 * maxChars * entry,
                             nameData.length);
        }
        return dirData;
    }
    
    /**
     * Allocate a new inode for this file
     *
     * @param - filename, name of new file
     */
    public short ialloc(String filename) {
        for (int i = 0; i < fsize.length; i++) {
            if (fsize[i] == 0) {
                fsize[i] = Math.min(filename.length(), maxChars);
                filename.getChars(0, fsize[i], fnames[i], 0);
                return (short) i;
            }
        }
        return -1;
    }
    
    /**
     * Deallocates this inumber (inode number)
     *
     * @param - iNumber, corresponding file to be freed (deleted)
     */
    public boolean ifree(short iNumber) {
        //if not allocated return false
        if (iNumber < 0 || fsize[iNumber] <= 0) {
            return false;
        }
        //if allocated, change iNumber to 0 return true
        if (fsize[iNumber] > 0) {
            fsize[iNumber] = 0;
            return true;
        }
        return false;
    }
    
    /**
     * Returns the inumber corresponding to this filename
     *
     * @param - filename, name of file
     */
    public short namei(String filename) {
        boolean found; //flag
        for (int entry = 0; entry < fsize.length; entry++) {
            found = false;
            // Okay,they are the same size. It has potential
            if (fsize[entry] == filename.length()) {
                found = true;
                for(int charIndex = 0; charIndex < filename.length();
                    charIndex ++){
                    if (fnames[entry][charIndex] != filename.charAt(charIndex)){
                        found = false;
                        continue;
                    }
                }
                
            }
            
            if(found){
                return (short) entry;
            }
        }
        return -1;
    }
}
