class Solution:
    def partitionArray(self, nums: List[int], k: int) -> int:

        # Array (Greedy, Sorting)

        # k = int.
        # nums = int array.

        # A subsequence is a sequence that can be derived from another sequence
        # by deleting some or no elements without changing the order of the
        # remaining elements.

        # Partition nums into one or more subsequences such that,
        # each element in nums appears in exactly one of the subsequences.

        # Return the number of subsequences needed such that the diff
        # between the max and min values in each subsequence is at most k.
        
        # Observation: The only values that matter are max and min.

        # Optimal would be to place all values inbetween max and min
        # in the original array in the same subsequence as max and min.

        nums.sort()

        # Tracks no. of valid subsequences.
        count = 0

        # Greedily group elements from left to right.
        i = 0
        while (i < len(nums)):
            
            # Smallest element in group.
            start = nums[i]
            
            # Add elements to current group while the max - min <= k.
            while (i < len(nums)) and (nums[i] - start <= k):
                i += 1

            # Once condition breaks, one valid group has been formed.
            count += 1
            
        return count
