#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <limits.h>

int maxSatisfied(int* customers, int n, int* grumpy, int m, int minutes) {
    int curSum = 0; // n == m == DIM(customers)
    for (int i = 0; i < n; ++i) {
        if (grumpy[i] == 0) {// 老板不生气
            curSum += customers[i];
        }
    }
    int left = 0, right = 0, maxSum = curSum;
    while (right < n) {
        if (grumpy[right] == 1) {// 老板生气 -> 不生气
            curSum += customers[right];
        }
        ++right;
        while (right - left >= minutes) { // 窗口缩小
            maxSum = fmax(maxSum, curSum);
            if (grumpy[left] == 1) {// 老板生气 -> 弹出窗口
                curSum -= customers[left];
            }
            ++left;
        }
    }
    return maxSum;
}