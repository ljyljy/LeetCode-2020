
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
// #include <string.h>
// #include <limits.h>
#include <stdbool.h>

// q88. 合并两个有序数组

void merge(int* nums1, int nums1Size, int m, int* nums2, int nums2Size, int n) {
    int i = m - 1, j = n - 1, idx = nums1Size - 1;
    while (i >= 0 && j >= 0) {
        if (nums1[i] > nums2[j]) {
            nums1[idx--] = nums1[i];
            i--;
        }
        else {
            nums1[idx--] = nums2[j];
            j--;
        }
    }
    while (i >= 0 && idx >= 0) {
        nums1[idx--] = nums1[i--];
    }
    while (j >= 0 && idx >= 0) {
        nums1[idx--] = nums2[j--];
    }
}

void printArr(int* nums, int n, char* msg) {
    printf("%s\n", msg);
    for (int i = 0; i < n; i++) {
        printf("%d ", nums[i]);
    }
    printf("\n");
}
int main() {
    int nums1[] = { 1, 2, 3, 0, 0, 0 };
    int nums2[] = { 2, 5, 6 };
    int m = 3, n = 3;
    int nums1Size = sizeof(nums1) / sizeof(nums1[0]);
    int nums2Size = sizeof(nums2) / sizeof(nums2[0]);
    printArr(nums1, nums1Size, "before merge:");
    merge(nums1, nums1Size, m, nums2, nums2Size, n);
    printArr(nums1, nums1Size, "\nafter merge:");
    return 0;
}