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

// 82. ɾ�����������е��ظ�Ԫ�� II

typedef struct ListNode Node;

struct ListNode* deleteDuplicates(struct ListNode* head) {
    Node* dummy = (Node*)calloc(1, sizeof(Node));
    dummy->val = -101; // ��֤��Ϸ�val���ص�
    dummy->next = head;

    Node* p = dummy, * q = p->next;
    while (p->next) {
        q = p->next;
        while (q && p->next->val == q->val) {
            q = q->next;
        } // q�Ʊػ���һ��
        if (p->next->next != q) {
            p->next = q; // ? �����޷���֤q��q.next���Ƿ��ظ������p�����Ժ��ƣ����ܻ������q'ȥ�أ���Ҫprev,��p���ɺ��ƣ�
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