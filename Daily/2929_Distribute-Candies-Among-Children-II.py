class Solution:
    def distributeCandies(self, n: int, limit: int) -> int:
        
        # Math (Combinatorics, Enumeration)

        # n, limit = integer.

        # Return the total number ways of distributing
        # n candies among 3 children, such that no child
        # gets more than limit candies.
        count = 0

        #-------------------------------------------------
        # BRUTE FORCE: TLE => 503/958
        # Enumerate the candies of one particular child.
        # The first child gets i candies.
        # for i in range(min(limit, n) + 1):
        #     # The second child gets j candies.
        #     for j in range(min(limit, n-i) + 1):
        #         # The third child gets k candies.
        #         k = n - i - j
        #         if (0 <= k <= limit):
        #             count += 1
        # return count
        #-------------------------------------------------

        # A more mathy approach...

        # Count the no. of solutions to (x, y, z) such that:
        # - x+y+z = n, and...
        # - x, y, z >= 0.
        def count_ways(n):
            if n < 0:
                return 0
            return math.comb(n + 2, 2)

        # Count all unrestricted cases.
        count = count_ways(n)

        # Subtract cases where any one child gets > limit.
        subtract = 0
        for i in range(3):
            # If one child gets > limit, reduce value to (limit + 1)
            # and count remaining solutions.
            subtract += count_ways(n - (limit + 1))

        # Add back over-subtracted cases (two children > limit).
        # These were subtracted twice in previous step.
        add_back = 0
        for i in range(3):
            for j in range(i + 1, 3):
                # Shift both children's values and count remaining valid cases.
                add_back += count_ways(n - 2 * (limit + 1))

        # Subtract cases where all three children > limit.
        # These were added back one too many times in previous step.
        subtract_back = count_ways(n - 3 * (limit + 1))

        return count - subtract + add_back - subtract_back
