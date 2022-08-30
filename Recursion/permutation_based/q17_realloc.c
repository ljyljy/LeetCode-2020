#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>

/**
* �ӣ�3'
    1. �����е�path����̬��ɾ����¼�±�pathLen
    2. res��̬���ݣ� ��̬malloc & realloc��
    3. ȫ�ֱ�����ʼ������������ں�������ʼ���������Ӱ��LC���������case��
*/

char* map[10] = { "", "", "abc", "def", "ghi", "jkl", \
                  "mno", "pqrs", "tuv", "wxyz" };

char** res;
char* path;
int basicSize;
int curCnt, pathLen; // path�������¼�±꣡����������ں�����ʼ��������Ḳ��LC����cases����
int n;

void dfs(char* digits, int idx) {
    if (strlen(path) == strlen(digits)) {
        res[curCnt] = (char*)malloc(sizeof(char) * (strlen(path) + 1));
        strcpy(res[curCnt++], path);
        // printf("path: %s\n", path);
        if (curCnt == basicSize) { // res��̬���ݣ�
            basicSize *= 2;
            res = (char**)realloc(res, sizeof(char*) * basicSize);
        }
        return;
    }

    char* letters = map[digits[idx] - '0']; // ��ѡ��ĸ
    // printf("digits[idx]=%c, letters: %s\n", digits[idx], letters);
    for (int i = 0; i < strlen(letters); i++) {
        // path��̬��ɾ��������Ҫ��¼pathLen�±꣡
        path[pathLen++] = letters[i]; path[pathLen] = 0;
        dfs(digits, idx + 1);
        path[--pathLen] = 0;
    }

}
char** letterCombinations(char* digits, int* returnSize) {
    if (!digits || strlen(digits) == 0) {
        *returnSize = 0;
        return res;
    }
    n = strlen(digits);
    curCnt = 0, pathLen = 0, basicSize = 8;

    res = (char**)malloc(sizeof(char*) * basicSize); // ��̬���ݣ���ֹ�������
    path = (char*)malloc(sizeof(char) * (n + 1));
    memset(path, 0, sizeof(char) * (n + 1));
    // for (int i = 0; i < basicSize; i++) { // res[i]��Ҫ��̬���ݣ�
    //     res[i] = (char*)malloc(sizeof(char) * n); // res[i]����=n
    //     memset(res[i], 0, sizeof(char) * n);
    // }
    dfs(digits, 0);
    *returnSize = curCnt;
    return res;
}

int main() {
    // // �ַ���ƴ��
    // char* digits = "23"; char* digits2 = "56789";
    // char* tmp = (char*)malloc(sizeof(char) * (strlen(digits) + strlen(digits2) + 1));
    // memset(tmp, 0, sizeof(char) * (strlen(digits) + strlen(digits2) + 1));
    // strcat(tmp, digits); strcat(tmp, digits2);
    // printf("tmp=%s\n", tmp);

    char* digits = "23";
    int* returnSize = (int*)malloc(sizeof(int) * 1);
    memset(returnSize, 0, sizeof(int));
    char** res = letterCombinations(digits, returnSize);
    for (int i = 0; i < *returnSize; i++) {
        printf("%s\n", res[i]);
    }

}


// ����汾��pathû�л��ݣ���̬��ɾ��
// void dfs_heapOverFlow(char* digits, int idx, char* path) {
//     if (strlen(path) == strlen(digits)) {
//         res[curCnt] = (char*)malloc(sizeof(char) * (strlen(path) + 1));
//         strcpy(res[curCnt++], path);
//         // printf("path: %s\n", path);
//         if (curCnt == basicSize) { // res��̬���ݣ�
//             basicSize *= 2;
//             res = (char**)realloc(res, sizeof(char*) * basicSize);
//         }
//         return;
//     }

//     char* letters = map[digits[idx] - '0']; // ��ѡ��ĸ
//     // printf("digits[idx]=%c, letters: %s\n", digits[idx], letters);
//     for (int i = 0; i < strlen(letters); i++) {
//         //���ӡ� newPath = path + letters[i]
//      �������е�path׷�ӣ��޷�realloc��ֻ��malloc newPath����strcpy/strcat + newPath[-1]��ֵchar + ĩβ0
//      path��Ҫ���ݣ���Ҫ��̬��ɾ�� �� �޷�������
//         // ��1��strcat / strcpy
//         // char* tmp = malloc(sizeof(char) * 2); // �����ַ���"letters[i] + \0"
//         // tmp[0] = letters[i], tmp[1] = 0;
//         // char* newPath = (char*)malloc(sizeof(char) * (strlen(path) + strlen(tmp) + 1));
//         // memset(newPath, 0, sizeof(newPath));
//         // strcat(newPath, path); strcat(newPath, tmp);

//         //// ��2��strcpy + ƫ��ĩλ��ֵchar
//         char* newPath = (char*)malloc(sizeof(char) * (strlen(path) + 1 + 1)); // 1=strlen(letters[i])
//         memset(newPath, 0, sizeof(newPath)); // ĩβΪ\0
//         strcpy(newPath, path);
//         newPath[strlen(path)] = letters[i]; // �ַ�ת�ַ���
//         dfs0(digits, idx + 1, newPath);
//     }

// }
