package lab3;

import java.awt.Color;

/**
 * Lab3 CSSKL 143B, Winter 2018 1/25/18
 *
 * This class contains my work from Lab 3: Shape
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class Shape {

    private int x;
    private int y;
    private Color color;

    /**
     * Initializes a new instance of the Shape class.
     */
    public Shape() {

    }

    /**
     * Initializes a new instance of the Shape class.
     *
     * @param x
     * @param y
     * @param color
     */
    public Shape(int x, int y, Color color) {
        if (x > -1 && y > -1) {
            this.x = x;
            this.y = y;
            this.color = color;
        } else {
            System.out.println("ERROR: Shape parameters must be positive");
        }
    }

    /**
     * Initializes a new instance of the Shape class.
     *
     * @param other
     */
    public Shape(Shape other) {
        this.x = other.x;
        this.y = other.y;
        this.color = other.color;
    }

    /**
     * Precondition: None 
     * Postcondition: Returns String of date
     *
     * @return String
     */
    @Override
    public String toString() {
        return ("Shape (" + this.x + "," + this.y + ")and color:" + this.color 
                + " and area: " + this.getArea());
    }

    /**
     * Precondition: none
     * Postcondition: Return area of shape
     * 
     * @return
     */
    public double getArea() {
        //not used yes
        return -1;
    }

    /**
     * Precondition: none
     * Postcondition: Prints drawing of shape to console.
     */
    public void draw(/*Graphics g*/) {
        //not used yet
    }

    /**
     * Precondition: none
     * Postcondition: Returns X position
     *
     * @return X position
     */
    public int getX() {
        return this.x;
    }

    /**
     * Precondition: none
     * Postcondition: Returns Y position
     *
     * @return Y position
     */
    public int getY() {
        return this.y;
    }

    /**
     * Precondition: none
     * Postcondition: Sets new X position
     *
     * @param nX new X
     */
    public void setX(int nX) {
        this.x = nX;
    }

    /**
     * Precondition: none
     * Postcondition: Sets new position Y
     *
     * @param nY new Y
     */
    public void setY(int nY) {
        this.y = nY;
    }
}
