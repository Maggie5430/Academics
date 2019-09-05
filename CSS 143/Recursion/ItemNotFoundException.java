/**
 * CSS 143 B, Winter 2018 Recursion
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class ItemNotFoundException extends Exception{

    /**
     * No-argument constructor for ItemNotFoundException.
     */
    public ItemNotFoundException(){
        super("Item Not Found");
    }
    
    /**
     * Custom message constructor for IllegalArguementException.
     * 
     * @param message
     */
    public ItemNotFoundException(String message){
        super(message);
    }
}
