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
        // Moving from one room to an adjacent one takes 1 second.

        // Return the min time to reach room (n-1, m-1).

        // -------------------- Dijkstra's Algorithm!! -------------------
        // Why? Because each room is a node, and movement is time-weighted.

        // Use a heap <time, row, col> to always expand the room with lowest time.
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        
        // Start at top-left room (0,0) at time = 0.
        pq.offer(new int[]{0, 0, 0}); // <time, row, col>.
    
        // Each move (up/down/left/right) is an edge with weight 1s.
        int[] dRow = {-1, 1, 0, 0}; // direction row.
        int[] dCol = {0, 0, -1, 1}; // direction col.

        // - BUT: Can't enter a room (i, j) before moveTime[i][j] seconds; so, if early => wait.

        // dist[i][j] keeps track of the earliest time we can reach room (i, j).
        int[][] dist = new int[n][m];
        for (int[] row : dist) {
            // Fill with an arbitrary max value, as we want to find min.
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // Start at time 0.
        dist[0][0] = 0;

        // - For step:
        //      > Pop the <time, row, col> with the smallest time.
        //      > For each adj room, check if going through is is faster than the known time.   
        // - Repeat until target room (n-1, m-1) is reached.

        // Explore rooms in order of earliest arrival time.
        while (!pq.isEmpty()) {

            // Pop the <time, row, col> with the smallest time.
            int[] current = pq.poll();
            int time = current[0], row = current[1], col = current[2];

            // If we have reached the target room, i.e. bottom-right...
            if (row == n-1 && col == m-1) {
                // Finished; Return the time.
                return time;
            }

            // If current time is already greater than a previous path found...
            if (time > dist[row][col]) {
                // Eliminate this path; skip.
                continue;
            }

            // Iterate through all 4 adjacent rooms to current room.
            for (int i = 0; i < 4; i++) {
                int new_row = row + dRow[i];
                int new_col = col + dCol[i];

                // Stay within grid bounds.
                if (new_row >= 0 && new_row < n && new_col >= 0 && new_col < m) {
                    
                    // Time to arrive at new room without considering its own moveTime constraint.
                    int baseEntry = time + 1; 

                    // Earliest time to enter new room due to its moveTime constraint.
                    int minEntry = moveTime[new_row][new_col] + 1;
                    
                    // If we arrive too early, wait.
                    int effectiveEntry = Math.max(baseEntry, minEntry);

                    // If this is a faster way to reach the neighbor,
                    if (effectiveEntry < dist[new_row][new_col]) {

                        // Update and explore it.
                        dist[new_row][new_col] = effectiveEntry;
                        pq.offer(new int[]{effectiveEntry, new_row, new_col});
                    }
                }
            }
        }

        // If room (n-1, m-1) is unreachable...
        return -1;
    }
}