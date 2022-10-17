#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>
#include <stdbool.h>
#include "uthash.h"

typedef struct HashMap {
    int key; // fruit type
    int val; // cnt
    UT_hash_handle hh;
} HashMap;

bool map_putOrAdd(HashMap** map, int key, int val) {
    HashMap* pEntry = NULL;
    HASH_FIND_INT(*map, &key, pEntry);
    if (pEntry == NULL) {
        pEntry = (HashMap*)malloc(sizeof(HashMap));
        pEntry->key = key, pEntry->val = val;
        HASH_ADD_INT(*map, key, pEntry);
    }
    else {
        pEntry->val++;
    }
    return true;
}

bool map_MinusOrRm(HashMap** map, int key) {
    HashMap* pEntry = NULL;
    HASH_FIND_INT(*map, &key, pEntry);
    if (pEntry == NULL) return false;
    pEntry->val--;
    if (pEntry->val <= 0) {
        HASH_DEL(*map, pEntry);
        free(pEntry);
        pEntry = NULL;
    }
    return true;
}

void map_free(HashMap** map) {
    HashMap* cur = NULL, * tmp = NULL;
    HASH_ITER(hh, *map, cur, tmp) {
        HASH_DEL(*map, cur);
        free(cur);
        cur = NULL;
    }
}

int totalFruit(int* fruits, int n) {
    int left = 0, right = 0, total = 0;
    HashMap* map = NULL;
    while (right < n) {
        int cur = fruits[right++];
        map_putOrAdd(&map, cur, 1);
        while (HASH_COUNT(map) > 2) {
            int pop = fruits[left++];
            map_MinusOrRm(&map, pop);
        }
        total = fmax(total, right - left);
    }
    map_free(&map);
    return total;
}