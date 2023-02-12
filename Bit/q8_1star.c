//C�ж������ͬ��java��������

#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <ctype.h>

bool overflow(int res, int mod) {
    // Java�ж����: res * 10 / 10 != res��C����һ����True�� || (res == Integer.MAX_VALUE / 10 && num > 7)
    // C �ж������
    return res < INT_MIN / 10 || res> INT_MAX / 10
        || (res == INT_MAX / 10 && mod > 7);
}
int myAtoi(char* s) {
    if (!s) return 0;
    int n = strlen(s);
    int i = 0;
    while (i < n && s[i] == ' ') i++; // 1. ����ǰ���ո�
    if (i >= n) return 0;

    int flag = 1;
    if (s[i] == '-') {
        flag = -1;
        i++;
    }
    else if (s[i] == '+') {
        i++;
    }
    if (i >= n || s[i] == '+' || s[i] == '-') {
        return 0;
    }

    int res = 0;
    while (i < n && isdigit(s[i])) {// 0 <= s[i] && s[i] <= 9
        int num = s[i] - '0'; // ����©����
        if (overflow(res, num)) {
            return flag > 0 ? INT_MAX : INT_MIN;
        }
        res = res * 10 + num;
        i++;
    }
    return flag * res;
}

 int main()
 {
     int x = INT_MAX / 10 + 10; // x > INT_MAX / 10
     // C����ΪTrue���������: 214748374, 214748374��
     // �� JavaΪFalse��214748374, -214748355��
     printf("%d, %d", x, x * 10 / 10);

     return 0;
 }