#include <stdio.h>
#include <stdlib.h>
// #include <string.h>
#include <stdbool.h>
// #include <math.h>
// #include <limits.h>

// q81.c

int binSearch_onceKO(int* nums, int n, int target, int start, int end) {
    while (start < end) { // [L, mid-1] [mid, R]
        int mid = start + end >> 1;
        if (nums[mid] == target) {
            return mid;
        }
        /*
        通常升序序列：nums[mid] >= target, 则在mid左侧继续搜索。
        但本题旋转升序，因此需要找到‘升序的子数组’，保持单调递增性：
            - 如：关注[start ~ target ~ mid]的单调递增性，更新end = mid-1，继续在[start, mid-1]中搜索
            - 避免死循环：[start]与[mid]值不可相等！
        */
        // 分别在左右半段【L/R vs mid】中，找出包含target的升序的子数列
        if (nums[start] == nums[mid]) {
            start++;
            continue;
        }
        if (nums[start] < nums[mid]) {
            if (nums[start] <= target && target <= nums[mid]) {
                end = mid - 1;
            }
            else start = mid;
        }
        else { //  if (nums[start] > nums[mid]) 说明start处旋转过，而mid~end升序
            // 故另关注[mid ~ target ~ end]的单调递增性
            if (nums[mid] <= target && target <= nums[end]) {
                start = mid;
            }
            else end = mid - 1;
        }
    }
    if (nums[start] == target) return start;
    return -1;
}

bool search(int* nums, int n, int target) {
    int end = n - 1;
    while (end > 0 && nums[end] == nums[0]) end--;
    return binSearch_onceKO(nums, n, target, 0, end) != -1;
}

int main() {
    int nums[] = { 2,5,6,0,0,1,2 };
    int n = sizeof(nums) / sizeof(int);
    int target = 0;
    printf("%d\n", search(nums, n, target));

    target = 3;
    printf("%d\n", search(nums, n, target));
    return 0;
}