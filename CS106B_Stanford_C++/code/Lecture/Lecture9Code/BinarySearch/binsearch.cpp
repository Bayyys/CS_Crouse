/*
 * binsearch.cpp
 *
 * @author Cynthia Lee, from textbook by Eric Roberts
 * @version 2123-Aut
 *
 * Demonstrates the binary search code implementation from the textbook.
 * Fills an array with random values, and allows user to search for keys
 * using binary search.
 *
 */
#include <iostream>
#include "console.h"
#include "random.h"
#include "vector.h"
#include "SimpleTest.h"
using namespace std;

/* Function Prototypes */
bool binarySearch(Vector<int>& data, int key);
bool binarySearch(Vector<int>& data, int key, int start, int end);
void fillWithRandom(Vector<int>& data);


/*
 * Generate a vector with random data, then sort it, then allow user to
 * search for values using our recursive binary search impelementation.
 */
int main()
{
    if (runSimpleTests(SELECTED_TESTS)) {
        return 0;
    }
    cout << "All done, exiting" << endl;
    return 0;
}

/*
 * Searches the provided vector for the given key.
 * @param data is the vector to search
 * @param key is the key to search for
 * @return true if the key is an element stored in data, else false
 */
bool binarySearch(Vector<int>& data, int key)
{
    return binarySearch(data, key, 0, data.size() - 1); // 2 new params
}

/*
 * Recursive implementation (private)
 * @param start is the index of the first valid array element in the
 *        current searchable region (if there is at least one element in it)
 * @param end is the index of the last valid array element in the current
 *        searchable region (if there is at least one element in it)
 */
bool binarySearch(Vector<int>& data, int key, int start, int end)
{
    if (start > end) {
        return false;
    }
    int mid = (start + end) / 2;
    if (key == data[mid]) {
        return true;
    } else if (key < data[mid]) {
        return binarySearch(data, key, start, mid - 1);
    } else {
        return binarySearch(data, key, mid + 1, end);
    }
}

/*
 * Fills the provided vector with random non-negative integers.
 * @param data is the vector to fill randomly
 * @pre the parameter vector should have its capacity already set to the
 *      desired size
 */
void fillWithRandom(Vector<int>& data)
{
    for (int i = 0; i < data.size(); i++) {
        data[i] = randomInteger(0, INT_MAX);
    }
}


/* * * * * * Test Cases * * * * * */


PROVIDED_TEST("Timing on 10K elements") {
    Vector<int> data(10000);
    fillWithRandom(data);
    data.sort();
    TIME_OPERATION(10000, binarySearch(data, 5)); // 5 is my fav number
}

PROVIDED_TEST("Timing on 100K elements") {
    Vector<int> data(100000);
    fillWithRandom(data);
    data.sort();
    TIME_OPERATION(100000, binarySearch(data, 5));
}

PROVIDED_TEST("Timing on 1M elements") {
    Vector<int> data(1000000);
    fillWithRandom(data);
    data.sort(); // spoiler: this sort takes MUCH longer than the search
    TIME_OPERATION(1000000, binarySearch(data, 5));
}


STUDENT_TEST("Basic correctness: found value") {
    Vector<int> data {1, 3, 5, 7, 9, 11};
    EXPECT(binarySearch(data, 5));
}

STUDENT_TEST("Basic correctness: missing value") {
    Vector<int> data {1, 3, 5, 7, 9, 11};
    EXPECT(!binarySearch(data, 2));
}

STUDENT_TEST("Edge case: found first value") {
    Vector<int> data {1, 3, 5, 7, 9, 11};
    EXPECT(binarySearch(data, 1));
}

STUDENT_TEST("Edge case: found last value") {
    Vector<int> data {1, 3, 5, 7, 9, 11};
    EXPECT(binarySearch(data, 11));
}

STUDENT_TEST("Edge case: smaller than smallest value") {
    Vector<int> data {1, 3, 5, 7, 9, 11};
    EXPECT(!binarySearch(data, 0));
}

STUDENT_TEST("Edge case: greater than greatest value") {
    Vector<int> data {1, 3, 5, 7, 9, 11};
    EXPECT(!binarySearch(data, 12));
}
