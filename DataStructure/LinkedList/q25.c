#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <limits.h>
#include <stdbool.h>

struct ListNode {
    int val;
    struct ListNode* next;
};

struct ListNode* reverseList(struct ListNode* head);

struct ListNode* reverseKGroup(struct ListNode* head, int k) {
    if (head == NULL) return NULL;
    struct ListNode* dummy = (struct ListNode*)malloc(sizeof(struct ListNode));
    dummy->next = head;
    struct ListNode* pre = dummy, * end = dummy;

    while (end->next != NULL) {
        for (int i = 0; i < k && end; i++) {
            end = end->next;
        }
        if (end == NULL) break;
        struct ListNode* start = pre->next, * nxt = end->next;

        end->next = NULL;
        pre->next = reverseList(start);
        start->next = nxt;

        pre = start;
        end = start;
    }
    return dummy->next;
}

struct ListNode* reverseList(struct ListNode* head) {
    struct ListNode* pre = NULL;
    struct ListNode* cur = head;
    while (cur != NULL) {
        struct ListNode* nxt = cur->next;
        cur->next = pre;
        pre = cur;
        cur = nxt;
    }
    return pre;
}
