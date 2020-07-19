package Divide_Conquer;

public class q105_buildTree1 {
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
