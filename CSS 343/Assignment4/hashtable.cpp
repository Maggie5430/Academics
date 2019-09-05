//-------------------------------- hashtable.cpp -------------------------------
// Margaret Connor, CSS 343 A
// Creation Date 03/13/19
// Last Modified 03/19/19
//------------------------------------------------------------------------------
// Purpose: A local movie rental store wishes to automate their inventory 
// tracking system. Currently there are three types of movies/videos 
// (in DVD media) to be tracked:Comedy, Drama, Classics. Four types of actions 
// are desired in the system: Borrow, Return, Inventory, and History
//------------------------------------------------------------------------------
// Notes: Hash table stores customers for retrieval and storage in O(1) time

#include "hashtable.h"

//-------------------------------- HashTable -----------------------------------
// Description: Default constructor for the HashTable
//------------------------------------------------------------------------------

HashTable::HashTable() {
    hashArray = new HashNode*[MAXSIZE];
    for (int i = 0; i < MAXSIZE; i++)
        hashArray[i] = NULL;
} //end of HashTable

//------------------------------ ~HashTable ------------------------------------
// Description: Destructor of HashTable
//------------------------------------------------------------------------------

HashTable::~HashTable() {
    //iterate through array
    for (int i = 0; i < MAXSIZE; i++) {
        HashNode *current = hashArray[i];
        //iterate through Linked List
        while (current != NULL) {
            HashNode *temp = current;
            current = current->next;
            delete temp->nodeData;
            delete temp;
        }
    }
    delete[] hashArray;
} //end of ~HashTable

//--------------------------------- insert -------------------------------------
// Description: Insert a customer into the HashTable
// @param object - new Customer to insert
//------------------------------------------------------------------------------

void HashTable::insert(Customer &object) {
    int index = hashFunction(object.getCID());
    HashNode *newNode = new HashNode(object);
    //if something in head exists (collision)
    if (hashArray[index] == NULL) {
        hashArray[index] = newNode;
    } else {
        newNode->next = hashArray[index];
        hashArray[index] = newNode;
    }
} //end of insert


//--------------------------------- remove -------------------------------------
// Description: Removes a customer from the HashTable
// @param Customer to be removed 
//------------------------------------------------------------------------------

void HashTable::remove(Customer &object) {
    int index = hashFunction(object.getCID());
    HashNode* current = hashArray[index];

    //delete occurs at head
    if (hashArray[index] != NULL && *(hashArray[index]->nodeData) == object) {
        HashNode *temp = hashArray[index];
        hashArray[index] = hashArray[index]->next;
        delete temp->nodeData;
        delete temp;
    } else {
        //delete occurs in Linked List
        while (current->next != NULL && *(current->next->nodeData) != object) {
            current = current->next;
        }
        if (current->next != NULL) {
            HashNode *temp = current->next;
            current->next = current->next->next;
            delete temp->nodeData;
            delete temp;
        }
    }
} //end of remove

//------------------------------ hashFunction ----------------------------------
// Description: Hash Function
// @param CID - Customer Identification Number
//------------------------------------------------------------------------------

int HashTable::hashFunction(int CID) {
    return CID % 100;
} //end of hashFunction

//-------------------------------- display -------------------------------------
// Description: Displays all customers in HashTable in order of hash index
//------------------------------------------------------------------------------

void HashTable::display() {
    for (int i = 0; i < MAXSIZE; i++) {
        HashNode *current = hashArray[i];
        while (current != NULL) {
            current->nodeData->display();
            current = current->next;
            cout << endl;
        }
    }
} //end of display

//--------------------------------- get ----------------------------------------
// Description: Returns the pointer of a Customer based on a CID
// @param CID - Customer identification number
//------------------------------------------------------------------------------

Customer* HashTable::get(int CID) {
    int index = hashFunction(CID);
    HashNode *current = hashArray[index];
    while (current != NULL && current->nodeData->getCID() != CID) {
        current = current->next;
    }

    if (current == NULL) {
        return NULL;
    } else {
        return current->nodeData;
    }
} //end of get

