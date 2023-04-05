#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>
#include <stdbool.h>

// д��1��top=0���Ƽ���
// top = 0, ����ջtop++����ջ��Ϊtop-1, ȡջ������ջ��stk[--top]����Ϊtop = 0
bool isValid1(char* s) {
    int n = strlen(s);
    char* stk = (char*)malloc(sizeof(char) * n);
    int top = 0;// top��ʼ���ǡ������롿ջ����idx
    for (int i = 0; i < n; i++) {
        char c = s[i];
        if (c == '(') stk[top++] = ')';
        else if (c == '[') stk[top++] = ']';
        else if (c == '{') stk[top++] = '}';
        else if (top == 0 || c != stk[--top]) {
            return false;
        }
    }
    return top == 0; // ��������deque.isEmpty()
}

// д��1-2��top=0���Ƽ���
// top = 0, ����ջtop++����ջ��Ϊtop-1, ȡջ������ջ��stk[--top]����Ϊtop = 0
bool isValid(char* s) {
    int n = strlen(s);
    int stk[n]; // char* stk����ת��Ϊint stk[]�� char stk[]
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

// д��2-1��top=-1, ����ջ++top����ջ��Ϊtop, ȡջ������ջ��stk[top--]����Ϊtop=-1
bool isValid(char* s) {
    int n = strlen(s);
    char* stk = (char*)calloc(n, sizeof(char));
    int top = -1;

    for (int i = 0; i < n; i++) {
        char c = s[i];
        if (c == '(') stk[++top] = ')';
        else if (c == '[') stk[++top] = ']';
        else if (c == '{') stk[++top] = '}';
        else if (top == -1 || c != stk[top--]) {
            return false;
        }
    }
    return top == -1;
}

// д��2-2��top=-1, ����ջ++top����ջ��Ϊtop, ȡջ������ջ��stk[top--]����Ϊtop=-1
bool isValid(char* s) {
    int n = strlen(s);
    char stk[n]; // char* stk����ת��Ϊint stk[]�� char stk[]
    int top = -1;

    // ������ ��2-1 һ��
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

int main() {
    char* s = "()[]{[]}";
    printf("%d\n", isValid(s));
}