#include "uthash.h"
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>

// 法2：差分数组/前缀和
#define MAX_LEN 1001
bool carPooling2(int** trips, int n, int* tripsColSize, int capacity) {
    int* sum = (int*)calloc(MAX_LEN, sizeof(int));// 差分数组

    for (int i = 0; i < n; i++) {// trip=[n, from, to]
        int cnt = trips[i][0], t_start = trips[i][1], t_end = trips[i][2];
        sum[t_start] += cnt; // 上车
        sum[t_end] -= cnt;   // 下车，(from, to)之中的人数为diff[from, to]前缀和
    }
    int curWeight = 0;
    for (int i = 0; i < MAX_LEN; i++) {
        curWeight += sum[i]; // 前缀和
        if (curWeight > capacity) {
            free(sum);
            sum = NULL;
            return false;
        }
    }
    free(sum);
    sum = NULL;
    return true;
}


// 法1：扫描线 = Map增减&排序 + 前缀和【类比：q218扫描线】
#define ADD 1
#define MINUS 2

typedef struct TreeMap {
    int time; // key
    int cnt; // val
    UT_hash_handle hh;
} TreeMap;

void map_putOrUpdate(TreeMap** map, int key, int val, int OPS) {
    TreeMap* pEntry = NULL;
    HASH_FIND_INT(*map, &key, pEntry);// 【入参key.地址】
    if (pEntry == NULL) { // put
        pEntry = (TreeMap*)malloc(sizeof(TreeMap));
        pEntry->time = key;
        if (OPS == ADD) pEntry->cnt = val;
        else pEntry->cnt = -val; // 后续用于前缀和
        HASH_ADD_INT(*map, time, pEntry);// 【Map.key字段名】
    }
    else { // Update: 加/减
        if (OPS == ADD) {
            pEntry->cnt += val;
        }
        else {
            pEntry->cnt -= val;
        }
    }
}

void map_free(TreeMap** map) {
    TreeMap* cur, * tmp;
    HASH_ITER(hh, *map, cur, tmp) {
        HASH_DEL(*map, cur);
        free(cur);
        cur = NULL;
    }
}

static inline int mapCmp(TreeMap* a, TreeMap* b) {
    return a->time - b->time; // key=time升序
}
bool carPooling(int** trips, int n, int* tripsColSize, int capacity) {
    TreeMap* map = NULL;
    for (int i = 0; i < n; i++) {
        int cnt = trips[i][0], t_start = trips[i][1], t_end = trips[i][2];
        map_putOrUpdate(&map, t_start, cnt, ADD);
        map_putOrUpdate(&map, t_end, cnt, MINUS);
    }
    HASH_SORT(map, mapCmp); // 构造为TreeMap（key/time升序）

    int totalCnt = 0;
    TreeMap* cur, * tmp;
    HASH_ITER(hh, map, cur, tmp) {
        // printf("time=%d, cnt=%d\n", cur->time, cur->cnt);
        totalCnt += cur->cnt;
        if (totalCnt > capacity) {
            map_free(&map);
            return false;
        }
    }
    map_free(&map);
    return true;
}

