/*
 * Filename: MyStack.h
 * --------------------------
 * Declare of MyStack class
 *
 */

#ifndef MYSTACK_H
#define MYSTACK_H

class MyStack {
private:
    int allocatedSize;
    int logicalSize;
    int *elems;

public:
    MyStack();
    ~MyStack();

    //- member function:
    void push(int value);
    int peek() const;
    int pop();

    int size() const;
    bool isEmpty() const;
};

#endif // MYSTACK_H
