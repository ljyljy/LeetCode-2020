#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <limits.h>

struct ListNode {
    int val;
    struct ListNode* next;
};

struct ListNode* swapPairs(struct ListNode* head) {
    if (!head || !head->next) return head;
    struct ListNode* dummy = (struct ListNode*)malloc(sizeof(struct ListNode));
    dummy->next = head;
    struct ListNode* p = dummy;
    while (p->next && p->next->next) { // ³É¶Ô{n1, n2}
        // p -> n1 -> n2 -> ...
        // p -> n2 -> n1 -> ...
        struct ListNode* n1 = p->next, * n2 = p->next->next, * nxt = n2->next;
        p->next = n2;
        n2->next = n1;
        n1->next = nxt;
        p = p->next->next;
    }
    return dummy->next;
}