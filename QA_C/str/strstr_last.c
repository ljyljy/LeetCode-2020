#include <string.h>
#include <stdio.h>
#include <stdlib.h>


// ��str�������һ�γ���sub��λ�ã�ָ�룩
char* strstr_last(const char* str, const char* sub) {
    register char* cur = NULL, * nxt = NULL;
    // �մ�'\0'���һ�γ��ֵ�λ����NULL�����Զ���
    if (sub == NULL || *sub == 0) return NULL;

    nxt = strstr(str, sub); // sub��str�е�һ�γ��ֵ�λ��(ָ��)
    while (nxt != NULL) {
        cur = nxt;
        nxt = strstr(cur + 1, sub); // ������̽
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