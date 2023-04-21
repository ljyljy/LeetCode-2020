#include <stdlib.h>
#include <stdio.h>
// #include <stdbool.h>
// #include <math.h>
#include <string.h>
// #include <limits.h>
// #include <ctype.h>
#include "uthash.h"

// q49. Group Anagrams -- UT_HASH

// ��0��Ƕ��Map�����Map�洢<����str, �ڲ�Map>���ڲ�Map�洢<��λ��, ��λ���б�>
// typedef struct List_s {
//     char* sortedStr; // ��Ŀ��strs[i].length��[0, 100]
//     char* lists[10001];// ��Ŀ��strs.length��[1, 10^4]��ע��ÿ��char*��calloc(100)
//     int idx;
// } List; // subMap <����str, ��λ������ & ����>

// typedef struct Map_s {
// 	char* sortedStr; // key
// 	List list;
// 	UT_hash_handle hh;
// } Map;//����ϣ�ṹ�壻


// ��1������Map���м�������λ�ʣ�ֱ�Ӵ洢��char*** res������һ��ָ�� i & ����ָ��j��
typedef struct StrIdx_s {
    char* sortedStr; // key; ��Ŀ��strs[i].length��[0, 100]
    int idxInRes; // val; ���贴�� ��Ӧ����λ���б����м�������res�±�i(idxInRes) & j��ֱ�ӱ��浽res��
    UT_hash_handle hh;
} StrIdxMap; // subMap <key=����str, val=key��res�е�����idxInRes>

char*** groupAnagrams(char** strs, int n, int* returnSize, int** returnColumnSizes) {
    char*** res = (char***)calloc(n, sizeof(char**));
    int curCnt = 0;
    int* n_cols = (int*)calloc(n, sizeof(int));

    StrIdxMap* map = NULL;
    for (int i = 0; i < n; i++) { // �����ַ����б�
        char* curStr = strs[i];
        int curLen = strlen(curStr);
        char* sortedStr = (char*)calloc(curLen + 1, sizeof(char));
        strcpy(sortedStr, curStr);
        qsort(sortedStr, curLen, sizeof(char), strcmp); // �������У���ΪStrIdx.key

        StrIdxMap* pEntry = NULL;
        HASH_FIND_STR(map, sortedStr, pEntry); // ����
        // HASH_FIND(hh, map, sortedStr, curLen, pEntry); // ����v2
        if (pEntry == NULL) {
            // 1-1) ����map
            pEntry = (StrIdxMap*)calloc(1, sizeof(StrIdxMap));
            pEntry->sortedStr = sortedStr; // �˴�������calloc & strcmp, ��ΪsortedStr����Ķ�
            // HASH_ADD_STR(map, sortedStr, pEntry); // ���
            HASH_ADD_KEYPTR(hh, map, sortedStr, curLen, pEntry); // ���v2
            // HASH_ADD(hh, map, pEntry->sortedStr, strlen(pEntry->sortedStr), pEntry); // ���v3 (�����ã���ΪsortedStr��Ķ�)

            // 2) ֱ�Ӹ���res
            res[curCnt] = (char**)calloc(1000, sizeof(char*)); // sortedStr��Ӧ����λ���б�
            n_cols[curCnt] = 0; // sortedStr��Ӧ����λ�ʸ��� / res[i].����
            res[curCnt][n_cols[curCnt]++] = curStr;
            pEntry->idxInRes = curCnt++;
        }
        else { // ֱ�Ӹ���res
            // ��map��ҵ���Ӧ������pEntry->idxInRes����Ӧ����Ϊn_cols[idxInRes]�����׷��curStr
            res[pEntry->idxInRes][n_cols[pEntry->idxInRes]++] = curStr;
        }
    }


    *returnSize = curCnt;
    *returnColumnSizes = n_cols;
    return res;
}

int main() {
    char* strs[] = { "eat", "tea", "tan", "ate", "nat", "bat" };
    int n = sizeof(strs) / sizeof(strs[0]);
    int returnSize = 0;
    int* returnColumnSizes = NULL;
    char*** res = groupAnagrams(strs, n, &returnSize, &returnColumnSizes);
    for (int i = 0; i < returnSize; i++) {
        for (int j = 0; j < returnColumnSizes[i]; j++) {
            printf("%s ", res[i][j]);
        }
        printf("\n");
    }
    return 0;
}