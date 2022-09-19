#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
// #include <limits.h>
// #include <stdbool.h>
#include "TreeNode.h"


/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */


 // ��������㷨��DFS��
 // - д��1��Ԥ�����ڴ�ռ�1001
void dfs(struct TreeNode* root, int curCnt, int* path, char** res, int* returnSize);

char** binaryTreePaths(struct TreeNode* root, int* returnSize) {
    // basicSize = 1001, curCnt = 0;
    int path[1001]; // char* path = (char*)malloc(sizeof(char) * basicSize);
    char** res = (char**)malloc(sizeof(char*) * 1001); // List<String> res = new ArrayList<>();
    *returnSize = 0; // ������ֵ��
    dfs(root, 0, path, res, returnSize);
    return res;
}

void dfs(struct TreeNode* root, int curCnt, int* path, char** res, int* returnSize) {
    if (!root) return;
    if (!root->left && !root->right) { // Ҷ��
        char* newPath = (char*)malloc(sizeof(char) * 1001);
        int len = 0;
        for (int i = 0; i < curCnt; i++) {
            // [����] ������/�ַ��������һϵ�д� ��->�� ���ַ���(���Ǵ�ӡ����Ļ)
            //sprintf����: ��1��������Ŀ���ַ����ĵ�ַ�����������printf()��ͬ��
                //������سɹ����򷵻�д����ַ������������������ַ���׷�����ַ���ĩβ�Ŀ��ַ�(\0)��
                //�������ʧ�ܣ��򷵻�һ��������
                //tmp + len��Ŀ���ַ����ĵ�ַ��ָ������ƶ�
                //path[i]������� %d ����ֵ�õ�
            len += sprintf(newPath + len, "%d->", path[i]);
        }
        sprintf(newPath + len, "%d", root->val);
        res[(*returnSize)++] = newPath;
        // ���״�curCnt: path�ĳ��ȣ�*returnSize = res�ĳ��ȣ�
        // res[curCnt++] = newPath; // ��������path�е�idx��res��idx��
        // *returnSize = curCnt;
        return;
    }
    // ��Ҷ��
    path[curCnt++] = root->val;
    dfs(root->left, curCnt, path, res, returnSize);
    dfs(root->right, curCnt, path, res, returnSize);
}

// - д��2����̬�ڴ���䡾WA����
// int basicSize = 8, curCnt = 0;
// dfs2(struct TreeNode* root, int curCnt, int* path, char** res, int* returnSize);
// char** binaryTreePaths(struct TreeNode* root, int* returnSize) {
//     basicSize = 8, curCnt = 0;
//     // int path[1001]; // String path = "";
//     int* path = (int*)malloc(sizeof(char) * basicSize);
//     char** res = (char**)malloc(sizeof(char*) * basicSize); // List<String> res = new ArrayList<>();
//     *returnSize = 0;
//     dfs2(root, 0, path, res, returnSize);
//     return res;
// }

// // curCnt: path�ĳ��ȣ�*returnSize = res�ĳ��ȣ�
// void dfs2(struct TreeNode* root, int curCnt, int* path, char** res, int* returnSize) {
//     if (!root) return;

//     if (!root->left && !root->right) { // Ҷ��
//         // ����·����root->...->Ҷ�ӣ�by sprintf��
//         char* newPath = (char*)malloc(1001); // ������path Ԥ����һ�£�ʡ������������
//         int len = 0;
//         for (int i = 0; i < curCnt; i++) {
//             len += sprintf(newPath + len, "%d->", path[i]);
//         }
//         sprintf(newPath + len, "%d", root->val);

//         if ((*returnSize) == basicSize) {
//             basicSize *= 2;
//             res = (char**)realloc(res, sizeof(char*) * basicSize);
//         }
//         res[(*returnSize)++] = newPath;
//         return;
//     }
//     path[curCnt++] = root->val;
//     dfs2(root->left, curCnt, path, res, returnSize);
//     dfs2(root->right, curCnt, path, res, returnSize);
// }