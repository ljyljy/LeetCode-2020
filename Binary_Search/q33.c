#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>

int search(int* nums, int n, int target) {
    int start = 0, end = n - 1;
    while (start + 1 < end) {
        int mid = start + end >> 1;
        if (nums[mid] == target) {
            return mid;
        }
        if (nums[start] < nums[mid]) {
            if (nums[start] <= target && target <= nums[mid]) {
                end = mid;
            }
            else start = mid;
        }
        else {
            if (nums[mid] <= target && target <= nums[end]) {
                start = mid;
            }
            else end = mid;
        }
    }
    if (nums[start] == target) return start;
    if (nums[end] == target) return end;
    return -1;
}

int main() {
    int nums[] = { 4,5,6,7,0,1,2 };
    int n = sizeof(nums) / sizeof(nums[0]);
    int target = 0;
    int ans = search(nums, n, target);
    printf("%d ", ans);
    return 0;
}