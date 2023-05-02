#include <stdio.h>
#include <stdlib.h>
#include <string.h>
// #include <stdbool.h>
// #include <math.h>
// #include <limits.h>

// q71.c

// 法1：栈
/* 知识点：
1) 字符串分割
  - `strtok(非字面量.str/NULL, "/");`
    - 参数2需要"双引号"！
    - 参数1不可以以“字面量”的形式传入！段错误！【见q71.c main】

2) 字符串比较
     - strcmp(str1, str2)==0

3) 最后保存结果，需要【逆序】遍历栈元素stk[i]
     - for i=[0, top-1], i++即可！
*/
char* simplifyPath(char* path) {
    int n = strlen(path);
    // 1) 字符串分割"/"
    char* words[n + 1]; // 各级目录(char*)的数组
    memset(words, 0, sizeof(words));
    int curCnt = 0;

    char* word;
    word = strtok(path, "/");
    while (word != NULL) {
        words[curCnt] = (char*)calloc(strlen(word) + 1, sizeof(char));
        words[curCnt++] = strcpy(words[curCnt], word);
        word = strtok(NULL, "/");
    }
    // // 遍历words
    // for (int i = 0; i < curCnt; i++) {
    //     printf("%s,", words[i]);
    // }

    // 2) 栈 - 简化路径(字符串比较：strcmp==0)
    char* stk[n], top = 0;
    for (int i = 0; i < curCnt; i++) {
        if (strcmp(words[i], "..") == 0) {
            if (top > 0) --top; // 非空，则弹栈
        }
        else if (strcmp(words[i], ".") == 0) {
            continue;
        }
        else { // 普通字符
            stk[top++] = words[i];
        }
    }
    // 3-1) 特判空
    if (top <= 0) return "/";

    // 3-2) 逆序保存res
    char* res = (char*)calloc(n + 1, sizeof(char)); // '\0'需+1
    for (int i = 0; i <= top - 1; i++) { // 逆序访问栈！
        strcat(res, "/");
        strcat(res, stk[i]);
    }
    // 以下错误：
    // while (top) {
    //     strcat(res, "/");
    //     char* word = stk[--top];
    //     strcat(res, word); // 错误！需要头插法！
    // }
    return res;
}

int main() {
    char path[100] = "/a/./b/../../c/";
    char* res = simplifyPath(path);
    printf("%s\n", res); // "/c"

    // char* path2 = "/home//foo/"; // strtok参数1不可以以“字面量”的形式传入！段错误！【见q71/q58 main】
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