public class Solution {
    public int Candy(int[] ratings) {
        
        // Array (Greedy)

        // ratings = 
        int n = ratings.Length;

        // There are n children standing in a line.
        // Each child is assigned a rating value.
        // i.e. rating[i] = ith child's rating.
        int[] candies = new int[n];

        // You are giving candies to children, subject to:
        // (1) Each child must have at least one candy.
        // (2) Children with a higher rating get more candies than their neighbours.

        // Return the minimum number of candies needed
        // to distribute the candies to the n children appropriately.

        // Start by giving each child 1 sweet.
        for (int i = 0; i < n; i++)
        {
            candies[i] = 1;
        }

        // Left-to-Right Pass:
        // If current child has a higher rating than left neighbour,
        // give them one more candy than the neighbour.
        for (int i = 1; i < n; i++)
        {
            if (ratings[i] > ratings[i-1])
            {
                candies[i] = candies[i-1] + 1;
            }
        }

        // Right-to-Left Pass:
        // If current child has a higher rating than right neighbour,
        // and doesn't already have more candies, update accordingly.
        for (int i = n-2; i >= 0; i--)
        {
            if (ratings[i] > ratings[i+1])
            {
                candies[i] = Math.Max(candies[i], candies[i+1] + 1);
            }
        }

        // Sum up the total candies needed.
        int totalCandies = 0;
        foreach (int c in candies)
        {
            totalCandies += c;
        }

        return totalCandies;
    }
}