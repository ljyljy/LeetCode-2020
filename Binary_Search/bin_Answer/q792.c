#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>
#include "uthash.h"

// typedef struct HashMap {
//     char key; // key
//     int* idxes; // val -> �滻Ϊint* idxes[26],ÿ��c��Ӧһ��int*����Ŷ�Ӧ���±꣨������
//     int len;
//     UT_hash_handle hh;
// } HashMap;

// ���� - strchr
int numMatchingSubseq(char* s, char** words, int wordsSize) {
    // int* idxes[26], idxesSize[26]; // idxes[c]��c��Ӧ���±꼯�ϣ�����ΪidxesSize[c]
    int cnt = 0;
    char* pattern = NULL;
    for (int i = 0; i < wordsSize; i++) {
        char* word = words[i];
        pattern = s;
        bool isSubSeq = true;
        for (int j = 0; j < strlen(word); j++) { // ����word�����ַ�c
            char c = word[j];
            // printf("pattern=%s, c=%c\n", pattern, c);
            pattern = strchr(pattern, c);
            // printf("NEW pattern = strchr(pattern, c)=%s\n", pattern);
            if (pattern == NULL) { // ��ǰword����������
                isSubSeq = false;
                break;
            }
            pattern += 1; // ����һλ
        }
        if (isSubSeq) cnt++;
    }
    return cnt;
}

int main() {
    char* s = "dsahjpjauf";
    char* words[4] = { "ahjpjau", "ja", "ahbwzgqnuk", "tnmlanowax" };
    int cnt = numMatchingSubseq(s, words, 4);
    printf("cnt = %d\n", cnt);
    return 0;
}

/**
"abcde"
["a","bb","acd","ace"]

pattern=abcde, c=a
NEW pattern = strchr(pattern, c)=abcde
pattern=abcde, c=b
NEW pattern = strchr(pattern, c)=bcde
pattern=cde, c=b
NEW pattern = strchr(pattern, c)=(null)
pattern=abcde, c=a
NEW pattern = strchr(pattern, c)=abcde
pattern=bcde, c=c
NEW pattern = strchr(pattern, c)=cde
pattern=de, c=d
NEW pattern = strchr(pattern, c)=de
pattern=abcde, c=a
NEW pattern = strchr(pattern, c)=abcde
pattern=bcde, c=c
NEW pattern = strchr(pattern, c)=cde
pattern=de, c=e
NEW pattern = strchr(pattern, c)=e

 *
 */