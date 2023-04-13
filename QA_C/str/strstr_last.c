#include <string.h>
#include <stdio.h>
#include <stdlib.h>


// 从str中找最后一次出现sub的位置（指针）
char* strstr_last(const char* str, const char* sub) {
    register char* cur = NULL, * nxt = NULL;
    // 空串'\0'最后一次出现的位置置NULL，可自定义
    if (sub == NULL || *sub == 0) return NULL;

    nxt = strstr(str, sub); // sub在str中第一次出现的位置(指针)
    while (nxt != NULL) {
        cur = nxt;
        nxt = strstr(cur + 1, sub); // 继续下探
    }
    return cur;
}

int main() {
    char* s1 = "vvv abc ddd abc pod", * s2 = "abc";
    char* pFirst = strstr(s1, s2);
    int idx_first = pFirst - s1;
    printf("pFirst = %s, idx_first = %d\n", pFirst, idx_first);
    // pFirst = abc ddd abc pod, idx_first = 4

    char* pLast = strstr_last(s1, s2);
    int idx_last = pLast - s1;
    printf("pLast = %s, idx_first = %d\n", pLast, idx_last);
    // pLast = abc pod, idx_first = 12
}