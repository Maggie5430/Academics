
import java.io.Serializable;

/**
 * CSS 143 B, Winter 2018 Classes & Interfaces (MoneyV2)
 *
 * @author Margaret Connor
 * @version 2.0
 */
public class Bill implements Comparable, Cloneable, Serializable {

    private Money amountPaid;
    private Date dueDate;
    private Date paidDate;
    private String originator;

    /**
     * Initializes no-arg instance of the Bill Class.
     */
    public Bill() {
        this.amountPaid = new Money();
        this.dueDate = null;
        this.paidDate = null;
        this.originator = "N/A";
    }

    /**
     * Constructor for an instance of the bills class. Precondition: The
     * internal paid date should be on or earlier than the due date if paid.
     * Postcondition: Initializes a new instance of the Bill Class
     *
     * @param amount
     * @param dueDate
     * @param originator
     */
    public Bill(Money amount, Date dueDate, String originator) {
        this.amountPaid = (Money) amount.clone();
        this.dueDate = (Date) dueDate.clone();
        this.paidDate = null;
        this.originator = originator;
    }

    /**
     * Returns the due date. Postcondition: Returns due date
     *
     * @return
     */
    public Date getDueDate() {
        return (Date) this.dueDate.clone();
    }

    /**
     * Returns the originator String. Postcondition: Returns originator
     *
     * @return
     */
    public String getOriginator() {
        return this.originator;
    }

    /**
     * Returns the money amount of this bill. Postcondition: Returns amount/cost
     *
     * @return
     */
    public Money getAmount() {
        return (Money) this.amountPaid.clone();
    }

    /**
     * Tests wither the bill has been paid. Postcondition: Returns true if bill
     * is paid
     *
     * @return
     */
    public boolean isPaid() {
        return this.paidDate != null;
    }

    /**
     * Sets the date paid to the provided date if the paid date comes after due
     * date. Precondition: The internal paid date should be on or earlier than
     * the due date. Postcondition: Sets payed date if paid before due date.
     *
     * @param dayPaid
     * @throws java.lang.Exception
     */
    public void setPaid(Date dayPaid) throws Exception {

        if (dayPaid != null) {
            boolean paidLate = false;

            //cycles through year month then day to check if before the due date
            if (dayPaid.getYear() == this.dueDate.getYear()) {
                if (dayPaid.getMonth() == this.dueDate.getMonth()) {
                    if (dayPaid.getDay() > this.dueDate.getDay()) {
                        paidLate = true;
                    }
                } else if (dayPaid.getMonth() > this.dueDate.getMonth()) {
                    paidLate = true;
                }
            } else if (dayPaid.getYear() > this.dueDate.getYear()) {
                paidLate = true;
            }

            //if not late sets the paid date to provided payed date
            if (!paidLate) {
                this.paidDate = (Date) dayPaid.clone();
            } else {
                this.paidDate = null;
                throw new Exception("Payment is past due date");
                //System.out.println("Payment is past due date");
            }
        }
    }

    /**
     * Changes the bill to be marked as unpaid. Postcondition: Set bill as
     * unpaid.
     *
     */
    public void setUnpaid() {
        this.paidDate = null;
    }

    /**
     * Changes the due date to the provided due date. Postcondition: Set new Due
     * date
     *
     * @param nextDate
     */
    public void setDueDate(Date nextDate) {
        this.dueDate = (Date) nextDate.clone();
    }

    /**
     * Changes the amount to the provided amount. Postcondition: Set new cost/
     * amount
     *
     * @param amount
     */
    public void setAmount(Money amount) {
        this.amountPaid = (Money) amount.clone();
    }

    /**
     * Changes the originator to the provided originator. Postcondition: Set new
     * originator
     *
     * @param newOriginator
     */
    public void setOriginator(String newOriginator) {
        this.originator = newOriginator;
    }

    /**
     * Returns a string representation of a Bill object. Postcondition: Returns
     * Bill as a String
     *
     * @return String
     */
    @Override
    public String toString() {
        String paperBill = "Bill of " + this.getAmount() + " to pay by "
                + this.getDueDate() + " to " + this.getOriginator()
                + ". Paid: ";
        if (this.isPaid()) {
            paperBill += this.paidDate;
        } else {
            paperBill += "false";
        }

        return paperBill;
    }

    /**
     * Compares two objects of the Bill class and returns true if the two are
     * equal to one another. Postcondition: Returns true if two Bill objects are
     * equals
     *
     * @param otherObject
     * @return true if equal
     */
    @Override
    public boolean equals(Object otherObject) {
        if (otherObject == null || !(otherObject instanceof Bill)) {
            return false;
        }

        Bill otherBill = (Bill) otherObject;

        if (this.paidDate == null || otherBill.paidDate == null) {
            if ((this.paidDate != null && otherBill.paidDate == null) || (this.paidDate == null && otherBill.paidDate != null)) {
                return false;
            }
        }

        return (this.dueDate.equals(otherBill.dueDate)
                && this.amountPaid.equals(otherBill.amountPaid)
                && this.originator.equals(otherBill.originator)
                && this.paidDate.equals(otherBill.paidDate));
    }

    /**
     * This method begins by comparing the price of the two bills, returning 1
     * if this object's amount is greater than the others. To begin the method
     * will return 0 if the two objects are equal, next it will return 1 if this
     * object is unpaid. If both objects are unpaid it will return 1 if this
     * object due date is sooner than the other object, if both object due dates
     * are the same it will return 1 if the amount is greater, if the amounts
     * are the same it will return 1 if the originators name comes first
     * alphabetically. If all else is the same and the two bills are paid it
     * will return 1 if this bill was paid most recently. Summary: orders by,
     * unpaid, due date, cost, originator, most recent paid date.
     *
     * @param other
     * @return
     */
    @Override
    public int compareTo(Object other) {
        if (other == null) {
            throw new NullPointerException();
        } else if (other.getClass() != new Bill().getClass()) {
            throw new IllegalArgumentException();
        } else {
            Bill otherBill = (Bill) other;

            if (other.equals(this)) {
                return 0;
            }

            if (this.paidDate == null && otherBill.paidDate != null) {
                return 1;
            } else if (this.paidDate != null && otherBill.paidDate == null) {
                return -1;
            } else if (this.dueDate.compareTo(otherBill.dueDate) != 0) {
                return -1 * this.dueDate.compareTo(otherBill.dueDate);
            } else if (this.amountPaid.compareTo(otherBill.amountPaid) != 0) {
                return this.amountPaid.compareTo(otherBill.amountPaid);
            } else if (this.originator.compareTo(otherBill.originator) != 0) {
                return -1 * this.originator.compareTo(otherBill.originator);
            } else if (this.paidDate != null && otherBill.paidDate != null && this.paidDate.compareTo(otherBill.paidDate) != 0) {
                return this.paidDate.compareTo(otherBill.paidDate);
            } else {
                return 0;
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
        Bill newClone = new Bill((Money) this.amountPaid.clone(),
                (Date) this.dueDate.clone(), this.originator);
        if (this.paidDate != null) {
            newClone.paidDate = (Date) this.paidDate.clone();
        }
        return newClone;
    }
}
