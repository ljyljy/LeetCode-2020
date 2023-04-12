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

bool Map_Put(HashMap** map, char* word) {
    HashMap* pEntry = NULL;
    // HASH_FIND_STR(*map, word, pEntry);
    // if (pEntry != NULL) { // 已存在, 则直接cnt++
    //     pEntry->cnt = cnt; // 无需HASH_DEL！
    //     return true;
    // }
    pEntry = (HashMap*)malloc(sizeof(HashMap));
    strcpy(pEntry->word, word);
    pEntry->cnt = 1;
    HASH_ADD_STR(*map, word, pEntry);
    return true;
}

// void map_iter(HashMap** map) {
//     HashMap* cur, * tmp;
//     HASH_ITER(hh, *map, cur, tmp) {
//         printf("word: %s, cnt: %d\n", cur->word, cur->cnt);
//     }
// }

void Map_Free(HashMap** map) {
    HashMap* cur, * tmp;
    HASH_ITER(hh, *map, cur, tmp) {
        HASH_DEL(*map, cur);
        free(cur);
        cur = NULL;
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
            Map_Put(&need, word);
        }
        else {
            pEntry->cnt++; // 与java不同：map.put(key, map.getOrDefault(key, 0)+1);
        }
    }
    // map_iter(&need); // test

    // 2) 遍历s，更新WindowMap，对比NeedMap
    for (int i = 0; i <= n - cnt * len; i++) { // i<= n-cnt*len, 否则TLE!
        int lf = i, rt = lf + cnt * len;
        HashMap* window = NULL; // 每轮遍历都重新计算
        while (lf < rt) {
            // String curWord = s.substring(lf, lf + len);
            char* curWord = (char*)malloc(sizeof(char) * (len + 1));
            strncpy(curWord, s + lf, len), curWord[len] = '\0'; // v1
            // snprintf(curWord, len + 1, "%s", s + lf); // v2 - 超时！
            // printf("curWord: %s\n", curWord); // test

            HashMap* pEntry_need = NULL, * pEntry_window = NULL;
            HASH_FIND_STR(need, curWord, pEntry_need); // 向needMap查找str2Add
            HASH_FIND_STR(window, curWord, pEntry_window); // 向needMap查找str2Add
            if (pEntry_need == NULL ||
                (pEntry_window != NULL && pEntry_need->cnt == pEntry_window->cnt)) {
                break; // needMap中没有str2Add，或单词数量达标
            }
            if (pEntry_window == NULL) {
                Map_Put(&window, curWord);
            }
            else {
                pEntry_window->cnt++;
            }

            lf += len;
        }
        if (lf == rt) res[curIdx++] = i;
        Map_Free(&window);
    }
    Map_Free(&need);

    *returnSize = curIdx;
    return res;
}

int main() {
    char* s = "barfoothefoobarman";
    char* words[] = { "foo", "bar" };
    int wordsSize = 2;
    int returnSize = 0;
    int* res = findSubstring(s, words, wordsSize, &returnSize);
    for (int i = 0; i < returnSize; i++) {
        printf("%d ", res[i]);
    }
    printf("\nreturnSize: %d ", returnSize);
    return 0;
}