public class Solution {
    public int FindFinalValue(int[] nums, int original) {
        
        // original = first number that needs to be searched for in nums.

        // (1) If original is found in nums, multiply it by 2.
        // (2) Otherwise, stop the process.
        // (3) Repeat with the new number, as long as you keep finding the number.

        while (nums.Contains(original))
        {
            original *= 2;
        }

        return original;
    }
}