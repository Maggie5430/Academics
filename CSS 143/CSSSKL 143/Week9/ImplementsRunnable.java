/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package week9lab;

/**
 *
 * @author margaretconnor
 */
public class ImplementsRunnable implements Runnable {

    public static void main(String[] args) {
        Thread t1 = new Thread(new ImplementsRunnable("Hi"));
        Thread t2 = new Thread(new ImplementsRunnable("Bye"));
        t1.start();
        t2.start();
    }

    private String input;

    public ImplementsRunnable(String input) {
        this.input = input;
    }

    @Override
    public void run() {
        System.out.println("|" + input + "|");
    }
}
