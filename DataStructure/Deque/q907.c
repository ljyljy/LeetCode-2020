#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>

/*
ջʵ��1���Ƽ���
- ����
  - ��ʼ����top = 0
  - ��ջ���� stk[top-1], ����ҿ�������
  - ���пա� top == 0
  - ��pop/��ջ��top--
    - top--����ջ�������ᱻ����
  - ��push/��ջ��top++
- ����
  - q907, 20, 84, 32�� 496
*/
int sumSubarrayMins(int* nums, int n) {
    int left[n], right[n];
    int* stk = (int*)calloc(n, sizeof(int));
    int top = 0;  // ջ��Ϊ[top-1], ����ҿ���
    for (int i = 0; i < n; i++) {
        while (top != 0 && nums[stk[top - 1]] >= nums[i]) {
            top--; /* ��ջ */  // ���ֵ���ջ
        }
        left[i] = i - (top == 0 ? -1 : stk[top - 1]);
        stk[top++] = i;
    }
    // memset(stk, 0, sizeof(stk)); // ���ɣ���LC-���������
    top = 0; // stk.clear() - ��©��
    for (int i = n - 1; i >= 0; i--) {
        // �˴�����ȡ�ȣ��ϸ���ڣ�[peek]>[i], ���������д����ظ�, ��[1]��[1]
        while (top != 0 && nums[stk[top - 1]] > nums[i]) {
            top--;
        }
        right[i] = (top == 0 ? n : stk[top - 1]) - i;
        stk[top++] = i;
    }
    long long sum = 0;
    const long long MOD = 1e9 + 7;
    for (int i = 0; i < n; i++) {
        sum = (sum + (long long)nums[i] * left[i] * right[i]) % MOD;
    }
    return (int)sum;
}