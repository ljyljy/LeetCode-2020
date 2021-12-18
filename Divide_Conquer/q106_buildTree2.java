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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return dfs(inorder, 0, inorder.length-1,
                postorder, 0, postorder.length-1);
    }

    private TreeNode dfs(int[] inorder, int inStart, int inEnd,
                         int[] postorder, int postStart, int postEnd) {
        if (inStart > inEnd) return null;

        int rootVal = postorder[postEnd];
        int rootIdx = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootVal) {
                rootIdx = i;
                break;
            }
        }
        // 中序： 左[inS, rI-1]，root，右 [rI+1, inE]; 左长度|L|=(rI-1)-inS+1=rI-iS
        // 后序： 左[pS, pS+|L|-1]，右[前者+1, pE-1]，root(pE)
        TreeNode root = new TreeNode(rootVal);
        int leftSize = rootIdx - inStart;
        root.left = dfs(inorder, inStart, rootIdx-1,
                postorder, postStart, postStart+leftSize-1);
        root.right = dfs(inorder, rootIdx+1, inEnd,
                postorder, postStart+leftSize, postEnd-1);
        return root;
    }
}

