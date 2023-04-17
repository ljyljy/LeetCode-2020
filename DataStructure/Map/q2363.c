#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
// #include <math.h>
// #include <string.h>
// #include <limits.h>
// #include <ctype.h>
#include "uthash.h"

// q2363.c
// ��1��HashMap ��� & ����(HASH_SORT or qsort -- int**)
typedef struct Map {
    int value; // key
    int weight; // val
    UT_hash_handle hh;
} HashMap;

bool map_putOrAdd(HashMap** map, int val, int weight) {
    HashMap* pEntry = NULL;
    // ���� - HASH_FIND_INT(Map*, ��&key0�����key.��ַ������key�ֶ�������, pEntry);
    HASH_FIND_INT(*map, &val, pEntry); // �����key.��ַ��
    if (pEntry == NULL) {
        pEntry = (HashMap*)malloc(sizeof(HashMap));
        pEntry->value = val;
        pEntry->weight = weight;
        // ��� - HASH_ADD_INT(Map*, ��keyField/key.�ֶ�����, pEntry);
        HASH_ADD_INT(*map, value, pEntry); // ��Map-key.�ֶ�����
    }
    else {
        pEntry->weight += weight;
    }
    return true;
}

// v1: �Աȵ�Ԫ����map, ����ΪHashMap*��ֱ�ӱȽϼ��ɡ�
static inline int Map_Cmp(HashMap* a, HashMap* b) {
    return a->value - b->value;
}

// v2���Աȵ�Ԫ����res[i], ����Ϊint*����ҪǿתΪint**���ٽ����á�
static inline int cmp(const void* a, const void* b) {
    // return (*(Item**)a)->value - (*(Item**)b)->value;
    return (*(int**)a)[0] - (*(int**)b)[0]; // ����res[i][0]����
}

void Map_Free(HashMap** map) {
    HashMap* cur = NULL, * tmp = NULL;
    HASH_ITER(hh, *map, cur, tmp) {
        HASH_DEL(*map, cur);
        free(cur);
        cur = NULL;
    }
}

int** mergeSimilarItems(int** items1, int n1, int* items1ColSize,
    int** items2, int n2, int* items2ColSize, int* returnSize, int** returnColumnSizes) {
    HashMap* map = NULL;
    for (int i = 0; i < n1; i++) {
        map_putOrAdd(&map, items1[i][0], items1[i][1]);
    }
    for (int i = 0; i < n2; i++) {
        map_putOrAdd(&map, items2[i][0], items2[i][1]);
    }
    HASH_SORT(map, Map_Cmp); // ����v1����key(��ֵvalue)����ʹ��HASH_SORT

    int cnt = HASH_COUNT(map);
    int** res = (int**)malloc(cnt * sizeof(int*));
    int* n_cols = (int*)malloc(cnt * sizeof(int));
    // ��     *returnColumnSizes = (int*)calloc(n, sizeof(int)); // n��item������
    int curCnt = 0;

    HashMap* cur = NULL, * tmp = NULL;
    HASH_ITER(hh, map, cur, tmp) {
        res[curCnt] = (int*)malloc(2 * sizeof(int)); // <k, v>
        res[curCnt][0] = cur->value;
        res[curCnt][1] = cur->weight;
        n_cols[curCnt++] = 2;
    }

    // qsort(res, cnt, sizeof(res[0]), cmp);// ����v2��qsort����
    Map_Free(&map);
    *returnSize = curCnt;
    *returnColumnSizes = n_cols;
    return res;
}


// ��2���Զ���ṹ�壬��ʾ��Ʒ�ļ�ֵ������
/* ֪ʶ��
- �Զ���ṹ��
- qsort �Զ���������
  - �����Զ���ṹ��
- ��ά�����ȥ��
  - memmove + realloc��HW01��
  - res[curCnt]ʱ�����أ���ֵ���� (q2363-��2)
- ��̬����
*/
typedef struct Item {
    int value;
    int weight;
} Item;

// �Զ���ȽϺ��������ռ�ֵ��������
// �Աȵ�Ԫ����items[i], ����ΪItem*����ҪǿתΪItem**���ٽ����á�
int cmp2(const void* a, const void* b) {
    const Item* item1 = *(const Item**)a;
    const Item* item2 = *(const Item**)b;
    return item1->value - item2->value;
}

