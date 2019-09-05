//------------------------------ inventory.h -----------------------------------
// Margaret Connor, CSS 343 A
// Creation Date 03/13/19
// Last Modified 03/19/19
//------------------------------------------------------------------------------
// Purpose: A local movie rental store wishes to automate their inventory 
// tracking system. Currently there are three types of movies/videos 
// (in DVD media) to be tracked:Comedy, Drama, Classics. Four types of actions 
// are desired in the system: Borrow, Return, Inventory, and History
//------------------------------------------------------------------------------
// Notes: The inventory class stores the store's inventory, movies and customers
// are kept track of in this class.

#ifndef INVENTORY_H
#define INVENTORY_H

#include "customer.h"
#include "hashtable.h"
#include "dvd.h"
#include "transaction.h"
#include <sstream>
#include <string>
#include <iostream>
#include <iomanip>
#include <fstream>
//#include <sstream>
using namespace std;

class Inventory {
public:
    Inventory(); //constructor
    virtual ~Inventory(); //destructor
    void newMovies(ifstream&); //reads in stream of movies
    void newCustomers(ifstream&); //reads in stream of customers
    void exeCommands(ifstream&); //reads in stream of commands
    void printInventory(); //displays inventory

private:
    //Linked List structure

    struct MediaNode {
        MediaNode *next;
        DVD *media;

        MediaNode(DVD *newMedia) {
            next = NULL;
            media = newMedia;
        }
    };
    //LL for media types 
    MediaNode *dramaLL;
    MediaNode *classicLL;
    MediaNode *comedyLL;
    HashTable customers;
    //helper fucntions 
    void deleteLL(MediaNode* current);
};

#endif /* INVENTORY_H */

