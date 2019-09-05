package lab1a;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Lab1a CSSKL 143B, Winter 2018 1/06/18
 *
 * This class contains my work from Lab 1 A: #2 Arrays.
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class Lab1a2 {

    public static void main(String[] args) {
        int[] data = {3, 1, -8, 4, -5, 2};//declare an array of integers
        double avg;//average sum of data

        //find the aveage of the sum function
        avg = (double) sum(data) / data.length;

        /* Write the contents of the array above to a file, all on one line 
         * separated by a comma */
        try {
            PrintWriter pw = new PrintWriter(new File("data.txt"));
            for (int i = 0; i < data.length; i++) {
                pw.print(data[i]);
                if (i != data.length - 1) {
                    pw.print(", ");
                }
            }

            //Write the sum of the array to the file on the next line
            pw.println(sum(data));
            //Write the average of the array to the third line
            pw.println(avg);

            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Returns the sum of an array.
     *
     * 2.b Write a separate function to sum the contents of an array.
     *
     * @param data Array of integers
     * @return the sum of the array
     */
    public static int sum(int[] data) {
        int sum = 0;
        for (int i = 0; i < data.length; i++) {
            sum += data[i];
        }
        return sum;
    }

    /**
     * Returns the position of a target value in an array of integers.
     *
     * 2.d Write a function to find the index of a specified element
     *
     * @param data Array of integers
     * @param target Integer to be found
     * @return Index position of target integer
     */
    public int indexOf(int[] data, int target) {
        //finds target
        for (int i = 0; i < data.length; i++) {
            if (data[i] == target) {
                return i;
            }
        }
        System.out.println("target does not exist");
        return -1;
    }

    /**
     * Returns the sum of positive integers in an array.
     *
     * 2.e Write a function to sum up only positive integers in an array
     *
     * @param data Array of integers
     * @return The sum of only the positive integers in an Array
     */
    public int positiveSum(int[] data) {
        int positiveSum = 0;
        
        //adds positive values
        for (int i = 0; i < data.length; i++) {
            if (data[i] > 0) {
                positiveSum += data[i];
            }
        }
        System.out.println(positiveSum);
        return positiveSum;
    }

    /**
     * Populates an array of integers with use inputed values.
     *
     * 2.f Write a function to populate an integer array with values obtained
     * from the console
     *
     * @param data Array of integers.
     */
    public void populateArray(int[] data) {
        Scanner userInfo = new Scanner(System.in);

        //populates array
        for (int i = 0; i < data.length; i++) {
            System.out.println("Enter " + (data.length - i)
                    + " more integer Values");
            data[i] = userInfo.nextInt();

        }
    }
}
