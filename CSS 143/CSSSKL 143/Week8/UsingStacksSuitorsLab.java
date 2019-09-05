package week9lab;

/* CSSSKL 162
 * 
 * UsingStacksSuitorsLab
 * 
 * This class is mostly a driver for playing with Strings as palindromes, 
 * both iteratively and recursively.  The UsingStacksSuitorsLab class itself is
 * a runnable object, so it can be passed to a thread in our Queue demo
 * 
 * 
 */
public class UsingStacksSuitorsLab implements Runnable {

    private static int threadCount = 0;
    private String name;

    public UsingStacksSuitorsLab() {
        name = "#" + threadCount++ + "Thread";
    }

    public static void main(String[] args) {
        String s1 = "food";		    //not a palindrome
        String s2 = "racecar";      //a palindrome

        System.out.println("String1 is \"" + s1 + "\"");
        System.out.println("String2 is \"" + s2 + "\"");

        System.out.print(s1 + " reversed is: ");
        printReverse(s1);
        System.out.print(s2 + " reversed is: ");
        printReverse(s2);

        recPrintReverse(s1);
        System.out.println();
        recPrintReverse(s2);
        System.out.println();

        System.out.println(s1 + " is a palindrome: " + isPalindrome(s1));
        System.out.println(s2 + " is a palindrome: " + isPalindrome(s2));

        System.out.println(s1 + " is a palindrome(recursively): " + isPalindromeRec(s1));
        System.out.println(s2 + " is a palindrome(recursively): " + isPalindromeRec(s2));

        System.out.println("Did we build a Queue of Threads and start them? " + buildThreadQueue());

        int n = 6;
        System.out.println("For " + n + " suitors, stand in place:" + findPlaceToStand(n));

        n = 10;
        System.out.println("For " + n + " suitors, stand in place:" + findPlaceToStand(n));
    }

    public static void printReverse(String target) {
        Stack<Character> reverseStack = new Stack();
        //adds characters of stings to stack
        for (int i = 0; i < target.length(); i++) {
            reverseStack.push(target.charAt(i));
        }
        //pops in reverse
        while (!reverseStack.isEmpty()) {
            System.out.print(reverseStack.pop().toString());
        }
        System.out.println("");
    }

    public static void recPrintReverse(String target) {
        if (target.length() != 0) {
            System.out.print(target.substring(target.length() - 1));
            recPrintReverse(target.substring(0, target.length() - 1));
        }
    }

    public static boolean isPalindrome(String input) {
        Stack<Character> reverseStack = new Stack();
        String reversedInput = "";

        for (int i = 0; i < input.length(); i++) {
            reverseStack.push(input.charAt(i));
        }
        while (!reverseStack.isEmpty()) {
            reversedInput += reverseStack.pop();
        }
        return reversedInput.equals(input);
    }

    public static boolean isPalindromeRec(String sentence) {
        if (sentence.length() <= 1) {
            return true;
        } else if (sentence.charAt(0) == sentence.charAt(sentence.length() - 1)) {
            return isPalindrome(sentence.substring(1, sentence.length() - 1));
        } else {
            return false;
        }
    }

    public static int findPlaceToStand(int numSuitors) {
        Queue<Integer> suitors = new Queue();

        //adds suitors starting at one
        for (int j = 1; j < numSuitors + 1; j++) {
            suitors.enqueue(j);
        }

        while (suitors.size() != 1) {
            //loops three times untill only one remains
            for (int i = 1; i < 4; i++) {
                int suitor = suitors.dequeue();
                if (i % 3 != 0) {
                    //puts the suitors back if they arn't the third
                    suitors.enqueue(suitor);
                }
            }
        }
        //returns the position
        return suitors.dequeue();
    }

    public static boolean buildThreadQueue() {	//returns true upon success
        Queue<Thread> q = new Queue<>(); //comment this out and use your own Queue

        //when our program starts up, it might create multiple threads to use
        q.enqueue( new Thread(new UsingStacksSuitorsLab()));
        q.enqueue( new Thread(new UsingStacksSuitorsLab()));
        q.enqueue( new Thread(new UsingStacksSuitorsLab()));
        q.enqueue( new Thread(new UsingStacksSuitorsLab()));
        System.out.println("Initial Thread order:");
        q.toString();

        //We need to iterate over our pool of threads and call start() on each one
        //Make a loop that dequeues a thread, calls start on it, and enqueues it again
        for (int i = 0; i < q.size(); i++) {
            Thread current = q.dequeue();
            current.start();
            q.enqueue(current);
        }
        System.out.println("Thread order after start()ing:");
        q.toString();

        return true;  //on successful start
    }

    /*
	 * No need to edit anything below here, 
	 * unless you'd like to change the 
	 * behaviour of each thread in the thread pool above
     */
    @Override
    public void run() {
        for (int i = 0; i < 4; i++) {
            System.out.println(name + ": " + i + "th iteration");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                //do nothing here
            }
        }
    }
}
