package shape.inheritance;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * CSS 143 B, Winter 2018 Shape Inheritance
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class BtsSymbol extends Shape {

    int length;

    /**
     * Precondition: Length is non-negative
     * Postcondition: Initializes an instance of the BtsSymbol shape
     *
     * @param x
     * @param y
     * @param length
     */
    public BtsSymbol(int x, int y, int length) {
        super(x, y);
        setLength(length);

    }

    /**
     * Precondition: Length is greater than zero 
     * Postcondition: Draws BtsSymbol shape to graphic 
     * Uses graphic to draw an image of a BTS Symbol consisting of a circle
     * and two polygon, using the length and (x, y) coordinates that have been 
     * assigned.
     *
     * @param baseGraphic
     */
    @Override
    public void draw(Graphics baseGraphic) {
        Graphics2D shapePrint = (Graphics2D) baseGraphic;

        //left verticle polygon
        int[] leftPillarXPosition = {getX() + 7 * length / 15,
            getX() + 4 * length / 15, getX() + 4 * length / 15, 
            getX() + 7 * length / 15};
        int[] leftPillarYPosition = {getY() + 2 * length / 8, 
            getY() + length / 8, getY() + 7 * length / 8, 
            getY() + 6 * length / 8};
        //right verticle polygon
        int[] rightPillarXPosition = {getX() + 11 * length / 15, 
            getX() + 8 * length / 15, getX() + 8 * length / 15, 
            getX() + 11 * length / 15};
        int[] rightPillarYPosition = {getY() + length / 8, 
            getY() + 2 * length / 8, getY() + 6 * length / 8,
            getY() + 7 * length / 8};

        //Black circle background
        shapePrint.setColor(Color.BLACK);
        shapePrint.fillOval(this.getX(), this.getY(), length, length);

        //Polygons with gradiant color
        shapePrint.setPaint(new GradientPaint(getX() + length / 4, getY(), 
                Color.MAGENTA, getX() + length, getY() + length, 
                Color.CYAN, true));
        shapePrint.fillPolygon(leftPillarXPosition, leftPillarYPosition, 4);
        shapePrint.fillPolygon(rightPillarXPosition, rightPillarYPosition, 4);
    }

    /**
     * Precondition: Length is non-negative
     * Postcondition: New length set to non-negative number
     *
     * @param newLength
     */
    public void setLength(int newLength) {
        if (newLength >= 0) {
            this.length = newLength;
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
     * Postcondition: Returns BtsSybol's length and coordinate position
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
     * Postcondition: Returns true if two BtsSymbol objects are equal
     * 
     * @param otherObject
     * @return true if equal
     */
    @Override
    public boolean equals(Object otherObject) {
        if (otherObject == null || !(otherObject instanceof BtsSymbol)) {
            return false;
        } else {
            BtsSymbol otherBtsSymbol = (BtsSymbol) otherObject;
            return otherBtsSymbol.getX() == this.getX()
                    && otherBtsSymbol.getY() == this.getY()
                    && otherBtsSymbol.getLength() == this.getLength();
        }
    }
}
