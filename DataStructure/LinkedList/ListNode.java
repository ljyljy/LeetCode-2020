package DataStructure.LinkedList;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode() {}
    public ListNode(int val){ this.val = val;}
    public ListNode(int val, ListNode next) {this.val = val; this.next = next;}

    public void printNodes() {
        ListNode p = this; // dummy.next;
        while (p != null) {
            System.out.print(p.val + " ");
            p = p.next;
        }
    }
}
