#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
// #include <string.h>
// #include <math.h>
// #include <limits.h>

// q341.c
/**
 * *********************************************************************
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * *********************************************************************
 *
 * // Return true if this NestedInteger holds a single integer, rather than a nested list.
 * bool NestedIntegerIsInteger(struct NestedInteger *);
 *
 * // Return the single integer that this NestedInteger holds, if it holds a single integer
 * // The result is undefined if this NestedInteger holds a nested list
 * int NestedIntegerGetInteger(struct NestedInteger *);
 *
 * // Return the nested list that this NestedInteger holds, if it holds a nested list
 * // The result is undefined if this NestedInteger holds a single integer
 * struct NestedInteger **NestedIntegerGetList(struct NestedInteger *);
 *
 * // Return the nested list's size that this NestedInteger holds, if it holds a nested list
 * // The result is undefined if this NestedInteger holds a single integer
 * int NestedIntegerGetListSize(struct NestedInteger *);
 * };
 */
struct NestedInteger** NestedIntegerGetList(struct NestedInteger*);
bool NestedIntegerIsInteger(struct NestedInteger*);
int NestedIntegerGetInteger(struct NestedInteger*);
int NestedIntegerGetListSize(struct NestedInteger*);

// 分析:
// nestedList = [[1,1],2,[1,1]]，即嵌套列表
// nestedList中每个元素都是一个结构体NestedInteger，它有两种类型：
// 1. NestedInteger是一个整数，即NestedIntegerIsInteger()返回true、NestedIntegerGetInteger返回具体整数
// 2. NestedInteger是一个列表，即NestedIntegerIsInteger()返回false、NestedIntegerGetList返回嵌套列表、NestedIntegerGetListSize返回嵌套列表的长度
// 本题要求将嵌套列表展开为一个一维列表，即将嵌套列表中的所有整数按顺序放入一个一维列表中
// 本题的难点在于，嵌套列表中的每个元素都可能是一个嵌套列表，即嵌套列表中的元素可能是整数，也可能是嵌套列表
// 【递归】，即将嵌套列表中的【每个元素都看作是一个嵌套列表】，然后递归地将嵌套列表中的每个元素都看作是一个嵌套列表，直到【嵌套列表中的元素都是整数】为止，即NestedIntegerIsInteger()返回true

// 法1：递归
typedef struct NestedIterator {
    int* arr;
    int size;
    int idx;
} NestedIterator;

void dfs(NestedIterator* iter,
    struct NestedInteger** nestedList, int nestedListSize) {
    for (int i = 0; i < nestedListSize; i++) {
        struct NestedInteger* curList = nestedList[i];
        if (NestedIntegerIsInteger(curList)) {
            iter->arr[iter->size++] = NestedIntegerGetInteger(curList);
        } // ↑ 不是idx++，而是size++（容量+1）
        else { // 参数类型：↓ 不可直接写curList，需要NestedIntegerGetList获取其嵌套列表
            dfs(iter, NestedIntegerGetList(curList), NestedIntegerGetListSize(curList));
        }
    }
}

struct NestedIterator* nestedIterCreate(
    struct NestedInteger** nestedList, int nestedListSize) {
    NestedIterator* iter = (NestedIterator*)calloc(1, sizeof(NestedIterator));
    iter->arr = (int*)calloc(30000, sizeof(int));
    iter->size = 0;
    iter->idx = 0;
    dfs(iter, nestedList, nestedListSize);
    return iter;
}

bool nestedIterHasNext(struct NestedIterator* iter) {
    return iter->idx != iter->size;
}

int nestedIterNext(struct NestedIterator* iter) {
    return iter->arr[iter->idx++];
}

/** Deallocates memory previously allocated for the iterator */
void nestedIterFree(struct NestedIterator* iter) {
    if (iter->arr) free(iter->arr);
    free(iter);
}

/**
 * Your NestedIterator will be called like this:
 * struct NestedIterator *iter = nestedIterCreate(nestedList, nestedListSize);
 * while (nestedIterHasNext(iter)) printf("%d\n", nestedIterNext(iter));
 * nestedIterFree(iter);
 */

