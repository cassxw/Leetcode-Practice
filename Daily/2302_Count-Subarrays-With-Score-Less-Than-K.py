class Solution:
    def countSubarrays(self, nums: List[int], k: int) -> int:
        
        # Array (Binary Search, Sliding Window, Prefix Sum)

        # nums = positive integer array.
        # k = integer.
        n = len(nums)

        # "score" of an array = sum * length.

        # Return the number of subarrays of nums,
        # whose score < k.

        # Constraints are too large to check every subarray.
        # But, to start from first principles:

        #-----------------------------------------------------------------
        # BRUTE FORCE => TLE (150/167)
        # count = 0
        # for left in range(n): # O(n)
        #     for right in range(left+1, n+1): # O(n)
        #         # Considering subarray nums[left:right]
        #         # sum(nums[left:right]) => sums elements from left up to (but not including) right.
        #         # (right - left) => gives the length of the subarray.
        #         if ((sum(nums[left:right]) * (right-left)) < k):
        #             # Valid subarray found.
        #             count += 1
        # return count
        #------------------------------------------------------------------

        # TWO POINTERS & SLIDING WINDOW

        # Track number of valid subarrays.
        count = 0

        left = 0    # Points to left side of current window.
        sum = 0     # Running sum for current window.

        # Expand the window by moving the right pointer.
        for right in range(n):
            
            # Include current element in the running sum.
            sum += nums[right]

            # If current window's score is too large (>= k),
            # shrink it from the left until it becomes valid.
            while (left <= right) and (sum * (right - left + 1) >= k):
                sum -= nums[left]
                left += 1

            # Now, all subarrays ending at right & starting from [left:right].
            # So, (right - left + 1) new subarrays are added.
            count += (right - left + 1)

        return count