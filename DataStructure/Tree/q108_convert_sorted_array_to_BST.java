package DataStructure.Tree;

public class q108_convert_sorted_array_to_BST {
    public TreeNode sortedArrayToBST(int[] nums) {
        return traversal(nums, 0, nums.length-1);
    }

    // ������� ����BST
    private TreeNode traversal(int[] nums, int start, int end) {
        if (start > end) return null;
        int mid = start + end >> 1; // [L, mid-1] mid [mid+1, R]
        TreeNode root = new TreeNode();
        root.left = traversal(nums, start, mid-1); // ��
        root.val = nums[mid]; // �У������ڴ˴����¸�ֵ��
        root.right = traversal(nums, mid+1, end); // ��
        return root;
    }
}
