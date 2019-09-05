package pkgnew.and.review;

/**
 * Lab1b
 * CSSKL 143B, Winter 2018
 * 1/07/18
 * 
 * Part: Overloading Methods.
 * 
 * @author Margaret Connor
 */
public class Square {

    /**
     * Returns Square root
     * 
     * @param base
     * @return square root of integer
     */
    public static int Square(int base) {
        return base * base;
    }

    /**
     * Returns Square root
     * 
     * @param base
     * @return square root of double
     */
    public static double Square(double base) {
        return base * base;
    }

    /**
     * Returns Square root
     * 
     * @param base
     * @return square root of float
     */
    public static float Square(float base) {
        return base * base;
    }
}
