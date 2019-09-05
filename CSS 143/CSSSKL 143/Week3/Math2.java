package lab3;

/**
 * Lab3 CSSKL 143B, Winter 2018 1/25/18
 *
 * This class contains my work from Lab 3: Math2 Class.
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class Math2 {

    public static final double PI = 3.14159265359;
    public static final double E = 2.718281828;

    /**
     * Precondition: None
     * Postcondition: Returns greater number
     * 
     * @param a
     * @param b
     * @return
     */
    public static int max(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    /**
     * Precondition: None
     * Postcondition: Returns smaller number
     * 
     * @param a
     * @param b
     * @return
     */
    public static double max(double a, double b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    /**
     * Precondition: None
     * Postcondition: Returns exponent sum
     * 
     * @param base
     * @param exp
     * @return
     */
    public static int pow(int base, int exp) {
        int solution = 1;
        for (int i = 0; i < exp; i++) {
            solution *= base;
        }
        return solution;
    }
}
