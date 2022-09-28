/*
 难点：
 1) PriorityQueue<Long> 优先级队列/堆 -> 数组实现 , struct MinHeap {long* nums, n, bool(*cmp)(long,long)}
 2) Set<String> 去重 -> Map<key, key> 保证唯一, key为String(char[])

 坑：
 1) **HashSet的使用；
    坑：为了避免HashSet-TLE, 需要使用二级指针！不可定义全局变量set！
 2) Heap：由于父子下标计算需要左/右移，因此idx不可为0, 必须从1起！
 3) heap_cmp、swap必须直接对struct元素取地址！【【用一级指针无效！】】
*/

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>
#include <stdbool.h>
// #include "uthash.h"


#ifndef DIM
#define DIM(arr) sizeof(arr)/sizeof(*arr)
#endif


// 法2: 三指针/dp
int min3(int a, int b, int c) {
    return fmin(a, fmin(b, c));
}

int nthUglyNumber(int n) {
    int factors[] = { 2, 3, 5 };
    int dp[n];
    memset(dp, 0, sizeof(int) * n);
    dp[0] = 1;
    int fac2 = 0, fac3 = 0, fac5 = 0; // 上一个因数为2/3/5的下标

    for (int i = 1; i < n; i++) {
        dp[i] = min3(dp[fac2] * 2, dp[fac3] * 3, dp[fac5] * 5);
        if (dp[i] == dp[fac2] * 2) {
            fac2++;
        }
        if (dp[i] == dp[fac3] * 3) {
            fac3++;
        }
        if (dp[i] == dp[fac5] * 5) {
            fac5++;
        }
    }
    return dp[n - 1];
}

// 法1：Set + Heap
/* HashSet_ops -- start */

// Set由Map实现，key=val，即可保证唯一性
typedef struct HashSet {
    long key;
    UT_hash_handle hh;
} HashSet;

// 不要讲指针变量作为函数返回值，可能造成野指针！可作为参数返回！
bool set_find(HashSet** set, long ikey, HashSet* psEntry) {
    HASH_FIND(hh, *set, &ikey, sizeof(ikey), psEntry);
    return psEntry != NULL; // 非空：找到，且psEntry被修改并返回
}

bool set_add(HashSet** set, long ikey) {
    HashSet* psEntry = NULL;
    if (set_find(set, ikey, psEntry)) return false;
    // 没找到，psEntry == NULL，新加entry
    psEntry = (HashSet*)malloc(sizeof(HashSet));
    psEntry->key = ikey;
    HASH_ADD(hh, *set, key, sizeof(ikey), psEntry);
    return true;
}

/* HashSet_ops -- end */

/* Heap_ops -- start */
typedef struct MinHeap {
    long* nums;
    int size;
    bool (*cmp) (long, long); // 针对cmp1
} MinHeap;

// 由于父子下标计算需要左/右移，因此idx不可为0, 必须从1起！
void heap_init(MinHeap* heap, int n, bool (*cmp) (long, long)) {
    heap->nums = (long*)malloc(sizeof(long) * (n + 1)); // idx∈[1, n]
    heap->size = 0;
    heap->cmp = cmp;
}

// bool heap_cmp0(const void* pa, const void* pb) {
//     return *(long*)pa > *(long*)pb;
// }

// 【荐】最小堆，升序排列，优先pop小数
bool heap_cmp1(long a, long b) {
    return a > b; // 子 > 父
}

void swap(long* pa, long* pb) {
    long tmp = *pa;
    *pa = *pb;
    *pb = tmp;
}

// 向上调整 - O(n)
void heap_offer(MinHeap* heap, long num) {
    int childIdx = ++(heap->size), fatherIdx = childIdx >> 1;
    // long child = heap->nums[childIdx], father = heap->nums[fatherIdx];
    heap->nums[childIdx] = num;
    while (fatherIdx) {
        if (heap->cmp(heap->nums[childIdx], heap->nums[fatherIdx])) { // 针对cmp1
            break; // 最小堆, 子>父, break
        }
        swap(&heap->nums[childIdx], &heap->nums[fatherIdx]); // 若子<父，则swap
        childIdx = fatherIdx, fatherIdx = childIdx >> 1; // 向上调整
    }
}

// 向上调整 - O(n)
void heap_offer2(MinHeap* heap, long num) {
    int childIdx = ++(heap->size), fatherIdx = childIdx >> 1;
    // long child = &heap->nums[childIdx], father = &heap->nums[fatherIdx]; // 必须是引用！
    heap->nums[childIdx] = num;
    while (fatherIdx) {
        if (heap->cmp(heap->nums[childIdx], heap->nums[fatherIdx])) { // 针对cmp1
            break; // 最小堆, 子>父, break
        }
        swap(&heap->nums[childIdx], &heap->nums[fatherIdx]); // 若子<父，则swap
        childIdx = fatherIdx, fatherIdx = childIdx >> 1; // 向上调整
    }
}

// swap(top=1, last=n), n--, 向下调整 - O(nlogn)
void heap_poll(MinHeap* heap) {
    swap(&heap->nums[1], &heap->nums[(heap->size)--]);
    int fatherIdx = 1, childIdx = fatherIdx << 1;
    // long* child = &(heap->nums[childIdx]), * father = &(heap->nums[fatherIdx]);
    while (childIdx <= heap->size) {
        if (childIdx + 1 <= heap->size) { // 【todo：注意！】
            if (heap->cmp(heap->nums[childIdx], heap->nums[childIdx + 1])) {
                childIdx++; // 【若 左子 > 右子，则swap(父，右子)】
            }
        }
        if (heap->cmp(heap->nums[childIdx], heap->nums[fatherIdx])) break;
        // swap(child, father); // 【【【WA！！！】】】
        swap(&heap->nums[childIdx], &heap->nums[fatherIdx]); // 若子<父，则swap
        fatherIdx = childIdx, childIdx = fatherIdx << 1; // 向下调整
    }
}

long heap_top(MinHeap* heap) {
    return heap->nums[1];
}

bool isEmpty(MinHeap* heap) {
    return heap->size == 0;
}

/* Heap_ops -- end */

int nthUglyNumber_HashHeap(int k) {
    int factors[3] = { 2, 3, 5 };
    HashSet* set = NULL;
    set_add(&set, 1);
    MinHeap* heap = malloc(sizeof(MinHeap));
    heap_init(heap, k * 3, heap_cmp1);
    heap_offer(heap, 1);
    long curNum = 1L;
    for (int i = 0; i < k; i++) {
        curNum = heap_top(heap);
        heap_poll(heap);

        for (int i = 0; i < 3; i++) {
            long nxtNum = curNum * factors[i];
            HashSet* psEntry = NULL;
            if (!set_find(&set, nxtNum, psEntry)) {
                set_add(&set, nxtNum);
                heap_offer(heap, nxtNum);
            }
        }
    }
    return (int)curNum;
}

