
#include <iostream>
using namespace std;

int* double_the_size(int arr[], int size) {

    int* newArr[size*2];

    for (int i = 0; i < size; i++) {
        newArr[i] = &arr[i];
        cout << *newArr[i];
    }

    for (int i = size; i < size*2; i++) {
        newArr[i] = 0;
        cout << newArr[i];
    }

    int* arrayStart = newArr[0];
    return arrayStart;

}

int main() 
{
    int snow[3] = {1, 2, 3};
    double_the_size(snow, 3);

}