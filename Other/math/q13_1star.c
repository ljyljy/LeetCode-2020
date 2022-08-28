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
{  // 指针数组（数组内每个元素都是一个指针）
    "M","CM","D","CD",
    "C","XC","L","XL",
    "X","IX","V","IV","I"
};

int romanToInt(char* s)
{
    int n = strlen(s), cnt = DIM(val);
    int res = 0;
    int i = 0, j = 0; // i: 遍历s，j：遍历str
    while (i < n && j < cnt)
    {
        char* curStr = str[j];
        int curV = val[j], curLen = strlen(curStr);
        // printf("curStr=%s, curV=%d, curLen=%d\n", curStr, val[j], curLen);
        char* subStr = (char*)malloc(sizeof(char) * (curLen + 1));
        memset(subStr, 0, sizeof(char) * (curLen + 1));// 勿忘v1-1！
        while (i + curLen <= n)
        {   // s[i+curLen-1]不可越界！s.substring(i, i+curLen)
            strncpy(subStr, s + i, curLen); // s+i是指针！非s[i](char)！
            subStr[curLen] = 0; // 【坑】勿忘v1-2！
            // printf("s[i]=%s\n", subStr);

            if (strcmp(subStr, curStr) == 0)
            {
                res += curV;
                i += curLen;
            }
            else break;

        }
        j++;
    }
    return res;
}

int main()
{
    char* str1 = "LVIII", * str2 = "IV";
    printf("str=%s, res=%d\n", str1, romanToInt(str1));
    // printf("%d\n", romanToInt(str2));
}