class Solution {
    public long countFairPairs(int[] nums, int lower, int upper) {

        // Array (Two Pointers, Binary Search, Sorting)

        // nums = integer array of size n
        // lower = integer
        // upper = integer
        int n = nums.length;

        // A "fair pair" (i, j) is one such that:
        //      (1) 0 <= i < j < n
        //      (2) lower <= nums[i] + nums[j] <= upper

        // Return the number of "fair pairs" in nums.

        // We need to search the space for values, x, in the space of [lower, upper]
        // such that we can (i,j) such that x = nums[i] + nums[j].

        //--------------------------------------------------------
        // (BAD) BRUTE FORCE: TLE (4/54)
        // int fairPairCount = 0;
        // for (int x = lower; x <= upper; x++) {
        //     // Iterate through nums to find x
        //     for (int i = 0; i < n-1; i++) {
        //         for (int j = i+1; j < n; j++) {
        //             if (nums[i] + nums[j] == x) {
        //                 System.out.println(i + " " + j);
        //                 fairPairCount++;
        //             }
        //         }
        //     }
        // }
        // return fairPairCount;
        //--------------------------------------------------------
        // BETTER, but still TLE (47/54)
        // // Track the number of "fair pairs" found.
        // int fairPairCount = 0;
        // // Sort the array <= fine, because order of actual (i,j) does not matter => commutivity.
        // Arrays.sort(nums);
        // int i = 0;
        // while (i < n-1) {
        //     for (int j = n-1; j > i; j--) {
        //         int sum = nums[i] + nums[j];
        //         if (lower <= sum && sum <= upper) {
        //             fairPairCount++;
        //         }
        //     }
        //     i++;
        // }
        // return fairPairCount;
        //--------------------------------------------------------

        // Sort the array.
        // Allowed because the order of i and j doesn’t matter as long as i < j.
        Arrays.sort(nums);

        // We want the number of pairs such that lower <= nums[i] + nums[j] <= upper.
        // Let sum = nums[i] + nums[j].
        // search(nums, upper+1) counts how many pairs have sum < upper+1 (i.e. sum <= upper).
        // search(nums, lower) counts how many pairs have sum < lower.
        // So, their difference gives us how many pairs fall into the inclusive range [lower, upper].
        return search(nums, upper + 1) - search(nums, lower);
    }

    // Helper function to count number of (i, j) pairs such that nums[i] + nums[j] < value.
    private long search(int[] nums, int value) {
        int i = 0;
        int j = nums.length - 1;
        long count = 0;

        // Use two pointers to efficiently count pairs with sum < value.
        while (i < j) {
            int sum = nums[i] + nums[j];

            if (sum < value) {
                // If nums[i] + nums[j] < value, then all pairs (i, i+1)...(i, j-1) are also valid.
                count += (j - i);
                i++;
            } else {
                // Otherwise, decrease the sum by moving the right pointer.
                j--;
            }
        }

        return count;
    }
}