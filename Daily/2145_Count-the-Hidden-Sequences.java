class Solution {
    public int numberOfArrays(int[] differences, int lower, int upper) {
        
        // Array (Prefix Sum)

        // differences = integer array,
        //               describes the difference between each pair of consecutive ints
        //               of a hidden sequence of length (n+1).
        //               e.g. differences[i] = hidden[i+1] - hidden[i]
        //                    hidden[i+1] = hidden[i] + differences[i]

        // lower, upper = integers,
        //                describes the range, [lower; upper] that hidden contains.

        // Return the number of possible hidden sequences there are.
        // If none, return 0.

        // Edge Case: Optional Early Exit - prunes impossibe cases.
        // If any value in differences is larger than abs(upper-lower),
        // then there would be no possible hidden sequences.
        // Note: Technically redundant because Prefix Sum below will handle this.
        //       But, might offer a minor optimisation in some test cases by exiting early.
        int maxRangeWidth = upper - lower;
        for (int diff : differences) {
            if (Math.abs(diff) > maxRangeWidth) {
                return 0;
            }
        }

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // BRUTE FORCE APPROACH => TLE (70/86)
        // - Test every possible starting value for hidden[0] within [lower, upper].
        // - For each, construct hidden sequence and check if all elements stay within bounds.

        // // Counter for valid hidden sequences.
        // int count = 0;

        // // Iterate through all possible starting values for hidden[0].
        // for (int startValue = lower; startValue <= upper; startValue++) {
        //     int[] hidden = new int[n + 1];
        //     hidden[0] = startValue;
        //     boolean isValidSequence = true; // Flag to track if the current sequence is valid.

        //     // Construct the rest of the hidden sequence using the differences.
        //     for (int i = 0; i < n; i++) {
        //         // Calculate the next element: hidden[i+1] = hidden[i] + differences[i]
        //         hidden[i + 1] = hidden[i] + differences[i];

        //         // Check if the newly calculated element is within the allowed bounds.
        //         if (hidden[i + 1] < lower || hidden[i + 1] > upper) {
        //             isValidSequence = false; // Mark sequence as invalid.
        //             break; // No need to check further for this startValue.
        //         }
        //     }

        //     // If the entire sequence remained within bounds, increment the count.
        //     if (isValidSequence) {
        //         count++;
        //     }
        // }

        // return count;
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        // OPTIMISED APPROACH: Prefix Sum
        // My brute force approach has a bottleneck with recalculating sequences repeatedly.
        // Instead of generating sequences, we determine the valid range for the starting
        // element hidden[0] based on the differences and the bounds [lower, upper].

        // Key Insight:
        // Want to instead leverage the bounds of hidden[0].
        // i.e. hidden[k] = hidden[0] + (differences[0] + ... + differences[k-1])
        // => Classic Prefix Sum

        int currentPrefixSum = 0;   // Represents current (hidden[k] - hidden[0]).
                                    // Set to 0, i.e. relative diff of hidden[k=0] from hidden[0] is 0.

        int minPrefixSum = 0;       // Tracks minimum found prefix sum.
        int maxPrefixSum = 0;       // Tracks maximum found prefix sum.

        // Calculate the minimum and maximum prefix sums in one pass.
        for (int diff : differences) { // O(n)

            // Calculate the current prefix sum, i.e. P[k+1] = P[k] + differences[k].
            currentPrefixSum += diff;

            // Update min and max.
            minPrefixSum = Math.min(minPrefixSum, currentPrefixSum);
            maxPrefixSum = Math.max(maxPrefixSum, currentPrefixSum);
        }

        // Now, determine valid range for hidden[0].

        // Since: hidden[k] = hidden[0] + PrefixSum[k]
        // and... lower <= hidden[i] <= upper

        // lower <= hidden[0] + PrefixSum[k] <= upper
        // lower - PrefixSum[k] <= hidden[0] <= upper - PrefixSum[k]

        // To satisfy this for all k, hidden[0] must be within the intersection:
        // hidden[0] >= max(lower - P[k]) = lower - min(P[k]) = lower - minPrefixSum
        // hidden[0] <= min(upper - P[k]) = upper - max(P[k]) = upper - maxPrefixSum
        
        // Calculate the lower and upper bounds for the starting value hidden[0].
        int validStartMin = lower - minPrefixSum;
        int validStartMax = upper - maxPrefixSum;

        // If validStartMax < validStartMin, the range is invalid (no possible start values).
        if (validStartMax < validStartMin) {
            return 0;
        }

        // The number of possible valid starting values for hidden[0] is the size of this range.
        return validStartMax - validStartMin + 1;
    }
}