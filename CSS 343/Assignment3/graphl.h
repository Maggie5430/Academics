// ------------------------------- graphl.h ------------------------------------
//
// Margaret Connor CSS343 A 
// Created: 02/14/19
// Modified: 02/16/10
// -----------------------------------------------------------------------------
// Purpose - This project is to display the graph information and implement 
//           depth-first. 
// -----------------------------------------------------------------------------
// Assumption - that the max size of the nodes will be 100.
// Notes - The nodes are stored internally beginning at 0, all external nodes 
// start at 1. Meaning if index is taking as input - 1 and if outputting + 1. 
// ----------------------------------------------------------------------------- 

#ifndef GRAPHL_H
#define GRAPHL_H

#include <string>
#include <iostream>
#include <iomanip>
#include <fstream>
#include <string>
#include "nodedata.h"
using namespace std;

class GraphL {
public:
    GraphL(); //Constructor
    GraphL(GraphL&); //Copy constructor
    ~GraphL(); //Deconstructor
    void buildGraph(ifstream&); //Builds up graph node from a data file. 
    void displayGraph(); //Displays graph and all edges
    void depthFirstSearch(); //Displays each node in depth-first order 
private:
    struct EdgeNode; // forward reference for the compiler

    struct GraphNode {
        EdgeNode* edgeHead; // head of the list of edges
        NodeData* data; // data information about each node
        bool visited; // visited for depth first purposes
    };

    struct EdgeNode {
        int adjGraphNode; // subscript of the adjacent graph node
        EdgeNode* nextEdge; // liked list of edges in order added
    };

    static const int MAXNODES = 100; //defined in assignment 3
    int size; //number of current nodes 
    GraphNode graphList[MAXNODES]; //node adjacency list
    void dfs(int); //utility methods 
};


#endif /* GRAPHL_H */

