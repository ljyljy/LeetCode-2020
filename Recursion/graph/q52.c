#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
// #include <string.h>
// #include <math.h>
// #include <limits.h>

bool cols[10], xyDiff[20], xySum[20];
int curCnt, curLen;

bool isValid(int row, int col, int n) {
    return !(cols[col] || xyDiff[row - col + n] || xySum[row + col]);
}

void dfs(int n, int row, int* path) {
    if (row == n) {
        curCnt++;
        return;
    }

    for (int col = 0; col < n; col++) {
        if (!isValid(row, col, n)) continue;
        cols[col] = xyDiff[row - col + n] = xySum[row + col] = true;
        path[curLen++] = col;
        dfs(n, row + 1, path);
        cols[col] = xyDiff[row - col + n] = xySum[row + col] = false;
        --curLen;
    }
}

int totalNQueens(int n) {
    curCnt = 0, curLen = 0;
    int* path = (int*)calloc(n, sizeof(int));
    dfs(n, 0, path);
    return curCnt;
}

int main() {
    int n = 4;
    int res = totalNQueens(n);
    printf("res = %d\n", res);
    return 0;
}