/**
 * CSS 143 B, Winter 2018 Recursion
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class BinarySearch extends SearchAlgorithm {

    /**
     *
     */
    public BinarySearch() {
        super();
    }

    /**
     * Non-recursive binary search to find the index of a word in a String array
     * Postcondition: If found returns index of word, if not found throws it
     * will ItemNotFound exception.
     *
     * @param words
     * @param wordToFind
     * @return
     * @throws ItemNotFoundException
     */
    @Override
    public int search(String[] words, String wordToFind) throws ItemNotFoundException {
        int lowIndex = 0;
        int highIndex = words.length - 1;
        int midIndex = (lowIndex + highIndex) / 2;
        //If there are still values between the min and max index keep searching
        while (midIndex != lowIndex && midIndex != highIndex) {
            if (words[midIndex].compareTo(wordToFind) > 0) {
                //ajusts to the top half of the current indexes 
                highIndex = midIndex;
                midIndex = (lowIndex + highIndex) / 2;
            } else if (words[midIndex].compareTo(wordToFind) == 0) {
                //Returns if found
                return midIndex;
            } else {
                //ajusts to the bottem half of the current indexes 
                lowIndex = midIndex;
                midIndex = (lowIndex + highIndex) / 2;
            }
            this.incrementCount();
        }
        throw new ItemNotFoundException();

    }

    /**
     * Recursive linear search that redirects to a helper method recSearch
     * Postcondition: If found returns index of word, if not found throws it
     * will ItemNotFound exception.
     *
     * @param words
     * @param wordToFind
     * @return
     * @throws ItemNotFoundException
     */
    @Override
    public int recSearch(String[] words, String wordToFind) throws ItemNotFoundException {
        int lowIndex = 0;
        int highIndex = words.length - 1;
        return recSearch(words, wordToFind, highIndex, lowIndex);
    }

    /**
     * Non-recursive binary search to find the index of a word in a String array
     * Postcondition: If found returns index of word, if not found throws it
     * will ItemNotFound exception.
     *
     * @param words
     * @param wordToFind
     * @param highIndex
     * @param lowIndex
     * @return
     * @throws ItemNotFoundException
     */
    private int recSearch(String[] words, String wordToFind, int highIndex, int lowIndex) throws ItemNotFoundException {
        int midIndex = (lowIndex + highIndex) / 2;
        //If there are still values between the min and max index keep searching
        if (midIndex != lowIndex && midIndex != highIndex) {
            this.incrementCount();
            if (words[midIndex].compareTo(wordToFind) > 0) {
                //recusive call to the top half of the current indexes 
                return recSearch(words, wordToFind, midIndex, lowIndex);
            } else if (words[midIndex].compareTo(wordToFind) == 0) {
                //Returns if found
                return midIndex;
            } else {
                //recusive call to the bottom half of the current indexes 
                return recSearch(words, wordToFind, highIndex, midIndex);
            }
        }

        throw new ItemNotFoundException();
    }
}
