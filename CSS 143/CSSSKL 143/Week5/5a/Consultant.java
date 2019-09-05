package lab5;

/**
 * CSS 143 B, Winter 2018 Lab5
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class Consultant extends HourlyWorker{
    public Consultant(String name, int social) {
        super(name, social);
    }

    public Consultant(String name, int social, double hourlyPay) {
        super(name, social, hourlyPay);
    }
    
    @Override
    public double calculateWeeklyPay() {
		return 3  *  20;
	}
}
