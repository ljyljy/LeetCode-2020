#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <math.h>
// #include <limits.h>

// q46.c
int** g_res;
int* g_pathLens;
int g_basicSize, g_curCnt, g_curLen;

void dfs(int* nums, int n, int idx, int* path, bool* used) {
    if (g_curLen == n) {
        g_res[g_curCnt] = (int*)calloc(g_curLen, sizeof(int));
        // memcpy_s(g_res[g_curCnt], g_curLen * sizeof(int), path, g_curLen * sizeof(int));
        memcpy(g_res[g_curCnt], path, g_curLen * sizeof(int));
        g_pathLens[g_curCnt] = g_curLen;
        g_curCnt++;

        if (g_curCnt == g_basicSize) {
            g_basicSize *= 2;
            g_res = (int**)realloc(g_res, sizeof(int*) * g_basicSize);
            g_pathLens = (int*)realloc(g_pathLens, sizeof(int) * g_basicSize);
        }
        return; // ��ͬ���Ӽ�����ϡ����н�����[Ҷ��]
    }

    for (int i = 0; i < n; i++) {
        if (used[i]) continue;
        used[i] = true;
        path[g_curLen++] = nums[i];
        dfs(nums, n, i + 1, path, used);
        g_curLen--;
        used[i] = false;
    }
}

int** permute(int* nums, int n, int* returnSize, int** returnColumnSizes) {
    g_basicSize = 8, g_curCnt = 0, g_curLen = 0;
    g_res = (int**)calloc(g_basicSize, sizeof(int*));
    g_pathLens = (int*)calloc(g_basicSize, sizeof(int));

    int* path = (int*)calloc(g_basicSize, sizeof(int));
    // ��ȫ���� ��(1) {1,2}��{2,1}�ǲ�ͬ������ -> for��i��0�𣬶���idx��
    // - (2) ��i��0�𣬡���Ҫused����·��(��֦)ȥ�أ�--��01����-��Ʒֻ��1�����������⡿?
    bool* used = (bool*)calloc(n, sizeof(bool));
    dfs(nums, n, 0, path, used);
    free(path);
    free(used);
    *returnColumnSizes = g_pathLens;
    *returnSize = g_curCnt;
    return g_res;
}

int main() {
    int nums[] = { 1, 2, 3 };
    int n = sizeof(nums) / sizeof(nums[0]);
    int returnSize;
    int* returnColumnSizes;
    int** res = permute(nums, n, &returnSize, &returnColumnSizes);
    for (int i = 0; i < returnSize; i++) {
        for (int j = 0; j < returnColumnSizes[i]; j++) {
            printf("%d ", res[i][j]);
        }
        printf("\n");
    }
    return 0;
}