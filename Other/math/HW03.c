
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define STR_LEN 33

// HW03. 二进制转十进制

// 将负数的补码转化为对应正数的原码
char* convert(char* binStr)
{
    int n = strlen(binStr);
    int carry = 1;
    char* ans = (char*)calloc(STR_LEN, sizeof(char)); // 负数的补码 = 反码 + 1
    ans[0] = 0;
    for (int i = n - 1; i >= 1; i--) {
        int curSum = carry;
        if (binStr[i] - '0' == 1) {
            curSum += 0; // 取反
        }
        else curSum += 1;
        ans[i] = curSum % 2 + '0';
        carry = curSum / 2; // 若最后carry溢出，则不管他
    }
    /**
    for (int i = 0; i < 32; i++) {
        printf("%c,", ans[i]);
    }
    */
    return ans;
}

// 未AC! PASS 90%
int BinaryToDecimal_v1(char* binStr)
{
    int n = strlen(binStr);
    int result = 0;
    int sign = binStr[0] == '1' ? -1 : 1;
    if (sign == -1) {
        binStr = convert(binStr); //则：负数补码 = 对应正数.反码 + 1 （见v1）
        n = 32; // 转化为相对应正数的原码，后续还需*sign
    }
    int bit = 1;

    for (int i = n - 1; i >= 0; i--) {
        result += (binStr[i] - '0') * bit;
        bit <<= 1; // *2^k, k = 0, 1, 2, ...
    }

    return sign * result;
}

// 未AC! PASS 90%
int BinaryToDecimal(char* binStr)
{
    int n = strlen(binStr);
    int ans = 0;
    for (int i = 0; i < n; i++) {
        ans = ans * 2 + (binStr[i] - '0');
    }

    if (binStr[0] == '1') {
        // printf("negative: offset= 1<<n = %d\n", 1<<n); // 减去 2^n + 1
        ans = ans - (1 << n) + 1;
    }

    return ans;
}


int main(void)
{
    char binaryString[STR_LEN];
    // if (scanf_s("%s", &binaryString, STR_LEN) != 1) {
    if (scanf("%s", &binaryString) != 1) {
        return -1;
    };
    int output = BinaryToDecimal_v1(binaryString);
    int output2 = BinaryToDecimal(binaryString);
    printf("%d, %d", output, output2);
    return 0;
}

/*
00011
3, 3

11111111111111111111111111111111
-1, -1
*/