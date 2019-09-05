
/**
 * CSS 143 B, Winter 2018 Classes & Interfaces (MoneyV2)
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class ExpenseAccount extends ArrayList<Bill> {

    public ExpenseAccount() {
        super();
    }

    /**
     * Returns a string representation of this collection. The string consists
     * of all the elements in one line separated by a space. Postcondition:
     * returns a string representation of this collection.
     *
     * @return A string representation of this collection
     */
    @Override
    public String toString() {
        String toString = "";
        for (Bill b : this) {
            toString += b.toString() + "\n";
        }
        return toString;
    }

    /**
     * Returns the number of unpaid bills. Postcondition: Returns the number of
     * unpaid bills.
     *
     * @return number of unpaid bills
     */
    public int sizeOfOutstanding() {
        int numOutstanding = 0;
        for (Bill b : this) {
            if (!b.isPaid()) {
                numOutstanding++;
            }
        }
        return numOutstanding;
    }

    /**
     * Returns the number of paid bills. Postcondition: Returns the number of
     * paid bills.
     *
     * @return number of paid bills
     */
    public int sizeOfPaid() {
        int numOutstanding = 0;
        for (Bill b : this) {
            if (b.isPaid()) {
                numOutstanding++;
            }
        }
        return numOutstanding;
    }

    /**
     * Returns a String of outstanding bills. Postcondition: Returns a String of
     * outstanding bills.
     *
     * @return String of outstanding bills
     */
    public String getOutstanding() {
        String toReturn = "";
        for (Bill b : this) {
            if (!b.isPaid()) {
                toReturn += b + "\n";
            }
        }
        return toReturn;
    }

    /**
     * Returns a String of paid bills. Postcondition: Returns a String of paid
     * bills.
     *
     * @return string of paid bills
     */
    public String getPaid() {
        String toReturn = "";
        for (Bill b : this) {
            if (b.isPaid()) {
                toReturn += b + "\n";
            }
        }
        return toReturn;
    }

    /**
     * Returns the bill with the closest due date. Postcondition: Returns the
     * bill with the closest due date.
     *
     * @return next closest bill
     */
    public Bill nextDue() {
        Bill nextBill = this.get(0);
        for (Bill b : this) {
            if (b.getDueDate().compareTo(nextBill.getDueDate()) < 0) {
                nextBill = b;
            }
        }
        return nextBill;
    }

    /**
     * Returns the total cost of all outstanding bills. Postcondition: Returns
     * the total cost of all outstanding bills
     *
     * @return total of all outstanding bills
     */
    public Money totalAmount() {
        Money total = new Money();
        for (Bill b : this) {
            if (!b.isPaid()) {
                total.add(b.getAmount());
            }
        }
        return total;
    }

    /**
     * Compares two objects of the ExpenseAccount class and returns true if the
     * two are equal to one another. Postcondition: Returns true if two
     * ExpenseAccount objects are equals
     *
     * @return true if equal
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || other.getClass()
                != new ExpenseAccount().getClass()) {
            return false;
        }
        ExpenseAccount otherExpenseAccount = (ExpenseAccount) other;
        if (otherExpenseAccount.size() == this.size()) {
            for (int i = 0; i < this.size(); i++) {
                if (!this.get(i).equals(otherExpenseAccount.get(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

}
