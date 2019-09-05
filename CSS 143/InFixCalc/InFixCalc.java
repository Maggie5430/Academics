/* * InFixCalc, V1.0 (concept borrowed from Carol Zander's Infix Calculator)
 * Exercise author: Rob Nash
 * Complete the calculate() function below to build a simple, infix
 * calculator that evaluates a compound expression from left to right,  
 * regardless of operator precedence
 *
 * Example: " 1 * -3 + 6 / 3"
 * Execution: calculate 1 * -3 first, then -3 + 6 next, then 3 / 3 
 * last to obtain the value 1
 * 
 * Solution by Margaret Connor
 */
package infixcalc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.PrintWriter;

/**
 * Lab1a CSSKL 143B, Winter 2018 1/06/18
 *
 * Infix Calculator that evaluates a compound expression from left to right.
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class InFixCalc {

    /**
     * Reads compound expressions from text file to inputData.txt and writes the
     * answers to output.txt.
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner fileIn;
        PrintWriter fileOut;

        try {
            fileIn = new Scanner(new File("inputData.txt"));
            fileOut = new PrintWriter(new File("output.txt"));

            /* loops through all line in inputData.txt calculates them and 
             * prints the answer into output.txt */
            while (fileIn.hasNext()) {
                fileOut.println(calculate(fileIn.nextLine()));
            }

            fileIn.close();
            fileOut.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Returns evaluation of a compound expression from left to right,
     * regardless of operator precedence.
     *
     * @param input compound expression
     * @return answer
     */
    public static int calculate(String input) {
        int lhs, rhs; //short for left-hand & right-hand side
        char operation;

        /* Use tokenizer to assigning lhs to the first token. The next token
        will be an operator and the token after will be rhs. */
        StringTokenizer inputTokenizer = new StringTokenizer(input);
        lhs = Integer.parseInt(inputTokenizer.nextToken());

        //loop all rhs into lhs by their preceding respective operation. 
        while (inputTokenizer.hasMoreTokens()) {
            operation = inputTokenizer.nextToken().charAt(0);
            rhs = Integer.parseInt(inputTokenizer.nextToken());

            //switch used to identify and apply operation.
            switch (operation) {
                case '+':
                    lhs += rhs;
                    break;
                case '-':
                    lhs -= rhs;
                    break;
                case '*':
                    lhs *= rhs;
                    break;
                case '/':
                    lhs /= rhs;
                    break;
            }
        }
        return lhs;
    }
}
