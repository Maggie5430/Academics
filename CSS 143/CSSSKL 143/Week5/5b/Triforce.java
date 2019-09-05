package lab5b;

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
     * Initializes instance of the Triforce Shape.
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
     * Draws Triforce shape to graphic
     *
     * @param baseGraphic
     */
    @Override
    public void draw(Graphics baseGraphic) {
        int[] xPositionsT1; //X cordinates for bottem left triangle
        int[] yPositionsT1; //Y cordinates for bottem left triangle
        int[] xPositionsT2; //X cordinates for bottom right triangle 
        int[] yPositionsT2; //Y cordinates for bottom right triangle 
        int[] xPositionsT3; //X cordinates for top center triangle
        int[] yPositionsT3; //Y cordinates for top center triangle
        final int x = getX();
        final int y = getY();

        /* All cordinates corrospond to point from left to right no matter 
         * the y positon */
        //triangle 1 bottom left triangle 
        xPositionsT1 = new int[]{x, x + (length / 4), x + (length / 2)};
        yPositionsT1 = new int[]{y + length, y + (length / 2), y + length};

        //triangle 2 bottom right triangle 
        xPositionsT2 = new int[]{x + (length / 2), x + (3 *length / 4), x + length};
        yPositionsT2 = new int[]{y + length, y + (length / 2), y + length};

        //Point cordinates for triangle 3 top center triangle 
        xPositionsT3 = new int[]{x + (length / 4),x + (length / 2),(length / 2) + x + (length / 4)};
        yPositionsT3 = new int[]{y + (length / 2), y,y + (length / 2)};

        Graphics2D shapePrint = (Graphics2D) baseGraphic;


        
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
     * Sets Length and Triforce coordinates based on length
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
     * Returns length of Triforce
     *
     * @return
     */
    public int getLength() {
        return this.length;
    }

    @Override
    public String toString() {
        return "Triforce at [" + this.getX() + ", " + this.getY()
                + "], length: " + this.length;
    }

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
