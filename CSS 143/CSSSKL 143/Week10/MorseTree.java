package week10lab;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

;

public class MorseTree {

    //TODO: data member called "root" goes here
    private TreeNode root;

    //TODO: Complete constructor
    public MorseTree() {

        //first, open data.txt, add each line to the tree
        Scanner fin;
        try {
            //for each line in the file,
            //  get the letter(char) and the Morse string
            //  call add() with this data
            //  print out the letter and Morse string here for debugging

            fin = new Scanner(new FileInputStream("data.txt")); //needed to remove the spaces that the end of each line in the file

            String letterInfo = fin.nextLine(); //grabbing the first line info
            char letter = letterInfo.charAt(0);//letter will be the first char
            String letterMorse = letterInfo.substring(2); //morse will be everything after the letter and the space
            add(letterMorse, letter); //then we use the add method to add
            while (fin.hasNextLine()) { //now we iterate throught the file and do the same thing for the rest of the lines
                letterInfo = fin.nextLine();
                letter = letterInfo.charAt(0);
                letterMorse = letterInfo.substring(2);
                add(letterMorse, letter);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void add(String morseStr, char letter) {
        root = insertInSubtree(morseStr, letter, root);
    }

    //TODO: recursively complete this function.  It's only a few characters different from findInSubtree()
    private TreeNode<Character> insertInSubtree(String morseStr, char letter, TreeNode subtree) {
        //base case 1 : subtree is null
        //base case 2 : morseStr is of length 0
        //recursive case 1: the first char in morseStr is a '.', so recursively traverse tree
        //recursive case 2: the first char in the morseStr is a '-', so recurse accordingly

        if (subtree == null) {
            return new TreeNode<>(letter, null, null); //returning as a root node
        }

        if (morseStr.length() == 1) {
            subtree.data = letter; //if its a string of 0 length, then we give the letter to the subtree data
        }
        //any string over the length of 0
        while (morseStr.length() > 1) {
            //right if we have '.'
            if (morseStr.charAt(0) == '.') {
                subtree.right = insertInSubtree(morseStr.substring(1), letter, subtree.right);
                return subtree;
            } //left if we have '-'
            else if (morseStr.charAt(0) == '-') {
                subtree.left = insertInSubtree(morseStr.substring(1), letter, subtree.left);
                return subtree;
            }

        }
        return subtree;  //always the last line, always return the node you are working on
    }

    public Character translate(String morseStr) {
        return findInSubtree(morseStr, root);
    }

    //TODO: recursively complete this function.  Very similar to insertInSubtree()
    private Character findInSubtree(String morseStr, TreeNode subtree) {
        //base case 1 : subtree is null
        //base case 2 : morseStr is of length 0
        //recursive case 1: the first char in morseStr is a '.', so recursively traverse tree
        //recursive case 2: the first char in the morseStr is a '-', so re-curse accordingly

        if (subtree == null) { //if the subtree is null and there were no chars or it wasn't found
            System.out.println("No Characters or not Found!");
            return null; //return a null
        } else if (morseStr.length() == 0) { //if the length of the morse string reaches zero, we have found it in the tree, return the char
            return (Character) subtree.data;
        } else if (morseStr.charAt(0) == '.') { //right if its '.'
            return findInSubtree(morseStr.substring(1), subtree.right);
        } else { //left if its '-'
            return findInSubtree(morseStr.substring(1), subtree.left);
        }

    }

    //TODO: Non-recursive function that calls other (recursive) functions
    public String translateString(String tokens) {
        String retVal = "";
        //build a scanner here using tokens as input
        //iterate over the tokens calling translate on each token (substring separated by a space)
        //concat these characters and return them

        Scanner scanner = new Scanner(tokens); //declaring a scanner to go through the string

        while (scanner.hasNext()) { //iterating through the string as long as it has a next token
            retVal += translate(scanner.next()); //adds to the retval
        }

        return retVal; //return once done
    }

    public String toMorseCode(Character c) {
        TreeNode previousNode = null;
        TreeNode currentNode = this.root;
        String morseCode = inorderWalk(this.root);
        //String

        /* while (!currentNode.data.equals(c)) {
            System.out.println(currentNode);
            Character letter = (Character) currentNode.data;
            if (letter.compareTo(c) < 0) {
                morseCode += "-";
                currentNode = currentNode.left;
            } else {
                morseCode += ".";
                currentNode = currentNode.right;
            }

        } */
        return "I failed";
    }

    public String toString() {
        return inorderWalk(this.root);
    }

    private String inorderWalk(TreeNode current) {
        String toString = "";

        if (current.left == null && current.right == null) {
            return current.data.toString() + " ";
        } else {
            if (current.left != null) {
                toString += inorderWalk(current.left);
            }
            toString += current.data + " ";

            if (current.right != null) {
                toString += inorderWalk(current.right);

            }
            //adds current data before right node
            //toString += current.data + " ";
        }
        return toString;
    }

    public static void main(String[] args) {
        MorseTree mt = new MorseTree();  //builds our tree using data from a file

        System.out.println("_____________________");
        System.out.println(mt.translate("..."));  //prints out S
        System.out.println(mt.translate("---"));  //prints out O
        System.out.println(mt.translate(".......-"));  //prints out null
        System.out.println(mt.translate(".-")); //prints out J
        System.out.println(mt.translateString("... --- ..."));  //SOS
        System.out.println(mt.toString());
        System.out.println(mt.toMorseCode('B'));  //find where we are in the tree, remember path to root
    }

    // Inner class to create the linked structure
    private class TreeNode<Character> {

        Object data;     // holds a given node’s data
        TreeNode right;
        TreeNode left;

        public TreeNode() {
            this.data = null;
            this.right = null;
            this.left = null;
        }

        /**
         * Created a overloaded c'tor to handle data and left and right links
         *
         * @param newData
         * @param newLeftLink
         * @param newRightLink
         */
        public TreeNode(char newData, TreeNode newLeftLink, TreeNode newRightLink) {
            data = newData;
            left = newLeftLink;
            right = newRightLink;
        }

        public void setRight(TreeNode rightNode) {
            this.right = rightNode;
        }

        public void setLeft(TreeNode leftNode) {
            this.left = leftNode;
        }

        public Character getData() {
            return (Character) this.data;
        }

    }
}
