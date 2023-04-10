#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>

/** 思路
双栈（数据栈 + 辅助栈）
关键点：
1： **数据栈**用来存储顺序入栈的数据
2： **辅助栈**按照顺序将小于等于当前值的辅助栈顶值 入栈
3： 辅助栈的作用就是维护一个**非递增的栈**
时间复杂度：O(1)
空间复杂度：O(n)
 */
typedef struct {
    // 提示：push最多被调用 3*10^4 次，故可用数组型stk，top控制下标
    int stk[30001];
    int top_stk;
    int minStk[30001]; // 单调递减栈，辅助栈
    int top_minStk;
} MinStack;


MinStack* minStackCreate() {
    MinStack* obj = (MinStack*)calloc(1, sizeof(MinStack));
    obj->top_stk = 0, obj->top_minStk = 0;
    return obj;
}

void minStackPush(MinStack* obj, int val) {
    obj->stk[obj->top_stk++] = val;
    // 辅助栈：若为空 || val<=minStk栈顶，则入栈
    if (obj->top_minStk == 0 || val <= obj->minStk[obj->top_minStk - 1]) {
        obj->minStk[obj->top_minStk++] = val;
    }
}

void minStackPop(MinStack* obj) {
    // 若出栈元素为minStk栈顶，则minStk也出栈
    if (obj->stk[obj->top_stk - 1] == obj->minStk[obj->top_minStk - 1]) {
        --obj->top_minStk;
    }
    --obj->top_stk;
}

int minStackTop(MinStack* obj) {
    return obj->stk[obj->top_stk - 1];
}

int minStackGetMin(MinStack* obj) {
    return obj->minStk[obj->top_minStk - 1];
}

void minStackFree(MinStack* obj) {
    free(obj);
}

/**
 * Your MinStack struct will be instantiated and called as such:
 * MinStack* obj = minStackCreate();
 * minStackPush(obj, val);

 * minStackPop(obj);

 * int param_3 = minStackTop(obj);

 * int param_4 = minStackGetMin(obj);

 * minStackFree(obj);
*/

int main() {
    MinStack* obj = minStackCreate();
    minStackPush(obj, -2);
    minStackPush(obj, 0);
    minStackPush(obj, -3);
    printf("%d ", minStackGetMin(obj)); // -3
    minStackPop(obj);
    printf("%d ", minStackTop(obj)); // 0
    printf("%d ", minStackGetMin(obj)); // -2
    minStackFree(obj);
    return 0;
}