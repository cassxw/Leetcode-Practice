public class Solution {

    public int ClimbStairs(int n) {
        
        // Math (1D Dynamic Programming, Memoization)

        // n = integer.

        // You are climbing a staircase.
        // It takes n steps to reach the top.

        // Each time, you can either climb 1 or 2 steps.

        // Return the number of distinct ways that you can climb to the top.

        // Bottom-Up DP Approach
        // - Classic overlapping problem; think Fibonacci.
        // - The no. of ways to reach step i depends on both:
        //      (1) the no. of ways to reach step i-1 (by taking one step).
        //      (2) the no. of ways to reach step i-2 (by taking two steps).

        // dp[i] represents the number of distinct ways to climb i stairs.
        // dp[0] = 1
        // dp[1] = 1
        // dp[2] = dp[1] + dp[0] = 1 + 1 = 2
        // dp[3] = dp[2] + dp[1] = 2 + 1 = 3
        // dp[4] = dp[3] + dp[2] = 3 + 2 = 5
        // dp[i] = dp[i-1] + dp[i-2]

        // Base Case:
        if (n == 1) {
            // Only one way to reach the first step.
            return 1;
        }

        // Create a DP array (or table) to store the results for each step.
        // Size n+1 because we need indices from 0 to n.
        int[] dp = new int[n+1];

        // Initialise the base cases in the DP array.
        dp[0] = 1; // Necessary for DP relation to work.
        dp[1] = 1; // Only one way to reach the first step.

        // Iterate from step 2 up to the target step n.
        for (int i = 2; i <= n; i++) {
            // Apply the recurrence relation.
            dp[i] = dp[i-1] + dp[i-2];
        }

        // The final answer is stored in dp[n], which represents the
        // total number of distinct ways to reach the step n.
        return dp[n];
    }
}