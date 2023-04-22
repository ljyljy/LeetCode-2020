#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
// #include <math.h>
// #include <limits.h>

/* bool数组代替哈希：
- 题目：1 <= n <= 9，
  - 1）row + col∈[0, 18];
  - 2）row - col∈[-n, n]->下标需要 + n，偏移至[0, 2n]内
*/
bool cols[10], xyDiff[20], xySum[20]; // 用于记录每一列、每一条对角线是否已有皇后
char*** res;
int curCnt, basicSize, curLen;
int* pathLens; // 用于记录每个解的长度, == *returnColumnSizes

void dfs(int n, int row, int* path);
bool isValid(int row, int col, int n);

char*** solveNQueens(int n, int* returnSize, int** returnColumnSizes) {
    basicSize = 8, curCnt = 0, curLen = 0;
    res = (char***)calloc(basicSize, sizeof(char**));
    pathLens = (int*)calloc(basicSize, sizeof(int));
    int* path = (int*)calloc(n, sizeof(int));  // 有误：path[i]中0为有效值，故需要初始化为-1
    // path:各行中 皇后的列号，后续转换为棋盘
    for (int i = 0; i < n; i++) {
        path[i] = -1;
    }

    dfs(n, 0, path);

    *returnSize = curCnt;
    *returnColumnSizes = pathLens;
    free(path);
    return res;
}


void dfs(int n, int row, int* path) {
    if (row == n) {
        res[curCnt] = (char**)calloc(n, sizeof(char*)); // 每个解(res[curCnt])都是一个n*n的棋盘(char**)
        for (int i = 0; i < n; i++) { // 遍历每一行
            res[curCnt][i] = (char*)calloc(n + 1, sizeof(char)); // 每一行，末尾'\0'
            memset(res[curCnt][i], '.', n);
            res[curCnt][i][path[i]] = 'Q';
            res[curCnt][i][n] = '\0';
            // printf("%s \t", res[curCnt][i]);
        }
        pathLens[curCnt++] = n; // curLen == n

        if (curCnt == basicSize) {
            basicSize *= 2;
            res = (char***)realloc(res, basicSize * sizeof(char**));
            pathLens = (int*)realloc(pathLens, basicSize * sizeof(int));
        }
        return;
    }

    for (int col = 0; col < n; col++) {
        if (!isValid(row, col, n)) continue;
        cols[col] = xyDiff[row - col + n] = xySum[row + col] = true;
        path[curLen++] = col; // path[row] = col
        dfs(n, row + 1, path);
        // path[--curLen] = -1; // v1：回溯, 恢复无效值-1
        curLen--; // v2：回溯, 下次path直接对curLen处覆盖
        cols[col] = xyDiff[row - col + n] = xySum[row + col] = false;
    }
}

bool isValid(int row, int col, int n) {
    // xyDiff∈[-n,n]需要下标偏移，保证idx∈[0, 2n]内
    return !(cols[col] || xyDiff[row - col + n] || xySum[row + col]);
}

int main() {
    int n = 4;
    int returnSize;
    int* returnColumnSizes;
    char*** res = solveNQueens(n, &returnSize, &returnColumnSizes);

    for (int i = 0; i < returnSize; i++) {
        for (int j = 0; j < n; j++) {
            printf("%s \t", res[i][j]);
        }
        printf("\n");
    }
    return 0;
}