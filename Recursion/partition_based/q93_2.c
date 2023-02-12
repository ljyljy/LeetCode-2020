#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <math.h>
#include <string.h>
#include <limits.h>


void dfs(char* s, int len, int idx, int validCnt, int* path, char** res, int* returnSize);
void makeIP(int* path, char* newIP); // char*返回值放到函数参数中，不要直接函数返回，无法free
// bool isValid(char* s, int len, int start, int end);
bool isValid(char* curSeg);


char** restoreIpAddresses(char* s, int* returnSize) {
    int len = strlen(s);
    *returnSize = 0;
    if (len < 4 || len > 12) {
        return NULL;
    }
    char** res = (char**)calloc(3000, sizeof(char*));
    // char* path = (char*)calloc(len + 4, sizeof(char)); // len + 3个'.' + '\0'
    int path[4] = { -1 }; // 单条IP
    dfs(s, len, 0, 0, path, res, returnSize);
    return res;
}

// res.idx = *returnSize; path.idx = validCnt++
void dfs(char* s, int len, int idx, int validCnt, int* path, char** res, int* returnSize) {
    if (idx == len) {
        if (validCnt == 4) {
            // 占位最长：4个int + 3个'.' + '\0' = 4*(int + char)
            char* newIP = (char*)calloc(4, sizeof(int) + sizeof(char));
            makeIP(path, newIP);
            res[*returnSize] = (char*)calloc(4, sizeof(int) + sizeof(char)); // 必须malloc！
            strcpy(res[(*returnSize)++], newIP); // 不可以str直接复赋值！！！否则LC堆溢出！
            free(newIP); newIP = NULL;
            // printf("idx = %d, validCnt = %d, res[(*returnSize)] = %s\n", idx, validCnt, res[(*returnSize)]);
        }
        return;
    }
    int remainLen = len - idx, remainValid = 4 - validCnt;
    if (remainLen < remainValid || remainLen > remainValid * 3) return;

    for (int i = idx; i < fmin(len, idx + 3); i++) { // 限制长度<=3位数
        char* curSeg = (char*)calloc(4, sizeof(char)); // s[idx, i], 如：255\0
        strncpy(curSeg, s + idx, i - idx + 1);
        int curVal = atoi(curSeg);
        // printf("str: %s, int: %d\n", curSeg, curVal);
        if (isValid(curSeg)) {
            path[validCnt] = curVal;
            dfs(s, len, i + 1, validCnt + 1, path, res, returnSize);
            path[validCnt] = -1;
        }
        free(curSeg); curSeg = NULL;
    }
}

// 举例：int path[4] = { 255, 255, 11, 35 };
void makeIP(int* path, char* newIP) {
    for (int i = 0; i < 4; i++) {
        sprintf(newIP, "%d.%d.%d.%d", path[0], path[1], path[2], path[3]);
    }
}

bool isValid(char* curSeg) {
    int n = strlen(curSeg);
    if (n < 1 || n > 3) return false; // 已被fmin限制，此处可不写
    if (curSeg[0] == '0' && n > 1) return false; // 前导0，非法IP
    int curVal = atoi(curSeg);
    return 0 <= curVal && curVal <= 255;
}

int main() {
    int path[4] = { 10, 1, 0, 23 };
    char* newIP = (char*)calloc(4, sizeof(int) + sizeof(char));
    makeIP(path, newIP);
    printf("%s\n", newIP);
    printf("atoi(\"255\") = % d\n", atoi("255"));// test：atoi

    char* s = "101023"; // "25525511135"
    int returnSize = 0;
    char** res = restoreIpAddresses(s, &returnSize);
    printf("returnSize = %d\n", returnSize);

    for (int i = 0; i < returnSize; i++) {
        printf("%s\n", res[i]);
    }
    return 0;
}