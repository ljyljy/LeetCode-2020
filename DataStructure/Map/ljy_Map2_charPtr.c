#include <stdlib.h>
#include <stdio.h>
// #include <stdbool.h>
// #include <math.h>
#include <string.h>
// #include <limits.h>
// #include <ctype.h>
#include "uthash.h"

typedef struct HashMap {
    const char* name;
    int id;
    UT_hash_handle hh;         /* makes this structure hashable */
} HashMap;

int main() {
    const char* names[] = { "joe", "bob", "betty", "ljy", "zzj", NULL };
    HashMap* userMap = NULL;

    // 1) 添加 - HASH_ADD_STR(map, keyField/字段名, pEntry);
    for (int i = 0; names[i]; ++i) {
        HashMap* pEntry = (HashMap*)calloc(1, sizeof(HashMap));
        pEntry->id = i;
        // char* vs char[N]

        // 1）通过calloc + strcpy，后续与char[N]操作一致
        pEntry->name = (char*)calloc(1, strlen(names[i]) + 1);
        // strcpy(pEntry->name, names[i]);
        // HASH_ADD_STR(userMap, name, pEntry); // v1，若对key使用strcpy，则可使用HASH_ADD_STR
        // HASH_ADD_KEYPTR(hh, userMap, pEntry->name, strlen(pEntry->name), pEntry); // v2 也可行

        // 2）通过char* key指向names[i]，需要使用HASH_ADD_KEYPTR
        pEntry->name = names[i];
        HASH_ADD_KEYPTR(hh, userMap, pEntry->name, strlen(pEntry->name), pEntry);// v1 可行
        // HASH_ADD_STR(userMap, name, pEntry); // v1，VSC可运行？
    }

    // 2) 查找 - HASH_FIND_STR(map, findstr, pEntry)
    HashMap* pEntry2 = NULL;
    HASH_FIND_STR(userMap, "ljy", pEntry2);
    if (pEntry2) printf("ljy's id is %d\n", pEntry2->id);

    // 3) 删除 - HASH_DEL(map, pEntry)
    HashMap* cur, * tmp;
    HASH_ITER(hh, userMap, cur, tmp) {
        HASH_DEL(userMap, cur);
        free(cur);
    }
    free(userMap);
    return 0;
}