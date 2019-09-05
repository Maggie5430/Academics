package money.bill.and.date.classes;

/**
 * CSS 143 B, Winter 2018 Money, Bill, and Date Classes
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class Money {
    
    public static void main(String args[]){
        Money m1 = new Money(3,50);
        Money m2 = new Money(m1);
        System.out.println(m1);
        System.out.println(m2);
    }

    private int cents;
    private int dollars;

    /**
     * Initializes a new instance of the Money Class.
     *
     */
    public Money() {
      
    }

    /**
     * Precondition: cents between [0-99], dollars never less than 0
     * Postcondition: Initializes a new instance of the Money Class
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
            System.out.println("Invalid Money");
        }
    }

    /**
     * Precondition: cents between [0-99], dollars never less than 0
     * Postcondition: Initializes a new instance of the Money Class
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
            System.out.println("Invalid Money");
        }
    }

    /**
     * Precondition: cents between [0-99], dollars never less than 0
     * Postcondition: Initializes a new instance of the Money Class
     *
     * @param other
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
     * Precondition: None
     * Postcondition: Returns dollar integer
     *
     * @return
     */
    public int getDollars() {
        return this.dollars;
    }

    /**
     * Precondition: New dollar value is positive
     * Postcondition: Assigns the dollar value to a new integer
     * 
     * @param newDollars
     * @return
     */
    public boolean setDollars(int newDollars) {
        if (newDollars >= 0) {
            this.dollars = newDollars;
            return true;
        } else {
            System.out.println("Invalid dollars");
            return false;
        }
    }

    /**
     * Precondition: None
     * Postcondition: Returns cents integer
     *
     * @return
     */
    public int getCents() {
        return this.cents;
    }

    /**
     * Precondition: New cents value is between [0-99]
     * Postcondition: Assigns the dollar value to a new integer
     * 
     * @param newCents
     * @return
     */
    public boolean setCents(int newCents) {
        if (newCents >= 0 && newCents < 100) {
            this.cents = newCents;
            return true;
        } else {
            System.out.println("Invalid cents");
            return false;
        }
    }

    /**
     * Precondition: None
     * Postcondition: Returns Money in double form 
     *
     * @return
     */
    public double getMoney() {
        return (((double) this.dollars) + (((double) this.cents) / 100.0));
    }

    /**
     * Precondition: New cents value is between [0-99] & new dollar >= 0
     * Postcondition: Assigns new Money value 
     * 
     * @param newDollars
     * @param newCents
     */
    public void setMoney(int newDollars, int newCents) {
        this.setDollars(newDollars);
        this.setCents(newCents);
    }

    /**
     * Precondition: new value is positive
     * Postcondition: Adds the current dollar and new dollar value
     * 
     * @param newDollars
     */
    public void add(int newDollars) {
        this.add(newDollars, 0);
    }

    /**
     * Precondition: new value is positive
     * Postcondition: Adds the current dollar and new dollar value
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
            System.out.println("Money cannot be negitive");
        }
    }

    /**
     * Precondition: new value is positive
     * Postcondition: Adds the current dollar and new dollar value
     * 
     * @param other
     */
    public void add(Money other) {
        this.add(other.getDollars(), other.getCents());
    }

    /**
     * Precondition: None
     * Postcondition: Returns true if two Money objects are equals
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
     * Precondition: None
     * Postcondition: Returns money as a String in form $0.00.
     * 
     * @return String 
     */
    @Override
    public String toString() {
        return ("$" + this.dollars + "." + this.cents);
    }
}
