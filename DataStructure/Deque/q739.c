#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>


// ��1��������, TLE
int* dailyTemperatures_BF(int* temperatures, int n, int* returnSize) {
    int* res = (int*)calloc(n, sizeof(int));
    for (int i = 0; i < n; i++) {
        int j = i + 1;
        while (j < n) {
            if (temperatures[j] > temperatures[i]) {
                res[i] = j - i;
                break;
            }
            j++;
        }
        if (j == n) res[i] = 0;
    }
    *returnSize = n;
    return res;
}

// ��2: ����ջ
/**
1. �ݼ�ջ
    - ��ǰԪ��temperatures[PEEK]��������һ������Ԫ�أ�temperatures[i]�����¼ & ��ջ
    - ��ǰԪ��temperatures[PEEK]��������һ����СԪ�أ�temperatures[i]������ջ���ݼ�ջ��
 */
#define PEEK (stk[top-1])
int* dailyTemperatures(int* temperatures, int n, int* returnSize) {
    int* res = (int*)calloc(n, sizeof(int));
    // ����ջ-�ݼ�ջ
    int* stk = (int*)calloc(n, sizeof(int));
    int top = 0;

    for (int i = 0; i < n; i++) {
        // ��ǰԪ��temperatures[PEEK]����һ������Ԫ�أ�temperatures[i]�����¼ & ��ջ
        while (top != 0 && temperatures[PEEK] < temperatures[i]) {
            res[PEEK] = i - PEEK;
            --top; // ��ջ
        }
        stk[top++] = i;
    }
    *returnSize = n;
    return res;
}

int main() {
    int temperatures[] = { 73, 74, 75, 71, 69, 72, 76, 73 }; // 1 1 4 2 1 1 0 0
    int n = sizeof(temperatures) / sizeof(temperatures[0]);
    int returnSize;
    int* res = dailyTemperatures(temperatures, n, &returnSize);
    for (int i = 0; i < returnSize; i++) {
        printf("%d ", res[i]);
    }
    return 0;
}
