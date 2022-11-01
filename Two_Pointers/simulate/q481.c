#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>

int magicalString(int n) {
    int nums[n + 2];
    memset(nums, 0, sizeof(nums)); // 赋0
    nums[0] = 1, nums[1] = 2, nums[2] = 2;
    int last = 2; // nums[2]为2，接下来填j=3处,需要交替（1、2交替）
    for (int i = 2, j = 3; i < n && j < n; i++) {
        int nxt = last == 2 ? 1 : 2;
        nums[j++] = nxt;
        if (nums[i] == 2) nums[j++] = nxt;
        last = nxt;
    }
    int cntOne = 0;
    for (int i = 0; i < n; i++) {
        if (nums[i] == 1) cntOne++;
    }
    return cntOne;
}