#include <stdio.h>
#include <stdlib.h>
// #include <string.h>
// #include <stdbool.h>
// #include <math.h>
// #include <limits.h>

// quickSort_test.c, ���q75

void swap(int* a, int* b);

// ����v1: ��ʽpartition()����mid�±꣨�ұգ�
int partition(int* nums, int start, int end);

void quickSort_v1(int* nums, int start, int end) {
    if (start >= end) return;
    int mid = partition(nums, start, end);
    quickSort_v1(nums, start, mid); // �ұ�
    quickSort_v1(nums, mid + 1, end);
}

int partition(int* nums, int start, int end) {
    int pivot = nums[start];
    int i = start, j = end;
    while (i <= j) {
        while (i <= j && nums[i] < pivot) i++;
        while (i <= j && nums[j] > pivot) j--; //?�ϸ񲻵���pivot! �������ظ�Ԫ�ؽ�������ѭ��!
        if (i <= j) { // ?�ϸ�i<=j
            swap(&nums[i], &nums[j]);
            i++; j--;// ?����i++; j--; ���򣬻�������ѭ��!
        }
    }
    swap(&nums[start], &nums[j]);
    return j; // ��i-1, ������mid�±꣨�ұգ�
}

void swap(int* a, int* b) {
    int tmp = *a;
    *a = *b;
    *b = tmp;
}

// ����v2: ��ʽpartition()
void quickSort_v2(int* nums, int start, int end) {
    if (start >= end) return;
    if (start >= end) return;
    int i = start, j = end;
    int pivot = nums[(start + end) / 2]; // pivotȡ�м䣬�������swap([start], [j])
    while (i <= j) {
        while (i <= j && nums[i] < pivot) i++;
        while (i <= j && nums[j] > pivot) j--;
        if (i <= j) {
            swap(&nums[i], &nums[j]);
            i++; j--;
        }
    }
    // swap(&nums[start], &nums[j]); // ��pivotȡnums[start]ʱ, ��Ҫ
    quickSort_v2(nums, start, j); // ��ʽpartition[start, R], [L/R+1, end]
    quickSort_v2(nums, i, end);
}



int main() {
    int nums[] = { 2,3,2,1,5,0 };
    int n = sizeof(nums) / sizeof(*nums);
    quickSort_v1(nums, 0, n - 1);
    for (int i = 0; i < n; i++) {
        printf("%d ", nums[i]);
    }
    printf("\n");

    int nums2[] = { 2,3,2,1,5,0 };
    quickSort_v2(nums2, 0, n - 1);
    for (int i = 0; i < n; i++) {
        printf("%d ", nums2[i]);
    }
    printf("\n");
    return 0;
}