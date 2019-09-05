package lab5;

import java.util.ArrayList;
/*
 * Driver
 * Lab 
 * 
 * A simple driver to exercise the Employee hierarchy 
 */

public class EmployeeDriver {
	
	
	public static void main(String[] args) {
		Accountant emp1 = new Accountant( "Rick", 123456789);
		//this next employee makes a bit more than Rick
		Accountant emp2 = new Accountant( "Tim", 55555555,10000);
		//and lets make an hourly worker
		HourlyWorker emp3 = new HourlyWorker( "Jim", 11111111);
		//then a salaried worker
		SalariedWorker emp4 = new SalariedWorker( "Jane", 222222222, 5000);
		HourlyWorker emp5 = new HourlyWorker( "Joe", 11111111,20);
		
		//todo: Build some PermanentHire and Consultant objects here
		//and add them to the ArrayList below
		
		//and build a set of workers, all of which are employees
		ArrayList<Employee> myEmployees = new ArrayList<>();
		
		myEmployees.add(emp1);
		myEmployees.add(emp2);
		myEmployees.add(emp3);
		myEmployees.add(emp4);
		myEmployees.add(emp5);
                myEmployees.add(new PermanentHire("Margaret", 333333, 25000));
                myEmployees.add(new PermanentHire("Scott", 44444));
                
                /* Adding a new ColorWithAlpha(cwa) will not work because CWA 
                 * does not extend employee, therefore it cannot be added to an
                 * array of emplotee objects the same problem happens with
                 * color exception. They could be added if this was an array
                 * of objects rather than employees since they all inherit from
                 * object*/
                //myEmployees.add(new ColorWithAlpha(12,12,12,255));
                //myEmployees.add(new ColorException());
		
                myEmployees.add(new Consultant("Fran", 555555));
                myEmployees.add(new Consultant("Steve", 66666));
		
		//this code doesn't need to change, even if you add 10 new employee classes and 
		//add 102 new employees - this is an example of generic code that can be written 
		//when inheritance hierarchies are in use
		for( int i = 0; i < myEmployees.size(); i++) {
			Employee current = myEmployees.get(i);
			System.out.println( current.getName() + " makes " + current.calculateWeeklyPay() + " per week.");
		}
		
		
	}
}
