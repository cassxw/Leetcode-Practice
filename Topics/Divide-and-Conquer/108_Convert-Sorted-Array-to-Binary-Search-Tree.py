# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def sortedArrayToBST(self, nums: List[int]) -> Optional[TreeNode]:

        # Array (Divide-and-Conquer, Binary Tree)

        # nums = sorted, integer array.
        n = len(nums)

        # Convert nums into a height-balanced BST.

        # DIVIDE INTO A SMALLER PROBLEM:
        # Median becomes the root.
            # Median of the left subarray becomes the root's left child.
            # Median of the right subarray becomes the root's right child.
                #...

        return self.createTree(nums, 0, n-1)

    def createTree(self, nums: List[int], start: int, end: int) -> TreeNode:

        # BASE CASE: When the subarray is empty, return None.
        if (start > end):
            return None

        # Find the median of the current subarray.
        mid = (start + end) // 2

        # Create a new tree node with the median as the root.
        node = TreeNode(nums[mid])

        # Recursively build the left & right subtrees,
        # using the respective subarray.
        node.left = self.createTree(nums, start, mid-1)
        node.right = self.createTree(nums, mid+1, end)

        # Return the constructed node, which is now the root of this subtree.
        return node