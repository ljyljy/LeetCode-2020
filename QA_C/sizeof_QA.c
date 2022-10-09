#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>
#include <stdbool.h>

#pragma pack(4) // ����test_Struct�����������С=min(pack_4, sizeof(short)_2)=2
// sizeof(struct) ��Ҫ��2����
//  1) �ڴ���룺��Ա����var_i����ʼƫ�Ƶ�ַ = sizeof(var_i)��������
//  2) ��Ȼ���룺������pack=min(pack, sizeof(Struct������Ա����)
//  3) ��2����ν��ۣ�sizeof�ṹ���� = ����pack��������
typedef struct {
    char a; // 1B  -> ����a���� sizeof(test)=1B, WHY NOT 4B? - ��Ϊ�������ֽڶ���ȡ��min(pack_4, sizeof(short_2����Struct������Ա����))��!!!
    short b; // 2B ->  ����a&b���� sizeof(test)=4B��short��ƫ�Ƶ�ַҲ��Ҫ���ڴ���롿������Ϊ2�ı������ڴ���OFFSET: 0=a, 1=[ ], 2&3=b
    // �� ����b����sizeof(test) = 2B-> WHY NOT 4B? - ��Ϊ�������ֽڶ���ȡmin(pack_4, sizeof(short))!!!
    char c; // 1B  -> a+b+c, �� sizeof(test)=6B -> WHY NOT 8B? - ��Ϊ�������ֽڶ���Ϊ2���ڴ���OFFSET: 0=a, 1=[ ], 2&3=b, 4=c, 5=[](�ṹ���С��ҪΪ����pack(2)����������)
} test_Struct; // sizeof��varλ��ǿ��أ�������short���룬������2B����������char-short-char����6B��

// ����test_Union�����������С=min(pack_4B, sizeof(struct_test_6B), sizeof(long_4B))=4
typedef union {
    struct {
        char a; // 1B  -> ����a���� sizeof(test)=1B
        short b; // 2B ->  ����a&b���� sizeof(test)=4B��OFFSET: 0=a, 1=[ ], 2&3=b
        char c; // 1B -> a+b+c, �� sizeof(test)=6B
    } test; // 6B -> �ֽڶ���, unionȡ����Ա�Ĵ�С����Ϊ6B��������Ҫpack=4��������մ�СΪ8B
    long d; // 4B
} test_Union; // 8B

typedef struct {
    short a;  // 2
    char b; // 1
    char c; // 1������short-char-char����4B��
    int* d; // 8B
    union {
        double e;
        int f;
    }; // 8B
} test_Struct2; // 20B

typedef union {
    double e; // 8B
    int f;  // 4B, ��double����
} test_Union2_2; // 8B

int main() {
    printf("sizeof(test_Struct) = %d, sizeof(test_Union) = %d\n",
        sizeof(test_Struct), sizeof(test_Union));

    printf("sizeof(test_Struct2) = %d, sizeof(test_Union2_2) = %d\n",
        sizeof(test_Struct2), sizeof(test_Union2_2));

    return 0;
}