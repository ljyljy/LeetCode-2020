#include <stdio.h>
#include <stdlib.h>
// #include <string.h>
// #include <stdbool.h>
// #include <math.h>
// #include <limits.h>

int* getRow(int rowIndex, int* returnSize) {
    int n = rowIndex + 1;
    int** res = (int**)calloc(n, sizeof(int*));
    int* n_cols = (int*)calloc(n, sizeof(int));
    *returnSize = n;

    for (int i = 0; i < n; i++) { // 遍历每行
        n_cols[i] = i + 1; // 每行元素个数 = 行数i + 1
        res[i] = (int*)calloc(n_cols[i], sizeof(int));
        res[i][0] = res[i][n_cols[i] - 1] = 1; // 首尾为1

        for (int j = 1; j < n_cols[i] - 1; j++) {
            res[i][j] = res[i - 1][j - 1] + res[i - 1][j];
        }
    }
    return res[rowIndex];
}

int main()
{
    int rowIndex = 3;
    int returnSize = 0;
    int* res = getRow(rowIndex, &returnSize);
    for (int i = 0; i < returnSize; i++) {
        printf("%d ", res[i]);
    }
    return 0;
}