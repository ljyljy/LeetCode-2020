#include <stdio.h>
#include <stdlib.h>
// #include <string.h>
// #include <limits.h>
#include <stdbool.h>

// q112. 路径总和
struct TreeNode {
    int val;
    struct TreeNode* left;
    struct TreeNode* right;
};

typedef struct TreeNode Node;

bool helper(Node* root, int targetSum);

bool hasPathSum(struct TreeNode* root, int targetSum) {
    if (!root) return false;
    return helper(root, targetSum - root->val);
}

bool helper(Node* root, int targetSum) {
    // if (!root) return false;
    // if (targetSum < 0) return false; // 由题：结点val、targetSum都可能为负数
    if (!root->left && !root->right && targetSum == 0) return true; // 叶子root && 和减至0
    bool res1 = false, res2 = false;
    if (root->left) res1 |= helper(root->left, targetSum - root->left->val);
    if (root->right) res2 |= helper(root->right, targetSum - root->right->val);
    return res1 || res2;
}

int main()
{
    int nums[] = { 1,2,3,4 };
    int targetSum = 7;
    Node* root = (Node*)calloc(1, sizeof(Node)); // calloc: 初始化为0，即左右子树指针为NULL
    root->val = nums[0];

    Node* node1 = (Node*)calloc(1, sizeof(Node));
    node1->val = nums[1];
    root->left = node1;

    Node* node2 = (Node*)calloc(1, sizeof(Node));
    node2->val = nums[2];
    root->right = node2;

    Node* node3 = (Node*)calloc(1, sizeof(Node));
    node3->val = nums[3];
    node1->left = node3;

    printf("%d\n", hasPathSum(root, targetSum)); // true
    return 0;
}