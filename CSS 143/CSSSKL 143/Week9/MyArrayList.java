package week9lab;

import java.util.*;
import java.util.Comparator;

/**
 * This class implements multiple sort algorithms to be used with ints, chars,
 * and Stings. Bubble sort, Selection sort, and Insertion sorts are implemented
 * here
 *
 * @author (your name)
 * @version (CSSSKL 162)
 */
public class MyArrayList implements Comparable {

    // instance data

    protected int[] IntList;
    protected char[] CharList;
    protected String[] StringList;

    // constructor will build all 3 arrays here

    public MyArrayList() {
        this.IntList = new int[10];
        this.CharList = new char[10];
        this.StringList = new String[5];

        // fill all 3 arrays with data
        for (int i = 0; i < IntList.length; i++) {
            IntList[i] = (int) (Math.random() * 52);
        }

        // Populate char array
        for (int i = 0; i < CharList.length; i++) {

            Random random = new Random();
            CharList[i] = (char) (random.nextInt(26) + 'a');
        }

        // Populate String array
        StringList[0] = "joe";
        StringList[1] = "mark";
        StringList[2] = "abbey";
        StringList[3] = "tony";
        StringList[4] = "kevin";
    }

    /**
     * Compare to helper/stand alone function
     * @param other
     * @return
     */
    public int compareTo(MyArrayList other) {

        //this means that the first object in the intList array is less than the 
        //first object in the other array
        if (this.IntList[0] < other.IntList[0]) {
            System.out.println("compareTo() is returning -1, array[0] < other[0]");
            return -1;
            //this means that the first object in the intList array is greater 
            //than the first object in the other array
        } else if (this.IntList[0] > other.IntList[0]) {
            System.out.println("compareTo() is returning 1, array[0] > other[0]");
            return 1;
        } else {
            //this means that the first object in the intList array is equal 
            //than the first object in the other array
            System.out.println("compareTo() is returning 0, array[0] != other[0] ");
            return 0;
        }
    }

    /**
     * Bubble sort for ints.
     */
    public void intBubbleSort() {
        for (int i = 1; i <= this.IntList.length; i++) {
            for (int j = 0; j < (this.IntList.length - i); j++) {
                if (this.IntList[j] < this.IntList[j + 1]) {
                    swapInts(IntList, j);
                }
            }
        }
    }

    /**
     * Bubble sort for char.
     */
    public void CharBubbleSort() {
        for (int i = 1; i <= this.CharList.length; i++) {
            for (int j = 0; j < (this.CharList.length - i); j++) {
                if (this.CharList[j] < this.CharList[j + 1]) {
                    swapChars(CharList, j);
                }
            }
        }
    }

    /**
     * Bubble sort for string.
     */
    public void stringBubbleSort() {
        for (int i = 1; i <= this.StringList.length; i++) {
            for (int j = 0; j < (this.StringList.length - i); j++) {
                if (this.StringList[j].compareTo(this.StringList[j + 1]) < 0) {
                    swapStrings(StringList, j);
                }
            }
        }
    }

    /**
     * Swaps the integer at index provided with the integer after it.
     *
     * @param intList
     * @param j
     */
    public void swapInts(int[] intList, int j) {
        int hold = intList[j];
        intList[j] = intList[j + 1];
        intList[j + 1] = hold;
    }

    /**
     * Swaps the char at index provided with the char after it.
     * 
     * @param charList
     * @param j
     */
    public void swapChars(char[] charList, int j) {
        char hold = charList[j];
        charList[j] = charList[j + 1];
        charList[j + 1] = hold;
    }

    /**
     * Swaps the String at index provided with the String after it.
     * 
     * @param stringList
     * @param j
     */
    public void swapStrings(String[] stringList, int j) {
        String hold = stringList[j];
        stringList[j] = stringList[j + 1];
        stringList[j + 1] = hold;
    }

    //selection sort for ints

    /**
     * selection sort for int.
     */
    public void selectionSort() {
        int[] sorted = new int[this.IntList.length];
        for (int i = 0; i < IntList.length; i++) {
            int indexOfMax = 0;       //stores index of smallest pizza cost
            for (int k = 1; k < this.IntList.length - i; k++) {
                if (this.IntList[i] > this.IntList[indexOfMax]) {
                    indexOfMax = k;
                }
            }
            sorted[i] = this.IntList[indexOfMax];
            this.IntList[indexOfMax] = this.IntList[this.IntList.length - i - 1];
        }
        this.IntList = sorted;
        // Implement your sort, call swapSelection(int[] intList, int i, int nextMin) 
    }

    //selection sort for Strings

    /**
     * String selection sort
     */
    public void stringSelectionSort() {
        String[] sorted = new String[this.StringList.length];
        for (int i = 0; i < StringList.length; i++) {
            int indexOfMax = 0;       //stores index of smallest
            for (int k = 1; k < this.StringList.length - i; k++) {
                if (this.StringList[i].compareTo(this.StringList[indexOfMax]) > 0) {
                    indexOfMax = k;
                }
            }
            sorted[i] = this.StringList[indexOfMax];
            this.StringList[indexOfMax] = this.StringList[this.StringList.length - i - 1];
        }
        this.StringList = sorted;
    }

    /**
     * Swaps too items in the array.
     * @param intList
     * @param i
     * @param nextMin
     */
    public void swapSelection(int[] intList, int i, int nextMin) {
        int hold = intList[i];
        intList[i] = intList[nextMin];
        intList[nextMin] = hold;
    }

    /**
     * Swaps too items in the array.
     * @param StringList
     * @param i
     */
    public void swapSelectionStrings(String[] StringList, int i) {
        String hold = StringList[i];
        StringList[i] = StringList[i - 1];
        StringList[i - 1] = hold;
    }

    /** 
     * finds the index of smallest in the array
     * @param arr
     * @param begin
     * @param end
     * @return
     */
    public int findSmallest(int[] arr, int begin, int end) {
        //returns the index of the smallest element
        int minIndex = begin;       //hint
        for (int i = begin; i < end; i++) {
            if (arr[begin - 1] < arr[begin]) {
                minIndex = begin;
            } else {
                minIndex = begin - 1;
            }
        }
        return minIndex;
    }

    //Insertion Sort 

    /**
     * insertionSort.
     */
    public void insertionSort() {

        for (int i = 1; i < this.IntList.length; i++) {
            for (int k = i; k > 0; k--) {
                if (this.IntList[k - 1] < this.IntList[k]) {
                    swapInts(this.IntList, k - 1);
                }
            }
        }
    }

    @Override
    public int compareTo(Object o) {
        if (o == null || !(o instanceof MyArrayList)) {
            throw new RuntimeException("CompareTo Failure");
        } else {
            return compareTo((MyArrayList) o);
        }
    }
}
