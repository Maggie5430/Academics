/**
 * CSS 143 B, Winter 2018 Recursion
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class IllegalArgumentException extends Exception {

    /**
     * No-argument constructor for IllegalArgumentException.
     */
    public IllegalArgumentException(){
        super("Unvalid Directory");
    }
    
    /**
     * Custom message constructor for IllegalArguementException.
     * 
     * @param message
     */
    public IllegalArgumentException(String message){
        super(message);
    }
}
