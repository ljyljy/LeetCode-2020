#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
// #include <math.h>
// #include <string.h>
// #include <limits.h>
// #include <ctype.h>
#include "uthash.h"

// q2363.c
// 法1：HashMap 添加 & 排序(HASH_SORT or qsort -- int**)
typedef struct Map {
    int value; // key
    int weight; // val
    UT_hash_handle hh;
} HashMap;

bool map_putOrAdd(HashMap** map, int val, int weight) {
    HashMap* pEntry = NULL;
    // 查找 - HASH_FIND_INT(Map*, 【&key0：入参key.地址（而非key字段名）】, pEntry);
    HASH_FIND_INT(*map, &val, pEntry); // 【入参key.地址】
    if (pEntry == NULL) {
        pEntry = (HashMap*)malloc(sizeof(HashMap));
        pEntry->value = val;
        pEntry->weight = weight;
        // 添加 - HASH_ADD_INT(Map*, 【keyField/key.字段名】, pEntry);
        HASH_ADD_INT(*map, value, pEntry); // 【Map-key.字段名】
    }
    else {
        pEntry->weight += weight;
    }
    return true;
}

// v1: 对比的元素是map, 类型为HashMap*，直接比较即可。
static inline int Map_Cmp(HashMap* a, HashMap* b) {
    return a->value - b->value;
}

// v2：对比的元素是res[i], 类型为int*，需要强转为int**，再解引用。
static inline int cmp(const void* a, const void* b) {
    // return (*(Item**)a)->value - (*(Item**)b)->value;
    return (*(int**)a)[0] - (*(int**)b)[0]; // 并按res[i][0]升序
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
    HASH_SORT(map, Map_Cmp); // 排序v1：按key(价值value)升序，使用HASH_SORT

    int cnt = HASH_COUNT(map);
    int** res = (int**)malloc(cnt * sizeof(int*));
    int* n_cols = (int*)malloc(cnt * sizeof(int));
    // 或     *returnColumnSizes = (int*)calloc(n, sizeof(int)); // n个item的列数
    int curCnt = 0;

    HashMap* cur = NULL, * tmp = NULL;
    HASH_ITER(hh, map, cur, tmp) {
        res[curCnt] = (int*)malloc(2 * sizeof(int)); // <k, v>
        res[curCnt][0] = cur->value;
        res[curCnt][1] = cur->weight;
        n_cols[curCnt++] = 2;
    }

    // qsort(res, cnt, sizeof(res[0]), cmp);// 排序v2：qsort排序
    Map_Free(&map);
    *returnSize = curCnt;
    *returnColumnSizes = n_cols;
    return res;
}


// 法2：自定义结构体，表示物品的价值和重量
/* 知识点
- 自定义结构体
- qsort 自定义排序函数
  - 基于自定义结构体
- 二维数组的去重
  - memmove + realloc（HW01）
  - res[curCnt]时就判重，旧值更新 (q2363-法2)
- 动态扩容
*/
typedef struct Item {
    int value;
    int weight;
} Item;

// 自定义比较函数，按照价值升序排列
// 对比的元素是items[i], 类型为Item*，需要强转为Item**，再解引用。
int cmp2(const void* a, const void* b) {
    const Item* item1 = *(const Item**)a;
    const Item* item2 = *(const Item**)b;
    return item1->value - item2->value;
}

int** mergeSimilarItems2(int** items1, int n1, int* items1ColSize,
    int** items2, int n2, int* items2ColSize, int* returnSize, int** returnColumnSizes) {
    // 创建 Item 类型的数组并初始化
    Item** items = (Item**)malloc(sizeof(Item*) * (n1 + n2));

    // 将 items1 和 items2 中的数据存入 items 中
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

    // 按照价值升序排序
    qsort(items, n1 + n2, sizeof(items[0]), cmp2);

    // 统计每个价值的总重量(去重 – by旧值更新)
    /* 法1：动态扩容，每次扩充1个int*空间
    int** ret = NULL;
    int curCnt = 0;
    int* n_cols = NULL;
    for (int i = 0; i < n1 + n2; i++) {
        if (i == 0 || items[i]->value != items[i - 1]->value) {
            // 如果是新的价值，则在结果数组中新增一个元素
            curCnt++;
            ret = (int**)realloc(ret, sizeof(int*) * curCnt); // 【动态扩容】一个int*的空间
            ret[curCnt - 1] = (int*)malloc(sizeof(int) * 2);
            ret[curCnt - 1][0] = items[i]->value;
            ret[curCnt - 1][1] = items[i]->weight;
            n_cols = (int*)realloc(n_cols, sizeof(int) * curCnt);
            n_cols[curCnt - 1] = 2;
        }
        else {
            // 如果是已有的价值，则累加重量
            ret[curCnt - 1][1] += items[i]->weight;
        }
    }
    */
    // 法2：动态扩容，每次扩充basicSize个int*空间
    int basicSize = 8, curCnt = 0;
    int** ret = (int**)malloc(sizeof(int*) * basicSize);
    int* n_cols = (int*)malloc(sizeof(int) * basicSize);

    for (int i = 0; i < n1 + n2; i++) {
        if (i - 1 >= 0 && items[i]->value == items[i - 1]->value) {
            // 新value最后会执行curCnt自增，这里需减去
            ret[curCnt - 1][1] += items[i]->weight;// 如果是已有的价值，则累加重量
        }
        else { // 如果是新的价值，则在结果数组中新增一个元素
            ret[curCnt] = (int*)malloc(2 * sizeof(int)); // <k, v>
            ret[curCnt][0] = items[i]->value;
            ret[curCnt][1] = items[i]->weight;
            n_cols[curCnt++] = 2;

            if (curCnt == basicSize) { // 动态扩容
                basicSize *= 2;
                ret = (int**)realloc(ret, sizeof(int*) * basicSize);
                n_cols = (int*)realloc(n_cols, sizeof(int) * basicSize);
            }
        }
    }

    // 将结果输出参数赋值
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

