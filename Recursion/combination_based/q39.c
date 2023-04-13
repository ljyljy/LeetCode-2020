#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
// #include <math.h>
// #include <limits.h>

// 39. Combination Sum
// https://leetcode.com/problems/combination-sum/
// ʱ�临�Ӷȣ�O(2^n)
// �ռ临�Ӷȣ�O(n)

/* ֪ʶ��
- �����㷨����¼path����
  - dfs�е�path���ȣ���������pathLens[g_curCnt]������
    - ��������dfs��̽ʱ��ı� ���մ�pathLens[g_curCnt]������path����Ϊ����
    - ����ⶨ��g_curLen�����ڼ�¼dfsǰ���'��̬'path����
    - pathLens[g_curCnt]������ÿ��path�ĳ��ȣ���'��̬'��
  - ��̬����
    - g_curCnt ׷�� res��pathLens, ���reallocʱ����pathLensҲҪ���ݣ�
*/
int** g_res;
int* g_pathLens;
int g_basicCnt, g_curCnt, g_curLen;

// cmpԪ��Ϊnums[i], ����int����'ǿת+������'����ܱȽϴ�С
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
        // ���ټ�֦��nums���򣬺���������󣬲�������������
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
        g_curLen--; // ���ݣ��ָ�path����
    }
}


int** combinationSum(int* nums, int n, int target, int* returnSize, int** returnColumnSizes) {
    g_basicCnt = 8, g_curCnt = 0, g_curLen = 0;
    g_res = (int**)calloc(g_basicCnt, sizeof(int*));
    g_pathLens = (int*)calloc(g_basicCnt, sizeof(int)); // ��¼ÿ��path�ĳ���

    qsort(nums, n, sizeof(int), cmp); // ����
    int* path = (int*)calloc(target / 2, sizeof(int)); // ����nums��СֵΪ2�����path��󳤶�Ϊtarget/2
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