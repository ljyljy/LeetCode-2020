#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>

// 37. ������
// https://leetcode-cn.com/problems/sudoku-solver/
// ˼·�����ݷ� DFS
// ʱ�临�Ӷȣ�O(9^m) mΪ�����Ŀո���
// �ռ临�Ӷȣ�O(m)


/* �ӣ�
1��dfs�����ķ���ֵ��bool
2���������Ϊchar** board�����������д�� & ���ó���(q37 vs q36)
*/
bool dfs(char** board, int n);
bool isValid(char** board, int row, int col, char c);

void solveSudoku(char** board, int boardSize, int* boardColSize) {
    // int n_row = boardSize, n_col = boardColSize[0];
    int n = 9; // n_row = n_col = 9
    bool res = dfs(board, n);
    printf("res = %d \n", res); // 1
}

bool dfs(char** board, int n) {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (board[i][j] != '.') continue;
            for (char c = '1'; c <= '9'; c++) {
                if (!isValid(board, i, j, c)) continue;
                board[i][j] = c;
                if (dfs(board, n)) return true;
                board[i][j] = '.';
            }
            return false;// ����©���޽⣬����
        }
    }
    return true;// ����©���ҵ��⣬����
}

bool isValid(char** board, int row, int col, char c) {
    for (int i = 0; i < 9; i++) {
        if (board[i][col] == c) return false;
        if (board[row][i] == c) return false;
    }
    // ��ǰ�Ӿ���(3*3)����Ԫ���±����char cΨһ
    int rowStart = row / 3 * 3, colStart = col / 3 * 3;
    for (int i = rowStart; i < rowStart + 3; i++) {
        for (int j = colStart; j < colStart + 3; j++) {
            if (board[i][j] == c) return false;
        }
    }
    return true;
}

int main() {
    /* ���test_CharStar_CharArr.c
    char* str0 = "53..7...X";
    str0[2] = '3'; // �����У���Ϊstr0��const char*
    printf("str0 = %s \n", str0);

    */

    // strs[i]���������ַ����滻(��������ֻ��)������strs[i][j]���У���Ϊstrs[i][j]��const char
    char* strs[] = { // �ַ�������
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

    // // strs[0][2] = '3'; // error: assignment of read-only location ��*(strs[0] + 2)��
    // printf("strs[0] = %s \n", strs[0]);
    // printf("strs[0][2] = %c \n", strs[0][2]);

    char** board = (char**)calloc(10 * 10, sizeof(char));
    for (int i = 0; i < 9; i++) {
        board[i] = (char*)calloc(10, sizeof(char));
        strcpy(board[i], strs[i]);
    }

    int boardSize = 9, boardColSize[9] = { 9 };
    solveSudoku(board, boardSize, boardColSize);

    for (int i = 0; i < boardSize; i++) {
        printf("%s \n", board[i]);
    }
}