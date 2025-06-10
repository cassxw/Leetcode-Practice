public class Solution {
    public int FindKthNumber(int n, int k) {
        
        // Trie

        // n, k = integers.
        
        // Return the kth lexicographically-smallest integer
        // in the range [1, n].
        int current = 1;
        
        // Already used the first number, so decrement k.
        k--;

        while (k > 0)
        {
            // Count how many numbers exist with the current prefix
            long count = getCount(current, n);

            // If k-th number is not in current prefix subtree...
            if (k >= count)
            {
                // Skip and move to next sibling prefix.
                k -= (int)count;
                current++;
            }
            else
            {
                // Otherwise, go one level deeper in current subtree.
                // e.g. from 1 to 10, then 100, and so on.
                k--;
                current *= 10;
            }
        }

        return current;
    }

    // Helper function to count how many numbers are w/in prefix [prefix, prefix+1)
    // that are <= n; i.e. how many valid numbers exist under this prefix in the trie.
    private long getCount(long prefix, long n)
    {
        long count = 0;
        long current = prefix;
        long next = prefix + 1;

        while (current <= n)
        {
            // At each level, add number of valid entries between current and next.
            count += Math.Min(n + 1, next) - current;
            
            // Move one level deeper in the tree.
            current *= 10;
            next *= 10;
        }

        return count;
    }
}