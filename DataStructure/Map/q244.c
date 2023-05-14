#include <stdio.h>
#include <stdlib.h>
#include <string.h>
// #include <limits.h>
#include <stdbool.h>
#include "uthash.h"

// q244. ��̵��ʾ��� II

/* ֪ʶ��
- ��ϣ�� -- <str, idxes�б�>
  - ����`char**`�ַ�������, ��¼��Ӧ��<key=str, val=idxes>, ��`HASH_ADD_STR`����Ԫ��
- ����
  - ���ڼ�¼idxes, ͷ�巨����idxes����
- ������̵��ʾ���
  - ��map�л�ȡ��Ӧ���ʵ�idxes�б�, ��ͷ��ʼ����, ��¼��С����
  - ����idxes�����򣬹ʲ���'�ƽ���'��idx1 > idx2ʱ��idx1�����ƶ�(try smaller idx1)
*/
// 2)

typedef struct ListNode_s {
    int idx;
    struct ListNode_s* next;
} ListNode;

typedef struct StrIdxMap_s {
    char* word;
    ListNode* idxList; // v1: ����, ͷ�巨��idx����
    // int* idxes; // v2-1: ����
    // int idxCnt; // v2-2: ����
    UT_hash_handle hh;
} StrIdxMap;

typedef struct {
    // char** words;
    StrIdxMap* map;
} WordDistance;


WordDistance* wordDistanceCreate(char** wordsDict, int wordsDictSize) {
    WordDistance* obj = (WordDistance*)calloc(1, sizeof(WordDistance));
    obj->map = NULL; // ����ʹ��HASH_ADD_STR����Ԫ�ء�

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
        else { // ͷ�巨
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
    while (idx1 && idx2) { // idxList ��������(idx >= 0 �� Ψһ)����̾����Ȼ��idx�ƽ�Ԫ����
        int dist = abs(idx1->idx - idx2->idx);
        if (dist < minDist) {
            minDist = dist;
        }
        if (idx1->idx < idx2->idx) { // idx2 �ɼ�����С��next��
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