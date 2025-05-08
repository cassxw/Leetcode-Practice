class Solution {
    public int minTimeToReach(int[][] moveTime) {
        
        // Array (Graph,Heap, Matrix, Shortest Path)

        // moveTime = 2D array of size nxm.
        int n = moveTime.length;
        int m = moveTime[0].length;

        // moveTime[i][j] represents the min time in s
        // when you can start moving to that room,
        // starting from room (0, 0) at t=0.
        // i.e. You can only enter room (i, j) at a time t_entry > moveTime[i][j].
        //      So, earliest entry is moveTime[i][j] + 1.

        // Valid movements are 4-directional (up, down, left, right).
        
        // Moving between adjacent rooms takes:
        //      - 1s for one move.
        //      - 2s for the next, alternating between the two.

        // Return the min time to reach room (n-1, m-1).

        // -------------------- Dijkstra's Algorithm!! -------------------
        // Why? Because each room is a node, and movement is time-weighted.

        // Use a heap <time, row, col, movesMade> to always expand the room with lowest time.
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        
        // Start at top-left room (0,0) at time = 0.
        pq.offer(new int[]{0, 0, 0, 0}); // <time, row, col, movesMade>.
    
        // Each move (up/down/left/right) is an edge with weight 1s.
        int[] dRow = {-1, 1, 0, 0}; // direction row.
        int[] dCol = {0, 0, -1, 1}; // direction col.

        // - BUT: Can't enter a room (i, j) before moveTime[i][j] seconds; so, if early => wait.

        // dist[row][col][0] = min time to reach (row,col) with an even number of moves.
        // dist[row][col][1] = min time to reach (row,col) with an odd number of moves.
        int[][][] dist = new int[n][m][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
            }
        }

        // Start at (0,0) at time 0, with 0 moves made, i.e. even parity.
        dist[0][0][0] = 0;

        // - For step:
        //      > Pop the <time, row, col> with the smallest time.
        //      > For each adj room, check if going through is is faster than the known time.   
        // - Repeat until target room (n-1, m-1) is reached.

        // Explore rooms in order of earliest arrival time.
        while (!pq.isEmpty()) {

            // Pop the <time, row, col, movesMade> with the smallest time.
            int[] current = pq.poll();
            int time = current[0];
            int row = current[1];
            int col = current[2];
            int movesMade = current[3];

            // If we have reached the target room, i.e. bottom-right...
            if (row == n-1 && col == m-1) {
                // Finished; Return the time.
                return time;
            }

            // If current time is already greater than a previous path found...
            if (time > dist[row][col][movesMade % 2]) {
                // Eliminate this path; skip.
                continue;
            }

            // Iterate through all 4 adjacent rooms to current room.
            for (int i = 0; i < 4; i++) {
                int new_row = row + dRow[i];
                int new_col = col + dCol[i];

                // Ensure the move stays within grid bounds.
                if (new_row >= 0 && new_row < n && new_col >= 0 && new_col < m) {
                    
                    // Movement cost alternates:
                    // - Even number of moves: next move takes 1s.
                    // - Odd number of moves: next move takes 2s.
                    int moveCost = 1;
                    if (movesMade % 2 != 0) {
                        moveCost = 2;
                    }

                    // Time to arrive at new room without considering its own moveTime constraint.
                    int baseEntry = time + moveCost; 

                    // Earliest time to enter new room due to its moveTime constraint.
                    int minEntry = moveTime[new_row][new_col] + moveCost;
                    
                    // If we arrive too early, wait.
                    int effectiveEntry = Math.max(baseEntry, minEntry);

                    int new_movesMade = movesMade + 1;
                    int new_parity = new_movesMade % 2; // 0 for even, 1 for odd.

                    // If this is a faster way to reach the neighbor,
                    if (effectiveEntry < dist[new_row][new_col][new_parity]) {

                        // Update and explore it.
                        dist[new_row][new_col][new_parity] = effectiveEntry;
                        pq.offer(new int[]{effectiveEntry, new_row, new_col, new_movesMade});
                    }
                }
            }
        }

        // If room (n-1, m-1) is unreachable...
        return -1;
    }
}