#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
// #include <string.h>
// #include <math.h>
// #include <limits.h>

// q54.c
// 1. 二维转一维：idx = i * col + j
// 2. 一维转二维：i = idx / col, j = idx % col
// 3. 去重：visited[idx] = true

static const int _row[4] = { 0, 1, 0, -1 };
static const int _col[4] = { 1, 0, -1, 0 };
static int g_m, g_n;

bool isValid(int x, int y, bool* visited) {
    return 0 <= x && x < g_m && 0 <= y && y < g_n && !visited[x * g_n + y];
}

int* spiralOrder(int** matrix, int m, int* n_cols, int* returnSize) {
    g_m = m; g_n = n_cols[0];
    int* res = (int*)calloc(g_m * g_n, sizeof(int));
    // ↓ 避免dir向上走时，覆盖住mat[0][0], 还需记录visited[idx=二维转一维=i*col+j]
    bool* visited = (bool*)calloc(g_m * g_n, sizeof(bool));
    int row = 0, col = 0, curCnt = 0;
    int dir = 0;
    while (curCnt < g_m * g_n) {
        res[curCnt++] = matrix[row][col];
        visited[row * g_n + col] = true;
        int tryRow = row + _row[dir];
        int tryCol = col + _col[dir];
        if (!isValid(tryRow, tryCol, visited)) {
            dir = (dir + 1) % 4; // dir一直不变，直到出界
        }
        row += _row[dir];
        col += _col[dir];
    }
    free(visited);
    *returnSize = curCnt;
    return res;
}

int main() {
    int m = 3, n = 3;
    int* n_cols = (int*)calloc(m, sizeof(int));
    n_cols[0] = n_cols[1] = n_cols[2] = n;
    int** mat = (int**)calloc(m, sizeof(int*));
    for (int i = 0; i < m; i++) {
        mat[i] = (int*)calloc(n, sizeof(int));
        for (int j = 0; j < n; j++) {
            mat[i][j] = i * n + j + 1;
        }
    }
    int returnSize = 0;
    int* res = spiralOrder(mat, m, n_cols, &returnSize);
    for (int i = 0; i < returnSize; i++) {
        printf("%d ", res[i]);
    }
    printf("\n");
    return 0;
}