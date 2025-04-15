class Solution:
    def __init__(self):
        # Grid dimensions.
        m = 0
        n = 0

    def dfs(self, grid: List[List[str]], row: int, col: int):
        """
        Depth-First Search to mark all connected "land" cells as visited.
        Uses '#' to mark visited cells to avoid revisiting them.
        
        Args:
            grid: The input grid
            row: Current row position
            col: Current column position
        """
        # Base cases: out of bounds or not a land cell.
        if row < 0 or col < 0 or row >= self.m or col >= self.n or grid[row][col] != '1':
            return

        # Mark current land cell as visited.
        grid[row][col] = '#'

        # Recursively visit all adjacent land cells in 4 directions:
        self.dfs(grid, row+1, col) # Down
        self.dfs(grid, row-1, col) # Up
        self.dfs(grid, row, col+1) # Right
        self.dfs(grid, row, col-1) # Left

    def numIslands(self, grid: List[List[str]]) -> int:

        # Array (DFS, BFS, Union Find, Matrix) => Graphs

        # grid = a mxn binary grid.
        # Represents a map of 1s (land) and 0s (water)
        self.m = len(grid)
        self.n = len(grid[0])

        # An "island" is surrounded by water,
        # formed by connecting adjacent land horizontally or vertically.
        
        # Assumption: all four edges of the grid are surrounded by water.

        # Return the number of "islands".

        # EDGE CASE: Empty grid.
        if not grid:
            return 0

        # Approach:
        # - Depth-First Search in all 4 directions when land is encountered.
        # - At each DFS step, consume (or "sink") all land encountered.

        # Count to keep track of number of islands.
        count = 0

        # Iterate through each cell in the grid.
        for row in range(self.m): # O(m)
            for col in range(self.n): # O(n)

                # When we find an unvisited land cell, it is a part of a new island.
                if (grid[row][col] == '1'):

                    # DFS Helper: Mark all connected land cells as visited.
                    self.dfs(grid, row, col)
                    count += 1

        return count
        # = O(mn)
        