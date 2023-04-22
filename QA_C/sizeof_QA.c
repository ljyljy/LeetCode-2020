#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>
#include <stdbool.h>

#pragma pack(4) // 对于test_Struct，真正对齐大小=min(pack_4, sizeof(short)_2)=2
// sizeof(struct) 需要看2个：
//  1) 内存对齐：成员变量var_i的起始偏移地址 = sizeof(var_i)的整数倍
//  2) 自然对齐：真正的pack=min(pack, sizeof(struct中最大成员大小))
//  3) 由2延伸の结论：sizeof结构体结果 = 真正pack的整数倍
typedef struct
{           // 对齐参数     偏移地址        大小
    char a; // 1            0               1
    // 1B  -> 仅有a，则 sizeof(test)=1B, WHY NOT 4B? - pack=min(pack_4, sizeof(char))=1
    short b;// 2            2               2
    // 2B ->  仅有a&b，则 sizeof(test)=4B；short的偏移地址也需要【内存对齐】，至少为2的倍数，【内存中OFFSET: 0=a, 1=[ ], 2&3=b】
    // ↑ 仅有b，则sizeof(test) = 2B-> WHY NOT 4B? - 因为真正的对齐参数取min(pack_4, sizeof(short_2))=2 !!!
    char c; // 1            4               1
    // 1B  -> pack = min(pack_4, char_1)=1
    // a + b + c, 则 sizeof(test) = 6B->WHY NOT 8B ? -【内存中OFFSET : 0 = a, 1 = [], 2 & 3 = b, 4 = c, 勿漏5 = []】
    //              (struct.pack = min(pack_4, 最大成员大小 = 2) = 2，结构体大小需要为真正pack(2)的整数倍！) ↑
} test_Struct;// 2          0               6
// sizeof与var位置强相关！！！按short对齐，都得是2B的整数倍（char-short-char，则test_Struct共6B）

// 对于test_Union，真正对齐大小=min(pack_4B, sizeof(struct_test_6B), sizeof(long_4B))=4
typedef union { // 对齐参数     偏移地址        大小
    struct {
        char a; // 1            0               1
        // 1B  -> 仅有a，则 sizeof(test)=1B
        short b;// 2            2               2
        // 2B ->  仅有a&b，则 sizeof(test)=4B，OFFSET: 0=a, 1=[ ], 2&3=b
        char c; // 1            4               1
        // 1B -> a+b+c, 则 sizeof(test)=6B
    } test;     // 2            0               6
    // test: 6B -> 字节对齐, union取最大成员的大小，即为6B，但还需要pack=4，因此最终大小为8B
    long d; // 4B
} test_Union; // min(pack_4, max_6)=4; 0;       8
// test_Union: 8B

typedef struct {// 对齐参数     偏移地址        大小
    short a;    // 2            0               2
    char b;     // 1            2               1
    char c;     // 1            3               1
    // 1，，（short-char-char，则共4B）
    int* d;     // 4            4               8
    union {
        double e;                           // 8B
        int f;                              // 4B
    }; // 8B    // min(pack_4,max_8)=4;12;      8
} test_Struct2; // 20B

typedef union {// 对齐参数     偏移地址        大小
    double e;                               // 8B
    int f;                          // 4B, 被double覆盖
} test_Union2_2; // min(pack_4,max_8)=4; 0;   8B

struct {
    short a; // 2 B
    char b; // 1
    char c; // 1
    // padding - 4B

    int* d; // x86-64位 - 8B

    union { // max=4B
        double e; // 4B
        int f; // 4B
    };
    // padding - 4B
} S2 = { 4, 'b0', 'c0', NULL, {.f = 30} };
// ↑ 默认将S2.a初始化为4，若给了其他非short类型数值，将会进行强转，再赋值给S2.a

int main() {
    printf("sizeof(test_Struct) = %d, sizeof(test_Union) = %d\n",
        sizeof(test_Struct), sizeof(test_Union)); // 6, 8

    printf("sizeof(test_Struct2) = %d, sizeof(test_Union2_2) = %d\n",
        sizeof(test_Struct2), sizeof(test_Union2_2));// 20, 8

    printf("sizeof(S2) = %d\n", sizeof(S2)); // 24
    printf("S2.a = %d, S2.b = %c, S2.c = %c, S2.d = %p, S2.e = %.2f, S2.f = %d\n",
        S2.a, S2.b, S2.c, S2.d, S2.e, S2.f); // 4, b0, c0, 0, 0, 30


    // char* 与 char [N]
    char str1[] = "123456";
    printf("sizeof(str1)=%d \n", sizeof(str1)); // 7
    printf("strlen(str1)=%d \n", strlen(str1)); // 6

    char* str2 = "123456";
    printf("sizeof(str2)=%d \n", sizeof(str2)); // 8
    printf("strlen(str2)=%d \n", strlen(str2)); // 6

    char str3[] = "\\";// 特殊字符'\'
    printf("sizeof(str3)=%d \n", sizeof(str3)); // 2
    printf("strlen(str3)=%d \n", strlen(str3)); // 1

    // 字符数组初始化
    // 双引号的字符串在定义字符串 时，系统会自动增加一个结束符，但是采取单引号依次赋值则不会主动增加结束符。
    char word[] = { 'T', 'u', 'r', 'b', 'o', '\0' };
    char word2[] = { "Turbo\0" };
    char word3[] = "Turbo\0";
    printf("sizeof(word)=%d, sizeof(word2)=%d, sizeof(word3)=%d \n",
        sizeof(word), sizeof(word2), sizeof(word3)); // 6, 7, 7


    return 0;
}