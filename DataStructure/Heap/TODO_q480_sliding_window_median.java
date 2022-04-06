package DataStructure.Heap;

import java.util.*;

public class TODO_q480_sliding_window_median {
    // 法1：双堆
    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int cnt = n - k + 1;
        double[] ans = new double[cnt];
        // 如果是奇数滑动窗口，让 right 的数量比 left 多一个
        // [(1,2,3)<-L↓, R↑->(4,5,6,7)]
        PriorityQueue<Integer> left  = new PriorityQueue<>((a,b)->Integer.compare(b,a)); // 滑动窗口的左半部分，降序（最大堆，保存小数,<mid）
        PriorityQueue<Integer> right = new PriorityQueue<>((a,b)->Integer.compare(a,b)); // 滑动窗口的右半部分，升序（最小堆，保存大数,>=mid）
        for (int i = 0; i < k; i++) right.add(nums[i]);
        for (int i = 0; i < k / 2; i++) left.add(right.poll());

        ans[0] = getMid(left, right);
        for (int i = k; i < n; i++) {
            // 人为确保了 right 会比 left 多，因此，删除和添加都与 right 比较（left 可能为空）
            int add = nums[i], del = nums[i - k];
            if (add >= right.peek()) {
                right.add(add);
            } else {
                left.add(add);
            }
            if (del >= right.peek()) {
                right.remove(del);
            } else {
                left.remove(del);
            }
            adjust(left, right);
            ans[i - k + 1] = getMid(left, right);
        }
        return ans;
    }
    void adjust(PriorityQueue<Integer> left, PriorityQueue<Integer> right) {
        while (left.size() > right.size()) right.add(left.poll());
        while (right.size() - left.size() > 1) left.add(right.poll());
    }
    double getMid(PriorityQueue<Integer> left, PriorityQueue<Integer> right) {
        if (left.size() == right.size()) {
            return (left.peek() / 2.0) + (right.peek() / 2.0);
        } else {
            return right.peek() * 1.0;
        }
    }

    // 法2：treeset
    public double[] medianSlidingWindow_TS(int[] nums, int k) {
        /*
        TreeSet解法:参考付雪明烛评论区的思路
         */
        // 这里的数量可以举例子看看:nums=[0,1,2,3],k=3,因此滑窗有2个,即len-k+1
        int len = nums.length;
        double[] res = new double[len - k + 1];
        // set存储的是[nums[i],i]的pair
        // TreeSet的排序规则为:元素相同,按照索引大小升序排;元素不同,按照元素升序排
        // 这里这里元素大小不能直接相减可能会导致溢出(MIN_VALUE-MAX_VALUE)->用Integer.compare(a,b)
        Set<int[]> set = new TreeSet<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : Integer.compare(a[0], b[0]));
        //装入首个滑窗的元素[0,k-1]
        for (int i = 0; i < k; i++) {
            set.add(new int[] {nums[i], i});
        }
        //首个中位数
        res[0] = getMid(set);
        // 开始求后面滑窗的中位数
        for (int start = 1, end = k; end < len; start++, end++) {
            // 更新当前窗口的元素:旧元素出set,新元素入set
            // nums[k]加入
            set.add(new int[] {nums[end], end});
            // nums[0]退出
            set.remove(new int[]{nums[start - 1], start - 1});
            // 求出新窗口的中位数并加入res
            res[start] = getMid(set);
        }
        return res;
    }

    /*
    根据滑窗的有序的TreeSet集合求中位数
     */
    private double getMid(Set<int[]> set) {
        // 这里的mid取值有技巧:size=5->mid=2;size=4->mid=1
        int mid = (set.size() - 1) / 2;
        // 获取set迭代器
        Iterator<int[]> iterator = set.iterator();
        // 让迭代器走到中间以便求中位数
        // 例:[0,1,2,3,4],size=5->mid=2->指针停留在元素1处
        // 例:[0,1,2,3],size=4->mid=1->指针停留在元素0处
        // 正好符合接下来的中位数求解
        while (mid-- > 0) iterator.next();
        // size为偶数->指针停留在元素0处->中位数=(1+2)/2=1.5=(next()+next())/2
        // size为奇数->指针停留在元素1处->中位数=2=next()
        return set.size() % 2 == 0 ? (((double)iterator.next()[0] + iterator.next()[0]) / 2.0) : iterator.next()[0];
    }
}
