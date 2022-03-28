package Recursion.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * ���������
 * M�����Թ٣�������Ը�����ͬ
 * N�������ߣ�����ĳ�����Ե�����
 * ÿ����������Ҫ2�����Թ�ȥ��
 * ÿ�����Թ������k��
 * �ж��Ƿ��ܹ����ź����ԣ�
 *
 * ���룺
 * 4 6 4  (M,N,k)
 * java c py  (���Թ�1���3������)
 * py
 * c java
 * py
 * java ��������ǰ�����Ե����ԣ�
 * py
 * c
 * py
 * c
 * java
 *
 * ���ߣ�������Եkb
 * ���ӣ�https://www.nowcoder.com/discuss/872518?type=post&order=hot&pos=&page=1&ncTraceId=&channel=-1&source_id=search_post_nctrack&gio_id=B35E78D97BDF0B28B17D0BFA6D669DF2-1648457642192
 * ��Դ��ţ����
 *
 * ���������dfs�����������
 * 1. ���ȹ���һ��mat���󣬴�����Թ�������������ƥ���ϵ����mλ���Թٻ��nλ�����ߵ����ԣ���mat[m][n]=1��������Ϊ0
 * ����һ���ȽϹؼ���������뵽�������ֹ�ϵ�Ļ���Ӧ�þ�֪����ô�����ˣ�
 *     ������������ӣ�mat���ӦΪ��
 *     [1, 1, 1, 1, 1, 1]
 *     [0, 1, 0, 1, 0, 0]
 *     [1, 0, 1, 0, 1, 1]
 *     [0, 1, 0, 1, 0, 0]
 *
 * 2.�ٳ�ʼ��res���󣬱�ʾ���Թ����������Ƿ�Ҫȥƥ�䡣��dfs�л�Ըþ����ֵ���и��£�
 *         ��mat[m][n]=0,�����ڲ�ƥ�䣬res[m][n]===0����Ϊ0��
 *         ����res[m][n]��ͨ������0/1��ʾ ��ƥ��/ƥ�䡣
 *     �����1������matΪ1����res��1�е�ÿ����㣬Ҫô��Ϊ1��ʾ������ԣ�Ҫô��Ϊ0��ʾ�����
 *
 * 3.dfs�����뷽ʽ���������mat���Ϊ1��������resΪ0/1��������һ��㣬����ֱ�ӽ�����һ���
 *         dfs�������������Խ�����½����һ�񣬴�ʱ��Ҫ�жϱ��α����Ƿ�ƥ��ɹ�
 *         �жϳɹ�����������1�����в��ܴ���k  ����2�����б������2
 *
 * 4.�������ӵĽ��
 *     [1, 0, 1, 0, 1, 1]
 *     [0, 1, 0, 1, 0, 0]
 *     [1, 0, 1, 0, 1, 1]
 *     [0, 1, 0, 1, 0, 0]
 */
public class HJ22_1_3_CodingInterviewMatch {
    private static boolean isValid = false;
    private static int[][] graph;
    private static int m, n, k;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            m = sc.nextInt(); n = sc.nextInt(); k = sc.nextInt();
            sc.nextLine();
            // typesEE: ���Թ�i.���Լ�: typesEE.get(i)
            List<String[]> typesEE = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                typesEE.add(i, sc.nextLine().split("\\s"));
            }
            graph = new int[m][n]; // EE*ER, ����ƥ�����ƥ������1
            int[][] res = new int[m][n]; // �����
            for (int j = 0; j < n; j++) {
                String curTypeER = sc.nextLine();
                for (int i = 0; i < m; i++) {
                    for (String type: typesEE.get(i)) {
                        if (type.equals(curTypeER)) {
                            graph[i][j] = 1;
                        }
                    }
                }
            }

            dfs(res, 0);
            if (isValid) {
                printRes(res);
            } else {
                System.out.println(false);
            }
        }
    }

    private static void dfs(int[][] res, int idx) {
        if (isValid) return;
        if (idx == m * n - 1) {
            if (check(res)) isValid = true;
            return;
        }
        //��<M=4, N=6>����idx=21��Ӧ(3,3)
        // ���q37, һά����ת����ά���� ?
        int row = idx / n, col = idx % n;
        if (graph[row][col] == 1) { // ������ԣ���ѡ/��ѡ
            // 2. ������
            res[row][col] = 0;
            dfs(res, idx+1);
            // 1. ѡ�����ԣ����i-j��
            res[row][col] = 1;
            dfs(res, idx+1);

        } else dfs(res, idx+1); // ��������ԣ�ֱ����̽
    }

    private static boolean check(int[][] res) {
        // ÿ�����Թ�m_i�����k�� - res�к�>k������
        // ÿ��������n_j��Ҫ2�����Թ�ȥ�� - res�к� != 2, ����
        for (int i = 0; i < m; i++) {
            int sumRow = Arrays.stream(res[i]).sum();
            if (sumRow > k) return false;
        }
        for (int j = 0; j < n; j++) {
            int sumCol = 0;
            for (int i = 0; i < m; i++) {
                sumCol += res[i][j];
            }
            if (sumCol != 2) return false;
        }
        return true;
    }

    private static void printRes(int[][] res) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(res[i][j] + " ");
            }
            System.out.println();
        }
    }
}

// TEST PASS
//4 6 4
//java c py
//py
//c java
//py
//java
//py
//c
//py
//c
//java

// ���:
//1 1 1 1 1 1
//0 1 0 1 0 0
//1 0 1 0 1 1
//0 1 0 1 0 0