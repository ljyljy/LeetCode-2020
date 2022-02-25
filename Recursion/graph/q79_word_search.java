package Recursion.graph;

public class q79_word_search {
    int m, n;
    char[][] board;
    char[] words;
    int[][] dirs = {{1,0}, {0,1}, {-1, 0}, {0, -1}}; // 四个方向
    public boolean exist(char[][] board, String word) {
        m = board.length; n = board[0].length;
        this.board = board; words = word.toCharArray();
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // if (!visited[i][j]) {
                visited[i][j] = true;
                if (dfs(0, i, j, visited)) {
                    return true;
                }
                visited[i][j] = false;
                // }
            }
        }
        return false;
    }

    private boolean dfs(int idx, int i, int j, boolean[][] visited) {
        if (words[idx] != board[i][j]) return false;// 条件1：大前提！放在最首！
        if (idx >= words.length-1) return true; // ?易错：必须-1！且必须在条件1后面！
        visited[i][j] = true;
        for (int[] dir: dirs) {
            int new_i = i + dir[0];
            int new_j = j + dir[1];
            if (check(new_i, new_j, visited)) {
                visited[new_i][new_j] = true;
                if (dfs(idx+1, new_i, new_j, visited)) {
                    return true;
                }
                visited[new_i][new_j] = false;
            }
        }
        return false;
    }

    private boolean check(int i, int j, boolean[][] visited) {
        return 0 <= i && i < m && 0 <= j && j < n && !visited[i][j];
    }
}
