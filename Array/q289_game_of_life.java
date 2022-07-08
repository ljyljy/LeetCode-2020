package Array;

public class q289_game_of_life {
    // 数组-模拟
    // 进阶1：原地修改（board[i][j]新值存于十位，旧值0/1存于个位，更新时>>1即可覆盖旧值）
    public void gameOfLife(int[][] board) {
        if (board == null) return;
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int liveAround = 0;
                // 边界！或：for (x = i-1; x <= i+1; x++) 然后check x是否越界
                for (int x = Math.max(i-1, 0); x <= Math.min(i+1, m-1); x++) {
                    for (int y = Math.max(j-1, 0); y <= Math.min(j+1, n-1); y++) {
                        if (x == i && y == j) continue;
                        if ((board[x][y] & 1) == 1) { // 【勿忘括号！'&'的优先级低！】
                            liveAround++;
                        }
                    }
                }
                int cur = board[i][j] & 1, nxt;
                if (cur == 1) { // 当前活
                    if (liveAround < 2 || liveAround > 3) {
                        nxt = 0;
                    } else nxt = 1; // liveAround == 2或3
                } else { // 当前死
                    if (liveAround == 3) nxt = 1;
                    else nxt = 0;
                }
                board[i][j] |= (nxt << 1); // 原地修改: 新值nxt存于十位
            }
        }

        // 原地修改2：新值（十位>>1）覆盖旧值（个位）
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] >>= 1;
            }
        }
    }
}
