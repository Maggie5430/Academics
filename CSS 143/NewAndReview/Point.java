package pkgnew.and.review;

/**
 * Lab1b
 * CSSKL 143B, Winter 2018
 * 1/07/18
 * 
 * Part: Access Modifiers: Public and Private.
 * 
 * @author Margaret Connor
 */
public class Point {

    int x;
    int y;
    
    //private variables
    public int x2;
    private int y2;

    /**
     * Constructor for Point object
     * 
     * @param x x position
     * @param y y position
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Public function 
     * 
     * @return X
     */
    public int getX() {
        return this.x;
    }

    /**
     * Private function 
     * 
     * @return Y
     */
    private int getY() {
        return this.y;
    }
}
