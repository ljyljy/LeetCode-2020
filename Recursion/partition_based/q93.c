#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <math.h>
#include <string.h>
#include <limits.h>

bool isValidNum(char* s, int l, int r) {
    if (l > r) return false;
    if (s[l] == '0' && l != r) return false;
    int num = 0;
    for (int i = l; i <= r; ++i) {
        num = num * 10 + (s[i] - '0');
        if (num > 255) return false;
    }
    return true;
}

void backtrace(char* s, char* path, char** res, int* returnSize, int idx, int len, int lastPoint, int pointNum) {
    if (pointNum == 3) {
        if (isValidNum(s, lastPoint, len - 1)) {
            // printf("enter\n");
            strncpy(path + idx, s + lastPoint, len - lastPoint + 1);
            res[*returnSize] = (char*)malloc(sizeof(char) * (len + 4));
            strcpy(res[*returnSize], path);
            (*returnSize)++;
        }
        return;
    }
    for (int i = lastPoint; i < len && i - lastPoint < 3; ++i) {
        if (!isValidNum(s, lastPoint, i)) continue;
        strncpy(path + idx, s + lastPoint, i - lastPoint + 1);
        path[idx + i - lastPoint + 1] = '.';
        // printf("%s\t%d\n", path, i);
        backtrace(s, path, res, returnSize, idx + i - lastPoint + 2, len, i + 1, pointNum + 1);
        // printf("return\n");
    }
    return;
}

char** restoreIpAddresses(char* s, int* returnSize) {
    char** res = (char**)malloc(sizeof(char*) * 3000);
    *returnSize = 0;
    int len = strlen(s);
    char* path = (char*)malloc(sizeof(char) * (len + 4));
    memset(path, 0, sizeof(char) * (len + 4));
    backtrace(s, path, res, returnSize, 0, len, 0, 0);
    printf("returnSize = %d\n", *returnSize);
    return res;
}

int main() {
    char* s = "25525511135";
    int returnSize = 0;
    char** res = restoreIpAddresses(s, &returnSize);
    for (int i = 0; i < returnSize; i++) {
        printf("%s\n", res[i]);
    }

    return 0;
}