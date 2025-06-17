class Solution:
    def countGoodArrays(self, n: int, m: int, k: int) -> int:
        
        # Math (Combinatorics)

        # n, m, k = integers.

        # A "good" array arr of size n is defined as follows:
        # - Each element in arr is in [1; m].
        # - Exactly k indices i [1; n) satisfy arr[i-1] == arr[i].

        # Return the number of "good" arrays that can be formed.
        # Since the answer may be large, return it modulo 10e9 + 7.
        MOD = 10**9 + 7

        #------------------------------------------------------------------------
        # DP: MLE (555/809) [O(n * k) space complexity for large values]
        # dp[i][j] = number of arrays of length i with exactly j equal adj pairs.
        # dp = [[0] * (k + 1) for _ in range(n + 1)]
        # # BASE CASE: Array of length 1, 0 equal adj pairs, is m choices.
        # dp[1][0] = m
        # for i in range(2, n + 1):
        #     for j in range(0, min(k, i - 1) + 1):
        #         # Case 1: pick a different number → no new equal pair
        #         dp[i][j] += dp[i - 1][j] * (m - 1)
        #         dp[i][j] %= MOD
        #         # Case 2: repeat previous → adds one equal adjacent pair
        #         if j > 0:
        #             dp[i][j] += dp[i - 1][j - 1] * 1
        #             dp[i][j] %= MOD
        # return dp[n][k]
        #------------------------------------------------------------------------

        # Precompute factorials and inverse factorials up to n.
        fact = [1] * (n)
        inv_fact = [1] * (n)

        # Compute factorials modulo MOD.
        for i in range(1, n):
            fact[i] = fact[i - 1] * i % MOD

        # Compute modular inverse of factorials using Fermat’s Little Theorem.
        inv_fact[n - 1] = pow(fact[n - 1], MOD - 2, MOD)
        for i in range(n - 2, -1, -1):
            inv_fact[i] = inv_fact[i + 1] * (i + 1) % MOD

        # Fast nCr using precomputed factorials.
        def comb(a, b):
            if (b < 0) or (b > a):
                return 0
            return fact[a] * inv_fact[b] % MOD * inv_fact[a - b] % MOD

        # Compute no. of ways to choose positions for equal pairs.
        equal_pair_ways = comb(n-1, k)

        # Compute no. of ways to assign values to segments.
        distinct_group_ways = m * pow(m-1, n-k-1, MOD) % MOD

        return equal_pair_ways * distinct_group_ways % MOD
