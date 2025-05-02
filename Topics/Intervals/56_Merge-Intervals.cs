public class Solution {
    public int[][] Merge(int[][] intervals) {

        // Array (Sorting)

        // intervals = integer array.
        // intervals[i] = [start_i, end_i]
        int n = intervals.Length;

        // Merge all overlapping intervals.
        // Return an array of the non-overlapping intervals
        // that cover all the intervals in the input.

        // Sort the intervals based on the starting value.
        Array.Sort(intervals, (a, b) => a[0].CompareTo(b[0]));

        // Create new list for the merged intervals.
        List<int[]> merged = new List<int[]>();

        // Start with the first interval.
        int[] current = intervals[0];

        // Iterate through each interval.
        foreach (int[] interval in intervals) {

            // If current interval overlaps with the new one (start <= current end)...
            if (interval[0] <= current[1]) {
                // Merge them by extending the end to the furthest point.
                current[1] = Math.Max(current[1], interval[1]);
            
            } else {
                // No overlap - add current to result and move to the next.
                merged.Add(current);
                current = interval;
            }
        }

        // Add last interval after the loop.
        merged.Add(current);

        // Return our merged interval list as an array.
        return merged.ToArray();
    }
}