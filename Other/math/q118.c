#include <stdio.h>
#include <stdlib.h>
// #include <string.h>
// #include <stdbool.h>
// #include <math.h>
// #include <limits.h>

int** generate(int numRows, int* returnSize, int** returnColumnSizes) {
    int** res = (int**)calloc(numRows, sizeof(int*));
    int* n_cols = (int*)calloc(numRows, sizeof(int));
    *returnSize = numRows;

    for (int i = 0; i < numRows; i++) { // 遍历每行
        n_cols[i] = i + 1; // 每行元素个数 = 行数i + 1
        res[i] = (int*)calloc(n_cols[i], sizeof(int));
        res[i][0] = res[i][n_cols[i] - 1] = 1; // 首尾为1

        for (int j = 1; j < n_cols[i] - 1; j++) {
            res[i][j] = res[i - 1][j - 1] + res[i - 1][j];
        }
    }
    *returnColumnSizes = n_cols;
    return res;
}

int main()
{
    int numRows = 5;
    int returnSize = 0;
    int* returnColumnSizes = NULL;
    int** res = generate(numRows, &returnSize, &returnColumnSizes);
    for (int i = 0; i < returnSize; i++) {
        for (int j = 0; j < returnColumnSizes[i]; j++) {
            printf("%d ", res[i][j]);
        }
        printf("\n");
    }
    return 0;
}