#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <limits.h>

//#include "TreeNode.h"


struct TreeNode { // ���淶����typedef
    int val;
    struct TreeNode* left;
    struct TreeNode* right;
};


int maxCnt = 1;

int diameterOfBinaryTree(struct TreeNode* root) {
    if (!root) return 0;
    maxCnt = 1; // LC�������⣺ȫ�ֱ���������������³�ʼ����
    dfs(root);
    return maxCnt - 1;
}
// ��¼�ڵ���
int dfs(struct TreeNode* root) {
    if (!root) return 0;
    int left = dfs(root->left);
    int right = dfs(root->right);
    maxCnt = fmax(maxCnt, left + right + 1);
    return fmax(left, right) + 1;
}