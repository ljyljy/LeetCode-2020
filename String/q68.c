#include <stdio.h>
#include <stdlib.h>
#include <string.h>
// #include <math.h>
// #include <limits.h>

// q68.c

/* ˼·��
1) n_row �𰸵��������޷�ֱ�Ӽ��㡣ֻ�ܱ���ʱ�����������
- ����last��ʾ��ÿ������һ���������ʣ����wordsSize�У�
2) strcat(str, " "); �����׺�����ַ�char��Ҳ����ʹ��˫���ţ�����
3) ����ÿ�е��ַ�����`List<String>` -- `char* curWords[n];` // ÿ�����ã����汾�е����б�(ָ������)
4����ӡ˫���ţ�ʹ��ת���ַ�[\"]
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
        res[curLine] = (char*)calloc(maxWidth + 1, sizeof(char)); // '\0'�������轫words_curRow����n���ո񣬷�������

        char* curWords[n]; // ÿ�����ã����汾�е����б�(ָ������)
        memset(curWords, 0, sizeof(curWords)); // List<String> ��ǰ�е�words

        int curLen = 0;
        int curCnt = 0; // ���е�����
        // // �� �ɲ���whileѭ��
        // curWords[curCnt++] = words[i]; // ��ǰ���ʱ������
        // curLen += strlen(words[i++]); // ���⣬һ������.����һ��������ÿ����󳤶�
        while (i < n && curLen + curCnt + strlen(words[i]) <= maxWidth) {
            // �������ۻ�����+��������ո����������ʸ���curCnt��+��һ�����ʳ��� <= ������󳤶� ��
            curWords[curCnt++] = words[i];
            curLen += strlen(words[i++]);
            // printf("wordCnt=%d, i = %d\n", curCnt, i);
        }

        i -= 1; // �˳�whileʱ��i���������1�����ȥ: iָ�������һ������
        // printf("wordCnt=%d, i = %d\n", curCnt, i);
        int spaceTotalWidth = maxWidth - curLen;
        int spaceCnt = curCnt - 1;
        if (curCnt == 1) spaceCnt = 1;
        // printf("curRow=%d: spaceTotalWidth=%d, spaceCnt = %d\n", curRow, spaceTotalWidth, spaceCnt);

        if (i == n - 1) { // ����1�����һ�������
            // printf("LastRow!\n");
            for (int j = 0; j < curCnt; j++) {
                strcat(res[curLine], curWords[j]);
                if (strlen(res[curLine]) + 1 <= maxWidth) { // ��ֹ��������һ���ַ�������ǡ�õ���maxWidth
                    strcat(res[curLine], " "); // ���½���׺һ���ַ���Ҳ����˫���ţ�
                }
            }
            fillBlanks(res[curLine], maxWidth - strlen(res[curLine]));
            curLine++;
            break;
        }

        if (curCnt == 1) { // ����2����һ������ռһ�У�������롣
            // printf("wordCnt == 1 !\n");
            strcat(res[curLine], words[i]);
            fillBlanks(res[curLine], maxWidth - strlen(res[curLine]));
            curLine++;
            continue; // ��һ�������һ�У�����break��
        }

        int spaceWidth = spaceTotalWidth / spaceCnt;
        int spaceRemain = spaceTotalWidth % spaceCnt; // // �ո��޷����֣������϶�ಹ��һ��" "��ֱ������=0
        // printf("curRow=%d: spaceWidth=%d, spaceRemain = %d\n", curRow, spaceWidth, spaceRemain);

        for (int j = 0; j < curCnt; j++) {
            // �׸�����ǰ�����ӿո��
            if (j != 0) fillBlanks(res[curLine], spaceWidth);
            if (j != 0 && spaceRemain > 0) { // ������������ÿ���ո񴦲�һ��" "
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
        printf("\"%s\"\n", rows[i]); // ��ӡ˫���ţ�ʹ��ת���ַ�[\"]
    }
    return 0;
}