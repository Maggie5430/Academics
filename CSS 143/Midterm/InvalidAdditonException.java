package midterm;

/**
 *
 * @author margaretconnor
 */
public class InvalidAdditonException extends Exception{
    
    /* 
     * Creates new instance of the InvalidAdditionException exception.
     */
    public InvalidAdditonException() {
        super("Invalid addition");
    }
    
    
    /* 
     * Creates new instance of the InvalidAdditionException exception.
     */
    public InvalidAdditonException(String newMessege){
        super(newMessege);
    }
}
