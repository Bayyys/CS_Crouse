/*
 * coinflips.cpp
 *
 * @author Cynthia Lee
 * @version 2123-Aut
 *
 * Demonstrates combining loops with recursion to generate all possible
 * sequences. The program generates all possible coin flip sequences,
 * i.e., sequences of H(eads) and T(ails).
 */

#include <iostream>
#include "console.h"
#include "vector.h"
#include "SimpleTest.h"
using namespace std;


/* Function Prototypes */
void generateAllSequences(int length, Vector<string>& allSequences);
void generateAllSequences(int length, Vector<string>& allSequences, string sequence);


int main()
{
    if (runSimpleTests(SELECTED_TESTS)) {
        return 0;
    }
    cout << "All done, exiting" << endl;
    return 0;
}


/*
 * Generates all sequences of H/T, and stores them in the provided vector.
 * @param length is the number of coin flips to perform
 * @param allSequences is the vector to fill with sequences
 * @pre the parameter allSequences should be initially empty
 */
void generateAllSequences(int length, Vector<string>& allSequences)
{
    if (length >= 0) {
        string sequence = "";
        generateAllSequences(length, allSequences, sequence);

    }
}

/*
 * Recursive implementation (private)
 * Generates all sequences of H/T, and stores them in the provided vector.
 * @param length is the number of coin flips to perform
 * @param allSequences is the vector to fill with sequences
 * @pre the parameter allSequences should be initially empty
 * @pre the parameter sequence should be empty string in the originating call
 *     to this function, and will fill as the recursion unfolds
 */
void generateAllSequences(int length, Vector<string>& allSequences, string sequence)
{
    // base case:
    // (detect that in-progress sequence is done)
    if (sequence.size() == length) {
        allSequences.add(sequence); // saved this result
        return;
    }

    // TODO: recursive case
    sequence += "H";
    generateAllSequences(length, allSequences, sequence);
    sequence.erase(sequence.size() - 1);
    sequence += "T";
    generateAllSequences(length, allSequences, sequence);
}


/* * * * * * Test Cases * * * * * */

PROVIDED_TEST("Sequences of length 0") {
    Vector<string> allSequences;
    generateAllSequences(0, allSequences);
    EXPECT_EQUAL(allSequences, {""});
}

PROVIDED_TEST("Sequences of length 1") {
    Vector<string> allSequences;
    generateAllSequences(1, allSequences);
    allSequences.sort(); // don't want to assume a certain ordering
    EXPECT_EQUAL(allSequences, {"H", "T"});
}

PROVIDED_TEST("Sequences of length 2") {
    Vector<string> allSequences;
    generateAllSequences(2, allSequences);
    allSequences.sort(); // don't want to assume a certain ordering
    EXPECT_EQUAL(allSequences, {"HH", "HT", "TH", "TT"});
}

