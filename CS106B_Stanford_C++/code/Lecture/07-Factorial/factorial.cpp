/*S
 * factorial.cpp
 *
 * @author Cynthia Lee
 * @version 2123-Aut
 *
 * A little "hello world"-level first look at recursion. This program computes
 * the factorial of a non-zero integer input by the user.
 *
 * n! = { 1         if n = 1
 *      { n(n-1)!   otherwise
 *
 * We use this program to demonstrate that the way stack frames push and pop
 * with function call and return is the same for "normal" functions as for
 * recursive functions. This version includes a little "debugging"-style
 * output of the memory addresses where the 'n' parameter for each recursive
 * call stack frame are stored, to see the layout of stack frames in memory
 * matches what we learned about the memory model in lecture.
 */

#include <iostream>
#include "console.h"
#include "simpio.h"
#include "stdio.h"
#include "map.h"
#include <strlib.h>
#include <set>
#include <set.h>
#include <fstream>
#include "filelib.h"
#include "stack.h"
#include "queue.h"

using namespace std;

// Factorial
int myfunction(int x);
int factorial(int n);

// word ladder
void populateDictionary(Set<string>& dictionary, string filename);  // Create Dictionary
void wordLadderProgram();   // Welcome part...
Stack<string> findWordLadder(string startingWord, string destinationWord, Set<string>& dictionary); // Find main...
Set<string> generateNeighboringWords(string currentWord, Set<string>& dictionary);  // Generate Neighbor word

// banana sort
string countingSort(string s){
    Map<char, int> freqMap;
    for(char ch:s){
        freqMap[ch] += 1;
    }

    string sortedString;
    for(char ch = 'a'; ch <= 'z'; ch++){
        // TODO
        if(freqMap.containsKey(ch)){
            while(freqMap[ch]--){
                sortedString.append(charToString(ch));
//                sortedString += charToString(ch);
            }
        }

    }

    return sortedString;

}

void sortBanana(){
    string str = "banana";
    string sortedString = countingSort(str);
    cout << "The sorted string is " << sortedString << endl;
}

int main() {
//    int x = getInteger("Enter an integer > 0: ");
//    while (x > 0) {
//        int answer = myfunction(x);
//        cout << "The answer is: " << answer << endl;
//        x = getInteger("Enter an integer > 0: ");
//    }

//    sortBanana();
    wordLadderProgram();


    return 0;
}

int myfunction(int x){
    int xfac = 0;
    xfac = factorial(x);
    return xfac;
}

int factorial(int n) {    cout << n << " is stored at memory address: " << &n << endl;
    cout << n << endl;
    if (n == 1) {
        return 1;
    } else {
        return n * factorial(n - 1);
    }
}




void wordLadderProgram(){
    cout << "Welcome to the Word Ladder Finder!" << endl;
    cout << "A powerful computer program that leverages many different" << endl;
    cout << "ADTs to solve a fun children's puzzle." << endl;
    cout << "Up, up, and away!" << endl << endl;

    Set<string> dictionary;
    populateDictionary(dictionary, "EnglishWords.txt");

    while (true) {
        string userInput = getLine("Please enter two words that you want to find "
                                   "a word ladder between, separated by a space: ");
        if (userInput == "") break;

        Vector<string> words = stringSplit(userInput, " ");
        Stack<string> wordLadder = findWordLadder(words[0], words[1], dictionary);

        if (wordLadder.isEmpty()){
            cout << "Coudn't find a word ladder between those two words!" << endl;
            continue;
        }

        cout << "Here is the word ladder from " << words[1] << " to " << words[0] << endl;
        while(!wordLadder.isEmpty()){
            cout << wordLadder.pop() << endl;
        }
        cout << endl;

    }

}

void populateDictionary(Set<string>& dictionary, string filename){
    ifstream in;
    openFile(in, filename);
    Vector<string> lines;
    readEntireFile(in, lines);
    for (string word: lines){
        dictionary.add(word);
    }
    cout << "Found " << dictionary.size() << " words in the dictionary file." << endl << endl;
}

Set<string> generateNeighboringWords(string currentWord, Set<string>& dictionary){
    Set<string> neighboringWords;
    for (int i = 0; i < currentWord.length(); i++){
        for (char ch = 'a'; ch <= 'z'; ch++){
            string newWord = currentWord;
            newWord[i] = ch;
            if (dictionary.contains(newWord)){
                neighboringWords.add(newWord);
            }
        }
    }
    return neighboringWords;
}

Stack<string> findWordLadder(string startingWord, string destinationWord, Set<string>& dictionary){
    /* TODO: Implement breadth-first search to find a word ladder. */
    Queue<Stack<string>> allLadders;
    Set<string> visitedWords;

    Stack<string> initialLadder = {startingWord};
    allLadders.enqueue(initialLadder);

    while(!allLadders.isEmpty()){
        Stack<string> currLadders = allLadders.dequeue();
        string currentWord = currLadders.peek();
        if(currentWord == destinationWord){
            return currLadders;
        }
        Set<string> neighbors = generateNeighboringWords(currentWord, dictionary);
        for(string neighbor: neighbors){
            if(!visitedWords.contains(neighbor)){
                Stack<string> newLadder = currLadders;
                newLadder.push(neighbor);
                visitedWords.add(neighbor);
                allLadders.enqueue(newLadder);
            }
        }

    }
    return {};
}
