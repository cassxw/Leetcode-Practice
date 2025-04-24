class Solution:
    def countCompleteSubarrays(self, nums: List[int]) -> int:

        # Array (Hash Table, Sliding Window)

        # nums = positive integer array.
        n = len(nums)

        # A "complete subarray" is one where:
        #   - no. of distinct elements in subarray = no. of distinct elements in whole array.
        #   - Contiguous, non-empty.

        # Return the number of "complete subarrays" in nums.

        # First, Determine how many unique elements exist in nums.
        totalDistinct = len(set(nums))

        # We know that every complete subarray must have a minimum of totalDistinct elements.
        # So, the minimum size of our sliding window must be totalDistinct.
        # And, the max would be the length of nums, i.e. nums itself.

        # Counter to track the number of "complete subarrays" found.
        count = 0

        # Iterate with a sliding window starting from each index,
        # using a left pointer to track this index.
        left = 0

        while (left <= n - totalDistinct):

            # For every starting index, expand the window to the right until
            # either the end of the array is reached, or all distinct elements found.

            # Stores unique elements in the current window.
            subarray_set = set()

            # Expand the window to the right, starting from the left pointer.
            for right in range(left, n):
                # Add current element to the set.
                subarray_set.add(nums[right])

                # While the small constraints of this question allow for brute force,
                # the below approach was a little too costly:
                # => if len(set(nums[left:right+1])) == totalDistinct:

                # If we have collected all unique elements...
                if len(subarray_set) == totalDistinct:
                    # A "complete subarray" has been found
                    count += 1

                    # We continue expanding to count all larger subarrays
                    # that still contain all unique elements.

            # Move sliding window start forward by one.
            left += 1

        # Return the total number of "complete subarrays" found.
        return count
