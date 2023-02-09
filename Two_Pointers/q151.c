#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <math.h>
#include <string.h>
#include <limits.h>

void reverse(char* s, int start, int end) {
    while (start < end) {
        char tmp = s[start];
        s[start] = s[end];
        s[end] = tmp;
        start++; end--;
    }
}

// ˫ָ��
char* reverseWords(char* s) {
    int n = strlen(s);
    int fast = 0, slow = 0; // ��/������/д
    while (s[fast] == ' ') fast++; // ����ǰ��' '
    while (fast < n - 1) { // ��ʼ���&��д
        if (s[fast] == ' ' && s[fast + 1] == ' ') {
            fast++; // ע�⣺�������' '��ֻдһ��' '
        }
        else { // ��ֻ�е���' '������
            s[slow++] = s[fast++];
        }
    }
    // ����fast == n-1��slow�������ܡ�Ϊĩβ��[n-1]�Ƿ�Ϊ' '?��
    if (s[n - 1] != ' ') {
        s[slow++] = s[n - 1];
    }
    s[slow] = '\0'; // slow����β

    reverse(s, 0, slow - 1); // 1. ���巴ת
    // 2. ÿ��������Ҫ�ٵߵ�����
    for (int i = 0; i < slow; i++) {
        int j = i; // ��תs[i, j-1]
        while (j < slow && s[j] != ' ') j++; // j��Ϊ' '
        reverse(s, i, j - 1);
        i = j;
    }
    return s;
}

int main() {
    char s[] = "     a good   example  "; // ���ɶ���Ϊchar* s = "xxx"(�ַ�������)
    printf("%s\n", reverseWords(s));
    return 0;
}