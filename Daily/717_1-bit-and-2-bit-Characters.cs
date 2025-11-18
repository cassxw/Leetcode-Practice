public class Solution {
    public bool IsOneBitCharacter(int[] bits) {
        
        // 2 special characters:
        // (1) Represented by one bit 0.
        // (2) Represented by two bits (10 or 11).

        // Given a binary array, bits, that ends with 0,
        // return true if the last character must be
        // a one-bit character.

        int n = bits.Length;
        int i = 0;

        // Parse through the bits.
        while (i < n - 1)
        {
            if (bits[i] == 0)
            {
                // one-bit character => move forward by 1.
                i++;
            }
            else // bits[i] == 1
            {
                // two-bit character => move forward by 2.
                i += 2;
            }
        }

        // If we end exactly at the last position (n-1),
        // the last character must be a one-bit character.
        return i == n - 1;
    }
}