#include <stdio.h>
#include <stdlib.h>
// #include <math.h>
// #include <string.h>
// #include <limits.h>
// #include <stdbool.h>

// q89.c ���ױ���

 // v1: ������һ��i-1��res����׺m�����ִ𰸣����������λ��1��res�Ӻ���ǰ��¼��
int* grayCode1(int n, int* returnSize) {
    int* res = (int*)calloc(1 << n, sizeof(int)); // 1<<n, ��2^n
    int curCnt = 0;
    res[curCnt++] = 0; // ���⣬��һ�������� 0

    // ģ��
    for (int i = 1; i <= n; i++) {
        curCnt <<= 1; // v1: ����(i+1��)�Ĵ���
        int m = curCnt;
        // printf("1) curCnt = m = %d, i = %d\n", m, i);
        for (int j = m - 1; j >= m / 2; j--) { // ����һ��ģ��õ���res[0~m-1]�У������λ��1
            // printf("\t2) j=%d, m - 1 - j = %d, (1 << (i-1) = %d\n", j, m-1-j, 1 << (i-1));
            res[j] = res[m - 1 - j] | (1 << (i - 1));
            // printf("\t3) res[%d]=%d, res[%d] = %d, (1 << (i-1) = %d\n", j, res[j], m -1- j, res[m -1- j], 1 << (i-1));

        }
    }

    *returnSize = curCnt;
    return res;
}


// v2 ������һ��i-1��res����׺m�����ִ𰸣����������λ��1��res˳���¼��
int* grayCode(int n, int* returnSize) {
    int* res = (int*)calloc(1 << n, sizeof(int)); // 1<<n, ��2^n
    int curCnt = 0;
    res[curCnt++] = 0; // ���⣬��һ�������� 0

    // ģ��
    for (int i = 1; i <= n; i++) {
        int m = curCnt;
        // printf("1) curCnt = m = %d, i = %d\n", m, i);
        for (int j = 0; j < m; j++) { // ����һ��ģ��õ���res[0~m-1]�У������λ��1
            // printf("\t2) m + j=%d, m - 1 - j = %d, (1 << (i-1) = %d\n", m + j, m-1-j, 1 << (i-1));
            res[m + j] = res[m - 1 - j] | (1 << (i - 1)); // idx��Ϊ2*m(����һ��curCnt)-1
            // printf("\t3) res[%d]=%d, res[%d] = %d, (1 << (i-1) = %d\n", m + j, res[m + j], m - 1- j, res[m -1- j], 1 << (i-1));

        }
        curCnt <<= 1; // v2: ��һ��(n+1��)�Ĵ���
    }

    *returnSize = curCnt;
    return res;
}

int main()
{
    int n = 3;
    int returnSize = 0;
    int* res = grayCode(n, &returnSize);
    printf("returnSize = %d\n", returnSize);
    for (int i = 0; i < returnSize; i++) {
        printf("%d ", res[i]); // 0 1 3 2 6 7 5 4
    }
    printf("\n");
    return 0;
}
/*
1
0,1

2
0,1,3,2
00, 01; 11, 10
N = 2^2 = 4
(1�𰸻����ϣ������λ��1)

3
0,1,3,2,            6,7,5,4
N = 2^3 = 8
(2�𰸻����ϣ������λ��1)
000, 001; 011, 010; 110, 111, 101, 100
����
n=3ʱ��res[4]=res[3] | (1<<2)
res[7]= res[0] | (1<<2)

*/