public class Solution {
    public int MaxIncreasingSubarrays(IList<int> nums) {

        // There exist 2 adjacent subarrays of length k that are strictly increasing.
        // Determine the maximum value of k.
        // For a boundary between i and i+1, the largest feasible k is
        // min(incEnd[i], incStart[i+1]). Taking the maximum over all boundaries gives
        // the answer. This runs in O(n).

        int n = nums.Count;
        if (n == 0)
        {
            return 0;
        }

        // incEnd[i]: length of the strictly increasing run ending at i.
        int[] incEnd = new int[n];

        // incStart[i]: length of the strictly increasing run starting at i.
        int[] incStart = new int[n];

        // For a boundary [i;i+1], the max k is min(incEnd[i], incStart[i+1]).
        // Taking the maximum over all boundaries gives the answer.

        incEnd[0] = 1;
        for (int i = 1; i < n; i++)
        {
            incEnd[i] = (nums[i - 1] < nums[i]) ? incEnd[i - 1] + 1 : 1;
        }

        incStart[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--)
        {
            incStart[i] = (nums[i] < nums[i + 1]) ? incStart[i + 1] + 1 : 1;
        }

        int maxK = 0;
        for (int i = 0; i < n - 1; i++)
        {
            int candidate = Math.Min(incEnd[i], incStart[i + 1]);

            if (candidate > maxK)
            {
                maxK = candidate;
            }
        }

        return maxK;
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