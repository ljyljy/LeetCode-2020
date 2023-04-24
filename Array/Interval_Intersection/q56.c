#include <stdio.h>
#include <stdlib.h>
// #include <stdbool.h>
// #include <string.h>
// #include <math.h>
// #include <limits.h>

// q56.c

int** res;
int* n_cols;
int curCnt;

// ��ά����(int**)���������� - cmp
// - ��Աpa/pb����ʵ��Ϊint* intv, ����ǿת & ������
// - �Ա�intv[0] or intv[1], ��pa/pb[0 or 1]
static inline int cmp(const void* pa, const void* pb) {
    int* intv1 = *(int**)pa, * intv2 = *(int**)pb;
    if (intv1[0] != intv2[0]) {
        return intv1[0] - intv2[0];
    }
    else {
        return intv1[1] - intv2[1];
    }
}

bool isOverlap(int* intv1, int* intv2) {
    // ����qsort����һ����intv2[0] <= int1[1]
    return fmax(intv1[0], intv2[0]) <= fmin(intv1[1], intv2[1]);
}

void mergeIntv(int* intv1, const int* intv2) {
    intv1[0] = fmin(intv1[0], intv2[0]); // minL
    intv1[1] = fmax(intv1[1], intv2[1]); // maxR
}

int** merge(int** intervals, int n, int* intervalsColSize, int* returnSize, int** returnColumnSizes) {
    res = (int**)calloc(n, sizeof(int*));
    n_cols = (int*)calloc(n, sizeof(int));
    curCnt = 0;
    qsort(intervals, n, sizeof(intervals[0]), cmp);// ������qsortһ��


    // int* last = (int*)calloc(2, sizeof(int)); // v1��δ�޸�
    // memcpy(last, intervals[0], 2 * sizeof(int));
    int* last = intervals[0]; // v2: �޸���ԭ����intervals[0]
    for (int i = 1; i < n; i++) {
        // printf("[%d, %d]\t", intervals[i][0], intervals[i][1]);
        if (isOverlap(last, intervals[i])) {
            mergeIntv(last, intervals[i]);
        }
        else {
            res[curCnt] = (int*)calloc(2, sizeof(int));
            memcpy(res[curCnt], last, 2 * sizeof(int));
            n_cols[curCnt] = 2;
            curCnt++;
            // memcpy(last, intervals[i], 2 * sizeof(int)); // v1
            last = intervals[i]; // v2
        }
    }
    // for����ʱ����ʣ���һ��lastû����res
    res[curCnt] = (int*)calloc(2, sizeof(int));
    memcpy(res[curCnt], last, 2 * sizeof(int));
    n_cols[curCnt] = 2;
    curCnt++;

    *returnSize = curCnt;
    *returnColumnSizes = n_cols;
    return res;
}