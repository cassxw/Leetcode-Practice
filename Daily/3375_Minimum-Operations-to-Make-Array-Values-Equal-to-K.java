class Solution {
    public int minOperations(int[] nums, int k) {
        
        // Array (Hash Table)

        // nums = integer array.
        int n = nums.length;

        // k = integer.

        // The following operations can be performed on nums:
        //  (1) Select an int h that is "valid" for the current values in nums.
        //  (2) For each index i where nums[i] > h, set nums[i] = h.

        // An int, h, is "valid" if all values in the array
        // that are strictly greater than h are identical.

        // Return the minimum number of operations required to make every element = k.
        // If impossible, return -1.

        // Key Observations:
        // - If there is any number that is less than k, it is impossible.
        // - The output matches the number of distinct numbers that are greater than k.

        // Create a HashSet to store distinct numbers greater than k.
        HashSet<Integer> hash = new HashSet<>();

        // Iterate through each number in nums.
        for (int num : nums) { // O(n)

            // Check if current number is less than k.
            if (num < k) {
                // If any number is less than k, return -1 => IMPOSSIBLE.
                return -1;

            // Check if current number is not equal to k.
            } else if (num != k) {
                // Add the number to the HashSet to track distinct values.
                hash.add(num);
            }

        }

        // Return the size of the HashSet, which represents the number of distinct numbers greater than k.
        return hash.size();
    }
}