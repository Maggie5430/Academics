package money.bill.and.date.classes;

/**
 * CSS 143 B, Winter 2018 Money, Bill, and Date Classes
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class Date {

    public static void main(String args[]){
        Date d1 = new Date(-1,-15,-2018);
        System.out.println(d1);
        System.out.print(d1.getMonth());
        System.out.print(d1.getDay());
        System.out.print(d1.getYear());
    }
    
    private int month;
    private int date;
    private int year;

    /**
     * Initializes a new instance of the Money Class.
     */
    public Date() {

    }

    /**
     * Precondition: All days should be between [1-31] All months should be 
     * between [1-12] and in format (m,dd,yyyy)
     * Postcondition: Initializes a new instance of the Date Class
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
            System.out.println("Invalid date set to 1/1/2014");
        }
    }

    /**
     * Precondition: All days should be between [1-31] All months should be 
     * between [1-12] and in format (m,dd,yyyy)
     * Postcondition: Initializes a new instance of the Date Class
     * 
     * @param newDate
     */
    public Date(Date newDate) {
        if (newDate != null) {
            if (newDate.date > 0 && newDate.date < 32 && newDate.month > 0
                    && newDate.month < 13 && newDate.year > 2013
                    && newDate.year < 2025) {
                this.date = newDate.date;
                this.month = newDate.month;
                this.year = newDate.year;
            } else {
                //all invalid dates are change to 1/1/2014
                this.date = 1;
                this.month = 1;
                this.year = 2014;
                System.out.println("Invalid date set to 1/1/2014");
            }

        }

    }

    /**
     * Precondition: New Day value is between [0-31]
     * Postcondition: Assigns new Date value
     * 
     * @param newDate
     * @return
     */
    public boolean setDay(int newDate) {
        if (newDate > 0 && newDate < 32) {
            this.date = newDate;
            return true;
        } else {
            System.out.println("Invalid day");
            return false;
        }
    }

    /**
     * Precondition: New Month value is between [1-12]
     * Postcondition: Assigns new month value
     * 
     * @param newMonth
     * @return
     */
    public boolean setMonth(int newMonth) {
        if (newMonth > 0 && newMonth < 13) {
            this.month = newMonth;
            return true;
        } else {
            System.out.println("Invalid month");
            return false;
        }
    }

    /**
     * Precondition: New year value is between [2014-2024]
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
            System.out.println("Invalid year must be between 2014 and 2024");
            return false;
        }
    }

    /**
     * Precondition: None
     * Postcondition: Returns day integer
     *
     * @return
     */
    public int getDay() {
        return this.date;
    }

    /**
     * Precondition: None
     * Postcondition: Returns month integer
     *
     * @return
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * Precondition: None
     * Postcondition: Returns year integer
     *
     * @return
     */
    public int getYear() {
        return this.year;
    }

    /**
     * Precondition: None
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
     * Precondition: None
     * Postcondition: Returns money as a String in form m/dd/yyyy
     * 
     * @return String 
     */
    @Override
    public String toString() {
        return (this.month + "/" + this.date + "/" + this.year);
    }
}
