#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <math.h>
#include <string.h>
#include <limits.h>

void reverse(char* s);
void reverse2(char* s);

char* addBinary(char* a, char* b) {
    int n1 = strlen(a), n2 = strlen(b);
    int n = fmax(n1, n2);
    char* res = (char*)calloc(n + 2, sizeof(char)); // carry + sum +'\0'

    int carry = 0, idx = 0;
    for (int i = n1 - 1, j = n2 - 1; i >= 0 || j >= 0 || carry; i--, j--) {
        int x = i >= 0 ? a[i] - '0' : 0;
        int y = j >= 0 ? b[j] - '0' : 0;
        res[idx++] = (x + y + carry) % 2 + '0'; // int2char
        carry = (x + y + carry) / 2;
    }
    reverse(res);
    return  res;
}

void reverse(char* s) {
    int n = strlen(s);
    for (int i = 0; i < n / 2; i++) {
        char tmp = s[i];
        s[i] = s[n - 1 - i];
        s[n - 1 - i] = tmp;
    }
}

void reverse2(char* s) {
    int n = strlen(s);
    int i = 0, j = n - 1;
    while (i < j) {
        char tmp = s[i];
        s[i] = s[j];
        s[j] = tmp;
        i++; j--;
    }
}

int main() {
    char* a = "1011", * b = "1011";
    printf("%s\n", addBinary(a, b));
    return 0;
}