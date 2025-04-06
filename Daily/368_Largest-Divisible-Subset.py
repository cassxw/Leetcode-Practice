class Solution:
    def largestDivisibleSubset(self, nums: List[int]) -> List[int]:

        # Array (Math, Dynamic Programming, Sorting)

        # nums = a list of distinct, positive ints.

        # Return the largest subset, answer,
        # such that every pair (answer[i], answer[j]) satisfies:
        # (1) answer[i] % answer[j] == 0, or
        # (2) answer[j] % answer[i] == 0
        
        # Note: There could be multiple valid solutions. Return any of them.
        n = len(nums)

        # Is nums always sorted? No
        # Ensure it is sorted to establish natural order: if a > b, then a % b != 0.
        # For any element in answer, only need to check divisibility with larger numbers.
        # i.e. reduces search space.
        nums.sort() #O(n.logn)

        #~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        # Immediately add nums[0], the lowest int <= factoring
        # ^ No, this is not always true. See [3,4,8,16]...
        #~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        # Dynamic Programming Approach:
        
        # dp[i] is the size of the largest divisible subset ending with nums[i].
        dp = [1] * n
        
        # prev[i] tracks the index of the previous number in the subset.
        prev = [-1] * n

        # For each number in nums, find the largest subset that it can be part of.
        for i in range(n): # O(n)
            for j in range(i): # O(n)
                # If nums[i] is divisible by nums[j], we can extend the subset.
                if (nums[i] % nums[j] == 0):
                    # If extending the current subset gives us a larger size, update dp and prev.
                    if (dp[j] + 1 > dp[i]):
                        dp[i] = dp[j] + 1
                        prev[i] = j
        # = O(n^2)

        # Find the index where we have the largest subset.
        max_size = max(dp)
        max_index = dp.index(max_size)

        # Reconstruct the maximum subset using prev array.
        answer = []
        while (max_index != -1):
            answer.append(nums[max_index])
            max_index = prev[max_index]

        # Reverse to get the correct order.
        return answer[::-1]
