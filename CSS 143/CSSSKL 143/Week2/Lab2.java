package lab2;

/**
 * Lab2 
 * CSSKL 143B, Winter 2018 
 * 1/15/18
 *
 * This class contains my work from Lab 2.
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class Lab2 {

    /**
     * Driver to test other classes 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Test Date class
        System.out.println("Date Test:");
        Date d1 = new Date(01, 12, 2017);
        d1.report();
        d1.setYear(2018);
        d1.report();
        
        //Test Point2D class 
        System.out.println("\nPoint2D Test:");
        Point2D a = new Point2D();
        a.setX(5);
        a.setY(2);
        System.out.println("Point at (" + a.getX() + ", " + a.getY() + ")");
        a.translate(-1, -1);
        System.out.println(
                "Point at (" + a.getX() + ", " + a.getY() + ")");
        a.resetToOrigin();
        System.out.println(
                "Point at (" + a.getX() + ", " + a.getY() + ")");
        Point2D b = new Point2D();
        Point2D c = new Point2D();
        System.out.println(b.toString());
        /*Question: why don’t I need c.toString() here? the c object calls the
         * to string automatically */
        System.out.println(c);
        System.out.println("Are b and c equal:" + b.equals(c));
        
        //Test IntList
        System.out.println("\nIntList Test:");
        IntList iList = new IntList();
        iList.add(95);
        iList.add(100);
        iList.add(58);
        System.out.println(iList.toString());
        System.out.println(iList.sum());
        System.out.println(iList.indexOf(100));
        System.out.println(iList.indexOf(20));
        System.out.println(iList.save());

        //Test Square
        System.out.println("\nSquare Test:");
        Square s1 = new Square(2, 2, 8);
        System.out.println(s1.toString());

        //Test Circle
        System.out.println("\nCircle Test:");
        Circle c1 = new Circle(1, 1, 5);
        c1.draw();

        //Test Object list
        System.out.println("ObjectList Test:");
        ObjectList oList = new ObjectList();
        oList.add(c1);
        oList.add(s1);
        System.out.println(oList.toString());
    }

}
