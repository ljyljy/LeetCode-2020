//
// Created by ljylj on 2022/8/20.
//

#ifndef CSTUDY_LISTNODE_H
#define CSTUDY_LISTNODE_H

#include <stdlib.h>
#include "ListNode.h"

struct ListNode* createList(int val) {
    struct ListNode* node = malloc(sizeof (struct ListNode));
    node->val = val;
    node->next = NULL;
    return node;
}

struct ListNode* createList2(int val, struct ListNode* next) {
    struct ListNode* node = malloc(sizeof (struct ListNode));
    node->val = val;
    node->next = next;
    return node;
}

struct ListNode* createListByArr(int* nums, int n) {
    struct ListNode* dummy = malloc(sizeof(struct ListNode));
    struct ListNode* cur = dummy;
    for (int i = 0; i < n; ++i) {
        cur->next = createList(nums[i]);
        cur = cur->next;
    }
    cur->next = NULL; // ÎðÍü£¡·ñÔòËÀÑ­»·£¡
    return dummy->next;
}

void printList(struct ListNode* list) {
    struct ListNode* cur = list;
    while (cur) {
        printf("%d ", cur->val);
        cur = cur->next;
    }
    printf("\n");
}
#endif //CSTUDY_LISTNODE_H
