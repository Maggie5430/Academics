
import java.awt.*;

/**
 * CSS 143 B: Winter 2018 Pizza Simulator
 *
 * @author Margaret Connor
 * @version 1.0
 */
abstract class Shape implements Cloneable {

    private int x = 0;
    private int y = 0;
    private Color baseColor;

    /**
     * The shape class represents the shape of the Pizza and can currently
     * either be a square or circle. Postcondition: Creates an instance of the
     * shape class.
     *
     * @param xPos X coordinate of Circle
     * @param yPos Y coordinate of Circle
     * @param newColor Color of the shape
     */
    public Shape(int xPos, int yPos, Color newColor) {
        this.x = xPos;
        this.y = yPos;
        this.baseColor = newColor;
    }

    /**
     * Returns area of shape. Postcondition: Returns area of shape.
     *
     * @return The area
     */
    public double getArea() {
        return -1;
    }

    /**
     * Draws the shape to a Graphics. Postcondition: Draws the shape to a
     * Graphics.
     *
     * @param g Graphic to draw to
     */
    public void draw(Graphics g) {
    }

    /**
     * Returns the X position of this shape. Postcondition: Returns the X
     * position of this shape.
     *
     * @return X position
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the Y position of this shape. Postcondition: Returns the Y
     * position of this shape.
     *
     * @return Y position
     */
    public int getY() {
        return y;
    }

    /**
     * Changes the color of this shape. Postcondition: Sets the color position
     * of this shape.
     */
    public void setColor(Color newColor) {
        this.baseColor = new Color(newColor.getRGB());
    }

    /**
     * Returns the color of this shape. Postcondition: Returns the color
     * position of this shape.
     *
     * @return Color of pizza
     */
    public Color getColor() {
        return new Color(this.baseColor.getRGB());
    }

    /**
     * Creates and returns a Deep copy of this object. Postcondition: Returns a
     * deep copy of the circle.
     *
     * @return Deep copy of shape
     */
    @Override
    public abstract Object clone();

    /**
     * Indicates whether some other Shape is "equal to" this one. Postcondition:
     * returns true if two Shapes are equal to each other.
     *
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        } else if (other instanceof Shape) {
            return false;
        }
        Shape otherShape = (Shape) other;
        return this.x == otherShape.x && this.y == otherShape.y
                && this.baseColor.equals(otherShape.baseColor);
    }
}
