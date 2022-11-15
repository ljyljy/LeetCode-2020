#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>
#include <stdbool.h>

// q1668_maximum-repeating-substring
#define N 101

int maxRepeating(char* sequence, char* word) {
    int cnt = 0;
    int n = strlen(sequence);
    // ∵连续子串 ∴每轮后缀一个word -> curWord
    char curWord[N] = { 0 }; // char[n+1]会溢出！

    strcat(curWord, word);
    if (word == NULL || *word == 0) return 0;
    char* cur = strstr(sequence, curWord);
    while (cur != NULL) {
        ++cnt;
        strcat(curWord, word);
        cur = strstr(sequence, curWord);
    }
    return cnt;
}