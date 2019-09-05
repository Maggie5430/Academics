package lab2;

/**
 * Lab2 
 * CSSKL 143B, Winter 2018 
 * 1/15/18
 *
 * This class contains my work from Lab 2: Point2D.
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class Point2D {

    private int x;
    private int y;
    /**
     * Point2D constructor ().
     */
    public Point2D() {

    }

    /**
     * Point2D constructor (x position, y position).
     * @param x position
     * @param y position
     */
    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns X position.
     * @return X position
     */
    public int getX() {
        return this.x;
    }

    /**
     * Sets X position.
     * @param nX new X position
     */
    public void setX(int nX) {
        this.x = nX;
    }

    /**
     * Sets Y position.
     * @return Y position
     */
    public int getY() {
        return this.y;
    }

    /**
     * Sets Y position. 
     * @param nY new Y position
     */
    public void setY(int nY) {
        this.y = nY;
    }

    /**
     * Sets X and Y positions to zero.
     */
    public void resetToOrigin() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Adds value to X and Y position.
     * @param dx add to X
     * @param dy add to Y
     */
    public void translate(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    /**
     * Returns object to a string.
     * @return Point at (x,y)
     */
    @Override 
    /* Question: what does @Override do? 
     * replaces to default toString provided for all objects with my toString.*/
    public String toString() {
        return ("Point @ (" + this.x + ", " + this.y + ")");
    }


    /**
     * Returns wither two Point2D objects are equal to one another. 
     * @param that point to compare to
     * @return true if equal 
     */
    public boolean equals(Point2D that) {
        //returns true if this is equal to that; don’t just use “this == that”
        if (this.x == that.x && this.y == that.y) {
            return true;
        } else {
            return false;
        }
    }
}
