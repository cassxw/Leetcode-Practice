public class Solution {
    public int MaxSubArray(int[] nums) {

        // Array (Divide-and-Conquer, DP) => Kadane's Algorithm

        // nums = integer array
        int n = nums.Length;

        // Return the sum of the subarray with the largest sum.

        // EDGE CASE;
        if (n == 1) {
            return nums[0];
        }

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // BRUTE FORCE => TLE (200/210)
        // int maxSum = Int32.MinValue;
        // for (int l = 0; l < n; l++) {
        //     for (int r = l; r < n; r++) {
        //         int sum = 0;
        //         for (int k = l; k <= r; k++) {
        //             sum += nums[k];
        //         }
        //         if (sum > maxSum) {
        //             maxSum = sum;
        //         }
        //     }
        // }
        // return maxSum;
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        // Using Kadane's Algorithm
        int maxSoFar = nums[0];
        int maxEndingHere = nums[0];

        // Iterate through the array.
        for (int i = 1; i < n; i++) { // O(n)
            // At each index, decide whether to...
            //      - include previous subarray sum.
            //      - start fresh from current index.

            // The maximum between current number, and maxEndingHere + current number.
            maxEndingHere = Math.Max(nums[i], maxEndingHere + nums[i]);

            // The maximum between maxSoFar and maxEndingHere.
            maxSoFar = Math.Max(maxSoFar, maxEndingHere);
        }

        return maxSoFar;
    }
}