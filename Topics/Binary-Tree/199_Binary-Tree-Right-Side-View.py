# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

from collections import deque

class Solution:
    def rightSideView(self, root: Optional[TreeNode]) -> List[int]:

        # Binary Tree (DFS, BFS)

        # Given the root of a binary tree,
        # imagine you are standing on the right side.
        # Return the values of the nodes you can see
        # ordered from top to bottom.

        # Breadth-First Search: at each level, only add the rightmost to our list.

        # EDGE CASE: Empty tree
        if root is None:
            return []

        result = []

        queue = deque([root])

        while queue:

            # Number of nodes at current level.
            level_size = len(queue)

            # Process all nodes at current level.
            for i in range(level_size):
                node = queue.popleft()

                # If this node is the last in the current level...
                if (i == level_size-1):
                    result.append(node.val)

                # Add children to the queue for next level
                if node.left:
                    queue.append(node.left)

                if node.right:
                    queue.append(node.right)

        return result   
