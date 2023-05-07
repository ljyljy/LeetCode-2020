#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <limits.h>
#include <string.h>

// #include "securec.h"

// HW02. ��С������(δAC!!! PASS 96%)
// 1. ����һ��������������Ӧ�Ĵ�����С�����16���Ʊ�ʾ

/* ֪ʶ�㣺
- 1) �ַ���ת������`atoll(str)`, ��ASCIIתΪlong long int
- 2) �ж����
  - ��Ч��Χ��[-2^31, 2^32)
  - �ӣ�����>=2^32(����INT_MAX)����overflow
  - ```C
    if (input < (long long int)INT_MIN || input >= (1LL << 32)) {
        strncpy(output, "overflow", outputSize);
        return;
    }
    ```
- 3) ��С����
  - �������������ֽ������/���ַ���ɶ���ǿ��
  - С������������ֽ������/�ҵ�ַ
    - winϵͳͨ��ΪС����
    - ��x�洢��ַ����ΪС������`*((char*)&x) == 0`
      - ��x�ĵ�ַ��ת��Ϊchar *,����ȡ�׵�ַ��LSB=0����ΪС����
      - 64λϵͳ����ַ8�ֽڡ����������Ŀ���Ǽ�� x �������Ч�ֽ��Ƿ�Ϊ 0.

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
    if (input < (long long int)INT_MIN || input >= (1LL << 32)) { // 1) ��Ŀ�ӣ�����>=2^32(����INT_MAX)����overflow
        // printf("inputNum = %lld, INT_MAX=%d, input < (long long int)INT_MIN?:%d, input >= (1ULL << 32):%d\n", input, INT_MAX, \
               // input < (long long int)INT_MIN, input >= (1ULL << 32));
        strncpy(output, "overflow", outputSize);
        return;
    }

    // Convert to big-endian and little-endian
    unsigned int x = (unsigned int)input;  // 4�ֽ�
    char little_endian[4]; // winϵͳͨ��ΪС����
    // memcpy(big_endian, &x, sizeof(x)); // ��x�ĵ�ַ��������char����
    memcpy(little_endian, &x, sizeof(x));
    // for (int i = 0; i < 4; i++) {
    //     printf("%02X ", (unsigned char)big_endian[i]); // ����ǿתΪ(unsigned char)���������з��ŵģ��������λ��һ��F
    // }
    // printf("\n");

    /* 64λϵͳ����ַ8�ֽڡ����������Ŀ���Ǽ�� x �������Ч�ֽ��Ƿ�Ϊ 0.
    ������ʾ�£���������ֽ������/���ַ���ɶ���ǿ�������������Ч��(LSB)���ڵ�ַ����ߣ��ҷ�0��
    ��� x �������Ч�ֽ�Ϊ 0����ô��ʾ����������ֽ������С������� x �������Ч�ֽڲ�Ϊ 0����ô��ʾ����������ֽ�����Ǵ����
    */
    if (*((char*)&x) == 0) { // ��x�ĵ�ַ��ת��Ϊchar *,����ȡ�׵�ַ��LSB=0����С����
        // printf("LSB == 0, little_endian\n");
        // reverse_bytes(big_endian, sizeof(x));
        reverse_bytes(little_endian, sizeof(x));
    }

    // Format the output string��Ĭ��ϵͳΪ�����ʽ��
    snprintf(output, outputSize, "%02X %02X %02X %02X\n%02X %02X %02X %02X", // �ַ����Ϊ2�����������ǰ��'0'���ո����Ϊ'0'
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