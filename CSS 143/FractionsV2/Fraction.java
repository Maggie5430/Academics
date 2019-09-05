package fractionsv2;

/**
 * CSS 143 B, Winter 2018 Fractions v2
 *
 * @author Margaret Connor
 * @version 2.0
 */
public class Fraction {

    private int numerator;
    private int denominator;

    /**
     * Initializes a new instance of the Fraction class.
     */
    public Fraction() {

    }

    /**
     * Initializes a new instance of the Fraction class.
     *
     * @param numerator
     * @param denominator
     */
    public Fraction(int numerator, int denominator) {
        //reduce the fraction by finding the GCD for numerator and denominator 
        for (int i = Math.abs(numerator + denominator); i > 0; i--) {
            if (numerator % i == 0 && denominator % i == 0) {
                this.numerator = numerator / i;
                this.denominator = denominator / i;
                break;
            }
        }
        //deals with negitave fractions 
        if ((this.numerator > 0 && this.denominator < 0)
                || (this.numerator < 0 && this.denominator < 0)) {
            this.numerator *= -1;
            this.denominator *= -1;
        }
    }

    /**
     * Returns true if Fractions are equal.
     *
     * @param other
     * @return
     */
    public boolean equals(Fraction other) {
        if ((this.denominator == 0 || this.numerator == 0)
                && (other.getNumerator() == 0 || other.getdenominator() == 0)) {
            return true;
        } else if (this.numerator == other.getNumerator()
                && this.denominator == other.getdenominator()) {
            return true;
        }
        return false;
    }

    /**
     * Returns numerator.
     *
     * @return Numerator
     */
    public int getNumerator() {
        return this.numerator;
    }

    /**
     * Sets new numerator.
     *
     * @param newNumerator
     */
    public void setNumerator(int newNumerator) {
        this.numerator = newNumerator;
    }

    /**
     * Returns denominator.
     *
     * @return Denominator
     */
    public int getdenominator() {
        return this.denominator;
    }

    /**
     * Sets new denominator
     *
     * @param newdenominator
     */
    public void setdenominator(int newdenominator) {
        this.denominator = newdenominator;
    }

    /* Custom toString returns 0 or returns fraction
     * in format A/B */
    @Override
    public String toString() {
        if (this.denominator == 0 || this.numerator == 0) {
            return "0";
        } else {
            return (this.numerator + "/" + this.denominator);
        }
    }
}
