#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>

// 法1-2：维护前缀最大值
bool isIdealPermutation(int* nums, int n) {
    int preMax = 0;
    for (int j = 2; j < n; j++) {
        preMax = fmax(preMax, nums[j - 2]);
        if (preMax > nums[j]) {
            return false;
        }
    }
    return true;
}