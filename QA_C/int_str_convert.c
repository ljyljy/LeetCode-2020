#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>
#include <stdbool.h>

// int -> str (ascii)
// ��1��itoa
void int2str(int num, int base) {
    // char* numStr = (char*)malloc(sizeof(char) * 4); // v1: �ַ���ָ���
    char numStr[10]; // v2: �ַ����� ��
    itoa(num, numStr, base); // val, char*ָ��, 10����
    int n = strlen(numStr); // char[]Ҳ����strlen
    printf("num = %d, numStr = %s, n = %d\r\n", num, numStr, n); // num = 123, numStr = 123, n = 3
    // free(numStr), numStr = NULL; // v1
}

// ��2��sprintf
void int2str_v2(int num) {
    char* numStr = (char*)malloc(sizeof(char) * 32);
    sprintf(numStr, "%d", num);
    printf("numStr_v2 = %s\n", numStr);
    free(numStr), numStr = NULL;
}

int main() {
    int num = 123, base = 10;
    // char* numStr; // ������NULL, main�в�����malloc��Ҳ����ac���������б���malloc��
    int2str(num, base);
    int2str_v2(num);

    printf("--------EXP 2-------\n");
    int cnt = 3;
    char cnt_c;
    itoa(cnt, &cnt_c, 10); // �� cnt_c = '0' + cnt
    printf("cnt_c = %c", cnt_c);
    return 0;
}

/* Output:
num = 123, numStr = 123, n = 3
numStr_v2 = 123
--------EXP 2-------
cnt_c = 3
*/