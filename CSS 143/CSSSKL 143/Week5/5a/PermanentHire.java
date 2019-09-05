package lab5;

/**
 * CSS 143 B, Winter 2018 Lab5
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class PermanentHire extends SalariedWorker {

    public PermanentHire(String name, int social) {
        super(name, social);
    }

    public PermanentHire(String name, int social, double monthlyPay) {
        super(name, social, monthlyPay);
    }

    @Override
    public double calculateWeeklyPay() {
        System.out.println("Does something differnt");
        return super.calculateWeeklyPay() + 250;
    }
}
