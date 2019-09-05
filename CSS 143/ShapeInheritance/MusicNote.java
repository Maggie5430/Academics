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
public class MusicNote extends Shape {

    int length;

    /**
     * Precondition: Length is non-negative
     * Postcondition: Initializes  instance of the MusicNote Shape. 
     * @param x
     * @param y
     * @param length
     */
    public MusicNote(int x, int y, int length) {
        super(x, y);
        this.setLength(length);
    }

    /**
     * Precondition: Length is greater than zero
     * Postcondition: Draws MusicNote shape to graphic
     * Uses graphic to draw an image of a MusicNote consisting of two circle
     * and three rectangle polygon, using the length and (x, y) coordinates that
     * have been assigned. 
     * @param g
     */
    @Override
    public void draw(Graphics g) {
        Graphics2D shapePrint = (Graphics2D) g;

        //Draws bottom right circle
        shapePrint.setColor(Color.RED);
        shapePrint.fillOval(getX(), getY() + (2 * length / 3), 
                length / 3, length / 3);

        //Draws bottom left circle
        shapePrint.fillOval(getX() + ((2 * length) / 3), 
                getY() + (2 * length / 3), length / 3, length / 3);

        //Draws left verticle bar
        shapePrint.fillRect(getX() + ((4 * length) / 15), getY(), 
                length / 15, (5 * length) / 6);

        //Draws right verticle bar
        shapePrint.fillRect(getX() + ((14 * length) / 15), getY(), 
                length / 15, (5 * length) / 6);

        //Draws top center bar
        shapePrint.fillRect(getX() + ((4 * length) / 15), getY() , 
                length - (length / 3), length / 15);

    }

    /**
     * Precondition: None
     * Postcondition: Returns the length
     * 
     * @return
     */
    public int getLength() {
        return this.length;
    }

    /**
     * Precondition: Length is non-negative
     * Postcondition: New length assigned
     *
     * @param newLength
     */
    public void setLength(int newLength) {
        if (newLength > 0) {
            this.length = newLength;
        } else {
            System.out.println("Invalid length");
        }
    }

    /**
     * Precondition: None
     * Postcondition: Returns MusicNote's length and coordinate position
     * 
     * @return String 
     */
    @Override
    public String toString() {

        return "MusicNote at [" + this.getX() + ", " + this.getY()
                + "], length: " + this.length;
    }

    /**
     * Precondition: None
     * Postcondition: Returns true if two MusicNote objects are equals
     * 
     * @param otherObject
     * @return true if equal
     */
    @Override
    public boolean equals(Object otherObject) {
        if (otherObject == null || !(otherObject instanceof MusicNote)) {
            return false;
        } else {
            MusicNote otherMusicNote = (MusicNote) otherObject;
            return otherMusicNote.getX() == this.getX()
                    && otherMusicNote.getY() == this.getY()
                    && otherMusicNote.getLength() == this.getLength();
        }
    }
}
