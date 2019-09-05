
#include "deque.h"

/**
 * The default constructor 
 */
template <class Object>
Deque<Object>::Deque() {
    front = back = NULL;
}

/**
 * Copy constructor
 * @param rhs other LongInt
 */
template <class Object>
Deque<Object>::Deque(const Deque &rhs) {
    front = back = NULL;
    *this = rhs;
}

/**
 * The Destructor
 */
template <class Object>
Deque<Object>::~Deque() {
    clear();
}

/**
 * check if a deque is empty
 * @return Returns true if queue is empty
 */
template <class Object>
bool Deque<Object>::isEmpty() const {
    return front == NULL;
}

/**
 * retrieves # deque nodes
 * @return number of nodes
 */
template <class Object>
int Deque<Object>::size() const { 
    int i = 0;
    for (DequeNode *ptr = front; ptr != NULL; ptr = ptr->next) // traverse queue
        ++i;

    return i;
}

/**
 * Retrieve the item in front node
 * @return returns item in front
 */
template <class Object>
const Object &Deque<Object>::getFront() const {
    if (isEmpty())
        throw "empty queue";
    return front->item;
}

/**
 * Retrieve the item in back node
 * @return returns item in back
 */
template <class Object>
const Object &Deque<Object>::getBack() const {
    if (isEmpty())
        throw "empty queue";
    return back->item;
}

/**
 * Clears all entries
 */
template <class Object>
void Deque<Object>::clear() {
    while (!isEmpty()) // dequeue till the queue gets empty.
        removeFront();
}

/**
 * add a new node to  front
 * @param obj item to be add
 */
template <class Object>
void Deque<Object>::addFront(const Object &obj) {
    DequeNode *newPtr = new DequeNode;
    newPtr->item = obj;
    newPtr->next = front;
    newPtr->prev = NULL;

    if (front == NULL)
        back = newPtr;
    else
        front->prev = newPtr;
    front = newPtr;
}

/**
 * add a new node to the back
 * @param obj item to be add
 */
template <class Object>
void Deque<Object>::addBack(const Object &obj) { // add a new node to tail
    DequeNode *newPtr = new DequeNode;
    newPtr->item = obj;
    newPtr->next = NULL;
    newPtr->prev = back;

    if (isEmpty())
        front = newPtr;
    else
        back->next = newPtr;
    back = newPtr;
}

/**
 * remove front node and return item
 * @return front item
 */
template <class Object>
Object Deque<Object>::removeFront() { // remove the front node
    Object frontItem = getFront();
    DequeNode *old = front;

    if (front == back)
        front = back = NULL;
    else {
        front = front->next;
        front->prev = NULL;
    }
    delete old;
    return frontItem;
}

/**
 * remove back node and return item
 * @return back item
 */
template <class Object>
Object Deque<Object>::removeBack() { // remove the tail node
    Object backItem = getBack();
    DequeNode *old = back;

    if (front == back)
        front = back = NULL;
    else {
        back = back->prev;
        back->next = NULL;
    }
    delete old;
    return backItem;
}

/**
 * assignment operator, sets current as a copy of other
 * @param rhs other node 
 * @return new current
 */
template <class Object>
const Deque<Object> &Deque<Object>::operator=(const Deque &rhs) { // assign
    if (this != &rhs) { // avoid self assignment
        clear();
        for (DequeNode *rptr = rhs.front; rptr != NULL; rptr = rptr->next)
            addBack(rptr->item);
    }
    return *this;
}


