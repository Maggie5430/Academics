/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   transposelist.cpp.h
 * Author: margaretconnor
 *
 * Created on November 20, 2018, 2:54 AM
 */

#ifndef TRANSPOSELIST_CPP_H
#define TRANSPOSELIST_CPP_H
#include "transposelist.h"

template<class Object>
int TransposeList<Object>::find(const Object &obj) {
    DListNode<Object> *found = DList<Object>::header->next;

    int i = 0;
    for (; found != NULL && found->item != obj; found = found->next, ++i)
        ++DList<Object>::cost;

    if (found == NULL)
        return -1; // not found

    if (found == DList<Object>::header->next)
        return 0; // no need to swap

    DListNode<Object> *previousNode = found->prev;
    // remove found from the current position
    if (found->next != NULL) {
        previousNode->next = found->next;
        found->next->prev = previousNode;
    } else {
        previousNode->next = NULL;
    }
    // insert found before previous
    found->prev = previousNode->prev;
    found->next = previousNode;
    previousNode->prev = found;
    found->prev->next = found;

    return i;
}

#endif /* TRANSPOSELIST_CPP_H */

