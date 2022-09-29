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
    if ((dividend ^ divisor) < 0) { // ��������һ��Ϊ����
        sign = -1;
    }
    if (divisor == INT_MIN) { // �����߽�ֵ���⴦��
        if (dividend == INT_MIN) {
            return 1;
        }
        else {
            return 0;
        }
    }
    if (dividend == INT_MIN) { // �������߽�ֵ���⴦��
        if (divisor == -1) {
            return INT_MAX;
        }
        else if (divisor == 1) {
            return INT_MIN;
        }
        dividend += abs(divisor); // ��ִ��һ�μӲ���������absת�����
        cnt++;
    }
    int a = abs(dividend);
    int b = abs(divisor);
    while (a >= b) {
        int c = 1;
        int s = b;
        // ��ָ�������ٱƽ����Ա���ִ�г�ʱ
        while (s < (a >> 1)) { // �ƽ���һ�룬ͬʱ�������
            s += s;
            c += c;
        }
        cnt += c;
        a -= s;
    }
    return (sign == -1) ? -cnt : cnt;
}

// JAVA�汾 - ��С��LC��TLE���߽翼�Ǳ�Java�࣡��
int div_v1(int dividend, int divisor);
int divide_v1(int dividend, int divisor) {
    if (dividend == INT_MIN && divisor == -1) {
        return INT_MAX;
    }
    int sign = 1;

    if ((dividend ^ divisor) < 0) { // ��������һ��Ϊ����
        sign = -1;
    }
    if (divisor == INT_MIN) { // �����߽�ֵ���⴦��
        return dividend == INT_MIN ? 1 : 0;
    }
    if (dividend == INT_MIN) { // �������߽�ֵ���⴦��
        if (divisor == -1) {
            return INT_MAX;
        }
        else if (divisor == 1) {
            return INT_MIN;
        }
    }
    // ȫ��ת���ɸ�������ֹ���
    dividend = dividend > 0 ? -dividend : dividend;
    divisor = divisor > 0 ? -divisor : divisor;

    int ans = div_v1(dividend, divisor);
    return sign > 0 ? ans : -ans;
}

// ���˷���ע�ⶼ�Ǹ����ˣ��Ƚϵ�ʱ���������෴��(|divided| >= |divisor|)
    // �򵥵���⣬����ÿ�μ�ȥ������2^x.����(tmp == divisor*(2^x) = divisor*cnt)��ʣ�µĲ��ּ����������Ĺ������
int div_v1(int dividend, int divisor) {
    /* unsigned */ int ans = 0; // ��TLE��
    while (dividend <= divisor) {
        /* unsigned */  int tmp = divisor, cnt = 1; // ?һ����while�ڣ�ÿ�����¸�ֵ��
        // ����ע�ⲻҪд�� tmp + tmp >= dividend������д�ӷ��п��ܻ����������ѭ��
        // ��while����if��
        while (tmp >= dividend - tmp) { // ����tmp + tmp <= dividend���ߵ� & ����
            // tmp �� cnt ÿ�ζ�Ӧ����һ����*2�������Խб���
            cnt <<= 1; // �� 2^x // [LC���⣺cnt *= 2]
            tmp <<= 1; // ��
        }
        // ��������ȥ������ 2^x ������Ϊ�µı�����
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