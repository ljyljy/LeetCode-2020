#include <stdio.h>
#include <stdlib.h>
// #include <math.h>
// #include <string.h>
// #include <limits.h>
// #include <stdbool.h>

// q80.c, 类比q26, q27

// 法1（通用，推荐）：控制计数curCnt <= 2
int removeDuplicates(int* nums, int n) {
    int write = 1, read = 1, curCnt = 1;
    while (read < n) {
        if (nums[read] != nums[write - 1]) {
            nums[write++] = nums[read];
            curCnt = 1;
        }
        else if (curCnt < 2) { // 相同，但重复数<2
            curCnt++;
            nums[write++] = nums[read];
        }
        read++;
    }
    return write;
}

// 【常规 荐】法1：快慢指针 - 显式双指针
int removeDuplicates1(int* nums, int n) {
    if (n <= 2) return n;
    int write = 2, read = 2; // nums[0], nums[1]肯定不变，因此write从2起
    while (read < n) {
        if (nums[read] != nums[write - 2]) { // 上上次写入 vs 当前read
            nums[write++] = nums[read];
        }
        read++;
    }
    return write;
}

int main() {
    int nums[] = { 1, 1, 1, 2, 2, 3 };
    int n = removeDuplicates(nums, 6);
    for (int i = 0; i < n; i++) {
        printf("%d ", nums[i]);
    }
    printf("\nn=%d\n", n);
    return 0;
}