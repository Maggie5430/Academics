package lab5b;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
/**
 * CSS 143 B, Winter 2018 Money, Bill, and Date Classes
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class Circle extends Shape {

    int radius;

    public Circle(int x, int y, int r) {
        super(x, y);
        this.radius = r;
    }

    @Override
    public double getArea() {
        return Math.PI * (Math.pow(radius, 2));
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.CYAN);
        g2d.drawOval(getX(), getY(), radius * 2, radius * 2);
    }
    
    public double getRadius(){
        return this.radius;
    }
    
    public void setRadius(int newRadius){
        this.radius = newRadius;
    }
}
