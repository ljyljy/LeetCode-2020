#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <limits.h>
#include <math.h>
#include <ctype.h>

// q705_��ƹ�ϣ����
// ���� - �����ϣ��ͻ

// 769��һ�������������ϣ��ͻ
#define HASH_SIZE 769

 // ���������ϣ��ͻ���������µ�ÿ���ڵ�.hash��ͬ����ͻ��
typedef struct ListNode_s {
    int val;
    struct ListNode_s* next; // ָ����һ���ڵ�
} ListNode;

typedef struct MyHashSet { // ��ϣͰ
    // �� (�ṹ��)ָ�����飬ÿ��Ԫ��ָ��һ��ListNode�ṹ��
    ListNode* buckets[HASH_SIZE]; /*���ɳ�ʼ�� = { NULL }*/;
    // buckets���±�idx�ǹ�ϣֵ��buckets[idx]ָ������ͷ
} MyHashSet;

int hash(int key) {
    return key % HASH_SIZE;
}

MyHashSet* myHashSetCreate() {
    MyHashSet* obj = (MyHashSet*)malloc(sizeof(MyHashSet));
    for (int i = 0; i < HASH_SIZE; i++) {
        obj->buckets[i] = (ListNode*)malloc(sizeof(ListNode)); // ����malloc��
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
    // ͷ�巨
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
        // �����ͷ�����cur��ÿ���ڵ㣡
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
    bool ret = myHashSetContains(obj, 1); // ���� true
    printf("%d ", ret);
    ret = myHashSetContains(obj, 3); // ���� false (δ�ҵ�)
    printf("%d ", ret);
    myHashSetAdd(obj, 2);
    ret = myHashSetContains(obj, 2); // ���� true
    printf("%d ", ret);
    myHashSetRemove(obj, 2);
    ret = myHashSetContains(obj, 2); // ����  false (�Ѿ���ɾ��)
    printf("%d ", ret);
    myHashSetFree(obj);
    return 0;
}