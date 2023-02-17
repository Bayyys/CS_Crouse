#include <iostream>
#include <vector>
#include <map>

using namespace std;

int bianaryFindNumHelper(vector<int> v, int num, int left, int right);

int bianaryFindNum(vector<int> v, int num){
    return bianaryFindNumHelper(v, num, 0, v.size() - 1);
}

int bianaryFindNumHelper(vector<int> v, int num, int left, int right){
    if (left > right)
    {
        return -1;
    }
    int mid = (left + right) / 2;
    if (v[mid] == num)
    {
        return mid;
    }
    else if (v[mid] > num)
    {
        return bianaryFindNumHelper(v, num, left, mid - 1);
    }
    else
    {
        return bianaryFindNumHelper(v, num, mid + 1, right);
    }
}

int main(int argc, char** argv){
    // int count = 0;
    // cin >> count;
    // for (int i = 0; i < count; i++)
    // {
    //     cout << "current round: " << i << endl;
    // }
    // cout << "........" << "End at num: " << count << endl; 
    vector<int> v;
    if(argc > 1){
        FILE *f = fopen(argv[1], "r");
        if(f == NULL){
            cout << "File not found" << endl;
            return 0;
        }
        while(!feof(f)){
            int num;
            fscanf(f, "%d", &num);
            v.push_back(num);
        }
    }
    else{
    while(true){
            int num;
            cin >> num;
            if (num == 0)
            {
                break;
            }
            v.push_back(num);
        }
    }
    int findNum;
    cout << "Please input the number you want to find: ";
    cin >> findNum;
    int find = bianaryFindNum(v, findNum);


    return 0;
}