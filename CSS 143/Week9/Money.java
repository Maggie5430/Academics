
import java.io.Serializable;

/**
 * CSS 143 B, Winter 2018 Classes & Interfaces (MoneyV2)
 *
 * @author Margaret Connor
 * @version 2.0
 */
public class Money implements Comparable, Cloneable, Serializable {

    private int cents;
    private int dollars;

    /**
     * Initializes a new instance of the Money Class.
     *
     */
    public Money() {
        this.cents = 0;
        this.dollars = 0;
    }

    /**
     * Constructor of the money class. Precondition: cents between [0-99],
     * dollars never less than 0 Postcondition: Initializes a new instance of
     * the Money Class
     *
     * @param dollars
     */
    public Money(int dollars) {
        // Internal cents should be between [0-99] at all times.
        // Our internal Cents and Dollars should never be < 0 at all times.
        if (dollars > 0) {
            this.dollars = dollars;
            this.cents = 0;
        } else {
            throw new RuntimeException("Invalid dollar");
            //System.out.println("Invalid Money");
        }
    }

    /**
     * Constructor of the money class. Precondition: cents between [0-99],
     * dollars never less than 0. Postcondition: Initializes a new instance of
     * the Money Class
     *
     * @param dollars
     * @param cents
     */
    public Money(int dollars, int cents) {
        // Internal cents should be between [0-99] at all times.
        // Our internal Cents and Dollars should never be < 0 at all times.
        if (dollars > 0 && cents >= 0 && cents < 100) {
            this.dollars = dollars;
            this.cents = cents;
        } else {
            throw new RuntimeException("Invalid dollar or cents");
            //System.out.println("Invalid Money");
        }
    }

    /**
     * Returns the dollar value of the money object. Postcondition: Returns
     * dollar integer
     *
     * @return
     */
    public int getDollars() {
        return this.dollars;
    }

    /**
     * Changes the dollar value of the money object. Precondition: New dollar
     * value is positive Postcondition: Assigns the dollar value to a new
     * integer
     *
     * @param newDollars
     * @return
     */
    public boolean setDollars(int newDollars) {
        if (newDollars >= 0) {
            this.dollars = newDollars;
            return true;
        } else {
            throw new RuntimeException("Invalid dollars");
            //System.out.println("Invalid dollars");
            //return false;
        }
    }

    /**
     * Returns the cent value of the money object. Postcondition: Returns cents
     * integer
     *
     * @return
     */
    public int getCents() {
        return this.cents;
    }

    /**
     * Changes the cents of the money object. Precondition: New cents value is
     * between [0-99]. Postcondition: Assigns the dollar value to a new integer
     *
     * @param newCents
     * @return
     */
    public boolean setCents(int newCents) {
        if (newCents >= 0 && newCents < 100) {
            this.cents = newCents;
            return true;
        } else {
            throw new RuntimeException("Invalid cents");
            //System.out.println("Invalid cents");
            //return false;
        }
    }

    /**
     * Returns the double value of the money object. Postcondition: Returns
     * Money in double form
     *
     * @return
     */
    public double getMoney() {
        return (((double) this.dollars) + (((double) this.cents) / 100.0));
    }

    /**
     * Changes the dollar and cents values. Precondition: New cents value is
     * between [0-99] & new dollar >= 0. Postcondition: Assigns new Money value.
     *
     * @param newDollars
     * @param newCents
     */
    public void setMoney(int newDollars, int newCents) {
        this.setDollars(newDollars);
        this.setCents(newCents);
    }

    /**
     * Adds two money objects together and producing a total. Precondition: new
     * value is positive. Postcondition: Adds the current dollar and new dollar
     * value.
     *
     * @param newDollars
     */
    public void add(int newDollars) {
        this.add(newDollars, 0);
    }

    /**
     * Adds two money objects together and producing a total. Precondition: new
     * value is positive Postcondition: Adds the current dollar and new dollar
     * value.
     *
     * @param newDollars
     * @param newCents
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
            throw new RuntimeException("Invalid input");
            //System.out.println("Money cannot be negitive");
        }
    }

    /**
     * Adds two money objects together and producing a total. Precondition: new
     * value is positive. Postcondition: Adds the current dollar and new dollar
     * value
     *
     * @param other
     */
    public void add(Money other) {
        this.add(other.getDollars(), other.getCents());
    }

    /**
     * Compares two objects of the Money class and returns true if the two are
     * equal to one another. Postcondition: Returns true if two Money objects
     * are equal.
     *
     * @param otherObject
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
     * Returns a string representation of a Money object. Postcondition: Returns
     * money as a String in form $0.00.
     *
     * @return String
     */
    @Override
    public String toString() {
        return ("$" + this.dollars + "." + this.cents);
    }

    /**
     * Compare to function that compares the value of two Money objects. Returns
     * 1 if this is greater than other, returns 0 if the two are equal, returns
     * -1 if this is less than the other. Precondition: other object is an
     * instance of the Money class and is not equal to null. Postcondition:
     * returns a numerical value representing the difference between two money
     * objects
     *
     * @param other object to be compared to
     * @return
     */
    @Override
    public int compareTo(Object other) {
        if (other == null) {
            throw new NullPointerException();
        } else if (other.getClass() != new Money().getClass()) {
            throw new IllegalArgumentException();
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

    /**
     * Produces a deep copy of the bill class. Postcondition: Produces a deep
     * copy of the bill class
     *
     * @return
     */
    @Override
    public Object clone() {
        return new Money(this.dollars, this.cents);
    }
}
