#include <string.h>
#include <stdio.h>
#include <stdlib.h>

// 从s1中找最后一次出现s2的位置
char* strrstr2(char const* s1, char const* s2) {
    register char* last;
    register char* current;

    last = NULL;

    if (*s2 != '\0') {
        current = strstr(s1, s2); // s2在s1中第一次出现的位置
        while (current != NULL) {
            last = current;
            current = strstr(last + 1, s2); // 继续下探
        }
    }
    return last;
}

// 从s1中找最后一次出现s2的位置（指针）
char* strstr_last(const char* s1, const char* s2) {
    char* last = NULL, * cur = NULL;
    // 空串'\0'最后一次出现的位置置NULL，可自定义
    if (s2 == NULL || *s2 == 0) return NULL;

    cur = strstr(s1, s2); // s2在s1中第一次出现的位置(指针)
    while (cur != NULL) {
        last = cur;
        cur = strstr(last + 1, s2); // 继续下探
    }
    return last;
}

int main() {
    char* s1 = "vabcdabcpod", * s2 = "abc";
    char* pFirst = strstr(s1, s2);
    int idx_first = pFirst - s1;
    printf("pFirst = %s, idx_first = %d\n", pFirst, idx_first);

    char* pLast = strstr_last(s1, s2);
    int idx_last = pLast - s1;
    printf("pLast = %s, idx_first = %d\n", pLast, idx_last);

    char* pLast2 = strrstr2(s1, s2);
    int idx_last2 = pLast2 - s1;
    printf("pLast = %s, idx_first = %d\n", pLast2, idx_last2);
    // pFirst = abcdabcpod, idx_first = 1
    // pLast = abcpod, idx_first = 5
}