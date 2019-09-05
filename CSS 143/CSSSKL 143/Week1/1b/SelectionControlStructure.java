package lab1a.standalone.program;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Lab1a CSSSKL 143B, Winter 2018 1/06/18
 *
 * This class contains my work for 5&6.A Second Java Program (Selection Control
 * Structures)and writes to data2.txt file.
 *
 * @author Margaret Connor
 * @Version 1.0
 */
public class SelectionControlStructure {

    /**
     * Finds the input's GCD (greatest common denominator).
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        PrintWriter pw;
        int userNumber;
        
        //promps user input
        System.out.print("Enter a number: ");
        userNumber = userInput.nextInt();
        
        try {
            pw = new PrintWriter(new File("data2.txt"));
            
            //handles special cases where user enters 1 or 0
            if ((userNumber != 1) && (userNumber != 0)) {
                System.out.print("Divisors are ");
                pw.print("Divisors are ");
                
                //calculates GCD from all values less then userInput
                for (int i = userNumber - 1; i > 0; i--) {
                    if (userNumber % i == 0) { 
                        System.out.print(i + " ");
                        pw.print(i + " ");
                    }
                }
            } else {
                System.out.println("Please enter a number other than 0 or 1");
            }
            
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
