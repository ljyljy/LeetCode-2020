package Recursion.graph;

import java.util.Scanner;

public class q37_HJ44_sudoku_solver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int[][] grid = new int[9][9];
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    grid[i][j] = sc.nextInt();
                }
            }
            if (dfs(grid)) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        System.out.print(grid[i][j] + " ");
                    }
                    System.out.println();
                }
            }
        }
    }

    private static boolean dfs(int[][] grid) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] != 0) continue;
                for (int k = 0; k <= 9; k++) {
                    if (check(grid, i, j, k)) {
                        grid[i][j] = k;
                        if (dfs(grid)) {
                            return true;
                        }
                        grid[i][j] = 0;
                    }
                }
                return false;
            }
        }
        return true;
    }

    private static boolean check(int[][] grid, int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (grid[i][col] == num) {
                return false;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (grid[row][i] == num) {
                return false;
            }
        }

        int row0 = row / 3 * 3, col0 = col / 3 * 3;
        for (int i = row0; i < row0 + 3; i++) {
            for (int j = col0; j < col0 + 3; j++) {
                if (grid[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }
}
