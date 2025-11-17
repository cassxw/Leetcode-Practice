public class Solution {
    public bool KLengthApart(int[] nums, int k) {
        
        // nums is a binary array.

        // If all 1's in nums are at least k places away from each other, return true.
        // Otherwise, return false.

        // Sliding Window, with Two Pointers.

        int left = -1;  // Initialise L to -1 to indicate no 1 found yet.
        int right = 0;

        while (right < nums.Length)
        {
            if (nums[right] == 1)
            {
                // Only check distance if we've found a previous 1.
                if (left != -1 && right - left - 1 < k)
                {
                    return false;
                }

                left = right;
            }
            
            right++;
        }

        // Here, all 1's are at least k places away from each other.
        return true;
    }
}