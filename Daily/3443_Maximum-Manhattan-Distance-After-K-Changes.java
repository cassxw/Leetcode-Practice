class Solution {
    public int maxDistance(String s, int k) {
        
        // Hash Table (Math, String, Counting)

        // k = int.
        // s = string, consisting of chars 'N', 'S', 'E', 'W'.
        // s[i] indicates movement in an infinite grid.

        // Initially you are at (0,0).
        // You can change at most k characters to any of the 4 directions.

        // Manhattan(xi, yi, xj, yj) = |xi - xj| + |yi - yj|

        // Find the maximum Manhattan distance from the origin that can be
        // achieved at any time, while performing the movements in order.

        // Map each direction to its (dx, dy) vector.
        Map<Character,int[]> dirs = Map.of(
            'N', new int[]{ 0,  1},  // North: +1 on y-axis
            'S', new int[]{ 0, -1},  // South: -1 on y-axis
            'E', new int[]{ 1,  0},  // East:  +1 on x-axis
            'W', new int[]{-1,  0}   // West:  -1 on x-axis
        );

        // Array of move chars for indexing.
        char[] moves = {'N','S','E','W'};
        
        // Precompute best possible "gain" for changing any original move
        // into the optimal new move for each target quadrant.
        // bestGain[pIdx][qIdx] = max. gain from redirecting move p in quadrant q.
        int[][] bestGain = new int[4][4]; 

        // Define four quadrants as (sx, sy) signs: (+,+), (+,-), (-,+), (-,-).
        int[][] quadrants = {{1,1}, {1,-1}, {-1,1}, {-1,-1}};
        
        // Compute bestGain table.
        for (int p = 0; p < 4; p++) {
            char original = moves[p];
            int px = dirs.get(original)[0];
            int py = dirs.get(original)[1];
            
            for (int q = 0; q < 4; q++) {

                // Sign for desired movement orientation.
                int sx = quadrants[q][0];
                int sy = quadrants[q][1];
                
                int best = 0;
                // Try changing original move to any other direction.
                for (int r = 0; r < 4; r++) {

                    if (r == p) {
                        // Skip no-change case.
                        continue;
                    }

                    char replacement = moves[r];
                    int rx = dirs.get(replacement)[0];
                    int ry = dirs.get(replacement)[1];
                    
                    // Gain is how much oriented distance in quadrant grows.
                    int gain = sx * (rx - px) + sy * (ry - py);
                    best = Math.max(best, gain);
                }
                bestGain[p][q] = best;
            }
        }
        
        // Count of each move type in current prefix.
        int[] count = new int[4];
        
        // Current raw position (dx, dy) without any changes.
        int x = 0;
        int y = 0;

        int answer = 0;
        
        // Process each step in path.
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // Determine index of this move for counting.
            int pIdx = switch(c) {
                case 'N' -> 0;
                case 'S' -> 1;
                case 'E' -> 2;
                case 'W' -> 3;
                default  -> -1;
            };

            // Increment count and update raw position.
            count[pIdx]++;
            x += dirs.get(c)[0];
            y += dirs.get(c)[1];
            
            // Number of changes we can use so far.
            int steps = i + 1;
            int m = Math.min(k, steps);
            
            // Evaluate all four quadrants for best re-orientation.
            for (int q = 0; q < 4; q++) {
                int sx = quadrants[q][0];
                int sy = quadrants[q][1];
                
                // Base oriented distance: projection of (x, y) onto (sx, sy).
                int orientedBase = sx * x + sy * y;
                
                // Gather potential gains from changing moves in this prefix.
                int[] gains = new int[4];
                for (int p = 0; p < 4; p++) {
                    gains[p] = bestGain[p][q];
                }
                
                // Sort move types by descending gain.
                Integer[] idx = {0,1,2,3};
                Arrays.sort(idx, (a,b) -> Integer.compare(gains[b], gains[a]));
                
                // Use up to m changes on highest-gain moves.
                int used = 0, gainSum = 0;
                for (int id : idx) {
                    if (used >= m) {
                        break;
                    }
                    
                    int take = Math.min(count[id], m - used);
                    gainSum += take * gains[id];
                    used += take;
                }
                
                // Update global maximum distance.
                answer = Math.max(answer, orientedBase + gainSum);
            }
        }
        
        return answer;
    }
}
