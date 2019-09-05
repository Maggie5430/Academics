// ---------------------------- bintree.cpp ------------------------------------
// Margaret Connor CSS 343 A
// Creation Date: 01/21/19
// Date of Last Modification: 02/03/19
// -----------------------------------------------------------------------------
// Purpose - This project is to create a binary search tree class called BinTree
// along with some additional functions.
// -----------------------------------------------------------------------------
// assumptions: array provided for arrayToBSTree has a length of 100, always use
// new NodeData as a parameter for insert function.
// -----------------------------------------------------------------------------

#include "bintree.h"
// ------------------------------- BinTree -------------------------------------
// Default constructor for BinTree Class.
// -----------------------------------------------------------------------------

BinTree::BinTree() {
    root = NULL;
} // end of default constructor

// ------------------------------- BinTree -------------------------------------
// Copy constructor for BinTree Class. (deep copy)
// -----------------------------------------------------------------------------

BinTree::BinTree(const BinTree& orig) {
    root = NULL;
    if (!orig.isEmpty()) {
        copyHelper(root, orig.root);
    }
} //end of copy constructor

// ------------------------------ ~BinTree -------------------------------------
// De-constructor for BinTree Class.
// -----------------------------------------------------------------------------

BinTree::~BinTree() {
    makeEmpty();
} //end of de-constructor

// ------------------------------ isEmpty --------------------------------------
// Checks if current BinTree is empty.
// @return true if empty, false if not empty
// -----------------------------------------------------------------------------

bool BinTree::isEmpty() const {
    if (root == NULL) {
        return true;
    } else {
        return false;
    }
} //end of isEmpty

// ------------------------------ makeEmpty ------------------------------------
// Removes all data from current instance of the BinTree class.
// -----------------------------------------------------------------------------

void BinTree::makeEmpty() {
    makeEmptyHelper(root); //recursive helper
} //end of makeEmpty

// ---------------------------- makeEmptyHelper --------------------------------
// Private recursive helper to makeEmpty. Removes all data from current instance
// of the BinTree class.
// @param current - Current Node
// -----------------------------------------------------------------------------

void BinTree::makeEmptyHelper(Node *&current) {
    if (current) {
        makeEmptyHelper(current->left); //clear left children
        makeEmptyHelper(current->right); //clear right children

        delete current->data; //clear self
        delete current;
        current->data = NULL;
        current = NULL;
    }
} //end of makeEmptyHelper

// ------------------------------- operator= -----------------------------------
// Overloaded assignment operator, makes current instance of BinTree class equal
// to another BinTree class. (Deep copy)
// @param other - other BinTree
// @return Current BinTree equal to other BinTree
// -----------------------------------------------------------------------------

BinTree& BinTree::operator=(const BinTree &other) {
    makeEmpty();
    if (*this != other)
        copyHelper(root, other.root); //recursive helper method
    return *this;
} //end of operator=

// ------------------------------ copyHelper -----------------------------------
// Private recursive helper to assignment operator and copy constructor. Makes 
// current instance of BinTree class equal to another BinTree class. (Deep copy)
// @param copy - Node of current(this) tree
// @param orig - Node of other(original) tree
// -----------------------------------------------------------------------------

void BinTree::copyHelper(Node* &copy, Node* orig)const {
    //deep copies data & constructs new Node
    Node* copyNode = new Node;
    NodeData* copyData = new NodeData(*orig->data);
    copyNode->data = copyData;
    copyNode->left = NULL;
    copyNode->right = NULL;
    copy = copyNode;

    //left right recursive calls
    if (orig->left)
        copyHelper(copy->left, orig->left);
    if (orig->right)
        copyHelper(copy->right, orig->right);
} //end of copyHelper

// ------------------------------ operator== -----------------------------------
// Overloaded equal to operator, checks if current instance of BinTree class is 
// equal to another BinTree class. Define two trees to be equal if they have the
// same data and same structure.
// @param other - other BinTree
// @return true if equal, false if not equal
// -----------------------------------------------------------------------------

