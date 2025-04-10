class Solution:
    def numberOfPowerfulInt(self, start: int, finish: int, limit: int, s: str) -> int:

        # Array (String, Dynamic Programming)

        # start = int
        # finish = int
        # limit = int
        # s = string, representing a positive integer.
        suffix_length = len(s)

        # A positive int is "powerful" if:
        # (1) Its suffix = s.
        # (2) Every digit is <= limit.
        
        # Return the total number of powerful integers in the range [start, finish].

        # EDGE CASE: If finish is smaller than the suffix itself,
        #            no numbers in range can have that suffix.
        if (finish < int(s)):
            return 0


        # Key Obersvation:
        # Update start and finish to fit in with the constraints of limit.

        def normalise(bound):
            """
            Replaces any digit in bound larger than limit, with the limit itself.
            This helps identify the largest possible powerful number <= limit.
            """
            new_bound = 0

            # Flag for if encountered a digit > limit.
            less = False

            for digit in map(int, str(bound)):
                if less:
                    # After finding a digit > limit,
                    # all subsequent digits should be set to limit.
                    new_bound = new_bound * 10 + limit

                elif digit > limit:
                    # When we find a digit > limit,
                    # replace it with limit.
                    less = True
                    new_bound = new_bound * 10 + limit
                
                else:
                    # Current digit is fine, leave as is.
                    new_bound = new_bound * 10 + digit
            
            return new_bound

        def count(num):
            """
            Counts how many "powerful" integers exist up to num.
            Works by:
            1. Taking the prefix (all digits except last suffix_length digits).
            2. For each digit in prefix, multiplies by possible digits (0 to limit).
            3. Adds 1 if the number formed by prefix + s is <= num.
            """
            answer = 0

            # Number of possible digits (0, limit).
            base = limit + 1

            # All digits except the suffix.
            prefix = str(num)[:-suffix_length]
            
            # Calculate count based on prefix digits.
            for n in prefix:
                answer = answer * base + int(n)

            # Check if number formed by current prefix + s
            # is less than or equal to our target number.
            if int(prefix + s) <= num:
                answer += 1
            
            return answer
        
        # The final answer is the difference between:
        # - Count of powerful numbers up to finish
        # - Count of powerful numbers up to (start-1)
        return count(normalise(finish)) - count(normalise(start-1))

        #~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        # dp = [[]]
        # ...Digit DP? :(
        