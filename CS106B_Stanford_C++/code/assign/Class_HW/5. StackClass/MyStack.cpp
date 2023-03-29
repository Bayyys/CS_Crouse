/*
 * Filename: MyStack.cpp
 * --------------------------
 * Implementation of MyStack class
 *
 */
#include "MyStack.h"
#include "testing/SimpleTest.h"

const int nElements = 10;

MyStack::MyStack(){
    allocatedSize  =   nElements;
    logicalSize    =   0;
    elems          =   new int[allocatedSize];
}

MyStack::~MyStack() {
    delete[] elems;
}

void MyStack::push(int value) {
    if ( logicalSize == allocatedSize ) {
        error("The stack is out of space!");
    } else {
        elems[logicalSize] = value;
        logicalSize += 1;
    }
}

int MyStack::peek() const {
    if ( isEmpty() ) {
        error("The stack is empty, it has no element!");
    } else {
        return elems[logicalSize - 1];
    }
}

int MyStack::pop(){
    int result = peek();
    logicalSize -= 1;
    return result;
}

int MyStack::size() const {
    return logicalSize;
}

bool MyStack::isEmpty() const {
    return size() == 0;
}
