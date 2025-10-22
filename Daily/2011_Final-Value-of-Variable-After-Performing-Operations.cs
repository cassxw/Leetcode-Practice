public class Solution {
    public int FinalValueAfterOperations(string[] operations) {

        // Possible operations:
        // -- "++X" or "X++": increment the value of the variable X by 1.
        // -- "--X" or "X--": decrement the value of the variable X by 1.

        int x = 0;

        // Given an array of operations,
        // return the final value of X
        // after performing all the operations.
        
        foreach (var operation in operations)
        {
            if (operation == "++X" || operation == "X++")
            {
                x++;
            }
            else 
            {
                x--;
            }
        }

        return x;
    }
}