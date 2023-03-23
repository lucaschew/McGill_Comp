
#include <iostream>
using namespace std;

void calcualteSnow(float snowfall[12]) {

    float* highestSnowfall = &snowfall[0];
    int highestSnowfallPos = 0;

    float* leastSnowfall = &snowfall[0];
    int leastSnowfallPos = 0;

    float totalSnowfall = 0;

    for (int i = 0; i < 12; i++) {

        if (snowfall[i] > *highestSnowfall) {
            highestSnowfall = &snowfall[i];
            highestSnowfallPos = i;
        }

        if (snowfall[i] < *leastSnowfall) {
            leastSnowfall = &snowfall[i];
            leastSnowfallPos = i;
        }

        totalSnowfall += snowfall[i];

    }

    cout << "Month " << highestSnowfallPos+1 << " has the highest snowfall of " << *highestSnowfall << endl;
    cout << "The average monthly snowfall is " << totalSnowfall/12 << endl;
    cout << "Month " << leastSnowfallPos+1 << " has the least snowfall of " << *leastSnowfall << endl;
    cout << "The total snowfall is " << totalSnowfall << endl;

}

int main() 
{
    float snow[12] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    calcualteSnow(snow);

}