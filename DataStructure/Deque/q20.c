#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>
#include <stdbool.h>


bool isValid(char* s) {
    int n = strlen(s);
    char* stk = (char*)malloc(sizeof(char) * n);
    int top = 0;
    for (int i = 0; i < n; i++) {
        char c = s[i];
        if (c == '(') stk[top++] = ')';
        else if (c == '[') stk[top++] = ']';
        else if (c == '{') stk[top++] = '}';
        else if (top == 0 || c != stk[--top]) {
            return false;
        }
    }
    return top == 0;
}

int main() {
    char* s = "()[]{[]}";
    printf("%d\n", isValid(s));
}