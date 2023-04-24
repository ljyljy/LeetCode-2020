#include <stdio.h>
#include <stdlib.h>
// #include <stdbool.h>
// #include <string.h>
// #include <math.h>
#include <limits.h>

// q53.c
// 贪心
int maxSubArray(int* nums, int n) {
    int curSum = 0;
    int maxSum = INT_MIN;
    for (int i = 0; i < n; i++) {
        curSum += nums[i];
        // 记录目前的最大和（即使为负）
        if (curSum > maxSum) maxSum = curSum;
        if (curSum < 0) curSum = 0;
    }
    return maxSum;
}

int main() {
    int nums[] = { -2,1,-3,4,-1,2,1,-5,4 };
    int n = sizeof(nums) / sizeof(nums[0]);
    int res = maxSubArray(nums, n);
    printf("res = %d \n", res);
    return 0;
}
