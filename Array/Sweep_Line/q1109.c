#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>

int* corpFlightBookings(int** bookings, int bookingsSize, int* bookingsColSize, int n, int* returnSize) {
    int* diff = (int*)calloc(n + 1, sizeof(int));
    for (int i = 0; i < bookingsSize; i++) {
        int lf = bookings[i][0] - 1, rt = bookings[i][1] - 1;
        int cnt = bookings[i][2];
        diff[lf] += cnt;
        diff[rt + 1] -= cnt;
    }
    int* res = (int*)calloc(n, sizeof(int));
    res[0] = diff[0];
    for (int i = 1; i < n; i++) {
        res[i] = diff[i] + res[i - 1];
    }
    *returnSize = n;
    return res;
}