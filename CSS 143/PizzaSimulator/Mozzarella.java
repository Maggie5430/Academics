
/**
 * CSS 143 B: Winter 2018 Pizza Simulator
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class Mozzarella extends Cheese {

    /**
     * The Mozzarella is a type of Cheese class representing an ingredient to be
     * put on a pizza. The Parmesan has 20 calories, and is free ($0.0).
     * Postcondition: Creates an instance of the Mozzarella class.
     */
    public Mozzarella() {
        super("Mozzarella", new Money(0, 0), 90);
    }
}
