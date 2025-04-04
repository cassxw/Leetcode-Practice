# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def lcaDeepestLeaves(self, root: Optional[TreeNode]) -> Optional[TreeNode]:

        # Binary Tree (Hash Table, Tree, Depth-First Search, Breadth-First Search)

        # Given the root of a Binary Tree, return the LCA of its deepest leaves.

        # "LCA" = Lowest Common Ancestor of a set S of nodes,
        # is the node A with the largest depth, such that
        # every node in S is in the subtree with root A.

        # Recall:
        # - A node is a leaf if and only if it has no children.
        # - The depth of the root is 0.
        # - If the depth of a node is d, the depth of its children is d+1.

        # EDGE CASE: Only one node, the root.
        if (root.left is None) and (root.right is None):
            return root

        # Post-Order Traversal
        def post_order(node):

            # BASE CASE: If current node is none, return.
            if node is None:
                return (None, -1)

            # Recursively process the left and right subtrees.
            left_lca, left_depth = post_order(node.left)
            right_lca, right_depth = post_order(node.right)

            # If the left is deeper than the right, return the left subtree's LCA.
            if left_depth > right_depth:
                return (left_lca, left_depth+1)
            # If the right is deeper than the left, return the right subtree's LCA.
            elif right_depth > left_depth:
                return (right_lca, right_depth+1)
            # If the depths are equal, the current node is the LCA.
            else:
                return (node, left_depth+1)

        # Perform the Post-Order Traversal
        (lca, _) = post_order(root)
        return lca
