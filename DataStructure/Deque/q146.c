#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
// #include <string.h>
// #include <math.h>
// #include <limits.h>

// q146.c
// 法2：略（双向链表 + 哈希表UT_HASH）
// https://leetcode.cn/problems/lru-cache/solution/by-nirvana-10-7xik/

// 法1: 双向链表 + keys数组代替UT_HASH
typedef struct Node_s {
    bool keyExist; // key存在与否。具体key对应nodes数组的下标
    int value;
    struct Node_s* prev;
    struct Node_s* next;
} Node; // 自建：双向链表Node, 包括<k,v>和prev/next指针

typedef struct {
    int curCnt; // 当前容量
    int capacity; // 最大容量
    Node* head; // 头指针, dummy
    Node* tail; // 尾指针, dummy
    Node nodes[10001]; // keys数组代替哈希，0 <= key <= 10000
} LRUCache; // 双向链表 + 哈希表<key, 链表Node>

void removeNode(Node* node);
void addToHead(LRUCache* obj, Node* node);

LRUCache* lRUCacheCreate(int capacity) {
    LRUCache* obj = (LRUCache*)calloc(1, sizeof(LRUCache));
    // memset(obj->nodes, 0, 10001 * sizeof(Node));
    obj->curCnt = 0;
    obj->capacity = capacity;
    obj->head = (Node*)calloc(1, sizeof(Node));
    obj->tail = (Node*)calloc(1, sizeof(Node));
    obj->head->next = obj->tail;
    obj->tail->prev = obj->head;
    return obj;
}

int lRUCacheGet(LRUCache* obj, int key) {
    if (obj->nodes[key].keyExist == false) {
        return -1;
    }
    Node* node = &(obj->nodes[key]);
    removeNode(node);
    addToHead(obj, node);
    return node->value;
}

void lRUCachePut(LRUCache* obj, int key, int value) {
    if (obj->nodes[key].keyExist == true) {
        Node* node = &(obj->nodes[key]);
        node->value = value;
        removeNode(node); // 勿忘！
        addToHead(obj, node); // 勿忘！
    }
    else {
        Node* node = &(obj->nodes[key]);
        node->keyExist = true;
        node->value = value;
        addToHead(obj, node); // 勿忘！
        obj->curCnt++;

        if (obj->curCnt > obj->capacity) {
            Node* tail = obj->tail->prev;
            removeNode(tail);
            tail->keyExist = false;
            obj->curCnt--;
        }
    }
}

void lRUCacheFree(LRUCache* obj) {
    free(obj->head);
    free(obj->tail);
    free(obj);
}

void removeNode(Node* node) {
    node->prev->next = node->next;
    node->next->prev = node->prev;
}

void addToHead(LRUCache* obj, Node* node) {
    node->next = obj->head->next;
    node->prev = obj->head;
    obj->head->next->prev = node;
    obj->head->next = node;
}

int main() {
    LRUCache* obj = lRUCacheCreate(2);
    lRUCachePut(obj, 1, 1);
    lRUCachePut(obj, 2, 2);
    printf("%d\n", lRUCacheGet(obj, 1)); // 1
    lRUCachePut(obj, 3, 3);
    printf("%d\n", lRUCacheGet(obj, 2)); // -1
    lRUCachePut(obj, 4, 4);
    printf("%d\n", lRUCacheGet(obj, 1)); // -1
    printf("%d\n", lRUCacheGet(obj, 3)); // 3
    printf("%d\n", lRUCacheGet(obj, 4)); // 4
    lRUCacheFree(obj);
    return 0;
}