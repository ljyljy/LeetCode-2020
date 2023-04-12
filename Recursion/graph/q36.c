#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>


bool isValidSudoku(char** board, int boardSize, int* boardColSize) {
    // int n_row = boardSize, n_col = boardColSize[0];
    const int n = 9, rangeCnt = 9; // n_row = n_col = 9,  valֵ��range=[1,9], ����rangeCnt=9
    bool row[n][rangeCnt]; // <�кţ�λͼ>
    bool col[n][rangeCnt]; // <�кţ�λͼ>
    bool submat[n][rangeCnt]; // <�Ӿ���idx��λͼ>
    memset(row, 0, sizeof(row)); // �����ʼ��������WA��
    memset(col, 0, sizeof(col));
    memset(submat, 0, sizeof(submat));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (board[i][j] == '.') continue;
            int num = board[i][j] - '0';// �±����1
            int idx = i / 3 * 3 + j / 3;
            if (row[i][num - 1] || col[j][num - 1] || submat[idx][num - 1]) {
                return false;
            }
            row[i][num - 1] = true, col[j][num - 1] = true, submat[idx][num - 1] = true;
        }
    }
    return true;
}

int main() {
    char* board[] = {
        "53..7....",
        "6..195...",
        ".98....6.",
        "8...6...3",
        "4..8.3..1",
        "7...2...6",
        ".6....28.",
        "...419..5",
        "....8..79"
    };
    int boardSize = 9, boardColSize[9] = { 9 };
    bool res = isValidSudoku(board, boardSize, boardColSize);
    printf("%d ", res); // 1
}