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

// q86. 分隔链表
typedef struct ListNode Node;

struct ListNode* partition(struct ListNode* head, int x) {
    Node node1 = { -101, head }, * dummy1 = &node1; // v1: 初始化dummy1
    Node* dummy2 = (Node*)calloc(1, sizeof(Node));  // v2: 初始化dummy2
    dummy2->val = -101, dummy2->next = head; // 设置非法值

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
    // cur到头了, 此时x_lt、x_ge均不为空，且指向最后一个满足条件的数
    x_ge->next = NULL; // 1) 必须首先：x_ge的尾部置NULL（否则，可能x_ge仍为dummy2(next为head)，结合下句，最终使链表产生循环）
    x_lt->next = dummy2->next; // 2) 最后，串联x_lt 与 x_ge的头部
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