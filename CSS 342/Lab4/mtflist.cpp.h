/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   mtflist.cpp.h
 * Author: margaretconnor
 *
 * Created on November 19, 2018, 6:36 PM
 */

#ifndef MTFLIST_CPP_H
#define MTFLIST_CPP_H
#include "mtflist.h"

template<class Object>
int MtfList<Object>::find(const Object & obj) {
    DListNode<Object> *top = DList<Object>::header->next;
    DListNode<Object> *found = top;
    
    for(;found != NULL && found->item != obj; found = found->next){
        ++DList<Object>::cost;
    }

    if (found == NULL)
        return -1;
    if (found == top)
        return 0;


    //remove found from the current position
    //remember the current precious data
    DListNode<Object> *previousNode = found->prev;
    DListNode<Object> *header =  DList<Object>::header;
    
    
    //remove from current position
    if (found->next != NULL){
    previousNode->next = found->next;
    found->next->prev= previousNode;
    } else {
        previousNode->next = NULL;
    }
    //insert found between header and top
    found->prev = header;
    found->next = header->next;
    header->next->prev = found;
    header->next = found;
    
    return 0;
}

#endif /* MTFLIST_CPP_H */

