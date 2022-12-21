#include <iostream>
#include "vector.h"
#include "console.h"
#include "SimpleTest.h"
using namespace std;

void runInsert(int size);
void runAdd(int size);


int main()
{
    if (runSimpleTests(SELECTED_TESTS)) {
        return 0;
    }

    cout << "All done, exiting" << endl;
    return 0;
}


void runInsert(int size)
{
    Vector<int> v;
    for (int i = 0; i < size; i++) {
        v.insert(0, i);
    }
}

void runAdd(int size)
{
    Vector<int> v;
    for (int i = 0; i < size; i++) {
        v.add(i);
    }
}



/* * * * * * Test Cases * * * * * */

PROVIDED_TEST("Timing comparison of add() at the end and insert() at the beginning") {
    int size = 500000;
    TIME_OPERATION(size, runInsert(size));
    TIME_OPERATION(size, runAdd(size));
}

STUDENT_TEST("") {
}