bool BinTree::operator==(const BinTree &other) const {
    if (other.isEmpty() && isEmpty())
        return true;
    else if (!other.isEmpty() && !isEmpty())
        return equalToHelper(root, other.root); //calls recursive helper method
    else
        return false;
} //end of operator ==

// --------------------------- equalToHelper -----------------------------------
// Private recursive helper to operator==. Checks if current instance of 
// BinTree class is equal to another BinTree class. Define two trees to be equal
// if they have the same data and same structure.
// @param current - Current Node
// @param other - Other Node
// @return true if equal, false if not equal
// -----------------------------------------------------------------------------

bool BinTree::equalToHelper(Node *current, Node *other)const {
    //checks self
    if (current->data != other->data)
        return false;

    //checks left
    if (current->left != NULL && other->left != NULL) {
        if (!equalToHelper(current->left, other->left))
            return false;
    }
    //checks right
    if (current->right != NULL && other->right != NULL) {
        if (!equalToHelper(current->right, other->right))
            return false;
    }
    //checks both is NULL or not NULL
    if ((current->right != NULL && other->right == NULL) ||
            (current->right == NULL && other->right != NULL) ||
            (current->left != NULL && other->left == NULL) ||
            (current->left == NULL && other->left != NULL))
        return false;

    //all test cases pass
    return true;
} //end of equalToHelper

// ---------------------------- operator!= -------------------------------------
// Overloaded not equal to operator, checks if current instance of BinTree class
// is not equal to another BinTree class. Define two trees to be equal if they 
// have the same data and same structure.
// @param other - other BinTree
// @return true if not equal, false if equals
// -----------------------------------------------------------------------------

bool BinTree::operator!=(const BinTree &other) const {
    //reverse equal to method
    if (*this == other) {
        return false;
    } else {
        return true;
    }
}

// ------------------------------ insert ---------------------------------------
// Inserts a NodeData passed by reference(pointer) into the current instance of
// the BinTree class. Will not insert a duplicate value. 
// @param data - Instance of NodeData to be inserted into BinTree
// @return true if inserted as expected, false if not inserted correctly
// -----------------------------------------------------------------------------

bool BinTree::insert(NodeData* data) {
    return insertHelper(root, data); //recursive helper function
} //end of insert

// --------------------------- insertHelper ------------------------------------
// Private recursive helper method for insert. Inserts a NodeData passed by 
// reference(pointer) into the current instance of the BinTree class. Will not
// insert a duplicate value. 
// @param current - Current Node
// @param data - Instance of NodeData to be inserted into BinTree
// @return true if inserted as expected, false if not inserted correctly
// -----------------------------------------------------------------------------

bool BinTree::insertHelper(Node* &current, NodeData* data) {
    //inserts data at current node
    if (!current) {
        Node* newNode = new Node;
        newNode->data = data;
        newNode->left = NULL;
        newNode->right = NULL;
        current = newNode;
        return true;
    }

    if (*current->data == *data) //catches duplicate values
        return false;
    if (*current->data > *data) { //insert left if data is less than current
        return insertHelper(current->left, data);
    }
    if (*current->data < *data) { //insert right if data is greater than current
        return insertHelper(current->right, data);
    }

    return false;
} //end of insertHelper

// ----------------------------- retrieve --------------------------------------
// Retrieves function to get the NodeData* of a given object in the tree 
// (via pass-by-reference parameter) and to report whether the object is found 
// (true or false). The 2nd parameter in the function signature may initially 
// be garbage. Then if the object is found, it will point to the actual object 
// in the tree.
// @param data - Data being searched for
// @param returnPtr - Pointer to pass-by-reference the location of the data
// @return true if found, false if not found
// -----------------------------------------------------------------------------

bool BinTree::retrieve(NodeData& data, NodeData* returnPtr) const {
    if (!isEmpty())
        return retrieveHelper(root, data, returnPtr); //recursive helper
    else
        return false;
}

