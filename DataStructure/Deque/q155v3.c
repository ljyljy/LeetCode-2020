#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>

/** ˼·3: ��ջͬʱ�洢����Сֵ+����ֵ��
- �ؼ��㣺
  - 1�� ��ջͬʱ�洢 **��Сֵ + ����ֵ��struct��** һ����׵���ջ��
  - 2�� ��ջ������ŵ���  **��Сֵ + ����ֵ��struct��**
- ʱ�临�Ӷȣ�O(1)
  �ռ临�Ӷȣ�O(n)
 */

typedef struct item_s {
    int val;
    int min; // ��ջ�׵���ǰλ�õ���Сֵ
} item;

typedef struct MinStack_s {
    // ��ʾ��push��౻���� 3*10^4 �Σ��ʿ���������stk��top�����±�
    item stk[30001];
    int top_stk;
} MinStack;


MinStack* minStackCreate() {
    MinStack* obj = (MinStack*)calloc(1, sizeof(MinStack));
    obj->top_stk = 0;
    return obj;
}

void minStackPush(MinStack* obj, int val) {
    if (obj->top_stk == 0 || val < obj->stk[obj->top_stk - 1].min) {
        obj->stk[obj->top_stk].min = val;
    }
    else {
        obj->stk[obj->top_stk].min = obj->stk[obj->top_stk - 1].min;
    }
    obj->stk[obj->top_stk++].val = val;
}

void minStackPop(MinStack* obj) {
    --obj->top_stk;
}

int minStackTop(MinStack* obj) {
    return obj->stk[obj->top_stk - 1].val;
}

int minStackGetMin(MinStack* obj) {
    return obj->stk[obj->top_stk - 1].min;
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