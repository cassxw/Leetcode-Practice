public class Solution {

    // words[i] consists of lowercase English letters.
    // In one step, select any i, such that:
    // (1) 0 < i < words.length.
    // (2) words[i] is an anagram of words[i - 1].
    // ...and delete words[i] from the array.
    // Return words after performing the above operation as many times as possible.
    public IList<string> RemoveAnagrams(string[] words)
    {
        // Loop through each word, and compare to the previous element.
        for (int i = 1; i < words.Length; i++)
        {
            // If they are anagrams...
            if (IsAnagram(words[i], words[i - 1]))
            {
                // Remove the current element.
                words = words.Where((val, idx) => idx != i).ToArray();
                i--;
            }
        }

        return words;
    }

    private bool IsAnagram(string a, string b)
    {
        // If the lengths are different...
        if (a.Length != b.Length)
        {
            // IMPOSSIBLE.
            return false;
        }

        // Create a freq arr for each letter in the alphabet.
        int[] freq = new int[26];

        // Count the freq of each letter in string a.
        foreach (char c in a)
        {
            freq[c - 'a']++;
        }

        // Subtract the freq of each letter in string b.
        foreach (char c in b)
        {
            freq[c - 'a']--;
        }

        // If all frequencies are zero, a and b are anagrams.
        return freq.All(count => count == 0);
    }
}