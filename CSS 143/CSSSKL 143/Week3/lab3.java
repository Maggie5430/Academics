package lab3;

import java.awt.Color;
import java.awt.Dimension;

/*
 * Lab2 Driver:   ClassDesignIIDriver.java
 * Author: Rob Nash, (moneyDriver by JoeMC)
 * 
 * Purpose: This is a test file to be included in the same (working) directory as your 
 * new class definitions.  Once you define a class (say, the Date class), uncomment the driver code
 * associated with that class to test it.  Match your output with the provided output, and pay 
 * particular attention to each new technique as we make use of it (Overloading, Constructors, Copy 
 * Constructors, Access Modifiers, Class Invariants, etc.)
 * 
 */
public class lab3 {

    private int foo = -1;  //used in one of the mutator functions below

    public static void main(String[] args) {
        //uncomment the desired Driver or Demo below and run this code to test your progress
        dateDriver();
        lineSegmentDriver();
        immutableFractionDriver();
        shapeDriver();
        charListDriver();
        math2Driver();
        passByReferenceDemo();
        //moneyDriver();
    }

    public static void dateDriver() {
        Date a = new Date();
        Date b = new Date(1, 2, 2030);
        Date c = new Date(b);

        System.out.println("a is :" + a.toString());
        System.out.println("b is :" + b.toString());
        System.out.println("c is :" + c.toString());

        a.setDate(5, 5, 2010);

        System.out.println("B and A are equal : " + b.equals(a));
        System.out.println("B and C are equal : " + b.equals(c));
    }

    public static void lineSegmentDriver() {
        LineSegment a = new LineSegment();
        LineSegment b = new LineSegment(new Point2D(1, 1), new Point2D(2, 2));
        LineSegment c = new LineSegment(b);

        a.setStartPoint(new Point2D(3, 3));
        a.setEndPoint(new Point2D(4, 4));

        System.out.println("Line a: " + a.toString());
        System.out.println("Line b: " + b.toString());
        System.out.println("Line c: " + c.toString());

        System.out.println("Line b's distance between points: " + b.distance());
        System.out.println("Using distance formula ^^");

        System.out.println("Does a equal b? " + a.equals(b));
        System.out.println("Does a equal c? " + a.equals(c));
        System.out.println("Does b equal c? " + b.equals(c));
    }

    public static void immutableFractionDriver() {
        Fraction a = new Fraction(1, 2);
        Fraction b = new Fraction(3, 4);
        Fraction c = new Fraction(b);

        System.out.println("a:" + a.toString());
        System.out.println("b:" + b.toString());
        System.out.println("c:" + c.toString());

        //(1)uncomment out to discover errors
        //a.setNum(3);
        //a.setDenom(5);
        //(2)fix the following 2 errors
        //System.out.println("a.num:"+a.getNum());
        //System.out.println("b.denom:"+b.getDenom());
        //(3)which of the following code is correct to change the fraction a?
        //a.add(b);
        a = a.add(b); //this one

        System.out.println("a:" + a.toString());
        System.out.println("Simplified ^^");
        System.out.println("b:" + b.toString());
        System.out.println("c:" + c.toString());

        System.out.println("a.equals(b):" + a.equals(b));
        System.out.println("b.equals(c):" + b.equals(c));

    }

    private static void shapeDriver() {
        Shape a = new Shape();
        Shape b = new Shape(10, 10, Color.DARK_GRAY);
        Shape c = new Shape(b);

        a.setX(120);
        b.setY(140);

        System.out.println("a: " + a.toString());
        System.out.println("b: " + b.toString());
        System.out.println("c: " + c.toString());
    }

    private static void math2Driver() {
        double a = Math2.PI;
        double b = Math2.E;
        double c = a + b;

        System.out.println("The larger of the two is " + Math2.max(10, 20));
        System.out.println("And the larger of the two is "
                + Math2.max(10.34, 10.31));
        System.out.println("2^8 is " + Math2.pow(2, 8));

    }

