/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;

/**
 *
 * @author margaretconnor
 */
public class Date {

    private int month;
    private int date;
    private int year;
    
    public Date(int month, int date, int year) {
        if(date > 0 && date < 32 && month > 0 && month < 13 && year > 2013 
                && year < 2025){
            this.date = date;
            this.month = month;
            this.year = year;
        } else {
            System.out.println("Invalid date");
        }
    }
    
    public Date(Date newDate){
        if(newDate.date > 0 && newDate.date < 32 && newDate.month > 0 
                && newDate.month < 13 && newDate.year > 2013 
                && newDate.year < 2025){
            this.date = newDate.date;
            this.month = newDate.month;
            this.year = newDate.year;
        } else {
            System.out.println("Invalid date");
        }
    }
    
    public boolean setDay(int newDate){
        if(newDate > 0 && newDate < 32){
            this.date = newDate;
            return true;
        }
        return false;
    }
    
    public boolean setMonth(int newMonth){
        if(newMonth > 0 && newMonth < 13){
            this.month = newMonth;
            return true;
        }
        return false;
    }
    
    public boolean setYear(int newYear){
         if(newYear > 2013 && newYear < 2025){
            this.year = newYear;
            return true;
        }
        return false;
    }
    
    public int getDay(){
        return this.date;
    }
    
    public int getMonth(){
        return this.month;
    }
    
    public int getYear(){
        return this.year;
    }

    @Override
    public boolean equals(Object otherObject){
        Date comparedTo = (Date)otherObject;
        return (this.date == comparedTo.date && this.month == comparedTo.month 
                && this.year == comparedTo.year);
        
    }
    
    @Override
    public String toString(){
        return (this.month + "/" + this.date + "/" + this.year);
    }
}
