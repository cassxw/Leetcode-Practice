public class Solution {
    public string TriangleType(int[] nums) {
        
        // Array (Math, Sorting)

        // nums = integer array, of length 3 to form sides of a triangle.

        // A triangle can be:
        // - "equilateral" if all sides are of equal length.
        // - "isosceles" if two sides are of equal length.
        // - "scalene" if all sides are of different lengths.

        // Return a string representing the type of triangle that can be formed.
        // Otherwise, if it cannot form a triangle, return "none".

        // Observation:
        // A triangle is invalid if for any two sides,
        // the sum of their lengths must be greater than the third side.

        // check if triangle is valid using triangle inequality rule
        for (int i = 0; i < 3; i++)
        {
            // Testing if nums[i] is taken as the third.
            // Using i, add the other two sides.
            switch (i)
            {
                case 0:
                    if (nums[1] + nums[2] <= nums[0])
                    {
                        return "none";
                    }
                    break;

                case 1:
                    if (nums[0] + nums[2] <= nums[1])
                    {
                        return "none";
                    }
                    break;

                case 2:
                    if (nums[0] + nums[1] <= nums[2])
                    {
                        return "none";
                    }
                    break;
            }
        }

        // Use HashSet to count unique side lengths.
        HashSet<int> uniqueSides = new HashSet<int>(nums);

        if (uniqueSides.Count == 1)
        {
            return "equilateral";
        }
        else if (uniqueSides.Count == 2)
        {
            return "isosceles";
        }
        else
        {
            return "scalene";
        }
    }
}
