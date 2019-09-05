//-------------------------------- main.cpp ------------------------------------
// Margaret Connor, CSS 343 A
// Creation Date 03/13/19
// Last Modified 03/19/19
//------------------------------------------------------------------------------
// Purpose: A local movie rental store wishes to automate their inventory 
// tracking system. Currently there are three types of movies/videos 
// (in DVD media) to be tracked:Comedy, Drama, Classics. Four types of actions 
// are desired in the system: Borrow, Return, Inventory, and History
//------------------------------------------------------------------------------
// Notes: Main.cpp is the main driver for this program

#include <cstdlib>
#include <iostream>
#include <fstream>
#include "hashtable.h"
#include "dvd.h"
#include "inventory.h"
using namespace std;

//--------------------------------- main ---------------------------------------
// Description: Driver for program.
//------------------------------------------------------------------------------

int main(int argc, char** argv) {
    //reads data4 movies & calls appropriate method
    Inventory myStore;
    ifstream movieFile;
    movieFile.open("data4movies.txt");
    if (!movieFile) {
        cout << "Error: File not found or corrupt. " << endl;
        return 1;
    } else {
        myStore.newMovies(movieFile);
    }
    //reads data4customers & calls appropriate method
    ifstream customerFile;
    customerFile.open("data4customers.txt");
    if (!customerFile) {
        cout << "Error: File not found or corrupt. " << endl;
        return 1;
    } else {
        myStore.newCustomers(customerFile);
    }
    //reads data4commands & calls appropriate method
    ifstream commandFile;
    commandFile.open("data4commands.txt");
    if (!commandFile) {
        cout << "Error: File not found or corrupt. " << endl;
        return 1;
    } else {
        myStore.exeCommands(commandFile);
    }
    return 0;

} //end of main

