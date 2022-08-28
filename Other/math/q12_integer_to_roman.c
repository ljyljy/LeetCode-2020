#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>

#ifndef DIM
#define DIM(arr) sizeof(arr)/sizeof(*arr)
#endif

const int val[] =
{
    1000, 900, 500, 400,
    100, 90, 50, 40,
    10, 9, 5, 4, 1
};

const char* str[] =
{  // ָ�����飨������ÿ��Ԫ�ض���һ��ָ�룩
    "M","CM","D","CD",
    "C","XC","L","XL",
    "X","IX","V","IV","I"
};

char* intToRoman(int num)
{
    int cnt = DIM(val), curLen = 0;
    char* res = malloc(sizeof(char) * 100);
    for (int i = 0; i < cnt; i++)
    {
        while (num >= val[i])
        {
            // res[curLen] = *str[i]; // WA! Ӧ��Ϊ���ַ�������strcpy or memcpy��
            memcpy(res + curLen, str[i], strlen(str[i]));
            // printf("str[i]=%s\n", str[i]);
            curLen += strlen(str[i]);
            num -= val[i];
        }
    }
    res[curLen] = 0; // ������
    return res;
}

int main()
{
    int num = 58;
    printf("num = %d, res = %s\n", num, intToRoman(num));
    num = 1994;
    printf("num = %d, res = %s\n", num, intToRoman(num));
}
