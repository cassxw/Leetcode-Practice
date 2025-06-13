class Solution:
    def minimizeMax(self, nums: List[int], p: int) -> int:
        
        # Array (Binary Search, Greedy)

        # nums = integer array.
        n = len(nums)

        # p = integer.

        # Find p pairs of indices of nums, such that
        # the max. difference amongst all the pairs is minimised.
        # Also, ensure no index appears more than once amongst p pairs.

        # Note: For a pair of elements at th eindex i and j,
        #       the difference of this pair is abs(nums[i] - nums[j]).

        # Return the min. max. difference among all p pairs.
        # Define the max. of an empty set to be zero.

        # To minimise the answer, array should be sorted first.
        nums.sort()

        #------------------------------------------------------------
        # Dynamic Programming: MLE (1574/1582)
        # @lru_cache(maxsize=None)
        # def dp(i: int, x: int) -> int:
        #     if (x == 0):
        #         return 0
        #     if (i >= n-1):
        #         # Not enough elements to form a pair => IMPOSSIBLE.
        #         return float('inf')
        #     # Skipping this index.
        #     skip = dp(i+1, x)
        #     # Pair i and i+1.
        #     pair = max(abs(nums[i] - nums[i+1]), dp(i+2, x-1))
        #     return min(skip, pair)
        # return dp(0, p)
        #------------------------------------------------------------

        # Greedy Helper: Can we form at least p non-overlapping pairs
        # such that each pair's difference is <= max_diff?
        def can_form_pairs(max_diff: int) -> bool:
            i = 0
            count = 0
            while (i < n - 1):

                if (nums[i+1] - nums[i] <= max_diff):
                    # Pair these two and move past them.
                    count += 1
                    i += 2  
                else:
                    # Try pairing the next one.
                    i += 1
                
                if (count >= p):
                    return True

            return False

        # Binary Search: minimum possible max difference.
        left, right = 0, nums[-1]-nums[0]
        answer = right

        while (left <= right):
            mid = (left + right) // 2

            if can_form_pairs(mid):
                # Try to find a smaller valid max diff.
                answer = mid
                right = mid - 1
            else:
                left = mid + 1

        return answer
