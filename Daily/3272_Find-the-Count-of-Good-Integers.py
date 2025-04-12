class Solution:
    def countGoodIntegers(self, n: int, k: int) -> int:
        
        # Hash Table (Math, Combinatorics, Enumeration)

        # n = positive integer.
        # k = positive integer.

        # An integer is called "k-Palindromic" if...
        # (1) It is a palindrome.
        # (2) It is divisible by k.

        # An integer is called "good" if its digits can be
        # rearranged to form a "k-Palindromic" integer.

        # Return the count of good integers containing n digits.

        MOD = 10 ** 9 + 7
        
        # Helper function to calculate factorial with modulo.
        # Uses modulo at each step to prevent integer overflow.
        def factorial(x):
            result = 1
            for i in range(1, x + 1):
                result = (result * i) % MOD
            return result
        
        seen = set()
        # For n digits, we only need to generate (n+1)//2 digits.
        # Example:  For n=5, we generate 3 digits (10^2 to 10^3-1).
        #           For n=4, we generate 2 digits (10^1 to 10^2-1).
        base = 10 ** ((n - 1) // 2)

        # skip=1 for odd length (skip middle digit when mirroring).
        # skip=0 for even length (use all digits when mirroring).
        skip = n & 1  
        
        # Iterate through all palindromic numbers of length n.
        # Start from the highest possible, down to the lowest possible.
        # We generate palindromes by taking a number and mirroring its digits.
        # Example:  For n=5, 123 -> 12321
        #           For n=4, 12 -> 1221
        for i in range(base, base * 10):
            s = str(i)
            s += s[::-1][skip:]  # Mirror the digits, skipping middle for odd length.
            palindrome = int(s)
            
            # If it is not divisible by k, skip.
            if palindrome % k != 0:
                continue
            
            # If we've already seen a permutation of it, skip.
            # Use a key that is unique to all permutations of this number, like sorting the digits.
            # Example: 12321 and 21312 have the same key "12233"
            perm_key = "".join(sorted(s))
            if perm_key in seen:
                continue
            seen.add(perm_key)
        
        # Calculate factorials once for efficiency.
        # Needed for the multinomial coefficient calculation.
        factorials = [factorial(i) for i in range(n + 1)]
        result = 0
        
        # For each unique permutation key we found...
        for s in seen:

            # Count the number of valid permutations of this number and add it to our total.
            # Combinatorics: count occurrences of each digit.
            # This forms the denominator of our multinomial coefficient.
            digit_counts = [0] * 10
            for digit in s:
                digit_counts[int(digit)] += 1
            
            # Calculate total permutations avoiding leading zeros.
            # First multiply by number of valid first digits (non-zero).
            # We multiply by (n-zeros) because we don't want zeros at the start.
            total = ((n - digit_counts[0]) * factorials[n - 1]) % MOD
            
            # Then, divide by factorial of each digit's frequency for repeated digits.
            # This is the multinomial coefficient formula:
            # n!/(k1!*k2!*...*km!) where ki are the frequencies of each digit
            # We use modular multiplicative inverse (pow(x,MOD-2,MOD)) for division
            for count in digit_counts:
                if count > 0:
                    total = (total * pow(factorials[count], MOD - 2, MOD)) % MOD
            
            result = (result + total) % MOD
        
        return result