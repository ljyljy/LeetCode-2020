#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <limits.h>

/*typedef*/ struct ListNode {
    int val;
    struct ListNode* next;
}; /* ListNode; */

struct ListNode* mergeTwoLists1(struct ListNode* list1, struct ListNode* list2) {
    struct ListNode dummy = { -1, NULL };
    struct ListNode* p = &dummy;

    while (list1 && list2) {
        if (list1->val < list2->val) {
            p->next = list1;
            list1 = list1->next;
        }
        else {
            p->next = list2;
            list2 = list2->next;
        }
        p = p->next;
    }
    p->next = list1 ? list1 : list2;
    return dummy.next;
}

// ·¨2£ºµÝ¹é
struct ListNode* mergeTwoLists(struct ListNode* list1, struct ListNode* list2) {
    if (list1 == NULL) return list2;
    if (list2 == NULL) return list1;
    if (list1->val < list2->val) {
        list1->next = mergeTwoLists(list1->next, list2);
        return list1;
    }
    else {
        list2->next = mergeTwoLists(list1, list2->next);
        return list2;
    }
}


int main() {
    struct ListNode* list1 = (struct ListNode*)malloc(sizeof(struct ListNode));
    struct ListNode* list2 = (struct ListNode*)malloc(sizeof(struct ListNode));
    list1->val = 1;
    list1->next = NULL;
    list2->val = 2;
    list2->next = NULL;
    struct ListNode* list = mergeTwoLists(list1, list2);
    while (list) {
        printf("%d ", list->val);
        list = list->next;
    }
    return 0;
}