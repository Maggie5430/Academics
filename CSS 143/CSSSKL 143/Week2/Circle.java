/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;

/**
 * Lab2 
 * CSSKL 143B, Winter 2018 
 * 1/15/18
 *
 * This class contains my work from Lab 2: the Circle Class.
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class Circle {

    int x;
    int y;
    double radius;

    /**
     * Circle object constructor ().
     */
    public Circle() {

    }

    /**
     * Circle object constructor (X position, Y position).
     *
     * @param x
     * @param y
     */
    public Circle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Circle object constructor (X position, Y position, radius).
     *
     * @param x
     * @param y
     * @param radius
     */
    public Circle(int x, int y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    /**
     * Draws object representation to console.
     */
    public void draw() {
        System.out.println(this.toString());
    }

    /**
     * Returns X position.
     *
     * @return X position
     */
    public int getX() {
        return this.x;
    }

    /**
     * Returns Y position.
     *
     * @return Y position
     */
    public int getY() {
        return this.y;
    }

    /**
     * Returns radius.
     *
     * @return radius
     */
    public double getradius() {
        return this.radius;
    }

    /**
     * Returns area of circle.
     *
     * @return area
     */
    public double getarea() {
        return ((this.radius * Math.PI) * (this.radius * Math.PI));
    }

    /**
     * Sets X position to (new X).
     *
     * @param nX new X
     */
    public void setX(int nX) {
        this.x = nX;
    }

    /**
     * Sets Y position to (new Y).
     *
     * @param nY new Y
     */
    public void setY(int nY) {
        this.y = nY;
    }

    /**
     * Sets radius to (new radius).
     *
     * @param nSL new radius
     */
    public void setradius(double nSL) {
        this.radius = nSL;
    }

    @Override
    public String toString() {
        String sReturn = "";
        //(x,y) top left cornor draws using circles
        for (int k = 0; k < y; k++) {
            sReturn += "\n";
        }
        for (int m = 0; m < radius * 2; m++) {
            for (int i = 0; i < x; i++) {
                sReturn += (" ");
            }
            for (int i = 0; i < radius * 2; i++) {
                sReturn += ("O");
            }
            sReturn += ("\n");
        }
        return sReturn;
    }

    /**
     * Returns true if two Circle objects are equal.
     *
     * @param that
     * @return True if equal
     */
    public boolean equals(Circle that) {
        if (this.x == that.x && this.y == that.y
                && this.radius == that.radius) {
            return true;
        }
        return false;
    }
}
