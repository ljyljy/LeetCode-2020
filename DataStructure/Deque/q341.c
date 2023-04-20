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

// ����:
// nestedList = [[1,1],2,[1,1]]����Ƕ���б�
// nestedList��ÿ��Ԫ�ض���һ���ṹ��NestedInteger�������������ͣ�
// 1. NestedInteger��һ����������NestedIntegerIsInteger()����true��NestedIntegerGetInteger���ؾ�������
// 2. NestedInteger��һ���б���NestedIntegerIsInteger()����false��NestedIntegerGetList����Ƕ���б�NestedIntegerGetListSize����Ƕ���б�ĳ���
// ����Ҫ��Ƕ���б�չ��Ϊһ��һά�б�����Ƕ���б��е�����������˳�����һ��һά�б���
// ������ѵ����ڣ�Ƕ���б��е�ÿ��Ԫ�ض�������һ��Ƕ���б���Ƕ���б��е�Ԫ�ؿ�����������Ҳ������Ƕ���б�
// ���ݹ顿������Ƕ���б��еġ�ÿ��Ԫ�ض�������һ��Ƕ���б���Ȼ��ݹ�ؽ�Ƕ���б��е�ÿ��Ԫ�ض�������һ��Ƕ���б�ֱ����Ƕ���б��е�Ԫ�ض���������Ϊֹ����NestedIntegerIsInteger()����true

// ��1���ݹ�
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
        } // �� ����idx++������size++������+1��
        else { // �������ͣ��� ����ֱ��дcurList����ҪNestedIntegerGetList��ȡ��Ƕ���б�
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

