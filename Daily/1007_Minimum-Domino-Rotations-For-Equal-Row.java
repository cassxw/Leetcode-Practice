class Solution {
    public int minDominoRotations(int[] tops, int[] bottoms) {
        
        // Array (Greedy)

        // tops[i] = top half of the ith domino.
        // bottoms[i] = bottom half of the ith domino.

        // We may rotate the ith domino,
        // so that tops[i] swaps with bottoms[i].

        // Return the minimum number of rotations, such that either:
        //  - all values in tops are the same.
        //  - all values in bottoms are the same.

        // The only valid candidates are the values tops[0] and bottoms[0].
        // Why?
        //      Because every domino must contain the target number,
        //      and it has to be present in at least one of the two halves
        //      of the first domino to even have a chance.

        // Try making all dominoes show tops[0].
        int rotations = check(tops[0], tops, bottoms);

        // If that worked (rotations != -1), or tops[0] == bottoms[0]...
        if (rotations != -1 || tops[0] == bottoms[0]) {
            // Return the result.
            return rotations;
        }

        // Otherwise, try making all dominoes show bottoms[0].
        return check(bottoms[0], tops, bottoms);
    }

    // Helper function to check how many rotations are needed
    // to make all values in either tops or bottoms equal to x.
    // If impossible, return -1.
    private int check(int x, int[] tops, int[] bottoms) {
        int rotationsTop = 0;     // Rotations needed to make all tops = x.
        int rotationsBottom = 0;  // Rotations needed to make all bottoms = x

        for (int i = 0; i < tops.length; i++) {

            // If the number x is not in either position for this domino...
            if (tops[i] != x && bottoms[i] != x) {
                // This number can't fill all dominoes => IMPOSSIBLE.
                return -1; 

            } else if (tops[i] != x) {
                // Need to rotate to bring x to top.
                rotationsTop++;

            } else if (bottoms[i] != x) {
                // Need to rotate to bring x to bottom.
                rotationsBottom++;
            }

            // If both tops[i] and bottoms[i] are already x, no rotation needed.
        }

        // Return the minimal number of rotations needed on either row.
        return Math.min(rotationsTop, rotationsBottom);
    }
}