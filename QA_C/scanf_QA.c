#include <stdlib.h>
#include <stdio.h>

int main() {
    long input;
    int ch; // 使用int（范围更大），而非char，来接收getchar的返回值！
    while (scanf("%ld", &input) != 1) {
        while ((ch = getchar()) != '\n') {
            putchar(ch);
        }
        printf(" is not a long number.\n");
    }
}
/**
low
low is not a long number.
dqwf1021 3203
dqwf1021 3203 is not a long number.
132313

 */