class Solution(object):
    def minimumIndex(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """

        # Array (or Hash Table), Sorting

        # nums = integer array, with one "dominant element", and length n.
        n = len(nums)

        # "dominant element": An element x in an integer array arr,
        #                     is dominant if more than half of the elements of arr
        #                     have a value of x.

        # Split nums at an index i into two arrays:
        # (1) nums[0, i]
        # (2) nums[i+1, n-1]
        # ...such that both (1) and (2) have the same dominant element.

        # Return the minimum index of a valid split.
        # If no valid split exists, return -1.

        # Determine nums' dominant element.
        # Smart => convert to dictionary, highest key is the dominant element
        freq = {}
        for num in nums: # O(n)
            freq[num] = freq.get(num, 0) + 1
        
        # Get the key with the maximum value in freq.
        dominant_element = max(freq, key=freq.get)

        # Useful to get the number of times dominant element is in nums?
        # We know that it is at least >1/n times.
        total_count = freq[dominant_element]

        # Inclinition: A left-shift approach to check for valid splits.
        # As we iterate through, maintain a count of how many times
        # the dominant element has appeared in the left portion of the split.
        left_count = 0

        # Try each possible split point, breaking early if we find a valid one.
        for i in range(n): # O(n)

            # If current element is the dominant element, increment left count.
            if nums[i] == dominant_element:
                left_count += 1

            # Calculate the size of the left and right parts of the split.
            left_size = i + 1  # nums[0:i]
            right_size = n - left_size  # nums[i+1:n-1]

            # Check if both the left and right parts contain the dominant element.
            # If so, then a valid split has been found; return the split index.
            if (left_count > left_size // 2) and ((total_count - left_count) > right_size // 2):
                return i

        # No valid split found.
        return -1

        # Time Complexity: O(n)
        