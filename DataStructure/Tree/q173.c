#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
// #include <math.h>
// #include <limits.h>

struct TreeNode {
    int val;
    struct TreeNode* left;
    struct TreeNode* right;
};

// q173. ����������������
#define MAX_LEN 100001

typedef struct TreeNode Node;
Node nullNode = { -1, NULL, NULL }; // ��������ڱ���ѹջ���� - �� & null - ��

typedef struct {
    Node* stk[MAX_LEN]; // ���⣺�ڵ���<=10^5
    int top;
} BSTIterator;


// ���⣺����root�����ΰ��������˳���ӡ���ǵݹ飩
BSTIterator* bSTIteratorCreate(struct TreeNode* root) {
    BSTIterator* obj = (BSTIterator*)calloc(1, sizeof(BSTIterator));
    // obj->top = 0;
    obj->stk[obj->top++] = root;

    while (obj->top > 0) {
        Node* cur = obj->stk[--obj->top];
        if (cur->val != -1) { // ѹջ���� - �� & null - ��
            if (cur->right) obj->stk[obj->top++] = cur->right;
            obj->stk[obj->top++] = cur;
            obj->stk[obj->top++] = &nullNode;
            if (cur->left) obj->stk[obj->top++] = cur->left;
        }
        else { // cur/ջ��Ϊnull������һ��/��ջ��Ϊ�����У�, ����Next()�з���
            break; // ��ͣ��Next()�м���
        }
    }
    return obj;
}

int bSTIteratorNext(BSTIterator* obj) {
    int rootVal = obj->stk[--obj->top]->val; // ��/����PEEK��
    // �Ӹ��ġ���һ���ڵ㡿��ʼ������ѹջ
    while (obj->top > 0) {
        Node* cur = obj->stk[--obj->top];
        if (cur->val != -1) { // ѹջ���� - �� & null - ��
            if (cur->right) obj->stk[obj->top++] = cur->right;
            obj->stk[obj->top++] = cur;
            obj->stk[obj->top++] = &nullNode;
            if (cur->left) obj->stk[obj->top++] = cur->left;
        }
        else { // cur/ջ��Ϊnull������һ��/��ջ��Ϊ�����У�, ����Next()�з���
            break; // ��ͣ��Next()�м���
        }
    }
    return rootVal;
}

bool bSTIteratorHasNext(BSTIterator* obj) {
    return obj->top > 0;
}

void bSTIteratorFree(BSTIterator* obj) {
    free(obj);
}

int main()
{
    int nums[] = { 7, 3, 15, -1, -1, 9, 20 };
    int numsSize = sizeof(nums) / sizeof(nums[0]);
    Node* root = (Node*)calloc(numsSize, sizeof(Node));
    for (int i = 0; i < numsSize; i++) {
        root[i].val = nums[i];
        if (2 * i + 1 < numsSize) {
            if (nums[2 * i + 1] == -1) {
                root[i].left = NULL;
            }
            else {
                root[i].left = &root[2 * i + 1];
            }
        }
        if (2 * i + 2 < numsSize) {
            if (nums[2 * i + 2] == -1) {
                root[i].right = NULL;
            }
            else {
                root[i].right = &root[2 * i + 2];
            }
        }
    }

    BSTIterator* obj = bSTIteratorCreate(root);
    int res1 = bSTIteratorNext(obj);
    int res2 = bSTIteratorNext(obj);
    bool res3 = bSTIteratorHasNext(obj);
    int res4 = bSTIteratorNext(obj);
    bool res5 = bSTIteratorHasNext(obj);
    int res6 = bSTIteratorNext(obj);
    bool res7 = bSTIteratorHasNext(obj);
    int res8 = bSTIteratorNext(obj);
    bool res9 = bSTIteratorHasNext(obj);

    printf("%d %d %d %d %d %d %d %d %d\n", res1, res2, res3, res4, res5, res6, res7, res8, res9);
    // 3 7 true 9 true 15 true 20 false
}