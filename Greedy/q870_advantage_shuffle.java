package Greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class q870_advantage_shuffle {
    // ̰��/˫ָ�� + ����<idx2, num2>
    public int[] advantageCount1(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] res = new int[n];
        // num2����nums2˳�򲻿ɱ䣬�豣���±꣬��resӳ��
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(n,
                ((o1, o2) -> (o2[1] - o1[1]))); // o1<idx2, num2>
        for (int i = 0; i < n; i++) {
            maxHeap.offer(new int[]{i, nums2[i]});
        }
        Arrays.sort(nums1); // nums1����

        // ˫ָ��/̰�ģ���nums1_max > nums2_max,��ѡ��nums1_max��
        //      ����ʹ��nums1_min"����ͷ"
        int left = 0, right = n-1;
        while (!maxHeap.isEmpty()) {
            int[] cur = maxHeap.poll();
            int max2 = cur[1], max2_idx = cur[0];
            int max1 = nums1[right], min1 = nums1[left];
            System.out.println("curMax2_idx=" + max2_idx + ", curMax2=" + max2);
            System.out.println("min1=" + min1 + ", max1=" + max1);
            if (max1 > max2) {
                res[max2_idx] = max1;
                right--;
            } else { // ����1�����򲻹�2, ��nums1_min"����ͷ"
                res[max2_idx] = min1;
                left++;
            }
        }
        return res;
    }

    // ̰��/˫ָ�� + �±�����(����תΪC)
    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        Integer[] idx1 = new Integer[n];// int[] �������򣡱����װ�࣡
        Integer[] idx2 = new Integer[n];
        for (int i = 0; i < n; i++) {
            idx1[i] = i;
            idx2[i] = i;
        }
        Arrays.sort(idx1, Comparator.comparingInt(i -> nums1[i]));// v1
        Arrays.sort(idx2, (i, j) -> nums2[i] - nums2[j]); // ��ӳ���±�(by����)��
//        System.out.println("idx1: " + Arrays.toString(idx1));
//        System.out.println("idx2: " + Arrays.toString(idx2));

        // ˫ָ��/̰�ģ���nums1_max > nums2_max,��ѡ��nums1_max��
        //      ����ʹ��nums1_min"����ͷ"
        int left = 0, right = n-1;// ���nums1
        int[] res = new int[n];
        for (int i = n-1; i >= 0; i--) {
            int curMax2_idx = idx2[i], curMax2 = nums2[curMax2_idx];
            int min1 = nums1[idx1[left]], max1 = nums1[idx1[right]];
//            System.out.println("curMax2_idx=" + curMax2_idx + ", curMax2=" + curMax2);
//            System.out.println("min1=" + min1 + ", max1=" + max1);
            if (max1 > curMax2) {
                res[curMax2_idx] = max1;
                right--;
            } else {
                res[curMax2_idx] = min1;
                left++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        q870_advantage_shuffle sol = new q870_advantage_shuffle();
        int[] nums1 = {2,7,11,15};
        int[] nums2 = {1,10,4,11};
        System.out.println(Arrays.toString(sol.advantageCount1(nums1, nums2)));
        System.out.println("-----------");
        System.out.println(Arrays.toString(sol.advantageCount(nums1, nums2)));
    }
}