int** mergeSimilarItems2(int** items1, int n1, int* items1ColSize,
    int** items2, int n2, int* items2ColSize, int* returnSize, int** returnColumnSizes) {
    // ���� Item ���͵����鲢��ʼ��
    Item** items = (Item**)malloc(sizeof(Item*) * (n1 + n2));

    // �� items1 �� items2 �е����ݴ��� items ��
    int idx = 0;
    for (int i = 0; i < n1; i++) {
        items[idx] = (Item*)malloc(sizeof(Item));
        items[idx]->value = items1[i][0];
        items[idx]->weight = items1[i][1];
        idx++;
    }
    for (int i = 0; i < n2; i++) {
        items[idx] = (Item*)malloc(sizeof(Item));
        items[idx]->value = items2[i][0];
        items[idx]->weight = items2[i][1];
        idx++;
    }

    // ���ռ�ֵ��������
    qsort(items, n1 + n2, sizeof(items[0]), cmp2);

    // ͳ��ÿ����ֵ��������(ȥ�� �C by��ֵ����)
    /* ��1����̬���ݣ�ÿ������1��int*�ռ�
    int** ret = NULL;
    int curCnt = 0;
    int* n_cols = NULL;
    for (int i = 0; i < n1 + n2; i++) {
        if (i == 0 || items[i]->value != items[i - 1]->value) {
            // ������µļ�ֵ�����ڽ������������һ��Ԫ��
            curCnt++;
            ret = (int**)realloc(ret, sizeof(int*) * curCnt); // ����̬���ݡ�һ��int*�Ŀռ�
            ret[curCnt - 1] = (int*)malloc(sizeof(int) * 2);
            ret[curCnt - 1][0] = items[i]->value;
            ret[curCnt - 1][1] = items[i]->weight;
            n_cols = (int*)realloc(n_cols, sizeof(int) * curCnt);
            n_cols[curCnt - 1] = 2;
        }
        else {
            // ��������еļ�ֵ�����ۼ�����
            ret[curCnt - 1][1] += items[i]->weight;
        }
    }
    */
    // ��2����̬���ݣ�ÿ������basicSize��int*�ռ�
    int basicSize = 8, curCnt = 0;
    int** ret = (int**)malloc(sizeof(int*) * basicSize);
    int* n_cols = (int*)malloc(sizeof(int) * basicSize);

    for (int i = 0; i < n1 + n2; i++) {
        if (i - 1 >= 0 && items[i]->value == items[i - 1]->value) {
            // ��value����ִ��curCnt�������������ȥ
            ret[curCnt - 1][1] += items[i]->weight;// ��������еļ�ֵ�����ۼ�����
        }
        else { // ������µļ�ֵ�����ڽ������������һ��Ԫ��
            ret[curCnt] = (int*)malloc(2 * sizeof(int)); // <k, v>
            ret[curCnt][0] = items[i]->value;
            ret[curCnt][1] = items[i]->weight;
            n_cols[curCnt++] = 2;

            if (curCnt == basicSize) { // ��̬����
                basicSize *= 2;
                ret = (int**)realloc(ret, sizeof(int*) * basicSize);
                n_cols = (int*)realloc(n_cols, sizeof(int) * basicSize);
            }
        }
    }

    // ��������������ֵ
    *returnSize = curCnt;
    *returnColumnSizes = n_cols;
    return ret;
}

int main()
{
    int n1 = 3, n2 = 3;
    int items1ColSize = 2, items2ColSize = 2;
    int returnSize = 0;
    int* returnColumnSizes = (int*)malloc(6 * sizeof(int));

    int** items1 = (int**)malloc(n1 * sizeof(int*));
    for (int i = 0; i < n1; i++) {
        items1[i] = (int*)malloc(2 * sizeof(int));
    }
    items1[0][0] = 1; items1[0][1] = 10;
    items1[1][0] = 2; items1[1][1] = 5;
    items1[2][0] = 3; items1[2][1] = 15;

    int** items2 = (int**)malloc(n2 * sizeof(int*));
    for (int i = 0; i < n2; i++) {
        items2[i] = (int*)malloc(2 * sizeof(int));
    }
    items2[0][0] = 1; items2[0][1] = 5;
    items2[1][0] = 2; items2[1][1] = 10;
    items2[2][0] = 4; items2[2][1] = 20;

    int** res = mergeSimilarItems(items1, n1, &items1ColSize, items2, n2, &items2ColSize, &returnSize, &returnColumnSizes);

    for (int i = 0; i < returnSize; i++) {
        printf("[%d, %d]", res[i][0], res[i][1]);
    }
    printf("\n");
    return 0;
}

