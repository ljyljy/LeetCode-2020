#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <limits.h>

struct Node { // 荐 typedef
    int val;
    int numChildren;
    struct Node** children; // 【二级结构指针！】
};

int g_maxCnt = 1;

int dfs(struct Node* root);

int diameter(struct Node* root) {
    if (!root) return 0;
    g_maxCnt = 1; // LC判题
    dfs(root);
    return g_maxCnt - 1;
}

int dfs(struct Node* root) {
    if (!root) return 0;
    int maxDepth1 = 0, maxDepth2 = 0; // 最长，次长
    for (int i = 0; i < root->numChildren; i++) {
        int curDepth = dfs(root->children[i]);
        if (curDepth > maxDepth1) {
            maxDepth2 = maxDepth1;
            maxDepth1 = curDepth;
        }
        else if (curDepth > maxDepth2) {
            maxDepth2 = curDepth;
        }
    }
    // 全局最大节点数=双边节点数=最长孩子+次长孩子+自己
    g_maxCnt = fmax(g_maxCnt, maxDepth1 + maxDepth2 + 1);
    return maxDepth1 + 1;
}
