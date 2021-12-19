package DataStructure.Tree;

public class q654_maximum_binary_tree {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return dfs(nums, 0, nums.length-1);
    }

    private TreeNode dfs(int[] nums, int start, int end) {
        if (start > end) return null;
        int rootVal = nums[start], rootIdx = start;
        for (int i = start+1; i <= end; i++) {
            if (nums[i] > rootVal) {
                rootVal = nums[i];
                rootIdx = i;
            }
        }
        TreeNode root = new TreeNode(rootVal);
        root.left = dfs(nums, start, rootIdx-1);
        root.right = dfs(nums, rootIdx+1, end);
        return root;
    }
}
