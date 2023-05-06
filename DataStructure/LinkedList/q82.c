#include <stdio.h>
#include <stdlib.h>
// #include <string.h>
// #include <math.h>
// #include <limits.h>

// q82.c
struct ListNode {
    int val;
    struct ListNode* next;
};

// 82. 删除排序链表中的重复元素 II

typedef struct ListNode Node;

struct ListNode* deleteDuplicates(struct ListNode* head) {
    Node* dummy = (Node*)calloc(1, sizeof(Node));
    dummy->val = -101; // 保证与合法val不重叠
    dummy->next = head;

    Node* p = dummy, * q = p->next;
    while (p->next) {
        q = p->next;
        while (q && p->next->val == q->val) {
            q = q->next;
        } // q势必会走一步
        if (p->next->next != q) {
            p->next = q; // ? 但是无法保证q与q.next还是否重复！因此p不可以后移！可能还需对新q'去重（需要prev,即p不可后移）
        }
        else {
            p = p->next;
        }
    }
    return dummy->next;
}

int main() {
    int arr[] = { 1, 1, 2, 3, 3, 4, 4, 5 };
    int n = sizeof(arr) / sizeof(int);
    // 根据arr，构造链表
    Node* head = (Node*)calloc(1, sizeof(Node));
    Node* p = head;
    for (int i = 0; i < n; i++) {
        Node* node = (Node*)calloc(1, sizeof(Node));
        node->val = arr[i];
        p->next = node;
        p = p->next;
    }
    p->next = NULL;
    // 去重
    Node* res = deleteDuplicates(head->next);
    while (res) {
        printf("%d ", res->val);
        res = res->next;
    }
    return 0;
}