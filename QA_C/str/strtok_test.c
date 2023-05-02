#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void split_tokens(char* line, char* split) {
    printf("\nsplit=[%s].\n", split);
    // 写法1
    // for (char* token = strtok(line, split); token != NULL; token = strtok(NULL, split)) {
    //     printf("Next token is '%s'\n", token);
    // }

    // 写法2
    int cnt = 1;
    char* token = strtok(line, split); // strtok参数1不可以以“字面量/const”的形式传入！！！段错误！【见q71.c main】
    while (token != NULL) {
        printf("token [%d] is '%s'\n", cnt++, token);
        token = strtok(NULL, split); // strtok传入NULL：从原串上一次切分的位置继续tok
        // 每次切分的token都是不含有split字符的，因此无法作为strtok的入参(返回token自身，切分失败)
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
    static char whitespace[] = "\t\f\r\v\n"; // 按所有空白字符分割
    split_tokens(line, whitespace); // 返回后，会修改原串，只保留切割的第一部分！！！
    printf("(After 1st strtok) line: '%s'\n", line);
    // (After 1st strtok) line: 'Linux was first developed for'

    printf("\n\nCASE 2: split ' ' ");
    char* split = " ";
    split_tokens(line, split);
    printf("(After 1st strtok) line: '%s'\n", line);
    // (After 1st strtok) line: 'Linux'

    printf("\n\nCASE 3-1: invalid tok");
    split_tokens(line, split); // 连续切分tok，则无效，返回line自身
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