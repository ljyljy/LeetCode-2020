#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>

// v0
int divide(int dividend, int divisor) {
    int cnt = 0;
    int sign = 1;
    if ((dividend ^ divisor) < 0) { // 两数任意一个为负数
        sign = -1;
    }
    if (divisor == INT_MIN) { // 除数边界值特殊处理
        if (dividend == INT_MIN) {
            return 1;
        }
        else {
            return 0;
        }
    }
    if (dividend == INT_MIN) { // 被除数边界值特殊处理
        if (divisor == -1) {
            return INT_MAX;
        }
        else if (divisor == 1) {
            return INT_MIN;
        }
        dividend += abs(divisor); // 先执行一次加操作，避免abs转换溢出
        cnt++;
    }
    int a = abs(dividend);
    int b = abs(divisor);
    while (a >= b) {
        int c = 1;
        int s = b;
        // 需指数级快速逼近，以避免执行超时
        while (s < (a >> 1)) { // 逼近至一半，同时避免溢出
            s += s;
            c += c;
        }
        cnt += c;
        a -= s;
    }
    return (sign == -1) ? -cnt : cnt;
}

// JAVA版本 - 【小心LC会TLE！边界考虑比Java多！】
int div_v1(int dividend, int divisor);
int divide_v1(int dividend, int divisor) {
    if (dividend == INT_MIN && divisor == -1) {
        return INT_MAX;
    }
    int sign = 1;

    if ((dividend ^ divisor) < 0) { // 两数任意一个为负数
        sign = -1;
    }
    if (divisor == INT_MIN) { // 除数边界值特殊处理
        return dividend == INT_MIN ? 1 : 0;
    }
    if (dividend == INT_MIN) { // 被除数边界值特殊处理
        if (divisor == -1) {
            return INT_MAX;
        }
        else if (divisor == 1) {
            return INT_MIN;
        }
    }
    // 全部转换成负数，防止溢出
    dividend = dividend > 0 ? -dividend : dividend;
    divisor = divisor > 0 ? -divisor : divisor;

    int ans = div_v1(dividend, divisor);
    return sign > 0 ? ans : -ans;
}

// 倍乘法，注意都是负数了，比较的时候与正数相反！(|divided| >= |divisor|)
    // 简单点理解，就是每次减去除数的2^x.倍数(tmp == divisor*(2^x) = divisor*cnt)，剩下的部分继续按这样的规则继续
int div_v1(int dividend, int divisor) {
    /* unsigned */ int ans = 0; // 【TLE】
    while (dividend <= divisor) {
        /* unsigned */  int tmp = divisor, cnt = 1; // ?一定在while内！每轮重新赋值！
        // 这里注意不要写成 tmp + tmp >= dividend，这样写加法有可能会溢出导致死循环
        // 是while！非if！
        while (tmp >= dividend - tmp) { // 正数tmp + tmp <= dividend，颠倒 & 防溢
            // tmp 和 cnt 每次对应增加一倍（*2），所以叫倍增
            cnt <<= 1; // 即 2^x // [LC判题：cnt *= 2]
            tmp <<= 1; // 即
        }
        // 被除数减去除数的 2^x 倍数做为新的被除数
        dividend -= tmp;
        ans += cnt;
    }
    return (int)ans;
}

int main() {
    int dividend = 1, divisor = 1;
    int ans = divide_v1(dividend, divisor);
    printf("ans = %d / %d = %d\n", dividend, divisor, ans);


    dividend = 10, divisor = 3;
    ans = divide_v1(dividend, divisor);
    printf("ans = %d / %d = %d\n", dividend, divisor, ans);

    dividend = 7, divisor = -3;
    ans = divide_v1(dividend, divisor);
    printf("ans = %d / %d = %d\n", dividend, divisor, ans);

    dividend = INT_MIN, divisor = -1;
    ans = divide_v1(dividend, divisor);
    printf("ans = %d / %d = %d\n", dividend, divisor, ans);
}