#include <stdio.h>
#include <stdlib.h>
// #include <math.h>
// #include <string.h>
// #include <limits.h>
// #include <stdbool.h>

// q89.c 格雷编码

 // v1: 根据上一轮i-1的res，后缀m个本轮答案：依次逆序高位置1（res从后往前记录）
int* grayCode1(int n, int* returnSize) {
    int* res = (int*)calloc(1 << n, sizeof(int)); // 1<<n, 即2^n
    int curCnt = 0;
    res[curCnt++] = 0; // 由题，第一个整数是 0

    // 模拟
    for (int i = 1; i <= n; i++) {
        curCnt <<= 1; // v1: 本轮(i+1后)的答案数
        int m = curCnt;
        // printf("1) curCnt = m = %d, i = %d\n", m, i);
        for (int j = m - 1; j >= m / 2; j--) { // 再上一轮模拟得到的res[0~m-1]中，逆序高位置1
            // printf("\t2) j=%d, m - 1 - j = %d, (1 << (i-1) = %d\n", j, m-1-j, 1 << (i-1));
            res[j] = res[m - 1 - j] | (1 << (i - 1));
            // printf("\t3) res[%d]=%d, res[%d] = %d, (1 << (i-1) = %d\n", j, res[j], m -1- j, res[m -1- j], 1 << (i-1));

        }
    }

    *returnSize = curCnt;
    return res;
}


// v2 根据上一轮i-1的res，后缀m个本轮答案：依次逆序高位置1（res顺序记录）
int* grayCode(int n, int* returnSize) {
    int* res = (int*)calloc(1 << n, sizeof(int)); // 1<<n, 即2^n
    int curCnt = 0;
    res[curCnt++] = 0; // 由题，第一个整数是 0

    // 模拟
    for (int i = 1; i <= n; i++) {
        int m = curCnt;
        // printf("1) curCnt = m = %d, i = %d\n", m, i);
        for (int j = 0; j < m; j++) { // 再上一轮模拟得到的res[0~m-1]中，逆序高位置1
            // printf("\t2) m + j=%d, m - 1 - j = %d, (1 << (i-1) = %d\n", m + j, m-1-j, 1 << (i-1));
            res[m + j] = res[m - 1 - j] | (1 << (i - 1)); // idx和为2*m(即上一轮curCnt)-1
            // printf("\t3) res[%d]=%d, res[%d] = %d, (1 << (i-1) = %d\n", m + j, res[m + j], m - 1- j, res[m -1- j], 1 << (i-1));

        }
        curCnt <<= 1; // v2: 下一轮(n+1后)的答案数
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
(1答案基础上，逆序高位置1)

3
0,1,3,2,            6,7,5,4
N = 2^3 = 8
(2答案基础上，逆序高位置1)
000, 001; 011, 010; 110, 111, 101, 100
即：
n=3时，res[4]=res[3] | (1<<2)
res[7]= res[0] | (1<<2)

*/