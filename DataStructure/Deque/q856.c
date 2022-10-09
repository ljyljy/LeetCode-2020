#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>
#include <stdbool.h>

// 法1：分治, 时、空O(n^2), 类比q224、227、856
int scoreOfParentheses(char* s) {
    int n = strlen(s);
    if (n == 2) return 1;
    int cnt = 0, splitIdx = 0;
    for (int i = 0; i < n; i++) {
        cnt += (s[i] == '(') ? 1 : -1;
        if (cnt == 0) {
            splitIdx = i;
            break;
        }
    }
    if (splitIdx == n - 1) {
        char* subStr = (char*)calloc(sizeof(char), n); // v2（荐）： char subStr[n-1];
        strncpy(subStr, s + 1, n - 2), subStr[n - 2] = 0; // subStr[0:n-1]=s[1:n-2]
        // printf("subStr=%s\n", subStr);
        return 2 * scoreOfParentheses(subStr);
    }
    else {
        // lf = s[0:splitIdx], rt=s[splitIdx+1:n-1]
        int len_lf = splitIdx + 1, len_rt = n - 1 - splitIdx;
        // printf("splitIdx=%d, len_lf=%d, len_rt=%d\n", splitIdx, len_lf, len_rt);
        char* lf = (char*)calloc(sizeof(char), n); // v2（荐）：char lf[len_lf], rt[len_rt];
        char* rt = (char*)calloc(sizeof(char), n);
        strncpy(lf, s, len_lf), lf[len_lf] = 0; // strcpy会导致溢出，除非calloc(n+1)字节!！
        strncpy(rt, s + splitIdx + 1, len_rt), rt[len_rt] = 0;
        // printf("lf=%s, rt=%s\n", lf, rt);
        return scoreOfParentheses(lf) + scoreOfParentheses(rt);
    }
}

// todo：v2: 栈，O(n)，类比q1190,、394、856