/*
 * CS106B Section Handout Test Harness: Section 1
 * ----------------------------------------------
 * These problems have been galvanized from years of
 * section handouts that have been worked on by numerous
 * instructors and TA's. Codified by Trip Master and Nick * Bowman for CS106B Spring 2020. *
 * A huge thank you to Keith Schwarz and Julie Zelenski
 * for creating an amazing testing harness! */
#include <iostream>
#include "grid.h"
#include "SimpleTest.h"
#include "filelib.h"
#include <string.h>
#include "strlib.h"
#include <vector>
using namespace std;

/*
 * Sum Numbers (Code Write)
 * ----------------------------------
 * Write a program to read through a given file and sum
 * all of the numbers in the file. You can assume that numbers
 * will be composed entirely of numerical digits, optionally
 * preceded by a single negative sign.
 */
bool isNumber(string word){
    if(word.length() > 0 && word[0] == '-'){
        word = word.substr(1);
    }
    for(char letter: word){
       if(!isdigit(letter)) {
           return false;
       }
    }
    return true;
}

int sumNumbers(string filename) {
    ifstream in;
    in.open(filename);
    string line;
    int sum = 0;
    while(in){
        getline(in, line);
        Vector<string> words= stringSplit(line, " ");
        cout << words << endl;
        for(string word: words){
            if(isNumber(word)){
                sum += stringToInteger(word);
            }
        }
    }

    return sum;
}


/* * * * * Provided Tests Below This Point * * * * */
PROVIDED_TEST("given file in handout") {
    EXPECT_EQUAL(sumNumbers("res/numbers.txt"), 42);
}

PROVIDED_TEST("Nonexistent file should sum to zero") {
    EXPECT_EQUAL(sumNumbers("nonexistent_filename"), 0);
}

