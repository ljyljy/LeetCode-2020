#include <stdlib.h>
#include <stdio.h>
#include <string.h>

// 法1：双指针-暴力
int strStr_BF(char* src, char* pattern) {
    int n0 = strlen(src), n1 = strlen(pattern);
    if (n1 == 0) return 0;
    for (int i = 0; i <= n0 - n1; i++) {
        int ii = i, jj = 0;
        while (ii < n0 && jj < n1 && src[ii] == pattern[jj]) {
            ++ii; ++jj;
        }
        if (jj == n1) return i;
    }
    return -1;
}

// 法2：kmp
int strStr(char* src, char* pattern) {
    int n = strlen(src), m = strlen(pattern);
    if (m == 0) return 0;
    char* s = (char*)malloc(sizeof(char) * (n + 2)); // " " + src + "\0"
    char* p = (char*)malloc(sizeof(char) * (m + 2)); // " " + src + "\0"
    s[0] = ' ', p[0] = ' ';
    // WHY + 1? 勿忘最后的'\0'占1B
    // strncpy(s + 1, src, n + 1); strncpy(p + 1, pattern, m + 1); // strcpy(s + 1, src); // v1
    strcpy(s + 1, src); strcpy(p + 1, pattern);  // v2
    // s[1] = '\0'; p[1] = 0; strcat(s, src); strcat(p, pattern); // v3

    // 1) 构造next[]
    int next[m + 1];
    memset(next, 0, sizeof(int) * (m + 1));
    for (int i = 2, j = 0; i <= m; i++) {
        while (j > 0 && p[i] != p[j + 1]) {
            j = next[j];
        }
        if (p[i] == p[j + 1]) j++;
        next[i] = j;
    }
    // 2) 匹配过程 O(n)
    for (int i = 1, j = 0; i <= n; i++) {
        while (j > 0 && s[i] != p[j+1]) {
            j = next[j];
        }
        if (s[i] == p[j + 1]) j++;
        if (j == m) return i - m;
    }
    return -1;
}