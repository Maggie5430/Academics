package lab2;

/**
 * Lab2 
 * CSSKL 143B, Winter 2018 
 * 1/15/18
 *
 * This class contains my work from Lab 2: the Square Class.
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class Square {

    int x;
    int y;
    double sideLength;

    /**
     * Square object constructor ().
     */
    public Square() {

    }

    /**
     * Square object constructor (x position,y position).
     *
     * @param x
     * @param y
     */
    public Square(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Square object constructor (x position,y position, length).
     *
     * @param x
     * @param y
     * @param sideLength
     */
    public Square(int x, int y, double sideLength) {
        this.x = x;
        this.y = y;
        this.sideLength = sideLength;
    }

    /**
     * Draws object representation to console.
     */
    public void draw() {
        System.out.println(this.toString());
    }

    /**
     * Returns X position.
     *
     * @return X position
     */
    public int getX() {
        return this.x;
    }

    /**
     * Returns y position.
     *
     * @return Y position
     */
    public int getY() {
        return this.y;
    }

    /**
     * Returns side length.
     *
     * @return length
     */
    public double getSideLength() {
        return this.sideLength;
    }

    /**
     * Returns area.
     *
     * @return area
     */
    public double getarea() {
        return (this.sideLength * 2);
    }

    /**
     * Sets X position to (new X).
     *
     * @param nX new X
     */
    public void setX(int nX) {
        this.x = nX;
    }

    /**
     * Sets Y position to (new Y).
     *
     * @param nY new Y
     */
    public void setY(int nY) {
        this.y = nY;
    }

    /**
     * Sets length to (new Length).
     *
     * @param nSL new Length
     */
    public void setSideLength(double nSL) {
        this.sideLength = nSL;
    }

    @Override
    public String toString() {
        String sReturn = "";
        //builds square from top left cornor 
        for (int k = 0; k < y; k++) {
            sReturn += "\n";
        }
        for (int m = 0; m < sideLength; m++) {
            for (int i = 0; i < x; i++) {
                sReturn += (" ");
            }
            for (int i = 0; i < sideLength; i++) {
                sReturn += ("[]");
            }
            sReturn += ("\n");
        }
        return sReturn;
    }

    /**
     * Returns true if two Square objects are equal.
     *
     * @param that
     * @return True if equal
     */
    public boolean equals(Square that) {
        if (this.x == that.x && this.y == that.y
                && this.sideLength == that.sideLength) {
            return true;
        }
        return false;
    }
}
