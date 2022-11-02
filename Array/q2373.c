#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>
#include <stdbool.h>

// q2373_largest-local-values-in-a-matrix
int findMax(int** grid, int nRow, int nCol, int x, int y);

int** largestLocal(int** grid, int nRow, int* gridColSize, int* returnSize, int** returnColumnSizes) {
    int nCol = gridColSize[0];
    int** maxLocal = (int**)calloc(nRow - 2, sizeof(int*));
    for (int i = 0; i < nRow - 2; i++) {
        maxLocal[i] = (int*)calloc(nCol - 2, sizeof(int));
    }
    *returnSize = nRow - 2;
    *returnColumnSizes = (int*)calloc(nRow - 2, sizeof(int)); // 【勿忘malloc】！！

    for (int i = 0; i < nRow - 2; i++) {
        for (int j = 0; j < nCol - 2; j++) {
            maxLocal[i][j] = findMax(grid, nRow, nCol, i, j);
        }
        // (int* 列数)用作返回值：int**，需先malloc！
        (*returnColumnSizes)[i] = nCol - 2;
    }

    return maxLocal;
}

int findMax(int** grid, int nRow, int nCol, int x, int y) {
    int maxVal = 0;
    for (int i = x; i <= x + 2; i++) {
        for (int j = y; j <= y + 2; j++) {
            maxVal = fmax(maxVal, grid[i][j]);
        }
    }
    return maxVal;
}