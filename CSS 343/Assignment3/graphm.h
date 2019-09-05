// ------------------------------graphm.cpp ------------------------------------
//
// Margaret Connor CSS343 A 
// Created: 02/14/19
// Modified: 02/16/10
// -----------------------------------------------------------------------------
// Purpose - This project is to implement Dijkstra's shortest path algorithm. 
// -----------------------------------------------------------------------------
// Assumption - that the max size of the nodes will be 100. The constant
// infinity will never appear as a valid weight. 
// Notes - The nodes are stored internally beginning at 0, all external nodes 
// start at 1. Meaning if index is taking as input - 1 and if outputting + 1. 
// ----------------------------------------------------------------------------- 

#ifndef GRAPHM_H
#define GRAPHM_H
#include <string>
#include <iostream>
#include <iomanip>
#include <fstream>
#include <string>
#include "nodedata.h"
using namespace std;

class GraphM {
public:
    //default constructors
    GraphM();
    GraphM(const GraphM&);
    ~GraphM();

    void buildGraph(ifstream&); //build graph
    void insertEdge(int, int, int); //insert edge between node
    void removeEdge(int, int); //remove edge between node
    void findShortestPath(); //find shortest path between all nodes
    //uses couts to demonstrate that the algorithm works properly
    void displayAll();
    //uses couts to display the shortest distance with path info between the fromNode to toNode
    void display(int, int);
private:
    static const int MAXNODES = 100; //constant as defined in assignment 3
    static const int INFINITY = 2147483647; //infinity max size of int

    struct TableType {
        bool visited; // whether node has been visited
        int dist; // shortest distance from source known so far
        int path; // previous node in path of min dist
    };

    NodeData data[MAXNODES]; // data for graph nodes 
    int C[MAXNODES][MAXNODES]; // Cost array, the adjacency matrix
    int size; // number of nodes in the graph
    TableType T[MAXNODES][MAXNODES]; // stores visited, distance, path
};

#endif /* GRAPHM_H */