    private static void charListDriver() {
        CharList a = new CharList();
        CharList b = new CharList("Batman");
        CharList c = new CharList(b);

        a.add('k');
        a.add('a');
        a.add('t');
        a.add('n');
        a.add('i');
        a.add('s');

        System.out.println("a is :" + a.toString() + " and has " + a.size()
                + " chars");
        System.out.println("b is :" + b.toString() + " and has " + b.size()
                + " chars");
        System.out.println("c is :" + c.toString() + " and has " + c.size()
                + " chars");

        System.out.println("B and A are equal : " + b.equals(a));
        System.out.println("B and C are equal : " + b.equals(c));
    }

    public static void passByReferenceDemo() {
        int a = 3; 						//a primitive (pass-by-value)
        Dimension b = new Dimension();  //an Object (pass-by-reference)

        System.out.println("a is :" + a);
        System.out.println("b is :" + b);

        mutate(a);
        mutate(b);

        System.out.println("a is :" + a);
        System.out.println("b is :" + b);

    }

    public static void mutate(int z) {  //does it matter that I called my input data "z" here?
        z = -1000;  //if z is only a copy, this won't affect the variable a's value in main

        //this.foo = -1000;
        //why does the line above cause compiler errors?
    }

    public static void mutate(Dimension c) {
        c.height = -1000;
        c.width = -1000;
        //if I didn't hand this function a copy of b from main, but rather a reference to the object that is known in main as b, what happens here?
        //if I make a change to c, are my changes reflected back in the code that called this function?  In other words, did this change b of main?
    }

