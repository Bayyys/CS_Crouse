/*
 * TODO: remove and replace this file header comment
 * This is a .cpp file you will edit and turn in.
 * Remove starter comments and add your own
 * comments on each function and on complex code sections.
 */
#include <cctype>
#include <fstream>
#include <string>
#include "console.h"
#include "strlib.h"
#include "filelib.h"
#include "simpio.h"
#include "vector.h"
#include "SimpleTest.h" // IWYU pragma: keep (needed to quiet spurious warning)
#include "map.h"
#include "simpio.h"
#include <strlib.h>

using namespace std;

/* This function is intended to return a string which
 * includes only the letter characters from the original
 * (all non-letter characters are excluded)
 *
 * WARNING: The provided code is buggy!
 *
 * Use test cases to identify which inputs to this function
 * are incorrectly handled. Then, remove this comment and
 * replace it with a description of the bug you fixed.
 */
string lettersOnly(string s) {
    string result = charToString(s[0]);
    for (int i = 1; i < s.length(); i++) {
        if (isalpha(s[i])) {
            result += s[i];
        }
    }
    return result;
}

void createTable(Map<char, int>& table){
    Vector<string> orignal = {"aeiouhwy", "bfpv",  "cgjkqsxz", "dt", "l", "mn", "r"};
    for(int i = 0; i < orignal.size(); i++){
        for(char ch: orignal[i]){
            table[ch] = i;
        }
    }
}

string strToNum(string str, Map<char, int> table){
    string result = str;
    for(int i = 0; i < str.size(); i++){
        result[i] = integerToChar(table[str[i]]);
    }
    return result;
}

string delRepeat(string str){
    for(int i = str.size() - 1; i >= 0; i--){
        if(str[i] == str[i + 1]){
            str.erase(i, 1);
        }
    }
    return str;
}

string del0(string str){
    string result = "";
    for(char ch: str){
        if(ch != '0'){
            result += ch;
        }
    }
    return result;
}

/* TODO: Replace this comment with a descriptive function
 * header comment.
 */
string soundex(string s) {
    /* TODO: Fill in this function. */
    // Create Letter table
    Map<char, int> table;
    createTable(table);
    // Change str to lower case
    s = toLowerCase(lettersOnly(s));
    string original = s;
    // Change str to num_string
    s = strToNum(s, table);
    // Delete the repeat number
    s = delRepeat(s);
    s[0] = toUpperCase(original[0]);
    // Delete the 0
    s = del0(s);
    string result = "";
    for(int i = 0; i < 4; i++){
        if(s.size() <= i){
            result += "0";
        } else {
            result += s[i];
        }
    }
    return result;
}


/* TODO: Replace this comment with a descriptive function
 * header comment.
 */
void soundexSearch(string filepath) {
    // This provided code opens the specified file
    // and reads the lines into a vector of strings
    ifstream in;
    Vector<string> allNames;

    if (openFile(in, filepath)) {
        readEntireFile(in, allNames);
    }
    cout << "Read file " << filepath << ", "
         << allNames.size() << " names found." << endl;

    // The names read from file are now stored in Vector allNames

    /* TODO: Fill in the remainder of this function. */
    Map<string, Vector<string>> Table;
    for(string name: allNames){
        Table[soundex(name)].add(name);
    }
    while(true){
        string str = getLine("Enter a surname (RETURN to quit): ");
        if(str == "") break;
        string s = soundex(str);
        cout << "Sound code is " << s << endl;
        Vector<string> vec;
        for(string curr: Table[s]){
            if(curr != str){
                vec.add(curr);
            }
        }
        cout << "Matches from database: " << vec << endl;
        cout << endl;
    }
    cout << "All done!" << endl;
}


/* * * * * * Test Cases * * * * * */


PROVIDED_TEST("Test exclude of punctuation, digits, and spaces") {
    string s = "O'Hara";
    string result = lettersOnly(s);
    EXPECT_EQUAL(result, "OHara");
    s = "Planet9";
    result = lettersOnly(s);
    EXPECT_EQUAL(result, "Planet");
    s = "tl dr";
    result = lettersOnly(s);
    EXPECT_EQUAL(result, "tldr");
}


PROVIDED_TEST("Sample inputs from handout") {
    EXPECT_EQUAL(soundex("Curie"), "C600");
    EXPECT_EQUAL(soundex("O'Conner"), "O256");
}

PROVIDED_TEST("hanrahan is in lowercase") {
    EXPECT_EQUAL(soundex("hanrahan"), "H565");
}

PROVIDED_TEST("DRELL is in uppercase") {
    EXPECT_EQUAL(soundex("DRELL"), "D640");
}

PROVIDED_TEST("Liu has to be padded with zeros") {
    EXPECT_EQUAL(soundex("Liu"), "L000");
}

PROVIDED_TEST("Tessier-Lavigne has a hyphen") {
    EXPECT_EQUAL(soundex("Tessier-Lavigne"), "T264");
}

PROVIDED_TEST("Au consists of only vowels") {
    EXPECT_EQUAL(soundex("Au"), "A000");
}

PROVIDED_TEST("Egilsdottir is long and starts with a vowel") {
    EXPECT_EQUAL(soundex("Egilsdottir"), "E242");
}

PROVIDED_TEST("Jackson has three adjcaent duplicate codes") {
    EXPECT_EQUAL(soundex("Jackson"), "J250");
}

PROVIDED_TEST("Schwarz begins with a pair of duplicate codes") {
    EXPECT_EQUAL(soundex("Schwarz"), "S620");
}

PROVIDED_TEST("Van Niekerk has a space between repeated n's") {
    EXPECT_EQUAL(soundex("Van Niekerk"), "V526");
}

PROVIDED_TEST("Wharton begins with Wh") {
    EXPECT_EQUAL(soundex("Wharton"), "W635");
}

PROVIDED_TEST("Ashcraft is not a special case") {
    // Some versions of Soundex make special case for consecutive codes split by hw
    // We do not make this special case, just treat same as codes split by vowel
    EXPECT_EQUAL(soundex("Ashcraft"), "A226");
}

// TODO: add your test cases here


