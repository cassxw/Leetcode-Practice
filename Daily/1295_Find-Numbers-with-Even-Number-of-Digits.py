class Solution:
    def findNumbers(self, nums: List[int]) -> int:

        # Array (Math)

        # nums = integer array.

        # Return how many of them contain an even number of digits.

        # Track how many numbers have even digit counts.
        count = 0

        # Iterate through every num in nums.
        for num in nums:

            # ---------------------- STRING APPROACH ------------------------
            # Convert the number to a string, check the length of the string.
            # If it's even, increment the count.
            # if (len(str(num)) % 2 == 0):
            #     # Length of current number is even.
            #     count += 1

            # ---------------------- MATH APPROACH ------------------------
            # Count digits without converting the number to a string.
            digits = 0
            n = num # Copy
    
            # Keep (int) dividing by 10 to strip off digits until 0.
            while (num > 0):
                num //= 10
                digits += 1

            # Check if the number of digits is even.
            if (digits % 2 == 0):
                count += 1
        
        # Return the total count of numbers with even number of digits.
        return count