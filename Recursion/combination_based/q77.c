#include <stdio.h>
#include <stdlib.h>
// #include <string.h>
// #include <stdbool.h>
// #include <math.h>
// #include <limits.h>

// q77.c

int** res;
int* n_cols;
int basicSize, curCnt, curLen;

void dfs(int n, int k, int idx, int* path) {
    if (curLen == k) {
        res[curCnt] = (int*)calloc(k, sizeof(int));
        memcpy(res[curCnt], path, k * sizeof(int));
        n_cols[curCnt++] = k;

        if (curCnt == basicSize) {
            basicSize *= 2;
            res = (int**)realloc(res, basicSize * sizeof(int*));
            n_cols = (int*)realloc(n_cols, basicSize * sizeof(int));
        }
        return;
    }

    for (int i = idx; i <= n; i++) {
        path[curLen++] = i;
        dfs(n, k, i + 1, path);
        --curLen;
    }
}

int** combine(int n, int k, int* returnSize, int** returnColumnSizes) {
    basicSize = 8, curCnt = curLen = 0;
    res = (int**)calloc(basicSize, sizeof(int*));
    n_cols = (int*)calloc(basicSize, sizeof(int));

    int* path = (int*)calloc(k, sizeof(int));
    dfs(n, k, 1, path);
    *returnSize = curCnt;
    *returnColumnSizes = n_cols;
    return res;
}

int main() {
    int n = 4, k = 2;
    int returnSize, * returnColumnSizes;
    int** res = combine(n, k, &returnSize, &returnColumnSizes);
    for (int i = 0; i < returnSize; i++) {
        for (int j = 0; j < returnColumnSizes[i]; j++) {
            printf("%d ", res[i][j]);
        }
        printf("\n");
    }
    return 0;
}