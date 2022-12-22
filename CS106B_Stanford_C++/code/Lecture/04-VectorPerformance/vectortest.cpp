#include <iostream>
#include "vector.h"
#include "console.h"
#include "SimpleTest.h"
#include <string>
#include "string.h"
#include "strlib.h"
#include "simpio.h"
using namespace std;

void runInsert(int size);
void runAdd(int size);


int main()
{
//    if (runSimpleTests(SELECTED_TESTS)) {
//        return 0;
//    }

//    cout << "This progrom greets with a personalized message" << endl;
//    string name = getLine("please enter your name: ");
//    string food = getLine("Please enter your favorite food: ");
//    cout << "Hello" << name << ", it's so nice to meet you!" << endl;
//    cout << "Would you like some " << food << " to eat?" << endl;

    cout << "It's a program of Compute the Number Square" << endl;
    cout << "You can enter one int_num! And I will tell the square of it!" << endl;
    cout << " " << endl;
    while(true){
        string number;
        cin >> number;
        if(number.find(".") <= number.length()){
            cout << "Please enter a INT_NUM!" << endl;
            continue;
        }
        int num = stringToInteger(number);
        if(0 == num){
            break;
        } else {
            cout << "The square of " << number << " is " << num * num << "." << endl;
        }
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

