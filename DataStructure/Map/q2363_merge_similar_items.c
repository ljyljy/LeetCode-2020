#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <ctype.h>
#include "uthash.h"

typedef struct HashMap {
    int value; // key
    int weight; // val
    UT_hash_handle hh;
} HashMap;

bool map_putOrAdd(HashMap** map, int val, int weight) {
    HashMap* pEntry = NULL;
    // ���� - HASH_FIND_INT(Map*, ��&key0�������е����֣�����key�ֶ�������, pEntry);
    HASH_FIND_INT(*map, &val, pEntry);
    if (pEntry == NULL) {
        pEntry = (HashMap*)malloc(sizeof(HashMap));
        pEntry->value = val;
        pEntry->weight = weight;
        // ��� - HASH_ADD_INT(Map*, ��keyField/key.�ֶ�����, pEntry);
        HASH_ADD_INT(*map, value, pEntry);
    }
    else {
        pEntry->weight += weight;
    }
    return true;
}

void map_free(HashMap** map) {
    HashMap* cur, * tmp;
    HASH_ITER(hh, *map, cur, tmp) {
        HASH_DEL(*map, cur);
        free(cur);
    }
}

static inline int cmp(const void* a, const void* b) {
    // return (*(Item**)a)->value - (*(Item**)b)->value;
    return (*(int**)a)[0] - (*(int**)b)[0];
}

int** mergeSimilarItems(int** items1, int n1, int* items1ColSize,
    int** items2, int n2, int* items2ColSize,
    int* returnSize, int** returnColumnSizes) {
    HashMap* map = NULL;
    for (int i = 0; i < n1; i++) {
        map_putOrAdd(&map, items1[i][0], items1[i][1]);
    }
    for (int i = 0; i < n2; i++) {
        map_putOrAdd(&map, items2[i][0], items2[i][1]);
    }
    int n = HASH_COUNT(map);
    int** res = (int**)calloc(n, sizeof(int*));
    for (int i = 0; i < n; i++) {
        res[i] = (int*)calloc(2, sizeof(int)); // [val, weight]
    }

    int idx = 0;
    HashMap* cur, * tmp;
    HASH_ITER(hh, map, cur, tmp) {
        res[idx][0] = cur->value;
        res[idx++][1] = cur->weight;
    }
    qsort(res, n, sizeof(int*), cmp);
    *returnSize = n;
    *returnColumnSizes = (int*)calloc(n, sizeof(int)); // n��item������
    for (int i = 0; i < n; i++) {
        (*returnColumnSizes)[i] = 2;
    }
    return res;
}


// ��2���Զ���ṹ�壬��ʾ��Ʒ�ļ�ֵ������
typedef struct Item {
    int value;
    int weight;
} Item;

// �Զ���ȽϺ��������ռ�ֵ��������
int cmp2(const void* a, const void* b) {
    const Item* item1 = *(const Item**)a;
    const Item* item2 = *(const Item**)b;
    return item1->value - item2->value;
}

int** mergeSimilarItems2(int** items1, int items1Size, int* items1ColSize, int** items2, int items2Size, int* items2ColSize, int* returnSize, int** returnColumnSizes) {
    // ���� Item ���͵����鲢��ʼ��
    Item** items = (Item**)malloc(sizeof(Item*) * (items1Size + items2Size));
    for (int i = 0; i < items1Size + items2Size; i++) {
        items[i] = (Item*)malloc(sizeof(Item));
        items[i]->value = 0;
        items[i]->weight = 0;
    }

    // �� items1 �� items2 �е����ݴ��� items ��
    int index = 0;
    for (int i = 0; i < items1Size; i++) {
        items[index]->value = items1[i][0];
        items[index]->weight = items1[i][1];
        index++;
    }
    for (int i = 0; i < items2Size; i++) {
        items[index]->value = items2[i][0];
        items[index]->weight = items2[i][1];
        index++;
    }

    // ���ռ�ֵ��������
    qsort(items, items1Size + items2Size, sizeof(Item*), cmp2);

    // ͳ��ÿ����ֵ��������
    int** ret = NULL;
    int retSize = 0;
    int* retColumnSizes = NULL;
    for (int i = 0; i < items1Size + items2Size; i++) {
        if (i == 0 || items[i]->value != items[i - 1]->value) {
            // ������µļ�ֵ�����ڽ������������һ��Ԫ��
            retSize++;
            ret = (int**)realloc(ret, sizeof(int*) * retSize);
            ret[retSize - 1] = (int*)malloc(sizeof(int) * 2);
            ret[retSize - 1][0] = items[i]->value;
            ret[retSize - 1][1] = items[i]->weight;
            retColumnSizes = (int*)realloc(retColumnSizes, sizeof(int) * retSize);
            retColumnSizes[retSize - 1] = 2;
        }
        else {
            // ��������еļ�ֵ�����ۼ�����
            ret[retSize - 1][1] += items[i]->weight;
        }
    }

    // ��������������ֵ
    *returnSize = retSize;
    *returnColumnSizes = retColumnSizes;
    return ret;
}
