
#include <stdio.h>
#include <stdlib.h>
// #include <stdbool.h>
// #include <string.h>
// #include <math.h>
// #include <limits.h>

void swap(int* x, int* y) {
    int tmp = *x;
    *x = *y;
    *y = tmp;
}

int firstMissingPositive(int* nums, int n) {
    for (int i = 0; i < n; i++) {
        while (nums[i] - 1 >= 0 && nums[i] - 1 < n &&
            nums[i] != nums[nums[i] - 1]) {
            swap(&nums[i], &nums[nums[i] - 1]);
        }
    }

    for (int i = 0; i < n; i++) {
        if (i + 1 != nums[i]) {
            return i + 1;
        }
    }
    return n + 1;
}

int main() {
    int nums[] = { 3, 4, -1, 1 };
    int n = sizeof(nums) / sizeof(nums[0]);
    printf("%d \n", firstMissingPositive(nums, n));
    return 0;
}