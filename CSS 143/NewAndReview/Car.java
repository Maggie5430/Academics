package pkgnew.and.review;

/**
 * Lab1b
 * CSSKL 143B, Winter 2018
 * 1/07/18
 * 
 * Part: Data in Java: Primitives and Objects
 * 
 * @author Margaret Connor
 */
public class Car {

    int odometer;
    String make, model;

    /**
     * Blank Car object constructor
     */
    public Car() {

    }

    /**
     * Car object Constructor
     *
     * @param newOdometer
     * @param newMake
     * @param newModel
     */
    public Car(int newOdometer, String newMake, String newModel) {
        odometer = newOdometer;
        make = newMake;
        model = newModel;
    }

    /**
     * Car object Constructor
     * 
     * @param make
     */
    public Car(String make) {
        this.make = make;
    }

    /**
     * Car object Constructor
     *
     * @param make
     * @param model
     */
    public Car(String make, String model) {
        this.make = make;
        this.model = model;
    }

    /**
     * This (the Implicit Parameter)
     * Using This parameter
     * 
     */
    public void printThis() {
        System.out.println(this);
        /* The two adresses are the same because the this statement is just 
         * another name for the calling object. The this parameter is a hidden
         * parameter that is pluged in when the method is invoked. There is no
         * this in main because this is refering to the object.
         */
    }

    @Override
    public String toString() {
        return ("Odometer: " + this.odometer + ", Make: " + this.make
                + ", Model: " + this.model);
    }

    @Override
    public boolean equals(Object other) {
        Car theOther = (Car) other;

        if (this.odometer == theOther.odometer && this.make == theOther.make
                && this.model == theOther.model) {
            return true;
        }

        return false;
    }
}
