/*
 �ѵ㣺
 1) Queue<String> ���� -> ��������head, tail: struct NodeQ {String(char[]), idx, *nxt}
 2) Set<String> ȥ�� -> Map<key, key> ��֤Ψһ, keyΪString(char[])
    �ӣ�Ϊ�˱���HashSet-TLE, ��Ҫʹ�ö���ָ�룡���ɶ���ȫ�ֱ���set��
*/

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>
#include <stdbool.h>
#include "uthash.h"


#ifndef DIM
#define DIM(arr) sizeof(arr)/sizeof(*arr)
#endif

#define MAX_LEN 21


// Queue<Node{String, idx}>
typedef struct NodeQ {
    char str[MAX_LEN]; // �м�����str
    int idx; // ���ֱ����±�idx��pattern=s2[idx]
    // �� ���ȣ�str[0:idx) = s2[0:idx), ��s1->strת����step��
    struct NodeQ* next;
} NodeQ;

/* HashSet_ops -- start */

// Set��Mapʵ�֣�key=val�����ɱ�֤Ψһ��
typedef struct HashSet {
    char str[MAX_LEN]; // �м�����curStr
    UT_hash_handle hh;
} HashSet;

HashSet* set = NULL; // ��Ϊȫ�ֱ�����LC��TLE����, ������ں����д������ָ��HashSet**

// ���淶�������鷵��ָ�����ͣ��ᵼ��Ұָ�룩, ���Խ�HashSet*��װΪ��Σ����أ�
// HashSet* setFind_deprecated(const char* key) {
//     HashSet* pEntry = NULL;
//     HASH_FIND_STR(set, key, pEntry);
//     return pEntry;
// }
// ���޸����� �� ��һ������װ��ʹ�ú�
// #define SET_FIND(key, pEntry) \
//     do { \
//         HASH_FIND_STR(set, key, pEntry); \
//     } while (0); \

bool set_find(HashSet** set, const char* key, HashSet* pEntry) {
    HASH_FIND_STR(*set, key, pEntry);
    return pEntry != NULL;
}

bool set_add(HashSet** set, const char* key) {
    HashSet* pEntry = (HashSet*)malloc(sizeof(HashSet));
    if (set_find(set, key, pEntry) != NULL) return false;
    strcpy(pEntry->str, key);
    HASH_ADD_STR(*set, str, pEntry);
    return true;
}

void set_free(HashSet** set) {
    HashSet* cur = NULL, * tmp = NULL;
    HASH_ITER(hh, *set, cur, tmp) {
        HASH_DEL(*set, cur);
        free(cur);
    }
}
/* HashSet_ops -- end */

// static inline void swap1(char** s, int i, int j) {
//     char tmp = (*s)[i];
//     (*s)[i] = (*s)[j];
//     (*s)[j] = tmp;
// }

static inline void swap2(char* s, int i, int j) {
    char tmp = s[i];
    s[i] = s[j];
    s[j] = tmp;
}

static inline void swap3(char* pa, char* pb) {
    char c = *pa;
    *pa = *pb;
    *pb = c;
}

void FREE(void* p) {
    free(p);
    p = NULL;
}

int kSimilarity(char* s1, char* s2) {
    int n = strlen(s1);
    NodeQ* head = NULL, * tail = NULL; // head=tail=node{s1, 0},,, queue.size=1
    ////  ��1
    head = tail = (NodeQ*)malloc(sizeof(NodeQ));
    tail->idx = 0;
    strcpy(tail->str, s1); // queue.add(node{s1, 0});
    tail->next = NULL;
    //// ��2��WA��
    // NodeQ node = { .idx = 0, .next = NULL };
    // strcpy(node.str, s1);
    // head = tail = &node; // queue.size=1,TODO: head��tail������malloc�

    HashSet* set = NULL; // LC���⣺��ڱ������³�ʼ����
    set_add(&set, s1); // visitedSet.set_add(s1);

    int step = 0, sizeQ = 1; // nodeQ��size
    while (sizeQ > 0) {
        int size = sizeQ;
        char curStr[MAX_LEN];
        for (int i = 0; i < size; i++) {
            char* curStr = head->str;  /* queue.peekFirst() - 1 */
            int curIdx = head->idx;

            if (!strcmp(curStr, s2)) { // strcmp: ��ȡ�����0, ���String.compare()�����൱��str.equals()=true
                return step;
            }
            // Ų��curStr��s2��һ������ȵ��±�
            while (curIdx < n && curStr[curIdx] == s2[curIdx]) {
                curIdx++;
            }
            char pattern = s2[curIdx];
            for (int j = curIdx + 1; j < n; j++) {
                // ��֦��������ͬ=û��Ҫ��������ͽ��step��������
                if (curStr[j] == s2[j]) continue;
                if (curStr[j] == pattern) {
                    swap2(curStr, curIdx, j);// �˺�curStrʵ��ΪnxtStr
                    // swap3(&curStr[curIdx], &curStr[j]);

                    // char* nxtStr = curStr; // ����WA�������ɷ���Hash�����
                    HashSet* pEntry = NULL;
                    if (!set_find(&set, curStr, pEntry)) { // pEntry=NULL
                        set_add(&set, curStr);
                        // queue.offer(node{*nxtStr, curIdx+1})
                        //// ��1
                        tail->next = (NodeQ*)malloc(sizeof(NodeQ));
                        tail = tail->next;
                        strcpy(tail->str, curStr);
                        tail->idx = curIdx + 1;
                        tail->next = NULL;
                        sizeQ++;
                        //// ��2 ��WA��
                        // NodeQ nxtNode = { .idx = curIdx + 1, .next = NULL };
                        // strcpy(nxtNode.str, curStr);
                        // tail->next = &nxtNode;
                        // tail = tail->next;
                        // sizeQ++;
                    }
                    swap2(curStr, curIdx, j); // �ָ�
                    // swap3(&curStr[curIdx], &curStr[j]);
                }
            }
            /* queue.pollFirst() - 2 */
            NodeQ* p = head;
            head = head->next; // ������λ
            FREE(p);
            sizeQ--;
        }
        step++;
    }
    set_free(&set);
    return step;
}

int main()
{
    // char* s1 = "abcdefabcdefabcdef", * s2 = "edcfbebceafcfdabad";
    char* s1 = "aaaabbbbccccddddeeee", * s2 = "acbecdaacdbedeabcedb";
    int ans = kSimilarity(s1, s2);
    printf("ans = %d\n", ans);
}