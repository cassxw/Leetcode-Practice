class Solution:
    def maximumTripletValue(self, nums: List[int]) -> int:

        # Array

        # nums = integer array.
        n = len(nums)

        # Return the maximum value over all triplets of indices (i, j, k),
        # such that i < j < k.

        # "value" of a triplet, (i, j, k) = (nums[i] - nums[j]) * nums[k]

        #~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        # BRUTE: O(n^3)
        # maxValue = 0

        # for left in range(n):            
        #     for right in range(n-1, 1, -1):
        #         for mid in range(left+1, right):
        #             maxValue = max(maxValue, (nums[left] - nums[mid]) * nums[right])

        # return maxValue
        #~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        # GREEDY: O(n^2)
        # maxLeft: Keeps left fixed, updating when a used mid is larger than current.
        # maxValue = 0
        
        # for right in range(2, n):
        #     maxLeft = nums[0]
        #     for mid in range(1, right):
        #         maxValue = max(maxValue, (maxLeft - nums[mid]) * nums[right])
        #         maxLeft = max(maxLeft, nums[mid])

        # return maxValue
        #~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        # GREEDY: O(n)
        maxValue = 0
        maxLeft = 0
        maxExpr = 0 # nums[i] - nums[j]

        for right in range(n):
            maxValue = max(maxValue, maxExpr * nums[right])
            maxExpr = max(maxExpr, maxLeft - nums[right])
            maxLeft = max(maxLeft, nums[right])

        return maxValue
