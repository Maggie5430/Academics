package lab3;

/**
 * Lab3 CSSKL 143B, Winter 2018 1/25/18
 *
 * This class contains my work from Lab 3: Point2D
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
     * Point2D constructor (x position, y position).
     * @param newPoint
     */
    public Point2D(Point2D newPoint) {
        this.x = newPoint.x;
        this.y = newPoint.y;
    }

    /**
     * Precondition: None 
     * Postcondition: Returns X position
     * 
     * @return X position
     */
    public int getX() {
        return this.x;
    }

    /**
     * Precondition: None 
     * Postcondition: Sets new value to X
     * 
     * @param nX new X position
     */
    public void setX(int nX) {
        this.x = nX;
    }

    /**
     * Precondition: None 
     * Postcondition: Returns Y position
     * 
     * @return Y position
     */
    public int getY() {
        return this.y;
    }

    /**
     * Precondition: None 
     * Postcondition: Sets new value to Y
     * 
     * @param nY new Y position
     */
    public void setY(int nY) {
        this.y = nY;
    }

    /**
     * Precondition: None 
     * Postcondition: Sets both x and y to zero.
     */
    public void resetToOrigin() {
        this.x = 0;
        this.y = 0;
    }

    /**
     Precondition: None 
     * Postcondition: adds values to x and Y.
     * @param dx add to X
     * @param dy add to Y
     */
    public void translate(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    /**
     * Precondition: None 
     * Postcondition: Returns string of object
     * 
     * @return Point at (x,y)
     */
    @Override 
    /* Question: what does @Override do? 
     * replaces to default toString provided for all objects with my toString.*/
    public String toString() {
        return ("[x=" + this.x + ", y=" + this.y + "]");
    }


    /**
     * Precondition: None 
     * Postcondition:Returns true if Point2D objects are equal to one another. 
     * 
     * @param that point to compare to
     * @return true if equal 
     */
    public boolean equals(Point2D that) {
        //returns true if this is equal to that; don’t just use “this == that”
        return this.x == that.x && this.y == that.y;
    }
}
