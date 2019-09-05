package lab3;

/**
 * Lab3 CSSKL 143B, Winter 2018 1/25/18
 *
 * This class contains my work from Lab 3: The Date Class
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class Date {

    private int month;
    private int date;
    private int year;

    /**
     * Initializes a new instance of the Date class.
     */
    public Date() {

    }

    /**
     * Initializes a new instance of the Date class.
     *
     * @param date
     * @param month
     * @param year
     */
    public Date(int date, int month, int year) {
        this.month = month;
        this.date = date;
        this.year = year;
    }

    /**
     * Initializes a new instance of the Date class.
     *
     * @param newDate
     */
    public Date(Date newDate) {
        this.month = newDate.month;
        this.date = newDate.date;
        this.year = newDate.year;
    }

    /**
     * Precondition: None 
     * Postcondition: Sets new values to Date
     *
     * @param month
     * @param date
     * @param year
     * @return
     */
    public boolean setDate(int date, int month, int year) {
        if (date < 0 && date > 31 && year > 0 && month < 0 && month > 13) {
            this.date = date;
            this.year = year;
            this.month = month;
            return true;
        }
        return false;
    }

    /**
     * Precondition: None 
     * Postcondition: Returns month value
     * 
     * @return month 
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * Precondition: None 
     * Postcondition: Returns date value
     * 
     * @return day
     */
    public int getDate() {
        return this.date;
    }

    /**
     * Precondition: None 
     * Postcondition: Returns year value
     *
     * @return year
     */
    public int getYear() {
        return this.year;
    }

    /**
     * Precondition: None 
     * Postcondition: Returns String of date
     * 
     * @return String
     */
    @Override
    public String toString() {
        return (month + "/" + date + "/" + year);
    }
}
