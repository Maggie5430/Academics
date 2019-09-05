package lab4;

import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Lab2 
 * CSSKL 143B, Winter 2018 
 * 1/15/18
 *
 * This class contains my work from Lab 2: IntList (A.k.a ArrayList v0.0).
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class IntList {

    private int count = 0;
    private int[] data = new int[100];

    /**
     * Adds a new int to the array.
     *
     * @param nInt new int
     */
    public void add(int nInt) {
        data[count] = nInt;
        count++;
    }

    /**
     * Returns the sum of all array values.
     *
     * @return sum of array
     */
    public int sum() {
        int sum = 0;
        for (int i = 0; i < count; i++) {
            sum += data[i];
        }
        return sum;
    }

    /**
     * Returns the index of the integer.
     *
     * @param find integer to be found
     * @return index of integer
     */
    public int indexOf(int find) {
        for (int i = 0; i < count; i++) {
            if (data[i] == find) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns true if writes all of the elements in the list to disk.
     *
     * @return True if saved correctly
     */
    public boolean save() {
        try {
            PrintWriter save = new PrintWriter(new File("savedata.txt"));
            for (int i = 0; i < count; i++) {
                save.print(data[i]);
                if (i < count - 1) {
                    save.print(", ");
                }
            }
            save.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return false;
        }
    }

    @Override
    public String toString() {
        String returnList = "";
        for (int i = 0; i < count; i++) {
            returnList += data[i];
            if (i < count - 1) {
                returnList += ", ";
            }
        }
        return returnList;
    }
}
