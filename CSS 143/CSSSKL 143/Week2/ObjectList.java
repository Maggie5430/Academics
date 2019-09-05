package lab2;

/**
 * Lab2 
 * CSSKL 143B, Winter 2018 
 * 1/15/18
 *
 * This class contains my work from Lab 2: The ObjectList (or ArrayList (v1.0)).
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class ObjectList {

    private int count = 0;
    private Object[] myShapes = new Object[100];

    /**
     * Adds a new object to the array.
     *
     * @param nObj new Object
     */
    public void add(Object nObj) {
        myShapes[count] = nObj;
        count++;
    }

    @Override
    public String toString() {
        String returnList = "";
        for (int i = 0; i < count; i++) {
            returnList += myShapes[i];
        }
        return returnList;
    }
}
