#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <limits.h>
#include <math.h>
#include <ctype.h>

// q705_设计哈希集合
// 链表法 - 解决哈希冲突

// 769是一个质数，避免哈希冲突
#define HASH_SIZE 769

 // 链表法解决哈希冲突，该链表下的每个节点.hash相同（冲突）
typedef struct ListNode_s {
    int val;
    struct ListNode_s* next; // 指向下一个节点
} ListNode;

typedef struct MyHashSet { // 哈希桶
    // ↓ (结构体)指针数组，每个元素指向一个ListNode结构体
    ListNode* buckets[HASH_SIZE]; /*不可初始化 = { NULL }*/;
    // buckets的下标idx是哈希值，buckets[idx]指向链表头
} MyHashSet;

int hash(int key) {
    return key % HASH_SIZE;
}

MyHashSet* myHashSetCreate() {
    MyHashSet* obj = (MyHashSet*)malloc(sizeof(MyHashSet));
    for (int i = 0; i < HASH_SIZE; i++) {
        obj->buckets[i] = (ListNode*)malloc(sizeof(ListNode)); // 勿忘malloc！
        obj->buckets[i]->val = -1;
        obj->buckets[i]->next = NULL;
    }
    return obj;
}

bool listContains(ListNode* head, int key) {
    ListNode* cur = head;
    while (cur != NULL) {
        if (cur->val == key) {
            return true;
        }
        cur = cur->next;
    }
    return false;
}

void listPush(ListNode* head, int key) {
    // 头插法
    ListNode* node = (ListNode*)malloc(sizeof(ListNode));
    node->val = key;
    node->next = head->next;
    head->next = node;
}

void listDelete(ListNode* head, int key) {
    ListNode* cur = head;
    while (cur->next != NULL) {
        if (cur->next->val == key) {
            ListNode* node2del = cur->next;
            cur->next = node2del->next;
            free(node2del);
            node2del = NULL;
            return;
        }
        cur = cur->next;
    }
}

void myHashSetAdd(MyHashSet* obj, int key) {
    int idx = hash(key);
    ListNode* cur = obj->buckets[idx];
    if (!listContains(cur, key)) {
        listPush(cur, key);
    }
}

void myHashSetRemove(MyHashSet* obj, int key) {
    int idx = hash(key);
    ListNode* cur = obj->buckets[idx];
    if (listContains(cur, key)) {
        listDelete(cur, key);
    }
}

bool myHashSetContains(MyHashSet* obj, int key) {
    int idx = hash(key);
    ListNode* cur = obj->buckets[idx];
    return listContains(cur, key);
}

void myHashSetFree(MyHashSet* obj) {
    for (int i = 0; i < HASH_SIZE; i++) {
        ListNode* cur = obj->buckets[i];
        // 勿忘释放链表cur的每个节点！
        while (cur != NULL) {
            ListNode* node2del = cur;
            cur = cur->next;
            free(node2del);
            node2del = NULL;
        }
    }
    free(obj);
    obj = NULL;
}

int main() {
    MyHashSet* obj = myHashSetCreate();
    myHashSetAdd(obj, 1);
    myHashSetAdd(obj, 2);
    bool ret = myHashSetContains(obj, 1); // 返回 true
    printf("%d ", ret);
    ret = myHashSetContains(obj, 3); // 返回 false (未找到)
    printf("%d ", ret);
    myHashSetAdd(obj, 2);
    ret = myHashSetContains(obj, 2); // 返回 true
    printf("%d ", ret);
    myHashSetRemove(obj, 2);
    ret = myHashSetContains(obj, 2); // 返回  false (已经被删除)
    printf("%d ", ret);
    myHashSetFree(obj);
    return 0;
}