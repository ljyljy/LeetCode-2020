#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// 类似：HW01.c - 单板告警统计
#define STR_LEN 10

void removeDuplicates(char** arr, int* len) {
    int i = 0, j = 0;
    for (int i = 0; i < *len; i++) {
        for (int j = i + 1; j < *len; j++) {
            if (strcmp(arr[i], arr[j]) == 0) {
                // 由于arr原型是指针数组char* [N]，所以arr[j]是char*（指针），删除的是指针
                memmove(arr + j, arr + j + 1, (*len - j - 1) * sizeof(arr[0]));
                (*len)--; // 注意：len是指针，所以要加括号
            }
        }
    }
}

void reverse(char** arr, int len) {
    int i = 0, j = len - 1;
    while (i < j) {
        char* tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
        i++;
        j--;
    }
}

void printArr(char** arr, int len, char* msg) {
    if (msg != NULL) {
        printf("%s", msg);
    }
    for (int i = 0; i < len; i++) {
        printf("%s ", arr[i]);
    }

    printf("\n");
}

int cmp(const void* a, const void* b) {
    return strcmp(*(char**)a, *(char**)b);
}

int main() {
    char* chs1[] = { "abcd",  "abd123", "2345abc",  "efgh" };  // 指针数组，chs1[i]存放指针，指向字符串，而非字符串本身！
    char* chs2[] = { "abd123", "efghXYZ", "yz01", "2345abc" };
    int len1 = sizeof(chs1) / sizeof(chs1[0]);
    int len2 = sizeof(chs2) / sizeof(chs2[0]);
    int n = len1 + len2;
    char* pChs[n]; // 指针数组，pChs[i]存放指针，指向字符串，而非字符串本身！
    for (int i = 0; i < len1; i++) {
        pChs[i] = chs1[i]; // pChs[i]是指针，指向chs1[i]的地址
    }
    for (int i = 0; i < len2; i++) {
        pChs[len1 + i] = chs2[i];
    }
    // char* pChs[] = { "abcd",  "abd123", "2345abc",  "efgh", "abd123", "efghXYZ", "yz01", "2345abc" };

    // 结论：
    // - 1）qsort排序规则与strcmp不同；
    // - 2）char * arr[N]可使用strcmp、自定义cmp进行排序
        /*--------- 0）基于字符串数组char* chs1[N] -------*/
    printf("\n--------- 0) Based on char* chs1[N] -------\n");
    // 0-1）升序排序 - qsort - 基于字符串数组char* arr[N]
    printArr(chs1, len1, "origin chs1: ");
    // origin chs1: abcd abd123 2345abc efgh

    qsort(chs1, len1, sizeof(chs1[0]), strcmp); // v1
    printArr(chs1, len1, "After qsort(strcmp): "); // strcmp基于字典序?结果怪怪的
    // After qsort(strcmp): abcd abd123 2345abc efgh

    qsort(chs1, len1, sizeof(chs1[0]), cmp); // v2：注意与strcmp排序规则不同！！
    printArr(chs1, len1, "After qsort(cmp): "); // cmp认为数字小于字母
    // After qsort(cmp): 2345abc abcd abd123 efgh

    /*--------- 1）基于指针数组char* pChs[N] -------*/
    printf("\n--------- 1) Based on char* pChs[N] -------\n");
    // 1）升序排序 - qsort - 基于指针数组char* pChs[N]
    printArr(pChs, n, "origin pChs: ");
    // origin pChs:  abcd abd123 2345abc efgh abd123 efghXYZ yz01 2345abc

    qsort(pChs, n, sizeof(pChs[0]), \
        /* (int(*)(const void*, const void*)) */strcmp); // v1
    printArr(pChs, n, "After qsort(strcmp): ");
    // After qsort(strcmp): abcd abd123 abd123 2345abc 2345abc efgh efghXYZ yz01

    qsort(pChs, n, sizeof(pChs[0]), cmp); // v2
    printArr(pChs, n, "After qsort(cmp): ");
    // After qsort(cmp): 2345abc 2345abc abcd abd123 abd123 efgh efghXYZ yz01

    // 2）去重
    removeDuplicates(pChs, &n);
    printArr(pChs, n, "After removeDuplicates: ");
    // After removeDuplicates: 2345abc abcd abd123 efgh efghXYZ yz01

    // 3）降序排序 - 先qsort升序，再reverse
    reverse(pChs, n);
    printArr(pChs, n, "After reverse: ");
    // After reverse: yz01 efghXYZ efgh abd123 abcd 2345abc

    return 0;
}

/* Output:
--------- 0) Based on char* chs1[N] -------
origin chs1 : abcd abd123 2345abc efgh
After qsort(strcmp) : abcd abd123 2345abc efgh
After qsort(cmp) : 2345abc abcd abd123 efgh

--------- 1) Based on char* pChs[N] -------
origin pChs : abcd abd123 2345abc efgh abd123 efghXYZ yz01 2345abc
After qsort(strcmp) : abcd abd123 abd123 2345abc 2345abc efgh efghXYZ yz01
After qsort(cmp) : 2345abc 2345abc abcd abd123 abd123 efgh efghXYZ yz01
After removeDuplicates : 2345abc abcd abd123 efgh efghXYZ yz01
After reverse : yz01 efghXYZ efgh abd123 abcd 2345abc
* /