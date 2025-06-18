class Solution {
    public int[][] divideArray(int[] nums, int k) {
        
        // Array (Greedy, Sorting)

        // k = positive int.
        // nums = int array.
        int n = nums.length; // A multiple of 3.

        // Divide nums into n/3 arrays of size 3, such that
        // the difference between any two elements in one array <= k.

        // Return a 2D array contianing the arrays.
        // If impossible, return [].
        // If multiple, return any of them.

        Arrays.sort(nums);

        // Calculate how many groups of 3 needed.
        int groups = n / 3;

        int[][] result = new int[groups][3];

        // Try forming groups of 3 from sorted array.
        for (int i = 0; i < groups; i++)
        {
            int start = i * 3;

            // Check if diff between smallest and largest in current group > k.
            // Since sorted, only check first and third elements.
            if (nums[start + 2] - nums[start] > k)
            {
                // IMPOSSIBLE.
                return new int[0][0];
            }

            // Group is valid , so add to result.
            result[i][0] = nums[start];
            result[i][1] = nums[start + 1];
            result[i][2] = nums[start + 2];
        }

        return result;
    }
}
