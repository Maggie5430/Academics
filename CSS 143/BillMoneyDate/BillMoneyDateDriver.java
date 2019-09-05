package money.bill.and.date.classes;

/**
 * HW3.java: Simple driver to test Money, Date, and Bill classes
 *
 * @author Rob Nash, borrowed from cfolsen
 */
public class BillMoneyDateDriver {

    /**
     * main driver function pre: none post: exercises the methods in Bill,
     * Money, and Date (not done)
     */
    public static void main(String[] args) {
        //Money Tests
        System.out.println("-Money Testing area-");
        //constructors
        Money testMoney1 = new Money(5);
        Money testMoney2 = new Money(2, 50);
        Money testMoney3 = new Money(testMoney2);
        //toString
        System.out.println("testMoney1: " + testMoney1);
        System.out.println("testMoney2: " + testMoney2);
        System.out.println("testMoney3: " + testMoney3);
        //others
        System.out.println("Are testMoney1 & testMoney3 equal? " 
                + testMoney1.equals(testMoney3));
        System.out.println("Are testMoney2 & testMoney3 equal? " 
                + testMoney2.equals(testMoney3));
        System.out.println("testMoney2 amount: " + testMoney2.getMoney());
        testMoney2.setDollars(100);
        testMoney2.setCents(0);
        testMoney3.add(new Money(1, 75));
        System.out.println("testMoney 2: " + testMoney2);
        System.out.println("testMoney 3: " + testMoney3);
        //invalid setters
        testMoney1.setMoney(-5, -1);
        testMoney1.add(-6);

        //Date Tests
        System.out.println("\n-Date Testing area-");
        Date testDate1 = new Date(12, 31, 2024);
        Date testDate2 = new Date(3, 16, 2012);
        //broken date setters
        testDate1.setDay(0);
        testDate1.setMonth(0);
        testDate1.setYear(2013);
        //working date setters
        testDate1.setDay(1);
        testDate1.setMonth(1);
        testDate1.setYear(2014);
        //date getters
        System.out.println(testDate1.getYear() + "/" + testDate1.getMonth()
                + "/" + testDate1.getDay());
        //date equals
        testDate2 = new Date(testDate1);
        System.out.println("Are testDate1 & testDate2 equal? "
                + testDate1.equals(testDate2));
        //date to string
        System.out.println("testDate1: " + testDate1);
        System.out.println("testDate2: " + testDate1);

        //Bill Tests
        System.out.println("\n-Bill Testing area-");
        //constructors
        Bill testBill1 = new Bill(new Money(400), new Date(6, 15, 2016),
                 "Utilities");
        Bill testBill2 = new Bill(testBill1);
        //setters
        testBill1.setAmount(new Money(450));
        testBill1.setDueDate(new Date(7, 15, 2016));
        testBill1.setOriginator("Utilities (late)");
        try{
        testBill1.setPaid(new Date(6, 24, 2016));
        testBill2.setPaid(new Date(6, 18, 2016));
        } catch (Exception e){
            System.out.println(e);
        }
        //getters
        System.out.println(testBill1.getAmount() + ", "
                + testBill1.getDueDate()
                + ", " + testBill1.getOriginator() + ". Done: "
                + testBill1.isPaid());
        System.out.println(testBill2.getAmount() + ", " + testBill2.getDueDate()
                + ", " + testBill2.getOriginator() + ". Done: "
                + testBill2.isPaid());
        //toString
        System.out.println(testBill1);
        System.out.println(testBill2);
        testBill2 = new Bill(testBill1);
        System.out.println("Are testBill1 & 2 equal?: "
                + testBill2.equals(testBill1));

        //Construct some money
        System.out.println("\n-Default Driver-");
        Money money1 = new Money(10);
        Money money2 = new Money(money1);
        money1.setMoney(30,50);
        //TODO: do more functional exercises with the money class
	    
	    
        System.out.println("Money objects output:");
        System.out.println(money1);
        System.out.println(money2);
	
	
        //Construct some bills
        Money amount = new Money(20);
        Date dueDate = new Date(4, 30, 2016);
        Bill bill1 = new Bill(amount, dueDate, "The phone company");
       
        Bill bill2 = new Bill(bill1);
        bill2.setDueDate(new Date(5, 30, 2012));
        amount.setMoney(31, 99);
        dueDate.setDay(29);
        Bill bill3 = new Bill(amount, dueDate, "The record company");
        
        System.out.println("Bill objects output:");
        System.out.println(bill1);
        System.out.println(bill2);
        System.out.println(bill3);
        
    }
}
