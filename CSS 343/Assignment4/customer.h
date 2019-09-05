//------------------------------- customer.h -----------------------------------
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

#ifndef CUSTOMER_H
#define CUSTOMER_H

#include <string>
#include <iostream>
#include <iomanip>
#include <fstream>
#include <string>
#include  "transaction.h"
#include  "dvd.h"
using namespace std;

class Customer {
public:
    Customer(int, string, string); //default constructor
    virtual ~Customer(); //destructor
    bool operator==(const Customer &other) const; //equals to overload
    bool operator!=(const Customer &other) const; //not equals to overload
    int getCID(); //parameter access
    void newTransaction(DVD*, bool); //customer's transaction
    void printHistory(); //display transaction history
    void display(); //display customer info
private:
    //internal structure for Linked List

    struct Node {
        Node *next;
        Transaction *trans;

        Node(Transaction *newTransaction) {
            next = NULL;
            trans = newTransaction;
        }
    };

    Node *transactions; //Linked List head
    int CID; //customer ID
    string fname; //first name
    string lname; //last name
};

#endif /* CUSTOMER_H */

