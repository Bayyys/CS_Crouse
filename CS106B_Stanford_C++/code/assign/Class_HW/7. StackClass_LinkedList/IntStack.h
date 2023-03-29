/**
 *
 */
#pragma once

/**
 * @brief 通过链表实现栈这个结构
 */
class IntStack {
public:
    IntStack(); // constructor
    ~IntStack();

    void push(int value);
    int pop();
    bool isEmpty();

private:
    struct Node {
        int data;
        Node* next;
    };

    Node* top;
};
