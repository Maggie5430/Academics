package lab1a.standalone.program;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Lab1a CSSSKL 143B, Winter 2018 1/06/18
 *
 * This class contains my work for 4&6.Standalone program using loop structure 
 * part1
 *
 * @author Margaret Connor
 * @Version 1.0
 */
public class Lab1aStandaloneProgram {

    /**
     * Calculates factorial based on the user inputed integer and writes to 
     * data1.txt file.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int product = 1;
        int userNumber;
        PrintWriter pw;
        /* Should int product start at 0?
         * No because 0 * n will always equal 0. */

        //props user input
        Scanner userInput = new Scanner(System.in);
        System.out.print("Enter a number: ");
        userNumber = userInput.nextInt();

        try {
            //writes to file
            pw = new PrintWriter(new File("data1.txt"));
            pw.print("Enter a number: " + userNumber +"\n");

            //multiply until reaches user input
            for (int i = 1; i <= userNumber; i++) {
                product *= i;
                
                //prints each step with symbol where needed
                System.out.print(i);
                pw.print(i);
                if (i != userNumber) {
                    System.out.print(" * ");
                    pw.print(" * ");
                }
            }
            //prints final awnswer
            System.out.println("\n" + product);
            pw.print("\n" + product);

            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

    }
}
