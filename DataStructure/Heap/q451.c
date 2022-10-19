#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>
#include "uthash.h"

typedef struct HashMap {
    char key; // char
    int val; // cnt
    UT_hash_handle hh;
} HashMap;

void map_putOrAdd(HashMap** map, char* key, int val) {
    HashMap* pEntry = NULL;
    HASH_FIND(hh, *map, key, sizeof(char), pEntry);
    if (pEntry == NULL) {
        pEntry = (HashMap*)malloc(sizeof(HashMap));
        pEntry->key = *key, pEntry->val = val;
        HASH_ADD(hh, *map, key, sizeof(char), pEntry);
    }
    else {
        pEntry->val++;
    }
}

void map_free(HashMap** map) {
    HashMap* cur, * tmp;
    HASH_ITER(hh, *map, cur, tmp) {
        HASH_DEL(*map, cur);
        free(cur);
        cur = NULL;
    }
}

static inline int cmp(HashMap* a, HashMap* b) {
    return b->val - a->val; // cnt½µÐò
}

char* frequencySort(char* s) {
    int n = strlen(s);
    HashMap* map = NULL;
    for (int i = 0; i < n; i++) {
        map_putOrAdd(&map, &s[i], 1);
    }
    int cnt = HASH_COUNT(map);
    HASH_SORT(map, cmp);

    char* str = (char*)malloc(sizeof(char) * (n + 1));
    int idx = 0;
    HashMap* cur, * tmp;
    HASH_ITER(hh, map, cur, tmp) {
        for (int i = 0; i < cur->val; i++) {
            str[idx++] = cur->key;
        }
    }
    str[idx] = '\0';
    map_free(&map);
    return str;
}