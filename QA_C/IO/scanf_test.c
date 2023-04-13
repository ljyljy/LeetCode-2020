#include <stdlib.h>
#include <stdio.h>

int main() {
    printf("-------CASE 1: getchar & putchar------\n");
    long input;
    int ch; // 使用int（范围更大），而非char，来接收getchar的返回值！
    while (scanf("%ld", &input) != 1) {
        while ((ch = getchar()) != '\n') {
            putchar(ch); // 打印输入无效的字符
        }
        printf(" is not a long number.\n");
    }
    printf("valid input = %d\n", input);
    getchar(); // 在下一轮scanf之前，先'吃掉'本轮最后敲入的回车

    printf("-------CASE 2: ------\n");
    int num;
    while (scanf("%d", &num) != 1) {
        scanf("%*s"); // 读取并丢弃非整数字符
        printf("please input an integer, such as 100.\n");
    }
    printf("The num is: %d \n", num);

}
/**
-------CASE 1: getchar & putchar------
low
low is not a long number.
high102 1038
high102 1038 is not a long number.
102832
valid input = 102832
-------CASE 2: ------
siw92 3012
please input an integer, such as 100.
The num is: 3012

 */