
/**
 * CSS 143 B: Winter 2018 Pizza Simulator
 *
 * @author Margaret Connor
 * @version 1.0
 * @param <E>
 */
public class ArrayList<E> {

    private Object[] baseArray;
    private int arrayListSize;

    /**
     * Initializes a new instance of the ArrayList Class. Postcondition: Creates
     * a new and empty instance of Array lists.
     */
    public ArrayList() {
        baseArray = new Object[0];
        arrayListSize = 0;
    }

    /**
     * Inserts a new Object at the index provided. Precondition: Index is not
     * more than the size of the array. Postcondition: Adds a new object at the
     * index.
     *
     * @param object New Object
     * @param index Index
     */
    public void insert(E object, int index) {
        if (index > arrayListSize || index < 0) {
            throw new IndexOutOfBoundsException();
        } else {
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
        }
    }

    /**
     * Removes and returns the item at the provided index. Precondition:
     * ArrayList is not empty. Postcondition: Returns and removes the object at
     * the index.
     *
     * @param index Index
     * @return Object at index
     */
    public E remove(int index) {
        if (this.arrayListSize < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            arrayListSize--;
            E toReturn = (E) this.baseArray[index];
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
    }

    /**
     * Returns the number on items in the array list. Postcondition: Returns the
     * number of objects in ArrayList.
     *
     * @return the size of the array.
     */
    public int size() {
        return arrayListSize;
    }

    /**
     * Returns a string representation of the ArrayList. Postcondition: Returns
     * String form of ArrayList, each object separated.
     *
     * @return String
     */
    @Override
    public String toString() {
        String output = "";
        for (Object s : baseArray) {
            output += s + "";
        }
        return output;
    }

    /**
     * Returns true if the array list is empty. Postcondition: Returns true if
     * empty.
     *
     * @return
     */
    public boolean isEmpty() {
        return arrayListSize == 0;
    }

    /**
     * Returns the index position of an object. Postcondition: Return index
     * position of object or -1 if does not exist.
     *
     * @param object
     * @return
     */
    public int indexOf(E object) {
        for (int i = 0; i < arrayListSize; i++) {
            if (this.baseArray[i].equals(object)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Indicates whether some other ArrayList is "equal to" this one.
     * Postcondition: returns true if two ArrayList are equal to each other.
     *
     * @param other returns true if two ArrayList are equal to each other
     * @return
     */
    @Override
    public boolean equals(Object other) {
        //compares types (use instanceof becuase of how queue and stack inherit
        //from list)
        if (other == null || !(other instanceof ArrayList)) {
            return false;
        }

        ArrayList otherList = (ArrayList) other;

        if (this.size() == otherList.size()) {
            for (int i = 0; i < this.size(); i++) {
                if (this.get(i) == null || otherList.get(i) == null) {
                    if (this.get(i) != otherList.get(i)) {
                        return false;
                    }
                } else if (!this.get(i).equals(otherList.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Returns the item at the provided index. Precondition: Object exists at
     * index. Postcondition: Returns object at index.
     *
     * @param index
     * @return
     */
    public E get(int index) {
        if (index > this.size() - 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            return (E) this.baseArray[index];
        }
    }

    /**
     * Swaps the objects at the two provided indexes. Precondition: Indexes are
     * greater than 0 and less than size -1.
     *
     * @param idx1 Index of first item
     * @param idx2 Index of second item
     */
    public void swap(int idx1, int idx2) {
        if (idx1 > this.size() - 1 || idx1 < 0
                || idx2 > this.size() - 1 || idx2 < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            Object item1 = baseArray[idx1];
            baseArray[idx1] = baseArray[idx2];
            baseArray[idx2] = item1;
        }
    }
}
