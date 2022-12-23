/*
 * wordcount.cpp
 *
 * @author Cynthia Lee
 * @version 2122-Aut
 *
 * A program to demonstrate the use of Stanford library Map
 * to count the number of times each word occurs in a book
 * and make reports of the counts.
 */

#include <fstream>
#include <iostream>
#include "console.h"
#include "filelib.h"
#include "map.h"
#include "simpio.h"
using namespace std;

void readWordsFromBook(string filename, Vector<string>& words);
void countUniqueWords(Vector<string>& words, Map<string, int>& wordCounts);
void doInitialStatisticsReport(Map<string, int>& wordCounts);
void doInteractiveQueryMode(Map<string, int>& wordCounts);

const int FREQUENCY_THRESHOLD = 100;


/*
 * Counts the words found in a book, then reports in two ways.
 */
int main() {
    Vector<string> words;
    readWordsFromBook("books/hurston.txt", words);
    Map<string, int> wordCounts;
    countUniqueWords(words, wordCounts);
    doInitialStatisticsReport(wordCounts);
    doInteractiveQueryMode(wordCounts);
    return 0;
}

/*
 * Takes vector containing full text (tokenized), and populates a map
 * of words -> word counts.
 * @param words is a Vector of tokens (words) to be counted.
 * @param wordCounts should be initially empty, and will be filled with
 *   associations of words with the count of that word's occurrences in
 *   the file.
 */
void countUniqueWords(Vector<string>& words, Map<string, int>& wordCounts)
{
    for (string word : words) {
        wordCounts[word]++;
    }
}

/*
 * Initial statistical report:
 * * Print all words that appeared in the file at least
 *   FREQUENCY_THRESHOLD times, in alphabetical order.
 * @param wordCounts associates words with the count of that word's
 *   occurrences in the file.
 */
void doInitialStatisticsReport(Map<string, int>& wordCounts) {
    for (string word : wordCounts) {
        if (wordCounts[word] >= FREQUENCY_THRESHOLD) {
            cout << word << " appeared " << wordCounts[word] << " times." << endl;
        }
    }
}

/*
 * Interactive query mode:
 * * The user types a word and we report how many times that word
 *   appeared in the book (repeat in a loop until quit).
 * @param wordCounts associates words with the count of that word's
 *   occurrences in the file.
 */
void doInteractiveQueryMode(Map<string, int>& wordCounts) {
    string userWord = getLine("Enter a word (or enter to quit): ");
    while (userWord != "") {
        int count = 0;
        // Guard against users filling our Map with extra entries
        if (wordCounts.containsKey(userWord)) {
                count = wordCounts[userWord];
        }
        cout << userWord << " appeared " << count << " times." << endl;
        userWord = getLine("Enter a word (or enter to quit): ");
    }
}


/*
 * Read contents of filename, tokenized by whitespace, and store
 * each token as an element of the provided Vector.
 * @param words: the Vector to add all the file's words to.
 */
void readWordsFromBook(string filename, Vector<string>& words)
{
    ifstream infile;
    infile.open(filename);
    string word;
    while (infile >> word) {
        words.add(word);
    }
    infile.close();
}
