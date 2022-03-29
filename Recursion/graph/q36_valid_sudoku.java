package Recursion.graph;

public class q36_valid_sudoku {
    public boolean isValidSudoku(char[][] board) {
        int n = 9; // board.length;
        boolean[][] row = new boolean[n][n]; // <�кţ�λͼ>
        boolean[][] col = new boolean[n][n]; // <�кţ�λͼ>
        boolean[][] subMat = new boolean[n][n]; // <idx��λͼ>
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int idx = i / 3 * 3 + j / 3; // ��άתһά?���Ӿ���idx=[0,8]
                if (board[i][j] == '.') continue;
                int num = board[i][j] - '0';
                if (row[i][num-1] || col[j][num-1] || subMat[idx][num-1])
                    return false;
                row[i][num-1] = col[j][num-1] = subMat[idx][num-1] = true;
            }
        }
        return true;
    }
}
