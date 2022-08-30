#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>

/**
* 坑：3'
    1. 回溯中的path，动态增删：记录下标pathLen
    2. res动态扩容！ 动态malloc & realloc！
    3. 全局变量初始化：必须在入口函数处初始化！否则会影响LC判题的其他case！
*/

char* map[10] = { "", "", "abc", "def", "ghi", "jkl", \
                  "mno", "pqrs", "tuv", "wxyz" };

char** res;
char* path;
int basicSize;
int curCnt, pathLen; // path回溯需记录下标！（必须在入口函数初始化！否则会覆盖LC后续cases！）
int n;

void dfs(char* digits, int idx) {
    if (strlen(path) == strlen(digits)) {
        res[curCnt] = (char*)malloc(sizeof(char) * (strlen(path) + 1));
        strcpy(res[curCnt++], path);
        // printf("path: %s\n", path);
        if (curCnt == basicSize) { // res动态扩容！
            basicSize *= 2;
            res = (char**)realloc(res, sizeof(char*) * basicSize);
        }
        return;
    }

    char* letters = map[digits[idx] - '0']; // 候选字母
    // printf("digits[idx]=%c, letters: %s\n", digits[idx], letters);
    for (int i = 0; i < strlen(letters); i++) {
        // path动态增删！回溯需要记录pathLen下标！
        path[pathLen++] = letters[i]; path[pathLen] = 0;
        dfs(digits, idx + 1);
        path[--pathLen] = 0;
    }

}
char** letterCombinations(char* digits, int* returnSize) {
    if (!digits || strlen(digits) == 0) {
        *returnSize = 0;
        return res;
    }
    n = strlen(digits);
    curCnt = 0, pathLen = 0, basicSize = 8;

    res = (char**)malloc(sizeof(char*) * basicSize); // 动态扩容，防止堆溢出！
    path = (char*)malloc(sizeof(char) * (n + 1));
    memset(path, 0, sizeof(char) * (n + 1));
    // for (int i = 0; i < basicSize; i++) { // res[i]需要动态扩容！
    //     res[i] = (char*)malloc(sizeof(char) * n); // res[i]长度=n
    //     memset(res[i], 0, sizeof(char) * n);
    // }
    dfs(digits, 0);
    *returnSize = curCnt;
    return res;
}

int main() {
    // // 字符串拼接
    // char* digits = "23"; char* digits2 = "56789";
    // char* tmp = (char*)malloc(sizeof(char) * (strlen(digits) + strlen(digits2) + 1));
    // memset(tmp, 0, sizeof(char) * (strlen(digits) + strlen(digits2) + 1));
    // strcat(tmp, digits); strcat(tmp, digits2);
    // printf("tmp=%s\n", tmp);

    char* digits = "23";
    int* returnSize = (int*)malloc(sizeof(int) * 1);
    memset(returnSize, 0, sizeof(int));
    char** res = letterCombinations(digits, returnSize);
    for (int i = 0; i < *returnSize; i++) {
        printf("%s\n", res[i]);
    }

}


// 错误版本：path没有回溯（动态增删）
// void dfs_heapOverFlow(char* digits, int idx, char* path) {
//     if (strlen(path) == strlen(digits)) {
//         res[curCnt] = (char*)malloc(sizeof(char) * (strlen(path) + 1));
//         strcpy(res[curCnt++], path);
//         // printf("path: %s\n", path);
//         if (curCnt == basicSize) { // res动态扩容！
//             basicSize *= 2;
//             res = (char**)realloc(res, sizeof(char*) * basicSize);
//         }
//         return;
//     }

//     char* letters = map[digits[idx] - '0']; // 候选字母
//     // printf("digits[idx]=%c, letters: %s\n", digits[idx], letters);
//     for (int i = 0; i < strlen(letters); i++) {
//         //【坑】 newPath = path + letters[i]
//      错：回溯中的path追加（无法realloc，只能malloc newPath）：strcpy/strcat + newPath[-1]赋值char + 末尾0
//      path需要回溯，需要动态增删！ ↑ 无法做到！
//         // 法1：strcat / strcpy
//         // char* tmp = malloc(sizeof(char) * 2); // 构造字符串"letters[i] + \0"
//         // tmp[0] = letters[i], tmp[1] = 0;
//         // char* newPath = (char*)malloc(sizeof(char) * (strlen(path) + strlen(tmp) + 1));
//         // memset(newPath, 0, sizeof(newPath));
//         // strcat(newPath, path); strcat(newPath, tmp);

//         //// 法2：strcpy + 偏移末位赋值char
//         char* newPath = (char*)malloc(sizeof(char) * (strlen(path) + 1 + 1)); // 1=strlen(letters[i])
//         memset(newPath, 0, sizeof(newPath)); // 末尾为\0
//         strcpy(newPath, path);
//         newPath[strlen(path)] = letters[i]; // 字符转字符串
//         dfs0(digits, idx + 1, newPath);
//     }

// }
