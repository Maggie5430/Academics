package lab1a.standalone.program;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Lab1a CSSSKL 143B, Winter 2018 1/06/18
 *
 * This class contains my work for 7.File IO using Scanner.
 *
 * @author Margaret Connor
 * @Version 1.0
 */
public class FileIOUsingScanner {

    /**
     * Reads file to console bases on user input
     * 
     * @param args  the command line arguments
     */
    public static void main(String[] args) {
        String fileName;
        Scanner readFile;
        Scanner userInput = new Scanner(System.in);
        
        //promps user for input
        System.out.print("What file would you like to view? ");
        fileName = userInput.next();
        
        try {
            readFile = new Scanner(new File(fileName));
            
            //reads txt in file to console line by line
            while (readFile.hasNext()) {
                System.out.println(readFile.nextLine());
            }
            
            readFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("No file found");
        }
    }
}
