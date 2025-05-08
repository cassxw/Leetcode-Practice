class Solution:
    def solve(self, board: List[List[str]]) -> None:
        """
        Do not return anything, modify board in-place instead.
        """

        # Array (DFS, BFS, Union Find, Matrix)

        # board = mxn matrix, containing cells of either 'X' or 'O'.
        m = len(board)      # Number of rows.
        n = len(board[0])   # Number of cols.

        # Capture regions that are surrounded.

        # Region = Connect every 'O' cell.

        # Surround = Regions are surrounded by 'X' cells if one can
        #            connect the Region with 'X', and none of its cells
        #            are on the edge of the board.

        # A helper DFS function to mark safe 'O's that are connected
        # to the border and therefore should not be flipped.
        def dfs(row: int, col: int):

            # BASE CASE:
            # If outside of grid or if not a 'O'...
            if (row < 0 or row >= m or col < 0 or col >= n or board[row][col] != 'O'):
                return

            # Temporarily mark the cell as 'S' to indicate it's safe
            board[row][col] = 'S'

            # Explore neighbours.
            dfs(row + 1, col) # Down
            dfs(row - 1, col) # Up
            dfs(row, col + 1) # Right
            dfs(row, col - 1) # Left

        # Start DFS from 'O's on the borders, since these cannot be captured.

        # Iterate over first and last rows.
        for j in range(n):
            # First row.
            if (board[0][j] == 'O'):
                dfs(0, j)
            # Last row.
            if (board[m-1][j] == 'O'):
                dfs(m - 1, j)

        # Iterate over fist and last cols.
        for i in range(1, m-1):
            # First col.
            if (board[i][0] == 'O'):
                dfs(i, 0)
            # Last col.
            if (board[i][n-1] == 'O'):
                dfs(i, n - 1)

        # Flip remaining 'O's = 'X' and 'S' = 'O'.
        for row in range(m):
            for col in range(n):

                if (board[row][col] == 'O'):
                    # This 'O' was not connected to a border => flip it.
                    board[row][col] = 'X'

                if (board[row][col] == 'S'):
                    # This 'O' was safe => revert back to 'O'.
                    board[row][col] = 'O'
            