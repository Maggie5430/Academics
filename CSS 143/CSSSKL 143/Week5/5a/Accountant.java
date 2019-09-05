package lab5;


public class Accountant extends SalariedWorker {

	public static final double ACCOUNTANT_MONTHLY = 8000;
	
	
	private Accountant() {//private blocks this object from being created this way
	
	}
	
	//the only way to make an Accountant is to specify the name and social, or with the other constructor below that takes name, social, and pay
	public Accountant(String name, int social){
		super(name, social, ACCOUNTANT_MONTHLY );
	}
	
	public Accountant(String name, int social, double monthlyPay) {
		super(name, social, monthlyPay);
	}
	
}
