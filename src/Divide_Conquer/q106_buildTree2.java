package Divide_Conquer;

public class q106_buildTree2 {
    // Py
//    class Solution:
//    def buildTree(self, inorder: List[int], postorder: List[int]) -> TreeNode:
//            if not postorder: return None
//            root = TreeNode(postorder[-1])
//    rootPos = inorder.index(root.val)              # 注意postorder的idx！！
//    root.left = self.buildTree(inorder[ : rootPos], postorder[ : rootPos])
//    root.right = self.buildTree(inorder[rootPos+1 : ], postorder[rootPos :-1])
//            return root
}

