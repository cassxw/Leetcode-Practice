public class Solution {
    public IList<string> GetLongestSubsequence(string[] words, int[] groups) {
        
        // Array (String, Dynamic Programming, Greedy)

        // words = distinct string array.
        // groups = binary array.
        // words[i] is associated with groups[i].
        int n  = words.Length;

        // Terrible problem description...

        // Find the longest subsequence of words such that
        // for every pair of consecutive words in the subsequence,
        // their corresponding group values are different.
        var result = new List<string>();

        // EDGE CASE: If words empty, none possible.
        if (words.Length == 0)
        {
            return result;
        }

        // Start with first word by default.
        result.Add(words[0]);

        // Track of the group of the last added word.
        int lastGroup = groups[0];

        // Iterate through the rest of words.
        for (int i = 1; i < words.Length; i++)
        {
            // If current group differs from last.
            if (groups[i] != lastGroup)
            {
                // Add to result.
                result.Add(words[i]);
                
                // Update last group.
                lastGroup = groups[i];
            }

            // If same as the last group, skip to maintain alternating pattern.
        }

        return result;
    }
}