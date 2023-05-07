#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <limits.h>
#include <string.h>

// #include "securec.h"

// HW02. 大小端整数(未AC!!! PASS 96%)
// 1. 输入一个整数，输出其对应的大端序和小端序的16进制表示

/* 知识点：
- 1) 字符串转整数：`atoll(str)`, 将ASCII转为long long int
- 2) 判断溢出
  - 有效范围：[-2^31, 2^32)
  - 坑：数字>=2^32(而非INT_MAX)才算overflow
  - ```C
    if (input < (long long int)INT_MIN || input >= (1LL << 32)) {
        strncpy(output, "overflow", outputSize);
        return;
    }
    ```
- 3) 大小端序
  - 大端序：整数最高字节在最低/左地址（可读性强）
  - 小端序：整数最高字节在最高/右地址
    - win系统通常为小端序
    - 若x存储地址方法为小端序，则`*((char*)&x) == 0`
      - 将x的地址，转换为char *,并获取首地址（LSB=0，即为小端序）
      - 64位系统，地址8字节。这个操作的目的是检查 x 的最低有效字节是否为 0.

*/

#define MAX_STR_LEN 512

void reverse_bytes(char* buf, int n) {
    int i = 0, j = n - 1;
    while (i < j) {
        int tmp = buf[i];
        buf[i] = buf[j];
        buf[j] = buf[i];
        i++;
        j--;
    }
}

void GetHexString(const char* inputStr, char* output, int outputSize) {
    // Parse input string to integer
    long long int input = atoll(inputStr);
    // printf("input = %lld\n", input);
    if (input < (long long int)INT_MIN || input >= (1LL << 32)) { // 1) 题目坑：数字>=2^32(而非INT_MAX)才算overflow
        // printf("inputNum = %lld, INT_MAX=%d, input < (long long int)INT_MIN?:%d, input >= (1ULL << 32):%d\n", input, INT_MAX, \
               // input < (long long int)INT_MIN, input >= (1ULL << 32));
        strncpy(output, "overflow", outputSize);
        return;
    }

    // Convert to big-endian and little-endian
    unsigned int x = (unsigned int)input;  // 4字节
    char little_endian[4]; // win系统通常为小端序
    // memcpy(big_endian, &x, sizeof(x)); // 将x的地址，拷贝到char数组
    memcpy(little_endian, &x, sizeof(x));
    // for (int i = 0; i < 4; i++) {
    //     printf("%02X ", (unsigned char)big_endian[i]); // 必须强转为(unsigned char)，否则是有符号的，会输出高位的一堆F
    // }
    // printf("\n");

    /* 64位系统，地址8字节。这个操作的目的是检查 x 的最低有效字节是否为 0.
    大端序表示下，整数最高字节在最低/左地址（可读性强），所以最低有效字(LSB)节在地址上最高，且非0。
    如果 x 的最低有效字节为 0，那么表示这个整数的字节序就是小端序。如果 x 的最低有效字节不为 0，那么表示这个整数的字节序就是大端序。
    */
    if (*((char*)&x) == 0) { // 将x的地址，转换为char *,并获取首地址（LSB=0，则小端序）
        // printf("LSB == 0, little_endian\n");
        // reverse_bytes(big_endian, sizeof(x));
        reverse_bytes(little_endian, sizeof(x));
    }

    // Format the output string（默认系统为大端序方式）
    snprintf(output, outputSize, "%02X %02X %02X %02X\n%02X %02X %02X %02X", // 字符宽度为2，不够则填充前导'0'：空格填充为'0'
        (unsigned char)little_endian[3], (unsigned char)little_endian[2],
        (unsigned char)little_endian[1], (unsigned char)little_endian[0],
        (unsigned char)little_endian[0], (unsigned char)little_endian[1],
        (unsigned char)little_endian[2], (unsigned char)little_endian[3]
    );
}

int main()
{
    char inputStr[MAX_STR_LEN];
    // if (scanf_s("%s", inputStr, MAX_STR_LEN) != 1) { return -1; }
    if (scanf("%s", inputStr) != 1) { return -1; }

    char output[MAX_STR_LEN];
    GetHexString(inputStr, output, MAX_STR_LEN);
    printf("big_endian and little_endian: \n");
    printf("%s", output);

    return 0;
}

/*
-10
big_endian and little_endian:
FF FF FF F6
F6 FF FF FF
*/