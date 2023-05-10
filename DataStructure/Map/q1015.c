#include <stdio.h>
#include <stdlib.h>
// #include <math.h>
// #include <string.h>
// #include <limits.h>
// #include <stdbool.h>

// q1015.c 可被 K 整除的最小整数
// 1 <= K <= 10^5

// 法1：数组代替哈希Set
int smallestRepunitDivByK(int k) {
    int mod = 1 % k, oneCnt = 1;
    // 数组代替哈希/Set
    int modKSet[100001] = { 0 }; // 由题，1 <= k <= 10^5
    modKSet[mod] = 1;
    while (mod != 0) {
        // 遍历全为1的数字，再取余k
        mod = (mod * 10 + 1) % k;
        if (modKSet[mod] > 0) {
            return -1; // 余数相同，存在循环，且均无法整除
        }
        modKSet[mod]++;
        oneCnt++;
    }
    return oneCnt;
}

// 法2：数学
int smallestRepunitDivByK_math(int k) {
    // 1) 当 k 为 2或者 5 的倍数时，能够被 k 整除的数字末尾一定不为 1，所以此时一定无解。
    // 如果 k 是偶数或者是 5 的倍数，则无法整除，直接返回 -1
    if (k % 2 == 0 || k % 5 == 0) {
        return -1;
    }
    // 2) 否则，一定有解
    int resid = 1 % k, oneCnt = 1;
    while (resid != 0) { // 避免溢出：每轮都mod k！！！
        resid = (resid * 10 + 1) % k;
        oneCnt++;
    }
    return oneCnt;
}

// 法3（WA）溢出
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
    int res = smallestRepunitDivByK(k); // 最小的答案是 n = 111，其长度为 3。
    printf("res=%d\n", res);
    return 0;
}