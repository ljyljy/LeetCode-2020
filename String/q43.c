#include <stdio.h>
#include <stdlib.h>
// #include <stdbool.h>
#include <string.h>
// #include <math.h>
// #include <limits.h>

// q43.c
void swap(char* s, int n) {
    for (int i = 0; i < n / 2; i++) {
        char tmp = s[i];
        s[i] = s[n - 1 - i];
        s[n - 1 - i] = tmp;
    }
}

// 对两个字符串数字进行逐位相加，返回字符串形式的和, 见Q415
char* addStrings(char* num1, char* num2) {
    int n1 = strlen(num1), n2 = strlen(num2);
    int carry = 0;
    int curLen = 0;
    char* res = (char*)calloc(n1 + n2 + 1, sizeof(char));
    for (int i = n1 - 1, j = n2 - 1; (i >= 0 || j >= 0 || carry); i--, j--) {
        int num1_i = i >= 0 ? num1[i] - '0' : 0;
        int num2_j = j >= 0 ? num2[j] - '0' : 0;
        int tmp = num1_i + num2_j + carry;
        int sum = tmp % 10;
        res[curLen++] = sum + '0';
        carry = tmp / 10;
    }
    swap(res, curLen);
    res[curLen] = '\0'; // 勿忘！
    return res;
}

char* multiply(char* num1, char* num2) {
    if (num1[0] == '0' || num2[0] == '0') return "0";
    int n1 = strlen(num1), n2 = strlen(num2);
    char* res = (char*)calloc(n1 + n2 + 1, sizeof(char));
    // num2 逐位与 num1 相乘
    for (int j = n2 - 1; j >= 0; j--) {
        char* tmpAns = (char*)calloc(n1 + n2 + 1, sizeof(char));
        int curLen = 0;
        for (int i = j + 1; i <= n2 - 1; i++) tmpAns[curLen++] = '0';

        int num2_j = num2[j] - '0';
        int carry = 0;
        // num2 的第j位数字 num2_j 与 num1 相乘(逐位，从后往前)
        for (int i = n1 - 1; (i >= 0 || carry); i--) {
            int num1_i = i >= 0 ? num1[i] - '0' : 0;
            int tmp = num1_i * num2_j + carry;
            int mul = tmp % 10;
            tmpAns[curLen++] = mul + '0';
            carry = tmp / 10;
        }
        // tmpAns 逆序
        swap(tmpAns, curLen);
        tmpAns[curLen] = '\0'; // 勿忘！
        res = addStrings(res, tmpAns);
    }
    return res;
}

// 法2：略
char* multiply_V1(char* num1, char* num2) {
    int n1 = strlen(num1), n2 = strlen(num2);
    int* res = (int*)calloc(n1 + n2, sizeof(int));
    for (int i = n1 - 1; i >= 0; i--) {
        for (int j = n2 - 1; j >= 0; j--) {
            int mul = (num1[i] - '0') * (num2[j] - '0');
            int p1 = i + j, p2 = i + j + 1;
            int sum = mul + res[p2];
            res[p1] += sum / 10;
            res[p2] = sum % 10;
        }
    }
    int i = 0;
    while (i < n1 + n2 && res[i] == 0) i++;
    if (i == n1 + n2) return "0";
    char* ans = (char*)calloc(n1 + n2 - i + 1, sizeof(char));
    int j = 0;
    while (i < n1 + n2) ans[j++] = res[i++] + '0';
    ans[j] = '\0';
    free(res);
    return ans;
}

int main() {
    char* num1 = "123";
    char* num2 = "456";
    char* res = multiply(num1, num2);
    printf("%s * %s = %s\n", num1, num2, res);
    free(res);
    return 0;
}