class Solution {

    // Memoisation Table:
    // memo[row][i] stores the minimum path sum starting from (row, i).
    private int[][] memo;

    private int dp(List<List<Integer>> triangle, int i, int row) {

        // BASE CASE: If on last row, return the value at (row, i).
        if (row == triangle.size() - 1) {
            return triangle.get(row).get(i);
        }

        // If already computed, return cached value.
        if (memo[row][i] != Integer.MAX_VALUE) {
            return memo[row][i];
        }

        // Test taking index i of below.
        // Recursive call: move to the next row, same index (i).
        int firstAdj = dp(triangle, i, row + 1);

        // Test taking index i+1 of below.
        // Recursive call: move to the next row, next index (i + 1).
        int secondAdj = dp(triangle, i + 1, row + 1);

        // Current value plus the min of two adjacent paths.
        int currentVal = triangle.get(row).get(i) + Math.min(firstAdj, secondAdj);

        // Store the result in memo table.
        memo[row][i] = currentVal;

        return currentVal;
    }

    public int minimumTotal(List<List<Integer>> triangle) {

        // Array (Dynamic Programming) -> Multi-Dimensional

        // triangle = 2D integer ArrayList
        int n = triangle.size();

        // Return the minimum path sum from top to bottom in triangle.
        // For each step, you can move to an adjacenet number of the row below:
        //      - index i in next row.
        //      - index i+1 in next row.

        // Fill memo with arbitrary big number, to know if we've visited.
        memo = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], Integer.MAX_VALUE);
        }

        // Start from (0, 0), top of triangle.
        return dp(triangle, 0, 0);
    }
}