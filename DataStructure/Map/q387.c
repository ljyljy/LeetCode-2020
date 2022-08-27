//
// Created by ljylj on 2022/8/24.
//

#include "utlib/uthash.h"
#include <stdbool.h>

typedef struct hashTable {
    int key; // char
    int val; // cnt
    UT_hash_handle hh;
} hashTable;

hashTable *map = NULL;

hashTable *find(int ch) {
    hashTable *entry = NULL;
    HASH_FIND_INT(map, &ch, entry);
    return entry;
}

void add(int ch) {
    hashTable *entry = find(ch);
    if (entry) {
        entry->val++;
    } else {
        entry = malloc(sizeof(hashTable));
        entry->key = ch, entry->val = 1;
        HASH_ADD_INT(map, key, entry);
    }
}

void freeMap() {
    hashTable *cur, *tmp;
    HASH_ITER(hh, map, cur, tmp) {
        HASH_DEL(map, cur);
        free(cur);
        cur = NULL;
    }
}

int firstUniqChar(char *s) {
    map = NULL;
    int n = strlen(s);
    for (int i = 0; i < n; i++) {
        add(s[i]);
    }

    for (int i = 0; i < n; ++i) {
        hashTable *entry = find(s[i]);
        if (entry && entry->val == 1) {
            freeMap();
            return i;
        }
    }
    freeMap();
    return -1;
}
