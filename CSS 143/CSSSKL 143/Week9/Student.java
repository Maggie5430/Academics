/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package week9lab;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * CSSSKL: 162 Winter 2018, Lab 9.
 * 
 * @author Margaret Connor
 */
public class Student implements Comparable, Cloneable, Serializable {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        //Driver for testing
        ArrayList<Student> studentList = new ArrayList();

        Student s1 = new Student("Milly", 0.4);
        Student s2 = new Student("Milly",0.8);
        Student s3 = new Student("Milly",1.2);
        Student s4 = new Student("Milly",1.6);
        Student s5 = new Student("Milly",2.0);
        Student s6 = new Student("Milly",2.4);
        Student s7 = new Student("Milly",2.8);
        Student s8 = new Student("Milly",3.2);
        Student s9 = new Student("Milly",3.6);
        Student s10 = new Student("Milly",4);

        System.out.println(s1.compareTo(s10));
        System.out.println(s9.compareTo(s2));
        System.out.println(s3.compareTo(s8));
        System.out.println(s7.compareTo(s4));
        System.out.println(s5.compareTo(s6));

        Student s0 = new Student("John", 4 );
        Student s01 = new Student(s0);

        System.out.println("The two are " + s0.compareTo(s01) + "% different");
    }

    private double GPA;
    private String name;

    /**
     * Constructor.
     */
    public Student() {
        this.GPA = 0;
        this.name = "n/a";
    }
    
    /**
     * Constructor.
     * 
     * @param name
     * @param GPA
     */
    public Student (String name,double GPA) {
        this.GPA = GPA;
        this.name = name;
    }

    /**
     * Constructor.
     * 
     * @param otherStudetn
     */
    public Student(Student otherStudetn) {
        this.GPA = otherStudetn.GPA;
        this.name = otherStudetn.name;
    }
    
    /**
     * Returns name.
     * 
     * @return 
     */
    public String getName(){
        return this.name;
    }
    
    /**
     * Returns GPA.
     * 
     * @return 
     */
    public String getGPA(){
        return this.name;
    }
    
    /**
     * Changes GPA.
     * 
     * @return 
     */
    public void setGPA(double newGPA){
        this.GPA = newGPA;
    }
    
     /**
     * Changes Name.
     * 
     * @return 
     */
    public void setName(String newName){
        this.name = newName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Student)) {
            throw new NullPointerException();
        }
        Student otherStudent = (Student) o;
        if (this.GPA == otherStudent.GPA) {
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Object o) {
        if (o == null || !(o instanceof Student)) {
            throw new NullPointerException();
        } else {
            Student otherStudent = (Student) o;
            if (this.equals(otherStudent)) {
                return 0;
            } else {
                return (int) (10 * (this.GPA - otherStudent.GPA));
            }
        }
    }

    @Override
    public Student clone() {
        return new Student(this);
    }
}
