
import java.util.Scanner;

/**
 * CSS 143 B: Winter 2018 Pizza Simulator
 *
 * @author Margaret Connor
 * @version 1.0
 *
 * PizzaManager Skeleton File CSS 162, Final Project
 *
 * This class is a starting point for your final project and is incomplete. Note
 * that if there are any inconsistencies between this skeleton and the
 * assignment description, the assignment description controls.
 *
 * Author: Rob Nash with edits by Johnny Lin
 */
public class PizzaManager {

    private ArrayList<Pizza> pizzaManager = new ArrayList<>();

    /**
     * This method is a basic interface starting point to manage Pizza.
     */
    public void start() {
        char selection = 'a';
        Scanner scannerInput = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayAllPizzas();
            displayInstructions();

            selection = scannerInput.next().charAt(0);

            switch (selection) {
                case 'A':
                case 'a':
                    System.out.println("Adding a random pizza to the "
                            + "ArrayList<Pizza>.");
                    addRandomPizza();
                    break;
                case 'H':
                case 'h':
                    System.out.println("Adding one hundred random pizzas to the"
                            + " ArrayList<Pizza>.");
                    for (int i = 0; i < 100; i++) {
                        addRandomPizza();
                    }
                    break;
                case 'E':
                case 'e':
                    System.out.println("Eating a fraction of a pizza. How much?"
                            + " (a/b)");
                    try {
                        eatSomePizza(scannerInput);
                    } catch (PizzaException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'P':
                case 'p':
                    System.out.println("Sorting pizzas by (P)rice");
                    sortByPrice();
                    break;
                case 'S':
                case 's':
                    System.out.println("Sorting pizzas by (S)ize");
                    sortBySize();
                    break;
                case 'C':
                case 'c':
                    System.out.println("Sorting pizzas by (C)alories");
                    sortByCalories();
                    break;
                case 'B':
                case 'b':
                    System.out.println("(B)inary search over pizzas by calories"
                            + "(int).  Sorting first.  What calorie count are "
                            + "you looking for?");
                    sortByCalories();
                    try {
                        int searchingFor = scannerInput.nextInt();
                        int pizzaIndex = binarySearchByCalories(searchingFor);
                        if (pizzaIndex >= 0) {
                            System.out.println("Pizza number "
                                    + (binarySearchByCalories(searchingFor) + 1)
                                    + " has " + searchingFor + " calories");
                        } else {
                            System.out.println("No pizzas with " + searchingFor
                                    + " calories per servingsize were found");
                        }
                    } catch (Exception e) {
                        System.out.println("Unrecognized input - try again");
                    }
                    break;
                case 'Q':
                case 'q':
                    System.out.println("(Q)uitting!");
                    scannerInput.close();
                    running = false;
                    break;
                default:
                    System.out.println("Unrecognized input - try again");
            }
        }
    }

