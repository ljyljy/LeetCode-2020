package DataStructure.Heap;

import java.util.Arrays;
import java.util.PriorityQueue;

// 类比q23, 373, 786, 719（PQ超时 -> 二分+双指针）
public class q719_find_k_th_smallest_pair_distance {
    // 法2：二分答案 + 双指针/滑窗 -- O(nlogn + nlogW)
    public int smallestDistancePair(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums); // O(nlogn)
        // 二分答案：数对距离k∈[ 0, nums[-1]-nums[0] ]
        int start = 0, end = nums[n-1] - nums[0];
        while (start < end) { // O(logW), W=end0
            int mid = start + end >> 1;
            if (tryCnt(nums, mid) >= k) { // O(n)
                // 共tryCnt个数对的距离<=mid(若tryCnt>=k,说明mid可以更小)
                end = mid;
            } else start = mid + 1;
        }
        return start;
    }
    // 双指针/滑动窗口：统计数对距离<=mid的个数 - O(n)
    private int tryCnt(int[] nums, int mid) {
        int cnt = 0;
        int left = 0, right = 0;
        while (right < nums.length) {
            while (nums[right] - nums[left] > mid) {
                left++;
            } // 退出时，说明数对[L ~ (R-1), R]的距离<=mid
            cnt += right - left;
            right++;
        }
        return cnt;
    }

    // 法1（TLE）：堆 O(NlogK)，即O(n^2*log(n)) - 超时
    // N = n_nums + n_k, 数对距离k最多可以有n*(n-1)种
    // K = n_nums，堆中元素个数
    public int smallestDistancePair_heap_TLE(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums); // 保证堆中Node<i, j>距离升序
        // 按照距离对，升序
        PriorityQueue<Node> minHeap = new PriorityQueue<Node>(n,
                (n1, n2)->(nums[n1.second]-nums[n1.first]
                        - (nums[n2.second]-nums[n2.first])));
        // 将所有<i, i+1>入堆，堆内共n个元素
        for (int i = 0; i+1 < n; i++) {
            minHeap.offer(new Node(i, i+1));
        }
        Node node = null;
        while (k-- > 0) {
            // poll出第m小的距离对<f,s>，入堆<f,s+1>（距离比poll的更大些）
            // 保持堆内总共n个元素
            node = minHeap.poll();
            if (node.second+1 < n) {
                minHeap.offer(new Node(node.first, node.second+1));
            }
        } // 退出时，恰好为第k小的距离对
        return nums[node.second] - nums[node.first];
    }

    private class Node {
        int first, second;
        public Node(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}
