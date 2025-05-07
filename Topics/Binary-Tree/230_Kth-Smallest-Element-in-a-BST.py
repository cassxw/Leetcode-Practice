# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def kthSmallest(self, root: Optional[TreeNode], k: int) -> int:

        # Binary Tree (DFS)

        # root = The root of a BST.
        # k = integer.

        # Return the kth smallest value of all
        # the values of the nodes of the BST.

        # In-Order Traversal!!
        # i.e. Visits nodes in ascending order.
        
        # Instance variables to keep track of count of visited & kth value.
        self.k_count = 0
        self.kth_value = 0

        # Start the recursive in-order traversal.
        self.inorder_traverse(root, k)

        return self.kth_value

    def inorder_traverse(self, node: Optional[TreeNode], k: int):
        # BASE CASE:
        # If node is None, reached the end of a branch; return.
        if (not node):
            return

        # (1) Traverse the left subtree of node.
        #     All nodes in the left subtree are smaller than node.
        self.inorder_traverse(node.left, k)

        # If the kth smallest element was found, stop.
        if (self.k_count == k):
            return

        # (2) Process the current node.

        # Increment the count of visited nodes.
        self.k_count += 1

        # If the current node is the k-th smallest...
        if (self.k_count == k):
            # Store its value, and stop.
            self.kth_value = node.val
            return

        # (3) Traverse the right subtree of node.
        #     Only if kth element has not been found yet,
        #     i.e. not root of in left subtree.
        self.inorder_traverse(node.right, k)
