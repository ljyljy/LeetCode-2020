package BFS.TopoSort;

import java.util.ArrayList;
import java.util.List;

public class q997_find_the_town_judge {
    /** 拓扑排序变题(模拟入度出度) - Easy
     * 令 m 为 trust 数组长度，对于每个 trust[i] = (a, b) 而言，看作是从 a 指向 b 的有向边。
     *     遍历 trust，统计每个节点的「入度」和「出度」：若存在 a -> b，则 a 节点「出度」加一，b 节点「入度」加一。
     *     最后遍历所有点，若存在【「入度」数量为 n - 1，且「出度」数量为 0 】的节点即是【法官】。
     */
    public int findJudge(int n, int[][] trust) {
        int[] inDeg = new int[n+1]; // idx从1起
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= n; i++) adjList.add(new ArrayList<>());

        for (int[] pair: trust) {
            int pre = pair[0], nxt = pair[1];
            adjList.get(pre).add(nxt);
            inDeg[nxt]++;
        }

        for (int i = 1; i <= n; i++) {
            if (inDeg[i] == n-1 && adjList.get(i).size() == 0) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int n = 3;
        int[][] trust = {{1,3}, {2,3}};
        q997_find_the_town_judge sol = new q997_find_the_town_judge();
        System.out.println(sol.findJudge(n, trust));
    }
}
