#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
// #include <limits.h>
// #include <stdbool.h>

#ifndef DIM
#define DIM(arr) sizeof(arr)/sizeof(*arr)
#endif

// static inline int cmp_test(const void* pa, const void* pb) {
//     // 1) o[0]���� 2)���򣬡�o[1]����, �ӣ�������֤right�����ܴ�
//     //  ��[1, 2], [1, 4], [3, 4] ->[1, 4]��[1, 2]֮ǰ����֤o[0]��ͬʱ��right��ֵ������ = 4 > 2��
//     int* o11 = (int*)pa, * o22 = (int*)pb;
//     int* o1 = *(int**)pa, * o2 = *(int**)pb; // ��C�ӣ�o1/o2����ǿתΪ*(int**), ��Javaһ�£�����ǿתΪint**, �����߼� �� ָ��+1Խ�磡��
//     printf("&o11=%d, o11[0]=%d, &o22=%d,  o22[0]=%d,  &o1=%d, o1[1]=%d, &o2=%d,  o2[1]=%d;\n", &o11, o11[0], &o22, o22[0], o11[1], o22[1], &o1, o1[0], &o2, o2[0]);
//     printf("o11[0] - o22[0]=%d, o22[1] - o11[1]=%d, o1[0] - o2[0]=%d, o2[1] - o1[1]=%d\n", o11[0] - o22[0], o22[1] - o11[1], o1[0] - o2[0], o2[1] - o1[1]);
//     // printf("sizeof(o11)=%d, sizeof(o22)=%d, sizeof(o1)=%d, sizeof(o2)=%d,\n", sizeof(o11), sizeof(o22), sizeof(o1), sizeof(o2)); // ����8����intervals[0]
//     printf("o1[0]=%d, o2[0]=%d,o1[1]=%d, o2[1]=%d\n\n", o1[0], o2[0], o1[1], o2[1]);

//     // return o11[0] != o22[0] ? o11[0] - o22[0] : o22[1] - o11[1]; // WA = 3
//     return o1[0] != o2[0] ? o1[0] - o2[0] : o2[1] - o1[1]; // AK = 2
// }

static inline int cmp(const void* pa, const void* pb) { // д��1
    // 1) o[0]���� 2)���򣬡�o[1]����, �ӣ�������֤right�����ܴ�
    //  ��[1, 2], [1, 4], [3, 4] ->[1, 4]��[1, 2]֮ǰ����֤o[0]��ͬʱ��right��ֵ������ = 4 > 2��
    int* o1 = *(int**)pa, * o2 = *(int**)pb; // ��C�ӣ�o1/o2����ǿתΪ*(int**), ��Javaһ�£�����ǿתΪint**, �����߼� �� ָ��+1Խ�磡��
    return o1[0] != o2[0] ? o1[0] - o2[0] : o2[1] - o1[1];
}

// int cmp2(void* a, void* b) { // д��2
//     int** tmpA = (int**)a, **tmpB = (int**)b;
//     return ((*tmpA)[0] == (*tmpB)[0] ? (*tmpB)[1] - (*tmpA)[1] : (*tmpA)[0] - (*tmpB)[0]);
// }


int removeCoveredIntervals(int** intervals, int n, int* intervalsColSize) {
    *intervalsColSize = 2;
    qsort(intervals, n, sizeof(intervals[0]), cmp); // [C��] ��sizeof(int*)
    // printArr(intervals, n, *intervalsColSize);

    int right = INT_MIN;
    int rmCnt = 0;
    for (int i = 0; i < n; i++) {
        int rt = intervals[i][1];
        if (rt <= right) {
            rmCnt++;
        }
        right = fmax(right, rt);
    }

    return n - rmCnt;
}

void printArr(int** arr, int row, int col) {
    printf("row=%d, col=%d\n", row, col);
    for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
            printf("%d ", arr[i][j]);
        }
        printf("\n");
    }
}

/**
 * ��C��*2��
 * 1��int**��cmp  ��C�ӣ�o1/o2����ǿתΪ*(int**), ��Javaһ�£�����ǿתΪint**, �����߼� �� ָ��+1Խ�磡��
 * 2��int**��int[][]�Ĳ�ͬ�� -- ���� sizeof
 */
int main() {
    int arr[][2] = { {1,4}, {3,6}, {2,8} }; // int (*arr)[3];
    int row = DIM(arr), col = DIM(arr[0]);
    printf("[main1] (int[][2]) -- row=%d, col=%d\n", row, col);

    int** intervals = (int**)malloc(sizeof(int*) * row);
    for (int i = 0; i < row; i++) {
        intervals[i] = calloc(col, sizeof(int));
    }
    for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
            intervals[i][j] = arr[i][j];
        }
    }

    // int* pIntv = &intervals;
    int n = row;// DIM(intervals);
    printf("sizeof(intervals)=%d,sizeof(*intervals)=%d,sizeof(**intervals)=%d\n", \
        sizeof(intervals), sizeof(*intervals), sizeof(**intervals)); // ���ӣ���
    printf("[main2] (int**) -- row=%d, col=%d\n", DIM(intervals), DIM(intervals[0]));

    int* intervalsColSize = (int*)malloc(sizeof(int));
    int leftCnt = removeCoveredIntervals(intervals, row, intervalsColSize);
//    int intervalsColSize = 0; // todo: test
//    int leftCnt = removeCoveredIntervals(intervals, row, &intervalsColSize);
    printf("leftCnt=%d\n", leftCnt);
}