#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>
#include <stdbool.h>

#pragma pack(4) // 对于test_Struct，真正对齐大小=min(pack_4, sizeof(short)_2)=2
// sizeof(struct) 需要看2个：
//  1) 内存对齐：成员变量var_i的起始偏移地址 = sizeof(var_i)的整数倍
//  2) 自然对齐：真正的pack=min(pack, sizeof(Struct中最大成员类型)
//  3) 由2延伸の结论：sizeof结构体结果 = 真正pack的整数倍
typedef struct {
    char a; // 1B  -> 仅有a，则 sizeof(test)=1B, WHY NOT 4B? - 因为真正的字节对齐取【min(pack_4, sizeof(short_2，即Struct中最大成员类型))】!!!
    short b; // 2B ->  仅有a&b，则 sizeof(test)=4B；short的偏移地址也需要【内存对齐】，至少为2的倍数，内存中OFFSET: 0=a, 1=[ ], 2&3=b
    // ↑ 仅有b，则sizeof(test) = 2B-> WHY NOT 4B? - 因为真正的字节对齐取min(pack_4, sizeof(short))!!!
    char c; // 1B  -> a+b+c, 则 sizeof(test)=6B -> WHY NOT 8B? - 因为真正的字节对齐为2！内存中OFFSET: 0=a, 1=[ ], 2&3=b, 4=c, 5=[](结构体大小需要为真正pack(2)的整数倍！)
} test_Struct; // sizeof与var位置强相关！！！按short对齐，都得是2B的整数倍（char-short-char，则共6B）

// 对于test_Union，真正对齐大小=min(pack_4B, sizeof(struct_test_6B), sizeof(long_4B))=4
typedef union {
    struct {
        char a; // 1B  -> 仅有a，则 sizeof(test)=1B
        short b; // 2B ->  仅有a&b，则 sizeof(test)=4B，OFFSET: 0=a, 1=[ ], 2&3=b
        char c; // 1B -> a+b+c, 则 sizeof(test)=6B
    } test; // 6B -> 字节对齐, union取最大成员的大小，即为6B，但还需要pack=4，因此最终大小为8B
    long d; // 4B
} test_Union; // 8B

typedef struct {
    short a;  // 2
    char b; // 1
    char c; // 1，，（short-char-char，则共4B）
    int* d; // 8B
    union {
        double e;
        int f;
    }; // 8B
} test_Struct2; // 20B

typedef union {
    double e; // 8B
    int f;  // 4B, 被double覆盖
} test_Union2_2; // 8B

int main() {
    printf("sizeof(test_Struct) = %d, sizeof(test_Union) = %d\n",
        sizeof(test_Struct), sizeof(test_Union));

    printf("sizeof(test_Struct2) = %d, sizeof(test_Union2_2) = %d\n",
        sizeof(test_Struct2), sizeof(test_Union2_2));

    return 0;
}