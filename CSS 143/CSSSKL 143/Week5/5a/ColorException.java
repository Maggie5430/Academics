package lab5;

/**
 * CSS 143 B, Winter 2018 Lab5
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class ColorException extends RuntimeException {

    ColorException() {
        super("An error occured in color");
    }

    ColorException(String msg) {
        super(msg);
    }
    
    
}
