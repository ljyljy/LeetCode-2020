package DataStructure.Heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

// ���q373, 378, 786, 719
public class q373_find_k_pairs_with_smallest_sums {
    // [��] ��2����·�鲢 & ��С�� O((m+k)logk)
    // ������������
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        int n1 = nums1.length, n2 = nums2.length;
        // nums1[i] + nums2[j]����
        // ��С��, popС������������k��С����vs�볣��topKС-ʹ������ ��ͬ����
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            return (nums1[o1[0]] + nums2[o1[1]]) - (nums1[o2[0]] + nums2[o2[1]]);
            // ��Integer.compare(nums1[o1[0]] + nums2[o1[1]], nums1[o2[0]] + nums2[o2[1]]));
        });

        for (int i = 0; i < Math.min(n1, k); i++) {
            pq.offer(new int[]{i, 0}); // {i=0:n1-1, j=0}
        }

        while (res.size() < k && !pq.isEmpty()) {
            int[] cur = pq.poll();
            int i = cur[0], j = cur[1];
            List<Integer> level = new ArrayList<>();
            level.add(nums1[i]);
            level.add(nums2[j]);
            res.add(level);

            if (j + 1 < n2) { // ?��ѡ��{i+1, j}����pq�У���һ�ֽ�poll��min��
                pq.offer(new int[]{i, j + 1});
            }
        }
        return res;
    }

    public List<List<Integer>> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        int n1 = nums1.length;
        int n2 = nums2.length;
        PriorityQueue<int[]> minQ = new PriorityQueue<>((o1, o2) ->
                Integer.compare(nums1[o1[0]] + nums2[o1[1]], nums1[o2[0]] + nums2[o2[1]]));
        for (int i = 0; i < Math.min(n1, k); i++) {
            minQ.offer(new int[]{i, 0});
        }

        int curK = 0;
        while (curK++ < k && !minQ.isEmpty()) {
            int[] cur = minQ.poll();
            List<Integer> level = new ArrayList<>();
            level.add(nums1[cur[0]]);
            level.add(nums2[cur[1]]);
            res.add(level);

            if (cur[1] + 1 < n2) {
                minQ.offer(new int[]{cur[0], cur[1] + 1});
            }
        }
        return res;
    }

    public static void main(String[] args) {
        q373_find_k_pairs_with_smallest_sums obj = new q373_find_k_pairs_with_smallest_sums();
        int[] nums1 = {1, 7, 11};
        int[] nums2 = {2, 4, 6};
        System.out.println(obj.kSmallestPairs(nums1, nums2, 3));
    }
}
