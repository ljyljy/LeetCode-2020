package DataStructure.Union_Find;

import java.util.*;

public class q130_surrounded_regions {
    // 类比q130,547
    // 法1: DFS(掌握)-v1
    int[] _x = {1, 0, -1, 0};
    int[] _y = {0, 1, 0, -1};
    int m, n;
    char[][] board;

    public void solve_dfs(char[][] board) {
        m = board.length;
        n = board[0].length;
        this.board = board;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 对边界深搜，任何与边界相连的'O'都不可变'X',
                // 为与内陆'O'区分，dfs将边界'O'全部临时标记为‘*’
                if (!isBorder(i, j)) continue;
                if (board[i][j] == 'O') { // 写法2-1
                    dfsBorder(i, j);
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '*') board[i][j] = 'O'; // 恢复边界的'O'
                else board[i][j] = 'X';
            }
        }
    }

    private void dfsBorder(int x, int y) {
//        if (board[x][y] == 'X') return;  // 写法2-2(与写法2-1选一个即可)

//         ↓ 必须对graph做原地修改，visited无用！【类比q130、connectedZoneSort】
        board[x][y] = '*';// DFS的时候一定要将她改掉！不然会影响判断！可以不加visited，改掉以后自然就visited了
        for (int dir = 0; dir < 4; dir++) {
            int newX = x + _x[dir], newY = y + _y[dir];
            if (isValid(newX, newY)) {
                // ↓说明visited
                if (board[newX][newY] == '*') continue; // 写法1-【勿漏！若不写下句，会TLE！】
                if (board[newX][newY] == 'O')
                    dfsBorder(newX, newY);
            }
        }
    }

    private boolean isBorder(int i, int j) {
        return (i == 0 || i == m - 1 || j == 0 || j == n - 1);
    }

    private boolean isValid(int i, int j) {
        return 0 <= i && i < m && 0 <= j && j < n;
    }

    // dfs：写法2
    public void solve_dfs2(char[][] board) {
        m = board.length;
        n = board[0].length;
        this.board = board;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 对边界深搜，任何与边界相连的'O'都不可变'X',
                // 为与内陆'O'区分，dfs将边界'O'全部临时标记为‘*’
                if (!isBorder(i, j)) continue;
                dfsBorder2(i, j);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '*') board[i][j] = 'O'; // 恢复边界的'O'
                else board[i][j] = 'X';
            }
        }
    }

    private void dfsBorder2(int x, int y) {
        if (!isValid(x, y)) return;
        if (board[x][y] == 'X') return;

        board[x][y] = '*';
        for (int dir = 0; dir < 4; dir++) {
            int newX = x + _x[dir], newY = y + _y[dir];
            if (isValid(newX, newY)) {
                // ↓说明visited
                if (board[newX][newY] == '*') continue; // 【勿漏！否则TLE！】???
                dfsBorder(newX, newY);
            }
        }
    }


    // 法2：并查集[代码量大，不推荐]
//    int m, n;
//    char[][] board;
    int[] _i = {1, 0, -1, 0};
    int[] _j = {0, 1, 0, -1};

    public void solve(char[][] board) {
        m = board.length;
        n = board[0].length;
        this.board = board;
        UnionFind uf = new UnionFind(m * n + 1); // 新增结点dummy, idx=m*n，作为边界的集合
        int dummy = m * n; // 边界集合的root/dummy
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'X') continue;
                if (isBorder(i, j)) { // 边界‘O’集合合并（与dummy连通）
                    uf.union(getID(i, j), dummy);
                } else {  // 四周搜索，边界内部同样计算连通块
                    for (int dir = 0; dir < 4; dir++) {
                        int newI = i + _i[dir], newJ = j + _j[dir];
                        if (!isValid(newI, newJ)) continue;
                        if (board[newI][newJ] == 'O') {
                            uf.union(getID(newI, newJ), getID(i, j));
                        }
                    }
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    // 边界'O'跳过， 与dummy相连
                    // if (uf.find(getID(i, j)) == dummy) continue;
                    if (uf.isConnected(getID(i, j), dummy)) continue;
                    else board[i][j] = 'X';
                }

            }
        }
    }

    private int getID(int i, int j) {
        return i * n + j;
    }

    class UnionFind {
        int[] father; // 数组形式

        public UnionFind(int n) {
            this.father = new int[n];
            for (int i = 0; i < n; i++) {
                father[i] = i; // 初始化：root指向自己
            }
        }

        private void union(int i, int j) {
            int root1 = find(i), root2 = find(j);
            if (root1 != root2) {
                father[root1] = root2;
            }
        }

        private int find(int i) {
            while (father[i] != i) {
                father[i] = find(father[i]); // 递归-法1
            }
//            // 迭代-法2
//            if (i == father[i]) return i;
//            List<Integer> path = new ArrayList<>(); // 需要更新/改向root的路径
//            while (i != father[i]) {
//                path.add(i);
//                i = father[i]; // 往前找root
//            } // 退出时，i == father[i]
//            for (int node: path) {
//                father[node] = i;
//            }
            return father[i];
        }

        private boolean isConnected(int i, int j) {
            return find(i) == find(j);
        }
    }

}
