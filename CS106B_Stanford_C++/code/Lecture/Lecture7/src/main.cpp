#include <iostream>
#include "console.h"
#include "strlib.h"
#include "filelib.h"
#include "simpio.h"
#include <fstream>
#include "stack.h"
#include "queue.h"
#include "set.h"

using namespace std;

void populateDictionary(Set<string>& dictionary, string filename);
void wordLadderProgram();
Stack<string> findWordLadder(string startingWord, string destinationWord, Set<string>& dictionary);
Set<string> generateNeighboringWords(string currentWord, Set<string>& dictionary);



int main() 
{
    wordLadderProgram();

    cout << "All done!" << endl;
    return 0;
}

void wordLadderProgram(){
    cout << "Welcome to the Word Ladder Finder!" << endl;
    cout << "A powerful computer program that leverages many different" << endl;
    cout << "ADTs to solve a fun children's puzzle." << endl;
    cout << "Up, up, and away!" << endl << endl;

    Set<string> dictionary;
    populateDictionary(dictionary, "res/EnglishWords.txt");

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
    return {};
}
