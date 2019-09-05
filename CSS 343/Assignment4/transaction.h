//------------------------------ transactions.cpp ------------------------------
// Margaret Connor, CSS 343 A
// Creation Date 03/13/19
// Last Modified 03/19/19
//------------------------------------------------------------------------------
// Purpose: A local movie rental store wishes to automate their inventory 
// tracking system. Currently there are three types of movies/videos 
// (in DVD media) to be tracked:Comedy, Drama, Classics. Four types of actions 
// are desired in the system: Borrow, Return, Inventory, and History
//------------------------------------------------------------------------------
// Notes: Transactions between customers and store

#ifndef TRANSACTION_H
#define TRANSACTION_H
#include "dvd.h"

class Transaction {
public:
    Transaction(DVD, bool); //constructor
    virtual ~Transaction(); //destructor
    virtual void display(); //display
    DVD getMedia(); //returns media
private:
    DVD media; //DVD
    bool borrow; //borrow
};

#endif /* TRANSACTION_H */

