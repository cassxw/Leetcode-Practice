class Solution {
    public long countSubarrays(int[] nums, int k) {

        // Array (Sliding Window)

        // nums = integer array.
        // k = positive integer.
        int n = nums.length;

        // Return the number of subarrays of nums,
        // where the max element of nums appears
        // at least k times in that subarray.

        // Determine the max element in nums.
        int maxNum = -1;
        for (int num : nums) {
            maxNum = Math.max(maxNum, num);
        }

        // Track the numnber of subarrays that satisfy.
        long count = 0;

        // Points to the left side of the sliding window.
        int left = 0;

        // Running count of the current subarray's number of instances of maxNum.
        int maxCount = 0;

        // Iterate through nums, using fixed left.
        for (int right = 0; right < n; right++) {

            // If the current element is maxNum..
            if (nums[right] == maxNum) {
                // Increase its count in current window.
                maxCount++;
            }

            // When current window has at least k occurrences of maxNum,
            // shrink window from the left while counting valid subarrays.
            while (maxCount >= k) {

                // All subarrays starting at current left and ending at any index >= right
                // are valid, because they will all contain at least k instances of maxNum.
                count += (n - right);
                
                if (nums[left] == maxNum) {
                    // Update maxCount for new window.
                    maxCount--;
                }

                // Shrink the sliding window.
                left++;
            }
        }

        // Return the total count of valid subarrays.
        return count;
    }
}