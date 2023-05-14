#include <stdio.h>
#include <stdlib.h>
#include <string.h>
// #include <limits.h>
#include <stdbool.h>
#include "uthash.h"

// q244. 最短单词距离 II

/* 知识点
- 哈希表 -- <str, idxes列表>
  - 给定`char**`字符串数组, 记录对应的<key=str, val=idxes>, 用`HASH_ADD_STR`增加元素
- 链表
  - 用于记录idxes, 头插法。故idxes降序
- 查找最短单词距离
  - 从map中获取对应单词的idxes列表, 从头开始遍历, 记录最小距离
  - 由于idxes链表降序，故采用'逼近法'：idx1 > idx2时，idx1往后移动(try smaller idx1)
*/
// 2)

typedef struct ListNode_s {
    int idx;
    struct ListNode_s* next;
} ListNode;

typedef struct StrIdxMap_s {
    char* word;
    ListNode* idxList; // v1: 链表, 头插法（idx降序）
    // int* idxes; // v2-1: 数组
    // int idxCnt; // v2-2: 数组
    UT_hash_handle hh;
} StrIdxMap;

typedef struct {
    // char** words;
    StrIdxMap* map;
} WordDistance;


WordDistance* wordDistanceCreate(char** wordsDict, int wordsDictSize) {
    WordDistance* obj = (WordDistance*)calloc(1, sizeof(WordDistance));
    obj->map = NULL; // 后面使用HASH_ADD_STR增加元素。

    for (int i = 0; i < wordsDictSize; i++) {
        ListNode* node = (ListNode*)calloc(1, sizeof(ListNode));
        node->idx = i;
        node->next = NULL;

        StrIdxMap* pEntry = NULL;
        HASH_FIND_STR(obj->map, wordsDict[i], pEntry);
        if (pEntry == NULL) {
            pEntry = (StrIdxMap*)malloc(sizeof(StrIdxMap));
            pEntry->word = wordsDict[i];
            pEntry->idxList = node;
            HASH_ADD_STR(obj->map, word, pEntry);
        }
        else { // 头插法
            node->next = pEntry->idxList;
            pEntry->idxList = node;
        }
    }
    return obj;
}

int wordDistanceShortest(WordDistance* obj, char* word1, char* word2) {
    StrIdxMap* pEntry1 = NULL, * pEntry2 = NULL;
    HASH_FIND_STR(obj->map, word1, pEntry1);
    HASH_FIND_STR(obj->map, word2, pEntry2);
    if (pEntry1 == NULL || pEntry2 == NULL) {
        return -1;
    }
    ListNode* idx1 = pEntry1->idxList, * idx2 = pEntry2->idxList;
    int minDist = INT_MAX;
    while (idx1 && idx2) { // idxList 降序排列(idx >= 0 且 唯一)，最短距离必然在idx逼近元素中
        int dist = abs(idx1->idx - idx2->idx);
        if (dist < minDist) {
            minDist = dist;
        }
        if (idx1->idx < idx2->idx) { // idx2 可继续减小（next）
            idx2 = idx2->next;
        }
        else {
            idx1 = idx1->next;

        }
    }
    return minDist;
}

void wordDistanceFree(WordDistance* obj) {
    StrIdxMap* cur = NULL, * tmp = NULL;
    HASH_ITER(hh, obj->map, cur, tmp) {
        HASH_DEL(obj->map, cur);
        free(cur);
    }
    free(obj);
}


int main()
{
    char* wordsDict[] = { "practice", "makes", "perfect", "coding", "makes" };
    int wordsDictSize = sizeof(wordsDict) / sizeof(wordsDict[0]);
    WordDistance* obj = wordDistanceCreate(wordsDict, wordsDictSize);
    int res = wordDistanceShortest(obj, "coding", "practice");
    printf("%d\n", res);  // 3
    res = wordDistanceShortest(obj, "makes", "coding");
    printf("%d\n", res);  // 1
    wordDistanceFree(obj);
    return 0;
}