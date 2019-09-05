package data.structures;

/**
 * CSS 143 B, Winter 2018 Data Structures: Lists, Stacks, and Queues
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class ArrayBasedDataStructuresDriver {

    /**
     * Test Driver
     * @param args
     */
    public static void main(String[] args) {
        stackTests();
        queueTests();
        arrayListTests();
    }

    private static void arrayListTests() {
        System.out.println("--ArrayList--");

        ArrayList a = new ArrayList();

        a.insert('B', 0);
        a.insert('a', 0);
        a.insert('t', 1);

        System.out.println(a.toString());

        while (a.isEmpty() == false) {
            System.out.println(a.remove(0));
        }

        ArrayList b = new ArrayList();

        a.insert(0, 0);
        a.insert(1, 1);
        a.insert(2, 2);
        a.insert(3, 3);
        a.insert(4, 4);

        b.insert(3, 0);
        b.insert(2, 0);
        b.insert(1, 0);
        b.insert(0, 0);
        b.insert(null, 0);

        /* Expected outcome
         * "No null objects"
         * "a: 0 1 2 3 4"
         * "b: 0 1 2 3"
         * "Does a = b: false" */
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        System.out.println("Does a = b: " + a.equals(b));

        /* Expected outcome
         * "a: 0 1 2 3"
         * "b: 0 1 2 3"
         * "Does a = b: true" */
        a.remove(4);
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        System.out.println("Does a = b: " + a.equals(b));

        /* Expected outcome
         * "8 is at -1 in b"
         * "a size: 4"
         * "is a empty: false" */
        System.out.println("8 is at " + b.indexOf(8) + " in b");
        System.out.println("a size: " + a.size());
        System.out.println("is a empty: " + a.isEmpty());
    }

    private static void queueTests() {
        //todo: make more tests here
        System.out.println("--Queue--");
        Queue a = new Queue();

        a.enqueue('B');
        a.enqueue('a');
        a.enqueue('t');

        System.out.println(a.toString());

        while (a.isEmpty() == false) {
            System.out.println(a.dequeue());
        }
        a.enqueue(4);
        a.enqueue(5);
        a.enqueue(6);
        a.enqueue(7);
        a.enqueue(8);

        Queue b = new Queue();
        b.enqueue(4);
        b.enqueue(5);
        b.enqueue(6);
        b.enqueue(7);
        b.enqueue(null);

        /* Expected outcome
        * "No null objects"
         * "a: 4 5 6 7 8"
         * "b: 4 5 6 7"
         * "Does a = b: false*/
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        System.out.println("Does a = b: " + a.equals(b));

        /* Expected outcome
         * "a: 5 6 7 8"
         * "b: 4 5 6 7" 
         * "Does a = b: false*/
        a.dequeue();
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        System.out.println("Does a = b: " + a.equals(b));

        /* Expected outcome
         * "a size: 4"
         * "is a empty: false"
         */
        System.out.println("a size: " + a.size());
        System.out.println("is a empty: " + a.isEmpty());

    }

    private static void stackTests() {
        System.out.println("--Stack--");
        Stack a = new Stack();

        a.push('B');
        a.push('a');
        a.push('t');

        System.out.println(a.toString());

        while (a.isEmpty() == false) {
            System.out.println(a.pop());
        }
        
        a.push(0);
        a.push(1);
        a.push(2);
        a.push(3);

        Stack b = new Stack();
        b.push(0);
        b.push(1);
        b.push(2);
        b.push(3);
        b.push(4);
        b.push(null);

        /*Expected outcome
        * "No null objects"
         * "a: 3 2 1 0"
         * "b: 4 3 2 1 0 
         * "Does a = b: false*/
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        System.out.println("Does a = b: " + a.equals(b));

        /*Expected outcome
         * "a: 3 2 1 0"
         * "b: 3 2 1 0 
         * "Does a = b: true*/
        b.pop();
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        System.out.println("Does a = b: " + a.equals(b));

        /*Expected outcome
         * "a size: 4
         * "is a empty: false*/
        System.out.println("a size: " + a.size());
        System.out.println("is a empty: " + a.isEmpty());

    }

}
