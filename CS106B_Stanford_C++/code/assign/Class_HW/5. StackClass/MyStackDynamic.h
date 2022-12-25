/*
 * Filename: StackDynamic.h
 * --------------------------
 * Declare of StackDynamic class
 *
 */
#ifndef StackDynamic_H
#define StackDynamic_H

class StackDynamic{
private:
    //- Data member:
    int allocatedSize;
    int logicalSize;
    int *elements;

    //- function member:
    void grow();

public:
    StackDynamic();
    ~StackDynamic();

    void push(int value);
    int pop();
    int peek() const;

    int size() const;
    bool isEmpty() const;
};

#endif // StackDynamic_H
