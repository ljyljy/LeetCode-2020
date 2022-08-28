//
// Created by ljylj on 2022/8/20.
//

#ifndef CSTUDY_LISTNODE_H
#define CSTUDY_LISTNODE_H

#include <stdlib.h>

struct ListNode {
    int val;
    struct ListNode *next;
};

struct ListNode* createList(int val) ;

struct ListNode* createList2(int val, struct ListNode* next) ;

struct ListNode* createListByArr(int* nums, int n) ;

void printList(struct ListNode* list) ;

#endif //CSTUDY_LISTNODE_H
