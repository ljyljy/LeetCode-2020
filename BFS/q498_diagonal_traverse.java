package BFS;

import java.util.*;
import java.util.stream.Stream;

/*
1. 对每个元素广度优先搜索其右边的一个元素，特殊情况当列为0，行小于最后一行时，额外多搜索其下面的一个元素。
2. size为0时一个对角线遍历完，如果flag为-1则直接加到res，如果flag为1，将遍历结果反转后加到res。
3. flag取反。
4. 最后遍历res，存入数组，返回该数组。

1. 坐标索引用x，y来表示，题目m和n小于10000，用x*10001+y存入队列。
2. 注意边界：y<n-1 才能搜索右边元素，【y==0 且 x < m-1】才能搜索下边一个元素。

 */
public class q498_diagonal_traverse {
    private int[] _row = {0, 1}; // 法1：右，下（dir需要去重visited）
    private int[] _col = {1, 0};
    private int m, n;
    private int[][] mat;
    private List<Integer> res = new ArrayList<Integer>();
    private Queue<int[]> queue = new ArrayDeque<>(); // (x, y)
    private boolean[][] visited; // 法1

    public int[] findDiagonalOrder(int[][] mat) {
        this.mat = mat; m = mat.length; n = mat[0].length;

        bfs();

        // List<Integer> 转 int[]：
//        int[] ans2 = res.stream().mapToInt(Integer::intValue).toArray();
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
                // 法1：常规BFS
                for (int dir = 0; dir < 2; dir++) {
                    int nx = row + _row[dir], ny = col + _col[dir];
                    if (!isValid(nx, ny) || visited[nx][ny]) continue;
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny}); // 右、下
                }
                // 法2[荐]：仅列0时，向下搜索，其他结点只向右搜索；
                //      无需for(dir...)、visited
//                if (col+1 < n) queue.offer(new int[]{row, col+1}); // 右
//                if (col == 0 && row+1 < m) queue.offer(new int[]{row+1, col}); // 下
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
