#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>

/** ˼·
˫ջ������ջ + ����ջ��
�ؼ��㣺
1�� **����ջ**�����洢˳����ջ������
2�� **����ջ**����˳��С�ڵ��ڵ�ǰֵ�ĸ���ջ��ֵ ��ջ
3�� ����ջ�����þ���ά��һ��**�ǵ�����ջ**
ʱ�临�Ӷȣ�O(1)
�ռ临�Ӷȣ�O(n)
 */
typedef struct {
    // ��ʾ��push��౻���� 3*10^4 �Σ��ʿ���������stk��top�����±�
    int stk[30001];
    int top_stk;
    int minStk[30001]; // �����ݼ�ջ������ջ
    int top_minStk;
} MinStack;


MinStack* minStackCreate() {
    MinStack* obj = (MinStack*)calloc(1, sizeof(MinStack));
    obj->top_stk = 0, obj->top_minStk = 0;
    return obj;
}

void minStackPush(MinStack* obj, int val) {
    obj->stk[obj->top_stk++] = val;
    // ����ջ����Ϊ�� || val<=minStkջ��������ջ
    if (obj->top_minStk == 0 || val <= obj->minStk[obj->top_minStk - 1]) {
        obj->minStk[obj->top_minStk++] = val;
    }
}

void minStackPop(MinStack* obj) {
    // ����ջԪ��ΪminStkջ������minStkҲ��ջ
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