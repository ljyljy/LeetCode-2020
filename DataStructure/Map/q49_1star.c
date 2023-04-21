#include <stdlib.h>
#include <stdio.h>
// #include <stdbool.h>
// #include <math.h>
#include <string.h>
// #include <limits.h>
// #include <ctype.h>
#include "uthash.h"

// q49. Group Anagrams -- UT_HASH

// 法0：嵌套Map，外层Map存储<升序str, 内层Map>，内层Map存储<异位词, 异位词列表>
// typedef struct List_s {
//     char* sortedStr; // 题目：strs[i].length∈[0, 100]
//     char* lists[10001];// 题目：strs.length∈[1, 10^4]，注意每个char*需calloc(100)
//     int idx;
// } List; // subMap <升序str, 异位词数组 & 个数>

// typedef struct Map_s {
// 	char* sortedStr; // key
// 	List list;
// 	UT_hash_handle hh;
// } Map;//外层哈希结构体；


// 法1：单层Map，中间结果（异位词）直接存储于char*** res【利用一级指针 i & 二级指针j】
typedef struct StrIdx_s {
    char* sortedStr; // key; 题目：strs[i].length∈[0, 100]
    int idxInRes; // val; 无需创建 对应的异位词列表，将中间结果根据res下标i(idxInRes) & j，直接保存到res中
    UT_hash_handle hh;
} StrIdxMap; // subMap <key=升序str, val=key在res中的行数idxInRes>

char*** groupAnagrams(char** strs, int n, int* returnSize, int** returnColumnSizes) {
    char*** res = (char***)calloc(n, sizeof(char**));
    int curCnt = 0;
    int* n_cols = (int*)calloc(n, sizeof(int));

    StrIdxMap* map = NULL;
    for (int i = 0; i < n; i++) { // 遍历字符串列表
        char* curStr = strs[i];
        int curLen = strlen(curStr);
        char* sortedStr = (char*)calloc(curLen + 1, sizeof(char));
        strcpy(sortedStr, curStr);
        qsort(sortedStr, curLen, sizeof(char), strcmp); // 升序排列，作为StrIdx.key

        StrIdxMap* pEntry = NULL;
        HASH_FIND_STR(map, sortedStr, pEntry); // 查找
        // HASH_FIND(hh, map, sortedStr, curLen, pEntry); // 查找v2
        if (pEntry == NULL) {
            // 1-1) 加入map
            pEntry = (StrIdxMap*)calloc(1, sizeof(StrIdxMap));
            pEntry->sortedStr = sortedStr; // 此处无需再calloc & strcmp, 因为sortedStr不会改动
            // HASH_ADD_STR(map, sortedStr, pEntry); // 添加
            HASH_ADD_KEYPTR(hh, map, sortedStr, curLen, pEntry); // 添加v2
            // HASH_ADD(hh, map, pEntry->sortedStr, strlen(pEntry->sortedStr), pEntry); // 添加v3 (不可用，因为sortedStr会改动)

            // 2) 直接更新res
            res[curCnt] = (char**)calloc(1000, sizeof(char*)); // sortedStr对应的异位词列表
            n_cols[curCnt] = 0; // sortedStr对应的异位词个数 / res[i].列数
            res[curCnt][n_cols[curCnt]++] = curStr;
            pEntry->idxInRes = curCnt++;
        }
        else { // 直接更新res
            // 在map里，找到对应的行数pEntry->idxInRes，对应列数为n_cols[idxInRes]，向后追加curStr
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