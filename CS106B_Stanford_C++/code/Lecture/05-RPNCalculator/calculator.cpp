/*
 * calculator.cpp
 *
 * @author Cynthia Lee
 * @version 2022-Aut
 *
 * Demonstrates use of Stack ADT to implement a very simple Reverse Polish
 * Notation (RPN) calculator.
 *
 */
#include <iostream>   // for cout
#include "console.h"  // for Stanford libs
#include "simpio.h"   // for getting input from console
#include "stack.h"    // for parsing RPN notation
#include "strlib.h"   // for converting digit character to int
#include "SimpleTest.h" // for tests
using namespace std;

void printWelcome();
void doOneCalc(string expression);
bool calculate(string expression, int& result);
bool isSupportedOperator(char op);
int  applyOperator(int lhs, char op, int rhs);

/*
 * This program asks user for an input file, in which each line is an
 * arithmetic expression in RPN format, and performs the calculations. After
 * each calculation, the user is asked if they would like to continue.
 */
int main()
{
    if (runSimpleTests(SELECTED_TESTS)) {
        return 0;
    }

    printWelcome();
    bool calcAgain = true;
    while (calcAgain) {
        string expression = getLine("Enter an expression: ");
        doOneCalc(expression);
        calcAgain = getYesOrNo("Do you want another calculation (Y/N)? ");
    }
    return 0;
}

/*
 * Prints out an initial greeting message for the program.
 */
void printWelcome()
{
    cout << "Welcome to the CS106B RPN Calculator!" << endl;
    cout << endl;
    cout << "This program takes arithmetic expressions in RPN " << endl;
    cout << "format, and performs the calculations." << endl;
    cout << endl;
}

/*
 * Parses an arithmetic expression and prints the result.
 * @param expression is an arithmetic expression in RPN form
 * Side effect: prints out the result of the calculation, or an error
 *        if the expression could not be parsed.
 */
void doOneCalc(string expression) {
    int result;
    if (calculate(expression, result)) {
        cout << expression << " = " << result << endl;
    } else {
        cout << "Could not parse expression: " << expression << endl;
    }
}

/*
 * Demonstrates use of a Stack to calculate an RPN arithmetic expression.
 * @param expression is an arithmetic expression in RPN form
 * @param result "returns" the result of the calculation
 * @return true if expression could be parsed and result was set
 *        successfully, else false.
 */
bool calculate(string expression, int& result)
{
    Stack<int> memory;
    // Examine each character of input, left to right
    for (char c : expression) {
        // if digit, store it
        if (isdigit(c)) {
            int value = charToInteger(c);
            memory.push(value);
        // if operator, perform operation
        } else if (isSupportedOperator(c) && memory.size() >= 2) {
            int rhs = memory.pop();
            int lhs = memory.pop();
            memory.push(applyOperator(lhs, c, rhs));
        // otherwise parse error
        } else {
            return false;
        }
    }
    // should be single number in memory, that's our answer
    if (memory.size() != 1) {
        return false;
    }
    result = memory.pop();

    return true;
}

/*
 * Performs a check to make sure we support the arithmetic operator.
 * @param op is a character representation of the operator
 * @return true if op is one of * / + -, otherwise false
 */
bool isSupportedOperator(char op) {
    return op == '*' || op == '/' || op == '+' || op == '-';
}

/*
 * Performs a single arithmetic operator computation.
 * @pre op must be a valid operator (else throws exception)
 * @param lhs is the first or left-hand-side operand
 * @param op is a character representation of the operator
 * @param rhs is the second or right-hand-side operand
 * @return the result of the calculation
 */
int applyOperator(int lhs, char op, int rhs)
{
    switch (op) {
    case '*' :
        return lhs * rhs;
    case '/':
        return lhs / rhs;
    case '+':
        return lhs + rhs;
    case '-':
        return lhs - rhs;
    default:
        error("Invalid operator"); // we should have already checked for this
    }
}


/* * * * * * Test Cases * * * * * */

PROVIDED_TEST("applyOperator") {
    EXPECT_EQUAL(applyOperator(5, '+', 1), 6);
    EXPECT_EQUAL(applyOperator(5, '-', 1), 4);
    EXPECT_EQUAL(applyOperator(5, '/', 2), 2);
    EXPECT_EQUAL(applyOperator(5, '*', 2), 10);
}

PROVIDED_TEST("isSupportedOperator") {
    EXPECT_EQUAL(isSupportedOperator('+'), true);
    EXPECT_EQUAL(isSupportedOperator('-'), true);
    EXPECT_EQUAL(isSupportedOperator('/'), true);
    EXPECT_EQUAL(isSupportedOperator('*'), true);
    EXPECT_EQUAL(isSupportedOperator('^'), false);
    EXPECT_EQUAL(isSupportedOperator('('), false);
    EXPECT_EQUAL(isSupportedOperator('5'), false);
}

PROVIDED_TEST("calculate 11+") {
    int result = 0;
    bool success = false;
    success = calculate("11+", result);
    EXPECT_EQUAL(success, true);
    EXPECT_EQUAL(result, 2);
}

PROVIDED_TEST("calculate 99+7*73+/") {
    int result = 0;
    bool success = false;
    success = calculate("99+7*73+/", result);
    EXPECT_EQUAL(success, true);
    EXPECT_EQUAL(result, 12);
}

PROVIDED_TEST("calculate 11++ (should fail to parse)") {
    int result = 0;
    bool success = false;
    success = calculate("11++", result);
    EXPECT_EQUAL(success, false);
}

STUDENT_TEST("test additional cases not covered by the provided tests") {
}
