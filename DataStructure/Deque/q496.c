#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>

// ��1��������O(nq*n)
int* nextGreaterElement_BF(int* nums_query, int nq, int* nums, int n, int* returnSize) {
    int* res = (int*)calloc(nq, sizeof(int));
    for (int i = 0; i < nq; i++) {
        int j = 0;
        while (j < n && nums[j] != nums_query[i]) j++; // nums�в���curNum���±�Ϊj
        // �˳�while��j>=nԽ�� �� nums[j] == curNum
        while (j < n && nums[j] <= nums_query[i]) j++; // nums�в���nums[k] > curNum����Ϊans
        res[i] = j >= n ? -1 : nums[j];
    }
    *returnSize = nq;
    return res;
}


// ��2������ջ-�ݼ�ջ��O(nq+n)
/**
����1�� �жϵ���ջ�ǵ���or�ݼ�ջ��
a) ��ĳ��Ԫ��[x,��peek]�ұߵ�һ���������Ԫ��[i]��
    - �����ǰԪ��[i]>ջ��[peek]���������ظ�Ԫ�أ�, ��ջ���ҵ��ұߵ�һ���������Ԫ�أ���Ϊ[i]����ջ����ջ��
        - ��¼�� res[nums[peek]]=nums[i], ����������nums������query��������Ҫmap��¼��
            - map[nums[peek]]=nums[i]��������query��res[i]=map[query[i]]
    - �����ж�ջ��Ԫ�أ�ֱ��ջ��Ԫ�ر�[i]С����[i]��ջ������ջһ���ǵݼ��ġ�
b) vs ��ߵ�һ������С-�ݼ�ջ, q1856/907
    - ջ��Ԫ��[peek]�ǵ�ǰԪ��[i]��ߵ�һ������С��Ԫ�أ���Ϊ[i]����ջ����ջ�������ж�ջ��Ԫ�أ�ֱ��ջ��Ԫ�ر�[i]����[i]��ջ������ջһ���ǵ����ġ�
 */
# define PEEK (stk[top-1])
int* nextGreaterElement(int* nums_query, int nq, int* nums, int n, int* returnSize) {
    int* res = (int*)calloc(nq, sizeof(int));
    int* stk = (int*)calloc(n, sizeof(int));
    int top = 0; // ջ��Ϊ[top-1], ����ҿ���
    int* map = (int*)calloc(10001, sizeof(int));  // ��Ŀ��nums[i]�ķ�ΧΪ[1,10000]

    for (int i = 0; i < n; i++) {
        while (top != 0 && nums[PEEK] < nums[i]) {
            map[nums[PEEK]] = nums[i]; // Map��¼���н����������Ϊres
            --top; /* ��ջ */
        }
        stk[top++] = i;
    }
    for (int i = 0; i < nq; i++) {
        res[i] = map[nums_query[i]] == 0 ? -1 : map[nums_query[i]];
    }
    *returnSize = nq;
    return res;
}

int main() {
    int nums_query[] = { 4, 1, 2 };
    int nq = sizeof(nums_query) / sizeof(nums_query[0]);
    int nums[] = { 1, 3, 4, 2 };
    int n = sizeof(nums) / sizeof(int);
    int returnSize;
    int* res = nextGreaterElement(nums_query, nq, nums, n, &returnSize);
    for (int i = 0; i < returnSize; i++) {
        printf("%d ", res[i]);
    }
    return 0;
}