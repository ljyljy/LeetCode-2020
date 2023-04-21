#include <stdlib.h>
#include <stdio.h>
// #include <stdbool.h>
// #include <math.h>
// #include <string.h>
// #include <limits.h>
// #include <ctype.h>

// q303. Range Sum Query - Immutable
typedef struct {
    int* nums;
    int* preSum;
    int n;
} NumArray;


NumArray* numArrayCreate(int* nums, int n) {
    NumArray* obj = (NumArray*)calloc(1, sizeof(NumArray));
    obj->nums = (int*)calloc(10001, sizeof(int)); // 题目：1 <= nums.length <= 10^4
    obj->preSum = (int*)calloc(10002, sizeof(int)); // 题目：1 <= nums.length <= 10^4
    memcpy(obj->nums, nums, n * sizeof(int));
    for (int i = 1; i <= n; i++) { // obj->preSum[0] = 0
        obj->preSum[i] = obj->preSum[i - 1] + nums[i - 1];
    }
    return obj;
}

int numArraySumRange(NumArray* obj, int left, int right) {
    return obj->preSum[right + 1] - obj->preSum[left];
}

void numArrayFree(NumArray* obj) {
    if (obj->nums) free(obj->nums);
    if (obj->preSum) free(obj->preSum);
    free(obj);
}

int main() {
    int nums[] = { -2, 0, 3, -5, 2, -1 };
    int n = sizeof(nums) / sizeof(nums[0]);
    NumArray* obj = numArrayCreate(nums, n);
    int left = 0, right = 2;
    int ret = numArraySumRange(obj, left, right);
    printf("ret = %d\n", ret);

    left = 2, right = 5;
    ret = numArraySumRange(obj, left, right);
    printf("ret = %d\n", ret);

    left = 0, right = 5;
    ret = numArraySumRange(obj, left, right);
    printf("ret = %d\n", ret);
    numArrayFree(obj);
    return 0;
}