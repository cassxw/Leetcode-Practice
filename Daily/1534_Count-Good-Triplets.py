class Solution:
    def countGoodTriplets(self, arr: List[int], a: int, b: int, c: int) -> int:

        # Array (Enumeration)

        # arr = integer array.
        # a, b, c = integers
        n = len(arr)

        # A "good" triplet (arr[i], arr[j], arr[k]) must satisfy:
        # (1) Ordering condition: 0 <= i < j < k < len(arr)
        # (2) Difference conditions:
        #       - |arr[i] - arr[j]| <= a
        #       - |arr[j] - arr[k]| <= b
        #       - |arr[i] - arr[k]| <= c

        # Return the number of "good" triplets.

        # Due to minimal constraints (3 <= arr.length <= 100),
        # brute force (although ugly...) is still the best.

        count = 0

        # Iterate through all possible triplet combinations.
        for left in range(n-2): # O(n)
            for mid in range(left+1, n-1): # O(n)
                
                # Simplest optimisation: Early Pruning
                # Skip the innermost loop if first condition fails.
                # Saves unnecessary iterations.
                if abs(arr[left] - arr[mid]) > a:
                    continue
                
                for right in range(mid+1, n): # O(n)

                    # Check remaining conditions for a "good" triplet.
                    if (abs(arr[mid] - arr[right]) <= b) and (abs(arr[left] - arr[right]) <= c):
                        count += 1

        return count
        # = O(n^3)
