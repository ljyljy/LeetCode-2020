#include <stdio.h>
#include <stdlib.h>
// #include <math.h>
// #include <string.h>
// #include <limits.h>
#include <stdbool.h>

typedef struct {
    int stk1[101]; // ��Ŀ�������� 100 �� push
    int stk2[101];
    int top1;
    int top2;
} MyQueue;


MyQueue* myQueueCreate() {
    MyQueue* obj = (MyQueue*)calloc(1, sizeof(MyQueue));
    // obj->stk1 = {0}; // ���⣺�Ϸ�num ��[1,9]
    return obj;
}

// ����stk1:[3,2,1(top1)], ��push(4):
// - 1) ��stk1����stk2[1,2,3(top2)]
// - 2) ��x��4����ջstk1��[4(top1)]
// - 3) ��stk2����stk1��[4, 3,2,1(top1)]
void myQueuePush(MyQueue* obj, int x) {
    // ���뱣֤����Ԫ��xλ��ջ�ף�top1ʼ��ָ������Ԫ��/��ͷ�����push��ͷ��
    // - ��ˣ�����Ԫ��x��Ҫ����stk1��պ��ջ��
    while (obj->top1 > 0) { // 1)
        obj->stk2[obj->top2++] = obj->stk1[--obj->top1];
    }
    obj->stk1[obj->top1++] = x; // 2)
    while (obj->top2 > 0) { // 1)
        obj->stk1[obj->top1++] = obj->stk2[--obj->top2];
    }
}

int myQueuePop(MyQueue* obj) {
    return obj->stk1[--obj->top1]; // ����Ԫ��/��ͷ(top)����
}

int myQueuePeek(MyQueue* obj) { // ��ͷ/ջ��
    return obj->stk1[obj->top1 - 1];
}

bool myQueueEmpty(MyQueue* obj) {
    return obj->top1 == 0 && obj->top2 == 0;
}

void myQueueFree(MyQueue* obj) {
    free(obj);
}

int main() {
    MyQueue* obj = myQueueCreate();
    myQueuePush(obj, 1); // ����Ԫ��/��ͷ(top)����
    myQueuePush(obj, 2);
    myQueuePush(obj, 3);
    myQueuePush(obj, 4); // stk1:[4,3,2,1(top1)]
    printf("%d\n", myQueuePeek(obj)); // 1
    printf("%d\n", myQueuePop(obj)); // 1������, stk1:[4,3,2(top1)]
    printf("%d\n", myQueueEmpty(obj)); // false
    myQueueFree(obj);
}

/**
 * Your MyQueue struct will be instantiated and called as such:
 * MyQueue* obj = myQueueCreate();
 * myQueuePush(obj, x);

 * int param_2 = myQueuePop(obj);

 * int param_3 = myQueuePeek(obj);

 * bool param_4 = myQueueEmpty(obj);

 * myQueueFree(obj);
*/