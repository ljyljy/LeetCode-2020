#include <stdio.h>
#include <stdlib.h>
#include <string.h>


int main() {
    char* cp = "9001 leetcode.com ljyljyok";
    char* ans = strchr(cp, ' '); // strchr���ص�һ�γ���' '�ĵ�ַ���Ӵ���
    int spaceIdx = strchr(cp, ' ') - cp; // �õ��ַ����ָ��idx
    printf("spaceHdr=%s, spaceIdx=%d\n", ans, spaceIdx);
    // spaceHdr= leetcode.com  ljyljyok, spaceIdx=4

    char* ans_R = strrchr(cp, ' ');
    int spaceIdx_R = strrchr(cp, ' ') - cp;
    printf("spaceHdr_R=%s, spaceIdx_R=%d\n", ans_R, spaceIdx_R);
    // spaceHdr_R= ljyljyok, spaceIdx_R=17
    return 0;
}