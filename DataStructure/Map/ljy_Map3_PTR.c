#include <stdlib.h>
#include <stdio.h>
// #include <stdbool.h>
// #include <math.h>
#include <string.h>
// #include <limits.h>
// #include <ctype.h>
#include "uthash.h"

typedef struct Point_s {
    int loc[2];
    int val;
} Point;


typedef struct HashMap {
    void* key;
    int val;
    UT_hash_handle hh;         /* makes this structure hashable */
} HashMap;

// 原始key分别为char*、int、结构体(指针)时，分别可使用HASH_ADD_STR、HASH_ADD_INT、HASH_ADD_PTR
int main() {
    const char* names[] = { "joe", "bob", "betty", "ljy", "zzj", NULL };
    const int nums[] = { 1, 2, 3, 4, 5, 0 };
    HashMap* userMap = NULL;

    // 1-1) 添加 - HASH_ADD_STR(map, keyField / 字段名, pEntry);
    for (int i = 0; names[i]; ++i) {
        HashMap* pEntry = (HashMap*)calloc(1, sizeof(HashMap));
        pEntry->val = i;
        pEntry->key = names[i]; // 可强转void*
        // HASH_ADD_STR(userMap, key, pEntry); // v1, 配合HASH_FIND_STR可行, 警告void*
        // HASH_ADD_KEYPTR(hh, userMap, pEntry->key, strlen(pEntry->key), pEntry); // v2, 配合HASH_FIND_STR可行
        HASH_ADD_PTR(userMap, key, pEntry); // v3, 必须配合HASH_FIND_PTR
    }

    // 2-1) 查找 - HASH_FIND_STR(map, findstr, pEntry)
    HashMap* pEntry2 = NULL;
    // HASH_FIND_STR(userMap, "ljy", pEntry2); // v1 & v2可行, 配合HASH_ADD_STR；v3不可行
    HASH_FIND_PTR(userMap, &names[3], pEntry2); // v3, 必须配合HASH_ADD_PTR, 参数2必须是findptr(ptr的地址)
    if (pEntry2) {
        printf("%s's id is %d\n", pEntry2->key, pEntry2->val);
    }
    else {
        printf("key not found\n");
    }

    // 1-2)  添加 int key - HASH_ADD_INT(map, keyField / 字段名, pEntry);
    for (int i = 0; i < 6; i++) {
        HashMap* pEntry = (HashMap*)calloc(1, sizeof(HashMap));
        pEntry->val = i + 5;
        pEntry->key = nums[i]; // 可强转void*
        HASH_ADD_INT(userMap, key, pEntry); // v1, 必须配合HASH_FIND_INT
        // HASH_ADD_PTR(userMap, key, pEntry); // 不可行
    }

    // 2-2) 查找 - HASH_FIND_INT
    pEntry2 = NULL;
    HASH_FIND_INT(userMap, &nums[2], pEntry2); // v1, 必须配合HASH_ADD_INT, 参数2必须是findint(int的地址)
    // HASH_FIND_PTR(userMap, &nums, pEntry2); // 不可行
    if (pEntry2) {
        printf("%d's id is %d\n", pEntry2->key, pEntry2->val);
    }
    else {
        printf("key not found\n");
    }

    // 1-3)  添加结构体 Point*
    Point* p;
    for (int i = 0; i < 6; i += 3) {
        p = (Point*)calloc(1, sizeof(Point));
        p->loc[0] = nums[i];
        p->loc[1] = nums[i + 1];
        p->val = nums[i + 2];

        HashMap* pEntry = (HashMap*)calloc(1, sizeof(HashMap));
        pEntry->val = i + 10;
        pEntry->key = p; // 可强转void*
        HASH_ADD_PTR(userMap, key, pEntry); // 不可行
    }

    // 2-3) 查找 - HASH_FIND_INT
    pEntry2 = NULL;
    // Point p = { {1, 2}, 3 }; // 新建Point对象不可行，地址不同
    HASH_FIND_PTR(userMap, &p, pEntry2); // 不可行
    if (pEntry2) {
        printf("Point(loc=[%d, %d], val=%d)'s id is %d\n",
            ((Point*)(pEntry2->key))->loc[0], ((Point*)(pEntry2->key))->loc[1],
            ((Point*)(pEntry2->key))->val,
            pEntry2->val);
    }
    else {
        printf("key not found\n");
    }



    // 3) 删除 - HASH_DEL(map, pEntry)
    HashMap* cur, * tmp;
    HASH_ITER(hh, userMap, cur, tmp) {
        HASH_DEL(userMap, cur);
        free(cur);
    }
    free(userMap);
    return 0;
}

/* OUTPUT:
ljy's id is 3
3's id is 7
Point(loc=[4, 5], val=0)'s id is 13
*/