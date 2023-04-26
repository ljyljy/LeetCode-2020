#include <stdio.h>
#include <stdlib.h>
// #include <stdbool.h>
#include <string.h>
// #include <math.h>
// #include <limits.h>

// q78. 子集
int** res;
int* n_cols;
int curCnt, curLen, basicSize;

void dfs(int* nums, int n, int idx, int* path) {
    // if (curLen > n) return; // 可不写。for(i < n)控制数组不越界
    res[curCnt] = (int*)calloc(curLen, sizeof(int));
    memcpy(res[curCnt], path, curLen * sizeof(int)); // path动态变化，需要memcpy，不可直接指针赋值
    n_cols[curCnt] = curLen;
    curCnt++;

    if (curCnt == basicSize) {
        basicSize *= 2;
        res = (int**)realloc(res, basicSize * sizeof(int*));
        n_cols = (int*)realloc(n_cols, basicSize * sizeof(int));
    }

    for (int i = idx; i < n; i++) {
        path[curLen++] = nums[i];
        dfs(nums, n, i + 1, path);
        --curLen;
    }
}

int** subsets(int* nums, int n, int* returnSize, int** returnColumnSizes) {
    curCnt = 0, curLen = 0, basicSize = 8;
    res = (int**)calloc(basicSize, sizeof(int*));
    n_cols = (int*)calloc(basicSize, sizeof(int));
    int* path = (int*)calloc(n, sizeof(int));

    dfs(nums, n, 0, path);

    *returnSize = curCnt;
    *returnColumnSizes = n_cols;
    return res;
}

int main() {
    int nums[] = { 1, 2, 3 };
    int n = sizeof(nums) / sizeof(nums[0]);
    int returnSize;
    int* returnColumnSizes;
    int** res = subsets(nums, n, &returnSize, &returnColumnSizes);
    for (int i = 0; i < returnSize; i++) {
        printf("[");
        for (int j = 0; j < returnColumnSizes[i]; j++) {
            printf("%d,", res[i][j]);
        }
        printf("]\n");
    }
}