package DataStructure.LinkedList;

import java.util.ArrayList;

public class q82_qo19_remove_duplicates_from_sorted_list_ii {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode p = dummy, q = p.next;
        while (p.next != null) {
            q = p.next;

            // while (nxt.val == nxt.next.val) { // 错误！nxt会动态后移！
            //     nxt = nxt.next;
            // }
            // cur.next = nxt.next;
            while (q != null && p.next.val == q.val) { //
                q = q.next; // q至少后移一步
            }
            if (p.next.next == q)   // 不含重复，q后移一步
                p = p.next; // p正常后移（大while决定）
            else
                p.next = q;

        }
        return dummy.next;
    }

    private ListNode init(String array){
        ListNode dummy = new ListNode(-1);
//        Scanner sc = new Scanner(System.in);
//        System.out.println("input array: ");
//        String array = sc.nextLine();

//        String array = "1 2 3 3 4 4 5";
        System.out.println("origin array_String: " + array);

        ArrayList<Integer> numArr = new ArrayList<>();
        String[] nums = array.split(",");
        for (String num: nums) {
            numArr.add(Integer.valueOf(num));
        }
        ListNode cur = dummy;
        for (int num: numArr) {
            cur.next = new ListNode(num);
            cur = cur.next;
        }
        cur = dummy;
        while (cur.next != null){
            System.out.print(cur.next.val + " ");
            cur = cur.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        q82_qo19_remove_duplicates_from_sorted_list_ii sol = new q82_qo19_remove_duplicates_from_sorted_list_ii();
        ListNode head = sol.init("1,2,3,3,4,4,5");

        ListNode head2 = sol.deleteDuplicates(head);
        ListNode cur = head2;
        System.out.println("\nafter deleteDuplicates: ");
        while (cur != null){
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
    }
}

