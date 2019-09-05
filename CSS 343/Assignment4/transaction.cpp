//---------------------------- transactions.cpp --------------------------------
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

#include  "transaction.h"

//----------------------------- Transactions -----------------------------------
// Description: constructor
//------------------------------------------------------------------------------

Transaction::Transaction(DVD product, bool borrow) : media(product), 
        borrow(borrow) {

} //end of Transactions

//----------------------------- ~Transactions ----------------------------------
// Description: destructor
//------------------------------------------------------------------------------

Transaction::~Transaction() {
} //end of ~Transaction

//------------------------------- getMedia -------------------------------------
// Description: returns media
// @return - Returns the media stored in this transaction
//------------------------------------------------------------------------------

DVD Transaction::getMedia() {
    return media;
} //end of getMedia

//------------------------------- display --------------------------------------
// Description: Displays transaction details 
//------------------------------------------------------------------------------

void Transaction::display() {
    if (borrow) {
        cout << "Borrowed " << media.toString() << endl;
    } else {
        cout << "Returned " << media.toString() << endl;
    }

} //end of display
