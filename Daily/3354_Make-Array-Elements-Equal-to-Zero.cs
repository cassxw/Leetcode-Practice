public class Solution {
    public int CountValidSelections(int[] nums) {
        
        int n = nums.Length;

        // Select a starting position, curr,
        // such that nums[curr] == 0,
        // and choose a direction of L or R.

        // After that, repeat the following:
        // (1) If curr is out of range [0; n-1], end.
        // (2) If nums[curr] == 0, move in current direction by curr++ (R) or curr-- (L).
        // (3) Else:
        //     - nums[curr]--
        //     - Reverse direction.
        //     - Take a step in new direction.

        // A selection of curr and direction is "valid"
        // if every element in nums becomes 0 by the end.

        // Return the number of "valid" selections, i.e. {curr, direction}.

        char[] directions = {'L', 'R'};
        int valid = 0;
    
        foreach (var direction in directions)
        {
            for (int startPos = 0; startPos < n; startPos++)
            {
                // Create a copy of nums for this simulation
                int[] numsCopy = new int[n];
                Array.Copy(nums, numsCopy, n);
                
                int curr = startPos;
                char currentDirection = direction;
                
                // Check if starting position has 0 (required for valid start)
                if (numsCopy[curr] != 0)
                {
                    continue;
                }

                while (true)
                {
                    if (curr < 0 || curr >= n)
                    {
                        // End; check if "valid": i.e. all 0.
                        bool allZero = true;
                        for (int i = 0; i < n; i++)
                        {
                            if (numsCopy[i] != 0)
                            {
                                allZero = false;
                                break;
                            }
                        }
                        if (allZero)
                        {
                            valid++;
                        }
                        break;
                    }
                    
                    if (numsCopy[curr] == 0)
                    {
                        switch (currentDirection)
                        {
                            case 'L':
                                curr--;
                                break;
                            case 'R':
                                curr++;
                                break;
                        }
                    }
                    else
                    {
                        numsCopy[curr]--;

                        switch (currentDirection)
                        {
                            case 'L':
                                currentDirection = 'R';
                                curr++;
                                break;
                            case 'R':
                                currentDirection = 'L';
                                curr--;
                                break;
                        }
                    }
                }
            }
        }
        
        return valid;
    }
}