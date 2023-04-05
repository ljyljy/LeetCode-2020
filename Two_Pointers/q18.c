/**
 * Return an array of arrays of size *returnSize.
 * The sizes of the arrays are returned as *returnColumnSizes array.
 * Note: Both returned array and *columnSizes array must be malloced, assume caller calls free().
 */

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>
#include <stdbool.h>

#ifndef DIM
#define DIM(arr) sizeof(arr)/sizeof(*arr)
#endif

 /**
  * 坑：5'
     1. 回溯中的path，动态增删：记录下标pathLen
     2. res动态扩容！ 动态malloc & realloc！
     3. 全局变量初始化：必须在入口
     4. long last_pair[] = { LONG_MAX, LONG_MAX }; // 用于去重
        并与bool equals(long p1[], long p2[])配合，去重！
     5. 其他：
        - 传参【**列数】的理解与赋值！（*retColCnts, 等价于int* n_cols）
        - 传参【**行数】的理解与赋值！（*returnSize, 等价于int* curCnt）
  */


int basicSize;
int curCnt;

bool equals(long p1[], long p2[]) {
    return p1[0] == p2[0] && p1[1] == p2[1];
}

static inline int cmp(const void* pa, const void* pb) {
    return *(int*)pa - *(int*)pb;
}

int** fourSum(int* nums, int n, int target, int* returnSize, int** retColCnt) {
    qsort(nums, n, sizeof(int), cmp);
    basicSize = 8, curCnt = 0;
    long last_pair[] = { LONG_MAX, LONG_MAX };

    int** res = (int**)malloc(sizeof(int*) * basicSize); // 动态分配！对res**的【行空间】进行分配。
    int* n_cols = (int*)malloc(sizeof(int) * basicSize); // 每行的列数（1个int），等价于*retColCnt

    for (int i = 0; i < n - 3; i++) {
        if (i - 1 >= 0 && nums[i] == nums[i - 1]) continue;
        for (int j = i + 1; j < n - 2; j++) {
            // ↓ 不可写！否则target=8时，会漏掉[2,2,2,2]这个解！
            // if (j-1 >= 0 && nums[j] == nums[j-1]) continue;
            long cur_pair[] = { nums[i], nums[j] };
            if (equals(last_pair, cur_pair)) continue;
            memcpy(last_pair, cur_pair, sizeof(cur_pair)); // last_pair = cur_pair 不可！
            // printf("last_pair=%d %d, cur_pair=%d %d\n", last_pair[0], last_pair[1], cur_pair[0], cur_pair[1]);

            int lf = j + 1, rt = n - 1;
            while (lf < rt) {
                long sum = (long)nums[i] + (long)nums[j] + (long)nums[lf] + (long)nums[rt];
                if (sum == target) {
                    res[curCnt] = (int*)calloc(4, sizeof(int)); // 【勿忘】，【列空间】继续分配
                    res[curCnt][0] = nums[i], res[curCnt][1] = nums[j], res[curCnt][2] = nums[lf], res[curCnt][3] = nums[rt];
                    n_cols[curCnt] = 4; // 第idx行的列数=4，即(*retColCnt)[idx]=4;
                    curCnt++;
                    while (lf < rt && nums[lf] == nums[lf + 1]) lf++;
                    while (lf < rt && nums[rt] == nums[rt - 1]) rt--;
                    lf++; rt--;

                    if (curCnt == basicSize) { // 动态扩容！
                        basicSize *= 2;
                        res = (int**)realloc(res, sizeof(int*) * basicSize);
                        n_cols = (int*)realloc(n_cols, sizeof(int) * basicSize);
                    }
                }
                else if (sum < target) lf++;
                else rt--;
            }
        }
    }
    *returnSize = curCnt;
    *retColCnt = n_cols;
    return res;
}

int main() {
    int basicSize = 8;
    int nums[] = { 1, 0, -1, 0, -2, 2 }, target = 0;
    int returnSize = 0;
    int* retColCnt = (int*)malloc(sizeof(int*) * basicSize);
    int** res = fourSum(nums, DIM(nums), target, &returnSize, &retColCnt);
    for (int i = 0; i < returnSize; i++) {
        for (int j = 0; j < retColCnt[i]; j++) {
            printf("%d ", res[i][j]);
        }
        printf("\n");
    }
}