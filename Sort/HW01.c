#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>
#include <stdbool.h>

// hw01-单板告警统计 Single board alarm statistics
/* 知识点：
1）思路：字符串数组(char [N][STR_LEN])：两数组合并 + 排序 + 去重

2）字符串数组(char [N][STR_LEN])排序
 - qsort(..., strcmp)函数，无需强转
   - 注意：strcmp(*(char**)pa, *(char**)pb)则不可通过 -- 针对char* arr[N]可通过
3）字符串数组(char [N][STR_LEN])去重
 - 利用memmove()函数，将重复元素后面的元素向前移动，覆盖重复元素
 - *len是数组长度，函数执行后，*len是去重后的数组长度
*/
#define STR_LEN 16
#define ARR_MAX_LEN 1000


// removeDuplicates:
// - 去重，利用memmove()函数，将重复元素后面的元素向前移动，覆盖重复元素
// - *len是数组长度，函数执行后，*len是去重后的数组长度
void removeDuplicates(char arr[][STR_LEN], int* len);
void printOutBuf(char outBuf[][STR_LEN], int len);

// 不强转则可通过，错误：强转strcmp(*(char**)a, *(char**)b)则不可通过
// - outBuf是二维数组，每个元素是char[STR_LEN]，即字符数组，所以不需要强转
static inline int cmp2(const void* a, const void* b) {
    return strcmp(a, b);
}

// 生成的信息列表存于outBuf中，maxOutBufLen是最大数组长度，返回值为实际长度。
static int GetAllFault(char** pArr_A, int arrayASize, char** pArr_B, int arrayBSize,
    char outBuf[][STR_LEN], int maxOutBufLen) {
    // 1) 合并两个数组
    int len = arrayASize + arrayBSize;
    for (int i = 0; i < arrayASize; i++) {
        strcpy_s(outBuf[i], STR_LEN, pArr_A[i]);
    }
    for (int i = 0; i < arrayBSize; i++) {
        strcpy_s(outBuf[arrayASize + i], STR_LEN, pArr_B[i]);
    }
    // printOutBuf(outBuf, len);

    // 2) 使用qsort排序
    // qsort(outBuf, len, sizeof(outBuf[0]), cmp2); // v1
    qsort(outBuf, len, sizeof(outBuf[0]), strcmp); // v2

    // printf("outBuf(After qsort): ");
    // printOutBuf(outBuf, len);
    // 3) 去重
    removeDuplicates(outBuf, &len);

    return len;
}

// 去重，利用memmove()函数，将重复元素后面的元素向前移动，覆盖重复元素
// - *len是数组长度，函数执行后，*len是去重后的数组长度
void removeDuplicates(char arr[][STR_LEN], int* len) {
    int i, j;
    for (i = 0; i < *len; i++) {
        for (j = i + 1; j < *len; j++) {
            if (strcmp(arr[i], arr[j]) == 0) { // 发现重复,break
                break;
            }
        }
        if (j < *len) { // 发现重复，删除重复元素
            // 去重利器：memmove(*Dst, *Src, Size)
            // - 内存前移 size = [j+1, *len-1] * (strlen(arr[0] + 1))
            memmove(arr[j], arr[j + 1], (*len - j - 1) * STR_LEN);
            (*len)--;
            j--;
        }
    }
    // *len是去重后的数组长度
}

void printOutBuf(char outBuf[][STR_LEN], int len) {
    // 打印outBuf
    printf("outBuf = [");
    for (int i = 0; i < len; i++) {
        if (i == 0) {
            printf("%s", outBuf[i]);
        }
        else {
            printf(" %s", outBuf[i]);
        }
    }
    printf("]\n");
}

int main() {
    /** 示例1
    // 告警ID列表 pArr_A 和 pArr_B
    char *pArr_A[] = {"00001001", "00ABCD00"};
    char *pArr_B[] = {"FFFFFAAB", "FFFFFAAB", "00ABCD00"};

    int arrayASize = sizeof(pArr_A) / sizeof(pArr_Test[0]); // pArr_A 的长度
    int arrayBSize = sizeof(pArr_B) / sizeof(pArr_B[0]); // pArr_B 的长度
     */

     // int arrayASize = 0;
     // if (scanf_s("%d", &arrayASize) != 1) {
     //     return -1;
     // }

     // char bufA[ARR_MAX_LEN][STR_LEN] = { 0 }; // bufA[1000][16]
     // char* pArr_A[ARR_MAX_LEN];
     // for (int i = 0; i < arrayASize; ++i) {
     //     if (scanf_s("%s", bufA[i], STR_LEN) != 1) {
     //         return -1;
     //     }
     //     pArr_A[i] = bufA[i]; // pArr_A[i]是char*指针，指向bufA[i]
     // }

     // int arrayBSize = 0;
     // if (scanf_s("%d", &arrayBSize) != 1) {
     //     return -1;
     // }
     // char bufB[ARR_MAX_LEN][STR_LEN] = { 0 }; // bufB[1000][16]
     // char* pArr_B[ARR_MAX_LEN];
     // for (int i = 0; i < arrayBSize; ++i) {
     //     if (scanf_s("%s", bufB[i], STR_LEN) != 1) {
     //         return -1;
     //     }
     //     pArr_B[i] = bufB[i];// pArr_B[i]是char*指针，指向bufB[i]
     // }

    char* pArr_A[] = { "00001001", "00ABCD00" };
    char* pArr_B[] = { "FFFFFAAB", "FFFFFAAB", "00ABCD00" };

    int arrayASize = sizeof(pArr_A) / sizeof(pArr_A[0]); // pArr_A 的长度
    int arrayBSize = sizeof(pArr_B) / sizeof(pArr_B[0]); // pArr_B 的长度

    char outBuf[ARR_MAX_LEN * 2][STR_LEN]; // merge后的数组(ID升序 & 去重)！
    int outBufSize = GetAllFault(pArr_A, arrayASize, pArr_B, arrayBSize, outBuf, ARR_MAX_LEN * 2);
    printf("outBufSize = %d \n", outBufSize);

    printOutBuf(outBuf, outBufSize);

    return 0;
}

/* char* 指针数组的长度，Test
char* pArr_Test[] = { "00001001XXYYZ", "00ABCD00XYZMUS" };

printf("A. pArr_Test[0]: %s,  B. strlen(pArr_Test[0]): % d, \
        C. sizeof(pArr_Test[0]) : % d, D. sizeof(pArr_A) : % d\n", \
       pArr_Test[0], strlen(pArr_Test[0]), \
       sizeof(pArr_Test[0]), sizeof(pArr_A));

/* A. pArr_Test[0]="00001001XXYYZ", B. 字符串长度=13, C. 指针长度=8, D. 数组长度=16 */