public class Solution {
    public int MaxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        
        // Array (BFS, Graph)

        // status = int array.
        // status[i] = 1 if the ith box is open.
        //             0 if the ith box is closed.
        int n = status.Length;

        // candies = int array.
        // candies[i] = no. of candies in the ith box.

        // keys = int array.
        // keys[i] = list of labels of the boxes that can be opened after opening the ith box.

        // containedBoxes = int array.
        // containedBoxes[i] = list of boxes found inside the ith box.

        // initialBoxes = int array.
        // Contains labels of the boxes you initially have.

        // You can take all candies in any open box.
        // You can use the keys in any open box.
        // You can use the boxes you find in it.

        // Return the max. no. of canides you can get,
        // following the rules above.

        // Track which boxes we have open already or not.
        // haveKey[i] = true means we can open the ith box.
        bool[] haveKey = new bool[n];
        for (int i = 0; i < n; i++)
        {
            if (status[i] == 1)
            {
                // Box i is initially open, so treat it like we "already had its key".
                haveKey[i] = true;
            }
        }

        // Track which distinct boxes we physically own or have seen,
        // but not necessarily opened.
        HashSet<int> ownedBoxes = new HashSet<int>();
        foreach (int b in initialBoxes)
        {
            ownedBoxes.Add(b);
        }

        // Track which boxes have already been opened,
        // so that we never open them twice.
        bool[] opened = new bool[n];
        
        int totalCandies = 0;
        bool madeProgress = true;

        // Keep looping until we do a full scan and open zero new boxes.
        while (madeProgress)
        {
            madeProgress = false;
            
            // Convert ownedBoxes to a list here so we can iterate over it safely,
            // in case we add new boxes midway through the loop.
            List<int> snapshotOfOwned = new List<int>(ownedBoxes);
            
            foreach (int box in snapshotOfOwned)
            {
                // If we've already opened it...
                if (opened[box])
                {
                    // Skip.
                    continue;
                }
                
                // Can we open it right now?
                // i.e. It is already open or we collected its key.
                if (haveKey[box])
                {
                    // Mark as opened so we don’t re‐open it later.
                    opened[box] = true;
                    madeProgress = true;
                    
                    // Collect candies immediately.
                    totalCandies += candies[box];
                    
                    // Collect any new keys from this box.
                    foreach (int newKey in keys[box])
                    {
                        if (!haveKey[newKey])
                        {
                            haveKey[newKey] = true;
                        }
                    }
                    
                    // Add any new boxes we find inside to ownedBoxes.
                    foreach (int innerBox in containedBoxes[box])
                    {
                        if (!ownedBoxes.Contains(innerBox))
                        {
                            ownedBoxes.Add(innerBox);
                        }
                    }
                }

                // We don’t have the key for current box.
                // Leave it in ownedBoxes for future passes.
            }
            
            // We went through every box we know about and didn’t open any; exit loop.
        }
        
        return totalCandies;
    }
}