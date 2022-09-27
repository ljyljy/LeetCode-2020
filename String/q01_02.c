#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <limits.h>
#include <stdbool.h>

#define DIM(arr) sizeof(arr)/sizeof(*arr)

bool allZero(int* cnts, int n);

bool CheckPermutation(char* s1, char* s2) {
    int n = strlen(s1);
    if (n != strlen(s2)) return false;
    int cnts[26] = { 0 };
    // int* cnts = (int*)calloc(26, sizeof(int));
    for (int i = 0; i < n; i++) {
        cnts[s1[i] - 'a']++;
        cnts[s2[i] - 'a']--;
    }
    return allZero(cnts, DIM(cnts));
}

bool allZero(int* cnts, int n) {
    for (int i = 0; i < n; i++) {
        if (cnts[i] != 0) return false;
    }
    return true;
}