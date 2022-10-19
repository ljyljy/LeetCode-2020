#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>
#include <stdbool.h>

// int -> str (ascii)
// 法1：itoa
void int2str(int num, int base) {
    // char* numStr = (char*)malloc(sizeof(char) * 4); // v1: 字符串指针√
    char numStr[10]; // v2: 字符数组 √
    itoa(num, numStr, base); // val, char*指针, 10进制
    printf("numStr = %s\n", numStr);
    // free(numStr), numStr = NULL; // v1
}

// 法2：sprintf
void int2str_v2(int num) {
    char* numStr = (char*)malloc(sizeof(char) * 32);
    sprintf(numStr, "%d", num);
    printf("numStr_v2 = %s\n", numStr);
    free(numStr), numStr = NULL;
}

int main() {
    int num = 123, base = 10;
    // char* numStr; // 不可置NULL, main中不进行malloc，也可以ac；但函数中必须malloc！
    int2str(num, base);
    int2str_v2(num);
    return 0;
}