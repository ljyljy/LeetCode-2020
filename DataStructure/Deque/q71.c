#include <stdio.h>
#include <stdlib.h>
#include <string.h>
// #include <stdbool.h>
// #include <math.h>
// #include <limits.h>

// q71.c

// ��1��ջ
/* ֪ʶ�㣺
1) �ַ����ָ�
  - `strtok(��������.str/NULL, "/");`
    - ����2��Ҫ"˫����"��
    - ����1�������ԡ�������������ʽ���룡�δ��󣡡���q71.c main��

2) �ַ����Ƚ�
     - strcmp(str1, str2)==0

3) ��󱣴�������Ҫ�����򡿱���ջԪ��stk[i]
     - for i=[0, top-1], i++���ɣ�
*/
char* simplifyPath(char* path) {
    int n = strlen(path);
    // 1) �ַ����ָ�"/"
    char* words[n + 1]; // ����Ŀ¼(char*)������
    memset(words, 0, sizeof(words));
    int curCnt = 0;

    char* word;
    word = strtok(path, "/");
    while (word != NULL) {
        words[curCnt] = (char*)calloc(strlen(word) + 1, sizeof(char));
        words[curCnt++] = strcpy(words[curCnt], word);
        word = strtok(NULL, "/");
    }
    // // ����words
    // for (int i = 0; i < curCnt; i++) {
    //     printf("%s,", words[i]);
    // }

    // 2) ջ - ��·��(�ַ����Ƚϣ�strcmp==0)
    char* stk[n], top = 0;
    for (int i = 0; i < curCnt; i++) {
        if (strcmp(words[i], "..") == 0) {
            if (top > 0) --top; // �ǿգ���ջ
        }
        else if (strcmp(words[i], ".") == 0) {
            continue;
        }
        else { // ��ͨ�ַ�
            stk[top++] = words[i];
        }
    }
    // 3-1) ���п�
    if (top <= 0) return "/";

    // 3-2) ���򱣴�res
    char* res = (char*)calloc(n + 1, sizeof(char)); // '\0'��+1
    for (int i = 0; i <= top - 1; i++) { // �������ջ��
        strcat(res, "/");
        strcat(res, stk[i]);
    }
    // ���´���
    // while (top) {
    //     strcat(res, "/");
    //     char* word = stk[--top];
    //     strcat(res, word); // ������Ҫͷ�巨��
    // }
    return res;
}

int main() {
    char path[100] = "/a/./b/../../c/";
    char* res = simplifyPath(path);
    printf("%s\n", res); // "/c"

    // char* path2 = "/home//foo/"; // strtok����1�������ԡ�������������ʽ���룡�δ��󣡡���q71/q58 main��
    char path2[100] = "/home//foo/";
    char* res2 = simplifyPath(path2);
    printf("%s\n", res2); // "/home/foo"

    char path3[100] = "/../";
    res = simplifyPath(path3);
    printf("%s\n", res); // "/"

    char path4[100] = "/home/";
    res = simplifyPath(path4);
    printf("%s\n", res); // "/home"
    return 0;
}