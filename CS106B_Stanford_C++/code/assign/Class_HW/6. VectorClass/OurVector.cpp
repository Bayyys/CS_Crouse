// OurVector.cpp
// Implementation of OurVector class

#include "error.h"
#include "strlib.h"
#include "OurVector.h"
#include "testing/SimpleTest.h"

const int INITIAL_CAPACITY = 8;

// constructor
OurVector::OurVector(){
   allocatedSize = INITIAL_CAPACITY;
   logicalSize = 0;
   elems = new int[allocatedSize];
}

// destructor
OurVector::~OurVector(){
   delete [] elems;
}

// append value to the end of our array
void OurVector::add(int value){
   if ( logicalSize == allocatedSize ) {
       expand();
   }
   elems[logicalSize] = value;
   logicalSize += 1;
}

// insert value at index
void OurVector::insert(int index, int value){
    if ( index < 0 || index > logicalSize ) {
        error("The index is out of range.");
    }

    if ( logicalSize == allocatedSize ) {
        expand();
    }

    //- move backward
    for ( int i = logicalSize; i > index; i-- ) {
        elems[i] = elems[i - 1];
    }
    elems[index] = value;
    logicalSize += 1;
}


// remove value from index
void OurVector::remove(int index){
    if ( index < 0 || index >= logicalSize ) {
        error("The index is out of range.");
    }

    for ( int i = index; i < logicalSize - 1; i++ ) {
        elems[i] = elems[i + 1];
    }
    logicalSize -= 1;
}

// return the element at index
int OurVector::get(int index){
    if ( index < 0 || index >= logicalSize ) {
        error("The index is out of range.");
    }

    return elems[index];
}

// returns the number of elements
int OurVector::size(){
   return logicalSize;
}

// returns true if there aren't any elements
bool OurVector::isEmpty(){
   return size() == 0;
}

void OurVector::printDebugInfo(){
    cout << "Num items: " << logicalSize << " Allocated capacity: " << allocatedSize << endl;
        cout << "{";
        for (int i = 0; i < logicalSize; i++) {
            cout << i;
            if (i != logicalSize - 1) cout << ", ";
        }
        cout << "}" << endl;
}

void OurVector::expand() {
   allocatedSize *= 2;
   int *helper = new int[allocatedSize];

   for ( int i = 0; i < logicalSize; i++ ) {
       helper[i] = elems[i];
   }

   delete [] elems;

   elems = helper;
}


/* * * * * Test cases * * * * * * */

PROVIDED_TEST("Construct vector and see constructor/destructor be called."){
    OurVector vec;
}

PROVIDED_TEST("Check that newly created vector is empty"){
    OurVector vec;
    EXPECT_EQUAL(vec.isEmpty(), true);
}

PROVIDED_TEST("Add 5 elements to vector."){
    OurVector vec;
    for (int i = 0; i < 5; i++){
        vec.add(i);
    }

    EXPECT_EQUAL(vec.size(), 5);
    EXPECT_EQUAL(vec.isEmpty(), false);
    cout << endl;
    vec.printDebugInfo();
}

PROVIDED_TEST("Add 5 elements and then insert another element at index 3."){
    OurVector vec;
    for (int i = 0; i < 5; i++){
        vec.add(i);
    }

    vec.insert(3, 100);
    EXPECT_EQUAL(vec.size(), 6);
    EXPECT_EQUAL(vec.get(3), 100);

    cout << endl;
    vec.printDebugInfo();
}

PROVIDED_TEST("Add 5 elements and then remove element at index 3."){
    OurVector vec;
    for (int i = 0; i < 5; i++){
        vec.add(i);
    }

    vec.remove(3);
    EXPECT_EQUAL(vec.size(), 4);
    EXPECT_EQUAL(vec.get(3), 4);

    cout << endl;
    vec.printDebugInfo();
}

PROVIDED_TEST("Add 5 elements and then do an insertion at the very beginning and very end of vector."){
    OurVector vec;
    for (int i = 0; i < 5; i++){
        vec.add(i);
    }

    vec.insert(0, 200);
    EXPECT_EQUAL(vec.size(), 6);
    EXPECT_EQUAL(vec.get(0), 200);
    for (int i = 0; i < 5; i++){
        EXPECT_EQUAL(vec.get(i+1), i);
    }
    cout << endl;
    vec.printDebugInfo();

    vec.insert(6, 300);
    EXPECT_EQUAL(vec.size(), 7);
    EXPECT_EQUAL(vec.get(0), 200);
    for (int i = 0; i < 5; i++){
        EXPECT_EQUAL(vec.get(i+1), i);
    }
    EXPECT_EQUAL(vec.get(6), 300);
    vec.printDebugInfo();
    vec.insert(6, 200);
    vec.insert(6, 150);
}

PROVIDED_TEST("Add 50 elements. Tests expansion (twice)"){
    OurVector vec;
    for (int i = 0; i < 50; i++){
        vec.add(i);
    }

    EXPECT_EQUAL(vec.size(), 50);
    for (int i = 0; i < 50; i++){
        EXPECT_EQUAL(vec.get(i), i);
    }
    EXPECT_EQUAL(vec.isEmpty(), false);
    cout << endl;
    vec.printDebugInfo();
}
