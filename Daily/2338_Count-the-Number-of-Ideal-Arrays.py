class Solution:
    def idealArrays(self, n: int, maxValue: int) -> int:
        
        # Math (DP, Combinatorics, Number Theory)

        # n = integer.
        # maxValue = integer.

        # An array, arr, of length n, is considered "ideal" if the following holds:
        # (1) Every arr[i] is a value from [1; maxValue], for 0 <= i < n.
        # (2) Every arr[i] is divisible by arr[i-1], for 0 < i < n.

        # Observation: All "ideal" arrays are non-decreasing.

        # Return the number of distinct ideal arrays of length n.
        # May be very large, so return it modulo 1e9 + 7.
        MOD = int(1e9 + 7)

        #--------------------------------------------------------------------
        # BRUTE FORCE RECURSIVE => TLE (21/47)
        # Recursively build out arrays, by only appending the numbers
        # divisible by the last number in the array.

        # # Counter to store total number of "ideal" arrays.
        # self.ideal_count = 0

        # def dfs(path):
        #     # BASE CASE:
        #     # If we have built an array of length n, it's "ideal".
        #     if len(path) == n:
        #         self.ideal_count += 1
        #         return

        #     # Get the last value to determine valid next steps.
        #     last = path[-1]

        #     # Try all possible next values that are divisible by the last.
        #     for next_val in range(last, maxValue+1, last):
        #         dfs(path + [next_val])

        # # Start DFS from every possible first element from 1 to maxValue.
        # for i in range(1, maxValue+1):
        #     dfs([i])

        # # Return result modulo 1^9 + 7.
        # return self.ideal_count % MOD
        #--------------------------------------------------------------------

        # Very hard...brute force is not possible due to constraints!
        # Combinatorics is a weak point of mine, and editorial is in Greek.
        # TODO: Revise this :)

        # ~~~~~~~~~~~~~ Combinatorics-Based Approach ~~~~~~~~~~~~~~~~~~~~~~~~
        # Count how many "ideal" arrays end in each number x in [1; maxValue]
        # - For each x, we factor it into its prime components.
        # - For each prime factor with count p, we compute the number of ways
        #   to distribute p identical items into n slots:
        #   => C(n - 1 + p, p)

        # Precompute all primes up to maxValue using sieve.
        # spf[i] stores the smallest prime factor of i.
        spf = [0] * (maxValue + 1)  # Smallest Prime Factor
        for i in range(2, maxValue + 1):
            if spf[i] == 0:
                for j in range(i, maxValue + 1, i):
                    if spf[j] == 0:
                        spf[j] = i

        # Precompute combinations: C(n + p - 1, p) up to p <= 100.
        from math import comb

        # comb_cache is used to avoid recalculating combinations.
        # comb(a, b) computes C(a, b) => number of ways to choose b items from a.
        comb_cache = {}

        def get_comb(a, b):
            if (a, b) not in comb_cache:
                comb_cache[(a, b)] = comb(a, b)
            return comb_cache[(a, b)]

        ideal_count = 0

        # Use Combinatorics to count distributions across n slots.
        # Iterate through each possible last number in "ideal" array.
        for x in range(1, maxValue + 1):
            factors = {}
            val = x

            # Prime factorisation of x using precomputed SPF.
            while val > 1:
                prime = spf[val]
                factors[prime] = factors.get(prime, 0) + 1
                val //= prime

            # For each prime factor:
            # Calculate how many ways we can spread copies of it across n elements.
            # Think of distributing identical balls (prime powers) into n slots.
            ways = 1
            for power in factors.values():
                ways *= get_comb(n - 1 + power, power)

            # Add the number of valid combinations for this ending value x.
            ideal_count += ways

         # Return the final count modulo 1e9 + 7.
        return ideal_count % MOD