// -------------------------- retrieveHelper -----------------------------------
// Private recursive helper method for retrieve. Retrieves function to get the 
// NodeData* of a given object in the tree (via pass-by-reference parameter) and
// to report whether the object is found (true or false). The 2nd parameter in 
// the function signature may initially be garbage. Then if the object is found,
// it will point to the actual object in the tree.
// @param current - Current Node
// @param data - Data being searched for
// @param returnPtr - Pointer to pass-by-reference the location of the data
// @return true if found, false if not found
// -----------------------------------------------------------------------------

bool BinTree::retrieveHelper(Node *current, NodeData& data,
        NodeData* returnPtr) const {
    //checks if data is current
    if (*(current->data) == data) {
        returnPtr = current->data;
        return true;
    }

    //checks left children
    if (*current->data > data && current->left) {
        if (retrieveHelper(current->left, data, returnPtr)) {
            returnPtr = current->data;
            return true;
        }
    }

    //checks right children
    if (*current->data < data && current->right) {
        if (retrieveHelper(current->right, data, returnPtr)) {
            returnPtr = current->data;
            return true;
        }
    }

    //if leftFound and rightFound are both false and current data isn't equal
    return false;
} //end of returnHelper

// -------------------------- operator<< -----------------------------------
// Overload output operator, to display the tree using inorder traversal.
// @param output - output ostream
// @param other - BinTree to output
// @return string of BinTree using inorder traversal
// -----------------------------------------------------------------------------

ostream& operator<<(ostream &output, const BinTree &other) {
    if (!other.isEmpty())
        other.outputHelper(output, other.root); //recursive helper
    return output;
} //end of operator<<

// -------------------------- outputHelper -----------------------------------
// Private recursive helper for operator<<, to display the tree using inorder 
// traversal.
// @param output - output ostream
// @param current - Current Node
// -----------------------------------------------------------------------------

void BinTree::outputHelper(ostream &output, Node* current) const {
    //inorder = left, root, right
    if (current->left != NULL)
        outputHelper(output, current->left);
    output << *(current->data);
    if (current->right != NULL)
        outputHelper(output, current->right);
} //end of outputHelper


//------------------------- displaySideways ------------------------------------
// Displays a binary tree as though you are viewing it from the side;
// hard coded displaying to standard output.
// Preconditions: NONE
// Postconditions: BinTree remains unchanged.
// -----------------------------------------------------------------------------

void BinTree::displaySideways() const {
    sideways(root, 0);
} //end of displaySideways

//---------------------------- Sideways ----------------------------------------
// Helper method for displaySideways
// Preconditions: NONE
// Postconditions: BinTree remains unchanged.
// -----------------------------------------------------------------------------

void BinTree::sideways(Node* current, int level) const {
    if (current != NULL) {
        level++;
        sideways(current->right, level);

        // indent for readability, 4 spaces per depth level 
        for (int i = level; i >= 0; i--) {
            cout << "    ";
        }

        cout << *current->data << endl; // display information of object
        sideways(current->left, level);
    }
} //end of sideways

//---------------------------- getHeight ---------------------------------------
// To find the height of a given value in the tree. SPECIAL INSTRUCTION:
// For this function only, you do not get to know that the tree is a binary 
// search tree. You must solve the problem for a general binary tree where data
// could be stored anywhere.
// @param data - Data to find the height of in tree
// @return the height of the data in the tree or 0 if not found
// -----------------------------------------------------------------------------

int BinTree::getHeight(const NodeData &data) const {
    if (isEmpty()) {
        return 0;
    } else {
        int height = 0;
        return getHeightHelper(root, data, height); //recursive helper
    }
} //end of getHeight

//---------------------------- getHeight ---------------------------------------
// Private recursive helper for getHeight, to find the height of a given value 
// in the tree.
// @param current - Current Node
// @param data - Data to find the height of in tree
// @param depth - Current depth
// @return the height of the data in the tree or 0 if not found
// -----------------------------------------------------------------------------

