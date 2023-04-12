#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>

#define DIM(x) sizeof(x) / sizeof(*x)

int searchFirst(int* nums, int n, int target) {
    int start = 0, end = n - 1;
    while (start < end) {
        int mid = start + end >> 1; // [L, mid] [mid+1, R]
        if (target <= nums[mid]) {
            end = mid; //  相等，则继续找左区间
        }
        else start = mid + 1;
    }
    return nums[start] == target ? start : -1;
}


int searchLast(int* nums, int n, int target) {
    int start = 0, end = n - 1;
    while (start < end) {
        int mid = start + end + 1 >> 1; // [L, mid-1] [mid, R]
        if (target >= nums[mid]) {
            start = mid; // 相等，则继续找右区间
        }
        else end = mid - 1;
    }
    return nums[start] == target ? start : -1;
}


int* searchRange(int* nums, int n, int target, int* returnSize) {
    int* res = (int*)calloc(2, sizeof(int));
    res[0] = -1, res[1] = -1;
    *returnSize = 2;
    if (nums == NULL || n <= 0) return res;

    res[0] = searchFirst(nums, n, target);
    res[1] = searchLast(nums, n, target);

    return res;
}

int main() {
    int nums[] = { 5,7,7,8,8,10 }, target = 8;
    int n = DIM(nums), returnSize = 0;
    int* res = searchRange(nums, n, target, &returnSize);
    for (int i = 0; i < returnSize; i++) {
        printf("%d ", res[i]);
    }
}