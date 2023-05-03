#include <stdio.h>
#include <stdlib.h>
// #include <string.h>
// #include <stdbool.h>
// #include <math.h>
// #include <limits.h>

// quickSort_test.c, 类比q75

void swap(int* a, int* b);

// 快排v1: 显式partition()返回mid下标（右闭）
int partition(int* nums, int start, int end);

void quickSort_v1(int* nums, int start, int end) {
    if (start >= end) return;
    int mid = partition(nums, start, end);
    quickSort_v1(nums, start, mid); // 右闭
    quickSort_v1(nums, mid + 1, end);
}

int partition(int* nums, int start, int end) {
    int pivot = nums[start];
    int i = start, j = end;
    while (i <= j) {
        while (i <= j && nums[i] < pivot) i++;
        while (i <= j && nums[j] > pivot) j--; //?严格不等于pivot! 否则，有重复元素将陷入死循环!
        if (i <= j) { // ?严格i<=j
            swap(&nums[i], &nums[j]);
            i++; j--;// ?勿忘i++; j--; 否则，会陷入死循环!
        }
    }
    swap(&nums[start], &nums[j]);
    return j; // 或i-1, 即分区mid下标（右闭）
}

void swap(int* a, int* b) {
    int tmp = *a;
    *a = *b;
    *b = tmp;
}

// 快排v2: 隐式partition()
void quickSort_v2(int* nums, int start, int end) {
    if (start >= end) return;
    if (start >= end) return;
    int i = start, j = end;
    int pivot = nums[(start + end) / 2]; // pivot取中间，避免最后swap([start], [j])
    while (i <= j) {
        while (i <= j && nums[i] < pivot) i++;
        while (i <= j && nums[j] > pivot) j--;
        if (i <= j) {
            swap(&nums[i], &nums[j]);
            i++; j--;
        }
    }
    // swap(&nums[start], &nums[j]); // 当pivot取nums[start]时, 需要
    quickSort_v2(nums, start, j); // 隐式partition[start, R], [L/R+1, end]
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