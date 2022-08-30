#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>
#include <stdbool.h>

int shortestDistance(char** wordsDict, int n, char* word1, char* word2) {
    int res = n;
    int idx1 = -1, idx2 = -1;
    for (int i = 0; i < n; i++) {
        char* word = wordsDict[i];
        if (strcmp(word, word1) == 0) {
            idx1 = i;
        }
        else if (strcmp(word, word2) == 0) {
            idx2 = i;
        }
        if (idx1 >= 0 && idx2 >= 0) {
            res = fmin(res, abs(idx1 - idx2));
        }
    }
    return res;
}