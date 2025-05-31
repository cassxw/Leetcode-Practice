public class Solution {

    private (int row, int col) GetBoardCoordinates(int square, int n) {

        // Convert to 0-based indexing.
        square--; 

        // Convert to board coordinates (top to bottom).
        int rowFromBottom = square / n;
        int actualRow = n - 1 - rowFromBottom; 
        
        int col = square % n;

        // If row from bottom is odd, reverse the column direction
        if (rowFromBottom % 2 == 1) {
            col = n - 1 - col;
        }
        
        return (actualRow, col);
    }

    public int SnakesAndLadders(int[][] board) {
        
        // Array (BFS, Matrix)

        // board = n x n integer matrix where...
        // - Cells are labeled from [1:n^2] in a Boustrophedon style,
        //   starting from the bottom left of the board, i.e. board[n-1][0].
        // - Direction alternates each row.
        int n = board.Length;
        int target = n * n;
        
        // With each move, starting from square curr, do the following:
        //  (1) Choose a destination square, next, with a label in the range
        //      [curr + 1, min(curr + 6, n^2)], simulating rolling a 6-sided die.
        //  (2) If next has a snake or ladder, i.e != -1, move to the destination 
        //      of that snake/ladder. Otherwise, move to next.
        //  (3) If next is square n^2, game is over.

        // Return the least number of dice rolls required to reach square n^2.
        // If it is not possible, return -1.

        // Use BFS to find shortest path.
        Queue<int> queue = new Queue<int>();
        HashSet<int> visited = new HashSet<int>();
        Dictionary<int, int> moves = new Dictionary<int, int>(); // Square -> Moves needed

        // Player starts on square 1.
        queue.Enqueue(1);
        visited.Add(1);
        moves[1] = 0;
        
        while (queue.Count > 0) {

            int current = queue.Dequeue();
            
            // If we reached the target, return moves needed.
            if (current == target) {
                return moves[current]; // GAME WON
            }
            
            // Try all possible dice rolls (1-6)
            for (int dice = 1; dice <= 6; dice++) {
                int next = current + dice;
                
                // If we would exceed the target, skip
                if (next > target) continue;
                
                // Check if any position is a snake or ladder.
                var (row, col) = GetBoardCoordinates(next, n);

                // If a snake or ladder found...
                if (board[row][col] != -1) {
                    next = board[row][col];
                }

                // If we haven't visited this resulting position yet.
                if (!visited.Contains(next)) {
                    visited.Add(next);
                    moves[next] = moves[current] + 1;
                    queue.Enqueue(next);
                }
            }
        }

        // If we can't reach the target => IMPOSSIBLE.
        return -1;
    }
}