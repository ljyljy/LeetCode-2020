#include <stdio.h>
#include <stdlib.h>
// #include <string.h>
// #include <stdbool.h>
// #include <math.h>
// #include <limits.h>

struct TreeNode {
    int val;
    struct TreeNode* left;
    struct TreeNode* right;
};


/* q99. 恢复二叉搜索树
注意点:
- BST形状/结构不可改变，只可以交换val
- 交换的两个节点x, y，必须是逆序对（x.val > y.val）
  - x: 逆序对的较大值, 中序遍历 & 逆序对时，【首次（只变更一次val）】出现一定是较大值
  - y: 逆序对的较小值, 中序遍历 & 逆序对时，【最后（持续更新val）】出现一定是较小值
*/

typedef struct TreeNode Node;

void swap(Node* x, Node* y);

// 法1-1：dfs+空间O(n)
Node** res;
int curCnt;
Node* xx, * yy;

void dfs_inorder(Node* root);

void recoverTree(struct TreeNode* root) {
    if (!root) return;
    xx = NULL, yy = NULL;
    res = (Node**)calloc(1000, sizeof(Node*));
    curCnt = 0;

    dfs_inorder(root);

    for (int i = 0; i + 1 < curCnt; i++) {
        if (res[i]->val > res[i + 1]->val) {
            if (xx == NULL) xx = res[i]; // xx=maxVal, 位于中序列表res的首个逆序对
            yy = res[i + 1]; // 【最后（持续更新yy->val）】出现一定是较小值, yy
        }
    }

    if (xx && yy) {
        swap(xx, yy);
    }
}

void dfs_inorder(Node* root) {
    if (!root) return;
    dfs_inorder(root->left);
    res[curCnt++] = root;
    dfs_inorder(root->right);
}


// 法1-2：dfs+空间O(1)
Node* preR, * x, * y;

void dfs(Node* root);

void recoverTree(struct TreeNode* root) {
    if (!root) return;
    preR = NULL, x = NULL, y = NULL;
    dfs(root);
    if (x && y) {
        swap(x, y);
    }
}

void dfs(Node* root) {

    if (!root) return;

    // if (preR) {
    //     printf("1) root->val=%d, preR->val=%d\n", root->val, preR->val);
    // } else {
    //     printf("1) root->val=%d, preR == NULL\n", root->val);
    // }

    // 中序遍历（左[中]右），升序
    dfs(root->left);
    if (preR) { // 真根跳过
        if (preR->val > root->val) {
            y = root;
            if (x == NULL) {
                x = preR;
            } // 若发现逆序对，更新[x=pre（只更新首次）, y=cur（第二次）]
        }
    }
    // if (x && y) printf("2) root->val=%d, preR->val=%d, x->val=%d, y->val=%d\n", root->val, preR->val, x->val, y->val);
    preR = root;
    dfs(root->right);
}

void swap(Node* x, Node* y) {
    int tmp = x->val;
    x->val = y->val;
    y->val = tmp;
}

int main()
{
    Node* root = (Node*)malloc(sizeof(Node));
    root->val = 1;
    root->left = (Node*)malloc(sizeof(Node));
    root->left->val = 3;
    root->left->left = NULL;
    root->left->right = NULL;
    root->right = (Node*)malloc(sizeof(Node));
    root->right->val = 2;
    root->right->left = NULL;
    root->right->right = NULL;
    recoverTree(root);
    return 0;
}
/**
[1,3,null,null,2] --> [3,1,null,null,2]

1) root->val=1, preR == NULL
1) root->val=3, preR == NULL
1) root->val=2, preR->val=3
2) root->val=2, preR->val=3, x->val=3, y->val=2
2) root->val=1, preR->val=2, x->val=3, y->val=1
*/