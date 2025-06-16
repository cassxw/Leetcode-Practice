class Solution {
    public int maximumDifference(int[] nums) {
        
        // Array

        // nums = interger array.
        int n = nums.length;

        // Find the maximum difference between nums[i] and nums[i],
        // such that 0 <= i < j < n and nums[i] < nums[j].

        // Return the max difference.
        // If no such i and j exists, return -1.

        int maxDiff = -1;
        int prevMin = nums[0];

        for (int i = 1; i < n; i++) {
            if (nums[i] > prevMin) {
                maxDiff = Math.max(maxDiff, nums[i] - prevMin);
            }
            else {
                prevMin = nums[i];
            }
        }        

        return maxDiff;
    }
}
