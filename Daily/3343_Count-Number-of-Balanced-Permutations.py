from collections import Counter
from functools import lru_cache
import math # Needed for factorial, although precomputing is better with modulo

class Solution:
    def countBalancedPermutations(self, num: str) -> int:

        # Math (String, Dynamic Programming, Combinatorics)

        # num = String.
        n = len(num)

        # "balanced": sum of digits at even indices = sum of digits at odd indices.

        # Return the number of distinct, "balanced" permutations of num.
        # Since the answer may be very large, return it mod 10^9 + 7.
        MOD = 10**9 + 7

        # A tough one... :(
        # Math and Combinatorics are my weakest.

        # Sum of all digits.
        total_sum  = 0

        # Frequency count for each digit 0-9.
        digit_counts = [0] * 10 

        # Count digit frequencies and calculate total sum.
        for ch in num:
            d = int(ch)
            digit_counts[d] += 1
            total_sum += d

        # If the total sum is odd...
        if total_sum % 2 != 0:
            # A "balanced" permutation is impossible.
            return 0

        # Target sum for both even and odd indices must be half total_sum.
        target_sum = total_sum // 2

        # Number of even positions in the final permutation.
        num_even_pos = (n + 1) // 2
        # Number of odd positions in the final permutation.
        num_odd_pos = n - num_even_pos # i.e. n // 2

        # DP Table: dp[s][k] = number of ways to form a partial arrangement using digits seen so far
        #                      s.t. s = even_sum, and k of these digits are placed at even positions.
        #
        # State: (current_even_sum, current_num_even_pos)
        # Size: dp[possible_even_sum][possible_even_pos_count]
        #
        # Max possible even_sum is target_sum.
        # Max possible even pos count is num_even_pos.
        dp = [[0] * (num_even_pos + 1) for _ in range(target_sum + 1)]

        # BASE CASE: Before considering any digits...
        # Even sum is 0, i.e. 0 digits placed at even positions.
        dp[0][0] = 1

        # Precompute factorials and their modular inverses modulo MOD for combinations.
        MAX_N_COMB = n
        fact = [1] * (MAX_N_COMB + 1)
        inv_fact = [1] * (MAX_N_COMB + 1)
        for i in range(2, MAX_N_COMB + 1):
            fact[i] = (fact[i - 1] * i) % MOD

            # Using Fermat's Little Theorem for modular inverse since MOD is a prime number.
            inv_fact[i] = pow(fact[i], MOD - 2, MOD)

        # Helper function to calculate combinations (n choose k) modulo MOD.
        def combinations(n_comb, k_comb):

            # If k is less than 0 or greater than n...
            if k_comb < 0 or k_comb > n_comb:
                # There are no combinations.
                return 0
            
            # combinations(n, k) = n! / (k! * (n-k)!) = n! * (k!)^-1 * ((n-k)!)^-1 mod MOD.
            return (((fact[n_comb] * inv_fact[k_comb]) % MOD) * inv_fact[n_comb - k_comb]) % MOD

        # Keep track of total count of digits processed so far.
        # Helps to determine total number of positions filled in the partial arrangement.
        total_digits_processed_count = 0

        # Keep track of the total value of digits processed so far.
        # Helps to determine bounds for even sum in the inner loop.
        total_digits_processed_val = 0

        # Iterate through each distinct digit from 0 to 9.
        for current_digit in range(10):

            # Get frequency of current digit in the input number.
            digit_count = digit_counts[current_digit]

            # If the current digit is not present...
            if (digit_count == 0):
                # Skip.
                continue

            # Store the total count and value *before* processing the current digit.
            prev_total_digits_processed_count = total_digits_processed_count
            prev_total_digits_processed_val = total_digits_processed_val

            # Update total counts and value *after* adding the current digit's counts.
            total_digits_processed_count += digit_count
            total_digits_processed_val += current_digit * digit_count

            # Iterate backward through possible states <even sum, even position count>
            # achievable *after* adding the current digit.

            # Iterate through no. of even positions filled by digits 0 to current_digit.
            # Range determined by total digits processed and total even/odd slots available.
            min_even_pos_curr = max(0, total_digits_processed_count - num_odd_pos)
            max_even_pos_curr = min(total_digits_processed_count, num_even_pos)

            for even_pos_count_curr_stage in range(max_even_pos_curr, min_even_pos_curr - 1, -1):

                # Number of odd positions filled by digits 0 to current_digit.
                odd_pos_count_curr_stage = total_digits_processed_count - even_pos_count_curr_stage

                # Iterate backward through possible even sums achievable *after* adding current digit.
                # Range determined by total value processed and the target sum.
                min_even_sum_curr = max(0, total_digits_processed_val - target_sum)
                max_even_sum_curr = min(total_digits_processed_val, target_sum)

                for even_sum_curr_stage in range(max_even_sum_curr, min_even_sum_curr - 1, -1):

                    # Accumulator for ways to reach (even_sum_curr_stage, even_pos_count_curr_stage).
                    ways_to_reach_state = 0

                    # Consider how many times current digit is placed at EVEN positions; Remaining go to ODD.
                    
                    # Iterate through possible counts of current digit at even positions.
                    # Range determined by digit's frequency and available even/odd slots among digits [0; current_digit].
                    min_curr_even_count = max(0, digit_count - odd_pos_count_curr_stage)
                    max_curr_even_count = min(digit_count, even_pos_count_curr_stage)
                    
                    for digit_count_at_even_pos in range(min_curr_even_count, max_curr_even_count + 1):

                        # No. of times the current digit is placed at odd positions.
                        digit_count_at_odd_pos = digit_count - digit_count_at_even_pos

                        # Calculate the previous state (before adding current digit).
                        
                        # Previous even sum = current even sum - (value of current digit at even pos)
                        prev_even_sum = even_sum_curr_stage - (digit_count_at_even_pos * current_digit)
                        # Previous even pos count = current even pos count - (count of current digit at even pos)
                        prev_even_pos_count = even_pos_count_curr_stage - digit_count_at_even_pos

                        # Check if previous state indices are valid and reachable (count > 0).
                        if (prev_even_sum < 0 or prev_even_sum > target_sum or \
                            prev_even_pos_count < 0 or prev_even_pos_count > num_even_pos):
                            # Invalid previous state indices.
                            continue

                        if (dp[prev_even_sum][prev_even_pos_count] == 0):
                            # Previous state is unreachable.
                            continue

                        # Calculate ways to place digit_count instances.
                        # into the used even/odd slots: comb(total even slots used, at even) * comb(total odd slots used, at odd).
                        ways_to_place_current_digit = (
                            combinations(even_pos_count_curr_stage, digit_count_at_even_pos) *
                            combinations(odd_pos_count_curr_stage, digit_count_at_odd_pos) % MOD
                        )

                        # Accumulate ways: ways to reach previous state * ways to place current digit.
                        ways_to_reach_state = (
                            ways_to_reach_state + ways_to_place_current_digit * dp[prev_even_sum][prev_even_pos_count] % MOD
                        ) % MOD

                    # Update the DP table entry for the current state.
                    dp[even_sum_curr_stage][even_pos_count_curr_stage] = ways_to_reach_state % MOD

        # The final answer is the number of ways to use all digits, such that:
        # 1. The sum at even positions is the target sum.
        # 2. The number of digits at even positions is the total number of even positions.
        # => odd positions and their sum are also correct for a "balanced" permutation.
        return dp[target_sum][num_even_pos]
