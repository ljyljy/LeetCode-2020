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

int basicSize;
int idx;

bool equals(long p1[], long p2[]) {
    return p1[0] == p2[0] && p1[1] == p2[1];
}

static inline int cmp(const void* pa, const void* pb) {
    return *(int*)pa - *(int*)pb;
}

int** fourSum(int* nums, int n, int target, int* returnSize, int** retColCnt) {
    qsort(nums, n, sizeof(int), cmp);
    basicSize = 8, idx = 0;
    long last_pair[] = { LONG_MAX, LONG_MAX };

    int** res = (int**)malloc(sizeof(int*) * basicSize); // 动态分配！
    int* cols = (int*)malloc(sizeof(int) * basicSize); // 每行的列数（1个int），等价于*retColCnt

    for (int i = 0; i < n - 3; i++) {
        if (i - 1 >= 0 && nums[i] == nums[i - 1]) continue;
        for (int j = i + 1; j < n - 2; j++) {
            long cur_pair[] = { nums[i], nums[j] };
            if (equals(last_pair, cur_pair)) continue;
            memcpy(last_pair, cur_pair, sizeof(cur_pair)); // last_pair = cur_pair 不可！
            // printf("last_pair=%d %d, cur_pair=%d %d\n", last_pair[0], last_pair[1], cur_pair[0], cur_pair[1]);

            int lf = j + 1, rt = n - 1;
            while (lf < rt) {
                long sum = (long)nums[i] + (long)nums[j] + (long)nums[lf] + (long)nums[rt];
                if (sum == target) {
                    res[idx] = (int*)calloc(4, sizeof(int));
                    res[idx][0] = nums[i], res[idx][1] = nums[j], res[idx][2] = nums[lf], res[idx][3] = nums[rt];
                    cols[idx] = 4; // 第idx行的列数=4，即(*retColCnt)[idx]=4;
                    idx++;
                    while (lf < rt && nums[lf] == nums[lf + 1]) lf++;
                    while (lf < rt && nums[rt] == nums[rt - 1]) rt--;
                    lf++; rt--;

                    if (idx == basicSize) { // 动态扩容！
                        basicSize *= 2;
                        res = (int**)realloc(res, sizeof(int*) * basicSize);
                        cols = (int*)realloc(cols, sizeof(int) * basicSize);
                    }
                }
                else if (sum < target) lf++;
                else rt--;
            }
        }
    }
    *returnSize = idx;
    *retColCnt = cols;
    return res;
}

int main() {
    int basicSize = 8;
    int nums[] = { 1, 0, -1, 0, -2, 2 }, target = 0;
    int* returnSize = malloc(sizeof(int));
    int** retColCnt = malloc(sizeof(int*) * basicSize);
    int** res = fourSum(nums, DIM(nums), target, returnSize, retColCnt);
    for (int i = 0; i < *returnSize; i++) {
        for (int j = 0; j < (*retColCnt)[i]; j++) {
            printf("%d ", res[i][j]);
        }
        printf("\n");
    }

}