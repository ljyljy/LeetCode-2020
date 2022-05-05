package Recursion.graph;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 1�������0����ͨ��
 * 2��������ͨ��Ĵ�С�������������
 * �����q130�� 200��
 */
public class q695_HJ_connectedZoneSort {
    private static int m = 5, n = 4;
    private static int[][] graph;
    private static boolean[][] visited;
    private static List<Integer> connCnts = new ArrayList<>();
    private static int[] _x = {0, 1, 0, -1, 1, 1, -1, -1}; // �������ң��Խ��� ��8������
    private static int[] _y = {1, 0, -1, 0, 1, -1, 1, -1};

    public static void main(String[] args) {
        graph = new int[m][n];
        visited = new boolean[m][n];

        System.out.println("init 0-1 graph: ");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = new Random().nextInt(2);
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }

        // ��1��dfs ������ͨ�飬�����graph��ԭ���޸ģ�visited���ã������q130��
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] == 0 && !visited[i][j]) {
//                    visited[i][j] = true;
                    connCnts.add(dfs(i, j));
//                    visited[i][j] = false;
                }
            }
        }

        // ��2�����鼯���ԣ����ο�q130

        System.out.println("find 0-zone, sort:");
        Collections.sort(connCnts);
        for (int cnt: connCnts) {
            System.out.print(cnt + " ");
        }

    }

    private static int dfs(int i, int j) {
        int cnt = 1;
//        �� �����graph��ԭ���޸ģ�visited���ã������q130��
        graph[i][j] = -1; // DFS��ʱ��һ��Ҫ�����ĵ�����Ȼ��Ӱ���жϣ����Բ���visited���ĵ��Ժ���Ȼ��visited��
        for (int dir= 0; dir < 4; dir++) {
            int newX = i + _x[dir], newY = j + _y[dir];
            if (!check(newX, newY)) continue;
            if (visited[newX][newY]) continue;
//            visited[newX][newY] = true; // ���Բ���
            cnt += dfs(newX, newY);
//            visited[newX][newY] = false;
        }


        return cnt;
    }

    private static boolean check(int x, int y) {
        return 0 <= x && x < m && 0 <= y && y < n && graph[x][y] == 0;
    }


}
