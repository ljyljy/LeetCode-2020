#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// 类似：HW01.c - 单板告警统计
#define STR_LEN 10
#define N 4

void removeDuplicates(char arr[][STR_LEN], int* len) {
    int i = 0, j = 0;
    for (int i = 0; i < *len; i++) {
        for (int j = i + 1; j < *len; j++) {
            if (strcmp(arr[i], arr[j]) == 0) {
                // 由于arr原型是char arr[N][LEN]，所以arr[j]是char [Len] 字符数组，删除的是'字符数组'
                memmove(arr + j, arr + j + 1, (*len - j - 1) * sizeof(arr[0]));
                (*len)--;
            }
        }
    }
}


// 入参：char arr[][STR_LEN] 或 char (*arr)[STR_LEN]
void printArr(char arr[][STR_LEN], int n, char* msg) {
    if (msg != NULL) {
        printf("%s", msg);
    }
    for (int i = 0; i < n; i++) {
        printf("%s ", arr[i]);
    }
    printf("\n");
}

static inline int cmp(const void* a, const void* b) {
    return strcmp(a, b);
}

int main() {
    char chs1[N][STR_LEN] = { "abcd",  "abd123", "2345abc",  "efgh" };  // chs1[i]存放'字符数组'，而非字面量！
    char chs2[N][STR_LEN] = { "abd123", "efghXYZ", "yz01", "2345abc" };
    // int len1 = sizeof(chs1) / sizeof(chs1[0]); // 4
    // int len2 = sizeof(chs2) / sizeof(chs2[0]); // 4
    // int n = len1 + len2; // 8
    // -- sizeof(chs1)=40, sizeof(chs1[0])=10, strlen(chs1[0])=4
    int cnt = N * 2;
    char chs[cnt][STR_LEN]; // 二维数组，'字符数组'的数组
    for (int i = 0; i < N; i++) {
        strcpy_s(chs[i], STR_LEN, chs1[i]);
        // strcpy(chs[i], chs1[i]);
    }
    for (int i = 0; i < N; i++) {
        strcpy_s(chs[N + i], STR_LEN, chs2[i]);
        // strcpy(chs[N + i], chs2[i]);
    }

    // char chs[N * 2][STR_LEN] = { "abcd",  "abd123", "2345abc", "efgh", "abd123", "efghXYZ", "yz01", "2345abc" };
    printf("\n--------- 2) Based on char arr[N][LEN] -------\n");

    // 2-1）升序排序 - qsort - 基于二维数组char arr[N][LEN]
    printArr(chs, cnt, "origin chs: ");
    // origin chs: abcd abd123 2345abc efgh abd123 efghXYZ yz01 2345abc
    qsort(chs, cnt, sizeof(chs[0]), cmp); // 本质还是调用了strcmp
    printArr(chs, cnt, "After qsort(cmp): ");
    // After qsort(cmp): 2345abc 2345abc abcd abd123 abd123 efgh efghXYZ yz01

    qsort(chs, cnt, sizeof(chs[0]), strcmp);
    printArr(chs, cnt, "After qsort(strcmp): ");
    // After qsort(strcmp): 2345abc 2345abc abcd abd123 abd123 efgh efghXYZ yz01

    // 2-2）去重 - 基于二维数组char arr[N][LEN]
    removeDuplicates(chs, &cnt);
    printArr(chs, cnt, "After removeDuplicates: ");
    // After removeDuplicates: 2345abc abcd abd123 efgh efghXYZ yz01

    return 0;
}