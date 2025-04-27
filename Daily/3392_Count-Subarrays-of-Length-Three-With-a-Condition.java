class Solution {
    public int countSubarrays(int[] nums) {
        
        // Array

        // nums = integer array.
        int n = nums.length;

        // Return the number of subarrays of length 3,
        // such that the sum of the first & third number
        // equals exactly half of the second number.
        // i.e.
        //  - (i, j, k)
        //  - 0 <= i < j < k <= n
        //  - nums[i] + nums[k] = nums[j] / 2

        // Due to small constraints, brute-force is feasible!

        // Track number of valid subarrays found.
        int count = 0;

        // Always looking for a group of 3.
        // So, loop through nums until the third-last element.
        for (int i = 0; i <= n-3; i++) {

            // Working with subarray nums[i:i+2].

            // Calculate sum of the first and third elements.
            int sum = nums[i] + nums[i+2];

            // If twice this sum equals the middle element...
            if (sum * 2 == nums[i+1]) {
                // Valid subarray found.
                count++;
            }
        }

        // Return the number of valid subarrays found.
        return count;
    }
}