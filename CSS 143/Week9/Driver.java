
/**
 * CSS 143 B, Winter 2018 Classes & Interfaces (MoneyV2)
 *
 * @author Margaret Connor
 */
public class Driver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ExpenseAccount checking = new ExpenseAccount();

        //adds bills
        Bill b1 = new Bill(new Money(205), new Date(4, 24, 2018), "Water");
        Bill b2 = new Bill(new Money(150), new Date(1, 10, 2018), "Gas");
        Bill b3 = new Bill(new Money(200), new Date(4, 24, 2018), "HotWater");
        Bill b8 = new Bill(new Money(200), new Date(4, 23, 2018), "HotWater");
        Bill b4 = new Bill(new Money(450), new Date(5, 3, 2018), "Travel");
        Bill b5 = new Bill(new Money(210), new Date(3, 12, 2018), "Food");
        Bill b6 = new Bill(new Money(450), new Date(5, 2, 2018), "Travel");
        Bill b7 = new Bill(new Money(450), new Date(5, 3, 2018), "Travel");
        checking.insert(b1, 0);
        checking.insert(b2, 0);
        checking.insert(b3, 0);
        checking.insert(b4, 0);
        checking.insert(b5, 0);
        checking.insert(b8, 0);
        checking.insert(b6, 0);
        checking.insert(b7, 0);
        try {
            b6.setPaid(new Date(5, 1, 2018));
            b7.setPaid(new Date(5, 3, 2018));
        } catch (Exception ex) {
            System.out.println("fail");
        }

        //Tests custom Expense acount methods
        System.out.println("!Unsorted checking account bills!");
        System.out.println(checking.toString());
        System.out.println("!Custom Expense Account Method Tests!");
        System.out.println("Next due: " + checking.nextDue());
        System.out.println("-----All due-----\n" + checking.getOutstanding());
        System.out.println("Total Due (" + checking.sizeOfOutstanding()
                + "): " + checking.totalAmount());
        System.out.println("-----All paid-----\n" + checking.getPaid());

        //Checks sorting
        sort(checking);
        System.out.println("!Sorted checking account bills!");
        System.out.println(checking.toString());

        //Test iterator
        System.out.println("Testing iterator");
        for (Bill b : checking) {
            System.out.println(b);
        }
    }

    /**
     * Sort method for testing. Postcondition: Sorts an expense account. Lists
     * in order of: paid (false), next due date, amount, originators name
     * alphabetically, paid (true).
     *
     * @param toSort
     */
    public static void sort(ExpenseAccount toSort) {
        ExpenseAccount temp = new ExpenseAccount();
        for (int i = 1; i < toSort.size(); i++) {
            for (int k = i; k > 0; k--) {
                if (toSort.get(k - 1).compareTo(toSort.get(k)) < 0) {
                    toSort.swap(k, k - 1);
                }
            }
        }
    }

}
