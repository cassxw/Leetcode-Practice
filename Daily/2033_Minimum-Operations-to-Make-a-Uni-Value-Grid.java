class Solution {
    public int minOperations(int[][] grid, int x) {
        
        // Array (Math, Sorting, Matrix)

        // grid = 2D Integer of m x n
        // x = int

        // In one operation, you can add x or subtract x from any element in the grid.

        // "uni-value grid"
        // => A grid where all the elements are equal.

        // Return the minimum number of operations to make grid uni-value.
        // If not possible, return -1.

        // To make it easier to process, convert to 1D array.
        int m = grid.length;
        int n = grid[0].length;
        int[] nums = new int[m * n];

        // O(mn)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                nums[i * n + j] = grid[i][j];
            }
        }

        // Now, nums is a 1D equivalent of grid, ignoring the 2D structure.

        // Key observation, is that the most optimal target value for the uni-value grid,
        // would be the median value of the grid: most "closest" for each cell to get to.

        // Sort array to find median.
        Arrays.sort(nums); // O(log(mn))
        int median = nums[nums.length / 2];
        
        // Check that it is possible for all numbers to reach the median.
        int operations = 0;
        for (int num : nums) {

            // If difference between the current num and the median is not divisible by x, it's impossible.
            if (Math.abs(num - median) % x != 0) {
                return -1;
            }

            // Calculates the number of operations needed to make it equal to the median.
            // If the number is equal to the median, no operations are needed.
            operations += Math.abs(num - median) / x;
        }
        
        return operations;
    }
}