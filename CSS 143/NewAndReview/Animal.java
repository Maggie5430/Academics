package pkgnew.and.review;

/**
 * Lab1b
 * CSSKL 143B, Winter 2018
 * 1/07/18
 * 
 * Part: Variable Scope in Java: Local and Class-Level.
 * 
 * @author Margaret Connor
 */
public class Animal {

    int weight;//class-wide variable

    public static void main(String[] args) {
        String name = "cat"; //local variable
    }

    public Animal() {
        weight = 12;
        //System.out.println(name); //local variable 
    }

    /* Q: look inside of Rectangle.java and identify at least 4 instance 
     * (class-level) and at least 2 local variables.
     *
     * A: In the rectangle.java class file x, y, x1, y2 were all instance 
     * variables used and retVal, theOther were local variables */
}
