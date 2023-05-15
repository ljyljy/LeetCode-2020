#include <stdio.h>
#include <stdlib.h>
// #include <string.h>
// #include <stdbool.h>
// #include <math.h>
// #include <limits.h>

// q307v1. 区域和检索 - 数组可修改(TLE - 前缀和，类比q303)
typedef struct {
    int* nums;
    int* preSums;
    int n;
} NumArray;


NumArray* numArrayCreate(int* nums, int numsSize) {
    NumArray* obj = (NumArray*)calloc(1, sizeof(NumArray));
    obj->n = numsSize;
    obj->nums = (int*)calloc(numsSize, sizeof(int));
    obj->preSums = (int*)calloc(numsSize + 1, sizeof(int));

    for (int i = 0; i < numsSize; i++) {
        obj->nums[i] = nums[i];
    }

    for (int i = 1; i < numsSize + 1; i++) {
        obj->preSums[i] = obj->preSums[i - 1] + nums[i - 1];
    }
    return obj;
}

void numArrayUpdate(NumArray* obj, int index, int val) {
    obj->nums[index] = val; // index∈[0,n-1]

    for (int i = index + 1; i < obj->n + 1; i++) { // i = idx + 1 ∈ [1, n]
        obj->preSums[i] = obj->preSums[i - 1] + obj->nums[i - 1];
    }
}

int numArraySumRange(NumArray* obj, int left, int right) {
    return obj->preSums[right + 1] - obj->preSums[left];
}

void numArrayFree(NumArray* obj) {
    free(obj->nums);
    free(obj->preSums);
    free(obj);
}

int main()
{
    int nums[] = { 1, 3, 5 };
    int numsSize = sizeof(nums) / sizeof(nums[0]);
    NumArray* obj = numArrayCreate(nums, numsSize);
    int res1 = numArraySumRange(obj, 0, 2);
    printf("%d\n", res1); // 9
    numArrayUpdate(obj, 1, 2);
    int res2 = numArraySumRange(obj, 0, 2);
    printf("%d\n", res2); // 8
}

