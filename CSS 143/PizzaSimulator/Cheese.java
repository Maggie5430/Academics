
/**
 * CSS 143 B: Winter 2018 Pizza Simulator
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class Cheese extends Ingredient {

    /**
     * The Cheese is a type of ingredient to be put on a pizza, a pizza requires
     * a Cheese. Postcondition: Creates an instance of the Cheese class.
     *
     * @param description Name of cheese
     * @param cost Cost of cheese
     * @param Calories Calories per-serving
     */
    public Cheese(String description, Money cost, int Calories) {
        super(description, cost, Calories);
    }
}
