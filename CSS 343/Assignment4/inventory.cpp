///------------------------------ inventory.cpp --------------------------------
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

#include "inventory.h"
#include "hashtable.h"
#include "transaction.h"
#include <sstream>

//-------------------------------- Inventory -----------------------------------
// Description: Default constructor for Inventory class
//------------------------------------------------------------------------------

Inventory::Inventory() {
    dramaLL = NULL;
    classicLL = NULL;
    comedyLL = NULL;
}//end of Inventory

//------------------------------- ~Inventory -----------------------------------
// Description: destructor for Inventory class
//------------------------------------------------------------------------------

Inventory::~Inventory() {
    deleteLL(dramaLL);
    deleteLL(comedyLL);
    deleteLL(classicLL);
}

//-------------------------------- deleteLL ------------------------------------
// Description: Private helper class of destructor, deletes linked list
//------------------------------------------------------------------------------

void Inventory::deleteLL(MediaNode* current) {
    MediaNode *temp;
    while (current != NULL) {
        temp = current;
        current = current->next;
        delete temp->media; //free movie
        delete temp; //free current node
    }
}

//-------------------------------- newMovies -----------------------------------
// Description: Reads in ifstrem and adds movies to Inventory
//------------------------------------------------------------------------------

void Inventory::newMovies(ifstream& fileIn) {
    char type;
    string disregard;
    int stock;
    string director;
    string title;
    string majorActorFirst;
    string majorActorLast;
    int month;
    int year;

    while (getline(fileIn, disregard)) {
        //tests if empty
        if (!disregard.empty()) {
            istringstream line(disregard);
            line >> type;
            getline(line, disregard, ' ');

            if (type == 'F') {//create new comedy
                line >> stock;
                getline(line, disregard, ' ');
                getline(line, director, ',');
                getline(line, disregard, ' ');
                getline(line, title, ',');
                line >> year;
                getline(line, disregard);
                //test for correct input
                if (stock > 0 && year > 1887 && year < 2050) {
                    DVD *newDVD = new Comedy(stock, director, title, year);
                    MediaNode *newNode = new MediaNode(newDVD);
                    MediaNode *current = comedyLL;
                    //insert in head of Linked List
                    if (current == NULL || current->media->getTitle().compare
                            (newDVD->getTitle()) > 0) {
                        newNode->next = comedyLL;
                        comedyLL = newNode;
                    } else {
                        //insert in body of Linked List
                        while (current->next != NULL &&
                                (current->next->media->getTitle().compare
                                (newDVD->getTitle()) < 0
                                || (current->next->media->getTitle().compare
                                (newDVD->getTitle()) == 0
                                && current->next->media->getYear() >
                                newDVD->getYear()))) {
                            current = current->next;
                        }
                        newNode->next = current->next;
                        current->next = newNode;
                    }
                } else {
                    //invalid parameter errors 
                    cout << "ERROR: Invalid Movie Parameter, " << type << ", "
                            << stock << " " << director << ", " << title << ", "
                            << year << endl << endl;
                }
            } else if (type == 'D') { //create a new dram 
                line >> stock;
                getline(line, disregard, ' ');
                getline(line, director, ',');
                getline(line, disregard, ' ');
                getline(line, title, ',');
                line >> year;
                getline(line, disregard);

                //check parameters
                if (stock > 0 && year > 1887 && year < 2050) {
                    DVD *newDVD = new Drama(stock, director, title, year);
                    MediaNode *newNode = new MediaNode(newDVD);
                    MediaNode *current = dramaLL;

                    //insert in head of Linked List
                    if (current == NULL || current->media->getDirector().
                            compare(newDVD->getDirector()) > 0) {
                        newNode->next = dramaLL;
                        dramaLL = newNode;
                    } else {
                        //insert in body of Linked List
                        while (current->next != NULL &&
                                (current->next->media->getDirector().
                                compare(newDVD->getDirector()) < 0
                                || (current->next->media->getDirector().
                                compare(newDVD->getDirector()) == 0
                                && current->next->media->getTitle().
                                compare(newDVD->getTitle()) < 0))) {
                            current = current->next;
                        }
                        newNode->next = current->next;
                        current->next = newNode;
                    }
                } else {
                    //invalid parameters
                    cout << "ERROR: Invalid Movie Parameter, " << type << ", "
                            << stock << " " << director << ", " << title << ", "
                            << year << endl << endl;
                }
            } else if (type == 'C') {
                line >> stock;
                getline(line, disregard, ' ');
                getline(line, director, ',');
                getline(line, disregard, ' ');
                getline(line, title, ',');
                line >> majorActorFirst;
                line >> majorActorLast;
                line >> month;
                line >> year;
                getline(line, disregard);

                //check for valid parameters 
                if (stock > 0 && year > 1887 && year < 2050 && month > 0
                        && month < 13) {
                    DVD *newDVD = new Classic(stock, director, title,
                            majorActorFirst + " " + majorActorLast,
                            month, year);
                    MediaNode *newNode = new MediaNode(newDVD);
                    MediaNode *current = classicLL;

                    //insert in head of Linked List
                    if (current == NULL
                            || current->media->getYear() < newDVD->getYear()
                            || (current->media->getYear() == newDVD->getYear()
                            && current->media->getMonth() < newDVD->getMonth())
                            || (current->media->getYear() == newDVD->getYear()
                            && current->media->getMonth() == newDVD->getMonth()
                            && current->media->getMajorActor().compare
                            (newDVD->getMajorActor()) < 0)) {
                        newNode->next = classicLL;
                        classicLL = newNode;
                    } else {
                        //insert in body of LinkedList
                        while (current->next != NULL && (
                                current->next->media->getYear() >
                                newDVD->getYear()
                                || (current->next->media->getYear() ==
                                newDVD->getYear()
                                && current->next->media->getMonth() >
                                newDVD->getMonth())
                                || (current->next->media->getYear() ==
                                newDVD->getYear()
                                && current->next->media->getMonth() ==
                                newDVD->getMonth()
                                && current->next->media->getMajorActor()
                                .compare(newDVD->getMajorActor()) > 0))) {
                            current = current->next;
                        }
                        newNode->next = current->next;
                        current->next = newNode;
                    }
                } else {
                    //invalid parameters 
                    cout << "ERROR: Invalid Movie Parameter, " << type << ", "
                            << stock << " " << director << ", " << title << ", "
                            << majorActorFirst << " " << majorActorLast << " "
                            << month << " " << year << endl << endl;
                }
            } else {
                //invalid video code
                getline(line, disregard);
                cout << "ERROR: Invalid Video Code, " << type << ", "
                        << disregard << endl << endl;
            }
        }
    }
} //end of newMovies

