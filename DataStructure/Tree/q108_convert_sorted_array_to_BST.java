package DataStructure.Tree;

public class q108_convert_sorted_array_to_BST {
    public TreeNode sortedArrayToBST(int[] nums) {
        return traversal(nums, 0, nums.length-1);
    }

    // 中序遍历 构造BST
    private TreeNode traversal(int[] nums, int start, int end) {
        if (start > end) return null;
        int mid = start + end >> 1; // [L, mid-1] mid [mid+1, R]
        TreeNode root = new TreeNode();
        root.left = traversal(nums, start, mid-1); // 左
        root.val = nums[mid]; // 中：必须在此处重新赋值！
        root.right = traversal(nums, mid+1, end); // 右
        return root;
    }
}
