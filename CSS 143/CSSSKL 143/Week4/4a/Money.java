/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;

/**
 *
 * @author margaretconnor
 */
public class Money {

    private int cents;
    private int dollars;

    public Money(int dollars) {
        this.setDollars(dollars);
        this.cents = 0;
    }

    public Money(int dollars, int cents) {
        this.setDollars(dollars);
        this.setCents(cents);
    }

    public Money(Money other) {
        this.dollars = other.dollars;
        this.cents = other.cents;
    }

    public int getDollars() {
        return this.dollars;
    }

    public boolean setDollars(int newDollars) {
        if (newDollars > 0) {
            this.dollars = newDollars;
            return true;
        }
        System.out.println("Can not input negitive dollars");
        return false;
    }

    public int getCents() {
        return this.cents;
    }

    public boolean setCents(int newCents) {
        if (newCents > 0 && newCents < 100) {
            this.dollars = newCents;
            return true;
        }
        System.out.println("invalid input, cents not between [0,99]");
        return false;
    }

    public double getMoney() {
        return (this.dollars + this.cents / 10);
    }

    public void setMoney(int newDollars, int newCents) {
        this.setCents(newDollars);
        this.setCents(newCents);
    }

    public void add(int newDollars) {
        this.add(newDollars, 0);
    }

    public void add(int newDollars, int newCents) {
        if (newDollars > 0 && newCents > 0 && newCents < 100) {
            this.dollars += newDollars;
            this.cents += newCents;
            while (this.cents >= 100) {
                this.dollars++;
                this.cents -= 100;
            }
        } else {
            System.out.println("Can not add negitive money");
        }
    }

    public void add(Money other) {
        this.add(other.getDollars(), other.getCents());
    }

    public boolean equals(Money other) {
        return other.getCents() == this.cents && other.getDollars() == this.dollars;
    }

    @Override
    public String toString() {
        return ("$" + this.dollars + "." + this.cents);
    }
}
