package DataStructure.Tree;

public class q450_delete_node_in_bst {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (root.val == key) {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;
            else { // ������������Ϊ�գ�
                // ����ɾ���.������(��min) => ������.������ ����(��min)
                TreeNode subTreeL = root.left;
                // ��������.������
                TreeNode cur = root.right;
                while (cur.left != null) {
                    cur = cur.left;
                }
                cur.left = subTreeL;
                root = root.right; // ɾ����ǰ���root, �ø��º�����������ǣ�
                return root;
            }
        } else if (root.val > key) { // ��������
            root.left = deleteNode(root.left, key);
        } else { // <  ��������
            root.right = deleteNode(root.right, key);
        }
        return root;
    }

    // ��ͨ��������ɾ��
    // 1. swap(��ɾ���.val, ������.������.val)
    // 2. ���α���ʱ����NULL����(��swap�󣬱�Ҷ��)
    public TreeNode deleteNode2(TreeNode root, int key) {
        if (root == null) return null;
        if (root.val == key) {
            // ����ɾ���.������Ϊ�գ�ֱ�ӷ���������, �滻��ǰ�ڵ�
            if (root.right == null) return root.left;
            // �������ǿ�: 1. swap(��ɾ���.val, ������.������.val)
            //      2. ���α���ʱ����NULL����(��swap�󣬱�Ҷ��)
            // �� ��������.������
            TreeNode prev = root;
            TreeNode cur = root.right;
            while (cur.left != null) {
                prev = cur;
                cur = cur.left;
            }
            swap(root, cur); // ע��һ��Ҫ�������ã�ֵ�滻��
        }
        root.left = deleteNode2(root.left, key);
        root.right = deleteNode2(root.right, key);
        return root;
    }

    private void swap(TreeNode n1, TreeNode n2) {
        int tmp = n1.val;
        n1.val = n2.val;
        n2.val = tmp;
    }
}
