#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>
#include <stdbool.h>

// д����java����һ��

// ��ȣ�q915���ϳ��ӡ�q9_42
// ��ָ�㣬ʹ�÷ָ��ġ���ߵ�����������ֵ��С�ڵ��ڡ��ұߵ����������Сֵ����
// {0,...,k}max <= {k+1,...,n}min
int partitionDisjoint(int* nums, int n) {
    int dp_maxL[n];
    int dp_minR[n];
    dp_maxL[0] = nums[0];
    dp_minR[n - 1] = nums[n - 1];
    for (int i = 1; i < n; i++) {
        dp_maxL[i] = fmax(dp_maxL[i - 1], nums[i]);
    }
    for (int i = n - 2; i >= 0; i--) {
        dp_minR[i] = fmin(dp_minR[i + 1], nums[i]);
    }
    for (int i = 0; i + 1 < n; i++) {
        if (dp_maxL[i] <= dp_minR[i + 1]) {
            return i + 1; // len = idx+1
        }
    }
    return -1;
}