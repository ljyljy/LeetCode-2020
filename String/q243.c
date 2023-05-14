#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>
// #include <stdbool.h>

int shortestDistance(char** wordsDict, int wordsDictSize, char* word1, char* word2) {
    int idx1 = -1, idx2 = -1;
    int minDist = INT_MAX;
    for (int i = 0; i < wordsDictSize; i++) {
        if (strcmp(wordsDict[i], word1) == 0) {
            idx1 = i;
        }
        else if (strcmp(wordsDict[i], word2) == 0) {
            idx2 = i;
        }
        if (idx1 != -1 && idx2 != -1) {
            minDist = fmin(minDist, abs(idx1 - idx2));
        }
    }
    return minDist;
}

int main()
{
    char* wordsDict[] = { "practice", "makes", "perfect", "coding", "makes" };
    int wordsDictSize = sizeof(wordsDict) / sizeof(wordsDict[0]);
    char* word1 = "coding";
    char* word2 = "practice";
    int minDist = shortestDistance(wordsDict, wordsDictSize, word1, word2);
    printf("%d\n", minDist);  // 3
    return 0;
}