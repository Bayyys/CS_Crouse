/*
 * File: main.cpp
 * --------------
 * Blank C++ project configured to use Stanford cslib and Qt
 */

#include "console.h"
#include "simpio.h"
#include "MyStack.h"
#include "MyStackDynamic.h"
#include "testing/SimpleTest.h"
using namespace std;

int main()
{
    if ( runSimpleTests(SELECTED_TESTS) ) {
        return 0;
    }

    MyStack stack;
    StackDynamic dynamicStack;

    for ( int i = 0; i < 10; i++ ) {
        stack.push(i);
        dynamicStack.push(i);
    }

    std::cout << "Stack 1: " << std::endl;
    while( !stack.isEmpty() ) {
        std::cout << stack.pop() << std::endl;
    }

    std::cout << std::endl << "Stack 2: " << std::endl;
    while( !dynamicStack.isEmpty() ) {
        std::cout << dynamicStack.pop() << std::endl;
    }

    return 0;
}
