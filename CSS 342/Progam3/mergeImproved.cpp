#include <vector>
#include <math.h>
#include <iostream>
using namespace std;

/**
 * Merge function is a helper method for the mergeImprove function. Merges 
 * two sorted sections into one section of sorted elements.
 *  
 * @param firstArray - array of elements 
 * @param secondArray - temporary array 
 * @param first - index of the first element in first section
 * @param mid - index of the last element in first section
 * @param last - index of last element in second section
 */
template <class Comparable>
void merge(vector<Comparable> &firstArray, vector<Comparable> &secondArray,
        int first, int mid, int last) {

    int leftFirst = first; //first index in left section
    int leftLast = mid; //last index in left section
    int rightFirst = mid + 1; //first index in right section
    int rightLast = last; //last index in right section

    int index = leftFirst; //index currently being used

    //chooses the smaller between the left and right sections
    for (; leftFirst <= leftLast && rightFirst <= rightLast; ++index)
        secondArray[index] = (firstArray[leftFirst] < firstArray[rightFirst]) ?
        firstArray[leftFirst++] : firstArray[rightFirst++];

    //exhaust the left array
    for (; leftFirst <= leftLast && index < secondArray.size(); ++leftFirst,
            ++index)
        secondArray[index] = firstArray[leftFirst];
    //exhausts the right array
    for (; rightFirst <= rightLast; ++rightFirst, ++index)
        secondArray[index] = firstArray[rightFirst];
    //writes back to original from provided temporary
    for (index = first; index <= last; ++index)
        firstArray[index] = secondArray[index];
}

/**
 * Sorts an array of comparable elements using the merge sort method. This 
 * method is non-recursive and semi-in-place.
 * 
 * @param firstArray - array of elements.
 */
template <class Comparable>
void mergeImproved(vector<Comparable> &firstArray) {
    int size = firstArray.size(); //size of given array
    vector<Comparable> secondArray(size); //Only temporary array 

    //selectionSize is the size of the un-merged sections. Complexity O(logn).
    for (int sectionSize = 1; sectionSize < size; sectionSize *= 2) {

        /* sectionStart is the first index in the current section. Complexity 
         * O(logn).Use to iterates through the sections so they can be merged */
        for (int sectionStart = 0; sectionStart < size;
                sectionStart += 2 * sectionSize) {

            //define start, middle, and end of sections to be merged
            int first = sectionStart;
            int mid = sectionStart + sectionSize - 1;
            int last;

            /* Statement tests for the case that the remaining number of 
             * elements is less than the sectionSize. Uses the smallest value 
             * for the last index of the to be merge sections*/
            if (sectionStart + 2 * sectionSize - 1 < size - 1) {
                last = sectionStart + 2 * sectionSize - 1;
            } else {
                last = size - 1;
            }

            //merges the sections defined by the index of first, mid, and last.
            merge(firstArray, secondArray, first, mid, last);
        }
    }
}
