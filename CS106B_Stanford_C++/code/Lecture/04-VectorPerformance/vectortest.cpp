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
void personalMessage();
void printSquare();
void vectorTest();
void loopPrintVec(Vector<int> vec);
void forEachLoopPrintVec(Vector<int> vec);
void testEliminateNegativity();
void eliminateNegativity(Vector<int> &vec);

int main()
{
//    if (runSimpleTests(SELECTED_TESTS)) {
//        return 0;
//    }

//    personalMessage();
//    printSquare();
//    vectorTest();
    testEliminateNegativity();


    cout << "All done, exiting" << endl;
    return 0;
}

void eliminateNegativity(Vector<int> &vec){
    for(int i = 0; i < vec.size(); i++){
        if(vec[i] < 0){
            vec[i] = vec[i] * (-1);
        }
    }
}

void testEliminateNegativity(){
    Vector<int> vec = {1, -4, 18, -11};
    eliminateNegativity(vec);
    cout << vec << endl;
}

void vectorTest(){
    Vector<int> vec = {2, 4, 6};
//    cout << vec[3] << endl;   // Vector::operator []: index of 3 is outside of valid range [0..2]
    cout << "The original vector is " << vec << endl;
    vec.remove(1);
    cout << "After remove the index 1 element, the vector is " << vec << endl;
    cout << "The size of vector is " << vec.size() << endl;
    loopPrintVec(vec);
    forEachLoopPrintVec(vec);
}

void forEachLoopPrintVec(Vector<int> vec){
       for(int num: vec){
           cout << num << endl;
       }
}

void loopPrintVec(Vector<int> vec){
       for(int i = 0; i < vec.size(); i++) {
           cout << "Vector[" << i << "] is " << vec[i] << endl;
       }
}

void  personalMessage(){
        cout << "This progrom greets with a personalized message" << endl;
        string name = getLine("please enter your name: ");
        string food = getLine("Please enter your favorite food: ");
        cout << "Hello" << name << ", it's so nice to meet you!" << endl;
        cout << "Would you like some " << food << " to eat?" << endl;
}

void printSquare(){
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

