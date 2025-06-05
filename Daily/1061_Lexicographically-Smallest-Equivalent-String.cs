public class Solution {

    // Array of parent of each character in union-find structure.
    // Each index represents a lowercase character ('a' to 'z' => 0 to 25).
    int[] parent = new int[26];

    public string SmallestEquivalentString(string s1, string s2, string baseStr) {

        // String (Union Find)

        // baseStr = string.
        int n = baseStr.Length;

        // s1, s2 = strings.
        // s1[i] = s2[i] = equivalent characters.
        // - Reflexivity:   'a' == 'a'
        // - Symmetry:      'a' == 'b' => 'b' == 'a'.
        // - Transitivity:  'a' == 'b' and 'b' == 'c' => 'a' == 'c'.

        // Return the lexicographically-smallest equivalent string
        // of baseStr, by using the equivalency information from s1 and s2. 

        // Initialise parent so that each char is its own parent.
        for (int i = 0; i < 26; i++)
        {
            parent[i] = i;
        }

        // For each char paris in s1 and s2, create equivalence relation.
        // Union two characters so that they belong to same group.
        for (int i = 0; i < s1.Length; i++)
        {
            Union(s1[i] - 'a', s2[i] - 'a');
        }

        StringBuilder sb = new StringBuilder();

        // For each char in baseStr, find lexicographically smallest
        // equivalent character from the union-find structure.
        foreach (char c in baseStr)
        {
            // Find representative of group and convert back to a char.
            sb.Append((char)(Find(c - 'a') + 'a'));
        }
        
        return sb.ToString();
    }

    // Find operation with path compression.
    // Returns root parent of character group.
    int Find(int x)
    {
        // If x is not parent of itself...
        if (parent[x] != x)
        {
            // Recurse and path compress.
            parent[x] = Find(parent[x]);
        }

        return parent[x];
    }

    // Union operation that merges two char groups.
    // Always chooses lexicographically smaller character as group leader.
    void Union(int x, int y)
    {
        int px = Find(x);
        int py = Find(y);

        // If both chars are already in same group...
        if (px == py)
        {
            // Skip.
            return;
        }

        // Always attach lexicographically larger one to smaller one.
        // Ensures that smallest char becomes the representative.
        if (px < py)
        {
            parent[py] = px;
        }
        else
        {
            parent[px] = py;
        }
    }
}
