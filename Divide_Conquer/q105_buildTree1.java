package Divide_Conquer;

public class q105_buildTree1 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return dfs(preorder, 0, preorder.length-1,
                inorder, 0, inorder.length-1);
    }

    private TreeNode dfs(int[] preorder, int preStart, int preEnd,
                         int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd) return null;

        // root 节点对应的值就是前序遍历数组的第一个元素
        int rootVal = preorder[preStart];
        // 根据rootVal，在中序遍历找到其对应下标
        int rootIdx = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootVal) {
                rootIdx = i;
                break;
            }
        }

        int leftSize = rootIdx - inStart; // 中序的左区间长度（与后序左区间长度一样！）
        // 先构造出当前根节点
        TreeNode root = new TreeNode(rootVal);

        // 前序：根preStart，左[pS+1, +leftSize-1]，右[左のend+1, preEnd];
        // 中序：左[iS, rI-1]，根rootIdx，右[rI+1,inEnd]
        root.left = dfs(preorder, preStart+1, preStart + leftSize,  // 前序&中序の左区间
                inorder, inStart, rootIdx-1);
        root.right = dfs(preorder, preStart+leftSize+1, preEnd,  // 前序&中序の右区间
                inorder, rootIdx+1, inEnd);
        return root;
    }
    // py
//    class Solution:
//    def buildTree(self, preorder: List[int], inorder: List[int]) -> TreeNode:
//            if not inorder: return None
//            root = TreeNode(preorder[0])
//    rootIdx = inorder.index(root.val)  # 中序遍历中的root_idx
//    root.left = self.buildTree(preorder[1: 1+rootIdx], inorder[ :rootIdx])
//    root.right = self.buildTree(preorder[rootIdx+1: ], inorder[rootIdx+1: ])
//            return root
}
