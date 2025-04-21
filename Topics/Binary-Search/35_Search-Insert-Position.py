class Solution:
    def searchInsert(self, nums: List[int], target: int) -> int:

        # Array (Binary Search)

        # nums = sorted, distinct integer array.
        # target = int.

        # Return the index if the target is found.
        # If not, return the index where it would be if it were inserted in order.

        # Initialise pointers for binary search.
        low = 0
        high = len(nums)-1

        # Perform binary search.
        while (low <= high):

            # Calculate mid index of the current search range.
            mid = low + (high - low) // 2

            # If the target is found, return its index.
            if (nums[mid] == target):
                return mid

            if (target > nums[mid]):
                # If the target is greater than mid, search the right half.
                low = mid + 1
            elif (target < nums[mid]):
                # If the target is smaller than mid, search the left half.
                high = mid - 1

        # If we exit the binary search loop, the target is not found.
        # At this point, low represents the index where the target should be inserted.
        return low