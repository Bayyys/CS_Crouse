#include <iostream>
#include "console.h"
#include "SimpleTest.h"
#include <fstream>
#include <cstring>

using namespace std;


int main() 
{
    if (runSimpleTests(SELECTED_TESTS)) {
        return 0;
    }

    return 0;
}
