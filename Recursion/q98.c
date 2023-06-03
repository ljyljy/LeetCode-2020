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
    // rootֵ����ȡ��INT_MIN/MAX, ��dfs��;����ȷ���ϸ�����±߽磨������������ʹ��LONG_MIN/MAX
}

bool dfs(Node* root, long min, long max) {
    if (!root) return true; // ��̽��Ҷ�����£����ϻ��ݣ�T��
    bool left = dfs(root->left, min, root->val); // �� (��ǰ: �� < ��)
    if (!left) return false;
    if (min >= root->val || root->val >= max) {
        return false;
    }
    bool right = dfs(root->right, root->val, max); // �� (��ǰ���� < ��)
    return left && right;
}


// WA: �ڵ����������ֻ������ С�� ����ǰ�ڵ�root������
//    --> ����ԭ��root.val�����������������������ֵ��������δ���ǣ������ֻⷨ��������������Ĵ�С��ϵ��
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