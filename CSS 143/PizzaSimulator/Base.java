
/**
 * CSS 143 B: Winter 2018 Pizza Simulator
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class Base extends Ingredient {

    /**
     * The Base is a type of ingredient to be put on a pizza, a pizza requires a
     * base. All pizza bases are free ($0.0). Postcondition: Creates an instance
     * of the base class.
     *
     * @param description Name of sauce
     * @param calories Calories per-serving
     */
    public Base(String description, int calories) {
        super(description, new Money(0), calories);
    }
}
