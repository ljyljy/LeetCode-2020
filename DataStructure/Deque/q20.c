#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>
#include <stdbool.h>

// д��2��top=-1, ����ջ++top��ջ��Ϊtop����Ϊtop=-1
bool isValid(char* s) {
    int n = strlen(s);
    int stk[n + 1], top = -1;
    for (int i = 0; i < n; i++) {
        char c = s[i];
        if (c == '(') stk[++top] = ')';
        else if (c == '[') stk[++top] = ']';
        else if (c == '{') stk[++top] = '}';
        else if (top == -1 || c != stk[top--]) {
            return false;
        }
    }
    return top == -1; // deque.isEmpty()
}

// д��1��top=1�����Ƽ���
bool isValid1(char* s) {
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