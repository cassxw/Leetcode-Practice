from collections import defaultdict

class Solution:
    def countGood(self, nums: List[int], k: int) -> int:
        
        # Array (Hash Table, Sliding Window)

        # nums = integer array
        # k = integer
        n = len(nums)

        # A "good" subarray is one that
        # has at least k pairs of indices, (i, j)
        # such that:
        #   - i < j
        #   - arr[i] == arr[j]

        # Return the number of "good" subarrays of nums.

        # Dictionary to keep track of frequency of each number in current window.
        freq_dict = defaultdict(int)

        # Track total count of good subarrays and current pairs in current window.
        good_count = 0

        # Number of pairs (i,j), where nums[i] == nums[j], in current window.
        pair_count = 0
        
        # Left pointer of the sliding window.
        left = 0

        # Expand window by moving right pointer.
        for right in range(n): # O(n)

            # Now, working with subarray [left, right].
            
            # When adding nums[right], it forms pairs with all previous occurrences.
            # e.g. If a number appeared 3 times before, adding it again forms 3 new pairs.
            pair_count += freq_dict[nums[right]]
            freq_dict[nums[right]] = freq_dict.get(nums[right], 0) + 1
            
            # If we have enough pairs, this window and all its extensions are "good".
            while pair_count >= k:

                # All subarrays [left:r] where r∈[right:n] are "good".
                # So, add (n - right) to our "good" count.
                good_count += n - right

                # Shrink sliding window from left until we have less than k pairs.
                # Remove pairs that involved nums[left].
                freq_dict[nums[left]] -= 1
                pair_count -= freq_dict[nums[left]]
                left += 1
            
        return good_count
