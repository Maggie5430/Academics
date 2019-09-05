package fractionsv2;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * CSS 143 B, Winter 2018 Fractions v2
 *
 * @author Margaret Connor
 * @version 2.0
 */
public class FractionsV2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int numerator, denominator, divide;
        ArrayList fractionArray = new ArrayList();
        Fraction newFraction;
        Scanner fileIn;

        //Read file line by line 
        try {
            fileIn = new Scanner(new File("fractions.txt"));
            while (fileIn.hasNext()) {
                String currentLine = fileIn.nextLine();

                //separate numerator and denominator
                divide = currentLine.indexOf("/");
                numerator = Integer.parseInt(currentLine.substring(0, divide));
                denominator
                        = Integer.parseInt(currentLine.substring(divide + 1));

                //Divide by 0 catch
                if (denominator != 0) {
                    /* Loop over list of FractionCounters if no 
                     * compareAndIncrement returns true, then create new 
                     * FractionCounter */
                    newFraction = new Fraction(numerator, denominator);
                    boolean exists = false;
                    for (int i = 0; i < fractionArray.size(); i++) {
                        exists = ((FractionCounter) fractionArray.get(i))
                                .compareAndIncrement(newFraction);
                        if (exists) {
                            break;
                        }
                    }
                    if (!exists) {
                        fractionArray.add(new FractionCounter(newFraction));
                    }
                } else {
                    System.out.println("Divide by 0 Error");
                }

            }

            //close and catch
            fileIn.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        //print array to console
        for (int i = 0; i < fractionArray.size(); i++) {
            System.out.println(fractionArray.get(i));
        }
    }

}
