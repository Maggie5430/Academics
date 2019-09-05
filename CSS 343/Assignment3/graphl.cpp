// ------------------------------graphl.cpp ------------------------------------
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

#include "graphl.h"

// ----------------------------------GraphL-------------------------------------
// Description: Default constructor for the GraphL class
// -----------------------------------------------------------------------------

GraphL::GraphL() {
    //initialize data
    size = 0;
    for (int i = 0; i < MAXNODES; i++) {
        graphList[i].data = NULL;
        graphList[i].edgeHead = NULL;
        graphList[i].visited = false;
    }
} //end of GraphL

// ----------------------------------GraphL-------------------------------------
// Description: Copy constructor for the GraphL class
// -----------------------------------------------------------------------------

GraphL::GraphL(GraphL& other) {
    //deep copy data
    size = other.size;
    for (int i = 0; i < MAXNODES; i++) {
        graphList[i].data = other.graphList[i].data;
        graphList[i].edgeHead = other.graphList[i].edgeHead;
        graphList[i].visited = other.graphList[i].visited;
    }
}//end of GraphL

// ---------------------------------~GraphL-------------------------------------
// Description: Deconstructor for the GraphL class
// -----------------------------------------------------------------------------

GraphL::~GraphL() {
    //properly delete data
    for (int i = 0; i < size; i++) {
        delete graphList[i].data;
        EdgeNode* currentEdge = graphList[i].edgeHead;
        EdgeNode* temp = currentEdge;
        while (currentEdge != NULL) {
            temp = currentEdge;
            currentEdge = currentEdge->nextEdge;
            delete temp;
            temp = NULL;
        }
    }
}

// ------------------------------buildGraph-------------------------------------
// Description: builds up graph node information and adjacency list of edges 
//              between each node reading from a data file. 
// -----------------------------------------------------------------------------

void GraphL::buildGraph(ifstream& fileIn) {
    //size
    fileIn >> size;

    //description
    string name;
    getline(fileIn, name);
    for (int i = 0; i < size; i++) {
        getline(fileIn, name);
        graphList[i].data = new NodeData(name);
    }

    //edges
    int start = 0;
    int end = 0;

    for (;;) {
        fileIn >> start >> end;
        if (start == 0 && end == 0)
            break;
        EdgeNode *newEdge = new EdgeNode();
        newEdge->adjGraphNode = end - 1;
        newEdge->nextEdge = NULL;

        if (graphList[start - 1].edgeHead == NULL) { //new edges
            graphList[start - 1].edgeHead = newEdge;
        } else { //insert edge
            newEdge->nextEdge = graphList[start - 1].edgeHead;
            graphList[start - 1].edgeHead = newEdge;
        }

        //insert edges in order 
        /*} else {
            EdgeNode* currentEdge = graphList[start - 1].edgeHead;
            //insert at head
            if (currentEdge->adjGraphNode > newEdge->adjGraphNode) {
                newEdge->nextEdge = currentEdge;
                graphList[start - 1].edgeHead = newEdge;
            } else {
                //insert else where 
                while (currentEdge->nextEdge != NULL && currentEdge->nextEdge->adjGraphNode < newEdge->adjGraphNode) {
                    currentEdge = currentEdge->nextEdge;
                }
                newEdge->nextEdge = currentEdge->nextEdge;
                currentEdge->nextEdge = newEdge;
         }
      }*/
    }
} //end of buildGraph


// ----------------------------displayGraph-------------------------------------
// Description: Displays each node information and edge in the graph
// -----------------------------------------------------------------------------

void GraphL::displayGraph() {
    cout << "\nGraph:\n";
    //for each node
    for (int i = 0; i < size; i++) {
        cout << "Node " << i + 1 << "       " << *(graphList[i].data) << endl;
        EdgeNode* currentEdge = graphList[i].edgeHead;
        //for each edge
        while (currentEdge != NULL) {
            cout << "  edge " << i + 1 << " " << currentEdge->adjGraphNode + 1 << endl;
            currentEdge = currentEdge->nextEdge;
        }
        cout << endl;
    }
} //end of displayGraph

// ------------------------depthFirstSearch-------------------------------------
// Description: Makes a depth-first search and displays each node in 
//              depth-first order
// -----------------------------------------------------------------------------

void GraphL::depthFirstSearch() {
    //mark all visited as false
    for (int i = 0; i < size; i++) {
        graphList[i].visited = false;
    }

    cout << "\nDepth-first ordering: ";
    for (int i = 0; i < size; i++) {
        if (!graphList[i].visited)
            dfs(i);
    }
    cout << endl;
} //end of depthFirstSearch


// ----------------------------------dfs----------------------------------------
// Description: Private helper method for depthFirstNode. Makes a depth-first
//              search and displays each node in depth-first order
// -----------------------------------------------------------------------------

void GraphL::dfs(int visiting) {
    //mark as true and print
    graphList[visiting].visited = true;
    cout << visiting + 1 << " ";

    //visit unvisited adjacent node 
    EdgeNode* currentEdge = graphList[visiting].edgeHead;
    while (currentEdge != NULL) {
        if (!graphList[currentEdge->adjGraphNode].visited)
            dfs(currentEdge->adjGraphNode);
        currentEdge = currentEdge->nextEdge;
    }
} //end of dfs