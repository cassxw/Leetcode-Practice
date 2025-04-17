class Solution {
    public int countPairs(int[] nums, int k) {
        
        // Array

        // nums = integer array
        // k = integer
        int n = nums.length;

        // Return the number of pairs (i, j)
        // where 0 <= i < j < n, i.e. increasing indices
        // such that:
        //      (1) nums[i] == nums[j]
        //      (2) (i * j) % k == 0

        // Current approach: O(n²) using two pointers
        // - Yes, there exists an O(n) solution using HashMaps and math tricks.
        // - But sometimes readability > optimization when it's not performance-critical
        // - Straightforward and easier to understand... :)

        // Two pointers:
        // - left = i
        // - right = j

        int count = 0;

        // First pointer (i).
        int left = 0;

        while (left < n) { // O(n)

            // Second pointer (j), starts after left to avoid counting same pair twice.
            for (int right = left+1; right < n; right++) { // O(n)

                // Check divisibility first as it is cheaper than array access.
                if ((left * right) % k == 0) {

                    // Then, check if we have a pair.
                    if (nums[left] == nums[right]) {
                        count++;
                    }

                }
            }

            left++;
        } // = O(n^2)

        return count;
    }
}