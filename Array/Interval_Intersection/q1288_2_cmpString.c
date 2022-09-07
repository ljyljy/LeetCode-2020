#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
// #include <limits.h>
// #include <stdbool.h>

#ifndef DIM
#define DIM(arr) sizeof(arr)/sizeof(*arr)
#endif


// char* s1[n] 的比较: 数组内元素o1/o2 = char*, 读取必须via解引用(将退化后的char**解引用)！类比二级指针 & 指针数组：q1288(int**)、char**、char*[]
// 理解2：必须先恢复/退化为char**, 才能知道第一维("bas","dfs")怎样移动; 再接着解引用一次，得到具体的字符串char*
int cmpStr1(const void* a, const void* b) {
    char* s1 = *(char**)a, * s2 = *(char**)b; // 目标：转换为char*，但需要解引用
    return strcmp(s1, s2);
}

// char** s2 的比较: 二级指针，o1/o2 = char*, 读取必须via解引用(将退化后的char**解引用)！类比二级指针 & 指针数组：q1288(int**)、char**、char*[]
int cmpStr2(const void* a, const void* b) { // 写法2
    char* s1 = *(char**)a, * s2 = *(char**)b; //
    return strcmp(s1, s2);
}

// char s2[m][n] 的比较: 二维数组，o1/o2 = char*, 读取必须via解引用(将退化后的数组指针(char*)[n]解引用，实则就是(char*))！二维数组 不同于二级指针 & 指针数组!
int cmpStr3(const void* a, const void* b) { // 写法2
    char* s1 = (char*)a, * s2 = (char*)b; //
    return strcmp(s1, s2);
}


/**
 * 【C坑*2】
 * 1）int**的cmp  【C坑：o1/o2必须强转为*(int**), 与Java一致！不可强转为int**, 不合逻辑 且 指针+1越界！】
 * 2）int**与int[][]的不同！ -- 见下 sizeof
 */
int main() {
    char* s1[3] = { "bas", "dfs", "asd" }; // 指针数组，数组内元素为char*，数组共包含3个char*类型的指针
    for (int i = 0; i < 3; ++i) {
        printf("%s\n", s1[i]);
    }
    qsort(s1, 3, sizeof(s1[0]), cmpStr1); // 3, 8(第三个参数永远都是sizeof(s[0]))
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
    printf("DIM(s2)=%d, DIM(s2[0])=%d, sizeof(s2[0])=%d\n", DIM(s2), DIM(s2[0]), sizeof(s2[0])); // 64位OS, sizeof(指针/地址)=8B
    qsort(s2, 3, sizeof(s2[0]), cmpStr2); // 3, 8(第三个参数永远都是sizeof(s[0]))
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