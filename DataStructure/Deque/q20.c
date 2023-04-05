#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>
#include <stdbool.h>

// 写法1：top=0（推荐）
// top = 0, 若入栈top++，则栈顶为top-1, 取栈顶并弹栈：stk[--top]，空为top = 0
bool isValid1(char* s) {
    int n = strlen(s);
    char* stk = (char*)malloc(sizeof(char) * n);
    int top = 0;// top：始终是【待填入】栈顶的idx
    for (int i = 0; i < n; i++) {
        char c = s[i];
        if (c == '(') stk[top++] = ')';
        else if (c == '[') stk[top++] = ']';
        else if (c == '{') stk[top++] = '}';
        else if (top == 0 || c != stk[--top]) {
            return false;
        }
    }
    return top == 0; // 【勿忘】deque.isEmpty()
}

// 写法1-2：top=0（推荐）
// top = 0, 若入栈top++，则栈顶为top-1, 取栈顶并弹栈：stk[--top]，空为top = 0
bool isValid(char* s) {
    int n = strlen(s);
    int stk[n]; // char* stk可以转化为int stk[]或 char stk[]
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

// 写法2-1：top=-1, 若入栈++top，则栈顶为top, 取栈顶并弹栈：stk[top--]，空为top=-1
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

// 写法2-2：top=-1, 若入栈++top，则栈顶为top, 取栈顶并弹栈：stk[top--]，空为top=-1
bool isValid(char* s) {
    int n = strlen(s);
    char stk[n]; // char* stk可以转化为int stk[]或 char stk[]
    int top = -1;

    // 以下与 法2-1 一样
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