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
#include "graphm.h"

// ----------------------------------GraphM-------------------------------------
// Description: Default constructor for the GraphM class
// -----------------------------------------------------------------------------

GraphM::GraphM() {
    size = 0;
    //initialize T 
    for (int i = 0; i < MAXNODES; i++) {
        for (int j = 0; j < MAXNODES; j++) {
            T[i][j].dist = INFINITY;
            T[i][j].visited = false;
            T[i][j].path = 0;
            C[i][j] = INFINITY; //initialize weight adjacency matrix
        }
    }
} //end of GraphM

// ----------------------------------GraphM-------------------------------------
// Description: Copy constructor for the GraphM class
// -----------------------------------------------------------------------------

GraphM::GraphM(const GraphM& other) {
    size = other.size;
    //initialize T 
    for (int i = 0; i < MAXNODES; i++) {
        data[i] = other.data[i]; //deep copy
        for (int j = 0; j < MAXNODES; j++) {
            T[i][j].dist = other.T[i][j].dist;
            T[i][j].visited = other.T[i][j].visited;
            T[i][j].path = other.T[i][j].path;
            C[i][j] = other.C[i][j]; //initialize weight adjacency matrix
        }
    }
} //end of GraphM

// ---------------------------------~GraphM-------------------------------------
// Description: Deconstructor for the GraphM class
// -----------------------------------------------------------------------------

GraphM::~GraphM() {
    //no dynamic memory allocation
} //end of ~GraphM

// ------------------------------buildGraph-------------------------------------
// Description: Builds up graph node information and adjacency matrix of 
//              edges between each node reading from a data file.
// -----------------------------------------------------------------------------

void GraphM::buildGraph(ifstream& fileIn) {
    //the first line tells the number of nodes
    fileIn >> size;
    //followed by a text description of each of the 1 through n nodes
    string name;
    getline(fileIn, name); //ignore the remaining char in first line
    for (int i = 0; i < size; i++) {
        getline(fileIn, name);
        data[i] = NodeData(name);
    }
    //next line consists of 3 integers representing an edge
    int start = 0;
    int end = 0;
    int weight = 0;

    for (;;) {
        fileIn >> start >> end >> weight;
        if (start == 0 && end == 0 && weight == 0)
            break;
        C[start - 1 ][end - 1 ] = weight;
    }
} //end of buildGraph

// ------------------------------insertEdge-------------------------------------
// Description: insert an edge into graph between two given nodes
// -----------------------------------------------------------------------------

void GraphM::insertEdge(int start, int end, int weight) {
    //check nodes
    if (start < 1 || start >= end || end > size) {
        cout << "Node(s) out of bounds" << endl;
    } else if (weight == INFINITY) {
        cout << "Weight Invalid" << endl;
    } else {
        //insert edge
        C[start][end] = weight;
        findShortestPath();
    }
} //end of insertEdge

// ------------------------------insertEdge-------------------------------------
// Description: remove an edge between two given nodes
// -----------------------------------------------------------------------------

void GraphM::removeEdge(int start, int end) {
    //check nodes
    if (start < 1 || start >= end || end > size) {
        cout << "Node(s) out of bounds" << endl;
    } else {
        //remove edge
        C[start][end] = INFINITY;
        findShortestPath();
    }
} //end of removeEdge

// ------------------------findShortestPath-------------------------------------
// Description: Find the shortest path between every node to every other node 
//              in the graph, i.e., TableType T is updated with shortest path
//              information.
// -----------------------------------------------------------------------------

