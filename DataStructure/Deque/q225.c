#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
// #include <string.h>
// #include <math.h>
// #include <limits.h>

typedef struct {
    int* stk;
    int top;
} MyStack;


MyStack* myStackCreate() {
    MyStack* obj = (MyStack*)calloc(1, sizeof(MyStack));
    obj->stk = (int*)calloc(101, sizeof(int)); // 题目：最多调用100 次 push
    obj->top = 0;
    return obj;
}

void myStackPush(MyStack* obj, int x) {
    obj->stk[obj->top++] = x;
}

int myStackPop(MyStack* obj) {
    return obj->stk[--obj->top];
}

int myStackTop(MyStack* obj) {
    return obj->stk[obj->top - 1];
}

bool myStackEmpty(MyStack* obj) {
    return obj->top == 0;
}

void myStackFree(MyStack* obj) {
    free(obj->stk);
    free(obj);
}
