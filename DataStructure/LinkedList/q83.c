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

// 83. ɾ�����������е��ظ�Ԫ��
typedef struct ListNode Node;

struct ListNode* deleteDuplicates(struct ListNode* head) {
    Node* dummy = (Node*)calloc(1, sizeof(Node));
    dummy->val = INT_MAX; // ������Ϸ�val�ص�
    dummy->next = head;
    Node* p = dummy, * q = p->next;
    while (p && q) {
        while (p && q && p->val == q->val) {
            q = q->next; // ������p.val��ȵĽڵ�
        } // �˳�ʱ��qָ��ͬ��p�ĵ�
        if (p->next != q) { // ��q����ֵΪp->next�������£�ִ�й�ȥ�أ�
            p->next = q;
        }
        p = p->next;
        if (p) q = p->next; // ����if��
    }
    return dummy->next;
}

int main() {
    int arr[] = { 1, 1, 2, 3, 3 };
    int n = sizeof(arr) / sizeof(int);
    // ����arr����������
    Node* head = (Node*)calloc(1, sizeof(Node));
    Node* p = head;
    for (int i = 0; i < n; i++) {
        Node* node = (Node*)calloc(1, sizeof(Node));
        node->val = arr[i];
        p->next = node;
        p = p->next;
    }
    p->next = NULL;
    // ȥ��
    Node* res = deleteDuplicates(head->next);
    while (res) {
        printf("%d ", res->val);
        res = res->next;
    }
    return 0;
}