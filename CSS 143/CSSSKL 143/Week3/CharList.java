package lab3;

/**
 * Lab3 CSSKL 143B, Winter 2018 1/25/18
 *
 * This class contains my work from Lab 3: The CharList
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class CharList {

    private char[] charArray = new char[100];
    private int charListSize = 0;

    /**
     * Initializes a new instance of the CharList class.
     */
    public CharList() {

    }

    /**
     * Initializes a new instance of the Date class.
     * @param startStr
     */
    public CharList(String startStr) {
        this.charArray = startStr.toCharArray();
        this.charListSize = startStr.length();
    }

    /**
     * Initializes a new instance of the Date class.
     * @param other
     */
    public CharList(CharList other) {
        this.charListSize = other.charListSize;
        for (int i = 0; i < other.charListSize; i++) {
            //if(other.get(i)!=null){
            this.charArray[i] = other.get(i);
            //}
        }
    }

    /**
     * Precondition: None
     * Postcondition: Adds a new char to CharList
     * 
     * @param next
     */
    public void add(char next) {
        this.charArray[this.charListSize] = next;
        this.charListSize++;
    }

    /**
     * Precondition: None
     * Postcondition: Returns the character at the index
     * 
     * @param index
     * @return
     */
    public char get(int index) {
        return this.charArray[index];
    }

    /**
     * Precondition: None
     * Postcondition: Returns the number of chars in CharList
     *
     * @return
     */
    public int size() {
        return this.charListSize;
    }

    /**
     * Precondition: None 
     * Postcondition: Returns String of CharList
     * 
     * @return String
     */
    @Override
    public String toString() {
        String toReturn = "";
        for (int i = 0; i < this.charListSize; i++) {
            toReturn += this.charArray[i];
        }
        return toReturn;
    }

    /**
     * Precondition: None 
     * Postcondition: Returns true if to CharLists are equal
     * 
     * @return boolean
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof CharList)) {
            return false;
        }
        return ((this.toString()).equals(other.toString()));
    }

}
