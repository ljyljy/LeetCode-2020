package DataStructure.LinkedList;

import java.util.HashMap;
import java.util.Map;

public class q138_copy_list_with_random_pointer {
    class Node {
        int val;
        Node next;
        Node random;
        public Node(){}
        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    // <srcNode, copyNode> ↓
    private Map<Node, Node> nodes = new HashMap<>();
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        Node p = head;
        if (!nodes.containsKey(p)) {
            // <src, p_copy>加入遍历列表
            Node p_copy = new Node(p.val);
            nodes.put(p, p_copy);
            p_copy.next = copyRandomList(p.next);
            p_copy.random = copyRandomList(p.random);
        }
        return nodes.get(p);
    }

}
