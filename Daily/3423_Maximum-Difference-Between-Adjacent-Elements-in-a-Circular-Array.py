class Solution:
    def maxAdjacentDistance(self, nums: List[int]) -> int:

        # Array

        # nums = circular int array.

        # Find the maximum absolute difference between adj elements.
        # Note: Since circular, the first and last elements are adj.

        # Append the first element after the last.
        nums.append(nums[0])
        n = len(nums)

        max_diff = -1

        for i in range(n-1):
            max_diff = max(max_diff, abs(nums[i]-nums[i+1]))

        return max_diff