class Solution:
    def threeConsecutiveOdds(self, arr: List[int]) -> bool:
        
        # Array

        # arr = integer array.
        n = len(arr)

        # Return true if there are three consecutive odd numbers.
        # Otherwise, return false.

        # Small constraints => Brute-Force.

        # EDGE CASE:
        # If the array has fewer than 3 elements, impossible to have 3 consecutive odds.
        if (n < 3):
            return False

        # Loop through arr up to the third-last element.
        for i in range(n-2):

            # Check if the current number and the next two are all odd.
            if ((arr[i] % 2 != 0) and (arr[i+1] % 2 != 0) and (arr[i+2] % 2 != 0 )):
                # Found three consecutive odd numbers!
                return True

        # If we get here, no odd triplet was found.
        return False