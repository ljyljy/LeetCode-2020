#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
// #include <math.h>
// #include <limits.h>

// 39. Combination Sum
// https://leetcode.com/problems/combination-sum/
// 时间复杂度：O(2^n)
// 空间复杂度：O(n)

/* 知识点
- 回溯算法，记录path数组
  - dfs中的path长度，不可以与pathLens[g_curCnt]混淆！
    - （否则，在dfs下探时会改变 最终答案pathLens[g_curCnt]，出现path长度为负）
    - 需额外定义g_curLen，用于记录dfs前后的'动态'path长度
    - pathLens[g_curCnt]：最终每条path的长度，是'静态'的
  - 动态扩容
    - g_curCnt 追踪 res、pathLens, 因此realloc时勿忘pathLens也要扩容！
*/
int** g_res;
int* g_pathLens;
int g_basicCnt, g_curCnt, g_curLen;

// cmp元素为nums[i], 类型int，需'强转+解引用'后才能比较大小
static inline int cmp(const void* a, const void* b) {
    return *(int*)a - *(int*)b;
}


void dfs(int* nums, int n, int idx, int target, int* path) {
    if (target < 0) return;
    if (target == 0) {
        g_res[g_curCnt] = (int*)malloc(sizeof(int) * g_curLen);
        memcpy(g_res[g_curCnt], path, sizeof(int) * g_curLen);
        g_pathLens[g_curCnt] = g_curLen;

        g_curCnt++;
        if (g_curCnt == g_basicCnt) {
            g_basicCnt *= 2;
            g_res = (int**)realloc(g_res, sizeof(int*) * g_basicCnt);
            g_pathLens = (int*)realloc(g_pathLens, sizeof(int) * g_basicCnt);
        }

        /* debug
        printf("1) g_curCnt: %d, g_pathLens[g_curCnt]: %d\n", g_curCnt, g_pathLens[g_curCnt - 1]);
        printf("g_res: ");
        for (int i = 0; i < g_curCnt; i++) {
            for (int j = 0; j < g_pathLens[i]; j++) {
                printf("%d ", g_res[i][j]);
            }
        }
        printf("\n");
        */
        return;
    }


    for (int i = idx; i < n; i++) {
        // 加速剪枝：nums升序，后面的数更大，不可能满足条件
        if (target - nums[i] < 0) break;

        path[g_curLen++] = nums[i];
        /* debug
        printf("2) g_curCnt: %d, g_pathLens[g_curCnt]: %d\n", g_curCnt, g_pathLens[g_curCnt]);
        printf("path: ");
        for (int i = 0; i < g_pathLens[g_curCnt]; i++) {
            printf("%d ", path[i]);
        }
        printf("\n");
        */
        dfs(nums, n, i, target - nums[i], path);
        g_curLen--; // 回溯，恢复path长度
    }
}


int** combinationSum(int* nums, int n, int target, int* returnSize, int** returnColumnSizes) {
    g_basicCnt = 8, g_curCnt = 0, g_curLen = 0;
    g_res = (int**)calloc(g_basicCnt, sizeof(int*));
    g_pathLens = (int*)calloc(g_basicCnt, sizeof(int)); // 记录每条path的长度

    qsort(nums, n, sizeof(int), cmp); // 升序
    int* path = (int*)calloc(target / 2, sizeof(int)); // 由于nums最小值为2，因此path最大长度为target/2
    dfs(nums, n, 0, target, path);

    *returnSize = g_curCnt;
    *returnColumnSizes = g_pathLens;

    free(path); path = NULL;
    return g_res;
}

int main() {
    int nums[] = { 2, 3, 5 };
    int n = sizeof(nums) / sizeof(nums[0]);
    int target = 8;
    int returnSize;
    int* returnColumnSizes;
    int** g_res = combinationSum(nums, n, target, &returnSize, &returnColumnSizes);
    printf("returnSize: %d \n", returnSize);

    for (int i = 0; i < returnSize; i++) {
        for (int j = 0; j < returnColumnSizes[i]; j++) {
            printf("%d ", g_res[i][j]);
        }
        printf("\n");
    }

}