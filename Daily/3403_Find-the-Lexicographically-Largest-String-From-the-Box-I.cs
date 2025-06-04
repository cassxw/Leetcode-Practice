public class Solution {

    public string AnswerString(string word, int numFriends) {
        
        // Two Pointers (String, Enumeration)

        // word = string.
        // numFriends = integer.
        int n = word.Length;

        // Allie is organising a game for numFriends friends.
        // The game has multiple rounds, where in each:
        // (1) word is split into numFriends non-empty strings,
        //     such that no previous round has had the exact same split.
        // (2) All the split words are put into a box.

        // Find the lexicographically-largest string from the box,
        // after all the rounds are finished.

       // If only one friend, whole string is the only split.
        if (numFriends == 1)
        {
            return word;
        }

        // Largest possible string in box will be largest substring that could appear
        // as part in some valid split into numFriends parts.
        int i = 0;          // Current candidate for best substring start index.
        int j = 1;          // Searches ahead to find a better candidate.
        int maxStart = 0;

        while (j < n)
        {
            int k = 0;

            // Compare characters at i+k and j+k until they differ or end of string reached.
            while (j + k < n && word[i + k] == word[j + k])
            {
                k++;
            }

            // If j+k didn't hit the end...
            if (j + k < n && word[i + k] < word[j + k])
            {
                // Found a better candidate at j; update i.
                int oldI = i;
                i = j;

                // Move j to avoid rechecking same prefix.
                j = Math.Max(j + 1, oldI + k + 1);
            }
            else
            {
                // i is still better, so advance j past overlap.
                j += k + 1;
            }
        }

        // Now, i is starting index of the lexicographically-largest suffix.
        string bestSuffix = word.Substring(i);

        // But in a valid split, a part can be at most (n - numFriends + 1) characters long.
        int maxPartLength = n - numFriends + 1;
        return bestSuffix.Substring(0, Math.Min(bestSuffix.Length, maxPartLength));
    }
}
