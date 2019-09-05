/**
 * CSS 143 B, Winter 2018 Recursion
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class Stack {

    private Object[] baseStack = new Object[0];
    private int stackSize = 0;

    /**
     * Initializes a new instance of the Stack Class.
     */
    public Stack() {

    }

    public Stack(Object[] input) {
        this.baseStack = input;
        this.stackSize = input.length;
    }

    /**
     * Precondition: None Postcondition: Adds a new object on top of the stack
     *
     * @param object
     */
    public void push(Object object) {
        if (object != null) {
            stackSize++;
            Object[] newStack = new Object[stackSize];

            //copies baseStack to expaned stack and adds new object
            for (int i = 0; i < stackSize - 1; i++) {
                newStack[i] = this.baseStack[i];
            }
            newStack[stackSize - 1] = object;
            this.baseStack = newStack;
        } else {
            System.out.println("No null objects");
        }
    }

    /**
     * Precondition: Stack is not empty Postcondition: Top object returned and
     * removed
     *
     * @return object
     */
    public Object pop() {
        if (this.stackSize > 0) {
            this.stackSize--;
            Object oReturn = this.baseStack[stackSize];
            Object[] newStack = new Object[stackSize];

            //copies baseStack to reduced stack removing the top object
            for (int i = 0; i < stackSize; i++) {
                newStack[i] = this.baseStack[i];
            }
            
            this.baseStack = newStack;
            return oReturn;
        }
        return null;
    }

    /**
     * Precondition: None Postcondition: Return the number of items in the stack
     *
     * @return integer
     */
    public int size() {
        return this.stackSize;
    }

    /**
     * Precondition: None Postcondition: Returns String form of Stack with each
     * object separated by a space
     *
     * @return String
     */
    @Override
    public String toString() {
        String tReturn = "";
        for (int i = this.stackSize - 1; i >= 0; i--) {
            tReturn += this.baseStack[i] + " ";
        }
        return tReturn;
    }

    /**
     * Precondition: None Postcondition: Returns true if there are no objects in
     * the array
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return stackSize == 0;
    }

    /**
     * Precondition: Two Stacks objects exist to be compared Postcondition:
     * Returns true if the Queues are equal
     *
     * @param otherStack
     * @return
     */
    public boolean equals(Stack otherStack) {
        int eql = 0;

        if (this.stackSize == otherStack.stackSize) {
            //counts the number of equal values and return true if equal to size
            for (int i = 0; i < this.stackSize; i++) {
                if (this.baseStack[i].equals(otherStack.baseStack[i])) {
                    eql++;
                }
            }
            if (eql == stackSize) {
                return true;
            }
        }
        return false;
    }
}
