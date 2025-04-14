# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def __init__(self):
        self.minVal = float('inf')
        self.prev = None

    def minDiffInBST(self, root: Optional[TreeNode]) -> int:
        # Binary Tree (DFS, BFS)

        # Return the minimum absolute difference between
        # any two values of any two different nodes in the tree.

        # BASE CASE: if we reach a null node, return current minimum.
        if root is None:
            return self.minVal

        # Traverse left subtree first (inorder traversal).
        self.minDiffInBST(root.left)

        # Process current node:
        # If we have a previous value, calculate and update minimum difference.
        if self.prev is not None:
            self.minVal = min(self.minVal, root.val - self.prev)

        # Update previous value for next comparison.
        self.prev = root.val

        # Traverse right subtree (inorder traversal).
        self.minDiffInBST(root.right)

        return self.minVal