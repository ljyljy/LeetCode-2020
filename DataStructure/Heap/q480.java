package DataStructure.Heap;

import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;

public class q480 {
    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        double res[] = new double[n - k + 1];
        TreeSet<int[]> treeSet = new TreeSet<>(
                (o1, o2) -> o1[0] != o2[0] ? Integer.compare(o1[0], o2[0]) : o1[1] - o2[1]); // {nums[i], i}
        for (int i = 0; i < k; i++) {
            treeSet.add(new int[]{nums[i], i});
        }
        res[0] = getMid(treeSet);
        for (int start = 1, end = k; start < end && end < n; start++, end++) {
            treeSet.add(new int[]{nums[end], end});
            treeSet.remove(new int[]{nums[start - 1], start - 1});
            res[start] = getMid(treeSet);
        }
        return res;
    }

    private double getMid(TreeSet<int[]> treeSet) {
        int mid = (treeSet.size() - 1) / 2;
        System.out.println("mid = " + mid);
        for (int[] ints : treeSet) {
            System.out.println("nums[i] = " + ints[0] + ", i = " + ints[1]);
        }
        Iterator<int[]> iterator = treeSet.iterator();
        while (mid-- > 0) {
            System.out.println("curMid = " + mid + ", nxt = " + iterator.next()[0]);
        }
        return treeSet.size() % 2 == 0 ? ((double) iterator.next()[0] + iterator.next()[0]) / 2.0 : iterator.next()[0];
    }

    public static void main(String[] args) {
        q480 obj = new q480();
        int nums[] = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println(Arrays.toString(obj.medianSlidingWindow(nums, k)));
    }
/*
mid = 1
nums[i] = -1, i = 2
nums[i] = 1, i = 0
nums[i] = 3, i = 1
curMid = 0, nxt = -1
mid = 1
nums[i] = -3, i = 3
nums[i] = -1, i = 2
nums[i] = 3, i = 1
curMid = 0, nxt = -3
mid = 1
nums[i] = -3, i = 3
nums[i] = -1, i = 2
nums[i] = 5, i = 4
curMid = 0, nxt = -3
mid = 1
nums[i] = -3, i = 3
nums[i] = 3, i = 5
nums[i] = 5, i = 4
curMid = 0, nxt = -3
mid = 1
nums[i] = 3, i = 5
nums[i] = 5, i = 4
nums[i] = 6, i = 6
curMid = 0, nxt = 3
mid = 1
nums[i] = 3, i = 5
nums[i] = 6, i = 6
nums[i] = 7, i = 7
curMid = 0, nxt = 3
res = [1.0, -1.0, -1.0, 3.0, 5.0, 6.0]

* */
}
