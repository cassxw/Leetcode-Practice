public class Solution {
    public int RemoveElement(int[] nums, int val) {
        
        // Array (Two Pointers)

        // nums = integer array.
        // val = integer.
        int n = nums.Length;

        // Remove all occurences of val in nums in-place.
        // - Order may be changed.
        
        // Return the number of elements in nums
        // which are not equal to val.

        // - Change nums, such that the first k elements
        //   contain the elements which are not equal to val.
        // - The remaining are important, as well as size of nums.
        // - Return k.

        // Track how many elements are NOT equal to val.
        int count = 0;

        // Iterate through array.
        for (int i = 0; i < n; i++) {

            // If current element is not val to be removed...
            if (nums[i] != val) {
                // Place it at the count index.
                // i.e. shift the good ones forward.
                nums[count] = nums[i];

                // Move count forward.
                count++;
            }
        }

        // Return how many elements are NOT equal to val.
        return count;
    }
}