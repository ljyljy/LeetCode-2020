#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
// #include <string.h>
// #include <math.h>
// #include <limits.h>

/* ������
1) LRUCache��Ҫ��capacity����������Լ���ǰ����curCnt
2) getҪ������ҵ�key���������˴�ʹ����������ϣ
  - ȫ�֣�Nodes nodes[N]���±�Ϊkeyֵ��������LRUCache��
    - ��Map<key, Node{isExist��val��*prev��*next}>������
  - ȫ��/Node�ڲ���bool isKeyExist[10001]���±�Ϊkeyֵ��������true
3) putҪ�����ӵ��<k,v>�ԣ��ɸ��£���������qsort�� �� ����ͷ�巨�̣� �����ڲ��롢ɾ������ö����ʹ�á�˫��������
�ܽ᣺
  - LRUCache�洢����Ҫ����Ӧ��һ������Lists����Ϊ˫����Ҫȫ����Ұ�±��ֶ���head��tail�ļ��ӣ�
    - ����������ListNode��k-v�ԡ�ǰ&���
4���״��漰��������ṹ���ṹ���Աֵ�Ȳ���������ȡָ�룡
  - ֱ�ӵġ���ֵ��䣨ǳ��������+ȡ��ַ�����������Ѿ�������ԭ���ˣ�
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
    bool isKeyExist[MAX_NUM]; // ��2��ȫ����Ұ�£�����key�������
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
    lRUCachePut(obj, 3, 3); // ��2����ȥ��
    printf("%d\n", lRUCacheGet(obj, 2)); // -1
    lRUCachePut(obj, 4, 4);
    printf("%d\n", lRUCacheGet(obj, 1)); // -1
    printf("%d\n", lRUCacheGet(obj, 3)); // 3
    printf("%d\n", lRUCacheGet(obj, 4)); // 4
    lRUCacheFree(obj);
    return 0;
}