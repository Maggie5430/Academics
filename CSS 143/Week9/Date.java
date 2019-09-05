
import java.io.Serializable;

/**
 * CSS 143 B, Winter 2018 Classes & Interfaces (MoneyV2)
 *
 * @author Margaret Connor
 * @version 2.0
 */
public class Date implements Comparable, Cloneable, Serializable {

    private int month;
    private int date;
    private int year;

    /**
     * Initializes a new instance of the Money Class.
     */
    public Date() {
        this.date = 1;
        this.month = 1;
        this.year = 2014;
    }

    /**
     * Initializes a new instance of the Money Class. Precondition: All days
     * should be between [1-31] All months should be between [1-12] and in
     * format (m,dd,yyyy). Postcondition: Initializes a new instance of the Date
     * Class.
     *
     * @param month
     * @param date
     * @param year
     */
    public Date(int month, int date, int year) {
        if (date > 0 && date < 32 && month > 0 && month < 13 && year > 2013
                && year < 2025) {
            this.date = date;
            this.month = month;
            this.year = year;
        } else {
            //all invalid dates are change to 1/1/2014
            this.date = 1;
            this.month = 1;
            this.year = 2014;
            throw new RuntimeException("Invalid date, new date set to 1/1/2014");
            //System.out.println("Invalid date set to 1/1/2014");
        }
    }

    /**
     * Sets new day. Precondition: New Day value is between [0-31].
     * Postcondition: Assigns new Date value.
     *
     * @param newDate
     * @return
     */
    public boolean setDay(int newDate) {
        if (newDate > 0 && newDate < 32) {
            this.date = newDate;
            return true;
        } else {
            throw new RuntimeException("Invalid date");
        }
    }

    /**
     * Sets new month. Precondition: New Month value is between [1-12].
     * Postcondition: Assigns new month value.
     *
     * @param newMonth
     * @return
     */
    public boolean setMonth(int newMonth) {
        if (newMonth > 0 && newMonth < 13) {
            this.month = newMonth;
            return true;
        } else {
            throw new RuntimeException("Invalid month");
            //System.out.println("Invalid month");
            //return false;
        }
    }

    /**
     * Sets new year. Precondition: New year value is between [2014-2024]
     * Postcondition: Assigns new year value
     *
     * @param newYear
     * @return
     */
    public boolean setYear(int newYear) {
        if (newYear > 2013 && newYear < 2025) {
            this.year = newYear;
            return true;
        } else {
            throw new RuntimeException("Invalid year");
        }
    }

    /**
     * Returns day. Precondition: None Postcondition: Returns day integer.
     *
     * @return
     */
    public int getDay() {
        return this.date;
    }

    /**
     * Returns month. Precondition: None Postcondition: Returns month integer.
     *
     * @return
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * Returns year. Postcondition: Returns year integer.
     *
     * @return
     */
    public int getYear() {
        return this.year;
    }

    /**
     * Compares two objects and returns true if the two are equal to each other.
     * Postcondition: Returns true if two Date objects are equals
     *
     * @param otherObject
     * @return true if equal
     */
    @Override
    public boolean equals(Object otherObject) {
        //catches nulls and objects of other classes
        if (otherObject == null || !(otherObject instanceof Date)) {
            return false;
        }
        Date otherDate = (Date) otherObject;
        return (this.date == otherDate.date && this.month == otherDate.month
                && this.year == otherDate.year);

    }

    /**
     * Returns a String representation of the date class. Postcondition: Returns
     * date as a String in form m/dd/yyyy
     *
     * @return String
     */
    @Override
    public String toString() {
        return (this.month + "/" + this.date + "/" + this.year);
    }

    /**
     * This method compares the occurrence of two date objects. Returns 1 if the
     * date comes after the other date and returns -1 if it comes before the
     * other date.
     *
     * @param other
     * @return
     */
    @Override
    public int compareTo(Object other) {
        if (other == null && this == null) {
            return 0;
        } else if (other == null || this == null) {
            throw new NullPointerException();
        } else if (other.getClass() != new Date().getClass()) {
            throw new IllegalArgumentException();
        } else {
            Date otherDate = (Date) other;
            if (this.equals(otherDate)) {
                return 0;
            } else if (this.year > otherDate.year) {
                return 1;
            } else if (this.year == otherDate.year) {
                if (this.month > otherDate.month) {
                    return 1;
                } else if (this.month == otherDate.month) {
                    if (this.date > otherDate.date) {
                        return 1;
                    } else if (this.date == otherDate.date) {
                        return 0;
                    }
                }
            }
            return -1;

        }
    }

    /**
     * Produces a deep copy of the Date class. Postcondition: Produces a deep
     * copy of the Date class
     *
     * @return
     */
    @Override
    public Object clone() {
        return new Date(this.month, this.date, this.year);
    }
}
