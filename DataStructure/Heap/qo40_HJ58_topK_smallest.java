package DataStructure.Heap;

import java.util.PriorityQueue;
import java.util.Scanner;

public class qo40_HJ58_topK_smallest {
    // 保留k个小数，pop剔除大数 - 大顶堆
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt(), k = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            PriorityQueue<Integer> pq = new PriorityQueue<>(k, (o1, o2)->(o2-o1));
            for (int num: arr) {
                pq.offer(num);
                if (pq.size() > k) {
                    pq.poll();
                }
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < k; i++) {
                sb.insert(0, pq.poll() + " ");
            }
            System.out.println(sb);
        }
    }
}
