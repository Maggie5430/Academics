//--------------------------------- dvd.cpp ------------------------------------
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

#include "dvd.h"
#include <sstream>

//------------------------------ DVD -------------------------------------------
// Description: Constructor for DVD Class
// @param stock - Number of DVDs currently in store
// @param director - DVD Director
// @param title - DVD Title
// @param year - DVD release year
//------------------------------------------------------------------------------

DVD::DVD(int stock, string director, string title, int year) :
director(director), title(title) {
    if (stock > 0)
        this->stock = stock;
    else
        this->stock = 0;

    if (year > 1888 && year < 2050)
        this->year = year;
    else
        this->year = 9999;
} //end of DVD

//--------------------------------- ~DVD ---------------------------------------
// Description: Destructor
//------------------------------------------------------------------------------

DVD::~DVD() {
} //end of ~DVD

//----------------------------- operator== -------------------------------------
// Description: Equals to operator for DVD Class
// @param other - compared to DVD
//------------------------------------------------------------------------------

bool DVD::operator==(const DVD &other) const {

    if (director.compare(other.getDirector()) == 0
            && title.compare(other.getTitle()) == 0
            && year == other.getYear()) {
        return true;
    } else {
        return false;
    }
} //end of operator==

//------------------------------- display --------------------------------------
// Description: print DVD information to console
//------------------------------------------------------------------------------

void DVD::display() {
    cout << "DVD, " << stock << ", " << director << ", " << title << ", "
            << year << endl;
} //end of display

//------------------------------- getStocks ------------------------------------
// Description: getter returns stocks
// @return int - stock of DVD
//------------------------------------------------------------------------------

int DVD::getStock() const {
    return stock;
} //end of getStock

//----------------------------- getDirector ------------------------------------
// Description: getter returns director
// @return string - director of DVD
//------------------------------------------------------------------------------

string DVD::getDirector() const {
    return director;
} //end of getDirector

//----------------------------- getTitle ---------------------------------------
// Description: Getter returns Title
// @return string - Title of DVD
//------------------------------------------------------------------------------

string DVD::getTitle() const {
    return title;
} //end of getTitle


//----------------------------- getYear ---------------------------------------
// Description: Getter returns year
// @return string - Year of DVD release
//------------------------------------------------------------------------------

int DVD::getYear()const {
    return year;
} //end of getYear

//----------------------------- getMonth ---------------------------------------
// Description: Getter returns month
// @return int - month of DVD
//------------------------------------------------------------------------------

int DVD::getMonth()const {
    return 0;
} //end of getMonth


//----------------------------- getMajorActor ----------------------------------
// Description: Getter returns Major Actor
// @return string - Major Actor of DVD
//------------------------------------------------------------------------------

string DVD::getMajorActor() const {
    return "";
} //end of getMajor

//------------------------------- toString -------------------------------------
// Description: Returns description of DVD object not including the stock
// @return string - description of DVD
//------------------------------------------------------------------------------

string DVD::toString() {
    stringstream yearString;
    yearString << year;
    return ("DVD, " + director + ", " + title + ", " + yearString.str());
} //end of toString

//------------------------------- borrowed -------------------------------------
// Description: borrow the DVD object from inventory
// @return bool - Can the item be borrowed (stock > 0)
//------------------------------------------------------------------------------

bool DVD::borrowed() {
    if (stock > 0) {
        stock--;
        return true;
    } else {
        return false;
    }
} //end of borrowed

//----------------------------- getDirector ------------------------------------
// Description: return the DVD object to inventory
//------------------------------------------------------------------------------

void DVD::returned() {
    stock++;
} //end or returned 

//------------------------------ Drama -----------------------------------------
// Description: Constructor for Drama Class
// @param stock - Number of Drama currently in store
// @param director - Drama Director
// @param title - Drama Title
// @param year - Drama release year
//------------------------------------------------------------------------------

Drama::Drama(int stock, string director, string title, int year) :
DVD(stock, director, title, year) {
} //end of Drama

//--------------------------------- ~Drama -------------------------------------
// Description: Destructor
//------------------------------------------------------------------------------

Drama::~Drama() {
} //end of ~Drama

//------------------------------- display --------------------------------------
// Description: print Drama information to console
//------------------------------------------------------------------------------

void Drama::display() {
    cout << "Drama, " << stock << ", " << director << ", " << title << ", "
            << year << endl;
} //end of display

//------------------------------- toString -------------------------------------
// Description: Returns description of Drama object not including the stock
// @return string - description of Drama
//------------------------------------------------------------------------------

