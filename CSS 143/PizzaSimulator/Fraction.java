
/**
 * CSS 143 B: Winter 2018 Pizza Simulator
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class Fraction implements Comparable {

    private final int numerator;
    private final int denominator;

    /**
     * No argument constructor for the Fraction class. Both the denominator and
     * the numerator are set to 0 by default. Postcondition: Creates and
     * instance of the fraction class.
     */
    public Fraction() {
        numerator = 0;
        denominator = 0;
    }

    /**
     * Constructor for the Fraction class. Postcondition: Creates and instance
     * of the fraction class.
     *
     * @param numerator
     * @param denominator
     */
    public Fraction(int numerator, int denominator) {
        int editNumerator = 0;
        int editDenominator = 0;
        //reduce the fraction by finding the GCD for numerator and denominator 
        for (int i = Math.abs(numerator + denominator); i > 0; i--) {
            if (numerator % i == 0 && denominator % i == 0) {
                editNumerator = numerator / i;
                editDenominator = denominator / i;
                break;
            }
        }
        //deals with negitave fractions 
        if ((editNumerator > 0 && editDenominator < 0)
                || (editNumerator < 0 && editDenominator < 0)) {
            editNumerator *= -1;
            editDenominator *= -1;
        }

        this.denominator = editDenominator;
        this.numerator = editNumerator;
    }

    /**
     * Copy constructor for the fraction class. Postcondition: Creates and
     * instance of the fraction class.
     *
     * @param newFraction
     */
    public Fraction(Fraction newFraction) {
        this(newFraction.getNumerator(), newFraction.getdenominator());
    }

    /**
     * Indicates whether some other Fractions is "equal to" this one.
     * Postcondition: returns true if two Fractions are equal to each other.
     *
     * @param other returns true if two Fractions are equal to each other
     * @return
     */
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        } else if (other.getClass() != new Fraction().getClass()) {
            return false;
        }
        Fraction otherFraction = (Fraction) other;
        if ((this.denominator == 0 || this.numerator == 0)
                && (otherFraction.getNumerator() == 0
                || otherFraction.getdenominator() == 0)) {
            return true;
        } else if (this.numerator == otherFraction.getNumerator()
                && this.denominator == otherFraction.getdenominator()) {
            return true;
        }
        return false;
    }

    /**
     * Returns numerator. Precondition: Returns the numerator.
     *
     * @return Numerator
     */
    public int getNumerator() {
        return this.numerator;
    }

    /**
     * Returns denominator. Precondition: Returns the denominator.
     *
     * @return Denominator
     */
    public int getdenominator() {
        return this.denominator;
    }

    /**
     * Converts the fraction into the decimal/double value. Postcondition:
     * Returns the value of the fraction as a double.
     *
     * @return
     */
    public double getFraction() {
        return (double) this.numerator / this.denominator;
    }

    /**
     * Subtracts a fraction from another fraction. Postcondition: Returns the
     * product of this fraction - other fraction.
     *
     * @param otherFraction
     * @return
     */
    public Fraction subtractFraction(Fraction otherFraction) {
        int lcm = 1; //least common multiple
        for (int i = Math.abs(this.denominator + otherFraction.denominator);
                i > 0; i--) {
            if (this.denominator % i == 0
                    && otherFraction.denominator % i == 0) {
                lcm = this.denominator * (otherFraction.denominator / i);
                break;
            }
        }
        return new Fraction(((lcm / this.denominator) * this.numerator)
                - ((lcm / otherFraction.denominator) * otherFraction.numerator),
                lcm);
    }

    /**
     * Returns a string representation of the Fraction. Postcondition: Returns
     * String form of Fraction.
     *
     * @return String
     */
    @Override
    public String toString() {
        if (this.denominator == 0 || this.numerator == 0) {
            return "0";
        } else if (this.numerator == this.denominator) {
            return Integer.toString(this.numerator);
        } else {
            return (this.numerator + "/" + this.denominator);
        }
    }

    /**
     * Compares this object with the specified object. Returns a negative
     * integer, zero, or a positive integer as this object is less than, equal
     * to, or greater than the specified object.
     *
     * @param other
     * @return
     */
    @Override
    public int compareTo(Object other) {
        if (other == null) {
            throw new NullPointerException();
        } else if (other.getClass() != new Fraction().getClass()) {
            throw new IllegalArgumentException();
        }
        Fraction otherFraction = (Fraction) other;
        if (this.getFraction() > otherFraction.getFraction()) {
            return 1;
        } else if (this.equals(otherFraction)) {
            return 0;
        } else {
            return -1;
        }
    }
}
