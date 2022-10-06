#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>
#include "uthash.h"

#define DIM(x) sizeof(x)/sizeof(*x)
/**
 * C特性：
 * 1) 自定义哈希表：
 *    map_putOrAdd(&map, key, val) <=>
 *        map.put(key, map.getOrDefault(key,0)+val)
 * 2) 字符串处理：strchr & 下标计算、atoi
 * 3) int(4B) -> string后，所占字节数会变大("INT_MAX"为12B)！
 **/
typedef struct HashMap {
    const char* domain; // key: 子域名
    int cnt; // val：频次
    UT_hash_handle hh;
} HashMap;


bool map_putOrAdd(HashMap** map, const char* domain, int cnt) {
    HashMap* pEntry = NULL;
    HASH_FIND_STR(*map, domain, pEntry);
    if (pEntry == NULL) {
        pEntry = (HashMap*)malloc(sizeof(HashMap));
        // pEntry->domain = (char*)malloc(sizeof(domain));
        // strcpy(pEntry->domain, domain);
        pEntry->domain = domain; // const*，不可改值
        pEntry->cnt = cnt;
        HASH_ADD_STR(*map, domain, pEntry);
    }
    else {
        pEntry->cnt += cnt;
    }
    return true;
}

void map_free(HashMap** map) {
    HashMap* cur, * tmp;
    HASH_ITER(hh, *map, cur, tmp) {
        HASH_DEL(*map, cur);
        free(cur); cur = NULL;
    }
}

void map_iter(HashMap* map) {
    HashMap* cur, * tmp;
    HASH_ITER(hh, map, cur, tmp) {
        printf("%s-%d\n", cur->domain, cur->cnt);
    }
}

char** subdomainVisits(char** cpdomains, int cpdomainsSize, int* returnSize) {
    HashMap* map = NULL;
    for (int i = 0; i < cpdomainsSize; i++) {
        char* cp = cpdomains[i]; // "cnt domains"
        char* spaceHdr = strchr(cp, ' ');// strchr返回第一次出现' '的地址（子串:" domains"）
        int spaceIdx = spaceHdr - cp; // 得到字符串分割处的idx

        int cnt = atoi(cp); // 只读取第一次出现的数字
        char* domain = spaceHdr + 1;
        map_putOrAdd(&map, domain, cnt);

        for (int j = 0; j < strlen(domain); j++) {
            if (domain[j] == '.') { // aaa.leetcode.com
                char* subDomain = domain + j + 1; // 子域名首地址
                map_putOrAdd(&map, subDomain, cnt);
            }
        }
    }
    // map_iter(map);
    int cnt = HASH_COUNT(map);
    char** res = (char**)malloc(sizeof(char*) * cnt);
    int idx = 0;
    for (HashMap* p = map; p != NULL; p = p->hh.next) { // 或HASH_ITER(hh, map, cur, tmp)
        const char* key = p->domain;
        int val = p->cnt;
        // printf("sizeof(val)=%d, sizeof(key)=%d\n", sizeof(val), sizeof(key));
        res[idx] = (char*)malloc(sizeof(char) * (sizeof(key) + 32)); // 必须+32(如INT_MAX转到string的长度)！否则堆溢出！
        //     printf("sizeof(2147483647)=%d , sizeof('INT_MAX')=%d\n", sizeof(int), sizeof("2147483647"));
        sprintf(res[idx], "%d %s", val, key);
        idx++;
    }
    map_free(&map);
    *returnSize = cnt;
    return res;
}


int main() {
    // test <strchr>
    // char* cp = "9001 leetcode.com";
    // char* ans = strchr(cp, ' '); // strchr返回第一次出现' '的地址（子串）
    // int spaceIdx = strchr(cp, ' ') - cp;
    // printf("spaceHdr=%s, spaceIdx=%d\n", ans, spaceIdx);
    char* cpdomains[] = { "900 google.mail.com", "50 yahoo.com",
        "1 intel.mail.com", "5 wiki.org" };
    int returnSize = 0;
    char** res = subdomainVisits(cpdomains, DIM(cpdomains), &returnSize);
    for (int i = 0; i < returnSize; i++) {
        printf("%s\n", res[i]);
    }

    // // test <int->string>
    // // int转str后，所占字节数>=12！(见q811)
    // printf("INT_MIN=%d, sizeof(-2147483648)=%d , sizeof('INT_MIN')=%d\n", INT_MIN, sizeof(int), sizeof("-2147483648"));
    // // INT_MIN=-2147483648, sizeof(-2147483648)=4 , sizeof('INT_MIN')=12
    return 0;
}