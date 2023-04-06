#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>

// ��1��������
int* nextGreaterElements_BF(int* nums, int n, int* returnSize) {
    int* res = (int*)calloc(n, sizeof(int));
    for (int i = 0; i < n; i++) {
        int j = (i + 1) % n;
        while (j != i) {
            if (nums[j] > nums[i]) {
                res[i] = nums[j];
                break;
            }
            j = (j + 1) % n;
        }
        if (j == i) res[i] = -1;
    }
    *returnSize = n;
    return res;
}

// ��2: ����ջ
/**
1. �ݼ�ջ
    - ��ǰԪ��nums[PEEK]��������һ������Ԫ�أ�nums[i]�����¼ & ��ջ
    - ��ǰԪ��nums[PEEK]��������һ����СԪ�أ�nums[i]������ջ���ݼ�ջ��
2. ѭ������
    - Ϊ��ģ��ѭ�����飬��Ҫ���±�idx % n��ջ��������Ϊn*2��������
 */
#define PEEK (stk[top-1])
int* nextGreaterElements_MonoStack(int* nums, int n, int* returnSize) {
    int* res = (int*)calloc(n, sizeof(int));
    for (int i = 0; i < n; i++) {
        res[i] = -1; // ��ʼ����memset��intֻ��ȫ�ָ�ֵ0
    }
    // ����ջ-�ݼ�ջ
    int* stk = (int*)calloc(n * 2, sizeof(int)); // ��ѭ������ ��ջ����=n*2���״�
    int top = 0;

    for (int i = 0; i < n * 2; i++) { // ѭ������: n*2, �±�idx % n
        // ��ǰԪ��nums[PEEK]����һ������Ԫ�أ�nums[i]�����¼ & ��ջ
        while (top != 0 && nums[PEEK % n] < nums[i % n]) {
            res[PEEK % n] = nums[i % n];
            --top; // ��ջ
        }
        stk[top++] = i % n;
    }
    *returnSize = n;
    return res;
}