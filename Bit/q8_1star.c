//C判断溢出不同于java！！！！

#include <stdbool.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

bool overFlow(int res, int num) {
    // Java判断溢出: res * 10 / 10 != res（C语言一定是True） || (res == Integer.MAX_VALUE / 10 && num > 7)
    // C 判断溢出：
    return (res < INT_MIN / 10 || res > INT_MAX / 10) || \
                (res == INT_MAX / 10 && num > 7);
}

int myAtoi(char * s){
    if (!s) return 0;
    int n = strlen(s);
    int i = 0;
    while (i < n && s[i] == ' ') i++;// 1. 跳过前导空格
    int flag = 1;
    if (i < n) {
        if (s[i] == '+') {
            i++;
        } else if (s[i] == '-') {
            flag = -1;
            i++;
        }
        if (i >= n || s[i] == '+' || s[i] == '-') {
            return 0;
        }
    }

    int res = 0;
    while (i < n && ('0' <= s[i] && s[i] <= '9')) {
        int num = s[i] - '0';
        if (overFlow(res, num)) {
            return flag>0? INT_MAX: INT_MIN;
        }
        res = res * 10 + num;
        i++;
    }

    return flag * res;
}

 int main()
 {
     int x = INT_MAX / 10 + 10; // x > INT_MAX / 10
     // C语言为True（二者相等: 214748374, 214748374）
     // 但 Java为False（214748374, -214748355）
     printf("%d, %d", x, x * 10 / 10);

     return 0;
 }