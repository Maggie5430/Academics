package fractionsv2;

/**
 * CSS 143 B, Winter 2018 Fractions v2
 *
 * @author Margaret Connor
 * @version 2.0
 */
public class FractionCounter {

    Fraction fraction;
    int count;

    /**
     * Initializes a new instance of the FractionCounter class.
     */
    public FractionCounter() {

    }

    /**
     * Initializes a new instance of the FractionCounter class.
     *
     * @param theFraction
     */
    public FractionCounter(Fraction theFraction) {
        this.fraction = theFraction;
        this.count++;
    }

    /**
     * Returns true and increments the counter by one if the newFraction is the
     * same as the stored fraction, and if so increments the counter by one
     *
     * @param newFraction
     * @return true if equal
     */
    public boolean compareAndIncrement(Fraction newFraction) {
        if (this.fraction.equals(newFraction)) {
            count++;
            return true;
        }
        return false;
    }

    // Custom toString returns [Fraction] has a count of [int]
    @Override
    public String toString() {
        return (this.fraction + " has a count of " + count);
    }
}
