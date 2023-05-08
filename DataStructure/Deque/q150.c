#include <stdio.h>
#include <stdlib.h>
#include <string.h>
// #include <math.h>
// #include <limits.h>

// q150.c, 逆波兰表达式(同HW04)

int evalRPN(char** tokens, int n) {
    int stk[n], top = 0;
    memset(stk, 0, sizeof(stk));

    char* ops = "+-*/";
    for (int i = 0; i < n; i++) {
        if (strlen(tokens[i]) == 1 && strspn(tokens[i], ops) == 1) {
            char op = tokens[i][0];
            int b = stk[--top];
            int a = stk[--top];
            int c = 0;
            switch (op) {
            case '+': c = a + b; break;
            case '-': c = a - b; break;
            case '*': c = a * b; break;
            case '/': c = a / b; break;
            }
            stk[top++] = c;
        }
        else {
            // sscanf(tokens[i], "%d", &stk[top++]); // 法2
            stk[top++] = atoi(tokens[i]); // 法1
        }
    }
    return stk[top - 1]; // PEEK
}

int main() {
    char* tokens[] = { "2", "1", "+", "3", "*" };
    int n = sizeof(tokens) / sizeof(char*);
    int res = evalRPN(tokens, n);
    printf("%d", res);
    return 0;
}