#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// ���ƣ�HW01.c - ����澯ͳ��
#define STR_LEN 10

void removeDuplicates(char** arr, int* len) {
    int i = 0, j = 0;
    for (int i = 0; i < *len; i++) {
        for (int j = i + 1; j < *len; j++) {
            if (strcmp(arr[i], arr[j]) == 0) {
                // ����arrԭ����ָ������char* [N]������arr[j]��char*��ָ�룩��ɾ������ָ��
                memmove(arr + j, arr + j + 1, (*len - j - 1) * sizeof(arr[0]));
                (*len)--; // ע�⣺len��ָ�룬����Ҫ������
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
    char* chs1[] = { "abcd",  "abd123", "2345abc",  "efgh" };  // ָ�����飬chs1[i]���ָ�룬ָ���ַ����������ַ�������
    char* chs2[] = { "abd123", "efghXYZ", "yz01", "2345abc" };
    int len1 = sizeof(chs1) / sizeof(chs1[0]);
    int len2 = sizeof(chs2) / sizeof(chs2[0]);
    int n = len1 + len2;
    char* pChs[n]; // ָ�����飬pChs[i]���ָ�룬ָ���ַ����������ַ�������
    for (int i = 0; i < len1; i++) {
        pChs[i] = chs1[i]; // pChs[i]��ָ�룬ָ��chs1[i]�ĵ�ַ
    }
    for (int i = 0; i < len2; i++) {
        pChs[len1 + i] = chs2[i];
    }
    // char* pChs[] = { "abcd",  "abd123", "2345abc",  "efgh", "abd123", "efghXYZ", "yz01", "2345abc" };

    // ���ۣ�
    // - 1��qsort���������strcmp��ͬ��
    // - 2��char * arr[N]��ʹ��strcmp���Զ���cmp��������
        /*--------- 0�������ַ�������char* chs1[N] -------*/
    printf("\n--------- 0) Based on char* chs1[N] -------\n");
    // 0-1���������� - qsort - �����ַ�������char* arr[N]
    printArr(chs1, len1, "origin chs1: ");
    // origin chs1: abcd abd123 2345abc efgh

    qsort(chs1, len1, sizeof(chs1[0]), strcmp); // v1
    printArr(chs1, len1, "After qsort(strcmp): "); // strcmp�����ֵ���?����ֵֹ�
    // After qsort(strcmp): abcd abd123 2345abc efgh

    qsort(chs1, len1, sizeof(chs1[0]), cmp); // v2��ע����strcmp�������ͬ����
    printArr(chs1, len1, "After qsort(cmp): "); // cmp��Ϊ����С����ĸ
    // After qsort(cmp): 2345abc abcd abd123 efgh

    /*--------- 1������ָ������char* pChs[N] -------*/
    printf("\n--------- 1) Based on char* pChs[N] -------\n");
    // 1���������� - qsort - ����ָ������char* pChs[N]
    printArr(pChs, n, "origin pChs: ");
    // origin pChs:  abcd abd123 2345abc efgh abd123 efghXYZ yz01 2345abc

    qsort(pChs, n, sizeof(pChs[0]), \
        /* (int(*)(const void*, const void*)) */strcmp); // v1
    printArr(pChs, n, "After qsort(strcmp): ");
    // After qsort(strcmp): abcd abd123 abd123 2345abc 2345abc efgh efghXYZ yz01

    qsort(pChs, n, sizeof(pChs[0]), cmp); // v2
    printArr(pChs, n, "After qsort(cmp): ");
    // After qsort(cmp): 2345abc 2345abc abcd abd123 abd123 efgh efghXYZ yz01

    // 2��ȥ��
    removeDuplicates(pChs, &n);
    printArr(pChs, n, "After removeDuplicates: ");
    // After removeDuplicates: 2345abc abcd abd123 efgh efghXYZ yz01

    // 3���������� - ��qsort������reverse
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