/*
 难点：
 1) Queue<String> 队列 -> 单向链表head, tail: struct NodeQ {String(char[]), idx, *nxt}
 2) Set<String> 去重 -> Map<key, key> 保证唯一, key为String(char[])
    坑：为了避免HashSet-TLE, 需要使用二级指针！不可定义全局变量set！
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
    char str[MAX_LEN]; // 中间结果串str
    int idx; // 本轮遍历下标idx：pattern=s2[idx]
    // ↑ 进度：str[0:idx) = s2[0:idx), 且s1->str转换了step次
    struct NodeQ* next;
} NodeQ;

/* HashSet_ops -- start */

// Set由Map实现，key=val，即可保证唯一性
typedef struct HashSet {
    char str[MAX_LEN]; // 中间结果串curStr
    UT_hash_handle hh;
} HashSet;

HashSet* set = NULL; // 设为全局变量（LC会TLE！）, 解决：在函数中传入二级指针HashSet**

// 不规范！不建议返回指针类型（会导致野指针）, 可以将HashSet*封装为入参（返回）
// HashSet* setFind_deprecated(const char* key) {
//     HashSet* pEntry = NULL;
//     HASH_FIND_STR(set, key, pEntry);
//     return pEntry;
// }
// 可修改如下 ↓ 单一函数封装，使用宏
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
    ////  法1
    head = tail = (NodeQ*)malloc(sizeof(NodeQ));
    tail->idx = 0;
    strcpy(tail->str, s1); // queue.add(node{s1, 0});
    tail->next = NULL;
    //// 法2【WA】
    // NodeQ node = { .idx = 0, .next = NULL };
    // strcpy(node.str, s1);
    // head = tail = &node; // queue.size=1,TODO: head、tail无需先malloc嘛？

    HashSet* set = NULL; // LC判题：入口必须重新初始化！
    set_add(&set, s1); // visitedSet.set_add(s1);

    int step = 0, sizeQ = 1; // nodeQ的size
    while (sizeQ > 0) {
        int size = sizeQ;
        char curStr[MAX_LEN];
        for (int i = 0; i < size; i++) {
            char* curStr = head->str;  /* queue.peekFirst() - 1 */
            int curIdx = head->idx;

            if (!strcmp(curStr, s2)) { // strcmp: 相等【返回0, 类比String.compare()】，相当于str.equals()=true
                return step;
            }
            // 挪到curStr与s2第一个不相等的下标
            while (curIdx < n && curStr[curIdx] == s2[curIdx]) {
                curIdx++;
            }
            char pattern = s2[curIdx];
            for (int j = curIdx + 1; j < n; j++) {
                // 剪枝：二者相同=没必要不交换（徒增step），跳过
                if (curStr[j] == s2[j]) continue;
                if (curStr[j] == pattern) {
                    swap2(curStr, curIdx, j);// 此后，curStr实则为nxtStr
                    // swap3(&curStr[curIdx], &curStr[j]);

                    // char* nxtStr = curStr; // 【【WA】】不可放入Hash句柄！
                    HashSet* pEntry = NULL;
                    if (!set_find(&set, curStr, pEntry)) { // pEntry=NULL
                        set_add(&set, curStr);
                        // queue.offer(node{*nxtStr, curIdx+1})
                        //// 法1
                        tail->next = (NodeQ*)malloc(sizeof(NodeQ));
                        tail = tail->next;
                        strcpy(tail->str, curStr);
                        tail->idx = curIdx + 1;
                        tail->next = NULL;
                        sizeQ++;
                        //// 法2 【WA】
                        // NodeQ nxtNode = { .idx = curIdx + 1, .next = NULL };
                        // strcpy(nxtNode.str, curStr);
                        // tail->next = &nxtNode;
                        // tail = tail->next;
                        // sizeQ++;
                    }
                    swap2(curStr, curIdx, j); // 恢复
                    // swap3(&curStr[curIdx], &curStr[j]);
                }
            }
            /* queue.pollFirst() - 2 */
            NodeQ* p = head;
            head = head->next; // 后者上位
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