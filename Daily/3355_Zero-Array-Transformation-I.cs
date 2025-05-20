public class Solution {
    public bool IsZeroArray(int[] nums, int[][] queries) {
        
        // Array (Prefix Sum)

        // nums = integer array.
        int n = nums.Length;

        // queries = 2D array, where
        //           queries[i] = [li, ri].

        // For each queries[i]:
        // (1) Select a subset of indices within the range of [li, ri] in nums.
        // (2) Decrement the values at the selected indiced by 1.

        // "Zero Array" is an array where all elements are equal to 0.

        // Return true if it is possible to transform nums into a "Zero Array",
        // after processing all the queries sequentially. Otherwise, return false.

        // Create a difference array to track how many times each index can be decremented.
        int[] diff = new int[n+1];

        // Iterate through every query, building a range coverage map.
        foreach (int[] query in queries)
        {
            // For each query [l, r], we can potentially decrement each index in the range once.
            int l = query[0];
            int r = query[1];

            // Increase start of range.
            diff[l]++;

            // Decrease just after end of range, if within bounds.
            if (r + 1 <= n - 1)
            {
                diff[r + 1]--;
            }
        }

        // Build actual decrement availability using prefix sum.
        // coverage[i] = how many times index i  can be decremented
        //               from the combined power of all queries.
        int[] coverage = new int[n];
        int current = 0;

        for (int i = 0; i < n; i++)
        {
            current += diff[i];
            coverage[i] = current;
        }

        // Check if every element got enough decrements,
        // i.e. each nums[i] must be <= number of times we can decrement it.
        for (int i = 0; i < n; i++)
        {
            if (coverage[i] < nums[i])
            {
                // Not enough queries to bring nums[i] down to 0 => IMPOSSIBLE.
                return false;
            }
        }

        // Reaching here, it is possible to reduce all elements to zero.
        return true;
    }
}
