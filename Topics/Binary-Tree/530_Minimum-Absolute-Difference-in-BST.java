/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    // Track the minimum difference found so far.
    int min = Integer.MAX_VALUE;

    // Keep track of the previously visited node's value.
    Integer prev = null;

    public int getMinimumDifference(TreeNode root) {
        
        // Binary Tree (DFS, BFS)

        // Return the minimum absolute difference between
        // any two values of any two different nodes in the tree.

        // BASE CASE: if we reach a null node, return current minimum.
        if (root == null) return min;

        // Traverse left subtree first (inorder traversal).
        getMinimumDifference(root.left);

        // Process current node:
        // If we have a previous value, calculate and update minimum difference.
        if (prev != null) {
            min = Math.min(min, root.val - prev);
        }

        // Update previous value for next comparison.
        prev = root.val;

        // Traverse right subtree (inorder traversal).
        getMinimumDifference(root.right);

        return min;
    }
}