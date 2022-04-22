package DataStructure.Union_Find;

import java.util.*;

public class q130_surrounded_regions {
    // ���q130,547
    // ��1: DFS(����)-v1
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
                // �Ա߽����ѣ��κ���߽�������'O'�����ɱ�'X',
                // Ϊ����½'O'���֣�dfs���߽�'O'ȫ����ʱ���Ϊ��*��
                if (!isBorder(i, j)) continue;
                if (board[i][j] == 'O') { // д��2-1
                    dfsBorder(i, j);
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '*') board[i][j] = 'O'; // �ָ��߽��'O'
                else board[i][j] = 'X';
            }
        }
    }

    private void dfsBorder(int x, int y) {
//        if (board[x][y] == 'X') return;  // д��2-2(��д��2-1ѡһ������)

//         �� �����graph��ԭ���޸ģ�visited���ã������q130��connectedZoneSort��
        board[x][y] = '*';// DFS��ʱ��һ��Ҫ�����ĵ�����Ȼ��Ӱ���жϣ����Բ���visited���ĵ��Ժ���Ȼ��visited��
        for (int dir = 0; dir < 4; dir++) {
            int newX = x + _x[dir], newY = y + _y[dir];
            if (isValid(newX, newY)) {
                // ��˵��visited
                if (board[newX][newY] == '*') continue; // д��1-����©������д�¾䣬��TLE����
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

    // dfs��д��2
    public void solve_dfs2(char[][] board) {
        m = board.length;
        n = board[0].length;
        this.board = board;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // �Ա߽����ѣ��κ���߽�������'O'�����ɱ�'X',
                // Ϊ����½'O'���֣�dfs���߽�'O'ȫ����ʱ���Ϊ��*��
                if (!isBorder(i, j)) continue;
                dfsBorder2(i, j);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '*') board[i][j] = 'O'; // �ָ��߽��'O'
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
                // ��˵��visited
                if (board[newX][newY] == '*') continue; // ����©������TLE����???
                dfsBorder(newX, newY);
            }
        }
    }


    // ��2�����鼯[�������󣬲��Ƽ�]
//    int m, n;
//    char[][] board;
    int[] _i = {1, 0, -1, 0};
    int[] _j = {0, 1, 0, -1};

    public void solve(char[][] board) {
        m = board.length;
        n = board[0].length;
        this.board = board;
        UnionFind uf = new UnionFind(m * n + 1); // �������dummy, idx=m*n����Ϊ�߽�ļ���
        int dummy = m * n; // �߽缯�ϵ�root/dummy
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'X') continue;
                if (isBorder(i, j)) { // �߽确O�����Ϻϲ�����dummy��ͨ��
                    uf.union(getID(i, j), dummy);
                } else {  // �����������߽��ڲ�ͬ��������ͨ��
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
                    // �߽�'O'������ ��dummy����
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
        int[] father; // ������ʽ

        public UnionFind(int n) {
            this.father = new int[n];
            for (int i = 0; i < n; i++) {
                father[i] = i; // ��ʼ����rootָ���Լ�
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
                father[i] = find(father[i]); // �ݹ�-��1
            }
//            // ����-��2
//            if (i == father[i]) return i;
//            List<Integer> path = new ArrayList<>(); // ��Ҫ����/����root��·��
//            while (i != father[i]) {
//                path.add(i);
//                i = father[i]; // ��ǰ��root
//            } // �˳�ʱ��i == father[i]
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
