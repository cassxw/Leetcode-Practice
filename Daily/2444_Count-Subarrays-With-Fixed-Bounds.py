class Solution:
    def countSubarrays(self, nums: List[int], minK: int, maxK: int) -> int:
        
        # Array (Queue, Sliding Window, Monotonic Queue)

        # nums = integer array.
        # minK = integer.
        # maxK = integer.
        n = len(nums)

        # A "fixed-bound subarray" is one that satisfies the following:
        # (1) Its minimum value = minK.
        # (2) Its maximum value = maxK.

        # Return the number of "fixed-bound subarrays" in nums.

        #-----------------------------------------------
        # BRUTE-FORCE APPROACH => TLE (27/53)
        # Pure Sliding Window.
        # Suffers from a lot of repeated comparisons.

        # # Initialise counter for valid subarrays.
        # fixed_count = 0

        # # Start  left pointer at beginning of nums.
        # left = 0

        # # Loop through each possible starting point.
        # while (left < n):

        #     # Loop through each possible ending point.
        #     for right in range(left+1, n+1):

        #         # Initialise max & min value for current subarray.
        #         maxArr = 0
        #         minArr = 10e6 + 1

        #         # Iterate through the current subarray nums[left:right].
        #         for num in nums[left:right]:
                    
        #             # Update the max & min values found so far.
        #             maxArr = max(maxArr, num)
        #             minArr = min(minArr, num)

        #         # After scanning the subarray, check if it satisfies "fixed-bound".
        #         if (maxArr == maxK) and (minArr == minK):
        #             # nums[left:right] is a valid "fixed-point" subarray.
        #             fixed_count += 1

        #     # Move left pointer to next position..
        #     left += 1

        # # Return total count of "fixed-bound subarrays" found.
        # return fixed_count
        #-----------------------------------------------

        # OPTIMISED
        fixed_count = 0

        # Initialise pointers to track the last seen indices.
        last_minK = -1
        last_maxK = -1
        last_invalid = -1

        # Iterate through each number in nums.
        for (i, num) in enumerate(nums):

            # If num is invalid, i.e. outside [minK; maxK]...
            if (num < minK) or (num > maxK):
                # Reset the last_invalid pointer.
                last_invalid = i

            # Update last positions of minK and maxK.
            if (num == minK):
                last_minK = i
            elif (num == maxK):
                last_maxK = i

            # The number of "fixed-bound" subarrays ending at i,
            # depends on the earliest occurrence of minK and maxK.
            # However, need to cater for special case when minK == maxK.
            valid_start = 0
            if (minK != maxK):
                valid_start = min(last_minK, last_maxK)
            else:
                valid_start = last_minK
            
            # Only count if both minK and maxK have been seen after the last invalid.
            if (valid_start > last_invalid):
                fixed_count += valid_start - last_invalid

        # Return the total count of "fixed-bound subarrays".
        return fixed_count