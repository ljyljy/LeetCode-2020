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
  * �ӣ�5'
     1. �����е�path����̬��ɾ����¼�±�pathLen
     2. res��̬���ݣ� ��̬malloc & realloc��
     3. ȫ�ֱ�����ʼ�������������
     4. long last_pair[] = { LONG_MAX, LONG_MAX }; // ����ȥ��
        ����bool equals(long p1[], long p2[])��ϣ�ȥ�أ�
     5. ������
        - ���Ρ�**������������븳ֵ����*retColCnts, �ȼ���int* n_cols��
        - ���Ρ�**������������븳ֵ����*returnSize, �ȼ���int* curCnt��
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

    int** res = (int**)malloc(sizeof(int*) * basicSize); // ��̬���䣡��res**�ġ��пռ䡿���з��䡣
    int* n_cols = (int*)malloc(sizeof(int) * basicSize); // ÿ�е�������1��int�����ȼ���*retColCnt

    for (int i = 0; i < n - 3; i++) {
        if (i - 1 >= 0 && nums[i] == nums[i - 1]) continue;
        for (int j = i + 1; j < n - 2; j++) {
            // �� ����д������target=8ʱ����©��[2,2,2,2]����⣡
            // if (j-1 >= 0 && nums[j] == nums[j-1]) continue;
            long cur_pair[] = { nums[i], nums[j] };
            if (equals(last_pair, cur_pair)) continue;
            memcpy(last_pair, cur_pair, sizeof(cur_pair)); // last_pair = cur_pair ���ɣ�
            // printf("last_pair=%d %d, cur_pair=%d %d\n", last_pair[0], last_pair[1], cur_pair[0], cur_pair[1]);

            int lf = j + 1, rt = n - 1;
            while (lf < rt) {
                long sum = (long)nums[i] + (long)nums[j] + (long)nums[lf] + (long)nums[rt];
                if (sum == target) {
                    res[curCnt] = (int*)calloc(4, sizeof(int)); // �������������пռ䡿��������
                    res[curCnt][0] = nums[i], res[curCnt][1] = nums[j], res[curCnt][2] = nums[lf], res[curCnt][3] = nums[rt];
                    n_cols[curCnt] = 4; // ��idx�е�����=4����(*retColCnt)[idx]=4;
                    curCnt++;
                    while (lf < rt && nums[lf] == nums[lf + 1]) lf++;
                    while (lf < rt && nums[rt] == nums[rt - 1]) rt--;
                    lf++; rt--;

                    if (curCnt == basicSize) { // ��̬���ݣ�
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