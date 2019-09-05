package pkgnew.and.review;

/**
 * Lab1b
 * CSSKL 143B, Winter 2018
 * 1/07/18
 * 
 * Part: Access Modifiers: Public and Private.
 * 
 * @author Margaret Connor
 */
public class Driver {

    /**
     * try to.
     * call a method declared as private. What message does Java print out? 
     * Next, declare some class-level data item as public (an int, say), and 
     * declare another class-level data item as private. In your driver’s
     * “main”, try again to access these two data items – what message does 
     * the Java compiler display now?
     * 
     * @param args
     */
    public static void main(String[] args) {
        Point p1 = new Point(4, 7);
        System.out.println(p1.getX());
        //System.out.println(p1.getY());     
        /* Q: What messege does Java try to print out?
         * A: getY() has private access in Point */

        //System.out.println(x2);
        //System.out.println(y2);
        /* Q: what message does the Java compiler display now?
         * A: cannot find symbol, because a claswide variable is only accessable
         * in the class it was declared*/
    }
}
