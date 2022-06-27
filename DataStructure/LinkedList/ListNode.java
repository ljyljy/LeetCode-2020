package DataStructure.LinkedList;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val){ this.val = val;}
    ListNode(int val, ListNode next) {this.val = val; this.next = next;}

    public void printNodes() {
        ListNode p = this; // dummy.next;
        while (p != null) {
            System.out.print(p.val + " ");
            p = p.next;
        }
    }
}
