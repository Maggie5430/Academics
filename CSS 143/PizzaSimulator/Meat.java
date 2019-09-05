
/**
 * CSS 143 B: Winter 2018 Pizza Simulator
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class Meat extends Ingredient {

    /**
     * The Meat is a type of ingredient to be put on a pizza. All pizza bases
     * are free ($1.50). Postcondition: Creates an instance of the base class.
     *
     * @param description Name of meat
     * @param calories Calories per-serving
     */
    public Meat(String description, int calories) {
        super(description, new Money(1, 50), calories);
    }
}
