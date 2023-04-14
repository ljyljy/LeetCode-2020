
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <math.h>
// #include <limits.h>

// 40. Combination Sum II
// https://leetcode.com/problems/combination-sum-ii/
// ʱ�临�Ӷȣ�O(2^n)
// �ռ临�Ӷȣ�O(n)

/* ֪ʶ��(���q39.c)
- �����㷨����¼path����
  - dfs�е�path���ȣ���������pathLens[g_curCnt]������
    - ��������dfs��̽ʱ��ı� ���մ�pathLens[g_curCnt]������path����Ϊ����
    - ����ⶨ��g_curLen�����ڼ�¼dfsǰ���'��̬'path����
    - pathLens[g_curCnt]������ÿ��path�ĳ��ȣ���'��̬'��
  - used���飬��¼nums[i]�Ƿ�ʹ�ù�
    - �Ա�q39.c���˴�����used���飬����ȥ��
    - ȥ�أ� nums[i]�Ƿ�ʹ�ù�([i-1]���ù����һ�����ɣ����used[i - 1]Ϊfalse)
      - `if (i - 1 >= 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;`
- ��̬����
  - g_curCnt ׷�� res��pathLens, ���reallocʱ����pathLensҲҪ���ݣ�
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
        // ȥ�أ� nums[i]�Ƿ�ʹ�ù�([i-1]���ù����һ�����ɣ����used[i - 1]Ϊfalse)
        if (i - 1 >= 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
        used[i] = true;
        path[g_curLen++] = nums[i];
        dfs(nums, n, i + 1, target - nums[i], path, used); // Ԫ��ֻ��ʹ��һ�Σ���̽�����i+1
        g_curLen--;
        used[i] = false;
    }
}

int** combinationSum2(int* nums, int n, int target, int* returnSize, int** returnColumnSizes) {
    g_basicSize = 8, g_curCnt = 0, g_curLen = 0;
    g_res = (int**)calloc(g_basicSize, sizeof(int*));
    g_pathLens = (int*)calloc(g_basicSize, sizeof(int));

    int* path = (int*)calloc(fmin(n, target), sizeof(int)); // ����Ԫ��ֻ��ʹ��һ�Σ�nums[i]��СΪ1����path�Ϊfmin(n,target)��1
    bool* used = (bool*)calloc(n, sizeof(bool)); // ȥ�أ� nums[i]�Ƿ�ʹ�ù�

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