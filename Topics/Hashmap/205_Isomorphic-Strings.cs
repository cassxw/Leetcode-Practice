public class Solution {
    public bool IsIsomorphic(string s, string t) {

        // Hash Table (String)

        // s, t = strings.
        int sLen = s.Length;
        int tLen = t.Length;
        
        // Two strings, s and t, are "isomorphic"
        // if the characters in s can be replaced to get t.

        // Given two strings, s and t,
        // determine if they are "isomorphic".

        // Edge case:
        // If their lengths differ, impossible.
        if (sLen != tLen) {
            return false;
        }

        // Hash maps for both directions.
        var mapST = new Dictionary<char, char>(); // s => t
        var mapTS = new Dictionary<char, char>(); // t => s
        
        // Traverse both strings character by character.
        for (int i = 0; i < s.Length; i++) {
            char c1 = s[i]; // character from s.
            char c2 = t[i]; // character from t.

            // Check mapping from s to t...
            if (mapST.ContainsKey(c1)) {
                if (mapST[c1] != c2) {
                    // Inconsistent mapping found.
                    return false;
                }

            } else {
                mapST[c1] = c2;
            }

            // Check mapping from t to s...
            if (mapTS.ContainsKey(c2)) {
                if (mapTS[c2] != c1) {
                    // Inconsistent mapping found.
                    return false;
                }

            } else {
                mapTS[c2] = c1;
            }
        }

        // All characters map consistently => ISOMORPHIC!!
        return true; 
    }
}