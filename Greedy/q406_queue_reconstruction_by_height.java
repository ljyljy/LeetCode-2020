package Greedy;

import java.util.*;

public class q406_queue_reconstruction_by_height {
    public int[][] reconstructQueue(int[][] people) {
        // 1) 排序：按身高降序o[0]; 2)插入：再按k值升序o[1] - 因为k越大，理应前面的数越多（k大的排在后面）
        Arrays.sort(people, (o1, o2)->(o1[0] != o2[0]? o2[0]-o1[0]: o1[1]-o2[1]));

        LinkedList<int[]> res = new LinkedList<>();
        for (int[] h_k: people) { // 按身高h降序,k升序排序-> 顺序插入第k位
            int insertIdx = h_k[1];
            res.add(insertIdx, h_k);
        }
        return res.toArray(new int[res.size()][2]);
    }
}
