#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <limits.h>
#include <string.h>

// #include "securec.h"

// HW02. ��С������
/**
 * ������һ���ַ�����ʾ��ʮ����������������������ֱ������4�ֽڱ�ʾ�Ĵ�����С����
 * �����Բ�����ʽ��ʾ��
 * ��������������ֵ���� [-2^31, 2^32) ��Χ��������ַ���overflow��
 */

 /* ֪ʶ�㣺
 - 1) �ַ���ת������
    - `atoll(str)`, ��ASCIIתΪlong long int
      - HWˢ�⾡������atoi�Ⱥ�����ʹ��sscanf_s�Ȱ�ȫ����(�ɼ�������Ƿ�Ϸ�)
    - `sscanf_s(str, "%lld", &input)`, ��ȡstr���ݣ�����תΪlong long int, �������&input
      - sscanf_s(str, "%lld", &input) != 1, �����벻�Ϸ�

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

 - 4��ʮ����ת������
   - x86ϵͳ����ַ4~8�ֽ�. ���ֵĴ洢��ʽ��Ĭ��Ϊ**<font color='red'>�������� & С����</font>**
   - ���������н�����תΪ�����ƴ���

     - ���ȣ����뽫ԭʼ����`x`ǿתΪ**<font color='red'>`(unsigned int)`</font>`x`**
       - ���򣬸����ĸ�λ�����`00`��ʵ��Ϊ`FF`

     - v1: ֱ��ָ��**`unsigned char* p`**ָ�����ֵ��׵�ַ���ɡ�
       - ```C
      unsigned int inputU = (unsigned int)input;
      unsigned char* p = (unsigned char*)&inputU; // ��x�ĵ�ַ��ת��Ϊ��unsigned��char *
      sprintf_s(binStr, MAX_LEN, "%02X %02X %02X %02X\n%02X %02X %02X %02X",
        p[3], p[2], p[1], p[0], // ����� ��������
        p[0], p[1], p[2], p[3]); // С����Ĭ�ϣ���������
      ```

    - v2: ʹ��**`unsigned char arr[n]`**�������������ǿתΪ���п�����
      - ```c
      unsigned char x = (unsigned char)input;  // �����������32λ����4B��
      unsigned char little_endian[4]; // winϵͳͨ��ΪС����
      // memcpy(big_endian, &x, sizeof(x)); // ��x�ĵ�ַ��������char����
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


    unsigned int inputU = (unsigned int)input; // 4�ֽ�, ����תΪunsigned int���ͣ��������ĸ�λ�����00��ʵ��ΪFF
    // x86ϵͳ����ַ4~8�ֽ�. ���ֵĴ洢��ʽ��Ĭ��Ϊ�������� & С����
    // - ���������н�����תΪ�����ƴ���ֱ��ָ��unsigned char* pָ�����ֵ��׵�ַ���ɡ�
    unsigned char* p = (unsigned char*)&inputU; // ����ָ��UChar* p��ָ������x���׵�ַ
    sprintf_s(output, outputSize, "%02X %02X %02X %02X\n%02X %02X %02X %02X",
        p[3], p[2], p[1], p[0],
        p[0], p[1], p[2], p[3]);
}


// д��1(AC)��

// ��������
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
    long long int input = atoll(inputStr); // HWˢ�⾡������atoi�Ⱥ�����ʹ��sscanf_s�Ȱ�ȫ����(�ɼ�������Ƿ�Ϸ�)
    // printf("input = %lld\n", input);
    if (input < (long long int)INT_MIN || input >= (1LL << 32)) { // 1) ��Ŀ�ӣ�����>=2^32(����INT_MAX)����overflow
        // printf("inputNum = %lld, INT_MAX=%d, input < (long long int)INT_MIN?:%d, input >= (1ULL << 32):%d\n", input, INT_MAX, \
               // input < (long long int)INT_MIN, input >= (1ULL << 32));
        strncpy(output, "overflow", outputSize);
        return;
    }

    // Convert to big-endian and little-endian
    unsigned int x = (unsigned int)input;  // 4�ֽ�, ����תΪunsigned int���ͣ��������ĸ�λ�����00��ʵ��ΪFF
    unsigned char little_endian[4]; // winϵͳͨ��ΪС����
    memcpy(little_endian, &x, sizeof(x));


    /* ���´�������!ע�ͺ��AC
        // todo check BUG
         64λϵͳ����ַ8�ֽ�(����Ŀ������������32λ��4B)�����������Ŀ���Ǽ�� x �������Ч�ֽ��Ƿ�Ϊ 0.
         ������ʾ�£���������ֽ������/���ַ���ɶ���ǿ�������������Ч��(LSB)���ڵ�ַ����ߣ��ҷ�0��
         ��� x �������Ч�ֽ�Ϊ 0����ô��ʾ����������ֽ������С������� x �������Ч�ֽڲ�Ϊ 0����ô��ʾ����������ֽ�����Ǵ����

        // if (*((char*)&x) == 0) { // ��x�ĵ�ַ��ת��Ϊchar *,����ȡ�׵�ַ��LSB=0����С����
        //     // printf("LSB == 0, little_endian\n");
        //     // reverse_bytes(big_endian, sizeof(x));
        //     reverse_bytes(little_endian, sizeof(x));
        // }
    */

    // Format the output string��Ĭ��ϵͳΪС����ʽ��
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