int BinTree::getHeightHelper(Node* current, const NodeData& data, int depth)const {
    depth++;
    //checks if data is current
    if (*(current->data) == data) {
        return depth;
    }

    int left = 0; //left depth
    int right = 0; //right depth

    //checks left children
    if (current->left) {
        left = getHeightHelper(current->left, data, depth);
    }

    //checks right children
    if (current->right) {
        right = getHeightHelper(current->right, data, depth);
    }

    //returns left or right depth != 0(found) or 0 if not found
    if (right != 0) {
        return right;
    } else if (left != 0) {
        return left;
    } else {
        return 0;
    }
} //end of getHeightHelper

//------------------------- bstreeToArray --------------------------------------
// Fill an array of Nodedata* by using an inorder traversal of the tree. It 
// leaves the tree empty.
// @param array - array to be filled 
// -----------------------------------------------------------------------------

void BinTree::bstreeToArray(NodeData* array[]) {
    if (!isEmpty()) {
        int count = 0;
        bstreeToArrayHelper(root, array, count); //recursive helper
        // arbitrary value 100 defined in project specifics, fills remaining 
        // array with null 
        for (int i = count; i < 100; i++) {
            array[count] = NULL;
        }
        //empties BinTree
        makeEmpty();
    }
} //end of bstreeToArray

//---------------------- bstreeToArrayHelper -----------------------------------
// Private recursive helper for bstreeToArray, Fill an array of Nodedata* by 
// using an inorder traversal of the tree. It leaves the tree empty.
// @param current - Current Node
// @param array - array to be filled 
// @param count - Current array index
// -----------------------------------------------------------------------------

void BinTree::bstreeToArrayHelper(Node* current, NodeData* array[],
        int& count) {
    //left
    if (current->left)
        bstreeToArrayHelper(current->left, array, count);
    //root
    array[count] = new NodeData(*current->data);
    count++;
    //right
    if (current->right)
        bstreeToArrayHelper(current->right, array, count);
} //end of bstreeToArrayHelper

//--------------------------- arrayToBSTree ------------------------------------
// Builds a balanced BinTree from a sorted array of NodeData* leaving the array 
// filled with NULLs. The root (recursively) is at (low+high)/2 where low is the
// lowest subscript of the array range and high is the highest
// @param array - Array to pull NodeData from
// -----------------------------------------------------------------------------

void BinTree::arrayToBSTree(NodeData* array[]) {
    //find how many values are in the array;
    int high = 0;
    while (array[high] != NULL) {
        high++;
    }

    //clear tree
    makeEmpty();
    arrayToBSTreeHelper(root, array, 0, high - 1); //recursive helper
} //end of arrayToBSTree

//--------------------------- arrayToBSTree ------------------------------------
// Private recursive helper for arrayToBSTree, builds a balanced BinTree from a 
// sorted array of NodeData* leaving the array filled with NULLs. The root 
// (recursively) is at (low+high)/2 where low is the lowest subscript of the 
// array range and high is the highest
// @param current - Current Node
// @param array - Array to pull NodeData from
// @param low - Lowest index of array being worked on
// @param high - Highest index of array being worked on
// -----------------------------------------------------------------------------
void BinTree::arrayToBSTreeHelper(Node* &current, NodeData* array[], int low,
        int high) {
    
    if (low <= high) {
        int mid = (low + high) / 2; //middle index to balance tree

        //create Node deep copy
        Node* newNode = new Node;
        NodeData* newData = new NodeData(*array[mid]);
        newNode->data = newData;
        newNode->left = NULL;
        newNode->right = NULL;
        current = newNode;

        //empty array
        delete array[mid];
        array[mid] = NULL;
        
        //recursively call on upper and lower half
        arrayToBSTreeHelper(current->left, array, low, mid - 1);
        arrayToBSTreeHelper(current->right, array, mid + 1, high);
    }
} //end of arrayToBSTreeHelper

