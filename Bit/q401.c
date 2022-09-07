#include <stdio.h>
#include <stdlib.h>
// #include <math.h>
#include <string.h>
// #include <limits.h>
// #include <stdbool.h>



char** readBinaryWatch(int turnedOn, int* returnSize) {
    int basicSize = 8, curCnt = 0;
    char** res = (char**)malloc(sizeof(char*) * basicSize);
    // memset(res, 0, sizeof(char*) * basicSize);
    for (int h = 0; h < 12; h++) {
        for (int m = 0; m < 60; m++) {
            if (__builtin_popcount(h) + __builtin_popcount(m) == turnedOn) {
                char* tmp = (char*)malloc(sizeof(char) * basicSize); // 形如"05:05\0" 长为6
                sprintf(tmp, "%d:%02d", h, m);
                // 法1 - 指针指向
                res[curCnt] = tmp;  // 等价于 *(res + curCnt) = tmp;
                // 法2 - 字符串拷贝
                // res[curCnt] = (char*)malloc(sizeof(char) * basicSize); // res[curCnt]的类型为(char*)
                // strcpy(*(res + curCnt), tmp);
                if (++curCnt == basicSize) {  // 扩容
                    basicSize *= 2;
                    res = (char**)realloc(res, sizeof(char*) * basicSize);
                }
            }
        }
    }
    *returnSize = curCnt;

    return res;
}

int main() {
    // test
    for (int i = 0; i <= 10; i++) {
        printf("%d ---- %d\n", i, __builtin_popcount(i));
        printf("%d, (8)=%o, (16)=%x\n\n", i, i, i);
    }

    int turnedOn = 5;
    int* returnSize = (int*)malloc(sizeof(int));
    char** res = readBinaryWatch(turnedOn, returnSize);

    printf("res size = %d\n", *returnSize);
    for (int i = 0; i < *returnSize; i++) {
        printf("%s ", res[i]);
    }

}