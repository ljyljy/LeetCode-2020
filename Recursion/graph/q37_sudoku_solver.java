package Recursion.graph;

public class q37_sudoku_solver {
    public void solveSudoku(char[][] board) {
        dfs(board); // 二维递归(行&列);
        // ↑?无需传入状态idx1/idx2; ?直接对board内修改;
    }

    private boolean dfs(char[][] board) { // ?返回值为bool!
        // 无递归出口(return T/F即可)
        for (int i = 0; i < 9; i++) { // board.length==9
            for (int j = 0; j < 9; j++) { // board[0].length==9
                if (board[i][j] != '.') continue; // 非空格，跳过
                for (char ch = '1'; ch <= '9'; ch++) {
                    if (check(i, j, ch, board)) {
                        board[i][j] = ch;
                        if (dfs(board)) {
                            return true;
                        }
                        board[i][j] = '.';
                    }
                }
                return false;
            }
        }
        return true;
    }

    private boolean check(int row, int col, char ch, char[][] board) {
        for (int i = 0; i < 9; i++) { // 同行，ch唯一
            if (board[i][col] == ch) {
                return false;
            }
        }
        for (int j = 0; j < 9; j++) {// 同列，ch唯一
            if (board[row][j] == ch) {
                return false;
            }
        }
        // 当前子矩阵(3*3)，ch唯一
        int startRow =  (row / 3) * 3;// 0,1,2--0; 3,4,5--1; 6,7,8--2
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == ch) {
                    return false;
                }
            }
        }
        return true;
    }
}
