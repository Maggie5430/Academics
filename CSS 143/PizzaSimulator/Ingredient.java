
/**
 * CSS 143 B, Winter 2018 Pizza Simulator
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class Ingredient implements Comparable {

    private Money cost;
    private int calories;
    private String description;

    /**
     * The Ingredient is a type of food to be put on a pizza. Postcondition:
     * Creates an instance of the pizza class.
     *
     * @param description Ingredient name
     * @param cost Ingredient cost
     * @param calories Calories per-serving
     */
    public Ingredient(String description, Money cost, int calories) {
        if (cost == null) {
            throw new NullPointerException();
        } else if (calories <= 0) {
            throw new IllegalArgumentException(
                    "Calories can not be less than 1");
        } else {
            this.cost = new Money(cost);
            this.calories = calories;
            this.description = description;
        }
    }

    /**
     * The Ingredient is a type of food to be put on a pizza. Postcondition:
     * Creates an instance of the pizza class.
     *
     * @param otherIngredient New Ingredient
     */
    public Ingredient(Ingredient otherIngredient) {
        if (otherIngredient != null) {
            this.cost = new Money(otherIngredient.getCost());
            this.calories = otherIngredient.getCalories();
            this.description = otherIngredient.getDescription();
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * Gets and returns the cost of this ingredient. Postcondition: Returns the
     * cost of the ingredient.
     *
     * @return Cost of ingredient
     */
    public Money getCost() {
        return new Money(this.cost);
    }

    /**
     * Gets and returns the calories per-serving of this ingredient.
     * Postcondition: Returns the amount of calories for this ingredient.
     *
     * @return Calories per-serving
     */
    public int getCalories() {
        return this.calories;
    }

    /**
     * Gets and returns the Description of this ingredient. Postcondition:
     * Returns the amount of calories for this ingredient.
     *
     * @return Description of ingredient
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Changes the cost of this ingredient. Precondition: The new cost of the
     * ingredient is greater than $0.00 and is not null Postcondition: Sets the
     * price of this ingredient.
     *
     * @param newCost New cost
     */
    private void setCost(Money newCost) {
        this.cost = new Money(newCost);
    }

    /**
     * Changes the calories of this ingredient. Precondition the new calories is
     * positive Postcondition: Sets the calories for this ingredient.
     *
     * @param newCalories New calories
     */
    private void setCalories(int newCalories) {
        if (newCalories > 0) {
            this.calories = newCalories;
        } else {
            throw new IllegalArgumentException("Calories can not be less than 1");
        }
    }

    /**
     * Changes the description of this ingredient. Postcondition: Sets the
     * description this ingredient.
     *
     * @param newDescription New description
     * @return
     */
    private void setDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * Returns a string representing the ingredient. Postcondition: Returns
     * Ingredient information as a String.
     *
     * @return String
     */
    @Override
    public String toString() {
        return this.description + " has a price:" + this.cost
                + " and calories:" + this.calories;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * Postcondition: Returns true if some other object is "equal to" this one.
     *
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other) {
        if (other != null && other instanceof Ingredient) {
            Ingredient otherIngredient = (Ingredient) other;
            if (this.calories == otherIngredient.getCalories()
                    && this.getCost().equals(otherIngredient.getCost())
                    && this.description.equals(otherIngredient.getDescription()
                    )) {
                return true;
            }
        }
        return false;
    }

    /**
     * Compares this Ingredient with the specified object. Returns a negative
     * integer, zero, or a positive integer as this object is less than, equal
     * to, or greater than the specified object.
     *
     * @param other
     * @return
     */
    @Override
    public int compareTo(Object other) {
        if (other != null && other instanceof Ingredient) {
            throw new PizzaException("Invalid compareTo object");
        }

        Ingredient otherIngredient = (Ingredient) other;
        return this.cost.compareTo(otherIngredient.cost);
    }
}
