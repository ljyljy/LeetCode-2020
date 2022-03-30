package DataStructure.Deque;

import java.util.ArrayDeque;
import java.util.Deque;

public class q321_create_maximum_number {
    int n1, n2;
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] res = new int[0];
        n1 = nums1.length; n2 = nums2.length;
        // �� nums1 ��ѡ���� i ��������
        for (int pick1 = 0; pick1 <= k && pick1 <= n1; pick1++) {
            int pick2 = k - pick1;
            // �� nums2 ��ѡ���� k - i ��������
            if (0 <= pick2 && pick2 <= n2) {
                int[] tmp = merge(pickK(nums1, pick1), pickK(nums2, pick2)); // �ϲ�
                if (compare(tmp, 0, res, 0)) res = tmp;
            }
        }
        return res;
    }

    // ����ջ�����q402,316,321
    private int[] pickK(int[] nums, int k) { // k: ʣ���ѡ������
        Deque<Integer> stack = new ArrayDeque<>();
        int drop = nums.length - k; // ��Ҫ����������
        for (int num: nums) {
            while (!stack.isEmpty() && num > stack.peek() && drop > 0) {
                stack.pop();         // �� ��ǰnum��ǰ�߸����п�����ɸ�����
                drop--;
            }
            stack.push(num);
        }

        int[] res = new int[k];
        int i = 0;
        while (!stack.isEmpty() && k-- > 0)
            res[i++] = stack.removeLast(); // ����ѡ��ջ��/��ͷ���ϴ�����
        return res;
    }

    // �ӣ������Լ�merge����67 & 604, ����ȽϺ����ĸ���
    public int[] merge(int[] nums1, int[] nums2) {
        int[] res = new int[n1 + n2];
        int cur = 0, p1 = 0, p2 = 0;
        while (cur < n1 + n2) {
            if (compare(nums1, p1, nums2, p2)) {
                res[cur++] = nums1[p1++];
            } else {
                res[cur++] = nums2[p2++];
            }
        }
        return res;
    }

    // compare����-0
    private boolean compare(int[] nums1, int p1, int[] nums2, int p2) {
        int n1 = nums1.length, n2 = nums2.length;
        int i = p1, j = p2;
        while (i < n1 && j < n2) {
            if (nums1[i] > nums2[j]) {
                return true;
            } else if (nums1[i] < nums2[j]) {
                return false;
            }
            i++; j++; // ��ǰ��λ��ͬ��������һλ�Ƚ�
        }
        if (i < n1) return true;// nums1����
        if (j < n2) return false;// nums1��
        return true; // ������ͬ
    }

    // compare�ݹ�-1
    public boolean compare1(int[] nums1, int p1, int[] nums2, int p2) { // nums1 > nums2, ��true
        if (p2 >= n2) return true; // ˵��nums1������>��
        if (p1 >= n1) return false;// ˵��nums1����, �ȵ�ͷ�ˣ�ǰp1��������ͬ��
        if (nums1[p1] > nums2[p2]) return true;
        if (nums1[p1] < nums2[p2]) return false;
        return compare(nums1, p1 + 1, nums2, p2 + 1);
    }
}

/**
 * wa
 *
 * class Solution {
 *     int n1, n2;
 *     public int[] maxNumber(int[] nums1, int[] nums2, int k) {
 *         n1 = nums1.length; n2 = nums2.length;
 *         List<List<Integer>> res = new ArrayList<>();
 *
 *
 *         for (int pick1 = 0; pick1 <= k; pick1++) {
 *             int pick2 = k - pick1;
 *             if (pick1 <= n1 && pick2 <= n2) {
 *                 List<Integer> num1 = pickK(nums1, pick1);
 *                 List<Integer> num2 = pickK(nums2, pick2);
 *                 System.out.println();
 *                 for (int nn1: num1) System.out.print(nn1+ " ");
 *                 System.out.println();
 *                 for (int nn2: num2) System.out.print(nn2+ " ");
 *                 res.add(merge(num1, num2));
 *             }
 *         }
 *
 *         System.out.println();
 *         for (List<Integer> ress: res) {
 *             for (int numm: ress) {
 *                 System.out.print(numm+ " ");
 *             }
 *             System.out.println();
 *         }
 *
 *         Collections.sort(res, (l1, l2) -> { // ???
 *             for (int i = 0; i < l1.size(); i++) {
 *                 if (l1.get(i) != l2.get(i))
 *                     return l2.get(i) - l1.get(i); // ����
 *             }
 *             return 0;
 *         });
 *
 *         int[] ans = new int[k];
 *         for (int i = 0; i < k; i++) {
 *             ans[i] = res.get(0).get(i);
 *         }
 *         return ans;
 *     }
 *
 *     // ����ջ�����q402,316,321
 *     private List<Integer> pickK(int[] nums, int k) { // k: ʣ���ѡ������
 *         Deque<Integer> stack = new ArrayDeque<>();
 *         int drop = nums.length - k; // ��Ҫ����������
 *         for (int num: nums) {
 *             while (!stack.isEmpty() && num > stack.peek() && drop > 0) {
 *                 stack.pop();         // �� ��ǰnum��ǰ�߸����п�����ɸ�����
 *                 drop--;
 *             }
 *             stack.push(num);
 *         }
 *
 *         List<Integer> res = new ArrayList<>();
 *         while (!stack.isEmpty() && k-- > 0)
 *             res.add(stack.removeLast()); // ����ѡ��ջ��/��ͷ���ϴ�����
 *         return res;
 *     }
 *
 *     // �ӣ������Լ�merge����67 & 604, ����ȽϺ����ĸ���
 *     private List<Integer> merge (List<Integer> l1, List<Integer> l2) {
 *         List<Integer> res = new ArrayList<>();
 *         int p1 = 0, p2 = 0, n1 = l1.size(), n2 = l2.size();
 *         while (!l1.isEmpty() && !l2.isEmpty()) {
 *             if (compare(l1, l2)) {
 *                 res.add(l1.remove(0));
 *             } else {
 *                 res.add(l2.remove(0));
 *             }
 *         }
 *         if (!l1.isEmpty()) res.addAll(l1);
 *         if (!l2.isEmpty()) res.addAll(l2);
 *         return res;
 *     }
 *
 *     private boolean compare(List<Integer> l1, List<Integer> l2) {
 *         int n1 = l1.size(), n2 = l2.size();
 *         for (int i = 0, j = 0; i < n1 && j < n2; i++, j++) {
 *             if (l1.get(i) == l2.get(j)) continue;
 *             else if (l1.get(i) > l2.get(j)) return true;
 *             else return false;
 *         }
 *         return true;
 *     }
 * }
 */
