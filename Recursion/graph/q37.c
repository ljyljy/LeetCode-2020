#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>

// 37. 解数独
// https://leetcode-cn.com/problems/sudoku-solver/
// 思路：回溯法 DFS
// 时间复杂度：O(9^m) m为待填充的空格数
// 空间复杂度：O(m)


/* 坑：
1）dfs函数的返回值：bool
2）函数入参为char** board，输入输出的写法 & 适用场景(q37 vs q36)
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
            return false;// 【勿漏】无解，回溯
        }
    }
    return true;// 【勿漏】找到解，回溯
}

bool isValid(char** board, int row, int col, char c) {
    for (int i = 0; i < 9; i++) {
        if (board[i][col] == c) return false;
        if (board[row][i] == c) return false;
    }
    // 当前子矩阵(3*3)的首元素下标↓，char c唯一
    int rowStart = row / 3 * 3, colStart = col / 3 * 3;
    for (int i = rowStart; i < rowStart + 3; i++) {
        for (int j = colStart; j < colStart + 3; j++) {
            if (board[i][j] == c) return false;
        }
    }
    return true;
}

int main() {
    /* 详见test_CharStar_CharArr.c
    char* str0 = "53..7...X";
    str0[2] = '3'; // 不可行，因为str0是const char*
    printf("str0 = %s \n", str0);

    */

    // strs[i]可以整体字符串替换(字面量、只读)，但是strs[i][j]不行，因为strs[i][j]是const char
    char* strs[] = { // 字符串数组
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

    // // strs[0][2] = '3'; // error: assignment of read-only location ‘*(strs[0] + 2)’
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