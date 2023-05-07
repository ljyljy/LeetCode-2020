#include <stdlib.h>
#include <stdio.h>

#define MAX_STR_LEN 512

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


    printf("-------CASE 3-1 (scanf_s): ------\n");
    char inputStr[MAX_STR_LEN];
    if (scanf_s("%s", inputStr, MAX_STR_LEN) != 1) {
        return -1;
    }
    printf("oringin inputStr: ");
    for (int i = 0; i < strlen(inputStr); i++) {
        printf("%c", inputStr[i]); // 前导0也会输出
    }
    printf("\n");
    // getchar();

    printf("-------CASE 3-2 (sscanf_s): ------\n");
    long long int inputNum = 0;
    if (sscanf_s(inputStr, "%lld", &inputNum) != 1) {
        return -1;
    }
    printf("inputNum=%lld\n", inputNum);

    printf("-------CASE 3-3 (scanf_s): ------\n");
    long long int inputNum2 = 0;
    if (scanf_s("%lld", &inputNum2) != 1) {
        return -1;
    }
    printf("inputNum2=%lld\n", inputNum2);
    return 0;


}
/**
-------CASE 1: getchar & putchar------
low
low is not a long number.
low13 23920
low13 23920 is not a long number.
21032
valid input = 21032
-------CASE 2: ------
kwwwwef 213 wqq2 2032
please input an integer, such as 100.
The num is: 213

-------CASE 3-1 (scanf_s): ------
0001823
oringin inputStr: 0001823
-------CASE 3-2 (sscanf_s): ------
inputNum=1823
-------CASE 3-3 (scanf_s): ------
0001823
inputNum2=1823
 */