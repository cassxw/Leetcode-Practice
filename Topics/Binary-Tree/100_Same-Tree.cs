/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left;
 *     public TreeNode right;
 *     public TreeNode(int val=0, TreeNode left=null, TreeNode right=null) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
public class Solution {
    public bool IsSameTree(TreeNode p, TreeNode q) {
        
        // Binary Tree (DFS, BFS)

        // p, q = roots of 2 Binary Trees.
        
        // Check if they are the same or not.
        // i.e. structurally identical, and nodes have same value.

        // BASE CASE 1:
        // If both nodes are null, reached the end of a branch in both,
        // and so far everything matches - return true.
        if (p == null && q == null) {
            return true;
        }

        // BASE CASE 2:
        // If one node is null but the other is not, OR
        // if the values at current nodes don't match,
        // then the trees are different - return false.
        if (p == null || q == null || p.val != q.val) {
            return false;
        }

        // Recursive Step:
        // Both nodes are not null and values match,
        // so we check whether:
        // - The left subtrees are the same.
        // - The right subtrees are the same.
        // Only if both are true, do we return true.
        return IsSameTree(p.left, q.left) && IsSameTree(p.right, q.right);
    }
}