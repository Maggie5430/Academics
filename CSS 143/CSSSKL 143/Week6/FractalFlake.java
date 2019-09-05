package lab6;

import java.awt.Graphics;

/**
 * CSSSKL 143 B, Winter 2018 Lab 6.
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class FractalFlake extends Shape {

    final private int LIMIT;
    final private int BRANCHES;
    private int length;

    /**
     * Initializes an instance of the FractalFlake class
     * @param newX
     * @param newY
     * @param newlength
     * @param newBranches
     * @param newLimit
     */
    public FractalFlake(int newX, int newY, int newlength, int newBranches, int newLimit) {
        super(newX, newY);
        this.LIMIT = newLimit;
        this.length = newlength;
        this.BRANCHES = newBranches;
    }

    /**
     * a redirect or facade
     * @param g
     */
    @Override
    public void draw(Graphics g) { 
        draw(g, getX(), getY(), LIMIT);
    }

    /**
     * Draws to graphic 
     * @param g
     * @param x
     * @param y
     * @param limit
     */
    public void draw(Graphics g, int x, int y, int limit) {
        if (limit >= 3) { //base case is depth <3
            for (int i = 0; i < this.BRANCHES; i++) {
                int x2 = x + (int) (length
                        * Math.cos((2 * Math.PI / this.BRANCHES) * i));
                int y2 = y - (int) (length
                        * Math.sin((2 * Math.PI / this.BRANCHES) * i));
                g.drawLine(x, y, x2, y2); //do a branch
                draw(g, x2, y2, limit / 3); //recursive call
            }
        }
    }
}
