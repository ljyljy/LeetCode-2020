#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
// #include <string.h>
// #include <math.h>
// #include <limits.h>

// q50.c

// 坑：n = INT_MIN时，转换正数会溢出，因此需要先转换为long类型

// 法1：迭代
double myPow(double x, int n) {
    if (n == 0) return 1.0;
    if (x == 0) return 0;
    double res = 1.0;

    long N = n; // 避免n = INT_MIN时，转换正数溢出

    if (n < 0) {
        x = 1.0 / x;
        N = -N;
    }
    while (N > 0) {
        if ((N & 1) == 1) { // N为奇数
            res *= x;
        }
        x *= x;
        N >>= 1; // N /= 2
    }
    return res;
}

// 递归
double helper(double x, long n) {
    if (n == 0) return 1.0;
    double curAns = helper(x, n / 2);
    curAns *= curAns;
    if (n % 2 == 1) curAns *= x;
    return curAns;
}

double myPow_2(double x, int n) {
    if (n == 0) return 1.0;
    if (x == 0) return 0;
    double res = 1.0;

    long N = n; // 避免n = INT_MIN时，转换正数溢出
    if (n < 0) {
        x = 1.0 / x;
        N = -N;
    }
    return helper(x, N);
}

int main() {
    double x = 2.0;
    int n = -2147483648;
    printf("%lf\n", myPow(x, n));
    printf("%lf\n", myPow_2(x, n));

    x = 2.0, n = 10;
    printf("%lf\n", myPow(x, n));
    printf("%lf\n", myPow_2(x, n));

    x = 2.1, n = 3;
    printf("%lf\n", myPow(x, n));
    printf("%lf\n", myPow_2(x, n));

    x = 2.0, n = -2;
    printf("%lf\n", myPow(x, n));
    printf("%lf\n", myPow_2(x, n));
    return 0;
}