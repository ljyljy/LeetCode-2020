#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>

bool isFlipedString1(char* s1, char* s2) {
    int n = strlen(s1);
    if (n != strlen(s2)) return false;
    if (n == 0) return true;

    int i = 0, j = 0;
    for (i = 0; i < n; i++) {
        for (j = 0; j < n; j++) {
            if (s1[(i + j) % n] != s2[j]) {
                break;
            }
        }
        if (j == n) return true;
    }
    return false;
}

// ��2��kmp��أ��������ַ���
//   �ַ���s2+s2 ���������� s1����ͨ����ת�����õ����ַ�����ֻ��Ҫ��� s1�Ƿ�Ϊ s2 + s2�����ַ������ɡ�
// Java��return s1.length() == s2.length() && (s1 + s1).contains(s2);
bool isFlipedString(char* s1, char* s2) {
    int n = strlen(s1);
    if (n != strlen(s2)) return false;
    if (n == 0) return true;

    char* str = (char*)malloc(sizeof(char) * n * 2 + 1); // s+s��ռ�ֽ���
    sprintf(str, "%s%s", s2, s2);
    // snprintf(str, sizeof(char) * n * 2 + 1, "%s%s", s1, s2);
    return strstr(str, s1) != NULL; // ���ص�һ�γ���s1���׵�ַ
}
