//----------------------------- customer.cpp -----------------------------------
// Margaret Connor, CSS 343 A
// Creation Date 03/13/19
// Last Modified 03/19/19
//------------------------------------------------------------------------------
// Purpose: A local movie rental store wishes to automate their inventory 
// tracking system. Currently there are three types of movies/videos 
// (in DVD media) to be tracked:Comedy, Drama, Classics. Four types of actions 
// are desired in the system: Borrow, Return, Inventory, and History
//------------------------------------------------------------------------------
// Notes: Customer class represents the users renting DVDs. 

#include "customer.h"

//--------------------------------- Customer -----------------------------------
// Description: Constructor for Customer class
// @param CIDN - New customer Identification number (0~9999)
// @param fnameN - New customer first name
// @param lnameN - New customer last name
//------------------------------------------------------------------------------

Customer::Customer(int CIDN, string fnameN, string lnameN) :
fname(fnameN), lname(lnameN), CID(CIDN) {
    transactions = NULL;
} //end of Customer

//------------------------------- ~Customer -------------------------------------
// Description: Destructor for Customer class
//------------------------------------------------------------------------------

Customer::~Customer() {
    Node *current = transactions;
    //iterate through linked list
    while (current != NULL) {
        Node *temp = current;
        current = current->next;
        delete temp->trans; //free movie
        delete temp; //free current node
    }
} //end of ~Customer

//----------------------------- operator== -------------------------------------
// Description: Equals to operator for Customer Class
// @param other - compare to object
//------------------------------------------------------------------------------

bool Customer::operator==(const Customer & other) const {
    //Only check CID
    if (CID == other.CID)
        return true;
    else
        return false;
} //end of operator==

//----------------------------- operator != ------------------------------------
// Description: Not equals to operator for Customer Class
// @param other - compare to object
//------------------------------------------------------------------------------

bool Customer::operator!=(const Customer & other) const {
    if (CID != other.CID)
        return true;
    else
        return false;
} //end of operator!=

//----------------------------- getCID -----------------------------------------
// Description: getter for Customer's ID number 
//------------------------------------------------------------------------------

int Customer::getCID() {
    return CID;
}//end getCID

//---------------------------- printHistory ------------------------------------
// Description: Display customer transaction history
//------------------------------------------------------------------------------

void Customer::printHistory() {
    Node *current = transactions;
    cout << "Transaction History ";
    display();
    if (current == NULL) {
        cout << ": No Transactions" << endl;
    } else {
        while (current != NULL) {
            current->trans->display();
            current = current->next;
        }
    }
    cout << endl;
} //end of printHistory 

//------------------------------- display --------------------------------------
// Description: display customer information
//------------------------------------------------------------------------------

void Customer::display() {
    cout << CID << " " << fname << " " << lname << " ";
} //end of display

//--------------------------- newTransaction -----------------------------------
// Description: Creates a new transaction for this customer and adds it to 
// transaction history
// @param product - The product the customer is checking out
// @param borrow - Is this transaction a borrow? else return
//------------------------------------------------------------------------------

void Customer::newTransaction(DVD* product, bool borrow) {
    if (borrow) {
        if (product->borrowed()) {
            Node *newNode = new Node(new Transaction(*product, borrow));
            newNode->next = transactions;
            transactions = newNode;
        } else {
            cout << "ERROR: Out of Stock, " << product->toString() << endl
                    << endl;
        }
    } else {
        Node *current = transactions;
        bool found = false;
        //was product previously borrowed
        while (current != NULL) {
            if (current->trans->getMedia() == *product) {
                found = true;
            }
            current = current->next;
        }
        //add transaction
        if (found) {
            Node *newNode = new Node(new Transaction(*product, borrow));
            newNode->next = transactions;
            transactions = newNode;
        } else {
            cout << "ERROR: Item Not Checked Out to User, "
                    << product->toString() << endl << endl;
        }
    }
} //end of newTransaction