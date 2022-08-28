/**
 * Return an array of arrays of size *returnSize.
 * The sizes of the arrays are returned as *retColCnts array.
 * Note: Both returned array and *columnSizes array must be malloced, assume caller calls free().
 */

 /**
  * ע��㣺4'
        1. ����̬���ݡ������⡾���������
        2. int** res��Ҫ����*������**������
        3. ���Ρ�**������������븳ֵ����*retColCnts, �ȼ���int* n_cols��
        4. FREE, FREE2D(void** p, int row)
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

// new: res֧�ֶ�̬���ݣ�����ArrayList/Vector��
// ���Ĳ���int** retColCnts��ʵ����һ��ָ��int* n_cols��ָ�룬��¼resÿ�е�����=3
int** threeSum(int* nums, int n, int* returnSize, int** retColCnts) {
    int basicSize = 8;
    int** res = (int**)malloc(sizeof(int*) * basicSize); // ����ʱ����Ҫ֪��������ͨ��ָ��(returnSize)����
    // for (int i = 0; i < basicSize;i++) { // ��Ҫ��̬���ݣ�
    //     res[i] = (int*)malloc(sizeof(int) * 3);
    //     memset(res[i], 0, sizeof(int) * 3);
    // }
    // �� �� *retColCnts = ...
    int* n_cols = (int*)malloc(sizeof(int) * basicSize * 1); // n�� * ����=res�ڸ��е�����(int*1)
    memset(n_cols, 0, sizeof(int) * basicSize);

    qsort(nums, n, sizeof(int), cmp);

    int idx = 0;
    for (int i = 0; i < n - 2; i++) {
        if (i - 1 >= 0 && nums[i] == nums[i - 1]) continue;
        int lf = i + 1, rt = n - 1;
        while (lf < rt) {
            int sum = nums[lf] + nums[rt] + nums[i];
            if (sum == 0) {
                res[idx] = (int*)malloc(sizeof(int) * 3);
                res[idx][0] = nums[i], res[idx][1] = nums[lf], res[idx][2] = nums[rt];
                n_cols[idx] = 3; // for���ã���¼��ǰ�е�����Ϊ3, ��(*retColCnts)[idx]=3
                idx++;

                while (lf < rt && nums[lf] == nums[lf + 1]) lf++;
                while (lf < rt && nums[rt] == nums[rt - 1]) rt--;
                lf++; rt--;

                if (idx == basicSize) {// ��̬���ݣ�
                    basicSize *= 2;
                    res = (int**)realloc(res, sizeof(int*) * basicSize); // ÿ��ָ��
                    n_cols = (int*)realloc(n_cols, sizeof(int) * basicSize);
                }

            }
            else if (sum > 0) rt--;
            else lf++;
        }
    }
    *returnSize = idx; // ����ǰ������malloc���Σ�
    *retColCnts = n_cols;
    return res;
}

// old: LC�������׶������res�ĸ��������ö�̬����������
// ���Ĳ���int** retColCnts��ʵ����һ��ָ��int* n_cols��ָ�룬��¼resÿ�е�����=3
int** threeSum_withoutDynamicMalloc(int* nums, int n, int* returnSize, int** retColCnts) {
    int** res = (int**)malloc(sizeof(int*) * n); // ����ʱ����Ҫ֪��������ͨ��ָ��(returnSize)����
    for (int i = 0; i < n;i++) {
        res[i] = (int*)malloc(sizeof(int) * 3);
        memset(res[i], 0, sizeof(int) * 3);
    }
    int* n_cols = (int*)malloc(sizeof(int) * n * 1); // n�� * ����=res�ڸ��е�����(int*1)
    memset(n_cols, 0, sizeof(int) * n);

    qsort(nums, n, sizeof(int), cmp);

    int idx = 0;
    for (int i = 0; i < n - 2; i++)
    {
        if (i - 1 >= 0 && nums[i] == nums[i - 1]) continue;
        int lf = i + 1, rt = n - 1;
        while (lf < rt)
        {
            int sum = nums[lf] + nums[rt] + nums[i];
            if (sum == 0) {
                // { nums[i], nums[lf], nums[rt] };
                res[idx][0] = nums[i];
                res[idx][1] = nums[lf];
                res[idx][2] = nums[rt];
                n_cols[idx] = 3; // for���ã���¼��ǰ�е�����Ϊ3, ��(*retColCnts)[idx]=3
                // for (int k = 0; k < 3; k++) {
                //     printf("row=%d: %d %d %d\n", idx, res[idx][0], res[idx][1], res[idx][2]);
                // }
                idx++;

                while (lf < rt && nums[lf] == nums[lf + 1]) lf++;
                while (lf < rt && nums[rt] == nums[rt - 1]) rt--;
                lf++; rt--;
            }
            else if (sum > 0) rt--;
            else lf++;
        }
    }
    *returnSize = idx; // ����ǰ������malloc���Σ�
    *retColCnts = n_cols;
    return res;
}

FREE(void* p) {
    free(p);
    p = NULL;
}

FREE2D(void** p, int row) {
    for (int i = 0; i < row; i++)
    {
        FREE(p[i]);
    }
    free(p);
    p = NULL;
}

int main() {
    // int nums[] = { -1,0,1,2,-1,-4,4,-2 };
    int nums[] = { -7,-4,-6,6,4,-6,-9,-10,-7,5,3,-1,-5,8,-1,-2,-8,-1,5,-3,-5,4,2,-5,-4,4,7 };
    int n = DIM(nums); // 27
    // printf("n=%d\n", n);
    int* returnSize = (int*)malloc(sizeof(int) * 1);  // ����ǰ ����malloc��
    int** retColCnts = (int**)malloc(sizeof(int*) * 8); // basicSize=8�������ᶯ̬����
    int** res = threeSum(nums, n, returnSize, retColCnts);
    printf("returnSize=%d\n", *returnSize);
    for (int i = 0; i < *returnSize; i++) {
        for (int j = 0; j < (*retColCnts)[i]; j++) {
            printf("%d ", res[i][j]);
        }
        printf("\n");
    }
    FREE(returnSize);
    FREE2D(retColCnts, DIM(retColCnts));
    FREE2D(res, DIM(retColCnts));
}