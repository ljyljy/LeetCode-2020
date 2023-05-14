#include <stdio.h>
#include <stdlib.h>
#include <string.h>
// #include <limits.h>
#include <stdbool.h>
#include "uthash.h"

/* 知识点
1）法1：UT_HASH 哈希表
  - 【坑1】注意传参是【一级指针】！
    - 因此TwoSum需要【指向一个哈希表】，但自身不能为哈希表！
    - 否则一级指针传参，函数对哈希表修改，函数返回后 修改无效
  - 其他解决方案[不推荐]：
    - v1 需要传参：`TwoSum** obj`
    - v2 使用全局变量`g_obj`（函数参数1不使用）

  - 【坑2】避免溢出！！！
    - 由于题目给定的value∈[INT_MIN, INT_MAX]，因此value ± num可能溢出
    - 需要转换成long类型，避免溢出
      - `long num2 = (long)value - cur->num; // 【坑2】避免溢出！！！`

  - 【坑3】若二者相等，则需判断Map.cnt是否大于1

2）法2：二分查找（sort）/ 双指针
  - 略
 */

 // q170. 两数之和 III - 数据结构设计
typedef struct {
    // ↓ 平移到[0, INT_MAX*2 + 1], 过大，不合适，直接使用UT_HASH
    // int nums[(long)INT_MAX * 2 + 1];
    int num;
    int cnt;
    UT_hash_handle hh;
} HashMap;

typedef struct {
    HashMap* map;
} TwoSum;


TwoSum* twoSumCreate() {
    TwoSum* obj = (TwoSum*)calloc(1, sizeof(TwoSum));
    obj->map = NULL; // 后面使用HASH_ADD_INT增加元素。
    return obj;
}

void twoSumAdd(TwoSum* obj, int number) {
    HashMap* pEntry = NULL;
    HASH_FIND_INT(obj->map, &number, pEntry);
    if (pEntry == NULL) {
        pEntry = (HashMap*)malloc(sizeof(HashMap));
        pEntry->num = number;
        pEntry->cnt = 1;
        HASH_ADD_INT(obj->map, num, pEntry);
        // printf("Add: %d, cnt = %d\n", number, pEntry->cnt);
    }
    else {
        pEntry->cnt += 1;
    }
    // long idx = number + (long)INT_MAX;
    // obj->nums[idx] += 1;
}

bool twoSumFind(TwoSum* obj, int value) {
    HashMap* cur = NULL, * tmp = NULL;
    HASH_ITER(hh, obj->map, cur, tmp) {
        long num2 = (long)value - cur->num;
        HashMap* pEntry = NULL;
        HASH_FIND_INT(obj->map, &num2, pEntry);
        if (pEntry != NULL) {
            // printf("num2=%d, cur->num=%d, pEntry->cnt=%d\n", num2, cur->num, pEntry->cnt);
            // 【坑1】若二者相等，则需判断Map.cnt是否大于1
            if (num2 == cur->num && pEntry->cnt > 1) return true;
            if (num2 != cur->num) return true;
        }
    }
    return false;
}


void twoSumFree(TwoSum* obj) {
    HashMap* cur = NULL, * tmp = NULL;
    HASH_ITER(hh, obj->map, cur, tmp) {
        HASH_DEL(obj->map, cur);
        free(cur);
    }
}

int main()
{
    TwoSum* obj = twoSumCreate();
    twoSumAdd(obj, 1);
    twoSumAdd(obj, 3);
    twoSumAdd(obj, 5);
    printf("twoSumFind(obj, 4) = %d\n", twoSumFind(obj, 4)); // 返回 true
    printf("twoSumFind(obj, 7) = %d\n", twoSumFind(obj, 7)); // 返回 false
    printf("twoSumFind(obj, 8) = %d\n", twoSumFind(obj, 8)); // 返回 true
    twoSumAdd(obj, 3);
    printf("twoSumFind(obj, 6) = %d\n", twoSumFind(obj, 6)); // 返回 true
    twoSumFree(obj);
    return 0;
}