#include <stdlib.h>
#include <stdio.h>
// #include <stdbool.h>
// #include <math.h>
#include <string.h>
// #include <limits.h>
// #include <ctype.h>
#include "uthash.h"

typedef struct HashMap {
    char name[10];             /* key (string is WITHIN the structure) */
    int id;
    UT_hash_handle hh;         /* makes this structure hashable */
} HashMap;

int main() {
    const char* names[] = { "joe", "bob", "betty", "ljy", "zzj", NULL };
    HashMap* userMap = NULL;

    // 1) 添加 - HASH_ADD_STR(map, keyField/字段名, pEntry);
    for (int i = 0; names[i]; ++i) {
        HashMap* pEntry = (HashMap*)calloc(1, sizeof(HashMap));
        strcpy(pEntry->name, names[i]);
        pEntry->id = i;
        HASH_ADD_STR(userMap, name, pEntry); // v1, 可行，常用
        // HASH_ADD_KEYPTR(hh, userMap, pEntry->name, strlen(pEntry->name), pEntry); // v2, 也可行，因为name本身指向char[N]首地址
    }

    // 2) 查找 - HASH_FIND_STR(map, findstr, pEntry)
    HashMap* pEntry2 = NULL;
    HASH_FIND_STR(userMap, "betty", pEntry2);
    if (pEntry2) printf("betty's id is %d\n", pEntry2->id);

    // 3) 删除 - HASH_DEL(map, pEntry)
    HashMap* cur, * tmp;
    HASH_ITER(hh, userMap, cur, tmp) {
        HASH_DEL(userMap, cur);
        free(cur);
    }
    free(userMap);
    return 0;
}