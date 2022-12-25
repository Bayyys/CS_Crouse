#include <iostream>
#include "console.h"
#include "testing/SimpleTest.h"
#include "simpio.h"
#include "linkedlist.h"
using namespace std;

int main() {
    if (runSimpleTests(SELECTED_TESTS)){
        return 0;
    }

    return 0;
}

/* * * * * * * Test cases * * * * * * * * */

PROVIDED_TEST("Test of lengthOf, printList, freeList"){
    Node* list = new Node("first", new Node("second", nullptr));
    cout << "Simple list has " << lengthOf(list) << " elements in it!" << endl;
    printList(list);
    freeList(list);
}


PROVIDED_TEST("Test of prependTo"){
    Node* list = nullptr;
    prependTo(list, "Trip");
    prependTo(list, "Kylie");
    prependTo(list, "Jenny");
    prependTo(list, "Mike");
    cout << "Simple list has " << lengthOf(list) << " elements in it!" << endl;
    printList(list);
    freeList(list);
}


PROVIDED_TEST("Test of appendTo"){
    Node* list = nullptr;
    appendTo(list, "Trip");
    appendTo(list, "Kylie");
    appendTo(list, "Jenny");
    appendTo(list, "Mike");
    cout << "Simple list has " << lengthOf(list) << " elements in it!" << endl;
    printList(list);
    freeList(list);
}


PROVIDED_TEST("Test of createListWithAppend"){
    Node* list1 = createListWithAppend({"Jenny", "Kylie", "Trip"});
    cout << "List 1 has " << lengthOf(list1) << " elements in it!" << endl;
    printList(list1);
    freeList(list1);
}


PROVIDED_TEST("Test of createListWithTailPtr"){
    cout << endl << endl;
    Node* list = createListWithTailPtr({"Lions", "Tigers", "Bears", "Oh My!"});
    cout << "List 2 has " << lengthOf(list) << " elements in it!" << endl;
    printList(list);
    freeList(list);
}


PROVIDED_TEST("Test of createListWithTailPtr, alphabeticalAdd, remove function"){
    cout << endl << endl;
    Node* head = createListWithTailPtr({"Bananas", "Dragonfruit", "Yuzu"});
    cout << "State of the list initially: " << endl;
    printList(head);
    cout << endl;

    /* Insert into middle of list. */
    alphabeticalAdd(head, "Oranges");
    alphabeticalAdd(head, "Coconuts");

    /* Insert into beginning and end of list. */
    alphabeticalAdd(head, "Apricots");
    alphabeticalAdd(head, "Zucchini"); /* Technically a fruit! */

    cout << "State of the list after insertions: " << endl;
    printList(head);
    cout << endl;

    /* Remove from the middle of the list. */
    remove(head, "Oranges");
    remove(head, "Coconuts");

    /* Remove from the beginning and end of the list. */
    remove(head, "Apricots");
    remove(head, "Zucchini");

    cout << "State of the list after removals: " << endl;
    printList(head);
    freeList(head);
}


PROVIDED_TEST("Timing Test to compare appending speed without tail pointer"){
    int startSize = 10000;
    for (int size = startSize; size < 10 * startSize; size *= 2){
        Vector<string> vals(size);
        Node* list1;
        TIME_OPERATION(size, list1 = createListWithAppend(vals));
        freeList(list1);
    }
}


PROVIDED_TEST("Timing Test to compare appending speed with tail pointer"){
    int startSize = 10000;
    for (int size = startSize; size < 20 * startSize; size *= 2){
        Vector<string> vals(size);
        Node* list2;
        TIME_OPERATION(size, list2 = createListWithTailPtr(vals));
        freeList(list2);
    }
}

