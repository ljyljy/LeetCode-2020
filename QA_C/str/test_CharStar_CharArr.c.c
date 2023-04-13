// string.c
#include <stdio.h>

int main() {
    const char strA[] = "Hello, geek!"; // 000000000061FDEB
    const char* strB = "Hello, geek!"; // 0000000000404000
    printf("%p\n%p\n", strA, strB);

    // 扩展）read-only string.
    const char strA2[] = "Hello, geek!"; // 000000000061FDDE
    const char* strB2 = "Hello" ", geek!"; // 0000000000404000
    char* strB3 = "Hello, geek!"; // 0000000000404000
    char strD[] = "Hello, geek!"; // 000000000061FDD1
    char strE[] = "Hello" ", geek!"; // 字面量不同, 000000000061FDC4
    printf("%p %p %p %p %p\n", strA2, strB2, strB3, strD, strE);

    // 尝试修改 字符串指针char*（失败），字符数组char[]（成功）
    // strB2[0] = 'h'; // 失败 error: assignment of read-only location '*strB2'
    // printf("%s\n", strB2);
    strD[0] = 'h';
    printf("%s\n", strD); // 成功： hello, geek!


    // 总结：1）strB、strB2、strB3都是char*，字面量相同, 地址都为0000000000404000
    // 2）strA、strA2、strD、strE字面量不同，地址不同
    // 3）strA、strA2、strB、strB2、strB3都是char*，不可修改，属于read-only string
    // 4）strD、strE都是char[] 字符数组，可修改
}