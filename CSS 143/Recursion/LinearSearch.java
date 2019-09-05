/**
 * CSS 143 B, Winter 2018 Recursion
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class LinearSearch extends SearchAlgorithm {

    /**
     * No-argument constructor.
     */
    public LinearSearch() {
        super();
    }

    /**
     * Non-recursive linear search to find the index of a word in a String array
     * Postcondition: If found returns index of word, if not found throws it
     * will ItemNotFound exception.
     *
     *
     * @param words
     * @param wordToFind
     * @return
     * @throws ItemNotFoundException
     */
    @Override
    public int search(String[] words, String wordToFind)
            throws ItemNotFoundException {
        //search till found or end
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(wordToFind)) {
                return i;
            }
            this.incrementCount();
        }
        throw new ItemNotFoundException();
    }

    /**
     * The recSearch causes a stack overflow error because the amount of times
     * the recursive method is called is greater than than the amount of space
     * avaliable in the stack in memory
     *
     * Recursive linear search to find the index of a word in a String array
     * Postcondition: If found returns index of word, if not found throws it
     * will ItemNotFound exception.
     *
     * @param words
     * @param wordToFind
     * @return
     * @throws ItemNotFoundException
     */
    @Override
    public int recSearch(String[] words, String wordToFind) 
            throws ItemNotFoundException {
        //search till end or found
        if (this.getCount() == words.length) {
            if (words[this.getCount()].equals(wordToFind)) {
                return this.getCount();
            }
            this.incrementCount();
            recSearch(words, wordToFind);
        }
        throw new ItemNotFoundException();
    }

}
