package shape.inheritance;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * CSS 143 B, Winter 2018 Shape Inheritance
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class Triforce extends Shape {

    private int length;

    /**
     * Precondition: Length is non-negative 
     * Postcondition: Initializes an
     * instance of the Triforce shape
     *
     * @param x
     * @param y
     * @param length
     */
    public Triforce(int x, int y, int length) {
        super(x, y);
        setLength(length);

    }

    /**
     * Precondition: Length is greater than zero 
     * Postcondition: Draws Triforce
     * shape to graphic Uses graphic to draw an image of a Triforce consisting
     * of three triangle polygons, using the length and (x, y) coordinates that
     * have been assigned.
     *
     * @param baseGraphic
     */
    @Override
    public void draw(Graphics baseGraphic) {
        Graphics2D shapePrint = (Graphics2D) baseGraphic;
        /* All cordinates corrospond to point from left to right no matter 
         * the y positon */
        //triangle 1 bottom left triangle 
        int[] xPositionsT1 = new int[]{getX(), getX() + (length / 4),
            getX() + (length / 2)};
        int[] yPositionsT1 = new int[]{getY() + length, getY() + (length / 2),
            getY() + length};

        //triangle 2 bottom right triangle 
        int[] xPositionsT2 = new int[]{getX() + (length / 2),
            getX() + (3 * length / 4), getX() + length};
        int[] yPositionsT2 = new int[]{getY() + length, getY() + (length / 2),
            getY() + length};

        //Point cordinates for triangle 3 top center triangle 
        int[] xPositionsT3 = new int[]{getX() + (length / 4),
            getX() + (length / 2), (length / 2) + getX() + (length / 4)};
        int[] yPositionsT3 = new int[]{getY() + (length / 2),
            getY(), getY() + (length / 2)};

        shapePrint.setColor(Color.YELLOW);
        //Draws bottom left triangle 
        shapePrint.fillPolygon(xPositionsT1, yPositionsT1, 3);
        //Draws bottom right triangle 
        shapePrint.fillPolygon(xPositionsT2, yPositionsT2, 3);
        //Draws top center triangle
        shapePrint.fillPolygon(xPositionsT3, yPositionsT3, 3);
    }

    /**
     * Returns area of filled Triforce Shape
     *
     * @return
     */
    @Override
    public double getArea() {
        return (length / 2) * 3;
    }

    /**
     * Precondition: Length is non-negative 
     * Postcondition: New length set to
     * non-negative number
     *
     * @param newLength
     */
    public void setLength(int newLength) {
        if (newLength > 0) {
            this.length = newLength;

        } else {
            System.out.println("Invalid Length");
        }
    }

    /**
     * Precondition: none 
     * Postcondition: returns length integer
     *
     * @return
     */
    public int getLength() {
        return this.length;
    }

    /**
     * Precondition: None 
     * Postcondition: Returns Triforce's length and
     * coordinate position
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Triforce at [" + this.getX() + ", " + this.getY()
                + "], length: " + this.length;
    }

    /**
     * Precondition: None 
     * Postcondition: Returns true if two triforce objects
     * are equal
     *
     * @param otherObject
     * @return true if equal
     */
    @Override
    public boolean equals(Object otherObject) {
        if (otherObject == null || !(otherObject instanceof Triforce)) {
            return false;
        } else {
            Triforce otherTriforce = (Triforce) otherObject;
            return otherTriforce.getX() == this.getX()
                    && otherTriforce.getY() == this.getY()
                    && otherTriforce.getLength() == this.getLength();
        }
    }
}