//--------------------------- printInventory -----------------------------------
// Description: reads in stream of customers and adds them to inventory
// @param fileIn - Stream of customer details
//------------------------------------------------------------------------------

void Inventory::newCustomers(ifstream& fileIn) {
    int CID;
    string line;
    string firstName;
    string lastName;

    while (getline(fileIn, line)) {
        if (!line.empty()) {
            istringstream tmp(line);
            tmp >> CID >> firstName >> lastName;
            Customer *newCustomer = new Customer(CID, firstName, lastName);
            customers.insert(*newCustomer);
        }
    }
} //end of newCustomer

//--------------------------- printInventory -----------------------------------
// Description: reads in stream of commands and performs the correct actions
// @param fileIn - Stream of customer details
//------------------------------------------------------------------------------

void Inventory::exeCommands(ifstream& FileIn) {
    char type;
    string disregard;
    while (getline(FileIn, disregard)) {
        if (!disregard.empty()) {
            istringstream fileIn(disregard);
            fileIn >> type;

            if (type == 'I') { //print inventory
                printInventory();
                getline(fileIn, disregard);
            } else if (type == 'H') { //view customer history
                int CID;
                fileIn >> CID;
                Customer *thisCustomer = customers.get(CID);

                if (thisCustomer != NULL) {
                    thisCustomer->printHistory();
                } else {
                    cout << "ERROR: Incorrect Customer ID,  " << CID << endl
                            << endl;
                }
            } else if (type == 'B' || type == 'R') { //borrow or return
                int CID;
                char mediaType;
                char movieType;
                string director;
                string title;
                string majorActorFirst;
                string majorActorLast;
                int month;
                int year;

                fileIn >> CID >> mediaType >> movieType;
                Customer *thisCustomer = customers.get(CID);

                if (movieType == 'F') { //comedy
                    getline(fileIn, title, ',');
                    fileIn >> year;
                    getline(fileIn, disregard);
                    MediaNode *current = comedyLL;
                    //find movie in inventory
                    while (current != NULL
                            && current->media->getTitle().compare(title) != 0
                            && current->media->getYear() != year) {
                        current = current->next;
                    }
                    //perform transaction
                    if (thisCustomer == NULL) {
                        cout << "ERROR: Incorrect Customer ID,  " << CID
                                << endl << endl;
                    } else if (current != NULL && type == 'B') {
                        thisCustomer->newTransaction(current->media, true);
                    } else if (current != NULL && type == 'R') {
                        thisCustomer->newTransaction(current->media, false);
                    } else {
                        cout << "ERROR: Invalid Movie, " << movieType << " "
                                << title << " " << year << endl << endl;
                    }
                } else if (movieType == 'D') { //drama
                    getline(fileIn, director, ',');
                    getline(fileIn, disregard, ' ');
                    getline(fileIn, title, ',');
                    MediaNode *current = dramaLL;
                    //find movie in inventory
                    while (current != NULL
                            && current->media->getDirector().compare(director)
                            != 0
                            && current->media->getTitle().compare(title) != 0) {
                        current = current->next;
                    }
                    //perform transaction
                    if (thisCustomer == NULL) {
                        cout << "ERROR: Incorrect Customer ID,  " << CID
                                << endl << endl;
                    } else if (current != NULL && type == 'B') {
                        thisCustomer->newTransaction(current->media, true);
                    } else if (current != NULL && type == 'R') {
                        thisCustomer->newTransaction(current->media, false);
                    } else {
                        cout << "ERROR: Invalid Movie, " << movieType << " "
                                << director << " " << title << endl << endl;
                    }
                } else if (movieType == 'C') { //classic
                    fileIn >> month >> year >> majorActorFirst
                            >> majorActorLast;
                    MediaNode *current = classicLL;
                    //find movie in inventory
                    while (current != NULL
                            && current->media->getMonth() != month
                            && current->media->getYear() != year
                            && current->media->getMajorActor().compare
                            (majorActorFirst + " " + majorActorLast)) {
                        current = current->next;
                    }
                    //perform transaction
                    if (thisCustomer == NULL) {
                        cout << "ERROR: Incorrect Customer ID,  " << CID
                                << endl << endl;
                    } else if (current != NULL && type == 'B') {
                        thisCustomer->newTransaction(current->media, true);
                    } else if (current != NULL && type == 'R') {
                        thisCustomer->newTransaction(current->media, false);
                    } else {
                        cout << "ERROR: Invalid Movie, " << movieType << " "
                                << month << " " << year << " "
                                << majorActorFirst << " " << majorActorLast
                                << endl << endl;
                    }
                } else {
                    //invalid video code 
                    getline(fileIn, disregard);
                    cout << "ERROR: Invalid Video Code, " << type << " "
                            << movieType << " " << disregard << endl << endl;
                }
            } else {
                //invalid validation code 
                getline(fileIn, disregard);
                cout << "ERROR: Invalid Action Code, " << type << " "
                        << disregard << endl << endl;
            }
        }
    }
}

//--------------------------- printInventory -----------------------------------
// Description: Default constructor for Inventory class
//------------------------------------------------------------------------------

void Inventory::printInventory() {
    cout << "------------------- Inventory --------------------" << endl;
    //comedy
    MediaNode *current = comedyLL;
    while (current != NULL) {
        current->media->display();
        current = current->next;
    }
    //drama
    current = dramaLL;
    while (current != NULL) {
        current->media->display();
        current = current->next;
    }
    //classic
    current = classicLL;
    while (current != NULL) {
        current->media->display();
        current = current->next;
    }
    cout << "----------------------------------------------" << endl << endl;

} //end of printInventory
