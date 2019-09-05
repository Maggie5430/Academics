package week.pkg8;

/**
 * CSS 143 B, Winter 2018 LinkedLists
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class Stack extends List {

    public static void main(String[] args) {
        Stack empty = new Stack();
        Stack one = new Stack();
        Stack multiple = new Stack();

        one.append(5);
        multiple.append(10);
        multiple.append(20);
        multiple.append(30);

        System.out.println("Empty:" + empty);     // ( note the implicit call to toString()! )
        System.out.println("One:" + one);
        System.out.println("Multiple:" + multiple);

        one.delete(0);
        multiple.delete(1);
        System.out.println("One (upon delete):" + one);
        System.out.println("Multiple (upon delete):" + multiple);

        //one.insert(600, 1);
        multiple.insert(400, 2);
        System.out.println("One (on insert):" + one);
        System.out.println("Multiple(on insert):" + multiple);

        System.out.println("\n--My tests--");
        Stack l1 = new Stack();
        Stack l2 = new Stack();
        l1.push(1);
        l1.push(2);
        l1.push(3);
        l1.push(null);
        l1.push(4);

        l2.push(1);
        l2.push(2);
        l2.push(3);
        l2.push(null);

        System.out.println("l1: " + l1.toString() + "\nl2:" + l2.toString());
        System.out.println("l1 == l2: " + l1.equals(l2));

        //demonstrates that it pops from last added
        System.out.println("\nPop the last element added: " + l1.pop());
        System.out.println("l1: " + l1.toString() + "\nl2:" + l2.toString());
        System.out.println("l1 == l2: " + l1.equals(l2));

        System.out.println("Index of null in l1: " + l1.indexOf(null));

        System.out.println("\nPoping from empty list:");
        try {
            Stack l3 = new Stack();
            l3.pop();
        } catch (LinkedListException e) {
            System.out.println(e);
        }
    }

    /**
     * Returns true is the Stack is empty. Postcondition: returns if Stack is
     * empty.
     *
     * @return true if empty
     */
    @Override
    public boolean isEmpty() {
        if (super.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Inserts the specified Object at the beginning of the Stack. Precondition:
     * The index is a non-negative integer. Postcondition: Adds a new Object to
     * the Stack.
     *
     * @param next Object to be inserted
     */
    public void push(Object next) {
        super.insert(next, 0);
    }

    /**
     * Removes and returns the Object at the beginning of the stack.
     * Precondition: provided index is a non-negative integer. Postcondition:
     * returns the first (last object added) from the stack.
     *
     * @return The first (last object added) from the stack.
     */
    public Object pop() {
        if (this.isEmpty()) {
            throw new LinkedListException("Stack is empty");
        } else {
            return super.remove(0);
        }
    }

    /**
     * Inserts the specified Object at the beginning of the Stack. Precondition:
     * The index is a non-negative integer. Postcondition: Adds a new Object to
     * the beginning of the Stack.
     *
     * @param next Object to be inserted
     * @param index index is ignored
     */
    @Override
    public void insert(Object next, int index) {
        push(next);
    }

    /**
     * Removes and returns the Object at the beginning of the Stack.
     * Precondition: provided index is a non-negative integer. Postcondition:
     * returns the first (last object added) from the stack.
     *
     * @return The first (last object added) from the stack.
     */
    @Override
    public Object remove(int index) {
        return pop();
    }

    /**
     * Inserts the specified Object at the beginning of the Stack. Precondition:
     * The index is a non-negative integer. Postcondition: Adds a new Object to
     * the beginning of the Stack.
     *
     * @param next
     */
    @Override
    public void append(Object next) {
        push(next);
    }

    /**
     * Removes and returns the Object at the beginning of the Stack.
     * Precondition: provided index is a non-negative integer. Postcondition:
     * removes the first (last object added) from the stack.
     *
     * @param index
     */
    @Override
    public void delete(int index) {
        pop();
    }
}
