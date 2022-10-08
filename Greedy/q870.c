#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>
#include <stdbool.h>

static inline int cmp(const void* pa, const void* pb) {
    return ((int*)pa)[1] - ((int*)pb)[1];
}
// ̰��/˫ָ�� + �±�����(����תΪC)
int* advantageCount(int* nums1, int n1, int* nums2, int n2, int* returnSize) {
    int n = n1;
    int idxNum1[n][2], idxNum2[n][2]; // �±�����<i,num>����nums������ӳ���±꣩
    for (int i = 0; i < n; i++) {
        idxNum1[i][0] = i, idxNum1[i][1] = nums1[i];
        idxNum2[i][0] = i, idxNum2[i][1] = nums2[i];
    }
    qsort(idxNum1, n, sizeof(idxNum1[0]), cmp); // ����ά�����qsort��
    qsort(idxNum2, n, sizeof(idxNum2[0]), cmp);

    int* res = (int*)calloc(n, sizeof(int));
    // ˫ָ��/̰�ģ���nums1_max > nums2_max,��ѡ��nums1_max��
        //      ����ʹ��nums1_min"����ͷ"
    int left = 0, right = n - 1;// ���nums1
    for (int i = n - 1; i >= 0; i--) {
        int curMax2_idx = idxNum2[i][0], curMax2 = idxNum2[i][1];
        int min1 = idxNum1[left][1], max1 = idxNum1[right][1];
        if (max1 > curMax2) {
            res[curMax2_idx] = max1;
            right--;
        }
        else {
            res[curMax2_idx] = min1;
            left++;
        }
    }
    *returnSize = n;
    return res;
}