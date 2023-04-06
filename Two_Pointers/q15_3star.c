/**
 * Return an array of arrays of size *returnSize.
 * The sizes of the arrays are returned as *retColCnts array.
 * Note: Both returned array and *columnSizes array must be malloced, assume caller calls free().
 */

 /**
  * 注意点：3'
        1. 【动态扩容】！避免【堆溢出】！
        2. int** res需要返回*行数，**列数！
        3. 传参【**列数】的理解与赋值！（*retColCnts, 等价于int* n_cols）
  */
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>

#ifndef DIM
#define DIM(arr) sizeof(arr)/sizeof(*arr)
#endif


static inline int cmp(const void* pa, const void* pb) {
    return *(int*)pa - *(int*)pb;
}

// v2（本题必须这么做，否则堆溢出！）: res支持动态扩容！类似ArrayList/Vector！
// 最后的参数int** retColCnts，实则是一个指向int* n_cols的指针，记录res每行的列数=3
int** threeSum(int* nums, int n, int* returnSize, int** retColCnts) {
    int basicSize = 8,  curCnt = 0;
    int** res = (int**)malloc(sizeof(int*) * basicSize); // 调用时，需要知道行数，通过指针(returnSize)返回
    // for (int i = 0; i < basicSize;i++) { // 需要动态扩容！
    //     res[i] = (int*)malloc(sizeof(int) * 3);
    //     memset(res[i], 0, sizeof(int) * 3);
    // }
    // ↓ 或 *retColCnts = ...
    int* n_cols = (int*)malloc(sizeof(int) * basicSize * 1); // n行 * 1列，保存：每行ans的列数
    // sizeof(int* n_cols)=8, sizeof(int) * basicSize = 32
    memset(n_cols, 0, sizeof(int) * basicSize); // 不可写sizeof(int*)=8

    qsort(nums, n, sizeof(int), cmp);

    for (int i = 0; i < n - 2; i++) {
        if (i - 1 >= 0 && nums[i] == nums[i - 1]) continue;
        int lf = i + 1, rt = n - 1;
        while (lf < rt) {
            int sum = nums[lf] + nums[rt] + nums[i];
            if (sum == 0) {
                res[curCnt] = (int*)malloc(sizeof(int) * 3);
                res[curCnt][0] = nums[i], res[curCnt][1] = nums[lf], res[curCnt][2] = nums[rt];
                n_cols[curCnt] = 3; // for调用：记录当前行的列数为3, 或(*retColCnts)[curCnt]=3
                curCnt++;

                if (curCnt == basicSize) {// 动态扩容！
                    basicSize *= 2;
                    res = (int**)realloc(res, sizeof(int*) * basicSize); // 每行指针, N*3
                    n_cols = (int*)realloc(n_cols, sizeof(int) * basicSize); // N*1
                }

                while (lf < rt && nums[lf] == nums[lf + 1]) lf++;
                while (lf < rt && nums[rt] == nums[rt - 1]) rt--;
                lf++; rt--;
            }
            else if (sum > 0) rt--;
            else lf++;
        }
    }
    *returnSize = curCnt; // 调用前，必须malloc传参！
    *retColCnts = n_cols;
    return res;
}


void FREE(void* p) {
    free(p);
    p = NULL;
}

void FREE2D(void** p, int row) {
    for (int i = 0; i < row; i++) {
        FREE(p[i]);
    }
    free(p);
    p = NULL;
}

/////////////////////////////////////////////////////////////////////////////
// v1: 没有动态扩容，有的案例会溢出！
// 最后的参数int** retColCnts，实则是一个指向int* n_cols的指针（从而可以在该函数中对n_cols中的元素修改，可改向），记录res每行的列数=3
int** threeSum_v1(int* nums, int n, int* returnSize, int** retColCnts) {
    int** res = (int**)malloc(sizeof(int*) * n); // 调用时，需要知道行数，通过指针(returnSize)返回
    for (int i = 0; i < n; i++) {
        res[i] = (int*)calloc(3, sizeof(int)); // 等价于下两句
        // res[i] = (int*)malloc(sizeof(int) * 3);
        // memset(res[i], 0, sizeof(int) * 3);
    }
    int* n_cols = (int*)malloc(sizeof(int) * n * 1); // n行 * 数字=res在该行的列数(int*1)
    memset(n_cols, 0, sizeof(int) * n);

    qsort(nums, n, sizeof(int), cmp);

    int curCnt = 0;
    for (int i = 0; i < n - 2; i++) {
        if (i - 1 >= 0 && nums[i] == nums[i - 1]) continue;
        int lf = i + 1, rt = n - 1;
        while (lf < rt) {
            int sum = nums[lf] + nums[rt] + nums[i];
            if (sum == 0) {
                // res[curCnt][] = { nums[i], nums[lf], nums[rt] }; // 错误！
                res[curCnt][0] = nums[i];
                res[curCnt][1] = nums[lf];
                res[curCnt][2] = nums[rt];
                n_cols[curCnt] = 3; // for调用：记录当前行的列数为3, 或(*retColCnts)[curCnt]=3
                for (int k = 0; k < 3; k++) {
                    printf("row=%d: %d %d %d\n", curCnt, res[curCnt][0], res[curCnt][1], res[curCnt][2]);
                }
                curCnt++;

                while (lf < rt && nums[lf] == nums[lf + 1]) lf++;
                while (lf < rt && nums[rt] == nums[rt - 1]) rt--;
                lf++; rt--;
            }
            else if (sum > 0) rt--;
            else lf++;
        }
    }
    *returnSize = curCnt; // 调用前，必须malloc传参！
    *retColCnts = n_cols;
    return res;
}
/////////////////////////////////////////////////////////////////////////////


int main() {
    // int nums[] = { -1,0,1,2,-1,-4,4,-2 }; // { -1, 0, 1, 2, -1, -4 };
    // [-1,0,1,2,-1,-4,-2,-3,3,0,4]
    int nums[] = { -7, -4, -6, 6, 4, -6, -9, -10, -7, 5, 3, -1, -5, 8, -1, -2, -8, -1, 5, -3, -5, 4, 2, -5, -4, 4, 7 };
    int n = DIM(nums); // 27
    // printf("n=%d\n", n);
    int returnSize = 0;  // 调用前 勿忘malloc！
    int* retColCnts = (int*)malloc(sizeof(int) * n); // 记录int** res中每一行的列数
    int** res = threeSum(nums, n, &returnSize, &retColCnts); // 注意：传参形式！
    printf("returnSize=%d\n", returnSize);
    for (int i = 0; i < returnSize; i++) {
        for (int j = 0; j < retColCnts[i]; j++) {
            printf("%d ", res[i][j]);
        }
        printf("\n");
    }

    FREE((void*)retColCnts);
    FREE2D((void**)res, DIM(retColCnts));
}