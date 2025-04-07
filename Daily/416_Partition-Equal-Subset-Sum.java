class Solution {
    public boolean canPartition(int[] nums) {
        
        // Array (Dynamic Programming)

        // nums = integer array
        int n  = nums.length;

        // Return true if you can partition the array into two "subsets",
        // such that the sum of the elements in both is equal.
        // Otherwise, return false.

        // Key Observation: Similar to Knapsack => DP.

        // EDGE CASE: If the total sum of nums is odd, then it would be impossible.
        int totalSum = 0;
        for (int num : nums) { // O(n)
            totalSum += num;
        }

        if (totalSum % 2 != 0) {
            return false;
        }

        // Reaching here, we know that it is possible.

        int targetSum = totalSum / 2;

        // dp[i] will be true if a subset summing to i can be found.
        boolean[] dp = new boolean[targetSum + 1];

        // BASE CASE: Summing to "0" will always be possible, i.e. empty.
        dp[0] = true;

        // Iterate through each num in nums.
        for (int num : nums) { // O(n)

            // Process backwards from targetSum to current num.
            for (int i = targetSum; i >= num; i--) { // O(targetSum)

                // If (i - num) was achievable, then i is now achievable by including the current num.
                // If i was already true, then unchanged.
                dp[i] = dp[i] || dp[i - num];
            }
        } 
        
        // If we have found a way that dp[targetSum] = True, then it is possible.
        // i.e. We found a way to sum to targetSum, which was half of the total sum.
        if (dp[targetSum]) {
            return true;
        }

        return false;

        // Time Complexity: O(n*targetSum)
    }
}