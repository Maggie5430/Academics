import java.io.File;

/**
 * CSS 143 B, Winter 2018 Recursion
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class FindFile {

    final private int MAX_FILES;
    private Stack foundPath;

    /**
     * Postconditions: Creates an instance of the FindFile class
     * 
     * @param maxFiles
     */
    public FindFile(int maxFiles) {
        this.MAX_FILES = maxFiles;
        this.foundPath = new Stack();
    }

    /**
     * Recursive method that searches for a file by it's name Precondition: 
     * Target file is a directory, Postcondition: sets path of found file(s) in
     * a stack. 
     * 
     * @param targetFile
     * @param pathToSearch
     * @throws Exception
     */
    public void directorySearch(String targetFile, String pathToSearch)
            throws Exception {
        System.out.println(pathToSearch);
        //if the max number of files is found throw exception
        File target = new File(pathToSearch);
        if (!target.isDirectory()) {
            throw new IllegalArgumentException();
        } else {
            //all files and directories in current directory 
            File[] openDirectory = target.listFiles();
            for (int i = 0; i < openDirectory.length; i++) {
                if (openDirectory[i].isDirectory()) {
                    //If directory recursively call to search directory
                    directorySearch(targetFile,
                            openDirectory[i].getAbsolutePath());
                } else {
                    //If file test if file is equal to target name
                    if (openDirectory[i].getName()
                            .equalsIgnoreCase(targetFile)) {
                        if (this.foundPath.size() == MAX_FILES) {
                            throw new Exception("Max number of files have be "
                                    + "reached (" + MAX_FILES + ")");
                        } else {
                            this.foundPath.push(openDirectory[i]
                                    .getAbsoluteFile());
                        }
                    }
                }
            }
        }

    }

    /**
     * Postconditions: Returns the number of files found
     * 
     * @return
     */
    public int getCount() {
        return this.foundPath.size();
    }

    /**
     * Postcondition: Returns all the files (up to the provided max) that were
     * found 
     * 
     * @return
     * @throws ItemNotFoundException
     */
    public String[] getFiles() throws ItemNotFoundException {
        if (foundPath.size() == 0) {
            //if no file was found
            throw new ItemNotFoundException();
        } else {
            String[] toReturn = new String[foundPath.size()];
            for (int i = 0; i < toReturn.length; i++) {
                toReturn[i] = foundPath.pop().toString();
            }
            return toReturn;
        }
    }
}
