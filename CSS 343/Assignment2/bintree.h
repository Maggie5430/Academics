// ----------------------------- bintree.h--------------------------------------
// Margaret Connor CSS 343 A
// Creation Date: 01/21/19
// Date of Last Modification: 02/03/19
// -----------------------------------------------------------------------------
// Purpose - This project is to create a binary search tree class called BinTree
// along with some additional functions
// -----------------------------------------------------------------------------
// assumptions: array provided for arrayToBSTree has a length of 100, always use
// new NodeData as a parameter for insert function.
// -----------------------------------------------------------------------------
#include <iostream>
#include "nodedata.h"

class BinTree { // you add class/method comments and assumptions
    friend ostream &operator<<(ostream &output, const BinTree &other);
public:
    BinTree();                  // constructor
    BinTree(const BinTree &);   // copy constructor
    ~BinTree();                 // destructor, calls makeEmpty	
    bool isEmpty() const;       // true if tree is empty, otherwise false
    void makeEmpty();           // make the tree empty so isEmpty returns true
    BinTree& operator=(const BinTree &);    //override assignment operator
    bool operator==(const BinTree &) const; //override equalTo operator
    bool operator!=(const BinTree &) const; //override notEqualTo operator
    bool insert(NodeData*);                 //insert data into BinTree
    bool retrieve(NodeData& data, NodeData* pointerData) const; //retrieve data
    void displaySideways() const;           //display tree sideways
    int getHeight(const NodeData &) const;  //get height of data
    void bstreeToArray(NodeData* array[]);  //BinTree to array
    void arrayToBSTree(NodeData* []);       //Array to balance BinTree
private:

    struct Node {
        NodeData* data; // pointer to data object
        Node* left; // left subtree pointer
        Node* right; // right subtree pointer
    };
    Node* root; // root of the tree

    // utility functions
    void sideways(Node*, int) const;        //displaySideways helper
    bool insertHelper(Node* &, NodeData*);  //insert hel[er
    void outputHelper(ostream &, Node*) const;  //output<< helper
    void makeEmptyHelper(Node *&);              //makeEmpty helper
    void copyHelper(Node *&, Node *)const;      //assignment helper
    bool equalToHelper(Node *currentNode, Node *compareNode)const; //equal helper
    bool retrieveHelper(Node *, NodeData&, NodeData*) const; //retrieve helper
    void bstreeToArrayHelper(Node*, NodeData* [], int&); //bstreeToArray helper
    void arrayToBSTreeHelper(Node* &, NodeData* [], int, int); //atbst helper
    int getHeightHelper(Node*, const NodeData&, int)const; //getHeight helper
};