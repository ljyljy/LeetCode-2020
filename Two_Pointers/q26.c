#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>

int removeDuplicates(int* nums, int n) {
    if (n <= 1) return n;
    int write = 1, read = 1;// nums[0]肯定不变，因此write从1起
    while (read < n) {
        if (nums[write - 1] != nums[read]) {// 上一次写入 vs 当前read
            nums[write++] = nums[read];
        }
        read++;
    }
    return write;
}

int main() {
    int nums[] = { 1, 1, 2 };
    int n = removeDuplicates(nums, 3);
    for (int i = 0; i < n; i++) {
        printf("%d ", nums[i]);
    }
    printf("\nn=%d ", n);
    return 0;
}