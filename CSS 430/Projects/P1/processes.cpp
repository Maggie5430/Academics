#include <cstdlib>
#include <assert.h>
#include <sys/wait.h>
#include <unistd.h>
#include<stdio.h>
#include<string.h>
#include<unistd.h>
#include <iostream>
using namespace std;

/*
 * Margaret Connor
 * CSS430: Operating Systems 
 * P1 pt1 
 * 
 * Code a C++ program, named processes.cpp that receives one argument, 
 * (i.e., argv[1]) upon its invocation and searches how many processes whose 
 * name is given in argv[1] are running on the system where your program has 
 * been invoked.
 */
int main(int argc, char** argv) {
    //files & pipes
    int fileD1[2];
    int fileD2[2];
    pipe(fileD1); //for great grand child to grand child
    pipe(fileD2);// for grand child to child 

    int status;
    int pid = fork();

    if (pid < 0) {
        cout << "Fork Error" << endl;
        return 0;
    } else if (pid == 0) {
        pid = fork();

        if (pid < 0) {
            cout << "Fork Error" << endl;
            return 0;
        } else if (pid == 0) {

            pid = fork();

            if (pid < 0) {
                cout << "Fork Error" << endl;
                return 0;
            } else if (pid == 0) { //great grand child

                close(fileD2[0]); //close pipe2 read
                close(fileD2[1]); //close pipe2 write
                close(fileD1[0]); //close pipe1 read
                dup2(fileD1[1], 1); //write to pipe1 write
                int idExe = execlp("ps", "ps", "-A", NULL);

                //execution error
                if (idExe < 0) {
                    cout << "Execution error" << endl;
                    return 0;
                }
            } else { //grand child;
                close(fileD1[1]); // close pipe1 write
                dup2(fileD1[0], 0); // read from pipe1 read
                close(fileD2[0]); // close pipe2 read
                dup2(fileD2[1], 1); // write to pipe2 write
                int idExe = execlp("grep", "grep", argv[1], NULL);

                //execution error
                if (idExe < 0) {
                    cout << "Execution error" << endl;
                    return 0;
                }
            }
        } else { //child
            close(fileD1[0]); //close pipe1 read
            close(fileD1[1]); //close pipe1 write
            close(fileD2[1]); //close pipe2 write
            dup2(fileD2[0], 0); //read from pipe1 read
            int idExe = execlp("wc", "wc", "-l", NULL);
            
            //execution error
            if (idExe < 0) {
                    cout << "Execution error" << endl;
                    return 0;
                }
        }
    } else { //parent 
        //close all pipes
        close(fileD1[0]);
        close(fileD1[1]);
        close(fileD2[0]);
        close(fileD2[1]);

        wait(&status);
    }
    return 0;
}

