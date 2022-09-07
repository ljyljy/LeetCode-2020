#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
// #include <limits.h>
// #include <stdbool.h>

#ifndef DIM
#define DIM(arr) sizeof(arr)/sizeof(*arr)
#endif


// char* s1[n] �ıȽ�: ������Ԫ��o1/o2 = char*, ��ȡ����via������(���˻����char**������)����ȶ���ָ�� & ָ�����飺q1288(int**)��char**��char*[]
// ���2�������Ȼָ�/�˻�Ϊchar**, ����֪����һά("bas","dfs")�����ƶ�; �ٽ��Ž�����һ�Σ��õ�������ַ���char*
int cmpStr1(const void* a, const void* b) {
    char* s1 = *(char**)a, * s2 = *(char**)b; // Ŀ�꣺ת��Ϊchar*������Ҫ������
    return strcmp(s1, s2);
}

// char** s2 �ıȽ�: ����ָ�룬o1/o2 = char*, ��ȡ����via������(���˻����char**������)����ȶ���ָ�� & ָ�����飺q1288(int**)��char**��char*[]
int cmpStr2(const void* a, const void* b) { // д��2
    char* s1 = *(char**)a, * s2 = *(char**)b; //
    return strcmp(s1, s2);
}

// char s2[m][n] �ıȽ�: ��ά���飬o1/o2 = char*, ��ȡ����via������(���˻��������ָ��(char*)[n]�����ã�ʵ�����(char*))����ά���� ��ͬ�ڶ���ָ�� & ָ������!
int cmpStr3(const void* a, const void* b) { // д��2
    char* s1 = (char*)a, * s2 = (char*)b; //
    return strcmp(s1, s2);
}


/**
 * ��C��*2��
 * 1��int**��cmp  ��C�ӣ�o1/o2����ǿתΪ*(int**), ��Javaһ�£�����ǿתΪint**, �����߼� �� ָ��+1Խ�磡��
 * 2��int**��int[][]�Ĳ�ͬ�� -- ���� sizeof
 */
int main() {
    char* s1[3] = { "bas", "dfs", "asd" }; // ָ�����飬������Ԫ��Ϊchar*�����鹲����3��char*���͵�ָ��
    for (int i = 0; i < 3; ++i) {
        printf("%s\n", s1[i]);
    }
    qsort(s1, 3, sizeof(s1[0]), cmpStr1); // 3, 8(������������Զ����sizeof(s[0]))
    printf("\nsorted: \n");
    for (int i = 0; i < 3; ++i) {
        printf("%s\n", s1[i]);
    }
    printf("--------------------------------------\n");


    char** s2 = (char**)malloc(sizeof(char*) * 3); // (char*)
    for (int i = 0; i < 3; ++i) {
        s2[i] = (char*)malloc(sizeof(char) * 4);
    }
    s2[0] = "bas";
    s2[1] = "dfs";
    s2[2] = "asd";
    for (int i = 0; i < 3; ++i) {
        printf("%s\n", s2[i]);
    }
    printf("DIM(s2)=%d, DIM(s2[0])=%d, sizeof(s2[0])=%d\n", DIM(s2), DIM(s2[0]), sizeof(s2[0])); // 64λOS, sizeof(ָ��/��ַ)=8B
    qsort(s2, 3, sizeof(s2[0]), cmpStr2); // 3, 8(������������Զ����sizeof(s[0]))
    printf("\nsorted: \n");
    for (int i = 0; i < 3; ++i) {
        printf("%s\n", s2[i]);
    }


    printf("--------------------------------------\n");



    char s3[3][4] = { "bas", "dfs", "asd" }; // (char*)[]
    for (int i = 0; i < 3; ++i) {
        printf("%s\n", s3[i]);
    }
    qsort(s3, DIM(s3), sizeof(s3[0]), cmpStr3); // 3, 4
    printf("\nsorted: \n");
    for (int i = 0; i < 3; ++i) {
        printf("%s\n", s3[i]);
    }
    printf("--------------------------------------\n");

}