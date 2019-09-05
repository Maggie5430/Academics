package data.structures;

/**
 * CSS 143 B, Winter 2018 Data Structures: Lists, Stacks, and Queues
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class ArrayList {

    Object[] baseArray = new Object[0]; //"dynamic" array
    int arrayListSize;

    /**
     * Initializes a new instance of the ArrayList Class.
     * 
     */
    public ArrayList() {

    }

    /**
     * Precondition: Index is not more than one than the length
     * Postcondition: Adds a new object at the index
     *
     * @param object
     * @param index
     */
    public void insert(Object object, int index) {
        if(object != null && index <= arrayListSize + 1){
            arrayListSize++;
            Object[] newArray = new Object[arrayListSize];

            /* Copies the baseArray up to the index to the expaneded newArray. 
             * assign the new object to the index,then copies the rest of baseArray
             * to the expanded newArray */
            for (int i = 0; i < index; i++) {
                newArray[i] = this.baseArray[i];
            }
            newArray[index] = object;
            for (int i = index + 1; i < arrayListSize; i++) {
                newArray[i] = this.baseArray[i - 1];
            }

            //makes the new expanded array the baseArray
            this.baseArray = newArray;
        } else {
            if(object == null){
                System.out.println("No null objects");
            } else {
                System.out.println("Index out of bounds");
            }
        }
    }

    /**
     * Precondition: ArrayList is not empty
     * Postcondition: Returns and removes the object at the index
     * 
     * @param index
     * @return
     */
    public Object remove(int index) {
        if(this.arrayListSize>0){
            arrayListSize--;
            Object toReturn = this.baseArray[index];
            Object[] newArray = new Object[arrayListSize];

            // Copies the baseArray excluding the object at index
            for (int i = 0; i < index; i++) {
                newArray[i] = this.baseArray[i];
            }
            for (int i = index; i < arrayListSize; i++) {
                newArray[i] = this.baseArray[i + 1];
            }

            // Makes the new reduced array the baseArray
            this.baseArray = newArray;
            //returns the object at the original index
            return toReturn;
        } 
        return null;
    }

    /**
     * Precondition: None
     * Postcondition: Returns the number of objects in ArrayList
     *
     * @return int
     */
    public int size() {
        return arrayListSize;
    }

    /**
     * Precondition: None
     * Postcondition: Returns String form of ArrayList, each object separated
     *
     * @return String
     */
    @Override
    public String toString() {
        String output = "";
        for (Object s : baseArray) {
            output += s + " ";
        }
        return output;
    }

    /**
     * Precondition: None
     * Postcondition: Returns true if empty
     *
     * @return
     */
    public boolean isEmpty() {
        return arrayListSize == 0;
    }

    /**
     * Precondition: None
     * Postcondition: Return index position of object or -1 if does not exist
     *
     * @param object
     * @return
     */
    public int indexOf(Object object) {
        for (int i = 0; i < arrayListSize; i++) {
            if (this.baseArray[i].equals(object)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Precondition: Two ArrayLists exist to compare
     * Postcondition: Returns true if the ArrayLists are equal
     *
     * @param otherArrayList
     * @return
     */
    public boolean equals(ArrayList otherArrayList) {
        int eql = 0;

        if (this.arrayListSize == otherArrayList.arrayListSize) {
            //counts the number of equal values and return true if equal to size
            for (int i = 0; i < this.arrayListSize; i++) {
                if (this.baseArray[i].equals(otherArrayList.baseArray[i])) {
                    eql++;
                }
            }
            if (eql == arrayListSize) {
                return true;
            }
        }
        return false;
    }

    /**
     * Precondition: Object exists at index
     * Postcondition: Returns object at index
     *
     * @param index
     * @return
     */
    public Object get(int index) {
        return this.baseArray[index];
    }
}
