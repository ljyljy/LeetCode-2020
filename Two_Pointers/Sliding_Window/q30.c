#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>
#include "uthash.h"

typedef struct HashMap {
    char word[32]; // key, 题目：strlen(str)∈[1, 30]
    int cnt; // val
    UT_hash_handle hh;
} HashMap;

// map_find功能单一，建议直接写在函数内，或定义为宏！

bool map_put(HashMap** map, char* word, int cnt) {
    HashMap* pEntry = NULL;
    // HASH_FIND_STR(*map, word, pEntry);
    // if (pEntry != NULL) { // 已存在, 则直接cnt++
    //     pEntry->cnt = cnt; // 无需HASH_DEL！
    //     return true;
    // }
    pEntry = (HashMap*)malloc(sizeof(HashMap));
    strcpy(pEntry->word, word);
    pEntry->cnt = cnt;
    HASH_ADD_STR(*map, word, pEntry);
    return true;
}

void FREE(void* p) {
    free(p);
    p = NULL;
}

// void map_iter(HashMap** map) {
//     HashMap* cur, * tmp;
//     HASH_ITER(hh, *map, cur, tmp) {
//         printf("word: %s, cnt: %d\n", cur->word, cur->cnt);
//     }
// }

void map_free(HashMap** map) {
    HashMap* cur, * tmp;
    HASH_ITER(hh, *map, cur, tmp) {
        HASH_DEL(*map, cur);
        FREE((HashMap*)cur);
    }
}

int* findSubstring(char* s, char** words, int wordsSize, int* returnSize) {
    HashMap* need = NULL; // LC判题，必须重新初始化；(HashMap*)malloc(sizeof(HashMap));
    int n = strlen(s), len = strlen(words[0]), cnt = wordsSize;
    int* res = (int*)malloc(sizeof(int) * n); // 最大长度为n，最少可能为空
    int curIdx = 0;
    // 1) 给定单词列表，加入NeedMap
    for (int i = 0; i < cnt; i++) {
        char* word = words[i];
        // map.put -> map.contains(word), 则curCnt = map.get(word)+1
        HashMap* pEntry = NULL;
        HASH_FIND_STR(need, word, pEntry);
        if (pEntry == NULL) {
            map_put(&need, word, 1);
        }
        else {
            pEntry->cnt++; // 与java不同：map.put(key, map.getOrDefault(key, 0)+1);
        }
    }
    // map_iter(&need); // test

    // 2) 遍历s，更新WindowMap，对比NeedMap
    for (int i = 0; i <= n - cnt * len; i++) {
        int lf = i, rt = lf + cnt * len;
        HashMap* window = NULL; // 每轮遍历都重新计算
        while (lf < rt) {
            // String str2Add = s.substring(lf, lf + len);
            char* str2Add = (char*)malloc(sizeof(char) * (len + 1));
            strncpy(str2Add, s + lf, sizeof(char) * len), str2Add[len] = '\0'; // v1
            // snprintf(str2Add, len + 1, "%s", s + lf); // v2 - 超时！
            // printf("str2Add: %s\n", str2Add); // test

            HashMap* pEntry_need = NULL, * pEntry_window;
            HASH_FIND_STR(need, str2Add, pEntry_need); // 向needMap查找str2Add
            HASH_FIND_STR(window, str2Add, pEntry_window); // 向needMap查找str2Add
            if (pEntry_need == NULL ||
                (pEntry_window != NULL && pEntry_need->cnt == pEntry_window->cnt)) {
                break; // needMap中没有str2Add，或单词数量达标
            }
            if (pEntry_window == NULL) {
                map_put(&window, str2Add, 1);
            }
            else {
                pEntry_window->cnt++;
            }

            lf += len;
        }
        if (lf == rt) res[curIdx++] = i;
        map_free(&window);
    }
    map_free(&need);

    *returnSize = curIdx;
    return res;
}