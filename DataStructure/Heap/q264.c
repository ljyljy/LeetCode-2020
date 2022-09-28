/*
 �ѵ㣺
 1) PriorityQueue<Long> ���ȼ�����/�� -> ����ʵ�� , struct MinHeap {long* nums, n, bool(*cmp)(long,long)}
 2) Set<String> ȥ�� -> Map<key, key> ��֤Ψһ, keyΪString(char[])

 �ӣ�
 1) **HashSet��ʹ�ã�
    �ӣ�Ϊ�˱���HashSet-TLE, ��Ҫʹ�ö���ָ�룡���ɶ���ȫ�ֱ���set��
 2) Heap�����ڸ����±������Ҫ��/���ƣ����idx����Ϊ0, �����1��
 3) heap_cmp��swap����ֱ�Ӷ�structԪ��ȡ��ַ��������һ��ָ����Ч������
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


// ��2: ��ָ��/dp
int min3(int a, int b, int c) {
    return fmin(a, fmin(b, c));
}

int nthUglyNumber(int n) {
    int factors[] = { 2, 3, 5 };
    int dp[n];
    memset(dp, 0, sizeof(int) * n);
    dp[0] = 1;
    int fac2 = 0, fac3 = 0, fac5 = 0; // ��һ������Ϊ2/3/5���±�

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

// ��1��Set + Heap
/* HashSet_ops -- start */

// Set��Mapʵ�֣�key=val�����ɱ�֤Ψһ��
typedef struct HashSet {
    long key;
    UT_hash_handle hh;
} HashSet;

// ��Ҫ��ָ�������Ϊ��������ֵ���������Ұָ�룡����Ϊ�������أ�
bool set_find(HashSet** set, long ikey, HashSet* psEntry) {
    HASH_FIND(hh, *set, &ikey, sizeof(ikey), psEntry);
    return psEntry != NULL; // �ǿգ��ҵ�����psEntry���޸Ĳ�����
}

bool set_add(HashSet** set, long ikey) {
    HashSet* psEntry = NULL;
    if (set_find(set, ikey, psEntry)) return false;
    // û�ҵ���psEntry == NULL���¼�entry
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
    bool (*cmp) (long, long); // ���cmp1
} MinHeap;

// ���ڸ����±������Ҫ��/���ƣ����idx����Ϊ0, �����1��
void heap_init(MinHeap* heap, int n, bool (*cmp) (long, long)) {
    heap->nums = (long*)malloc(sizeof(long) * (n + 1)); // idx��[1, n]
    heap->size = 0;
    heap->cmp = cmp;
}

// bool heap_cmp0(const void* pa, const void* pb) {
//     return *(long*)pa > *(long*)pb;
// }

// ��������С�ѣ��������У�����popС��
bool heap_cmp1(long a, long b) {
    return a > b; // �� > ��
}

void swap(long* pa, long* pb) {
    long tmp = *pa;
    *pa = *pb;
    *pb = tmp;
}

// ���ϵ��� - O(n)
void heap_offer(MinHeap* heap, long num) {
    int childIdx = ++(heap->size), fatherIdx = childIdx >> 1;
    // long child = heap->nums[childIdx], father = heap->nums[fatherIdx];
    heap->nums[childIdx] = num;
    while (fatherIdx) {
        if (heap->cmp(heap->nums[childIdx], heap->nums[fatherIdx])) { // ���cmp1
            break; // ��С��, ��>��, break
        }
        swap(&heap->nums[childIdx], &heap->nums[fatherIdx]); // ����<������swap
        childIdx = fatherIdx, fatherIdx = childIdx >> 1; // ���ϵ���
    }
}

// ���ϵ��� - O(n)
void heap_offer2(MinHeap* heap, long num) {
    int childIdx = ++(heap->size), fatherIdx = childIdx >> 1;
    // long child = &heap->nums[childIdx], father = &heap->nums[fatherIdx]; // ���������ã�
    heap->nums[childIdx] = num;
    while (fatherIdx) {
        if (heap->cmp(heap->nums[childIdx], heap->nums[fatherIdx])) { // ���cmp1
            break; // ��С��, ��>��, break
        }
        swap(&heap->nums[childIdx], &heap->nums[fatherIdx]); // ����<������swap
        childIdx = fatherIdx, fatherIdx = childIdx >> 1; // ���ϵ���
    }
}

// swap(top=1, last=n), n--, ���µ��� - O(nlogn)
void heap_poll(MinHeap* heap) {
    swap(&heap->nums[1], &heap->nums[(heap->size)--]);
    int fatherIdx = 1, childIdx = fatherIdx << 1;
    // long* child = &(heap->nums[childIdx]), * father = &(heap->nums[fatherIdx]);
    while (childIdx <= heap->size) {
        if (childIdx + 1 <= heap->size) { // ��todo��ע�⣡��
            if (heap->cmp(heap->nums[childIdx], heap->nums[childIdx + 1])) {
                childIdx++; // ���� ���� > ���ӣ���swap(��������)��
            }
        }
        if (heap->cmp(heap->nums[childIdx], heap->nums[fatherIdx])) break;
        // swap(child, father); // ������WA������������
        swap(&heap->nums[childIdx], &heap->nums[fatherIdx]); // ����<������swap
        fatherIdx = childIdx, childIdx = fatherIdx << 1; // ���µ���
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

