package DataStructure.Deque;

import java.util.ArrayDeque;
import java.util.Deque;

public class q321_create_maximum_number {
    int n1, n2;
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] res = new int[0];
        n1 = nums1.length; n2 = nums2.length;
        // 从 nums1 中选出长 i 的子序列
        for (int pick1 = 0; pick1 <= k && pick1 <= n1; pick1++) {
            int pick2 = k - pick1;
            // 从 nums2 中选出长 k - i 的子序列
            if (0 <= pick2 && pick2 <= n2) {
                int[] tmp = merge(pickK(nums1, pick1), pickK(nums2, pick2)); // 合并
                if (compare(tmp, 0, res, 0)) res = tmp;
            }
        }
        return res;
    }

    // 单调栈，类比q402,316,321
    private int[] pickK(int[] nums, int k) { // k: 剩余可选择数量
        Deque<Integer> stack = new ArrayDeque<>();
        int drop = nums.length - k; // 需要丢弃的数量
        for (int num: nums) {
            while (!stack.isEmpty() && num > stack.peek() && drop > 0) {
                stack.pop();         // ↑ 当前num比前者更大，有可能组成更大数
                drop--;
            }
            stack.push(num);
        }

        int[] res = new int[k];
        int i = 0;
        while (!stack.isEmpty() && k-- > 0)
            res[i++] = stack.removeLast(); // 优先选择栈底/队头（较大数）
        return res;
    }

    // 坑：不可以简单merge！若67 & 604, 还需比较后续哪个大！
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

    // compare迭代-0
    private boolean compare(int[] nums1, int p1, int[] nums2, int p2) {
        int n1 = nums1.length, n2 = nums2.length;
        int i = p1, j = p2;
        while (i < n1 && j < n2) {
            if (nums1[i] > nums2[j]) {
                return true;
            } else if (nums1[i] < nums2[j]) {
                return false;
            }
            i++; j++; // 当前数位相同，继续下一位比较
        }
        if (i < n1) return true;// nums1更长
        if (j < n2) return false;// nums1短
        return true; // 二者相同
    }

    // compare递归-1
    public boolean compare1(int[] nums1, int p1, int[] nums2, int p2) { // nums1 > nums2, 则true
        if (p2 >= n2) return true; // 说明nums1更长（>）
        if (p1 >= n1) return false;// 说明nums1更短, 先到头了（前p1个数都相同）
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
 *                     return l2.get(i) - l1.get(i); // 降序
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
 *     // 单调栈，类比q402,316,321
 *     private List<Integer> pickK(int[] nums, int k) { // k: 剩余可选择数量
 *         Deque<Integer> stack = new ArrayDeque<>();
 *         int drop = nums.length - k; // 需要丢弃的数量
 *         for (int num: nums) {
 *             while (!stack.isEmpty() && num > stack.peek() && drop > 0) {
 *                 stack.pop();         // ↑ 当前num比前者更大，有可能组成更大数
 *                 drop--;
 *             }
 *             stack.push(num);
 *         }
 *
 *         List<Integer> res = new ArrayList<>();
 *         while (!stack.isEmpty() && k-- > 0)
 *             res.add(stack.removeLast()); // 优先选择栈底/队头（较大数）
 *         return res;
 *     }
 *
 *     // 坑：不可以简单merge！若67 & 604, 还需比较后续哪个大！
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
