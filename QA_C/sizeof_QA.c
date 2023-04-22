#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>
#include <stdbool.h>

#pragma pack(4) // ����test_Struct�����������С=min(pack_4, sizeof(short)_2)=2
// sizeof(struct) ��Ҫ��2����
//  1) �ڴ���룺��Ա����var_i����ʼƫ�Ƶ�ַ = sizeof(var_i)��������
//  2) ��Ȼ���룺������pack=min(pack, sizeof(struct������Ա��С))
//  3) ��2����ν��ۣ�sizeof�ṹ���� = ����pack��������
typedef struct
{           // �������     ƫ�Ƶ�ַ        ��С
    char a; // 1            0               1
    // 1B  -> ����a���� sizeof(test)=1B, WHY NOT 4B? - pack=min(pack_4, sizeof(char))=1
    short b;// 2            2               2
    // 2B ->  ����a&b���� sizeof(test)=4B��short��ƫ�Ƶ�ַҲ��Ҫ���ڴ���롿������Ϊ2�ı��������ڴ���OFFSET: 0=a, 1=[ ], 2&3=b��
    // �� ����b����sizeof(test) = 2B-> WHY NOT 4B? - ��Ϊ�����Ķ������ȡmin(pack_4, sizeof(short_2))=2 !!!
    char c; // 1            4               1
    // 1B  -> pack = min(pack_4, char_1)=1
    // a + b + c, �� sizeof(test) = 6B->WHY NOT 8B ? -���ڴ���OFFSET : 0 = a, 1 = [], 2 & 3 = b, 4 = c, ��©5 = []��
    //              (struct.pack = min(pack_4, ����Ա��С = 2) = 2���ṹ���С��ҪΪ����pack(2)����������) ��
} test_Struct;// 2          0               6
// sizeof��varλ��ǿ��أ�������short���룬������2B����������char-short-char����test_Struct��6B��

// ����test_Union�����������С=min(pack_4B, sizeof(struct_test_6B), sizeof(long_4B))=4
typedef union { // �������     ƫ�Ƶ�ַ        ��С
    struct {
        char a; // 1            0               1
        // 1B  -> ����a���� sizeof(test)=1B
        short b;// 2            2               2
        // 2B ->  ����a&b���� sizeof(test)=4B��OFFSET: 0=a, 1=[ ], 2&3=b
        char c; // 1            4               1
        // 1B -> a+b+c, �� sizeof(test)=6B
    } test;     // 2            0               6
    // test: 6B -> �ֽڶ���, unionȡ����Ա�Ĵ�С����Ϊ6B��������Ҫpack=4��������մ�СΪ8B
    long d; // 4B
} test_Union; // min(pack_4, max_6)=4; 0;       8
// test_Union: 8B

typedef struct {// �������     ƫ�Ƶ�ַ        ��С
    short a;    // 2            0               2
    char b;     // 1            2               1
    char c;     // 1            3               1
    // 1������short-char-char����4B��
    int* d;     // 4            4               8
    union {
        double e;                           // 8B
        int f;                              // 4B
    }; // 8B    // min(pack_4,max_8)=4;12;      8
} test_Struct2; // 20B

typedef union {// �������     ƫ�Ƶ�ַ        ��С
    double e;                               // 8B
    int f;                          // 4B, ��double����
} test_Union2_2; // min(pack_4,max_8)=4; 0;   8B

struct {
    short a; // 2 B
    char b; // 1
    char c; // 1
    // padding - 4B

    int* d; // x86-64λ - 8B

    union { // max=4B
        double e; // 4B
        int f; // 4B
    };
    // padding - 4B
} S2 = { 4, 'b0', 'c0', NULL, {.f = 30} };
// �� Ĭ�Ͻ�S2.a��ʼ��Ϊ4��������������short������ֵ���������ǿת���ٸ�ֵ��S2.a

int main() {
    printf("sizeof(test_Struct) = %d, sizeof(test_Union) = %d\n",
        sizeof(test_Struct), sizeof(test_Union)); // 6, 8

    printf("sizeof(test_Struct2) = %d, sizeof(test_Union2_2) = %d\n",
        sizeof(test_Struct2), sizeof(test_Union2_2));// 20, 8

    printf("sizeof(S2) = %d\n", sizeof(S2)); // 24
    printf("S2.a = %d, S2.b = %c, S2.c = %c, S2.d = %p, S2.e = %.2f, S2.f = %d\n",
        S2.a, S2.b, S2.c, S2.d, S2.e, S2.f); // 4, b0, c0, 0, 0, 30


    // char* �� char [N]
    char str1[] = "123456";
    printf("sizeof(str1)=%d \n", sizeof(str1)); // 7
    printf("strlen(str1)=%d \n", strlen(str1)); // 6

    char* str2 = "123456";
    printf("sizeof(str2)=%d \n", sizeof(str2)); // 8
    printf("strlen(str2)=%d \n", strlen(str2)); // 6

    char str3[] = "\\";// �����ַ�'\'
    printf("sizeof(str3)=%d \n", sizeof(str3)); // 2
    printf("strlen(str3)=%d \n", strlen(str3)); // 1

    // �ַ������ʼ��
    // ˫���ŵ��ַ����ڶ����ַ��� ʱ��ϵͳ���Զ�����һ�������������ǲ�ȡ���������θ�ֵ�򲻻��������ӽ�������
    char word[] = { 'T', 'u', 'r', 'b', 'o', '\0' };
    char word2[] = { "Turbo\0" };
    char word3[] = "Turbo\0";
    printf("sizeof(word)=%d, sizeof(word2)=%d, sizeof(word3)=%d \n",
        sizeof(word), sizeof(word2), sizeof(word3)); // 6, 7, 7


    return 0;
}