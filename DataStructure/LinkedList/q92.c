#include <stdio.h>
#include <stdlib.h>
#include <math.h>
// #include <string.h>
// #include <limits.h>
#include <stdbool.h>

// q92.c
struct ListNode {
    int val;
    struct ListNode* next;
};

typedef struct ListNode Node;

Node* reverseList(Node* head);

struct ListNode* reverseBetween(struct ListNode* head, int left, int right) {
    Node dummyNode = { -501, head };
    Node* dummy = &dummyNode;
    Node* L_Prev = dummy;

    int i = 1; // L, R.idx从1起！
    while (i++ < left) {
        L_Prev = L_Prev->next;
    }
    int len = right - left + 1;
    int k = 0;
    Node* start = L_Prev->next, * end = start;
    while (k++ < len - 1) { // end: start起跳 len-1次
        end = end->next;
    }
    Node* R_nxt = end->next;
    end->next = NULL; // 准备反转
    L_Prev->next = reverseList(start);
    start->next = R_nxt;
    return dummy->next;
}

Node* reverseList(Node* head) {
    Node* pre = NULL;
    Node* cur = head;
    while (cur) {
        Node* nxt = cur->next;
        cur->next = pre;
        pre = cur;
        cur = nxt;
    }
    return pre;
}

int main()
{
    int nums[] = { 1, 2, 3, 4, 5 };
    int n = sizeof(nums) / sizeof(nums[0]);

    Node* dummy = (Node*)calloc(1, sizeof(Node));
    Node* cur = dummy;
    for (int i = 0; i < n; i++) {
        Node* tmp = (Node*)calloc(1, sizeof(Node));
        tmp->val = nums[i];
        cur->next = tmp;
        cur = cur->next;
    }

    Node* res = reverseBetween(dummy->next, 2, 4); // 1 [2,3,4] 5
    while (res) {
        printf("%d ", res->val); // 1 4 3 2 5
        res = res->next;
    }
    return 0;
}