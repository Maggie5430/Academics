//------------------------------ hashtable.h -----------------------------------
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

#ifndef HASHTABLE_H
#define HASHTABLE_H

#include <string>
#include <iostream>
#include <iomanip>
#include <fstream>
#include <string>
#include "customer.h"
using namespace std;

class HashTable {
public:
    HashTable(); //constructor
    virtual ~HashTable(); //destructor

    void insert(Customer &); //insert
    void remove(Customer &); //remove
    Customer* get(int); //get customer
    void display(); //display hash table 
private:
    static const int MAXSIZE = 100; //expect 1000 collisions 
    struct HashNode;
    //open hashing structure

    struct HashNode {
        Customer* nodeData;
        HashNode* next;

        HashNode(Customer &newCustomer) {
            nodeData = &newCustomer;
            next = NULL;
        }
    };

    HashNode **hashArray; //root
    int hashFunction(int); //private helper hash function
};

#endif /* HASHTABLE_H */