    /**
     * Removes a specified fraction of pizza that has been eaten from the pizza
     * manager. Precondition: Provided fraction is in "a/b" form and the
     * fraction is a positive number, index must also be in between or equal to
     * 1 and the size of the pizzaManager array. Postcondition: Removes a
     * fraction of the pizza from a specified pizza.
     *
     * @param keys
     */
    private void eatSomePizza(Scanner keys) {
        Fraction eating = new Fraction(0, 0);
        try {
            //catches input that isn't "a/b"
            String fractionInput = keys.next();
            eating = new Fraction(Integer.parseInt(
                    fractionInput.substring(0, fractionInput.indexOf("/"))),
                    Integer.parseInt(fractionInput.substring(
                            fractionInput.indexOf("/") + 1)));
        } catch (Exception e) {
            throw new PizzaException("Unrecognized input-try again");
        }

        if (this.pizzaManager.isEmpty()) {
            //checks to makesure there are pizzas 
            throw new PizzaException("You have no pizzas to eat! Fraction is "
                    + "too large");
        }

        int pizzaIndex = -1;
        System.out.println("Which Pizza would you like to eat from [1 - "
                + this.pizzaManager.size() + "]");

        try {
            pizzaIndex = keys.nextInt() - 1;
        } catch (Exception e) {
            throw new PizzaException("Unrecognized input-try again");
        }

        if (pizzaIndex >= 0 && pizzaIndex < this.pizzaManager.size()) {
            try {
                this.pizzaManager.get(pizzaIndex).eatSomePizza(eating);
            } catch (PizzaException e) {
                //Catches the pizza exception and removes the pizza.\
                System.out.println("You have finnished eating all of pizza "
                        + (pizzaIndex + 1));
                this.pizzaManager.remove(pizzaIndex);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Invalid Index");
        }
    }

    /**
     * Adds a single random pizza to the pizza manager. Postcondition: adds a
     * pizza to pizza manager.
     */
    private void addRandomPizza() {
        this.pizzaManager.insert(new Pizza(), 0);
    }

    /**
     * Prints all pizza to the console. Postcondition: Out prints all the pizza
     * information.
     */
    private void displayAllPizzas() {
        for (int i = 0; i < this.pizzaManager.size(); i++) {
            System.out.println(i + 1 + ". " + this.pizzaManager.get(i));
        }
    }

    /**
     * Uses a selection sort to sort pizzaManager by it's price. Postcondition:
     * sorts pizzaManager by each pizza price.
     */
    private void sortByPrice() {
        //selection sort
        ArrayList<Pizza> sorted = new ArrayList<>();
        int orderNum = pizzaManager.size();
        for (int i = 0; i < orderNum; i++) {
            int minIndex = 0;       //stores index of smallest pizza cost
            for (int k = 1; k < this.pizzaManager.size(); k++) {
                if (this.pizzaManager.get(k).compareTo(
                        pizzaManager.get(minIndex)) < 0) {
                    minIndex = k;
                }
            }
            sorted.insert(pizzaManager.remove(minIndex), 0);
        }
        pizzaManager = sorted;
    }

    /**
     * Uses a insertion sort to sort pizzaManager by it's area. Postcondition:
     * sorts pizzaManager by each pizza area.
     */
    private void sortBySize() {
        //insertion sort
        for (int i = 1; i < this.pizzaManager.size(); i++) {
            for (int k = i; k > 0; k--) {
                if (this.pizzaManager.get(k - 1).
                        compareToBySize(this.pizzaManager.get(k)) < 0) {
                    this.pizzaManager.swap(k, k - 1);
                }
            }
        }
    }

    /**
     * Uses a insertion sort to sort pizzaManager by it's calories.
     * Postcondition: sorts pizzaManager by each pizza calories.
     */
    private void sortByCalories() {
        //insertion sort
        for (int i = 1; i < this.pizzaManager.size(); i++) {
            for (int k = i; k > 0; k--) {
                if (this.pizzaManager.get(k - 1).
                        getCalories() > this.pizzaManager.
                                get(k).getCalories()) {
                    this.pizzaManager.swap(k, k - 1);
                }
            }
        }
    }

    /**
     * Uses the binary search method to search for a pizza by the specified
     * calorie count and returns the index of the pizza if found and -1 if not
     * found. Postcondition: returns the index of the pizza found with matching
     * calories.
     *
     * @param cals calories
     * @return index
     */
    private int binarySearchByCalories(int cals) {
        int lowIndex = 0;
        int highIndex = pizzaManager.size() - 1;
        int midIndex = (lowIndex + highIndex) / 2;

        while (highIndex - lowIndex > 1) {
            if (this.pizzaManager.get(midIndex).getCalories() < cals) {
                lowIndex = midIndex;
            } else if (this.pizzaManager.get(midIndex).getCalories() > cals) {
                highIndex = midIndex;
            } else if (this.pizzaManager.get(lowIndex).getCalories() == cals) {
                return lowIndex;
            } else if (this.pizzaManager.get(highIndex).getCalories() == cals) {
                return highIndex;
            } else if (this.pizzaManager.get(midIndex).getCalories() == cals) {
                return midIndex;
            }
            midIndex = (lowIndex + highIndex) / 2;
        }
        return -1;
    }

    /**
     * No need to edit functions below this line, unless extending the menu or
     * changing the instructions
     */
    private static final String instructions
            = "-----------------------\nWelcome to PizzaManager\n"
            + "-----------------------\n"
            + "(A)dd a random pizza\n"
            + "Add a (H)undred random pizzas\n"
            + "(E)at a fraction of a pizza\n"
            + "Sort pizzas by (P)rice\n"
            + "Sort pizzas by (S)ize\n"
            + "Sort pizzas by (C)alories\n"
            + "(B)inary Search pizzas by calories\n"
            + "(Q)uit\n";

    private void displayInstructions() {
        System.out.println(instructions);
    }

    /**
     * Notice the one-line main function.
     *
     * @param args
     */
    public static void main(String[] args) {
        new PizzaManager().start();
    }
}
