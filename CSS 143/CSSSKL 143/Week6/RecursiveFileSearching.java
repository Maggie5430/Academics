package lab6;

import java.io.File;
import java.util.ArrayList;

/**
 * CSSSKL 143 B, Winter 2018 Lab 6.
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class RecursiveFileSearching {

    private static ArrayList<File> moreDirectory = new ArrayList<>();

    /**
     * Testing driver for RecursiveFileSearching
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(searchFiles(new File("c:\\"), "hw3.zip"));
        System.out.println(searchFiles2(new File("c:\\"), "hw3.zip"));
    }

    /**
     * searches through directory for file and saves all other files in array 
     * lists
     * @param path
     * @param target
     * @return
     */
    public static String searchFiles(File path, String target) {
        if (path.isFile()) {
            return path.getName();
        } else if (path.isDirectory()) {
            for (int i = 0; i < path.length(); i++) {
                if (path.listFiles()[i].isFile()) {
                    if (path.listFiles()[i].getName().equals(target)) {
                        return path.listFiles()[i].getPath();
                    }
                } else {
                    moreDirectory.add(path.listFiles()[i]);
                }
            }
        }
        return "File not found";
    }

    /**
     * searches through directory for file using recursion
     * @param path
     * @param target
     * @return
     */
    public static String searchFiles2(File path, String target) {
        if (!path.isDirectory()) {
            return "Not directory";
        }
        for (int i = 0; i < path.length(); i++) {
            if (path.listFiles()[i].isFile()) {
                if (path.listFiles()[i].getName().equals(target)) {
                    return path.listFiles()[i].getPath();
                }
            } else {
                if (!searchFiles2(path.listFiles()[i], target).equals("File not found")) {
                    return searchFiles2(path.listFiles()[i], target);
                }
            }
        }
        return ("File not found");
    }
}
