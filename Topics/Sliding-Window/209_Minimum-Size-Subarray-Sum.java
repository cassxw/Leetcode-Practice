class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        
        // Array (Binary Search, Sliding Window, Prefix Sum)

        // nums = positive, integer array.
        // target = positive integer.
        int n = nums.length;

        // Return the minimal length of a subarray whose sum is greater than or equal to target.
        // If there is no such subarray, return 0.

        // Use Binary Search to find length of subarray.
        // Search Space of [1, n]
        // Start with n/2, so half the size of nums.
        int low = 1;
        int high = n;
        
        int minLength = 0;

        while (low <= high) {
            int length = low + (high - low) / 2;
            
            // Check if there exists a subarray of current length, with sum >= target.
            if (checkWindow(nums, length, target)) {
                // We found one! Let's try to find a smaller one, by moving to the lower length search space.
                minLength = length;
                high = length - 1;
            } else {
                // We didn't find one! Let's try increase the length, so move to the higher lengths search space.
                low = length + 1;
            }
        }

        return minLength;
    }

    private boolean checkWindow(int[] nums, int length, int target) {
        int n = nums.length;

        // Calculate initial window sum.
        int sum = 0;

        for (int i = 0; i < length; i++) {
            sum += nums[i];
        }
        
        if (sum >= target) return true;

        // Slide the window by one.
        for (int i = length; i < n; i++) {
            sum = sum - nums[i-length] + nums[i];
            if (sum >= target) return true;
        }

        return false;
    }
}