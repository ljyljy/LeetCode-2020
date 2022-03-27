package Greedy;

import java.util.Arrays;
import java.util.LinkedList;

public class q406_queue_reconstruction_by_height {
    public int[][] reconstructQueue(int[][] people) {
        // 1) 排序：按身高降序（o[0]）; 2)插入：再按k值升序（o[1]）
        Arrays.sort(people, (o1, o2)->(o1[0] != o2[0]? o2[0]-o1[0]: o1[1]-o2[1]));

        LinkedList<int[]> res = new LinkedList<>();
        for (int[] h_k: people) {
            int insertIdx = h_k[1];
            res.add(insertIdx, h_k);
        }
        return res.toArray(new int[res.size()][2]);
    }
}
