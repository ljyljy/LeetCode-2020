#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>

int main() {
    char* cp = "9001 leetcode.com";
    char* ans = strchr(cp, ' '); // strchr���ص�һ�γ���' '�ĵ�ַ���Ӵ���
    int spaceIdx = strchr(cp, ' ') - cp; // �õ��ַ����ָ��idx
    // spaceHdr= leetcode.com, spaceIdx=4
    printf("spaceHdr=%s, spaceIdx=%d\n", ans, spaceIdx);
    return 0;
}