#include <stdio.h>
#include <stdlib.h>
#include <math.h>
// #include <string.h>
// #include <limits.h>
#include <stdbool.h>


// q1048. 最长字符串链
// cmp: 按(char*) str长度升序
// 对比成员pa/pb: char*类型，需强转&解引用
static inline int cmp(const char* pa, const char* pb) {
    return strlen(*(char**)pa) - strlen(*(char**)pb);
}

bool isPrev(char* pat, char* str) { // pat: 子串/模式串
    int n1 = strlen(pat), n2 = strlen(str);
    if (n1 != n2 - 1) return false; // 【勿漏！】
    int i = 0, j = 0;
    while (i < n1 && j < n2) { // 遍历j: str
        if (pat[i] == str[j]) {
            i++; // 依次匹配i: pattern
        }
        j++;
    }
    return i == n1;
}

int longestStrChain(char** words, int n) {
    qsort(words, n, sizeof(*words), cmp); // 按str长度升序
    int dp[n]; // chainLens
    for (int i = 0; i < n; i++) {
        dp[i] = 1;
    }

    int maxLen = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < i; j++) { // dp正向遍历，只能搜索i之前的
            if (isPrev(words[j], words[i])) {
                dp[i] = fmax(dp[i], dp[j] + 1);
            }
        }
        maxLen = fmax(maxLen, dp[i]);
    }
    return maxLen;
}

int main() {
    char* words[] = { "a", "b", "ba", "bca", "bda", "bdca" };
    int n = sizeof(words) / sizeof(words[0]);
    printf("%d\n", longestStrChain(words, n)); // 4

    char* words2[] = { "xbc", "pcxbcf", "xb", "cxbc", "pcxbc" };
    int n2 = sizeof(words2) / sizeof(words2[0]);
    printf("%d\n", longestStrChain(words2, n2)); // 5
}