#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>
#include <stdbool.h>
// q1710_maximum-units-on-a-truck

static inline int cmp(const void* a, const void* b) {
    return (*(int**)b)[1] - (*(int**)a)[1];
}

int maximumUnits(int** boxTypes, int n, int* boxTypesColSize, int truckSize) {
    int maxWeight = 0;
    qsort(boxTypes, n, sizeof(boxTypes[0]), cmp);
    for (int i = 0; i < n; i++) {
        int cnt = boxTypes[i][0];
        int weight = boxTypes[i][1];
        if (truckSize > cnt) {
            maxWeight += cnt * weight;
            truckSize -= cnt;
        }
        else {
            maxWeight += truckSize * weight;
            // truckSize -= truckSize;
            break;
        }
    }
    return maxWeight;
}