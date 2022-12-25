/*************************************************
 * File: LinkedLists.cpp
 *
 * Lots of fucntions that show off the many different ways
 * to do linked list operations!
 */

#include <iostream>
#include <string>
#include "linkedlist.h"
using namespace std;

/* Given a linked list, returns the length of that list. */
/**
 * @brief  lengthOf
 * @param  list
 * @return 返回链表长度，传值而非传递引用
 */
int lengthOf(Node* list) {
    int len = 0;
    while ( list != nullptr ) {
        len += 1;
        list = list->next;
    }
    return len;
}

/* Prints the contents of a linked list, in order. */
/**
 * @brief printList
 * @param list
 */
void printList(Node* list) {
    while (list != nullptr) {
        cout << list->data << endl;
        list = list->next;
    }
}

/* Frees all the memory used by a linked list. */
/**
 * @brief freeList
 * @param list
 */
void freeList(Node* list) {
    while (list != nullptr) {
        /* Store where to go next, since we're about to blow up our linked
         * list Node.
         */
        Node *temp = list->next;
        delete list;
        list = temp;
    }
}

/* Reads a linked list from the user one element at a time, stopping when
 * the user enters an empty list. This returns the list, constructed in
 * reverse order.
 */
Node* readList() {
    Node* result = nullptr;
    while (true) {
        string line = getLine("Next item: ");
        if (line == "") break;

        Node* newNode = new Node;
        newNode->data = line;

        newNode->next = result;
        result = newNode;
    }
    return result;
}

/* Given a linked list, returns the length of that list. Operates recursively. */
int lengthOfRec(Node* list) {
    /* Base Case: The empty list has length zero. */
    if (list == nullptr) return 0;

    /* Recursive Case: If there's a Node at the front of the list, the list
     * has length 1 (for that Node) plus the length of the rest of the list.
     */
    return 1 + lengthOf(list->next);
}

/* Prints the contents of a linked list, in order. Operates recursively. */
void printListRec(Node* list) {
    /* Base Case: There's nothing to print if the list is empty. */
    if (list == nullptr) return;

    /* Recursive Case: Print the first Node, then the rest of the list.
     * Question to ponder: What happens if we swap the next two lines, and why? */
    cout << list->data << endl;
    printList(list->next);
}

/* Frees all the memory used by a linked list. Operates recursively. */
void freeListRec(Node* list) {
    /* Base Case: If the list is empty, there's nothing to do. */
    if (list == nullptr) return;

    /* Recursive Case: Delete the rest of the list, then the first Node.
     *
     * Question to ponder: Why is it a Bad Thing to reorder these lines?
     */
    freeListRec(list->next);
    delete list;
}

/* Appends to a linked list represented by the given head pointer. */
/**
 * @brief prependTo
 * @param list
 * @param data
 */
void prependTo(Node*& list, string data) {
    Node *newNode = new Node;
    newNode->data = data;
    newNode->next = list;
    list = newNode;
}


/* Appends to a linked list represented by the given head pointer. */
/**
 * @brief appendTo
 * @param list
 * @param data
 */
void appendTo(Node*& list, string data) {
    //- 1. create new node:
    Node *newNode = new Node(data, nullptr);

    //- 2. find out last node:
    Node* temp = list;
    while ( temp != nullptr && temp->next != nullptr ) {
        temp = temp->next;
    }

    //- 3. insert new node to last node
    if ( list == nullptr ) {
        list = newNode;
    } else {
        temp->next = newNode;
    }
}

/*
 * This helper function is provided a vector of integer values and
 * returns a pointer to the beginning of a linked list containing
 * those values in the specified order. It uses appendTo();
 */
/**
 * @brief createListWithAppend
 * @param values
 * @return
 */
Node* createListWithAppend(Vector<string> values) {
    // From section 6 utility.cpp
    if (values.isEmpty()) return nullptr;
    Node* head = new Node;
    head->data = values[0];
    head->next  = nullptr;

    for (int i = 1; i < values.size(); i++) {
        appendTo(head, values[i]);
    }
    return head;
}

/*
 * This helper function is provided a vector of integer values and
 * returns a pointer to the beginning of a linked list containing
 * those values in the specified order.
 */
 /**
 * @brief createListWithTailPtr
 * @param values
 * @return
 */
Node* createListWithTailPtr(Vector<string> values) {
    if ( values.isEmpty() ) return nullptr;

    //- 1. create new node:
    Node* list = new Node(values[0], nullptr);
    Node* tail = list;

    //- 2. insert new element into tailNode:
    //- 同一块内存，只需要用new申请一次，用delete释放一次即可，不能重复释放
    //- 即指向同一块内存地址的几个不同指针不能分别释放这块内存，只能由其中一个指针来释放
    //- 在链表结构中，这个工作由专门的 freeList 这个函数来完成。
    for ( int i = 1; i < values.size(); i++ ) {
        Node* temp = new Node(values[i], nullptr);
        tail->next = temp;
        tail = temp;
    }

    return list;
}

/* Adds data to a linked list in alphabetical order. Assumes existing list is already
 * sorted alphabetically. */
/**
 * @brief alphabeticalAdd
 * @param list
 * @param data
 */
void alphabeticalAdd(Node*& list, string data) {
    Node *node = new Node(data, nullptr);

    Node *cur = list;
    Node *prev = nullptr;

    while ( cur != nullptr && cur->data < data ) {
        prev = cur;
        cur = cur->next;
    }

    if ( prev == nullptr ) {
        //- 新元素位于链表开头
        node->next = list;
        list = node;
    } else {
        //- 新元素位于链表中间
        prev->next = node;
        node->next = cur;
    }
}

/* Removes all nodes matching dataToRemove from the passed in list (if the data exists). */
/**
 * @brief remove
 * @param list
 * @param dataToRemove
 */
void remove(Node*& list, string dataToRemove) {
    Node *cur = list;
    Node *prev = nullptr;

    while ( cur != nullptr ) {
        if ( cur->data == dataToRemove ) {
            Node *temp = cur->next;

            if ( prev == nullptr ) {
                list = temp;
            } else {
                prev->next = temp;
            }
            delete cur;
            cur = temp;
        } else {
            prev = cur;
            cur = cur->next;
        }
    }
}
