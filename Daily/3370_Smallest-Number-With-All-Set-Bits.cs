public class Solution {
    public int SmallestNumber(int n) {
        
        // n is a positive nunmber.

        // Return the smallest number x >= n,
        // such that the binary representation of x
        // contains only set bits.

        // Convert n to binary representation.
        // If already all set, return.
        // Set any zeroes, and return.
        // ^ A bit naive.

        // Improved with hint:
        // Find the strictly greater power of 2, then subtract 1.
        long p = 1;
        while (p <= n)
        {
            // Multiply by 2 until it's strictly greater than n.
            p <<= 1;
        }
        return (int)(p - 1);
    }
}