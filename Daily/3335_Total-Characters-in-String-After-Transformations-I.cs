public class Solution {
    public int LengthAfterTransformations(string s, int t) {

        // Hash Table (Math, String, Dynamic Programming, Counting)

        // s = string.
        // t = int, the no. of transformations to perform.

        // In one transformation, every character in s is
        // replaced according to the following rules:
        // (1) If character = 'z', replace it with "ab" => length+1.
        // (2) Otherwise, replace with *next* character in alphabet. => length.

        // Return the length of the resulting string after exactly t transformations.
        // Since the answer may be very large, return modulo 10^9 + 7.
        int MOD = 1000000007;

        // List to hold the initial letter frequency count in s.
        int[] alphaCount = new int[26];
        foreach (char c in s)
        {
            alphaCount[c - 'a']++;
        }

        // Simulate the t transformations needed.
        for (int tNum = 0; tNum < t; tNum++)
        {
            // Holds updated letter frequence counts for this transformation.
            int[] newAlphaCount = new int[26];

            // Handling characters from 'a' to 'y', simply becomes next letter in alphabet.
            for (int l = 0; l < 25; l++)
            {
                newAlphaCount[l + 1] = (newAlphaCount[l + 1] + alphaCount[l]) % MOD;
            }

            // Handling 'z' becomes 'a' + 'b'...
            // Each 'z' is replaced with "ab" => increase both 'a' and 'b' counts.
            newAlphaCount[0] = (newAlphaCount[0] + alphaCount[25]) % MOD; // 'a'
            newAlphaCount[1] = (newAlphaCount[1] + alphaCount[25]) % MOD; // 'b'
            
            // Prepare for next transformation by updating alphaCount.
            alphaCount = newAlphaCount;
        }

        // Sum all character counts to get final length of resulting string.
        // i.e. sum of all character frequences.
        long totalLength = 0;
        foreach (int c in alphaCount)
        {
            totalLength = (totalLength + c) % MOD;
        }

        return (int) totalLength;
    }
}