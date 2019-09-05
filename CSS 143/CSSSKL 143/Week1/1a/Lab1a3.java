package lab1a;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Lab1a 
 * CSSKL 143B, Winter 2018 
 * 1/06/18
 * 
 * This class contains my work from Lab 1 A: #3 File IO.
 * 
 * @author Margaret Connor
 * @version 1.0
 */
public class Lab1a3 {

    /**
     * Reads the content of a file and prints it to the console.
     * 
     * @param args
     */
    public static void main(String[] args) {
        //reads in line by line and outprints to console
        try {
            Scanner readFile = new Scanner(new File("data.txt"));
            while (readFile.hasNext()) {
                System.out.println(readFile.nextLine());
            }

        } catch (FileNotFoundException e) {
            System.out.println("No file found");
        }
    }
}
