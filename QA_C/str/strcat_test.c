#include <stdio.h>
#include <stdlib.h>
// #include <math.h>
// #include <limits.h>
#include <string.h>
// #include <stdbool.h>

int main() {
    char p[20] = { 'a','b','c','d',0 };
    char q[] = "abc";
    char r[] = "abcde";
    strcat(p, r); // abcdabcde\0
    strcpy(p + strlen(q), q); // abcabc\0de\0
    printf("%d \n", sizeof(p)); // 字符数组，固定长度=20
    printf("%d \n", strlen(p)); // 字符串长度=6
    printf("%s \n", p); // abcabc\0

    for (int i = 0; i < sizeof(p); i++) {
        printf("%c,", p[i]); // a,b,c,a,b,c,,d,e,,,,,,,,,,,,
    }
    printf("\n");
    return 0;
}