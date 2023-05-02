#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void split_tokens(char* line, char* split) {
    printf("\nsplit=[%s].\n", split);
    // д��1
    // for (char* token = strtok(line, split); token != NULL; token = strtok(NULL, split)) {
    //     printf("Next token is '%s'\n", token);
    // }

    // д��2
    int cnt = 1;
    char* token = strtok(line, split); // strtok����1�������ԡ�������/const������ʽ���룡�����δ��󣡡���q71.c main��
    while (token != NULL) {
        printf("token [%d] is '%s'\n", cnt++, token);
        token = strtok(NULL, split); // strtok����NULL����ԭ����һ���зֵ�λ�ü���tok
        // ÿ���зֵ�token���ǲ�����split�ַ��ģ�����޷���Ϊstrtok�����(����token�����з�ʧ��)
    }
}

int main() {
    // char* line = "25, 142, 330, Smith, J, 239-4123";
    char line[] = "Linux was first developed for\t\t 386/486-based\t pcs.";
    printf("line: %s\n", line);

    char line0[100];
    strcpy_s(line0, sizeof(line), line); // v1
    // memcpy_s(line0, sizeof(line), line, sizeof(line)); // v2
    printf("line0: %s\n", line0);

    printf("\n\nCASE 1: split ALL whitespaces['\\t\\f\\r\\v\\n'] ");
    static char whitespace[] = "\t\f\r\v\n"; // �����пհ��ַ��ָ�
    split_tokens(line, whitespace); // ���غ󣬻��޸�ԭ����ֻ�����и�ĵ�һ���֣�����
    printf("(After 1st strtok) line: '%s'\n", line);
    // (After 1st strtok) line: 'Linux was first developed for'

    printf("\n\nCASE 2: split ' ' ");
    char* split = " ";
    split_tokens(line, split);
    printf("(After 1st strtok) line: '%s'\n", line);
    // (After 1st strtok) line: 'Linux'

    printf("\n\nCASE 3-1: invalid tok");
    split_tokens(line, split); // �����з�tok������Ч������line����
    printf("(After 1st strtok) line: '%s'\n", line);
    // (After 1st strtok) line: 'Linux'

    printf("\nCASE 3-2: split '-' on original line0");
    char* split2 = "-";
    split_tokens(line0, split2);
    printf("(After 1st strtok) line0: '%s'\n", line0);
    return 0;
}

/**
line: Linux was first developed for              386/486-based   pcs.
line0: Linux was first developed for             386/486-based   pcs.


CASE 1: split ALL whitespaces['\t\f\r\v\n']
split=[


].
token [1] is 'Linux was first developed for'
token [2] is ' 386/486-based'
token [3] is ' pcs.'
(After 1st strtok) line: 'Linux was first developed for'


CASE 2: split ' '
split=[ ].
token [1] is 'Linux'
token [2] is 'was'
token [3] is 'first'
token [4] is 'developed'
token [5] is 'for'
(After 1st strtok) line: 'Linux'


CASE 3-1: invalid tok
split=[ ].
token [1] is 'Linux'
(After 1st strtok) line: 'Linux'

CASE 3-2: split '-' on original line0
split=[-].
token [1] is 'Linux was first developed for              386/486'
token [2] is 'based      pcs.'
(After 1st strtok) line0: 'Linux was first developed for                 386/486'
 */