
import java.util.Random;

/**
 * CSS 143 B: Winter 2018 Pizza Simulator
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class Pizza implements PizzaComparable {

    private ArrayList<Ingredient> ingredients;
    private Shape pizzaShape;
    private Money pizzaCost;
    private int pizzaCalories;
    private Fraction pizzaRemaining;

    /**
     * Creates an instance of the pizza class with a random base, cheese, shape
     * and up to three random toppings. Postcondition: New instance of the pizza
     * class is created.
     */
    public Pizza() {
        this.pizzaCost = new Money(11);
        this.pizzaCalories = 190;
        this.pizzaRemaining = new Fraction(1, 1);   //Invariant
        this.ingredients = new ArrayList<>();

        Random rand = new Random();

        //Pizza random Shape
        int randomVariable = rand.nextInt(2) + 1;
        switch (randomVariable) {
            case 1:
                this.pizzaShape = new Circle(0, 0, 10);
                break;
            case 2:
                this.pizzaShape = new Square(0, 0, 10);
                break;
        }

        //Pizza random Base
        randomVariable = rand.nextInt(2) + 1;
        switch (randomVariable) {
            case 1:
                this.addIngredient(new Alfredo());
                break;
            case 2:
                this.addIngredient(new Marinara());
                break;
        }

        //Pizza random Cheese
        randomVariable = rand.nextInt(2) + 1;
        switch (randomVariable) {
            case 1:
                this.addIngredient(new Mozzarella());
                break;
            case 2:
                this.addIngredient(new Goat());
                break;
        }

        //Up to 3 random toppings 
        randomVariable = rand.nextInt(3) + 1;
        for (int i = 0; i < randomVariable; i++) {
            int toppings = rand.nextInt(4) + 1;
            switch (toppings) {
                case 1:
                    this.addIngredient(new Pepperoni());
                    break;
                case 2:
                    this.addIngredient(new Sausage());
                    break;
                case 3:
                    this.addIngredient(new Olive());
                    break;
                case 4:
                    this.addIngredient(new Pepper());
                    break;

            }
        }
    }

    /**
     * Creates an instance of the pizza class with at least a cheese and base.
     * Postcondition: New instance of the pizza class is created.
     *
     * @param shape
     * @param cheese
     * @param base
     */
    public Pizza(Shape shape, Cheese cheese, Base base) {
        this.pizzaRemaining = new Fraction(1, 1); //Invariant
        this.ingredients = new ArrayList<>();
        this.pizzaShape = shape;
        this.pizzaCost = new Money(11, 0);  //base pizza cost 
        this.pizzaCalories = 190;           //base pizza calories (dough)
        this.addIngredient(base);           //Invariant
        this.addIngredient(cheese);         //Invariant
    }

    /**
     * Returns the cost of the pizza. Postcondition: Returns, Money object equal
     * to the total sum of the pizza.
     *
     * @return Money, cost of pizza
     */
    public Money getPizzaCost() {
        return new Money((int) (this.pizzaCost.getDollars()
                * this.pizzaRemaining.getFraction()),
                (int) (this.pizzaCost.getCents()
                * this.pizzaRemaining.getFraction()));
    }

    /**
     * Returns the remaining (surface) area of pizza. Postcondition: Returns the
     * remaining amount of pizza as a fraction.
     *
     * @return Fraction, remaining pizza
     */
    public Fraction getRemaining() {
        return new Fraction(this.pizzaRemaining);
    }

    /**
     * Returns this Pizza calories. Postcondition: Returns the total calorie
     * count of the pizza.
     *
     * @return Integer, calories of pizza
     */
    public int getCalories() {
        return this.pizzaCalories;
    }

    /**
     * Returns remaining area of pizza. Postcondition: Returns the remaining
     * area of pizza.
     *
     * @return Double, remaining pizza
     */
    public double getRemainingArea() {
        return this.pizzaShape.getArea() * this.pizzaRemaining.getFraction();
    }

    /**
     * Changes the remaining amount of pizza. Precondition: New fraction cannot
     * be less than or equal to zero or more than one. Postcondition: Remaining
     * pizza set to new fraction.
     *
     * @param newFraction Fraction
     */
    public void setRemaining(Fraction newFraction) {
        if (newFraction.getFraction() <= 0 || newFraction.getFraction() > 1) {
            throw new PizzaException("Cannot have less than 0 or "
                    + "more than 1 remaining pizza!");
        }
        this.pizzaRemaining = new Fraction(newFraction);
    }

    /**
     * Changes the shape of the pizza. Postcondition: Sets the shape of the
     * pizza to the specified shape.
     *
     * @param newShape New Shape
     */
    public void setShape(Shape newShape) {
        this.pizzaShape = (Shape) newShape.clone();
    }

    /**
     * Returns the shape of the pizza. Postcondition: Gets and returns the shape
     * of this pizza.
     *
     * @return Shape of pizza
     */
    public Shape getShape() {
        return (Shape) this.pizzaShape.clone();
    }

    /**
     * Adds new ingredient to the pizza and adds its calories and cost to the
     * total calories and cost. Postcondition: new Ingredient added.
     *
     * @param newIngredient
     */
    final public void addIngredient(Ingredient newIngredient) {
        this.pizzaCost.add(newIngredient.getCost());
        this.pizzaCalories += newIngredient.getCalories();
        this.ingredients.insert(newIngredient, ingredients.size());
    }

    /**
     * Removes a fraction of the pizza from remaining pizza. Precondition:
     * Fraction must be greater than 0 and less than the amount of pizza still
     * remaining. Postcondition: Remainder of pizza is reduced by fraction.
     *
     * @param eat
     */
    public void eatSomePizza(Fraction eat) {
        if (eat.getFraction() >= 0) {
            Fraction pizzaLeft = this.pizzaRemaining.subtractFraction(eat);
            if (pizzaLeft.getFraction() == 0) {
                this.pizzaRemaining = pizzaLeft;
                throw new PizzaException("You've finnished your pizza");
            } else if (pizzaLeft.getFraction() < 0) {
                throw new IllegalArgumentException("You don't have enough pizza"
                        + " to eat.");
            } else {
                this.pizzaRemaining = pizzaLeft;
            }
        } else {
            throw new IllegalArgumentException("Fraction can not be negative");
        }
    }

    //compare by costs
    /**
     * Compares this Pizza with the specified object. Returns a negative
     * integer, zero, or a positive integer as this object's cost is less than,
     * equal to, or greater than the specified object's cost.
     *
     * @param other
     * @return
     */
    @Override
    public int compareTo(Object other) {
        if (other == null) {
            throw new NullPointerException();
        } else if (other.getClass() != new Pizza().getClass()) {
            throw new IllegalArgumentException("Not a pizza!");
        }
        Pizza otherPizza = (Pizza) other;
        return this.getPizzaCost().compareTo(otherPizza.getPizzaCost());
    }

    /**
     * Returns a string representation of the Pizza. Lists the price, calories,
     * remaining fraction, area, and shape. Postcondition: Returns a string to
     * represent the pizza object.
     *
     * @return String
     */
    @Override
    public String toString() {
        String pizzaOrder = "Pizza has a price:" + this.getPizzaCost()
                + " and calories:" + this.pizzaCalories
                + ", Fraction remaining:" + this.pizzaRemaining
                //calculate remainin area based on fraction
                + " and area left: "
                + (this.pizzaShape.getArea()
                * ((double) this.pizzaRemaining.getNumerator()
                / this.pizzaRemaining.getdenominator()))
                + " and shape: ";
        if (this.pizzaShape.getClass() == new Circle(1, 1, 1).getClass()) {
            pizzaOrder += "Circle";
        } else {
            pizzaOrder += "Square";
        }
        return pizzaOrder;
    }

    /**
     * Compares this Money with the specified object. Returns a negative
     * integer, zero, or a positive integer as this object's area is less than,
     * equal to, or greater than the specified object's area.
     *
     * @param other
     * @return
     */
    @Override
    public int compareToBySize(Object other) {
        if (other == null) {
            throw new NullPointerException();
        } else if (other.getClass() != new Pizza().getClass()) {
            throw new IllegalArgumentException("Not a pizza!");
        }
        Pizza otherPizza = (Pizza) other;
        if (this.getRemainingArea() < otherPizza.getRemainingArea()) {
            return -1;
        } else if (this.getRemainingArea() > otherPizza.getRemainingArea()) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Compares this Money with the specified object. Returns a negative
     * integer, zero, or a positive integer as this object's calories is less
     * than, equal to, or greater than the specified object's calories.
     *
     * @param other
     * @return
     */
    @Override
    public int compareToByCalories(Object other) {
        if (other == null) {
            throw new NullPointerException();
        } else if (other.getClass() != new Pizza().getClass()) {
            throw new IllegalArgumentException("Not a pizza!");
        }
        Pizza otherPizza = (Pizza) other;
        if (this.pizzaCalories == otherPizza.pizzaCalories) {
            return 0;
        } else if (this.pizzaCalories > otherPizza.pizzaCalories) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * Indicates whether some other Shape is "equal to" this one. Postcondition:
     * returns true if two Shapes are equal to each other.
     *
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || other.getClass() != new Pizza().getClass()) {
            return false;
        }
        Pizza otherPizza = (Pizza) other;
        return this.ingredients.equals(otherPizza.ingredients)
                && this.pizzaCalories == otherPizza.pizzaCalories
                && this.getPizzaCost().equals(otherPizza.getPizzaCost())
                && this.pizzaRemaining.equals(otherPizza.pizzaRemaining)
                && this.pizzaShape.equals(otherPizza.pizzaShape);
    }

}
