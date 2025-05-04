from collections import defaultdict

class Solution:
    def numEquivDominoPairs(self, dominoes: List[List[int]]) -> int:
        
        # Array (Hash Table, Counting)

        # dominoes = list of dominoes.

        # dominoes[i] == dominoes[j]
        #      [a, b] == [c, d]
        # if either...
        #   - a==c and b==d
        #   - a==d and b==c
        # i.e. can be rotated to be equal.

        # Return the number of pairs (i, j)
        # for which 0 <= i < j < n.
        # and dominoes[i] is equivalent to dominoes[j].

        #-------------------------------------------------------------------------------------------
        # BRUTE FORCE => TLE (18/19)
        # n = len(dominoes)
        # count = 0
        # for i in range(n-1): # O(n)
        #     for j in range(i+1, n): # O(n)
        #         # Check if dominoes[i] and dominoes[j] are equivalent
        #         if  (((dominoes[i][0] == dominoes[j][0]) and (dominoes[i][1] == dominoes[j][1])) or
        #             ((dominoes[i][0] == dominoes[j][1]) and (dominoes[i][1] == dominoes[j][0]))):
        #             count += 1
        # return count
        # # = O(n^2)
        #-------------------------------------------------------------------------------------------

        # Optimise above by keeping track of what has been seen via a hashmap.
        # We "normalies" each domino by sorting its two numbers.
        # e.g. [1,2] and [2,1] both become (1,2), allowing us to track how many we've seen.
        # For each, we can instantly know how many equivalent ones we've seen before.

        count = 0
        seen = defaultdict(int)

        for (a, b) in dominoes:
            # Normalise the current domino,
            # so that [a, b] and [b, a] are treated the same
            key = tuple(sorted((a, b)))

            # If seen this normalised key before,
            # each occurrence can form a unique pair.
            count += seen[key]  

            # Record the current domino as seen.
            seen[key] += 1  

        # Return total number of equivalent domino pairs.
        return count
