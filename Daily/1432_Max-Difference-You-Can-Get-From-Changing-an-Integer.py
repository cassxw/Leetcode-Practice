class Solution:
    def maxDiff(self, num: int) -> int:
        
        # Math (Greedy)

        # num = integer.
        
        # Apply the following steps to num two times:
        # (1) Pick a digit x (0 <= x <= 9).
        # (2) Pick a digit y (0 <= y <= 9).
        # (3) Replace all occurrences of x in the decimal rep. of num by y.

        # Let a and b be two separate results by applying indepedently.

        # Return the max difference between a and b.
        # i.e. maximise a, and minimise b.

        # Note: Neither a nor b may have any leading zeroes.

        num_str = str(num)
        
        # Maximise:
        # Create a by replacing the first non-9 digit with 9.
        for ch in num_str:
            if (ch != '9'):
                a_str = num_str.replace(ch, '9')
                break
        else:
            # All digits are 9.
            a_str = num_str 
        a = int(a_str)

        # Minimise: Create b.
        # If the first digit isn't 1...
        if (num_str[0] != '1'):
            # Replace it with 1.
            b_str = num_str.replace(num_str[0], '1')
        else:
            # First digit is 1.
            for ch in num_str[1:]:
                if (ch != '0') and (ch != '1'):
                    # Replace next non-0 and non-1 with 0.
                    b_str = num_str.replace(ch, '0')
                    break
            else:
                # All digits are 0 or 1.
                b_str = num_str  
        b = int(b_str)

        # Return max - min.
        return a - b
        