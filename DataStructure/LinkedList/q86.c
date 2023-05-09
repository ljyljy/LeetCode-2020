#include <stdio.h>
#include <stdlib.h>
#include <math.h>
// #include <string.h>
// #include <limits.h>
#include <stdbool.h>

// q86.c
struct ListNode {
    int val;
    struct ListNode* next;
};

// q86. �ָ�����
typedef struct ListNode Node;

struct ListNode* partition(struct ListNode* head, int x) {
    Node node1 = { -101, head }, * dummy1 = &node1; // v1: ��ʼ��dummy1
    Node* dummy2 = (Node*)calloc(1, sizeof(Node));  // v2: ��ʼ��dummy2
    dummy2->val = -101, dummy2->next = head; // ���÷Ƿ�ֵ

    Node* cur = head, * x_lt = dummy1, * x_ge = dummy2; // lt (<x) ;  ge (>=x)

    while (cur) {
        if (cur->val < x) {
            x_lt->next = cur;
            x_lt = x_lt->next;
        }
        else {
            x_ge->next = cur;
            x_ge = x_ge->next;
        }
        cur = cur->next;
    }
    // cur��ͷ��, ��ʱx_lt��x_ge����Ϊ�գ���ָ�����һ��������������
    x_ge->next = NULL; // 1) �������ȣ�x_ge��β����NULL�����򣬿���x_ge��Ϊdummy2(nextΪhead)������¾䣬����ʹ�������ѭ����
    x_lt->next = dummy2->next; // 2) ��󣬴���x_lt �� x_ge��ͷ��
    // printf("x_lt == dummy1?: %d, x_lt->val = %d\n", x_lt == dummy1, x_lt->val);
    // printf("x_ge == dummy2?: %d, x_ge->val = %d\n", x_ge == dummy2, x_ge->val);

    return dummy1->next;
}

int main()
{
    int nums[] = { 1, 4, 3, 2, 5, 2 };
    int n = sizeof(nums) / sizeof(nums[0]);

    Node* dummy = (Node*)calloc(1, sizeof(Node));
    Node* cur = dummy;
    for (int i = 0; i < n; i++) {
        Node* tmp = (Node*)calloc(1, sizeof(Node));
        tmp->val = nums[i];
        cur->next = tmp;
        cur = cur->next;
    }

    Node* res = partition(dummy->next, 3);
    while (res) {
        printf("%d ", res->val);
        res = res->next;
    }
    printf("\n");
}