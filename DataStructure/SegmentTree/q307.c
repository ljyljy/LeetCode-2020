#include <stdio.h>
#include <stdlib.h>
// #include <string.h>
// #include <stdbool.h>
// #include <math.h>
// #include <limits.h>

// q307v2. ����ͼ��� - ������޸�(�߶���)
typedef struct SegmentTreeNode {
    int start, end, sum;
    struct SegmentTreeNode* left;
    struct SegmentTreeNode* right;
} SegmentTreeNode;

typedef struct {
    SegmentTreeNode* root;
} NumArray;

SegmentTreeNode* ST_build(int* nums, int start, int end);
void ST_update(SegmentTreeNode* root, int index, int val);
int ST_query(SegmentTreeNode* root, int start, int end);

NumArray* numArrayCreate(int* nums, int n) {
    NumArray* obj = (NumArray*)calloc(1, sizeof(NumArray));
    obj->root = ST_build(nums, 0, n - 1);
    return obj;
}

void numArrayUpdate(NumArray* obj, int index, int val) {
    ST_update(obj->root, index, val);
}

int numArraySumRange(NumArray* obj, int left, int right) {
    return ST_query(obj->root, left, right); // ����
}

void numArrayFree(NumArray* obj) {
    if (obj->root->left) free(obj->root->left);
    if (obj->root->right) free(obj->root->right);
    free(obj->root);
    free(obj);
}

SegmentTreeNode* ST_build(int* nums, int start, int end) {// ���� & ����
    if (start > end) return NULL;

    SegmentTreeNode* root = (SegmentTreeNode*)calloc(1, sizeof(SegmentTreeNode));
    root->start = start, root->end = end;

    if (start == end) {
        root->sum = nums[start];
        return root;
    }

    int mid = start + end >> 1;
    root->left = ST_build(nums, start, mid);
    root->right = ST_build(nums, mid + 1, end);
    root->sum = root->left->sum + root->right->sum;
    return root;
}

void ST_update(SegmentTreeNode* root, int index, int val) { // ���� & ����
    if (root->start == index && root->end == index) {
        root->sum = val;
        return;
    }
    // ����index��ֵ���жϸ�����������������������󣬸��¸��ڵ�root->sum
    int mid = root->start + root->end >> 1; // root->start + (root->end - root->start) / 2;
    if (index <= mid) { // ֻ�����������
        ST_update(root->left, index, val);
    }
    else {
        ST_update(root->right, index, val);
    }
    root->sum = root->left->sum + root->right->sum; // ���¸��ڵ㡾��������
}

int ST_query(SegmentTreeNode* root, int start, int end) {// ����
    if (start > end) return 0;
    if (root->start > end || root->end < start) return 0;
    if (start <= root->start && root->end <= end) return root->sum;

    int sumL = ST_query(root->left, start, end);
    int sumR = ST_query(root->right, start, end);
    return sumL + sumR;
}


int main()
{
    int nums[] = { 1, 3, 5 };
    int numsSize = sizeof(nums) / sizeof(nums[0]);
    NumArray* obj = numArrayCreate(nums, numsSize);
    int res1 = numArraySumRange(obj, 0, 2);
    printf("%d\n", res1); // 9
    numArrayUpdate(obj, 1, 2);
    int res2 = numArraySumRange(obj, 0, 2);
    printf("%d\n", res2); // 8
}
