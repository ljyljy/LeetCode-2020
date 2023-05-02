#include <string.h>
#include <stdio.h>
#include <stdlib.h>

// q58: 最后一个单词的长度

// 法1：strtok
int lengthOfLastWord0(char* s) {
    int lastLen = 0;
    char* token = strtok(s, " ");

    while (token) {
        // printf("%s\n", token);
        lastLen = strlen(token);
        token = strtok(NULL, " ");
    }
    return lastLen;
}

// 法2: 普通
int lengthOfLastWord(char* s) {
    int n = strlen(s);
    int i = 0;
    int lastLen = 0;
    while (i < n) {
        while (i < n && s[i] == ' ') i++; // 去除前导' '
        int j = i; // word_start: i
        while (j < n && s[j] != ' ') j++; // word_end: j-1
        if (j <= n) { // j == n到末尾
            // printf("[i, j-1] = [%d, %d]\n", i, j-1);
            lastLen = j - i; // word: [i, j-1]
        }
        i = j;
        while (i < n && s[i] == ' ') i++;// 去除后导' '，否则"moon   "答案为0！
    }
    return lastLen;
}

int main() {
    // char* s1 = "Hello World"; // 不可！strtok的参数1不可以以“字面量”的形式传入！段错误！【见q71/q58 main】
    char s1[100] = "Hello World";
    int len1 = lengthOfLastWord0(s1);
    printf("len1 = %d\n", len1);
    // len1 = 5

    char s2[100] = "   fly me   to   the moon  ";
    int len2 = lengthOfLastWord(s2);
    printf("len2 = %d\n", len2);
    // len2 = 4

    char s3[100] = "luffy is still joyboy";
    int len3 = lengthOfLastWord(s3);
    printf("len3 = %d\n", len3);
    // len3 = 6
}