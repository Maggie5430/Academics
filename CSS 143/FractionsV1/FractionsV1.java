/* 
 * Q: Can you complete this without using arrays? What is the least number of 
 * variables you can use to solve this problem?
 * A: Yes you could complete this specific problem without using an array, as 
 * long as the number of fractions were finite but it would not be unpracticle.
 * A: The least number of primitive variables necessarry would be 32, one
 * variable for each fraction(31) and one variable to count the numbers of 
 * duplicates for the current tested fraction. If you use other (non primative)
 * variable you could complete this with just one array.
 * 
 * Q: Can you use just one array to solve this? What would the data type be of 
 * that array?
 * A: Yes you can use just one array of either Strings or Doubles then run that
 * array through a counter and converter.
 *
 * Q: What does it mean to make a class so another class is inside (or part of)
 * the first class, so that it is composed of other data types?  What does 
 * "composition" mean in that case?  How is it done?
 * A: It means to use an instance of an object from another class in a class. 
 * Composition means to be part of or used in a class. This is simply done by
 * calling a constructor of an object in a class.
 * 
 * Q: What are some solutions to the reduction problem other than Euclid's 
 * GCD algorithm?
 * A: Take an array of fractions and compare them to all other items in the
 * array by looping. Count when one equals another and mark them so not counted
 * again. Then outprint the fraction and count.
 */
package fractionsv1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

/**
 * CSS 143 B, Winter 2018 Fractions v1
 *
 * @author Margaret Connor
 * @version 1.2
 */
public class FractionsV1 {

    /**
     * This program takes in fractions formated as "n/d" from a text file and
     * identifies how many times a fraction was repeated by value.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int count = countEntries();
        inFractions(count);
    }

    /**
     * Convert fractions into an array of their numerical values and an array of
     * their fraction form, then uses printDuplicates to sort all duplicates.
     *
     * @param count
     */
    public static void inFractions(int count) {
        int divide; //index position of "/" 
        double numerator;
        double deominator;
        String currentLine;
        String[] fractionArray = new String[count]; //array of fractions
        Double[] valueArray = new Double[count]; // array of fraction values
        Scanner fileInfoScanner;

        try {
            fileInfoScanner = new Scanner(new File("fractions.txt"));

            for (int i = 0; i < count; i++) {
                currentLine = fileInfoScanner.nextLine();
                
                //assigns the fraction in string to an array
                fractionArray[i] = currentLine;
                
                //assigns the numerical value of each fraction to an array
                divide = currentLine.indexOf("/");
                numerator = Double.parseDouble(currentLine.substring(0, divide));
                deominator = Double.parseDouble(currentLine.substring(divide + 1));
                valueArray[i] = numerator / deominator;
            }
            fileInfoScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return;
        }
        printDuplicates(fractionArray, valueArray, count);
    }

    /**
     * Returns number of fractions in the file
     *
     * @return number of fractions
     */
    public static int countEntries() {
        int count = 0;
        Scanner fileCountScanner;

        try {
            fileCountScanner = new Scanner(new File("fractions.txt"));
            while (fileCountScanner.hasNextLine()) {
                //counts for each line
                fileCountScanner.nextLine();
                count++;
            }
            fileCountScanner.close();
            return count;
        } catch (FileNotFoundException e) {

            System.out.println("File not found");
            return 0;
        }
    }

    /**
     * Checks for duplicates then prints the fraction and the number or times
     * they appear to console.
     *
     * @param fractionArray array of fractions
     * @param valueArray array of fraction values
     * @param count the number of fractions
     */
    public static void printDuplicates(String[] fractionArray, Double[] valueArray, int count) {
        // Loop to compare all fraction values to all other fraction values
        for (int i = 0; i < count; i++) {
            int repetition = 1;
            for (int j = i + 1; j < count; j++) {

                /* Compares fraction value to all other values then sets their 
                 * values to 0 to prevent being counted again*/
                if (Objects.equals(valueArray[j], valueArray[i])) {
                    repetition += 1;
                    valueArray[j] = 0.0;
                }
            }
            
            /* Stops values that were converted to 0 in the previously from 
             * being printed to console*/
            if (valueArray[i] != 0.00) {
                System.out.println(fractionArray[i] + " has a count of "
                        + repetition);
            }
        }
    }
}
