/**
 * 词梯（Word Ladder）是刘易斯·卡罗尔发明的一种单词游戏。
 * 游戏会给出一个开始单词和结束单词。玩家需要更改开始单词中的某个字母，
 * 获得一个新词，计作一步。然后玩家需要再次更改所得新词中的某个字母，
 * 获得另一个新词。最终获得结束单词，游戏结束。
 */

#include "main.h"
#include <fstream>
#include <iostream>
using namespace std;

/**
 * @brief findWordLadder - 实现一个单词阶梯查找算法
 *
 * 例如：开始单词 cold 和结束单词 warm 可以得到如下结果。
 *
 * COLD -> CORD -> CARD -> WARD -> WARM
 *
 * @param start
 * @param end
 * @return 将计算出的单词阶梯以栈的形式保存
 */
Queue<Stack<string> > findWordLadder(string start, string end) {
    Queue<Stack<string> > result;
    Queue<Stack<string> > allLadders;
    Stack<string> ladder;

    //- 从起点开始
    ladder.push(start);
    allLadders.enqueue(ladder);

    //- 当队列中还有元素时
    while( !allLadders.isEmpty() ) {
        //- 取出队列头部的词梯
        Stack<string> curLadder = allLadders.dequeue();
        string curWord = curLadder.peek();

        //- 当前梯子已经包含哪些单词，注意：当前路径已访问的单词
        //- 对于其他路径来说是未曾访问过的单词，因此不能记录所有
        //- 路径已经访问过哪些单词，只能记录当前路径已访问过哪些单词。
        Set<string> visitedWord = visitedWords(curLadder);

        //- 如果这个梯子的第一个元素等于终点元素，
        //- 则说明当前梯子已经到达终点，返回当前梯子即可
        //- 也可以用这个算法找到所有可能的路径。
        if ( curWord == end ) {
            // return curLadder;
            result.enqueue(curLadder);
        } else {
            //- 如果没有走到终点，则寻找当前位置可以向前移动的所有可能位置：
            Set<string> possibleWords = nextWords(curWord);

            //- 逐个处理这些可能的位置
            while ( !possibleWords.isEmpty() ) {
                string word = possibleWords.first();
                possibleWords.remove(word);

                //- 如果这个单词是当前梯子未曾访问过的单词
                if ( !visitedWord.contains(word) ) {
                    //- 则创建一条属于该单词的的新路径,
                    //- 新路径应当建立在当前路径上
                    //- 最后将这条新路径保存到所有可能路径中
                    //- 此处不将word加入已访问列表好像也没关系，
                    //- 因为下一步的所有可能单词本身就是不重复的。
                    Stack<string> possibleLadder = curLadder;
                    possibleLadder.push(word);
                    allLadders.enqueue(possibleLadder);
                    visitedWord.add(word);
                }
            }
        }
    }
    return result;
}

// ******************* 无需修改以下代码 **********************

int main() {
    // 初始化单词表
    ifstream fin("res/SelectedWords.txt");
    string word;
    while (fin >> word) {
        dictionary.add(word);
    }

    // 程序交互部分
    while (true) {
        string start = getLine("输入开始单词: ");
        if (!start.size())
            break;
        string end = getLine("输入结束单词: ");
        if (!end.size())
            break;

        Queue<Stack<string> > wordLadder = findWordLadder(toLowerCase(start), toLowerCase(end));

        if (wordLadder.isEmpty()) {
            cout << "未找到单词阶梯！" << endl;
            continue;
        }

        cout << "以下是从 " << start << " 到 " << end << " 生成的词梯：" << endl;
        while (!wordLadder.isEmpty()) {
            cout << wordLadder.dequeue() << endl;
        }
        cout << endl;
    }

    cout << "未获取输入，游戏结束！" << endl;
    return 0;
}

/**
 * @brief visitedWords - 由于栈无法遍历查询，转换为集合类型
 * @param currentladder
 * @return 以集合的形式返回栈中包含的所有单词
 */
Set<string> visitedWords(Stack<string> currentLadder) {
    Set<string> visited;
    while (!currentLadder.isEmpty()) {
        visited.add(currentLadder.pop());
    }
    return visited;
}

/**
 * @brief nextWords - 根据提供的 currentWord 找出与其仅一字之差的单词。
 *
 * 例如：cat 对应的单词有 bat、eat、cot、cab、car 等等。
 *
 * @param currentWord
 * @return 将所有找出的单词以集合的形式返回
 */
Set<string> nextWords(string currentWord) {
    Set<string> neighboringWords;
    for (int i = 0; i < currentWord.length(); i++) {
        for (char ch = 'a'; ch <= 'z'; ch++) {
            string newWord = currentWord;
            newWord[i] = ch;
            if (dictionary.contains(newWord)) {
                neighboringWords.add(newWord);
            }
        }
    }
    return neighboringWords;
}
