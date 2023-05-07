#include <stdio.h>
#include <stdlib.h>
#include <string.h>
// #include <math.h>
// #include <limits.h>

// q68.c

/* 思路：
1) n_row 答案的行数，无法直接计算。只能遍历时，计算出来。
- 根据last提示，每行至少一个完整单词（最多wordsSize行）
2) strcat(str, " "); 就算后缀单个字符char，也必须使用双引号！！！
3) 保存每行的字符串，`List<String>` -- `char* curWords[n];` // 每行重置，保存本行单词列表(指针数组)
4）打印双引号，使用转义字符[\"]
*/

void fillBlanks(char* str, int fillCnt) {
    for (int k = 0; k < fillCnt; k++) {
        strcat(str, " ");
    }
}

char** fullJustify(char** words, int n, int maxWidth, int* returnSize) {
    char** res = (char**)calloc(n, sizeof(char*));
    memset(res, 0, sizeof(n));

    int curLine = 0;

    for (int i = 0; i < n; i++) {
        res[curLine] = (char*)calloc(maxWidth + 1, sizeof(char)); // '\0'，后续需将words_curRow串联n个空格，放入其中

        char* curWords[n]; // 每行重置，保存本行单词列表(指针数组)
        memset(curWords, 0, sizeof(curWords)); // List<String> 当前行的words

        int curLen = 0;
        int curCnt = 0; // 本行单词数
        // // ↓ 可并入while循环
        // curWords[curCnt++] = words[i]; // 当前单词必入该行
        // curLen += strlen(words[i++]); // 由题，一个单词.长度一定不超过每行最大长度
        while (i < n && curLen + curCnt + strlen(words[i]) <= maxWidth) {
            // 纯单词累积长度+至少所需空格数（即单词个数curCnt）+下一个单词长度 <= 本行最大长度 ↑
            curWords[curCnt++] = words[i];
            curLen += strlen(words[i++]);
            // printf("wordCnt=%d, i = %d\n", curCnt, i);
        }

        i -= 1; // 退出while时，i会额外自增1，需减去: i指向本行最后一个单词
        // printf("wordCnt=%d, i = %d\n", curCnt, i);
        int spaceTotalWidth = maxWidth - curLen;
        int spaceCnt = curCnt - 1;
        if (curCnt == 1) spaceCnt = 1;
        // printf("curRow=%d: spaceTotalWidth=%d, spaceCnt = %d\n", curRow, spaceTotalWidth, spaceCnt);

        if (i == n - 1) { // 特判1：最后一行左对齐
            // printf("LastRow!\n");
            for (int j = 0; j < curCnt; j++) {
                strcat(res[curLine], curWords[j]);
                if (strlen(res[curLine]) + 1 <= maxWidth) { // 防止填充完最后一个字符，长度恰好到达maxWidth
                    strcat(res[curLine], " "); // 哪怕仅后缀一个字符，也必须双引号！
                }
            }
            fillBlanks(res[curLine], maxWidth - strlen(res[curLine]));
            curLine++;
            break;
        }

        if (curCnt == 1) { // 特判2：仅一个单词占一行，则左对齐。
            // printf("wordCnt == 1 !\n");
            strcat(res[curLine], words[i]);
            fillBlanks(res[curLine], maxWidth - strlen(res[curLine]));
            curLine++;
            continue; // 不一定是最后一行！不可break！
        }

        int spaceWidth = spaceTotalWidth / spaceCnt;
        int spaceRemain = spaceTotalWidth % spaceCnt; // // 空格无法均分，靠左间隙多补充一个" "，直到余数=0
        // printf("curRow=%d: spaceWidth=%d, spaceRemain = %d\n", curRow, spaceWidth, spaceRemain);

        for (int j = 0; j < curCnt; j++) {
            // 首个单词前，不加空格↓
            if (j != 0) fillBlanks(res[curLine], spaceWidth);
            if (j != 0 && spaceRemain > 0) { // 从左往右数，每个空格处补一个" "
                strcat(res[curLine], " ");
                spaceRemain--;
            }
            strcat(res[curLine], curWords[j]);
        }
        curLine++;
    }

    *returnSize = curLine;
    return res;
}

int main() {
    // char* words[] = {"This", "is", "an", "example", "of", "text", "justification."};
    // int maxWidth = 16;
    // char* words[] = {"What", "must", "be", "acknowledgment", "shall", "be"};
    // int maxWidth = 16;
    // char* words[] = {"Science", "is", "what", "we", "understand", "well", "enough", "to", "explain",
    //                  "to", "a", "computer.", "Art", "is", "everything", "else", "we", "do"};
    // int maxWidth = 20;
    char* words[] = { "Listen", "to", "many,", "speak", "to", "a", "few." };
    int maxWidth = 6;
    int wordsSize = sizeof(words) / sizeof(words[0]);
    int returnSize = 0;
    char** rows = fullJustify(words, wordsSize, maxWidth, &returnSize);
    for (int i = 0; i < returnSize; i++) {
        printf("\"%s\"\n", rows[i]); // 打印双引号，使用转义字符[\"]
    }
    return 0;
}