string Drama::toString() {
    stringstream yearString;
    yearString << year;
    return ("Drama, " + director + ", " + title + ", " + yearString.str());
} //end of toString

//----------------------------- operator== -------------------------------------
// Description: Equals to operator for Drama Class
// @param other - compared to Drama
//------------------------------------------------------------------------------

bool Drama::operator==(const Drama &other) const {
    if (director.compare(other.getDirector()) == 0
            && title.compare(other.getTitle()) == 0
            && year == other.getYear()) {
        return true;
    } else {
        return false;
    }
} //end of operator==

//----------------------------- Classic ----------------------------------------
// Description: Constructor for Classic Class
// @param stock - Number of Classic currently in store
// @param director - Classic Director
// @param title - Classic Title
// @param year - Classic release year
// @param majorActorN - Classic's major actor
// @param month - Classic release month
//------------------------------------------------------------------------------

Classic::Classic(int stock, string director, string title, string majorActor,
        int month, int year) : DVD(stock, director, title, year), 
        majorActor(majorActor) {
    if (month > 0 && month < 13)
        this->month = month;
    else
        this->month = 0;
} //end of classic

//----------------------------- ~Classic ---------------------------------------
// Description: Destructor
//------------------------------------------------------------------------------

Classic::~Classic() {
} //end of ~Classic

//----------------------------- operator== -------------------------------------
// Description: Equals to operator for Classic Class
// @param other - compared to Classic
//------------------------------------------------------------------------------

bool Classic::operator==(const Classic &other) const {
    if (director.compare(other.director) == 0
            && title.compare(other.title) == 0
            && majorActor.compare(other.majorActor) == 0
            && month == other.month && year == other.year)
        return true;
    else
        return false;
} //end of operator

//------------------------------- display --------------------------------------
// Description: print Classic information to console
//------------------------------------------------------------------------------

void Classic::display() {
    cout << "Classic, " << stock << ", " << director << ", " << title << ", "
            << majorActor << " " << month << " " << year << endl;
} //end of classic

//------------------------------- getMonth -------------------------------------
// Description: Returns the month the classic was released
// @return int - Month the Classic was released
//------------------------------------------------------------------------------

int Classic::getMonth() const {
    return month;
} //end of getMonth

//------------------------------- getMajorActor --------------------------------
// Description: Returns the major actor of the Classic 
// @return int - MajorActor of the Classic
//------------------------------------------------------------------------------

string Classic::getMajorActor() const {
    return majorActor;
} //end of majorActor

//------------------------------- toString -------------------------------------
// Description: Returns description of Classic object not including the stock
// @return string - description of Classic
//------------------------------------------------------------------------------

string Classic::toString() {
    stringstream monthString;
    stringstream yearString;
    monthString << month;
    yearString << year;
    return "Classic, " + director + ", " + title + ", " + majorActor + " " +
            monthString.str() + " " + yearString.str();
} //end of two string

//------------------------------ Comedy ----------------------------------------
// Description: Constructor for Comedy Class
// @param stock - Number of Comedy currently in store
// @param director - Comedy Director
// @param title - Comedy Title
// @param year - Comedy release year
//------------------------------------------------------------------------------

Comedy::Comedy(int stock, string director, string title, int year) :
DVD(stock, director, title, year) {
} //end of Comedy

//------------------------------- ~Comedy --------------------------------------
// Description: Destructor
//------------------------------------------------------------------------------

Comedy::~Comedy() {
} //end of ~Comedy

//----------------------------- operator== -------------------------------------
// Description: Equals to operator for Comedy Class
// @param other - compared to Comedy
//------------------------------------------------------------------------------

bool Comedy::operator==(const Comedy &other) const {
    if (director.compare(other.director) == 0 && title.compare(other.title) == 0
            && year == other.year)
        return true;
    else
        return false;
} //end of operator==

//------------------------------- display --------------------------------------
// Description: print Comedy information to console
//------------------------------------------------------------------------------

void Comedy::display() {
    cout << "Comedy, " << stock << ", " << director << ", " << title << ", "
            << year << endl;
} //end of display

//------------------------------- toString -------------------------------------
// Description: Returns description of Comedy object not including the stock
// @return string - description of Comedy
//------------------------------------------------------------------------------

string Comedy::toString() {
    stringstream yearString;
    yearString << year;
    return "Comedy, " + director + ", " + title + ", " + yearString.str();
} //end of toString
