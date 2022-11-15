#include "uthash.h"
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>

// ��2���������/ǰ׺��
#define MAX_LEN 1001
bool carPooling2(int** trips, int n, int* tripsColSize, int capacity) {
    int* sum = (int*)calloc(MAX_LEN, sizeof(int));

    for (int i = 0; i < n; i++) {
        int cnt = trips[i][0], t_start = trips[i][1], t_end = trips[i][2];
        sum[t_start] += cnt;
        sum[t_end] -= cnt;
    }
    int totalWeight = 0;
    for (int i = 0; i < MAX_LEN; i++) {
        totalWeight += sum[i];
        if (totalWeight > capacity) {
            return false;
        }
    }
    return true;
}


// ��1��ɨ���� = TreeMap / С���� + ǰ׺�͡���ȣ�q218ɨ���ߡ�
#define ADD 1
#define MINUS 2

typedef struct TreeMap {
    int time; // key
    int cnt; // val
    UT_hash_handle hh;
} TreeMap;

void map_putOrUpdate(TreeMap** map, int key, int val, int OPS) {
    TreeMap* pEntry = NULL;
    HASH_FIND_INT(*map, &key, pEntry);
    if (pEntry == NULL) { // put
        pEntry = (TreeMap*)malloc(sizeof(TreeMap));
        pEntry->time = key;
        if (OPS == ADD) pEntry->cnt = val;
        else pEntry->cnt = -val; // ��������ǰ׺��
        HASH_ADD_INT(*map, time, pEntry);
    }
    else { // Update: ��/��
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
    return a->time - b->time; // key=time����
}
bool carPooling(int** trips, int n, int* tripsColSize, int capacity) {
    TreeMap* map = NULL;
    for (int i = 0; i < n; i++) {
        int cnt = trips[i][0], t_start = trips[i][1], t_end = trips[i][2];
        map_putOrUpdate(&map, t_start, cnt, ADD);
        map_putOrUpdate(&map, t_end, cnt, MINUS);
    }
    HASH_SORT(map, mapCmp); // ����ΪTreeMap��key/time����

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