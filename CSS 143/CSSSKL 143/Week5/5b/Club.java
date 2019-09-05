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
public class Club extends Shape {

    int length;

    public Club(int x, int y, int length) {
        super(x, y);
        setLength(length);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D shapePrint = (Graphics2D) g;

        int[] triangleXPositions = {this.getX() + length / 5, getX() + length / 2, this.getX() + (4 * length / 5)};
        int[] triangleYPositions = {this.getY() + length, getY() + (3 * length / 4), this.getY() + length};

       
        shapePrint.setColor(Color.BLACK);
        //Draws the triangle base
        shapePrint.fillPolygon(triangleXPositions, triangleYPositions, 3);
        //Draws the center circle
        shapePrint.fillOval(getX() + length / 4, getY(), length / 2, length / 2);
        //Draws the left circle
        shapePrint.fillOval(getX(), getY() + (length / 3), length / 2, length / 2);
        //Draws the right circle
        shapePrint.fillOval(getX() + (length / 2), getY() + (length / 3), length / 2, length / 2);
        //fills in the center gap
        shapePrint.fillRect(getX() + length / 4, getY() + length / 2, length / 2, length / 4);

    }

    public void setLength(int newLength) {
        if (newLength >= 0) {
            this.length = newLength;
        }
    }

    public int getLength() {
        return this.length;
    }

    @Override
    public String toString() {
        return "Club at [" + this.getX() + ", " + this.getY()
                + "], length: " + this.length;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (otherObject == null || !(otherObject instanceof Club)) {
            return false;
        } else {
            Club otherClub = (Club) otherObject;
            return otherClub.getX() == this.getX()
                    && otherClub.getY() == this.getY()
                    && otherClub.getLength() == this.getLength();
        }
    }
}
