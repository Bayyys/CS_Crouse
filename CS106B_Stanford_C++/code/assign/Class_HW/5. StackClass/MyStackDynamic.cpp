/*
 * Filename: MyStackDynamic.cpp
 * --------------------------
 * Implementation of MyStackDynamic class
 *
 */
#include "MyStackDynamic.h"
#include "testing/SimpleTest.h"

#define version_1

const int capacity = 8;

StackDynamic::StackDynamic() {
    allocatedSize  =   capacity;
    logicalSize    =   0;
    elements       =   new int [allocatedSize];
}

StackDynamic::~StackDynamic() {
    delete [] elements;
}

void StackDynamic::push(int value) {
    if ( logicalSize == allocatedSize ) {
        grow();
    }
    elements[logicalSize] = value;
    logicalSize += 1;
}

int StackDynamic::peek() const {
    if ( isEmpty() ) {
        error("The stack is empty, it has no element!");
    }
    return elements[logicalSize - 1];
}

int StackDynamic::pop() {
    int result = peek();
    logicalSize -= 1;
    return result;
}

int StackDynamic::size() const {
    return logicalSize;
}

bool StackDynamic::isEmpty() const {
    return size() == 0;
}

/*
 *
 */
void StackDynamic::grow() {
#ifdef version_1
    //- O(N)
    allocatedSize *= 2;
#endif

#ifdef version_2
    //- O(N^2)
    allocatedSize += 1;
#endif

#ifdef version_3
    //- O(N^2), but two-times faster than version-2
    allocatedSize += 2;
#endif

    int *helper = new int[allocatedSize];

    /* copy everything over */
    for ( int i = 0; i < logicalSize; i++ ) {
        helper[i] = elements[i];
    }

    //- delete old array
    delete [] elements;

    //- point to new address, the helper will disapper
    //- when this function returns.
    elements = helper;
}


/* * * * * Test cases * * * * */
void pushElement(int size, StackDynamic& stack) {
    for ( int i = 0; i < size; i++ ) {
        stack.push(i);
    }
}

STUDENT_TEST("Time test of push method in stack:") {
    int size = 10000;
    for ( int i = 1; i <= 16; i *= 2 ) {
        int k = size * i;
        StackDynamic stack;
        TIME_OPERATION(k, pushElement(k, stack));
    }
}
