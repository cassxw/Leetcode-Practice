class Solution:
    def sortColors(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        
        # Array (Two Pointers, Sorting)

        # nums = int array.
        n = len(nums)

        # nums is a lift with n objects, coloured Red(0)/White(1)/Blue(2).
        # Sort them in-place, so that obj of same colour are adj,
        # with the colour in orders: 0 => 1 => 2.

        # Initialise counters for each colour.
        red = 0     # Count of 0s (red)
        white = 0   # Count of 1s (white)
        blue = 0    # Count of 2s (blue)

        # First pass to get counts of each colour.
        for num in nums:
            if (num == 0):
                red += 1
            elif (num == 1):
                white += 1
            else:
                blue += 1

        # Second pass to overwrite original list with sorted colors.
        for i in range(n):
            if (i < red):
                nums[i] = 0
            elif (i < red + white):
                nums[i] = 1
            elif (i < red + white + blue):
                nums[i] = 2
