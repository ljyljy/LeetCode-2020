//
// Created by ljylj on 2022/8/20.
//

#include <stdlib.h>
#include "ListNode.h"

//struct ListNode {
//    int val;
//    struct ListNode *next;
//};
//
//struct ListNode* createList(int val) {
//    struct ListNode* node = malloc(sizeof (struct ListNode));
//    node->val = val;
//    node->next = NULL;
//    return node;
//}

struct ListNode* addTwoNumbers(struct ListNode* l1, struct ListNode* l2) {
    struct ListNode* dummy = createList(-1);
    struct ListNode* cur = dummy;

    int carry = 0;
    while (l1 || l2 || carry) {
        if (l1) {
            carry += l1->val;
            l1 = l1->next;
        }
        if (l2) {
            carry += l2->val;
            l2 = l2->next;
        }
        cur->next = createList(carry % 10);
        cur = cur->next;
        carry /= 10;
    }
    cur = NULL; // 可加可不加
    return dummy->next;
}
