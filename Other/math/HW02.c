#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <limits.h>
#include <string.h>

// #include "securec.h"

// HW02. 大小端整数
/**
 * 现输入一个字符串表示的十进制整数（含负数），请分别输出以4字节表示的大端序和小端序：
 * 负数以补码形式表示。
 * 如果输入的整数的值超出 [-2^31, 2^32) 范围，则输出字符串overflow。
 */

 /* 知识点：
 - 1) 字符串转整数：
    - `atoll(str)`, 将ASCII转为long long int
      - HW刷题尽量避免atoi等函数，使用sscanf_s等安全函数(可检查输入是否合法)
    - `sscanf_s(str, "%lld", &input)`, 读取str数据，将其转为long long int, 并输出到&input
      - sscanf_s(str, "%lld", &input) != 1, 则输入不合法

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

 - 4）十进制转二进制
   - x86系统，地址4~8字节. 数字的存储方式：默认为**<font color='red'>【二进制 & 小端序】</font>**
   - 故无需自行将数字转为二进制串。

     - 首先：必须将原始数字`x`强转为**<font color='red'>`(unsigned int)`</font>`x`**
       - 否则，负数的高位会输出`00`，实则为`FF`

     - v1: 直接指定**`unsigned char* p`**指向数字的首地址即可。
       - ```C
      unsigned int inputU = (unsigned int)input;
      unsigned char* p = (unsigned char*)&inputU; // 将x的地址，转换为【unsigned】char *
      sprintf_s(binStr, MAX_LEN, "%02X %02X %02X %02X\n%02X %02X %02X %02X",
        p[3], p[2], p[1], p[0], // 大端序 二进制数
        p[0], p[1], p[2], p[3]); // 小端序（默认）二进制数
      ```

    - v2: 使用**`unsigned char arr[n]`**，将输入的数字强转为进行拷贝。
      - ```c
      unsigned char x = (unsigned char)input;  // 题中数字最多32位，即4B↓
      unsigned char little_endian[4]; // win系统通常为小端序
      // memcpy(big_endian, &x, sizeof(x)); // 将x的地址，拷贝到char数组
      memcpy(little_endian, &x, sizeof(x));
      ...
      ```

 */

#define MAX_STR_LEN 512

 // final AC version
void GetHexString(const char* inputStr, char* output, int outputSize) {
    long long input = 0;
    if (sscanf_s(inputStr, "%lld", &input) != 1) {
        return;
    }

    if (input < (long long)INT_MIN || input >= (1LL << 32)) {
        strcpy_s(output, outputSize, "overflow");
        return;
    }


    unsigned int inputU = (unsigned int)input; // 4字节, 必须转为unsigned int类型！否则负数的高位会输出00，实则为FF
    // x86系统，地址4~8字节. 数字的存储方式：默认为【二进制 & 小端序】
    // - 故无需自行将数字转为二进制串。直接指定unsigned char* p指向数字的首地址即可。
    unsigned char* p = (unsigned char*)&inputU; // 定义指针UChar* p，指向数字x的首地址
    sprintf_s(output, outputSize, "%02X %02X %02X %02X\n%02X %02X %02X %02X",
        p[3], p[2], p[1], p[0],
        p[0], p[1], p[2], p[3]);
}


// 写法1(AC)：

// 以下有误
// void reverse_bytes(char* buf, int n) {
//     int i = 0, j = n - 1;
//     while (i < j) {
//         int tmp = buf[i];
//         buf[i] = buf[j];
//         buf[j] = buf[i];
//         i++;
//         j--;
//     }
// }

void GetHexString_v1(const char* inputStr, char* output, int outputSize) {
    // Parse input string to integer
    long long int input = atoll(inputStr); // HW刷题尽量避免atoi等函数，使用sscanf_s等安全函数(可检查输入是否合法)
    // printf("input = %lld\n", input);
    if (input < (long long int)INT_MIN || input >= (1LL << 32)) { // 1) 题目坑：数字>=2^32(而非INT_MAX)才算overflow
        // printf("inputNum = %lld, INT_MAX=%d, input < (long long int)INT_MIN?:%d, input >= (1ULL << 32):%d\n", input, INT_MAX, \
               // input < (long long int)INT_MIN, input >= (1ULL << 32));
        strncpy(output, "overflow", outputSize);
        return;
    }

    // Convert to big-endian and little-endian
    unsigned int x = (unsigned int)input;  // 4字节, 必须转为unsigned int类型！否则负数的高位会输出00，实则为FF
    unsigned char little_endian[4]; // win系统通常为小端序
    memcpy(little_endian, &x, sizeof(x));


    /* 以下代码有误!注释后可AC
        // todo check BUG
         64位系统，地址8字节(但题目输入的数字最多32位，4B)。这个操作的目的是检查 x 的最低有效字节是否为 0.
         大端序表示下，整数最高字节在最低/左地址（可读性强），所以最低有效字(LSB)节在地址上最高，且非0。
         如果 x 的最低有效字节为 0，那么表示这个整数的字节序就是小端序。如果 x 的最低有效字节不为 0，那么表示这个整数的字节序就是大端序。

        // if (*((char*)&x) == 0) { // 将x的地址，转换为char *,并获取首地址（LSB=0，则小端序）
        //     // printf("LSB == 0, little_endian\n");
        //     // reverse_bytes(big_endian, sizeof(x));
        //     reverse_bytes(little_endian, sizeof(x));
        // }
    */

    // Format the output string（默认系统为小端序方式）
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
    if (scanf_s("%s", inputStr, MAX_STR_LEN) != 1) { return -1; }
    // if (scanf("%s", inputStr) != 1) { return -1; }

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