package pkgnew.and.review;

/**
 * Lab1b
 * CSSKL 143B, Winter 2018
 * 1/07/18
 * 
 * Multi-use main class
 * 
 * @author Margaret Connor
 */
public class NewAndReview {

    public static void main(String[] args) {
        /* This was used in This (the Implicit Parameter) before I made a custom
         * .toString function. They both printed the same adress and my thinking
         * and awnswers are writen in the Car Class under .printThis function*/
        Car c1 = new Car(0, "Subaru", "Impreza");
        System.out.println(c1.toString());
        c1.printThis();
        
        //Overloading Methods
        Car c2 = new Car("subaru");
        Car c3 = new Car("Subaru","Impreza");
    }
    
}
