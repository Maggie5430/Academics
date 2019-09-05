
import java.awt.Color;

/**
 * CSS 143 B: Winter 2018 Pizza Simulator
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class Square extends Shape {

    int length;

    /**
     * The Square is a type of pizza shape. Postcondition: Creates an instance
     * of the Square class.
     *
     * @param x X coordinate of Square
     * @param y Y coordinate of Square
     * @param length length of Square
     */
    public Square(int x, int y, int length) {
        super(x, y, new Color(188, 159, 96));
        this.length = length;
    }

    /**
     * Returns the area of the square. Postcondition: Returns the area of the
     * shape.
     *
     * @return Area of shape
     */
    @Override
    public double getArea() {
        return this.length * this.length;
    }

    /**
     * Returns the length of the square. Postcondition: Returns the length of
     * the square.
     *
     * @return length
     */
    public double getLength() {
        return this.length;
    }

    /**
     * Changes the length of the square. Postcondition: Sets the length of the
     * square.
     *
     * @param newLength New Length
     */
    public void setLength(int newLength) {
        this.length = newLength;
    }

    /**
     * Creates and returns a Deep copy of this object. Postcondition: Returns a
     * deep copy of the Square.
     *
     * @return Deep copy of shape
     */
    @Override
    public Object clone() {
        return new Square(this.getX(), this.getY(), length);
    }
}
