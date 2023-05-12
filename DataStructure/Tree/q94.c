#include <stdio.h>
#include <stdlib.h>
// #include <string.h>
// #include <limits.h>
// #include <stdbool.h>


// typedef struct TreeNode_s {
//     int val;
//     struct TreeNode_s* left;
//     struct TreeNode_s* right;
// } TreeNode;


// q94. 二叉树的中序遍历
struct TreeNode {
    int val;
    struct TreeNode* left;
    struct TreeNode* right;
};

typedef struct TreeNode Node;

// 法1：递归 - 自动扩容（通用）
int basicSize, curCnt;

void helper(Node* root, int* res);

int* inorderTraversal(struct TreeNode* root, int* returnSize) {
    basicSize = 8, curCnt = 0;
    int* res = (int*)calloc(basicSize, sizeof(int));
    helper(root, res);
    *returnSize = curCnt;
    return res;
}

void helper(Node* root, int* res) {
    if (!root) return;
    if (root->left) helper(root->left, res);
    res[curCnt++] = root->val;
    if (curCnt == basicSize) {
        basicSize *= 2;
        res = (int*)realloc(res, basicSize * sizeof(int));
    }
    if (root->right) helper(root->right, res);
}

// // 【大一统迭代(推荐模板)】
// 法2-1：：?迭代写法 - 中序(左中右) - 【压栈: 右 - 中 & null - 左】
Node nullNode = { -101, NULL }; // 非法值：-101
int* inorderTraversal_stk1(struct TreeNode* root, int* returnSize) {
    int* res = (int*)calloc(101, sizeof(int)), curCnt = 0;;
    if (!root) {
        *returnSize = 0;
        return res;
    }
    Node* stk[101] = { 0 }; // 由题，节点数<=100.（其他写法：自动扩容，略）
    int top = 0;
    stk[top++] = root;
    while (top > 0) {
        Node* cur = stk[--top];
        if (cur->val != -101) { // 非空节点
            if (cur->right) stk[top++] = cur->right;
            stk[top++] = cur;
            stk[top++] = &nullNode; // 利用nullNode，标记根
            if (cur->left) stk[top++] = cur->left;
        }
        else { // cur为空节点，接下来PEEK一定为根节点
            res[curCnt++] = stk[--top]->val; // 根节点（pop）
        }
    }
    *returnSize = curCnt;
    return res;
}

// 法2-2：非递归，栈
int* inorderTraversal_stk2(struct TreeNode* root, int* returnSize) {
    int* res = (int*)malloc(sizeof(int) * 100);
    *returnSize = 0;
    Node* stack[100] = { 0 };
    int top = -1;
    Node* cur = root;
    while (cur || top != -1) {
        while (cur) {
            stack[++top] = cur;
            cur = cur->left;
        }
        cur = stack[top--];
        res[(*returnSize)++] = cur->val;
        cur = cur->right;
    }
    return res;
}

int main()
{
    int nums[] = { 1, 2, 3, 4, 5, 6, 7 };
    int len = sizeof(nums) / sizeof(nums[0]);
    Node* root = (Node*)malloc(sizeof(Node) * len);
    for (int i = 0; i < len; i++) {
        root[i].val = nums[i];
        root[i].left = NULL;
        root[i].right = NULL;
    }
    for (int i = 0; i < len; i++) {
        if (2 * i + 1 < len) root[i].left = &root[2 * i + 1];
        if (2 * i + 2 < len) root[i].right = &root[2 * i + 2];
    }

    int returnSize = 0;
    // int* res = inorderTraversal(root, &returnSize);
    int* res = inorderTraversal_stk1(root, &returnSize);
    for (int i = 0; i < returnSize; i++) {
        printf("%d ", res[i]); // [4,2,5,1,6,3,7]
    }
    printf("\n");
    return 0;
}