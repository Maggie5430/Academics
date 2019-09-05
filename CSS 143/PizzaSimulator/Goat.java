
/**
 * CSS 143 B: Winter 2018 Pizza Simulator
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class Goat extends Cheese {

    /**
     * The Parmesan is a type of Cheese class representing an ingredient to be
     * put on a pizza. The Parmesan has 20 calories, and cost $0.75.
     * Postcondition: Creates an instance of the Parmesan class.
     */
    public Goat() {
        super("Goat", new Money(0, 75), 20);
    }
}
