public class Solution {
    public int FindSmallestInteger(int[] nums, int value) {
        
        // Edge case: If nums is empty, the smallest MEX is 0.
        if (nums == null || nums.Length == 0)
        {
            return 0;
        }

        // In one operation:
        // (1) Add / Subtract value from any element in nums.

        // MEX: Minimum Excluded
        // ...is the smallest non-negative integer that is not in the array.

        // Return the greatest MEX of nums,
        // after performing any number of operations.

        // Observation:
        // Adding/subtracting multiples of value keeps numbers in the same 'remainder % value' class.
        // We can rearrange numbers within 'remainder % value' classes to fill 0,1,2,... greedily.
        
        // Count how many numbers belong to each 'remainder % value' class, then for i from 0 up,
        // consume one from 'remainder % value' class (i % value) if available;
        // the first i that cannot be filled is the answer.


        int[] remainderCounts = new int[value];
        for (int i = 0; i < nums.Length; i++)
        {
            int r = nums[i] % value;
            if (r < 0)
            {
                r += value;
            }
            remainderCounts[r]++;
        }

        int mex = 0;

        // Repeat the operations any number of times.
        while (true) {
            int r = mex % value;
            if (remainderCounts[r] == 0)
            {
                return mex;
            }
            remainderCounts[r]--;
            mex++;
        }
    }
}