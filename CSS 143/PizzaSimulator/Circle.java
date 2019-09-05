
import java.awt.Color;

/**
 * CSS 143 B: Winter 2018 Pizza Simulator
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class Circle extends Shape {

    int radius;

    /**
     * The Circle is a type of pizza shape. Postcondition: Creates an instance
     * of the circle class.
     *
     * @param x X coordinate of Circle
     * @param y Y coordinate of Circle
     * @param radius radius of Circle
     */
    public Circle(int x, int y, int radius) {
        super(x, y, new Color(188, 159, 96));
        this.radius = radius;
    }

    /**
     * Returns the area of the circle. Postcondition: Returns the area of the
     * shape.
     *
     * @return Area of shape
     */
    @Override
    public double getArea() {
        return Math.PI * (Math.pow(radius, 2));
    }

    /**
     * Returns the radius of the circle. Postcondition: Returns the radius of
     * the circle.
     *
     * @return Radius
     */
    public double getRadius() {
        return this.radius;
    }

    /**
     * Changes the radius of the circle. Postcondition: Sets the radius of the
     * circle.
     *
     * @param newRadius New Radius
     */
    public void setRadius(int newRadius) {
        this.radius = newRadius;
    }

    /**
     * Creates and returns a Deep copy of this object. Postcondition: Returns a
     * deep copy of the circle.
     *
     * @return Deep copy of circle
     */
    @Override
    public Object clone() {
        return new Circle(this.getX(), this.getY(), radius);
    }
}
