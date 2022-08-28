/**
 * Note: The returned array must be malloced, assume caller calls free().
 */

// #include "utlib/uthash.h"

struct hashTable {
    int key; // num
    int val; // idx
    UT_hash_handle hh;
} *map;

struct hashTable *find(int num) {
    struct hashTable *entry;
    HASH_FIND_INT(map, &num, entry); // 此处是&num，而非key/num！
    return entry;
}

void insert(int num, int idx) {
    struct hashTable *entry = find(num);
    if (entry == NULL) {
        struct hashTable *tmp = malloc(sizeof(struct hashTable));
        tmp->key = num;
        tmp->val = idx;
        HASH_ADD_INT(map, key, tmp); // 此处是key（map.field属性字段），而非num！
    } else {
        entry->val = idx;
    }
}

int *twoSum(int *nums, int n, int target, int *returnSize) {
    map = NULL;
    for (int i = 0; i < n; ++i) {
        int x = nums[i], y = target - x;
        struct hashTable *entry = find(y);
        if (entry != NULL) {
            *returnSize = 2;
            int *ret = malloc(sizeof(int) * 2);
            ret[0] = entry->val, ret[1] = i;
            return ret;
        }
        insert(x, i);
    }
    *returnSize = 0;
    return NULL;
}