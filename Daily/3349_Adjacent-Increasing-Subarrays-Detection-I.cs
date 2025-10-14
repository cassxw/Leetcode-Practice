public class Solution {

    // Determine whether there exists 2 adjacent subarrays of length k,
    // such that both are strictly increasing.
    public bool HasIncreasingSubarrays(IList<int> nums, int k) {

        int n = nums.Count;

        // Check for 2 adjacent subarrays of length k.
        for (int i = 0; i <= n - 2 * k; i++)
        {
            // First subarray: nums[i:i+k-1]
            var firstSubarray = nums.Skip(i).Take(k).ToList();
            // Second subarray: nums[i+k:i+2k-1]  
            var secondSubarray = nums.Skip(i + k).Take(k).ToList();
            
            if (IsStrictlyIncreasing(firstSubarray) && IsStrictlyIncreasing(secondSubarray))
            {
                return true;
            }
        }

        return false;
    }

    private bool IsStrictlyIncreasing(IList<int> array)
    {
        for (int i = 0; i < array.Count - 1; i++)
        {
            if (array[i] >= array[i + 1])
            {
                return false;
            }
        }
        return true;
    }
}