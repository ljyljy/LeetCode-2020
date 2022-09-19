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


 // 深度优先算法（DFS）
 // - 写法1：预分配内存空间1001
void dfs(struct TreeNode* root, int curCnt, int* path, char** res, int* returnSize);

char** binaryTreePaths(struct TreeNode* root, int* returnSize) {
    // basicSize = 1001, curCnt = 0;
    int path[1001]; // char* path = (char*)malloc(sizeof(char) * basicSize);
    char** res = (char**)malloc(sizeof(char*) * 1001); // List<String> res = new ArrayList<>();
    *returnSize = 0; // 勿忘赋值！
    dfs(root, 0, path, res, returnSize);
    return res;
}

void dfs(struct TreeNode* root, int curCnt, int* path, char** res, int* returnSize) {
    if (!root) return;
    if (!root->left && !root->right) { // 叶子
        char* newPath = (char*)malloc(sizeof(char) * 1001);
        int len = 0;
        for (int i = 0; i < curCnt; i++) {
            // [需求] 向数组/字符串中输出一系列带 “->” 的字符串(而非打印在屏幕)
            //sprintf函数: 第1个参数是目标字符串的地址。其余参数和printf()相同。
                //如果返回成功，则返回写入的字符总数，【不包括】字符串追加在字符串末尾的空字符(\0)。
                //如果返回失败，则返回一个负数。
                //tmp + len，目标字符串的地址，指针向后移动
                //path[i]，是填充 %d 处的值用的
            len += sprintf(newPath + len, "%d->", path[i]);
        }
        sprintf(newPath + len, "%d", root->val);
        res[(*returnSize)++] = newPath;
        // 【易错】curCnt: path的长度，*returnSize = res的长度！
        // res[curCnt++] = newPath; // 错！混淆了path中的idx与res的idx！
        // *returnSize = curCnt;
        return;
    }
    // 非叶子
    path[curCnt++] = root->val;
    dfs(root->left, curCnt, path, res, returnSize);
    dfs(root->right, curCnt, path, res, returnSize);
}

// - 写法2：动态内存分配【WA！】
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

// // curCnt: path的长度，*returnSize = res的长度！
// void dfs2(struct TreeNode* root, int curCnt, int* path, char** res, int* returnSize) {
//     if (!root) return;

//     if (!root->left && !root->right) { // 叶子
//         // 生成路径：root->...->叶子（by sprintf）
//         char* newPath = (char*)malloc(1001); // 单独的path 预分配一下，省得另外扩容了
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