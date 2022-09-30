#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>

int main() {
    char ch = 1107;
    printf("%c\n", ch);
    // 打印结果为 1107 % 256，即ASCII = 83 对应的字符，即'S'

    ch = 80.89;
    printf("%c\n", ch);
    // 浮点数降级为int，产生截断。打印结果为 ASCII = 80 对应的字符，即'P'

    float f = 6.0f;
    printf("float=%.2f, int=%d\n", f, f);
    // float=6.00, int=0

    f = -23.99;
    printf("float=%.2f, int=%d\n", f, f);
    // float=-23.99, int=-1610612736

    f = 23.12;
    printf("float=%.2f, int=%d, sizeof(f)=%d\n", f, f, sizeof(f));
    // float=-23.99, int=-1610612736, sizeof(f)=4

    double dd = 23.89;
    printf("double=%.2lf, int=%d, sizeof(dd)=%d\n", dd, dd, sizeof(dd));
    // double=23.89, int=171798692, sizeof(dd)=8

    return 0;
}