#include <stdio.h>
#include <stdlib.h>
// #include <math.h>
// #include <string.h>
// #include <limits.h>
// #include <stdbool.h>

// q1015.c �ɱ� K ��������С����
// 1 <= K <= 10^5

// ��1����������ϣSet
int smallestRepunitDivByK(int k) {
    int mod = 1 % k, oneCnt = 1;
    // ��������ϣ/Set
    int modKSet[100001] = { 0 }; // ���⣬1 <= k <= 10^5
    modKSet[mod] = 1;
    while (mod != 0) {
        // ����ȫΪ1�����֣���ȡ��k
        mod = (mod * 10 + 1) % k;
        if (modKSet[mod] > 0) {
            return -1; // ������ͬ������ѭ�����Ҿ��޷�����
        }
        modKSet[mod]++;
        oneCnt++;
    }
    return oneCnt;
}

// ��2����ѧ
int smallestRepunitDivByK_math(int k) {
    // 1) �� k Ϊ 2���� 5 �ı���ʱ���ܹ��� k ����������ĩβһ����Ϊ 1�����Դ�ʱһ���޽⡣
    // ��� k ��ż�������� 5 �ı��������޷�������ֱ�ӷ��� -1
    if (k % 2 == 0 || k % 5 == 0) {
        return -1;
    }
    // 2) ����һ���н�
    int resid = 1 % k, oneCnt = 1;
    while (resid != 0) { // ���������ÿ�ֶ�mod k������
        resid = (resid * 10 + 1) % k;
        oneCnt++;
    }
    return oneCnt;
}

// ��3��WA�����
int smallestRepunitDivByK_overFlow(int k) {
    long long num = 0;
    int oneCnt = 0;
    while (num < LLONG_MAX / 10) {
        num = num * 10 + 1;
        oneCnt++;
        if (num % k == 0) {
            // printf("num=%d, i = %d, res=%d\n", num, (int)i, (int)i+1);
            return oneCnt; // getOneCnt(num);
        }
    }
    return -1;
}

int main()
{
    int k = 3;
    int res = smallestRepunitDivByK(k); // ��С�Ĵ��� n = 111���䳤��Ϊ 3��
    printf("res=%d\n", res);
    return 0;
}