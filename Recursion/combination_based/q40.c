
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <math.h>
// #include <limits.h>

// 40. Combination Sum II
// https://leetcode.com/problems/combination-sum-ii/
// 时间复杂度：O(2^n)
// 空间复杂度：O(n)

/* 知识点(类比q39.c)
- 回溯算法，记录path数组
  - dfs中的path长度，不可以与pathLens[g_curCnt]混淆！
    - （否则，在dfs下探时会改变 最终答案pathLens[g_curCnt]，出现path长度为负）
    - 需额外定义g_curLen，用于记录dfs前后的'动态'path长度
    - pathLens[g_curCnt]：最终每条path的长度，是'静态'的
  - used数组，记录nums[i]是否使用过
    - 对比q39.c，此处多了used数组，用于去重
    - 去重： nums[i]是否使用过([i-1]以用过，且回溯完成，因此used[i - 1]为false)
      - `if (i - 1 >= 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;`
- 动态扩容
  - g_curCnt 追踪 res、pathLens, 因此realloc时勿忘pathLens也要扩容！
*/

int** g_res;
int* g_pathLens;
int g_basicSize, g_curCnt, g_curLen;

static inline int cmp(const void* pa, const void* pb) {
    return *(int*)pa - *(int*)pb;
}

void dfs(int* nums, int n, int idx, int target, int* path, bool* used) {
    if (target < 0) return;
    if (target == 0) {
        g_res[g_curCnt] = (int*)calloc(g_curLen, sizeof(int));
        memcpy(g_res[g_curCnt], path, g_curLen * sizeof(int));
        g_pathLens[g_curCnt] = g_curLen;

        g_curCnt++;
        if (g_curCnt == g_basicSize) {
            g_basicSize *= 2;
            g_res = (int**)realloc(g_res, sizeof(int*) * g_basicSize);
            g_pathLens = (int*)realloc(g_pathLens, sizeof(int) * g_basicSize);
        }
        return;
    }

    for (int i = idx; i < n; i++) {
        // 去重： nums[i]是否使用过([i-1]以用过，且回溯完成，因此used[i - 1]为false)
        if (i - 1 >= 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
        used[i] = true;
        path[g_curLen++] = nums[i];
        dfs(nums, n, i + 1, target - nums[i], path, used); // 元素只能使用一次，下探需后移i+1
        g_curLen--;
        used[i] = false;
    }
}

int** combinationSum2(int* nums, int n, int target, int* returnSize, int** returnColumnSizes) {
    g_basicSize = 8, g_curCnt = 0, g_curLen = 0;
    g_res = (int**)calloc(g_basicSize, sizeof(int*));
    g_pathLens = (int*)calloc(g_basicSize, sizeof(int));

    int* path = (int*)calloc(fmin(n, target), sizeof(int)); // 由于元素只能使用一次，nums[i]最小为1，故path最长为fmin(n,target)个1
    bool* used = (bool*)calloc(n, sizeof(bool)); // 去重： nums[i]是否使用过

    qsort(nums, n, sizeof(nums[0]), cmp);
    dfs(nums, n, 0, target, path, used);

    *returnSize = g_curCnt;
    *returnColumnSizes = g_pathLens;
    return g_res;
}

int main() {
    int nums[] = { 10, 1, 2, 7, 6, 1, 5 };
    int n = sizeof(nums) / sizeof(nums[0]);
    int target = 8;
    int returnSize;
    int* returnColumnSizes;

    int** res = combinationSum2(nums, n, target, &returnSize, &returnColumnSizes);
    for (int i = 0; i < returnSize; i++) {
        for (int j = 0; j < returnColumnSizes[i]; j++) {
            printf("%d ", res[i][j]);
        }
        printf("\n");
    }

    return 0;
}