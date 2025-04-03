class Solution {
    public long maximumTripletValue(int[] nums) {

        // Array

        // nums = integer array.
        int n = nums.length;

        // Return the maximum value over all triplets of indices (i, j, k),
        // such that i < j < k.

        // "value" of a triplet, (i, j, k) = (nums[i] - nums[j]) * nums[k]

        // left = i
        // mid = j
        // right = k

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Keep only left fixed - O(n^2) = TLE
        // long maxValue = 0;
        // long maxLeft = nums[0];
        
        // for (int mid = 1; mid < n-1; mid++) {
        //     for (int right = mid+1; right < n; right++) {
        //         maxValue = Math.max(maxValue, (maxLeft - nums[mid]) * nums[right]);
        //         maxLeft = Math.max(maxLeft, nums[mid]);
        //     }
        // }

        // return maxValue;
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        // Keep left and (left - nums[j]), aka. expr, fixed - O(n)
        long maxValue = 0;
        long maxLeft = 0;
        long maxExpr = 0;

        for (int right = 0; right < n; right++) {
            maxValue = Math.max(maxValue, maxExpr * nums[right]);
            maxExpr = Math.max(maxExpr, maxLeft - nums[right]);
            maxLeft = Math.max(maxLeft, nums[right]);
        }

        return maxValue;

    }
}