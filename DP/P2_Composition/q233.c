#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>
#include <stdbool.h>

int dfs(char* numStr, int n, int(*memo)[10], int idx, int oneCnt, bool isLimit);

int countDigitOne(int num) {
    // num∈[0, 1e9], 最多占11位='1' + 9*'0' + '\0'
    // 数字 -> 字符串
    char* numStr = (char*)malloc(sizeof(char) * 11);
    // itoa(num, numStr, 10); // v1：itoa, LeetCode不能用
    sprintf(numStr, "%d", num); // v2：sprintf

    int n = strlen(numStr);
    int memo[n][10]; // <idx, oneCnt>
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < 10; j++) {
            memo[i][j] = -1;
        }
    }
    return dfs(numStr, n, memo, 0, 0, true);
}

int dfs(char* numStr, int n, int(*memo)[10], int idx, int oneCnt, bool isLimit) {
    if (idx == n) return oneCnt;
    if (!isLimit && memo[idx][oneCnt] != -1) return memo[idx][oneCnt];
    int cnt = 0;
    int top = isLimit ? numStr[idx] - '0' : 9;
    for (int bit = 0; bit <= top; bit++) {
        cnt += dfs(numStr, n, memo, idx + 1,
            oneCnt + (bit == 1 ? 1 : 0), isLimit && bit == top);
    }
    // 若有limit，则不记录memo！！！
    if (!isLimit) memo[idx][oneCnt] = cnt;
    return cnt;
}

int main() {
    // int -> str (ascii)
    // int num = 123;
    // char* numStr;
    // itoa(num, numStr, 10); // val, char*指针, 10进制
    // printf("numStr = %s\n", numStr);

    int num = 999999;
    int cnt = countDigitOne(num);
    printf("cnt = %d\n", cnt);
    return 0;
}