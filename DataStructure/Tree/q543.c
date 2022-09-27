#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <limits.h>

//#include "TreeNode.h"


struct TreeNode { // 不规范，加typedef
    int val;
    struct TreeNode* left;
    struct TreeNode* right;
};


int maxCnt = 1;

int diameterOfBinaryTree(struct TreeNode* root) {
    if (!root) return 0;
    maxCnt = 1; // LC判题问题：全局变量必须在入口重新初始化！
    dfs(root);
    return maxCnt - 1;
}
// 记录节点数
int dfs(struct TreeNode* root) {
    if (!root) return 0;
    int left = dfs(root->left);
    int right = dfs(root->right);
    maxCnt = fmax(maxCnt, left + right + 1);
    return fmax(left, right) + 1;
}