class Solution:
    def minimumOperations(self, nums: List[int]) -> int:

        # Array (Hash Tables)

        # nums = integer array.

        # Ensure that the elements in nums are distinct.
        # To do so, you can perform the following operation any number of times:
        #   - Remove the first 3 elements. If nums has fewer than 3 elements, remove all remaining.

        # Note: Empty array is considered to be distinct.

        # Return the minimum number of times that the above operation needs to make the elements in the array distinct.

        #~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        # BRUTE FORCE, but leverages using sets to check distinctness, rather than looping through nums.
        def check_distinct(nums) -> bool:
            return len(set(nums)) == len(nums)

        # Check if the given nums already distinct.
        if check_distinct(nums):
            return 0

        # # Tracks the number of times the operation is completed.
        # count = 0

        # # For each iteration of the while, the operation is performed once.
        # while (True):

        #     # First check if nums has less than 3 elements.
        #     # If so, then all needs to be removed => one last operation.
        #     if (len(nums) < 3):
        #         # Remove all elements => empty set
        #         count += 1
        #         return count

        #     # nums has more than 3 elements, so no need to worry about that.
        #     else:

        #         # Operation: Remove the first 3 elements in nums.
        #         for i in range(3):
        #             nums.pop(0)

        #         count += 1

        #         # Check if after performing the operation,
        #         # nums is now distinct. If so, then return count.
        #         if check_distinct(nums):
        #             return count
        #~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        n = len(nums)

        # Set up a list to track the number of times each number is seen.
        # Possible due to low nums[i] constraints: 1 <= nums[i] <= 100
        track = [0] * 101

        # Traverse nums back to front.
        for i in range(n-1, -1, -1):
            
            # Increment nums[i] in track as we've seen it.
            track[nums[i]] += 1
            
            # As soon as we find a duplicate element,
            # we can use its index to determine how many
            # times the operation would have needed to be
            # completed: (i+3) // 2
            if track[nums[i]] == 2:
                return (i+3) // 3

        return 0
        