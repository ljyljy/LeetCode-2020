//
// Created by ljylj on 2022/8/24.
//


#include "utlib/uthash.h"
#include <stdbool.h>
struct hashTable {
    int key; // num
    int val; // cnt
    UT_hash_handle hh;
} *map;

struct hashTable* find (int num) {
    struct hashTable* entry;
    HASH_FIND_INT(map, &num, entry);
    return entry;
}

void add(int num) {
    struct hashTable* entry = find(num);
    if (entry) {
        entry->val++;
    } else{
        entry = malloc(sizeof(struct hashTable));
        entry->key = num, entry->val = 1;
        HASH_ADD_INT(map, key, entry);
    }
}

void minus (int num) {
    struct hashTable* entry = find(num);
    if (entry) {
        entry->val--;
    } else{
        entry = malloc(sizeof(struct hashTable));
        entry->key = num, entry->val = -1;
        HASH_ADD_INT(map, key, entry);
    }
}

void freeMap() {
    struct hashTable *cur = NULL, *tmp = NULL;
    HASH_ITER(hh, map, cur, tmp) {
        HASH_DEL(map, cur);
        free(cur);
        cur = NULL;
    }
}

bool canBeEqual(int* target, int targetSize, int* arr, int arrSize) {
    map = NULL;
    if (targetSize != arrSize) {
        return false;
    }
    for (int i = 0; i < targetSize; ++i) {
        add(target[i]);
        minus(arr[i]);
    }

    struct hashTable* entry = map;
    while (entry) {
        if (entry->val != 0) {
            return false;
        }
        entry = entry->hh.next;
    }
    return true;
}
