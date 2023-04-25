#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
// #include <string.h>
// #include <math.h>
// #include <limits.h>

// q59.c
/*
- 二维转一维：idx = i * col + j
    -  一维转二维：i = idx / col, j = idx % col
- 去重(避免螺旋上走时覆盖res[0][0])：
    -  visited[idx] = true
-  螺旋式遍历，故不可用双层for，需用for-dir控制row & col
    -  for-dir是无限循环（if dir++, 有条件）, 限num > n*n时退出
*/
int** res;
int* n_cols;
int curCnt;
bool* visited;
static const int _row[] = { 0, 1, 0, -1 };
static const int _col[] = { 1, 0, -1, 0 };

bool isValid(int x, int y, int n) {
    return 0 <= x && x < n && 0 <= y && y < n && !visited[x * n + y];
}

int** generateMatrix(int n, int* returnSize, int** returnColumnSizes) {
    res = (int**)calloc(n * n, sizeof(int*));
    n_cols = (int*)calloc(n, sizeof(int));
    // curCnt = n;
    for (int i = 0; i < n; i++) {
        res[i] = (int*)calloc(n, sizeof(int));
        n_cols[i] = n;
    }
    visited = (bool*)calloc(n * n, sizeof(bool));

    // 螺旋式遍历，故不可用双层for，需用for-dir控制row & col
    int row = 0, col = 0, num = 1;

    for (int dir = 0; dir < 4; ) { // 限num > n*n时退出，否则无限循环！
        res[row][col] = num++;
        // printf("res[%d][%d] = %d\n", row, col, res[row][col]);
        visited[row * n + col] = true;

        int tryRow = row + _row[dir];
        int tryCol = col + _col[dir];
        if (!isValid(tryRow, tryCol, n)) {
            dir = (dir + 1) % 4;
        }
        row += _row[dir];
        col += _col[dir];
        if (num > n * n) break;
    }
    free(visited);
    *returnSize = n;
    *returnColumnSizes = n_cols;
    return res;
}

int main() {
    int n = 3;
    int returnSize = 0;
    int** returnColumnSizes = (int**)calloc(n, sizeof(int*));
    int** res = generateMatrix(n, &returnSize, returnColumnSizes);
    for (int i = 0; i < returnSize; i++) {
        for (int j = 0; j < returnColumnSizes[0][i]; j++) {
            printf("%d ", res[i][j]);
        }
        printf("\n");
    }
    return 0;
}