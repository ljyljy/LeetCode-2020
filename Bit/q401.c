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
                char* tmp = (char*)malloc(sizeof(char) * basicSize); // ����"05:05\0" ��Ϊ6
                sprintf(tmp, "%d:%02d", h, m);
                // ��1 - ָ��ָ��
                res[curCnt] = tmp;  // �ȼ��� *(res + curCnt) = tmp;
                // ��2 - �ַ�������
                // res[curCnt] = (char*)malloc(sizeof(char) * basicSize); // res[curCnt]������Ϊ(char*)
                // strcpy(*(res + curCnt), tmp);
                if (++curCnt == basicSize) {  // ����
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