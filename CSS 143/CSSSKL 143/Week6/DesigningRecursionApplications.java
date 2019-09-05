package lab6;

/**
 * CSSSKL 143 B, Winter 2018 Lab 6.
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class DesigningRecursionApplications {

    /**
     * Testing Driver for recursion application 
     * @param args
     */
    public static void main(String args[]){
        System.out.println("Exponent1: " +exponent(2, 4));
        System.out.println("Expinent2: " + exonentByHalf(2, 4));
        System.out.println("Factorial: "+ factorial(5));
        System.out.print("Fibonacci: ");
        for (int i = 1; i < 6; i++) {
            System.out.print(fibonacci(i) + " ");
        };
        System.out.println("\nBinomial coefficient: "+chooseFunction(4, 2));
    }
    
    /**
     * Returns factorial
     * @param n
     * @return
     */
    public static int factorial(int n) {
        if (n <= 1) {
            return n;
        } else {
            return factorial(n - 1) * n;
        }
    }

    /**
     * Returns exponent product
     * @param n
     * @param p
     * @return
     */
    public static int exponent(int n, int p) {
        if (p == 0) {
            return 1;
        } else {
            return exponent(n, p - 1) * n;
        }

    }

    /**
     * Returns exponent using half method
     * @param n
     * @param p
     * @return
     */
    public static int exonentByHalf(int n, int p) {
        if (p == 0) {
            return 1;
        } else if (p % 2 == 0) {
            return exponent(exponent(n, p / 2), 2);
        } else {
            return exponent(exponent(n, (p - 1) / 2), 2) * n;
        }

    }

    /**
     * Returns fibonacci number at index 
     * @param n
     * @return
     */
    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    /**
     * Returns binomial coefficient
     * @param n
     * @param r
     * @return
     */
    public static int chooseFunction(int n, int r) {
        if (n == r || r == 0) {
            return 1;
        } else {
            return (factorial(n) / (factorial(r) * factorial(n - r)));
        }
    }
}
