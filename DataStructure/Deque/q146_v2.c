#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
// #include <string.h>
// #include <math.h>
// #include <limits.h>

/* 分析：
1) LRUCache需要有capacity最大容量、以及当前容量curCnt
2) get要求快速找到key的索引，此处使用数组代替哈希
  - 全局：Nodes nodes[N]，下标为key值，定义于LRUCache内
    - 起到Map<key, Node{isExist、val、*prev、*next}>的作用
  - 全局/Node内部：bool isKeyExist[10001]，下标为key值，存在则true
3) put要求对象拥有<k,v>对，可更新，有序（数组qsort× 或 链表头插法√） ，便于插入、删除（∴该对象可使用【双向链表】）
总结：
  - LRUCache存储的主要对象应是一个链表Lists，且为双向（需要全局视野下保持对其head和tail的监视）
    - 具体链表结点ListNode：k-v对、前&后继
4）易错：涉及更改链表结构、结构体成员值等操作，必须取指针！
  - 直接的【赋值语句（浅拷贝）】+取地址，操作对象已经不是其原身了！
*/

#define MAX_NUM 10001
typedef struct Node_s {
    int key;
    int val;
    struct Node_s* next;
    struct Node_s* prev;
} Node;

typedef struct {
    int curCnt;
    int capacity;
    Node nodes[MAX_NUM];
    Node* head;
    Node* tail;
    bool isKeyExist[MAX_NUM]; // 法2：全局视野下，控制key存在与否
} LRUCache;


void removeNode(Node* node);
void addToHead(LRUCache* obj, Node* node);

LRUCache* lRUCacheCreate(int capacity) {
    LRUCache* obj = (LRUCache*)calloc(1, sizeof(LRUCache));
    obj->capacity = capacity;
    // memset(obj->nodes, 0, MAX_NUM * sizeof(Node));
    obj->head = (Node*)calloc(1, sizeof(Node));
    obj->tail = (Node*)calloc(1, sizeof(Node));
    obj->head->next = obj->tail;
    obj->tail->prev = obj->head;
    return obj;
}

int lRUCacheGet(LRUCache* obj, int key) {
    if (obj->isKeyExist[key]) {
        Node* node = &(obj->nodes[key]);
        removeNode(node);
        addToHead(obj, node);
        return node->val;
    }
    return -1;
}

void lRUCachePut(LRUCache* obj, int key, int value) {
    Node* node = &(obj->nodes[key]);
    if (obj->isKeyExist[key]) {
        node->val = value;
        removeNode(node);
        addToHead(obj, node);
    }
    else {
        obj->isKeyExist[key] = true;
        node->key = key;
        node->val = value;
        addToHead(obj, node);
        obj->curCnt++;

        if (obj->curCnt > obj->capacity) {
            Node* tail = obj->tail->prev;
            obj->isKeyExist[tail->key] = false;
            removeNode(tail);
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
    node->next->prev = node->prev;
    node->prev->next = node->next;
}

void addToHead(LRUCache* obj, Node* node) {
    Node* nxt = obj->head->next;
    obj->head->next = node;
    node->prev = obj->head;
    node->next = nxt;
    nxt->prev = node;
}
/**
 * Your LRUCache struct will be instantiated and called as such:
 * LRUCache* obj = lRUCacheCreate(capacity);
 * int param_1 = lRUCacheGet(obj, key);

 * lRUCachePut(obj, key, value);

 * lRUCacheFree(obj);
*/

int main() {
    LRUCache* obj = lRUCacheCreate(2);
    lRUCachePut(obj, 1, 1);
    lRUCachePut(obj, 2, 2);
    printf("%d\n", lRUCacheGet(obj, 1)); // 1
    lRUCachePut(obj, 3, 3); // 把2挤出去了
    printf("%d\n", lRUCacheGet(obj, 2)); // -1
    lRUCachePut(obj, 4, 4);
    printf("%d\n", lRUCacheGet(obj, 1)); // -1
    printf("%d\n", lRUCacheGet(obj, 3)); // 3
    printf("%d\n", lRUCacheGet(obj, 4)); // 4
    lRUCacheFree(obj);
    return 0;
}