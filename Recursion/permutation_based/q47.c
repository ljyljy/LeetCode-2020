#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <math.h>
// #include <limits.h>

// q47.c
int** g_res;
int* g_pathLens;
int g_basicSize, g_curCnt, g_curLen;

static inline int cmp(const void* a, const void* b) {
    return *(int*)a - *(int*)b;
}

void dfs(int* nums, int n, int idx, int* path, bool* used) {
    if (g_curLen == n) {
        g_res[g_curCnt] = (int*)calloc(g_curLen, sizeof(int));
        memcpy(g_res[g_curCnt], path, g_curLen * sizeof(int));
        g_pathLens[g_curCnt] = g_curLen;
        g_curCnt++;

        if (g_curCnt == g_basicSize) {
            g_basicSize *= 2;
            g_res = (int**)realloc(g_res, g_basicSize * sizeof(int*));
            g_pathLens = (int*)realloc(g_pathLens, g_basicSize * sizeof(int));
        }
        return;
    }
    // 全排列 i从0起[如:{1,2} & {2, 1(?逆序：又从0起)}]（不同于组合、排列startIdx）
    for (int i = 0; i < n; i++) {
        //  1)同一树枝的同一结点(地址同) - ∵ i从0起, 勿漏！↓
        if (used[i]) continue; // （∵每次for都从0遍历，难免碰到先前用过的）
        // 2)同一树层去重（nums[i-1]遍历结束后回溯-used置false）
        if (i - 1 >= 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;// ∵存在重复数 ∴存在相同的排列
        used[i] = true;
        path[g_curLen++] = nums[i];
        dfs(nums, n, i + 1, path, used);
        g_curLen--;
        used[i] = false;
    }
}
int** permuteUnique(int* nums, int n, int* returnSize, int** returnColumnSizes) {
    // 同一树层去重(∵去重判断：根据相邻元素 ∴预处理：sorted)
    qsort(nums, n, sizeof(int), cmp);
    g_basicSize = 8, g_curCnt = 0, g_curLen = 0;
    g_res = (int**)calloc(g_basicSize, sizeof(int*));
    g_pathLens = (int*)calloc(g_basicSize, sizeof(int));

    int* path = (int*)calloc(n, sizeof(int));
    bool* used = (bool*)calloc(n, sizeof(bool));
    dfs(nums, n, 0, path, used);
    free(path);
    free(used);
    *returnColumnSizes = g_pathLens;
    *returnSize = g_curCnt;
    return g_res;
}

int main() {
    int nums[] = { 1, 1, 2 };
    int n = sizeof(nums) / sizeof(int);
    int returnSize;
    int* returnColumnSizes;
    int** res = permuteUnique(nums, n, &returnSize, &returnColumnSizes);
    for (int i = 0; i < returnSize; i++) {
        for (int j = 0; j < returnColumnSizes[i]; j++) {
            printf("%d ", res[i][j]);
        }
        printf("\n");
    }
    return 0;
}