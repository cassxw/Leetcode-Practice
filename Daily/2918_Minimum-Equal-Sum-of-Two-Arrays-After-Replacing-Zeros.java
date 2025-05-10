class Solution {
    public long minSum(int[] nums1, int[] nums2) {
        
        // Array (Greedy)

        // nums1, nums2 = positive integer ararys.
        int n1Len = nums1.length;
        int n2Len = nums2.length;

        // Replace all the 0's in both with strictly positive ints
        // such that the sum of elements of both becomes equal.

        // Return the minimum equal sum you can obtain.
        // If impossible, return -1.

        // Array sums can only be increased if there are 0's present.
        // Determine how many 0's both have, and calculate original sums.
        int zeroCount1 = 0;
        long sum1 = 0;
        for (int num : nums1) {
            sum1 += num;
            if (num == 0) {
                zeroCount1++;
            }
        }

        int zeroCount2 = 0;
        long sum2 = 0;
        for (int num : nums2) {
            sum2 += num;
            if (num == 0) {
                zeroCount2++;
            }
        }

        // Calculate min possible sum each array
        // can achieve by replacing zeros with 1s.
        long minPossibleSum1 = sum1 + zeroCount1;
        long minPossibleSum2 = sum2 + zeroCount2;

        // === EDGE CASE 1 ===
        // If both arrays have no zeros...
        if (zeroCount1 == 0 && zeroCount2 == 0) {
            // If sum1 already equals sum2...
            if (sum1 == sum2) {
                // Return it.
                return sum1;
            } else {
                // Since no zeroes, cannot modify anything => IMPOSSIBLE.
                return -1;
            }
        }

        // === EDGE CASE 2 ===
        // If nums1 has no zeros, but nums2 does
        if (zeroCount1 == 0) {

            // If nums1's sum is less than minPossibleSum2, and num2 has zeroes...
            if (sum1 < minPossibleSum2 && zeroCount2 > 0) {
                return -1;
            }

            // If nums2 has zeroes, it must be able to sum up to sum1.
            // Since each zero in nums2 is at least 1, sum2 + zeroCount2 is its min sum.
            // For nums2 to reach sum1, sum1 must be >= sum2 + zeroCount2.
            if (sum1 >= minPossibleSum2) {
                return sum1;
            } else {
                return -1;
            }
        }

        // === EDGE CASE 3 ===
        // If nums2 has no zeros, but nums1 does
        if (zeroCount2 == 0) {

            // If nums2's sum is less than minPossibleSum1, and num1 has zeroes...
            if (sum2 < minPossibleSum1 && zeroCount1 > 0) {
                return -1;
            } 

            // If nums1 has zeroes, it must be able to sum up to sum2.
            // Since each zero in nums1 is at least 1, sum1 + zeroCount1 is its min sum.
            // For nums1 to reach sum2, sum2 must be >= sum1 + zeroCount1.
            if (sum2 >= minPossibleSum1) {
                return sum2;
            } else {
                return -1;
            }
        }

        // === GENERAL CASE ===
        // Both arrays have at least one zero; we can increase both sums.
        // The minimum equal sum we can reach must be at least:
        // - sum1 + zeroCount1 (all zeros in nums1 as 1)
        // - sum2 + zeroCount2 (all zeros in nums2 as 1)
        // So, return the max of those two.
        return Math.max(minPossibleSum1, minPossibleSum2);
    }
}