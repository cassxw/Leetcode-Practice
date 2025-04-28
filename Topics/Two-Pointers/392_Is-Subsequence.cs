public class Solution {
    public bool IsSubsequence(string s, string t) {
        
        // Two Pointers (String, Dynamic Programming)

        // s, t = String.
        int sLen = s.Length;
        int tLen = t.Length;

        // "subsequence" of a string,
        // is a new string that is formed from the original string
        // by deleting 0+ characters, without disturbing
        // the relative positions of remainign characters.
        // e.g. ace is a subsequence of abcde.
        //      ace is NOT a subsequence of aec.

        // Return true if s is a subsequence of t.
        // Otherwise, return false.

        // Initialise two pointers, one for each string.
        int si = 0;
        int ti = 0;

        // Traverse through both strings.
        while (si < sLen && ti < tLen) {

            if (s[si] != t[ti]) {
                // Characters don't match:
                // Move t pointer forward to keep searching.
                ti++;
            } else {
                // Characters match:
                // Move both pointers forward.
                si++;
                ti++;
            }
        }

        // If matched all characters in s (si reached sLen),
        // then s is a subsequence of t.
        return si == sLen;
    }
}