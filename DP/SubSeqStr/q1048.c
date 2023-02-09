#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>

// 按str长度升序
static inline int cmp(const char* a, const char* b) {
    return strlen(*(char**)a) - strlen(*(char**)b);
}

bool isPrev(char* pat, char* str) { // pat: 子串/模式串
    int n1 = strlen(pat), n2 = strlen(str);
    if (n1 != n2 - 1) return false;
    int i = 0, j = 0;
    while (i < n1 && j < n2) {
        if (pat[i] == str[j]) i++;
        j++;
    }
    return i == n1;
}

int longestStrChain(char** words, int wordsSize) {
    qsort(words, wordsSize, sizeof(char*), cmp);
    int dp[wordsSize];
    for (int i = 0; i < wordsSize; i++) {
        dp[i] = 1;
    }


    int maxLen = 1;
    for (int i = 0; i < wordsSize; i++) {
        for (int j = 0; j < i; j++) { // 遍历前面可能的子串
            if (isPrev(words[j], words[i])) {
                dp[i] = fmax(dp[i], dp[j] + 1);
            }
        }
        maxLen = fmax(maxLen, dp[i]);
    }
    return maxLen;
}