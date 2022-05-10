package BFS.TopoSort;

import java.util.ArrayList;
import java.util.List;

public class q997_find_the_town_judge {
    /** �����������(ģ����ȳ���) - Easy
     * �� m Ϊ trust ���鳤�ȣ�����ÿ�� trust[i] = (a, b) ���ԣ������Ǵ� a ָ�� b ������ߡ�
     *     ���� trust��ͳ��ÿ���ڵ�ġ���ȡ��͡����ȡ��������� a -> b���� a �ڵ㡸���ȡ���һ��b �ڵ㡸��ȡ���һ��
     *     ���������е㣬�����ڡ�����ȡ�����Ϊ n - 1���ҡ����ȡ�����Ϊ 0 ���Ľڵ㼴�ǡ����١���
     */
    public int findJudge(int n, int[][] trust) {
        int[] inDeg = new int[n+1]; // idx��1��
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
