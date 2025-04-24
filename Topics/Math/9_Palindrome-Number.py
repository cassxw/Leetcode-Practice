class Solution:
    def isPalindrome(self, x: int) -> bool:
        
        # Math

        # x = integer.

        # Return true if x is a palindrome.
        # Otherwise, false.

        # Observations:
        # (1) Negative numbers are never palindromes due to '-'.
        # (2) Numbers that are divisible by 10 with no remainder cannot be due to ending '0'.
        #     ...except the single digit 0, a special case.
        if (x < 0) or (x % 10 == 0 and x != 0):
            return False

        # Easy! (and fast...)
        # return str(x) == str(x)[::-1]

        # But what is the "mathy" way...? :)
        reverse = 0

        # During the below process,
        # at some point, reverse either be <= x.
        while x > reverse:
            
            # Pop the last digit of x.
            digit = x % 10

            # Push it onto reverse.
            reverse = reverse * 10 + digit

            # Chop off that last digit of x.
            x //= 10
        
        # After producing our reverse, it needs to be handled differently
        # depending on whether x has an even/odd number of digits.

        # If x is a palindrome...
        #   - and had an even no. of digits, x == reverse.
        #   - and had an odd no. of digits, x == reverse // 10. 

        return (x == reverse) or (x == reverse // 10)
