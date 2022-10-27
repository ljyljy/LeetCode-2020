#include <string.h>
#include <stdio.h>
#include <stdlib.h>

// ��s1�������һ�γ���s2��λ��
char* strrstr2(char const* s1, char const* s2) {
    register char* last;
    register char* current;

    last = NULL;

    if (*s2 != '\0') {
        current = strstr(s1, s2); // s2��s1�е�һ�γ��ֵ�λ��
        while (current != NULL) {
            last = current;
            current = strstr(last + 1, s2); // ������̽
        }
    }
    return last;
}

// ��s1�������һ�γ���s2��λ�ã�ָ�룩
char* strstr_last(const char* s1, const char* s2) {
    char* last = NULL, * cur = NULL;
    // �մ�'\0'���һ�γ��ֵ�λ����NULL�����Զ���
    if (s2 == NULL || *s2 == 0) return NULL;

    cur = strstr(s1, s2); // s2��s1�е�һ�γ��ֵ�λ��(ָ��)
    while (cur != NULL) {
        last = cur;
        cur = strstr(last + 1, s2); // ������̽
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