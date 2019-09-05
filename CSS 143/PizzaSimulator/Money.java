
/**
 * CSS 143 B: Winter 2018 Pizza Simulator
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class Money implements Comparable {

    private int cents;
    private int dollars;

    /**
     * Constructs a new instance of the Money Class. Default cents and dollars
     * are set to 0 Postcondition: Creates a new instance of the Money class.
     *
     */
    public Money() {
        this.cents = 0;
        this.dollars = 0;
    }

    /**
     * Constructs a new instance of the Money Class with specified dollar
     * amount. Default cents set to 0. Precondition: cents between [0-99],
     * dollars never less than 0. Postcondition: Creates a new instance of the
     * Money class.
     *
     * @param dollars Dollar value
     */
    public Money(int dollars) {
        if (dollars >= 0) {
            this.dollars = dollars;
            this.cents = 0;
        } else {
            throw new IllegalArgumentException("Money cannot be negative");
        }
    }

    /**
     * Constructs a new instance of the Money Class with specified dollar and
     * cent amount. Precondition: cents between [0-99], dollars never less than
     * 0. Postcondition: Creates a new instance of the Money class.
     *
     * @param dollars Dollar value
     * @param cents Cents value
     */
    public Money(int dollars, int cents) {
        // Internal cents should be between [0-99] at all times.
        // Our internal Cents and Dollars should never be < 0 at all times.
        if (dollars >= 0 && cents >= 0 && cents < 100) {
            this.dollars = dollars;
            this.cents = cents;
        } else {
            throw new IllegalArgumentException("Money cannot be negative");
        }
    }

    /**
     * Constructs a new instance of the Money Class with specified Money object.
     * Precondition: cents between [0-99], dollars never less than 0.
     * Postcondition: Creates a new instance of the Money class.
     *
     * @param other Other Money object
     */
    public Money(Money other) {
        // Internal cents should be between [0-99] at all times.
        // Our internal Cents and Dollars should never be < 0 at all times.
        if (other != null) {
            this.dollars = other.dollars;
            this.cents = other.cents;
        }
    }

    /**
     * Returns this money's dollars value. Postcondition: Returns dollar
     * integer.
     *
     * @return Dollar
     */
    public int getDollars() {
        return this.dollars;
    }

    /**
     * Changes this money's dollar value. Precondition: New dollar value is
     * positive. Postcondition: Assigns the dollar value to a new integer
     *
     * @param newDollars New Dollar value
     */
    public void setDollars(int newDollars) {
        if (newDollars >= 0) {
            this.dollars = newDollars;
        } else {
            throw new IllegalArgumentException("Money cannot be negative");
        }
    }

    /**
     * Returns this money's cents value. Postcondition: Returns cents integer
     *
     * @return Cents
     */
    public int getCents() {
        return this.cents;
    }

    /**
     * Changes this item's cent value. Precondition: New cents value is between
     * [0-99]. Postcondition: Assigns the dollar value to a new integer
     *
     * @param newCents New cents
     */
    public void setCents(int newCents) {
        if (newCents >= 0 && newCents < 100) {
            this.cents = newCents;
        } else {
            throw new IllegalArgumentException("Money cannot be negative");
        }
    }

    /**
     * Returns the value of money as a double. Postcondition: Returns Money in
     * double form
     *
     * @return
     */
    public double getMoney() {
        return (((double) this.dollars) + (((double) this.cents) / 100.0));
    }

    /**
     * Changes the cent value. Precondition: New cents value is between [0-99] &
     * new dollar >= 0. Postcondition: Assigns new Money value
     *
     * @param newDollars New dollars
     * @param newCents New cents
     */
    public void setMoney(int newDollars, int newCents) {
        this.setDollars(newDollars);
        this.setCents(newCents);
    }

    /**
     * Adds the two values together and sets this Money object to their sum.
     * Precondition: new value is positive Postcondition: Adds the current
     * dollar and new dollar value.
     *
     * @param newDollars New Dollars
     */
    public void add(int newDollars) {
        this.add(newDollars, 0);
    }

    /**
     * Adds the two values together and sets this Money object to their sum.
     * Precondition: new value is positive. Postcondition: Adds the current
     * dollar and new dollar value.
     *
     * @param newDollars New dollars
     * @param newCents New Cents
     */
    public void add(int newDollars, int newCents) {
        if (newDollars >= 0 && newCents >= 0 && newCents < 100) {
            this.dollars += newDollars;
            this.cents += newCents;
            //rounds/ carries the cents over when greater than 99
            while (this.cents >= 100) {
                this.dollars++;
                this.cents -= 100;
            }
        } else {
            throw new IllegalArgumentException("Money cannot be negative");
        }
    }

    /**
     * Adds the two values together and sets this Money object to their sum.
     * Precondition: new value is positive.Postcondition: Adds the current
     * dollar and new dollar value
     *
     * @param other Other Money
     */
    public void add(Money other) {
        this.add(other.getDollars(), other.getCents());
    }

    /**
     * Indicates whether some other Money is "equal to" this one. Postcondition:
     * Returns true if two Money objects are equals
     *
     * @param otherObject Other object
     * @return true if equal
     */
    @Override
    public boolean equals(Object otherObject) {
        if (otherObject == null || !(otherObject instanceof Money)) {
            return false;
        } else {
            Money otherMoney = (Money) otherObject;
            return this.cents == otherMoney.getCents()
                    && otherMoney.getDollars() == this.dollars;
        }
    }

    /**
     * Returns a string representation of the Money object. Postcondition:
     * Returns money as a String in form $0.00.
     *
     * @return String
     */
    @Override
    public String toString() {
        return ("$" + this.dollars + "." + this.cents);
    }

    /**
     * Compares this Money with the specified object. Returns a negative
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
        } else if (other.getClass() != new Money().getClass()) {
            throw new IllegalArgumentException("Not a Money object");
        } else {
            Money otherMoney = (Money) other;
            if (this.equals(otherMoney)) {
                return 0;
            } else if (this.getMoney() > otherMoney.getMoney()) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}
