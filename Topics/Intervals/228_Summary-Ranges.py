class Solution:
    def summaryRanges(self, nums: List[int]) -> List[str]:

        # Array

        # nums = a sorted, distinct integer array.
        n = len(nums)

        # Return the smallest sorted list of ranges that cover all the numbers in the array exactly.
        # - Each element is covered by exactly one of the ranges.
        # - There is no integer x that is in one of the ranges, and not in nums.

        # "a->b"    if a != b
        # "a"       if a == b

        # List to store the summary ranges.
        ranges = []

        # EDGE CASE: Return an empty list if the input is empty.
        if (n == 0):
            return ranges

        currentRangeStart = nums[0] # Start of the current range (initially first element)
        currentRangeEnd = None      # End of the current range (initially None).

        # Iterate through the sorted list.
        for i in range(1, n): # O(n)

            if (nums[i-1] + 1 == nums[i]):
                # Continuation of the current range.
                currentRangeEnd = nums[i] # Update the end of the current range.
                print("...in range starting from (", currentRangeStart, "->", currentRangeEnd, "->...")

            elif (nums[i-1] + 1 != nums[i]):
                # A break in the current range has been found.
                print("BREAK!")

                # Check if  current range consists of a single value.
                if (currentRangeEnd is None):
                    print("Solo Range:", currentRangeStart)
                    ranges.append(str(currentRangeStart))

                else:
                    # Finalise the end of the current range.
                    currentRangeEnd = nums[i-1]  
                    currentRange = str(currentRangeStart) + "->" + str(currentRangeEnd)
                    ranges.append(currentRange)
                    print("Finished Range:", currentRange)

                # Reset for the next potential range.
                currentRangeStart = nums[i]
                currentRangeEnd = None

        # Handle the last range after the loop.
        if (currentRangeEnd is None):
            ranges.append(str(currentRangeStart))
        else:
            currentRange = str(currentRangeStart) + "->" + str(currentRangeEnd)
            ranges.append(currentRange)
        
        # Return the list of summary ranges.
        return ranges