package sorting;

import java.util.ArrayList;

/**
 *
 * @author margaretconnor
 */
public class Sorting {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] l1 = {1, 5, 9, 3, 0, 6, 8, 15, 7, 25, 24};
        int[] l2 = {1, 5, 9, 3, 0, 6, 8, 15, 7, 25, 24};
        int[] l3 = {1, 5, 9, 3, 0, 6, 8, 15, 7, 25, 24};

        insertionSort(l1);
        bubbleSort(l2);
        selectionSort(l3);

        for (int i : l1) {
            System.out.print(i + " ");
        }
        System.out.println("");
        for (int i : l2) {
            System.out.print(i + " ");
        }
        System.out.println("");
        for (int i : l3) {
            System.out.print(i + " ");
        }
        System.out.println("\n" + reverserfact(5));

        ArrayList<Integer> finalArray = new ArrayList<>();
        finalArray.add(0);
        finalArray.add(2);
        finalArray.add(1);
        finalArray.add(4);
        finalArray = newList(finalArray);
        for(int i: finalArray){
            System.out.println(i);
        }
    }

    public static void insertionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j + 1] < arr[j]) {
                    int hold = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = hold;
                }
            }
        }
    }

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int hold = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = hold;
                }
            }
        }
    }

    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            int hold = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = hold;
        }
    }

    public static int reverserfact(int n) {
        int output = 1;
        if (n > 0) {
            output = n * reverserfact(n - 1);
        } else if (n == 0) {
            output = 1;
        } else {
            System.out.println("bad input");
            System.exit(0);
        }
        return output;
    }

    public static ArrayList newList(ArrayList<Integer> arr) {
        ArrayList<Integer> finalArray = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            int sum = 0;
            for (int j = i; j >= 0; j--) {
                sum += arr.get(j);
            }
            finalArray.add(sum * sum);
        }
        return finalArray;
    }
}
