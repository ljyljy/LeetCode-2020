#include <stdio.h>
#include <stdlib.h>
// #include <math.h>
// #include <string.h>
// #include <limits.h>
#include <stdbool.h>

typedef struct {
    int stk1[101]; // 题目：最多调用 100 次 push
    int stk2[101];
    int top1;
    int top2;
} MyQueue;


MyQueue* myQueueCreate() {
    MyQueue* obj = (MyQueue*)calloc(1, sizeof(MyQueue));
    // obj->stk1 = {0}; // 由题：合法num ∈[1,9]
    return obj;
}

// 例：stk1:[3,2,1(top1)], 需push(4):
// - 1) 将stk1倒入stk2[1,2,3(top2)]
// - 2) 将x（4）入栈stk1：[4(top1)]
// - 3) 将stk2倒入stk1：[4, 3,2,1(top1)]
void myQueuePush(MyQueue* obj, int x) {
    // 必须保证最新元素x位于栈底，top1始终指向最老元素/队头（最后push队头）
    // - 因此，最新元素x需要放在stk1清空后的栈底
    while (obj->top1 > 0) { // 1)
        obj->stk2[obj->top2++] = obj->stk1[--obj->top1];
    }
    obj->stk1[obj->top1++] = x; // 2)
    while (obj->top2 > 0) { // 1)
        obj->stk1[obj->top1++] = obj->stk2[--obj->top2];
    }
}

int myQueuePop(MyQueue* obj) {
    return obj->stk1[--obj->top1]; // 最老元素/队头(top)出列
}

int myQueuePeek(MyQueue* obj) { // 队头/栈底
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
    myQueuePush(obj, 1); // 最老元素/队头(top)入列
    myQueuePush(obj, 2);
    myQueuePush(obj, 3);
    myQueuePush(obj, 4); // stk1:[4,3,2,1(top1)]
    printf("%d\n", myQueuePeek(obj)); // 1
    printf("%d\n", myQueuePop(obj)); // 1，出列, stk1:[4,3,2(top1)]
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