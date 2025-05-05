class Solution {

    // Helper function to compute the number of ways to tile a 2xn board.
    private int dp(int n) {
        if (n == 1) {
            // Only one way to place a vertical domino on a 2x1 board.
            return 1;
        }

        // Since the answer may be very large, return it modulo 1e9 + 7.
        int MOD = 1000000007;

        // Create a memoisation array to store solutions to subproblems.
        long[] F = new long[n+1];
        F[0] = 1; // An empty board has one way to be "tiled" - do nothing.
        F[1] = 1; // Only one vertical domino fits.
        F[2] = 2; // Two vertical dominoes or two horizontal dominoes.

        // F[0] = 1
        // F[1] = 1
        // F[2] = 2
        // F[3] = F[2]*2 + F[0] = 2*2 + 0

        // General Recurrence:
        // F[n] = 2 * F[n-1] + F[n-3]
        // Explanation:
        // - 2 * F[n-1]: placing vertical domino or horizontal pair and extending the remaining board.
        // - F[n-3]:     handles L-shaped trominoes that cover 3 columns in a special way.

        for (int i = 3; i <= n; i++) {
            F[i] = (F[i-1] * 2 + F[i-3]) % MOD;
        }

        // Cast back to int for final result.
        return (int) F[n];
    }

    public int numTilings(int n) {

        // Dynamic Programming

        // n = integer.

        // 2 types of tiles:
        // (1) Domino Shape
        // (2) Tromino Shape

        // Return the number of ways to tile an 2xn board.
        // Every square must be covered by a tile.

        // 2 tilings are different iif there are two 4-directionally adj
        // such that exactly one of the tilings has both squares occupied by a tile.

        return dp(n);
    }
}