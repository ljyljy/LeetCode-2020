#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>

int dfs(char* numStr, int n, int(*memo)[n], int idx, int twoCnt, bool isLimit);

int numberOf2sInRange(int num) {
    char* numStr = (char*)malloc(sizeof(char) * 11);
    sprintf(numStr, "%d", num);
    int n = strlen(numStr);
    int memo[n][n]; // <idx, twoCnt>
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            memo[i][j] = -1;
        }
    }
    return dfs(numStr, n, memo, 0, 0, true);
}

int dfs(char* numStr, int n, int(*memo)[n], int idx, int twoCnt, bool isLimit) {
    if (idx == n) return twoCnt;
    if (!isLimit && memo[idx][twoCnt] != -1) {
        return memo[idx][twoCnt];
    }
    int cnt = 0;
    int top = isLimit ? numStr[idx] - '0' : 9;
    for (int bit = 0; bit <= top; bit++) {
        cnt += dfs(numStr, n, memo, idx + 1,
            twoCnt + (bit == 2 ? 1 : 0), isLimit && bit == top);
    }

    if (!isLimit) memo[idx][twoCnt] = cnt;
    return cnt;
}