#include <stdio.h>
#include <stdlib.h>
// #include <math.h>
// #include <limits.h>
// #include <string.h>
// #include <stdbool.h>

int main() {
    // Q1: 有符号数和无符号数的转换
    unsigned int a = 6; // 0x6
    int b = -20; // 0xFFFFFFEC
    int c = a + b; // c = 0xFFFFFFEC + 0x6 = 0xFFFFFFF2
    printf("b = 0x%X\n", b); // 0xFFFFFFEC
    // a+b先转换为无符号数，再转换成有符号数被c接收
    printf("a + b = (uint)%u\n", a + b); // 4294967286
    printf("%d\n", c); // -14  (0xFFFFFFF2解读为有符号数，整数部分:X.反码+1=0x7FFFFFF2，则X.反码=0x7FFFFFF1，故X=(1110)_2=14)

    // Q2. 符号扩展
    /* 符号扩展问题。
    从无符号的short转换为有符号的int类型，待扩展量为无符号数，遵循【零扩展】.
    因此从11111111 11111111扩展为00000000 00000000 11111111 11111111,输出为65535
    */
    unsigned short i = 65535;
    printf("%d \n", i); // 65535

    /* 符号扩展问题。
    -从有符号的short转换为有符号的int类型，待扩展量为有符号数，遵循【符号位】扩展
    因此从11111111 11111111扩展为11111111 1111111111111111 11111111,输出为-1
    */
    signed short i2 = 65535;
    printf("i2 = 0x%X\n", i2); // 0xFFFFFFFF
    printf("%d \n", i2); // -1 [-X.反码 + 1 = 0xFFFFFFFF] ∴X.反码 = 0xFFFFFFFE ∴X = 0x1 且 X为负数 ∴X = -1


    // Q3. 有符号数和无符号数的运算
    unsigned int a2 = 20; // 0x14
    int b2 = 13; // 0xD = (1101)_2
    int k = b2 - a2; // k = 0xD - 0x14 = (溢出) 0xFFFFFFF9, 0x9 = (1001)_2
    unsigned int m = (unsigned int)b2 + a2;
    int m2 = (int)(b2 + a2);
    int m3 = b2 + (int)a2;
    printf("b2 - a2 = 0x%X\n", b2 - a2); // 0xFFFFFFF9 解读为有符号数，整数部分:X.反码+1=0x7FFFFFF9，则X.反码=0x7FFFFFF8，故X=(0111)_2=7
    printf("k = %d = 0x%X = (uint)%u\n", k, k, k); // k = -7 = 0xFFFFFFF9 = (uint)4294967289
    printf("(unsigned int)b2 + a2  = %u = 0x%X \n", m, m); // 33，0x21
    printf("(int)(b2+a2)  = %d = 0x%X \n", m2, m2); // 33，0x21
    printf("b2 + (int)a2  = %d = 0x%X \n", m3, m3); // 33，0x21



    static char word[] = 'Turbo\0';
    printf("sizeof(word)=%d \n", sizeof(word));
    return 0;


}