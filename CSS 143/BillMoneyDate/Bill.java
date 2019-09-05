package money.bill.and.date.classes;

/**
 * CSS 143 B, Winter 2018 Money, Bill, and Date Classes
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class Bill {

    private Money amountPaid;
    private Date dueDate;
    private Date paidDate;
    private String originator;

    /**
     * Initializes a new instance of the Bill Class.
     */
    public Bill() {

    }

    /**
     * Precondition: The internal paid date should be on or earlier than the due
     * date if paid 
     * Postcondition: Initializes a new instance of the Bill Class
     *
     * @param amount
     * @param dueDate
     * @param originator
     */
    public Bill(Money amount, Date dueDate, String originator) {
        this.amountPaid = new Money(amount);
        this.dueDate = new Date(dueDate);
        this.paidDate = null;
        this.originator = originator;
    }

    /**
     * Precondition: The internal paid date should be on or earlier than the due
     * date if paid 
     * Postcondition: Initializes a new instance of the Bill Class
     *
     * @param otherBill
     */
    public Bill(Bill otherBill) {
        if (otherBill != null) {
            this.amountPaid = new Money(otherBill.amountPaid);
            this.dueDate = new Date(otherBill.dueDate);
            this.originator = otherBill.originator;
            if (otherBill.isPaid()) {
                this.paidDate = new Date(otherBill.paidDate);
            } else {
                this.paidDate = null;
            }

        } else {
            //null bill layout
            this.amountPaid = new Money(null);
            this.dueDate = null;
            this.paidDate = null;
            this.originator = "N/A";
        }
    }

    /**
     * Precondition: None 
     * Postcondition: Returns due date
     *
     * @return
     */
    public Date getDueDate() {
        return new Date(this.dueDate);
    }

    /**
     * Precondition: None 
     * Postcondition: Returns originator
     *
     * @return
     */
    public String getOriginator() {
        return this.originator;
    }

    /**
     * Precondition: None 
     * Postcondition: Returns amount/cost
     *
     * @return
     */
    public Money getAmount() {
        return new Money(this.amountPaid);
    }

    /**
     * Precondition: None 
     * Postcondition: Returns true if bill is paid
     *
     * @return
     */
    public boolean isPaid() {
        return this.paidDate != null;
    }

    /**
     * Precondition: The internal paid date should be on or earlier than the due
     * date
     * Postcondition: Sets payed date if paid before due date.
     * 
     * @param dayPaid
     */
    public void setPaid(Date dayPaid) throws Exception{
        
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
                this.paidDate = new Date(dayPaid);
            } else {
                this.paidDate = null;
                throw new Exception("Payment is past due date");
                //System.out.println("Payment is past due date");
            }
        }
    }

    /**
     * Precondition: None
     * Postcondition: Set bill as unpaid.
     * 
     */
    public void setUnpaid() {
        this.paidDate = null;
    }

    /**
     * Precondition: None
     * Postcondition: Set new Due date
     * 
     * @param nextDate
     */
    public void setDueDate(Date nextDate) {
        this.dueDate = new Date(nextDate);
    }

    /**
     * Precondition: None
     * Postcondition: Set new cost/ amount
     *
     * @param amount
     */
    public void setAmount(Money amount) {
        this.amountPaid = new Money(amount);
    }

    /**
     * Precondition: None
     * Postcondition: Set new originator 
     * 
     * @param newOriginator
     */
    public void setOriginator(String newOriginator) {
        this.originator = newOriginator;
    }

    /**
     * Precondition: None
     * Postcondition: Returns Bill as a String
     * 
     * @return String 
     */
    @Override
    public String toString() {
        String paperBill = "Bill of " + this.getAmount() + "to pay by "
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
     * Precondition: None
     * Postcondition: Returns true if two Bill objects are equals
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
        return (this.dueDate.equals(otherBill.dueDate)
                && this.amountPaid.equals(otherBill.amountPaid)
                && this.originator.equals(otherBill.originator)
                && this.paidDate.equals(otherBill.paidDate));
    }

}
