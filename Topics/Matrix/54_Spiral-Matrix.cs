public class Solution {
    public IList<int> SpiralOrder(int[][] matrix) {
        
        // Array (Matrix, Simulation)

        // matrix = m x n matrix.
        int m = matrix.Length;      // Rows.
        int n = matrix[0].Length;   // Cols.

        // Return all elements of the matrix in spiral order.

        // List to store elements in spiral order.
        List<int> spiral = new List<int>();

        // Initialise boundaries of the current layer.
        // Right => Down => Left => Up
        int top = 0, bottom = m - 1;
        int left = 0, right = n - 1;

        // Continue looping until the boundaries overlap.
        while (top <= bottom && left <= right) {

            // Traverse from Left => Right along current Top row.
            for (int i = left; i <= right; i++) {
                spiral.Add(matrix[top][i]);
            }
            top++; // Done with this row, move boundary down.

            // Traverse from Top => Down along the current Right col.
            for (int i = top; i <= bottom; i++) {
                spiral.Add(matrix[i][right]);
            }
            right--; // Done with this col, move boundary left.

            // Check if there are still rows remaining...
            if (top <= bottom) {

                // Traverse from Right => Left along the current Bottom row.
                for (int i = right; i >= left; i--) {
                    spiral.Add(matrix[bottom][i]);
                }
                bottom--; // Done with this row, move boundary Up.
            }

            // Check if there are still cols remaining...
            if (left <= right) {

                // Traverse from Bottom => Top along the current Left col.
                for (int i = bottom; i >= top; i--) {
                    spiral.Add(matrix[i][left]);
                }
                left++; // Done with this col, move boundary right.
            }
        }

        // Return the collected elements in spiral order.
        return spiral;
    }
}