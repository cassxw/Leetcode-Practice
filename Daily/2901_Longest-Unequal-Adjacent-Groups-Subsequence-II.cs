public class Solution
{
    // Helper to compute the Hamming Distance between two strings.
    // Returns the number of positions where the characters differ.
    // If not of equal length, returns -1.
    private int HammingDistance(string a, string b)
    {
        if (a.Length != b.Length)
        {
            return -1;
        }

        int distance = 0;

        for (int i = 0; i < a.Length; i++)
        {
            if (a[i] != b[i])
            {
                distance++;
            }
        }

        return distance;
    }

    public IList<string> GetWordsInLongestSubsequence(string[] words, int[] groups)
    {
        // Array (String, Dynamic Programming)

        // words = distinct string array.
        // groups = binary array.
        // words[i] is associated with groups[i].
        int n  = words.Length;

        // "Hamming Distance" between two strings of *equal* length
        // is the number of positions at which corresponding characters are different.

        // Select the longest subsequence from words from an array of indices [0; n-1]
        // such that it, with length k, allows the following to hold:
        //
        // (1)  For adj indices, their corresponding groups are unequal,
        //      i.e. groups[i] != groups[j]
        //
        // (2)  words[i] and words[j] are equal in length,
        //      and their "Hamming Distance" = 1, for all indices in the subsequence.

        // Return a string array containing the words corresponding to the indices
        // (in order) in the selected subsequences. If multiple, return any.

        // dp[i] will store the length of longest valid subsequence ending at index i.
        int[] dp = new int[n];
        Array.Fill(dp, 1);      // Every word by itself is a subsequence of length 1.

        // prev[i] will store the index of previous word in subsequence ending at index i.
        // prev is used to rebuild paths.
        int[] prev = new int[n];
        Array.Fill(prev, -1);   // Every index initialised to no previous element. 

        // Track length of longest subsequence found.
        int maxLen = 1;

        // Track ending index of longest subsequence found.
        int maxIndex = 0;

        // Iterate over each pair of words to find valid a path.
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < i; j++)
            {
                // Check both (1) and (2) conditions.
                if ((groups[i] != groups[j]) && (HammingDistance(words[i], words[j]) == 1))
                {
                    // Valid pair.
                    // If taking j before i gives a longer subsequence, update dp[i].
                    if (dp[j] + 1 > dp[i])
                    {
                        dp[i] = dp[j] + 1;
                        prev[i] = j;
                    }
                }
            }

            // Update length and index of longest subsequence found so far.
            if (dp[i] > maxLen)
            {
                maxLen = dp[i];
                maxIndex = i;
            }
        }

        // Reconstruct the longest subsequence found, by walking back through prev.
        var result = new List<string>();
        int index = maxIndex;

        while (index != -1)
        {
            result.Add(words[index]);
            index = prev[index];
        }

        // Reverse to get back in correct order from start to end.
        result.Reverse();
        return result;
    }
}