    public static void moneyDriver() {
        //uncomment below to get started on Money, Bill, etc.
        /*Money m0 = new Money();
			System.out.printf("m0 = new Money():  %s (should be Money$0.00)\n", m0);
			m0.add(1);
			System.out.printf("m0.add(1):    m0 = %s (should be Money$1.00)\n", m0);
			m0.add(0.1);
			System.out.printf("m0.add(0.1):  m0 = %s (should be Money$1.10)\n", m0);
			m0.add(0.01);
			System.out.printf("m0.add(0.01): m0 = %s (should be Money$1.11)\n", m0);
			System.out.println();

			m0.subtract(0.01);
			System.out.printf("m0.subtract(0.01): m0 = %s (should be Money$1.10)\n", m0);
			m0.subtract(0.1);
			System.out.printf("m0.subtract(0.1):  m0 = %s (should be Money$1.00)\n", m0);
			m0.subtract(1);
			System.out.printf("m0.subtract(1):    m0 = %s (should be Money$0.00)\n", m0);
			System.out.println();

			m0.add(0, 100);
			System.out.printf("m0.add(0, 100): m0 = %s (should be Money$1.00)\n", m0);
			m0.add(0, 200);
			System.out.printf("m0.add(0, 200): m0 = %s (should be Money$3.00)\n", m0);
			m0.add(0, 50);
			System.out.printf("m0.add(0, 50):  m0 = %s (should be Money$3.50)\n", m0);
			m0.add(0.50);
			System.out.printf("m0.add(0.50):   m0 = %s (should be Money$4.00)\n", m0);
			m0.add(0, 50);
			System.out.printf("m0.add(0, 50):  m0 = %s (should be Money$4.50)\n", m0);
			m0.add(0, 150);
			System.out.printf("m0.add(0, 150): m0 = %s (should be Money$6.00)\n", m0);
			System.out.println();

			m0.subtract(0, 100);
			System.out.printf("m0.subtract(0, 100): m0 = %s (should be Money$5.00)\n", m0);
			m0.subtract(0, 200);
			System.out.printf("m0.subtract(0, 200): m0 = %s (should be Money$3.00)\n", m0);
			m0.subtract(0, 50);
			System.out.printf("m0.subtract(0, 50):  m0 = %s (should be Money$2.50)\n", m0);
			m0.subtract(0.50);
			System.out.printf("m0.subtract(0.50):   m0 = %s (should be Money$2.00)\n", m0);
			m0.subtract(0, 150);
			System.out.printf("m0.subtract(0, 150): m0 = %s (should be Money$0.50)\n", m0);
			m0.subtract(0, 50);
			System.out.printf("m0.subtract(0, 50):  m0 = %s (should be Money$0.00)\n", m0);
			System.out.println();

			Money m1 = new Money();
			Money m2 = new Money(12, 34);
			Money m3 = new Money(12.34);
			Money m4 = new Money(m3);
			
			System.out.printf("m1 = new Money():       %s (should be Money$0.00)\n", m1);
			System.out.printf("m2 = new Money(12, 34): %s (should be Money$12.34)\n", m2);
			System.out.printf("m3 = new Money(12.34):  %s (should be Money$12.34)\n", m3);
			System.out.printf("m4 = new Money(m3):     %s (should be Money$12.34)\n", m4);
			System.out.println();
			
			m1.add(12.34);
			m2.add(12, 34);
			m3.add(m1);
			m4.add(m3);
			
			System.out.printf("m1.add(12.34):  m1 = %s (should be Money$12.34)\n", m1);
			System.out.printf("m2.add(12, 34): m2 = %s (should be Money$24.68)\n", m2);
			System.out.printf("m3.add(m1):     m3 = %s (should be Money$24.68)\n", m3);
			System.out.printf("m4.add(m3):     m4 = %s (should be Money$37.02)\n", m4);
			System.out.println();
			
			m1.subtract(12.34);
			m2.subtract(12, 34);
			m3.subtract(m1);
			m4.subtract(m3);
			
			System.out.printf("m1.subtract(12.34):  m1 = %s (should be Money$0.00)\n", m1);
			System.out.printf("m2.subtract(12, 34): m2 = %s (should be Money$12.34)\n", m2);
			System.out.printf("m3.subtract(m1):     m3 = %s (should be Money$24.68)\n", m3);
			System.out.printf("m4.subtract(m3):     m4 = %s (should be Money$12.34)\n", m4);
			System.out.println();
			
			Money m5;
			try {
				m5 = new Money(0, -1);
				System.out.printf("Failed in disallowing new Money(0, -1): %s\n", m5);
			} catch (IllegalStateException e) {
				System.out.printf("Success in disallowing new Money(0, -1)\n");
			}
			try {
				m5 = new Money(-1, 0);
				System.out.printf("Failed in disallowing new Money(-1, 0): %s\n", m5);
			} catch (IllegalStateException e) {
				System.out.printf("Success in disallowing new Money(-1, 0)\n");
			}
			try {
				m5 = new Money(0, -100);
				System.out.printf("Failed in disallowing new Money(0, -100): %s\n", m5);
			} catch (IllegalStateException e) {
				System.out.printf("Success in disallowing new Money(0, -100)\n");
			}
			try {
				m5 = new Money(-1.00);
				System.out.printf("Failed in disallowing new Money(-1.00): %s\n", m5);
			} catch (IllegalStateException e) {
				System.out.printf("Success in disallowing new Money(-1.00)\n");
			}
			System.out.println();
			
			m5 = new Money();
			System.out.printf("m5 = new Money(): %s\n", m5);
			System.out.println();
			try {
				m5.subtract(0.01);
				System.out.printf("Failed in disallowing %s.subtract(0.01)\n", m5);
			} catch (IllegalStateException e) {
				System.out.printf("Success in disallowing %s.subtract(0.01)\n", m5);
			}
			m5 = new Money();
			try {
				m5.subtract(0, 1);
				System.out.printf("Failed in disallowing %s.subtract(0, 1)\n", m5);
			} catch (IllegalStateException e) {
				System.out.printf("Success in disallowing %s.subtract(0, 1)\n", m5);
			}
			m5 = new Money();
			try {
				m5.subtract(1, 0);
				System.out.printf("Failed in disallowing %s.subtract(1, 0)\n", m5);
			} catch (IllegalStateException e) {
				System.out.printf("Success in disallowing %s.subtract(1, 0)", m5);
			}
		}*/
    }
}

/* Q: What is the primary difference between passing a primitive to a method 
 *    versus passing an object?
 * A: When passing an object to a method a reference point to the object is 
 *    passed while a copy of the primitive is passed. Meaning the original 
 *    object is still mutatable while the original primative does not change.
 * Q: When a primitive is passed to a method, does the scope of that primitive 
 *    change (i.e., grow to include the called method)?
 * A: No it does not
 * Q: What about when an object is passed to a method?
 * A: Yes it does the object is now included/used in that method so it expands
 * Q: How then would you describe the scope of an object that has been passed to
 *    a method?
 * A: The scope is the number of places that object exists/is being used, the
 *    scope increases because the original object in memory is being used in new
 *    location.
 */
