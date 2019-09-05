
import java.awt.Color;

/**
 * CSS 143 B: Winter 2018 Pizza Simulator
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class Vegetable extends Ingredient {

    private Color vegColor;

    /**
     * The Vegetable is a type of ingredient to be put on a pizza. All
     * vegetables are $1.25. The default color of a vegetable is green.
     * Postcondition: Creates an instance of the vegetable class.
     *
     * @param description Name of vegetable
     * @param calories Calories per-serving
     */
    public Vegetable(String description, int calories) {
        super(description, new Money(1, 25), calories);
        this.vegColor = Color.GREEN;
    }

    /**
     * The Vegetable is a type of ingredient to be put on a pizza. All
     * vegetables are $1.25. Postcondition: Creates an instance of the vegetable
     * class.
     *
     * @param description Name of vegetable
     * @param calories Calories per-serving
     * @param vegColor Color of vegetable
     */
    public Vegetable(String description, int calories, Color vegColor) {
        super(description, new Money(1, 25), calories);
        this.vegColor = new Color(vegColor.getRGB());
    }

    /**
     * Sets a new color for the this vegetable. Postcondition: Changes the color
     * of the vegetable to the new vegetable color.
     *
     * @param newColor New vegetable color
     */
    public void setVegColor(Color newColor) {
        this.vegColor = new Color(newColor.getRGB());
    }

    /**
     * Returns a new color for the this vegetable. Postcondition: Returns the
     * color of the vegetable to the new vegetable color.
     *
     * @return Vegetable color
     */
    public Color getVegColor() {
        return new Color(this.vegColor.getRGB());
    }

    /**
     * Indicates whether some other Vegetable is "equal to" this one.
     * Postcondition: returns true if two Shapes are equal to each other.
     *
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || other.getClass() != new Vegetable("", 0).
                getClass()) {
            return false;
        }
        Vegetable otherVeg = (Vegetable) other;
        return super.equals(otherVeg)
                && this.vegColor.equals(otherVeg.vegColor);
    }

    /**
     * Returns a string representing the Vegetable. Postcondition: Returns
     * Ingredient information as a String.
     *
     * @return String
     */
    @Override
    public String toString() {
        return super.toString() + " and the color: " + this.vegColor.toString();
    }
}
