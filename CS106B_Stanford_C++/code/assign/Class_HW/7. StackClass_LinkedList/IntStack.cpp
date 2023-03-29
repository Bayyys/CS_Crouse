#include "IntStack.h"
#include "testing/SimpleTest.h"
using namespace std;

/**
 * @brief 构造函数
 */
IntStack::IntStack() {
    top = nullptr;
}

/**
 * @brief IntStack::~IntStack
 */
IntStack::~IntStack() {
    //- 遍历每个指针，将其占用的内存释放并回收
    while ( top != nullptr ) {
        Node *temp = top;
        top = top->next;
        delete temp;
    }
}

/**
 * @brief IntStack::push
 * @param value
 */
void IntStack::push(int value) {
    //- 声明一个新指针来存放刚插入的元素
    Node *temp = new Node;
    temp->data = value;
    temp->next = top;
    //- 头指针指向新元素
    top = temp;
}

/**
 * @brief IntStack::pop
 * @return
 */
int IntStack::pop() {
    if (isEmpty()) {
        error("Trying to pop from an empty stack.");
    }
    int value = top->data;

    Node *temp = top;
    top = top->next;
    delete temp;

    return value;
}

/**
 * @brief IntStack::isEmpty
 * @return
 */
bool IntStack::isEmpty(){
    return top == nullptr;
}


PROVIDED_TEST("Reverse simple set of values"){
    IntStack stack;

    for (int i = 0; i < 10; i++){
        stack.push(i);
    }

    for (int i = 9; i >= 0; i--){
        EXPECT_EQUAL(i, stack.pop());
    }
}
