#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>
#include <stdbool.h>

// hw01-����澯ͳ�� Single board alarm statistics
/* ֪ʶ�㣺
1��˼·���ַ�������(char [N][STR_LEN])��������ϲ� + ���� + ȥ��

2���ַ�������(char [N][STR_LEN])����
 - qsort(..., strcmp)����������ǿת
   - ע�⣺strcmp(*(char**)pa, *(char**)pb)�򲻿�ͨ�� -- ���char* arr[N]��ͨ��
3���ַ�������(char [N][STR_LEN])ȥ��
 - ����memmove()���������ظ�Ԫ�غ����Ԫ����ǰ�ƶ��������ظ�Ԫ��
 - *len�����鳤�ȣ�����ִ�к�*len��ȥ�غ�����鳤��
*/
#define STR_LEN 16
#define ARR_MAX_LEN 1000


// removeDuplicates:
// - ȥ�أ�����memmove()���������ظ�Ԫ�غ����Ԫ����ǰ�ƶ��������ظ�Ԫ��
// - *len�����鳤�ȣ�����ִ�к�*len��ȥ�غ�����鳤��
void removeDuplicates(char arr[][STR_LEN], int* len);
void printOutBuf(char outBuf[][STR_LEN], int len);

// ��ǿת���ͨ��������ǿתstrcmp(*(char**)a, *(char**)b)�򲻿�ͨ��
// - outBuf�Ƕ�ά���飬ÿ��Ԫ����char[STR_LEN]�����ַ����飬���Բ���Ҫǿת
static inline int cmp2(const void* a, const void* b) {
    return strcmp(a, b);
}

// ���ɵ���Ϣ�б����outBuf�У�maxOutBufLen��������鳤�ȣ�����ֵΪʵ�ʳ��ȡ�
static int GetAllFault(char** pArr_A, int arrayASize, char** pArr_B, int arrayBSize,
    char outBuf[][STR_LEN], int maxOutBufLen) {
    // 1) �ϲ���������
    int len = arrayASize + arrayBSize;
    for (int i = 0; i < arrayASize; i++) {
        strcpy_s(outBuf[i], STR_LEN, pArr_A[i]);
    }
    for (int i = 0; i < arrayBSize; i++) {
        strcpy_s(outBuf[arrayASize + i], STR_LEN, pArr_B[i]);
    }
    // printOutBuf(outBuf, len);

    // 2) ʹ��qsort����
    // qsort(outBuf, len, sizeof(outBuf[0]), cmp2); // v1
    qsort(outBuf, len, sizeof(outBuf[0]), strcmp); // v2

    // printf("outBuf(After qsort): ");
    // printOutBuf(outBuf, len);
    // 3) ȥ��
    removeDuplicates(outBuf, &len);

    return len;
}

// ȥ�أ�����memmove()���������ظ�Ԫ�غ����Ԫ����ǰ�ƶ��������ظ�Ԫ��
// - *len�����鳤�ȣ�����ִ�к�*len��ȥ�غ�����鳤��
void removeDuplicates(char arr[][STR_LEN], int* len) {
    int i, j;
    for (i = 0; i < *len; i++) {
        for (j = i + 1; j < *len; j++) {
            if (strcmp(arr[i], arr[j]) == 0) { // �����ظ�,break
                break;
            }
        }
        if (j < *len) { // �����ظ���ɾ���ظ�Ԫ��
            // ȥ��������memmove(*Dst, *Src, Size)
            // - �ڴ�ǰ�� size = [j+1, *len-1] * (strlen(arr[0] + 1))
            memmove(arr[j], arr[j + 1], (*len - j - 1) * STR_LEN);
            (*len)--;
            j--;
        }
    }
    // *len��ȥ�غ�����鳤��
}

void printOutBuf(char outBuf[][STR_LEN], int len) {
    // ��ӡoutBuf
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
    /** ʾ��1
    // �澯ID�б� pArr_A �� pArr_B
    char *pArr_A[] = {"00001001", "00ABCD00"};
    char *pArr_B[] = {"FFFFFAAB", "FFFFFAAB", "00ABCD00"};

    int arrayASize = sizeof(pArr_A) / sizeof(pArr_Test[0]); // pArr_A �ĳ���
    int arrayBSize = sizeof(pArr_B) / sizeof(pArr_B[0]); // pArr_B �ĳ���
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
     //     pArr_A[i] = bufA[i]; // pArr_A[i]��char*ָ�룬ָ��bufA[i]
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
     //     pArr_B[i] = bufB[i];// pArr_B[i]��char*ָ�룬ָ��bufB[i]
     // }

    char* pArr_A[] = { "00001001", "00ABCD00" };
    char* pArr_B[] = { "FFFFFAAB", "FFFFFAAB", "00ABCD00" };

    int arrayASize = sizeof(pArr_A) / sizeof(pArr_A[0]); // pArr_A �ĳ���
    int arrayBSize = sizeof(pArr_B) / sizeof(pArr_B[0]); // pArr_B �ĳ���

    char outBuf[ARR_MAX_LEN * 2][STR_LEN]; // merge�������(ID���� & ȥ��)��
    int outBufSize = GetAllFault(pArr_A, arrayASize, pArr_B, arrayBSize, outBuf, ARR_MAX_LEN * 2);
    printf("outBufSize = %d \n", outBufSize);

    printOutBuf(outBuf, outBufSize);

    return 0;
}

/* char* ָ������ĳ��ȣ�Test
char* pArr_Test[] = { "00001001XXYYZ", "00ABCD00XYZMUS" };

printf("A. pArr_Test[0]: %s,  B. strlen(pArr_Test[0]): % d, \
        C. sizeof(pArr_Test[0]) : % d, D. sizeof(pArr_A) : % d\n", \
       pArr_Test[0], strlen(pArr_Test[0]), \
       sizeof(pArr_Test[0]), sizeof(pArr_A));

/* A. pArr_Test[0]="00001001XXYYZ", B. �ַ�������=13, C. ָ�볤��=8, D. ���鳤��=16 */