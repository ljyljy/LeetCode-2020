#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <limits.h>

struct ListNode* mergeHelper(struct ListNode** lists, int n, int start, int end);
struct ListNode* mergeTwoLists(struct ListNode* left, struct ListNode* right);

struct ListNode {
    int val;
    struct ListNode* next;
};

struct ListNode* mergeKLists(struct ListNode** lists, int n) {
    if (lists == NULL || n == 0) return NULL;
    return mergeHelper(lists, n, 0, n - 1);
}

struct ListNode* mergeHelper(struct ListNode** lists, int n, int start, int end) {
    if (start == end) return lists[start];
    int mid = start + end >> 1;
    struct ListNode* left = mergeHelper(lists, n, start, mid);
    struct ListNode* right = mergeHelper(lists, n, mid + 1, end);
    return mergeTwoLists(left, right);
}

struct ListNode* mergeTwoLists(struct ListNode* l1, struct ListNode* l2) {
    // struct ListNode dummy = { -1, NULL };
    struct ListNode* dummy = (struct ListNode*)malloc(sizeof(struct ListNode));
    struct ListNode* p = dummy;
    while (l1 && l2) {
        if (l1->val < l2->val) {
            p->next = l1;
            l1 = l1->next;
        }
        else {
            p->next = l2;
            l2 = l2->next;
        }
        p = p->next;
    }
    p->next = l1 ? l1 : l2; // 不可以写俩if（可if-else），若l1,l2全为空，则p不会赋值给任意一个，即尾部未置NULL！
    return dummy->next;
}

