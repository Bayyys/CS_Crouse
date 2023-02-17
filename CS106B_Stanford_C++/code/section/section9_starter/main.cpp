#include <iostream>
#include "console.h"
//#include "SimpleTest.h"

using namespace std;


int main() {
//    if (runSimpleTests(SELECTED_TESTS)) {
//        return 0;
//    }
    int count = 0;
    scanf("%d", count);
    for(int i=0; i < count; i++){
        printf("%d\t", i);
    }
    printf("The num you enter is: %d.\n", count);
    return EXIT_SUCCESS;
}
