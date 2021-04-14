package out.Divide_Conquer.MergeSort_Heap;


import java.util.Comparator;
import java.util.PriorityQueue;

public class q23_merge_k_sorted_lists {
    private class ListNode {
        ListNode next;
        int val;
        public ListNode() {}
        public ListNode(int val) {this.val = val;}
        public ListNode(int val, ListNode next){
            this.val = val;
            this.next = next;
        }
    }

    // 法1：归并排序 - 写法1（写法2: 两两合并见py法2）
    public ListNode mergeKLists_v1(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;
        return mergeHelper(lists, 0, lists.length-1);
    }

    private ListNode mergeHelper(ListNode[] lists, int start, int end) {
        if (start == end)
            return lists[start];
        int mid = start + end >> 1;
        ListNode left = mergeHelper(lists, start, mid);
        ListNode right = mergeHelper(lists, mid+1, end);
        return mergeTwoLists(left, right);
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                p.next = l1;
                p = l1; // 指针后移
                l1 = l1.next;
            } else {
                p.next = l2;
                p = l2; // 指针后移
                l2 = l2.next;
            }
        }
        if (l1 != null) p.next = l1;
        if (l2 != null) p.next = l2;
        return dummy.next;
    }

    // 法2：最小堆minHeap（每次将K个头结点放入堆，pop出的就是最小的元素）
    //  - 每次 O(logK) 比较 K个指针求 min, 时间复杂度：O(NlogK)
    private Comparator<ListNode> listNodeComparator = new Comparator<ListNode>() {
        @Override
        public int compare(ListNode l1, ListNode l2) {
            return l1.val - l2.val; // 升序 l1 < l2 < ...
        }
    };

    public ListNode mergeKLists_v2(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;
        PriorityQueue<ListNode> heap = new PriorityQueue<>(lists.length, listNodeComparator);
        for (ListNode listNode : lists){
            if (listNode == null) continue; // 勿漏！！！ 形如:[[]]
            heap.add(listNode); // 加入的只有每个链表的头部
        }

        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        while (!heap.isEmpty()) {
            ListNode cur = heap.poll(); // 不是pop！
            p.next = cur;
            p = cur;
            if (cur.next != null)
                heap.add(cur.next); // 每个链表的next
        }
        return dummy.next;
    }
}
