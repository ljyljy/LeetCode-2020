#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>

// 类比Q34
// 法1：特判答案在右边界处
int searchInsert(int* nums, int n, int target) {
    if (nums[n - 1] < target) return n;
    int start = 0, end = n - 1;
    // 在区间 nums[left..right] 里查找第 1 个【左边界】大于等于 target 的元素的下标
    while (start < end) {// [L, mid], [mid+1, R]
        int mid = start + end >> 1;
        if (nums[mid] == target) {
            return mid;
        }
        if (target < nums[mid]) {
            end = mid;
        }
        else start = mid + 1;
    }
    return start; // 若未找到target，则返回左边界（第 1 个【左边界】大于target的下标）
}

int main() {
    int nums[] = { 1,3,5,6 }, target = 7;
    int n = sizeof(nums) / sizeof(*nums);
    int res = searchInsert(nums, n, target);
    printf("%d\n", res); // 4

    target = 2;
    res = searchInsert(nums, n, target);
    printf("%d\n", res); // 1
}