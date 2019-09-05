package lab1a;

import java.util.Scanner;

/**
 * Lab1a 
 * CSSKL 143B, Winter 2018 
 * 1/05/18
 *
 * This class contains my work from Lab 1 A: #1 Control structures.
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class Lab1a {

    public static void main(String[] args) {
        int score; //score of 5 grades
        double average; //average of 5 grades

        //Write a for loop to print the first 10 digits.
        for (int i = 0; i <= 10; i++) {
            System.out.println(i);
        }

        // Write a while loop to print the first 10 even digits.
        for (int i = 0; i <= 10; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }

        /* Write a loop to sum the first 5 integer quiz scores entered by the 
         * console */
        score = 0;
        Scanner userInput = new Scanner(System.in);

        for (int i = 0; i < 5; i++) {
            System.out.println("Enter a score");
            score += userInput.nextInt();
        }

        // Average the integers entered in the previous problem.
        average = (double) score / 5;

        System.out.println("The sum of your scores is: " + score);
        System.out.println("Your average is: " + average);
        System.out.print("Grade: ");

        // Use an if statement to produce a letter grade {A,B,C,D,F}.
        if (average >= 90) {
            System.out.println("A");
        } else if (average >= 84.5) {
            System.out.println("B");
        } else if (average >= 74.5) {
            System.out.println("C");
        } else if (average >= 64.5) {
            System.out.println("D");
        } else {
            System.out.println("F");
        }

        // Rewrite the if statement above using a switch statement.
        switch ((int) average) {
            case 90:
                System.out.println("Grade: A");
                break;
            case 85:
                System.out.println("Grade: B");
                break;
            case 80:
                System.out.println("Grade: C");
                break;
            case 75:
                System.out.println("Grade: D");
                break;
            case 70:
                System.out.println("Grade: F");
                break;
            default:
                System.out.println("Grade: Error");
                break;
        }
        /*
         * Are there any problems with this?
         * Since switch statements can only be used with integers and characters
         * all values that are not 90,85,80,75,and 70 do not apply to a case.
         */
    }
}
