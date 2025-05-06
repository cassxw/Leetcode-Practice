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
    public List<Double> averageOfLevels(TreeNode root) {
        
        // Binary Tree (DFS, BFS)

        // root = The root of a Binary Tree.

        // Return the average value of the nodes at each level
        // in the form of an array.

        // List to store average values per level.
        List<Double> avgs = new ArrayList<>();

        // Queue to support level-order traversal.
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root); // Add the root to start BFS.

        // Continue until there are no more nodes to process.
        while (queue.size() != 0) {
            
            // Number of nodes at current level.
            int levelSize = queue.size();

            // Running sum for current level.
            long sum = 0;

            // Process all nodes at the current level.
            for (int i = 0; i < levelSize; i++) {

                // Add current node's value to sum.
                TreeNode node = queue.remove();
                sum += node.val;

                // Add children to the queue for next level
                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }
            }

            // All nodes in current level processed.
            // Calculate and store the average for current level.
            avgs.add((double) sum / levelSize);
        }

        // Return the list of averages, one for each level of the tree.
        return avgs;
    }
}