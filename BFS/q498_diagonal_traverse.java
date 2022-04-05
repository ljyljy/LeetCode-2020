package BFS;

import java.util.*;

/*
1. ��ÿ��Ԫ�ع�������������ұߵ�һ��Ԫ�أ������������Ϊ0����С�����һ��ʱ������������������һ��Ԫ�ء�
2. sizeΪ0ʱһ���Խ��߱����꣬���flagΪ-1��ֱ�Ӽӵ�res�����flagΪ1�������������ת��ӵ�res��
3. flagȡ����
4. ������res���������飬���ظ����顣

1. ����������x��y����ʾ����Ŀm��nС��10000����x*10001+y������С�
2. ע��߽磺y<n-1 ���������ұ�Ԫ�أ���y==0 �� x < m-1�����������±�һ��Ԫ�ء�

 */
public class q498_diagonal_traverse {
    private int[] _row = {0, 1}; // ��1���ң��£�dir��Ҫȥ��visited��
    private int[] _col = {1, 0};
    private int m, n;
    private int[][] mat;
    private List<Integer> res = new ArrayList<Integer>();
    private Queue<int[]> queue = new ArrayDeque<>(); // (x, y)
    private boolean[][] visited; // ��1

    public int[] findDiagonalOrder(int[][] mat) {
        this.mat = mat; m = mat.length; n = mat[0].length;

        bfs();

        int[] ans = new int[m * n];
        int k = 0;
        for (int num: res) {
            ans[k++] = num;
        }
        return ans;
    }

    private void bfs() {
        visited = new boolean[m][n];
        queue.offer(new int[]{0, 0});
        boolean flag = true; // isReverse

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                int[] loc = queue.poll();
                int row = loc[0], col = loc[1];
                level.add(mat[row][col]);
                // ��1������BFS
                for (int dir = 0; dir < 2; dir++) {
                    int nx = row + _row[dir], ny = col + _col[dir];
                    if (!isValid(nx, ny) || visited[nx][ny]) continue;
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny}); // �ҡ���
                }
                // ��2[��]������0ʱ�������������������ֻ����������
                //      ����for(dir...)��visited
//                if (col+1 < n) queue.offer(new int[]{row, col+1}); // ��
//                if (col == 0 && row+1 < m) queue.offer(new int[]{row+1, col}); // ��
            }
            if (flag) Collections.reverse(level);
            res.addAll(level);
            flag = !flag;
        }
    }

    private boolean isValid(int x, int y) {
        return 0 <= x && x < m && 0 <= y && y < n;
    }
}
