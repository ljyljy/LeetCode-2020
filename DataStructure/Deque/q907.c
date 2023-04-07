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
  - ��pop/��ջ��
    - --top����ջ�������ᱻ����
    - stk[--top]: ��ȡջ������ջ
  - ��push/��ջ��top++
- ����
  - q907, 20, 84, 32�� 496
*/

#define PEEK (stk[top - 1])
int sumSubarrayMins(int* nums, int n) {
    int cnt_R[n], cnt_L[n];
    int* stk = (int*)calloc(n, sizeof(int));
    int top = 0;  // ջ��Ϊ[top-1], ����ҿ���

    /**
    - 1���������ұ���[i]�����������飬��������"[i]���������С���������С��"������&��ջ��
      - ��ǰԪ��[PEEK]����������С��[i]��
        - ���ҵ��� ������[PEEK,i] �е���СԪ�� ��[i]��������ջ �����������������������飨̽����������߽�PEEK'�����Լ�������������(PEEK',i]�ĸ���
      - ֱ�� A)ջ�� �� B) [PEEK']��[i]С(���ֵ���ջ), ��ʱջ��Ԫ��[PEEK']��Ϊ����߽硿
        - B) ������[PEEK'+1,i]�е���СԪ��Ϊ[i]����������������[PEEK'+1 ~ i, i]����Ϊi-(PEEK'+1)+1, ��(i-PEEK')��
        - A) ����ջΪ�գ�˵����߽�PEEK'=-1��������[0,i]�е���СԪ��Ϊ[i], ��(i+1)��
    */
    for (int i = 0; i < n; i++) {
        while (top != 0 && nums[PEEK] >= nums[i]) {
          --top; /* ��ջ */  // ���ֵ���ջ
        }
        cnt_R[i] = i - (top == 0 ? -1 : PEEK); // R: ָ��[i]���������еġ���������С��Ԫ��
        stk[top++] = i;
    }
    // memset(stk, 0, sizeof(stk)); ���ɣ���stk��int*ָ�룬64λϵͳ�� ��ռ��СΪ8.
    top = 0; // stk.clear() - ��©��

    /**
    - 2������/�����������[i]�����������飬��������"[i]���������С���������С��"������&��ջ��
      - ��ǰԪ��[PEEK]����������С��[i]��
        - ���ҵ��� ������[i,PEEK] �е���СԪ�� ��[i]��������ջ �����������������������飨̽���������ұ߽�PEEK'�����Լ�������������[i,PEEK')�ĸ���
      - ֱ�� A)ջ�� �� B) [PEEK']��[i]С(���ֵ���ջ), ��ʱջ��Ԫ��[PEEK']��Ϊ���ұ߽硿
        - B) ������[i,PEEK'-1]�е���СԪ��Ϊ[i]����������������[i, i ~ PEEK'-1]����Ϊ(PEEK'-1)-i+1, ��(PEEK'-i)��
        - A) ����ջΪ�գ�˵���ұ߽�PEEK'=n��������[i,n-1]�е���СԪ��Ϊ[i], ��(n-i)��
    */
    // ��[i]��࿴����������������[i,n-1]����"[i]=��������С"�ĸ�������ջ��
    for (int i = n - 1; i >= 0; i--) {
        // ���״� �˴�����ȡ�ȣ��ϸ���ڣ�[peek]>[i], �����ظ�������1�������, ��[1]��[1]
        while (top != 0 && nums[PEEK] > nums[i]) {
            --top;
        }
        cnt_L[i] = (top == 0 ? n : PEEK) - i;
        stk[top++] = i;
    }
    long long sum = 0;
    const long long MOD = 1e9 + 7;
    for (int i = 0; i < n; i++) {
        sum = (sum + (long long)nums[i] * cnt_R[i] * cnt_L[i]) % MOD;
    }
    return (int)sum;
}

int main() {
    int nums[] = { 3, 1, 2, 4 };
    int n = sizeof(nums) / sizeof(nums[0]);
    int res = sumSubarrayMins(nums, n);
    printf("%d ", res);
    return 0;
}