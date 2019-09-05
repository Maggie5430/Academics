package lab2;

/**
 * Lab2 
 * CSSKL 143B, Winter 2018 
 * 1/15/18
 *
 * This class contains my work from Lab 2: Date V0.0 & V1.0.
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class Date {

    private int month;
    private int date;
    private int year;

    /**
     * Date object constructor (month, date, year).
     *
     * @param month numerical month
     * @param date numerical date
     * @param year numerical year
     */
    public Date(int month, int date, int year) {
        this.month = month;
        this.date = date;
        this.year = year;
    }

    /**
     * Sets int to month.
     *
     * @param month
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * Sets int to date.
     *
     * @param date
     */
    public void setDate(int date) {
        this.date = date;
    }

    /**
     * Sets int to year.
     *
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Prints date to console as /mm/dd/yyyy.
     */
    public void report() {
        System.out.println(this.month + "/" + this.date + "/" + this.year);
    }
}
