/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>
#include <stdbool.h>

#ifndef DIM
#define DIM(arr) sizeof(arr)/sizeof(*arr)
#endif

typedef struct ListNode {
    int val;
    struct ListNode* next;
} ListNode;

// typedef struct ListNode ListNode;

void FREE(void* p) {
    free(p);
    p = NULL;
}


ListNode* creatNode(int val) {
    ListNode* dummy = (ListNode*)malloc(sizeof(ListNode));
    dummy->val = val, dummy->next = NULL;
    return dummy;
}

ListNode* removeNthFromEnd(ListNode* head, int n) {
    ListNode* dummy = creatNode(-1);
    dummy->next = head;
    ListNode* fast = head, * slow_prev = dummy;
    for (int i = 0; i < n && fast; i++) {
        fast = fast->next;
    }
    while (fast) {
        slow_prev = slow_prev->next;
        fast = fast->next;
    }
    slow_prev->next = slow_prev->next->next;
    ListNode* res = dummy->next;
    FREE(dummy);
    return res;
}


ListNode* creatList(int nums[], int n) {
    ListNode* dummy = creatNode(-1);
    ListNode* p = dummy;
    for (int i = 0; i < n; i++)
    {
        p->next = creatNode(nums[i]);
        p = p->next;
    }
    return dummy->next;
}


int main() {
    int head[] = { 1,2,3,4,5 }, k = 2;
    int n = DIM(head);
    ListNode* list = creatList(head, n);
    ListNode* res = removeNthFromEnd(list, k);
    ListNode* p = res;
    for (int i = 0; i < n - 1 && p; i++) {
        printf("%d ", p->val);
        p = p->next;
    }
    printf("\n");

    int head2[] = { 1,2 }, k2 = 1;
    int n2 = DIM(head2);
    ListNode* list2 = creatList(head2, n2);
    ListNode* res2 = removeNthFromEnd(list2, k2);
    ListNode* p2 = res2;
    for (int i = 0; i < n2 - 1 && p2; i++) {
        printf("%d ", p2->val);
        p2 = p2->next;
    }

}