/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package midterm;

/**
 *
 * @author margaretconnor
 */
public class Queue {
    
    public static void main (String[]args){
        Queue q1 = new Queue();
        Integer i1 = 100;
        q1.enqueue(i1);
        System.out.println(q1.toString());
        
        for (int i = 1; i < 25; i++){
            q1.enqueue(i);
        }
        
        System.out.println(q1.toString());
    }
    
    Object[] baseQueue= new Object[10];
    int count;
    
    public Queue(){
        
    }
    
    public void enqueue(Object newObject){
        if (count >= 10) {
            Object[] newBaseQueue = new Object[count +10];
            for (int i = 0; i < this.baseQueue.length; i++){
                newBaseQueue[i] = this.baseQueue[i];
            }
            this.baseQueue = newBaseQueue;
        }

        baseQueue[count] = newObject;
        count++;
        
    }
    
    @Override
    public String toString(){
        String r = "";
        for(int i = 0; i < count; i++){
            r += this.baseQueue[i] + " ";
        }
        System.out.println("Count = " + this.count);
        return r;
    }
    
}
