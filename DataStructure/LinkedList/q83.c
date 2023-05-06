#include <stdio.h>
#include <stdlib.h>
// #include <string.h>
// #include <math.h>
#include <limits.h>

// q83.c
struct ListNode {
    int val;
    struct ListNode* next;
};

// 83. 删除排序链表中的重复元素
typedef struct ListNode Node;

struct ListNode* deleteDuplicates(struct ListNode* head) {
    Node* dummy = (Node*)calloc(1, sizeof(Node));
    dummy->val = INT_MAX; // 避免与合法val重叠
    dummy->next = head;
    Node* p = dummy, * q = p->next;
    while (p && q) {
        while (p && q && p->val == q->val) {
            q = q->next; // 跳过与p.val相等的节点
        } // 退出时，q指向不同于p的点
        if (p->next != q) { // 若q（初值为p->next）被更新（执行过去重）
            p->next = q;
        }
        p = p->next;
        if (p) q = p->next; // 勿忘if！
    }
    return dummy->next;
}

int main() {
    int arr[] = { 1, 1, 2, 3, 3 };
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