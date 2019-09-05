package lab3;

/**
 * Lab3 CSSKL 143B, Winter 2018 1/25/18
 *
 * This class contains my work from Lab 3: The Fraction Class
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class Fraction {

    public final int numerator;
    public final int denominator;

    /**
     * Initializes a new instance of the Fraction class.
     * 
     * @param numerator
     * @param denominator
     */
    public Fraction(int numerator, int denominator) {
        int largeNumerator = numerator;
        int largeDenominator = denominator;
        //reduce the fraction by finding the GCD for numerator and denominator 
        for (int i = Math.abs(numerator + denominator); i > 0; i--) {
            if (numerator % i == 0 && denominator % i == 0) {
                largeNumerator = numerator / i;
                largeDenominator = denominator / i;
                break;
            }
        }
        //deals with negitave fractions 
        if ((largeNumerator > 0 && largeDenominator < 0)
                || (largeNumerator < 0 && largeDenominator < 0)) {
            largeNumerator *= -1;
            largeDenominator *= -1;
        }
        this.numerator = largeNumerator;
        this.denominator = largeDenominator;
    }

    /**
     * Initializes a new instance of the Fraction class.
     * 
     * @param newFraction
     */
    public Fraction(Fraction newFraction) {
        this.numerator = newFraction.numerator;
        this.denominator = newFraction.denominator;
    }

    /** Custom toString returns 0 or returns fraction
     * in format A/B
     */
    @Override
    public String toString() {
        if (this.denominator == 0 || this.numerator == 0) {
            return "0";
        } else {
            return (this.numerator + "/" + this.denominator);
        }
    }

    /**
     * Precondition: None
     * Postcondition: Returns the sum of two fractions
     * 
     * @param other
     * @return fraction
     */
    public Fraction add(Fraction other) {
        int gcd = 1; // least common denominator 
        int lcm; // least common multiplyer 
        int a = this.denominator;
        int b = other.denominator;

        for (int i = Math.abs(a + b); i > 0; i--) {
            if (a % i == 0 && b % i == 0) {
                gcd = i;
                break;
            }
        }

        lcm = a * (b / gcd);

        int newNumerator = (lcm / this.denominator) * this.numerator;
        int newNumerator2 = (lcm / other.denominator) * other.numerator;
        return (new Fraction(newNumerator + newNumerator2, lcm));
    }

    /**
     * Precondition: None
     * Postcondition: Returns true if two fractions are equal
     * 
     * @param other
     * @return boolean
     */
    public boolean equals(Object other) {
        if (other != null && !(other instanceof Fraction)) {
            return false;
        }
        if ((this.denominator == 0 || this.numerator == 0)
                && (((Fraction) other).numerator == 0
                || ((Fraction) other).denominator == 0)) {
            return true;
        } else if (this.numerator == ((Fraction) other).numerator
                && this.denominator == ((Fraction) other).denominator) {
            return true;
        }
        return false;
    }
}
