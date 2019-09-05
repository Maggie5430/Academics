#include <iostream>
#include <vector>
#include <sys/time.h>
#include <stdlib.h>
#include <fstream>
#include "quicksort.cpp"
#include "mergesort.cpp"
#include "mergeImproved.cpp"

// array initialization with random numbers
void initArray( vector<int> &array, int randMax ) {
  int size = array.size( );

  for ( int i = 0; i < size; ) {
    int tmp = ( randMax == -1 ) ? rand( ) : rand( ) % randMax;
    bool hit = false;
    for ( int j = 0; j < i; j++ ) {
      if ( array[j] == tmp ) {
        hit = true;
        break;
      }
    }
    if ( hit )
      continue;
    array[i] = tmp;
    i++;
  }
}

// array printing
void printArray( vector<int> &array, char arrayName[] ) {
  int size = array.size( );

  for ( int i = 0; i < size; i++ )
    cout << arrayName << "[" << i << "] = " << array[i] << endl;
}

//returns the elapsed time in (ms)
int elapsed( timeval &startTime, timeval &endTime ) {
  return ( endTime.tv_sec - startTime.tv_sec ) * 1000000 
    + ( endTime.tv_usec - startTime.tv_usec );
}


int main( int argc, char* argv[] ) {
  ofstream outfile;
  outfile.open("compare.txt"); //Create an output file with "compare.txt" filename.
  outfile<<"size \t quick \t merge \t mergeImproved \n";
  int size = 20;
  int rnd = 1;

  while (size <= 1000) {
      srand(size); //seed for random 
      vector<int> firstArray(size); //create first array
      initArray(firstArray, size);  //array initialization with random numbers
      
      vector<int> secondArray = firstArray; //make a copy of array
      vector<int> thirdArray = firstArray;  //make a copy of array
      
      timeval startTime;    //start time
      timeval endTime;      //end time
      
      outfile<<size;
      
      gettimeofday(&startTime,0);   //get start time
      quicksort(firstArray);       //do bubble sort
      gettimeofday(&endTime,0);     //get end time
      int quickSortTime = elapsed( startTime, endTime );
      outfile<< "\t" << quickSortTime; //output runtime
      
      gettimeofday(&startTime,0);   //get start time
      mergesort(secondArray);   //do insertion sort
      gettimeofday(&endTime,0);     //get end time
      int mergeSortTime = elapsed( startTime, endTime );
      outfile<< "\t" << mergeSortTime; //output runtime
      
      gettimeofday(&startTime,0);   //get start time
      mergeImproved(thirdArray);    //do selection sort
      gettimeofday(&endTime,0);     //get end time
      int mergeImprovedTime = elapsed( startTime, endTime );
      outfile<< "\t" << mergeImprovedTime << "\n"; //output runtime
      
      size += 20; //increment array size by 20
  }
  
	outfile.close();

  return 0;
}
