#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
// #include <limits.h>
// #include <stdbool.h>

// ↓ C编程规范【推荐】
// typedef struct TreeNode
// {
//     int val;
//     TreeNode* left;
//     TreeNode* right;
// } TreeNode;

// ↓ LeetCode 定义【不推荐】
struct TreeNode
{
    int val;
    struct TreeNode* left;
    struct TreeNode* right;
};
