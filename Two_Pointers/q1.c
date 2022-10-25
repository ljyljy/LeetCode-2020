//
// Created by ljylj on 2022/8/20.
//

#include "uthash.h"

typedef struct HashMap {
    int key; // num
    int val; // idx
    UT_hash_handle hh;
} HashMap;

void map_putOrAdd(HashMap** map, int num, int idx) {
    HashMap* pEntry = NULL;
    HASH_FIND_INT(*map, &num, pEntry); // 此处是&num，而非key/num！
    if (pEntry == NULL) {
        pEntry = malloc(sizeof(HashMap));
        pEntry->key = num;
        pEntry->val = idx;
        HASH_ADD_INT(*map, key, pEntry); // 此处是key（map.field属性字段），而非num！
    }
    else {
        pEntry->val = idx;
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

int* twoSum(const int* nums, int n, int target, int* returnSize) {
    //    printf("n = %d, target = %d, returnSize = %d\n", n, target, *returnSize);
    HashMap* map = NULL;
    for (int i = 0; i < n; ++i) {
        HashMap* pEntry = NULL;
        int y = target - nums[i];
        HASH_FIND_INT(map, &y, pEntry); // 此处是&num，而非key/num！
        if (pEntry != NULL) {
            *returnSize = 2;
            int* ret = malloc(sizeof(int) * 2);
            ret[0] = pEntry->val, ret[1] = i;
            map_free(&map);
            return ret;
        }
        map_putOrAdd(&map, nums[i], i);
    }
    map_free(&map);
    *returnSize = 0;
    return NULL;
}



int main() {
    int nums[] = { 2, 7, 11, 15 };
    int target = 9;
    int n = sizeof(nums) / sizeof(int);
    int returnSize = 0;
    int* ans = twoSum(nums, n, target, &returnSize); // 【不加 &returnSize】
    printf("returnSize = %d\n", returnSize);
    for (int i = 0; i < returnSize; ++i) {
        printf("%d ", ans[i]);
    }
    return 0;
}