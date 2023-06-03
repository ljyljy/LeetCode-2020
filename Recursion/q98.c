#include <stdio.h>
#include <stdlib.h>
// #include <string.h>
#include <stdbool.h>
// #include <math.h>
#include <limits.h>

struct TreeNode {
    int val;
    struct TreeNode* left;
    struct TreeNode* right;
};

typedef struct TreeNode Node;

bool dfs(Node* root, long min, long max);

bool isValidBST(struct TreeNode* root) {
    return dfs(root, LONG_MIN, LONG_MAX);
    // root值可能取到INT_MIN/MAX, 但dfs中途必须确立严格的上下边界（不包含），故使用LONG_MIN/MAX
}

bool dfs(Node* root, long min, long max) {
    if (!root) return true; // 下探到叶子以下，向上回溯（T）
    bool left = dfs(root->left, min, root->val); // 左 (当前: 左 < 中)
    if (!left) return false;
    if (min >= root->val || root->val >= max) {
        return false;
    }
    bool right = dfs(root->right, root->val, max); // 右 (当前：中 < 右)
    return left && right;
}


// WA: 节点的左子树【只包含】 小于 【当前节点root】的数
//    --> 错误原因：root.val必须大于其右子树的所有数值！该条件未考虑！（本解法只考虑了上下两层的大小关系）
bool isValidBST_WA(struct TreeNode* root) {
    bool res = true;
    if (root) {
        if (root->left) {
            res &= root->val > root->left->val;
            res &= isValidBST(root->left);
        }
        if (!res) return false;
        if (root->right) {
            res &= root->val < root->right->val;
            res &= isValidBST(root->right);
        }
        if (!res) return false;
    }
    else {
        return false;
    }
    return res;
}

int main()
{
    Node* root = (Node*)malloc(sizeof(Node));
    root->val = 2;
    root->left = (Node*)malloc(sizeof(Node));
    root->left->val = 1;
    root->left->left = NULL;
    root->left->right = NULL;
    root->right = (Node*)malloc(sizeof(Node));
    root->right->val = 3;
    root->right->left = NULL;
    root->right->right = NULL;
    printf("res = %d\n", isValidBST(root));
    return 0;
}