void GraphM::findShortestPath() {
    //clear shortest path from previous calls
    for (int i = 0; i < MAXNODES; i++) {
        for (int j = 0; j < MAXNODES; j++) {
            T[i][j].dist = INFINITY;
            T[i][j].visited = false;
            T[i][j].path = 0;
        }
    }

    //for all nodes
    for (int source = 0; source < size; source++) {
        T[source][source].dist = 0;
        T[source][source].visited = true;
        T[source][source].path = source;

        //clear visits from previous iterations
        for (int i = 0; i < MAXNODES; i++) {
            for (int j = 0; j < MAXNODES; j++) {
                T[i][j].visited = false;
            }
        }

        //v = neighbor
        //w = adjacent
        //finds the shortest distance from source to all other nodes
        for (int i = 0; i < size; i++) {
            //find v, not visited, shortest distance at this point
            int closestNodeDistance = INFINITY;
            int closestNode = i;
            for (int neighbor = 0; neighbor < size; neighbor++) {
                if (!T[source][neighbor].visited &&
                        C[source][neighbor] < closestNodeDistance) {
                    closestNodeDistance = C[source][neighbor];
                    closestNode = neighbor;
                }
            }
            if (closestNode != i) {
                //mark V as visited 
                T[source][closestNode].visited = true;
                //change shortest distance & path if needed
                if (T[source][closestNode].dist > C[source][closestNode]) {
                    T[source][closestNode].dist = C[source][closestNode];
                    T[source][closestNode].path = source;
                }

                //for each adjacent node to neighbor
                for (int adjacent = 0; adjacent < size; adjacent++) {
                    if (C[closestNode][adjacent] != INFINITY
                            && !T[closestNode][adjacent].visited
                            && closestNode != adjacent) {

                        //change shortest distance & path if needed
                        if (T[source][adjacent].dist >
                                T[source][closestNode].dist +
                                C[closestNode][adjacent]) {

                            T[source][adjacent].dist =
                                    T[source][closestNode].dist +
                                    C[closestNode][adjacent];

                            T[source][adjacent].path = closestNode;
                        }
                    }
                }
            }
        }
    }

} //end of findShortestPath

// ------------------------------displayAll-------------------------------------
// Description: uses couts to demonstrate that the algorithm works properly. 
// -----------------------------------------------------------------------------

void GraphM::displayAll() {
    //title line
    cout << "\nDescription               From node  To node  Dijkstra's  Path"
            << endl;
    for (int i = 0; i < size; i++) {
        //String description of Node
        cout << data[i] << endl;
        for (int j = 0; j < size; j++) {
            if (i != j) {
                //From node to node
                cout << left << setw(30) << "" << setw(9) << i + 1 << left
                        << setw(7) << j + 1;
                //Dijksta's distance
                if (T[i][j].dist != INFINITY) {
                    cout << left << setw(12) << T[i][j].dist;

                    //to print numbers in reverse order, I will use a stack
                    int stack[size];
                    int pathSize = 0;
                    int currentPath = j;

                    //fill stack 
                    while (currentPath != i) {
                        stack[pathSize] = currentPath + 1;
                        currentPath = T[i][currentPath].path;
                        pathSize++;
                    }

                    //print starting number
                    cout << i + 1 << " ";

                    //pop values FILO
                    for (int k = pathSize - 1; k >= 0; k--) {
                        cout << stack[k] << " ";
                    }
                } else
                    cout << "---";
                cout << endl;
            }
        }
    }
} //end of displayAll

// ---------------------------------display-------------------------------------
// Description: uses couts to display the shortest distance with path info 
//              between the fromNode to toNode. 
// -----------------------------------------------------------------------------

void GraphM::display(int start, int end) {
    if (start < 1 || start >= end || end > size) {
        //from node to node
        cout << "\n" << left << setw(5) << "" << setw(9) << start << left
                << setw(7) << end << "---" << endl;
    } else {
        //From node to node
        cout << "\n" << left << setw(5) << "" << setw(9) << start << left
                << setw(7) << end;
        //Dijksta's distance
        if (T[start - 1][end - 1].dist != INFINITY) {
            cout << left << setw(12) << T[start - 1 ][end - 1].dist;

            //to print path numbers in reverse order, I will use a stack
            int stack[size];
            int pathSize = 0;
            int currentPath = end - 1;
            //fill stack 
            while (currentPath != start - 1) {
                stack[pathSize] = currentPath + 1;
                currentPath = T[start - 1][currentPath].path;
                pathSize++;
            }
            //print starting number
            cout << start << " ";
            //pop values FILO
            for (int k = pathSize - 1; k >= 0; k--) {
                cout << stack[k] << " ";
            }

            cout << endl << data[start - 1] << endl << endl;

            //pop description in reverse order
            for (int k = pathSize - 1; k >= 0; k--) {
                cout << data[stack[k]-1] << endl;
                if (k > 0)
                    cout << endl;
            }

        } else
            cout << "---" << endl;
    }
} //end of display


