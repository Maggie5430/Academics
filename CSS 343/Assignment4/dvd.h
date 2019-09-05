//---------------------------------- dvd.h -------------------------------------
// Margaret Connor, CSS 343 A
// Creation Date 03/13/19
// Last Modified 03/19/19
//------------------------------------------------------------------------------
// Purpose: A local movie rental store wishes to automate their inventory 
// tracking system. Currently there are three types of movies/videos 
// (in DVD media) to be tracked:Comedy, Drama, Classics. Four types of actions 
// are desired in the system: Borrow, Return, Inventory, and History
//------------------------------------------------------------------------------
// Notes: The DVD class represents the product that can be checked out and the
// appropriate type of products.

#ifndef DVD_H
#define DVD_H

#include <string>
#include <iostream>
#include <iomanip>
#include <fstream>
#include <string>
using namespace std;
//-------------------------------- DVD -----------------------------------------
// Description: Abstract class for DVD object
//------------------------------------------------------------------------------

class DVD {
public:
    DVD(int, string, string, int); //constructor
    virtual ~DVD(); //destructor
    virtual bool operator==(const DVD &other) const; //equals to
    virtual void display(); //DVD information
    virtual string toString(); //DVD information (not including stock)
    //getters
    int getStock()const;
    string getDirector() const;
    string getTitle()const;
    int getYear()const;
    int getMonth()const;
    string getMajorActor()const;
    bool borrowed(); // to borrow a DVD
    void returned(); // to return a DVD
protected:
    int stock;
    string director;
    string title;
    int year;
};

//------------------------------ Drama -----------------------------------------
// Description: Concrete child class of DVD, of type Drama
//------------------------------------------------------------------------------

class Drama : public DVD {
public:
    Drama(int, string, string, int); //constructor
    ~Drama(); //destructor
    bool operator==(const Drama &other) const; //equals to
    void display(); //DVD information
    string toString(); //DVD information (not including stock)
};

//------------------------------ Classic ---------------------------------------
// Description: Concrete child class of DVD, of type Classic
//------------------------------------------------------------------------------

class Classic : public DVD {
public:
    Classic(int, string, string, string, int, int); //constructor
    ~Classic(); //destructor
    bool operator==(const Classic &other) const; //equals to
    void display(); //DVD information
    string toString(); //DVD information (not including stock)
    //getters
    int getMonth()const;
    string getMajorActor() const;
private:
    string majorActor;
    int month;

};

//------------------------------ Comedy ----------------------------------------
// Description: Concrete child class of DVD, of type Comedy
//------------------------------------------------------------------------------

class Comedy : public DVD {
public:
    Comedy(int, string, string, int); //constructor
    ~Comedy(); //destructor
    bool operator==(const Comedy &other) const; //equals to
    void display(); //DVD information
    string toString(); //DVD information (not including stock)
};

#endif /* DVD_H */

