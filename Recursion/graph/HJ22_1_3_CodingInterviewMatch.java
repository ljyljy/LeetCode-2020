package Recursion.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 问题简述：
 * M个面试官，会的语言各不相同
 * N个受试者，参与某种语言的面试
 * 每个受试者需要2个面试官去面
 * 每个面试官最多面k次
 * 判断是否能够安排好面试？
 *
 * 输入：
 * 4 6 4  (M,N,k)
 * java c py  (面试官1会的3种语言)
 * py
 * c java
 * py
 * java （受试者前来面试的语言）
 * py
 * c
 * py
 * c
 * java
 *
 * 作者：风中奇缘kb
 * 链接：https://www.nowcoder.com/discuss/872518?type=post&order=hot&pos=&page=1&ncTraceId=&channel=-1&source_id=search_post_nctrack&gio_id=B35E78D97BDF0B28B17D0BFA6D669DF2-1648457642192
 * 来源：牛客网
 *
 * 解决方案：dfs深度优先搜索
 * 1. 首先构建一个mat矩阵，存放面试官与受试者语言匹配关系，第m位面试官会第n位受试者的语言，则mat[m][n]=1，否则设为0
 * （这一步比较关键，如果能想到构建这种关系的话，应该就知道怎么解题了）
 *     对上述情况例子，mat结果应为：
 *     [1, 1, 1, 1, 1, 1]
 *     [0, 1, 0, 1, 0, 0]
 *     [1, 0, 1, 0, 1, 1]
 *     [0, 1, 0, 1, 0, 0]
 *
 * 2.再初始化res矩阵，表示面试官与受试者是否要去匹配。在dfs中会对该矩阵的值进行更新：
 *         若mat[m][n]=0,则由于不匹配，res[m][n]===0（恒为0）
 *         否则res[m][n]可通过设置0/1表示 不匹配/匹配。
 *     例如第1行所有mat为1，则res第1行的每个格点，要么设为1表示组队面试，要么设为0表示不组队
 *
 * 3.dfs的深入方式：如果遇到mat格点为1，则设置res为0/1并进入下一格点，否则直接进入下一格点
 *         dfs结束条件：格点越过右下角最后一格，此时需要判断本次遍历是否匹配成功
 *         判断成功条件：条件1：各行不能大于k  条件2：各列必须等于2
 *
 * 4.上述例子的结果
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
            // typesEE: 面试官i.语言集: typesEE.get(i)
            List<String[]> typesEE = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                typesEE.add(i, sc.nextLine().split("\\s"));
            }
            graph = new int[m][n]; // EE*ER, 语言匹配表，能匹配则置1
            int[][] res = new int[m][n]; // 结果集
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
        //若<M=4, N=6>，则idx=21对应(3,3)
        // 类比q37, 一维坐标转换二维坐标 ?
        int row = idx / n, col = idx % n;
        if (graph[row][col] == 1) { // 若可配对，则选/不选
            // 2. 不面试
            res[row][col] = 0;
            dfs(res, idx+1);
            // 1. 选择面试（配对i-j）
            res[row][col] = 1;
            dfs(res, idx+1);

        } else dfs(res, idx+1); // 若不可配对，直接下探
    }

    private static boolean check(int[][] res) {
        // 每个面试官m_i最多面k次 - res行和>k，不可
        // 每个受试者n_j需要2个面试官去面 - res列和 != 2, 不可
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

// 输出:
//1 1 1 1 1 1
//0 1 0 1 0 0
//1 0 1 0 1 1
//0 1 0 1 0 0