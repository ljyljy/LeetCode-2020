#include <stdio.h>
#include <stdlib.h>
// #include <stdbool.h>
// #include <string.h>
// #include <math.h>
// #include <limits.h>


// q2418. 重排名单 - 按身高降序(姓名升序)
typedef struct Info_s {
    char* name;
    int height;
} Info;

// 对比成员pa/pb类型：*obj, 需强转 & 解引用
static inline int cmp(const void* pa, const void* pb) {
    const Info o1 = *(Info*)pa, o2 = *(Info*)pb;
    return o2.height - o1.height; // 身高 降序
    //follow-up：相同则按姓名升序return strcmp(o1.name, o2.name)
}

char** sortPeople(char** names, int n, int* heights, int n2, int* returnSize) {
    Info* obj = (Info*)calloc(n, sizeof(Info));
    for (int i = 0; i < n; i++) {
        obj[i].name = names[i];
        obj[i].height = heights[i];
    }
    qsort(obj, n, sizeof(*obj), cmp);

    char** res = (char**)calloc(n, sizeof(char*));
    for (int i = 0; i < n; i++) {
        res[i] = obj[i].name; // 指针赋值即可，无需strcpy/memcpy（需先calloc）
    }
    *returnSize = n;
    return res;
}

int main() {
    int n = 6;
    char* names[] = { "Tom", "Jack", "John", "Andy", "Lily", "Bob" };
    int heights[] = { 188,    180,    175,    188,    185,    173 };
    int returnSize;
    char** res = sortPeople(names, n, heights, n, &returnSize);
    for (int i = 0; i < returnSize; i++) {
        printf("%s ", res[i]);
    } // Andy Tom Lily Jack John Bob
    printf("\n");

    n = 4;
    char* names2[] = { "Tom", "Jack", "John", "Andy" };
    int heights2[] = { 188, 178, 185, 180 };
    res = sortPeople(names2, n, heights2, n, &returnSize);
    for (int i = 0; i < returnSize; i++) {
        printf("%s ", res[i]);
    } // Tom John Andy Jack
    printf("\n"); // puts("");
}