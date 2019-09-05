package pkgnew.and.review;

/**
 * Lab1b
 * CSSKL 143B, Winter 2018
 * 1/07/18
 * 
 * Part: Accessors and Mutators (or, Getters and Setters).
 * 
 * @author Margaret Connor
 */
public class Date {

    private int minutes;
    private int seconds;
    private int hours;

    /**
     * Constructs new Date object
     * 
     * @param minutes minutes
     * @param seconds seconds
     * @param hours hours
     */
    public Date(int minutes, int seconds, int hours) {
        this.minutes = minutes;
        this.seconds = seconds;
        this.hours = hours;
    }

    /**
     * Returns minutes
     *
     * @return minutes
     */
    public int getMinutes() {
        return this.minutes;
    }

    /**
     * Sets minutes
     *
     * @param minutes minutes
     */
    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    /**
     * Returns seconds
     *
     * @return seconds
     */
    public int getSeconds() {
        return this.seconds;
    }

    /**
     * Sets seconds
     * 
     * @param seconds seconds
     */
    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    /**
     *  Return hours
     *
     * @return hours
     */
    public int getHours() {
        return this.hours;
    }

    /**
     * Sets hours
     * 
     * @param hours hours
     */
    public void setHours(int hours) {
        this.hours = hours;
    }
}
