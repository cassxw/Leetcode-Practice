public class Solution {
    public void SetZeroes(int[][] matrix) {

        // Array (Hash Table, Matrix)

        // matrix = mxn integer matrix.
        int m = matrix.Length;
        int n = matrix[0].Length;

        // If an element is 0,
        // set its entire row and column to 0s.
        // => IN-PLACE.

        // To avoid using extra space, use the first row and col
        // to keep track of which rows and cols need to be zeroed out.
        bool firstRowZero = false;
        bool firstColZero = false;

        // If first row should be zero...
        for (int i = 0; i < m; i++)
        {
            if (matrix[i][0] == 0)
            {
                firstColZero = true;
                break;
            }
        }

        // If first col should be zero...
        for (int j = 0; j < n; j++)
        {
            if (matrix[0][j] == 0)
            {
                firstRowZero = true;
                break;
            }
        }

        // Use the first row and column as storage to mark to be zeroed.
        for (int i = 1; i < m; i++)
        {
            for (int j = 1; j < n; j++)
            {
                // If current cell is zero...
                if (matrix[i][j] == 0)
                {
                    // Mark its row and column by setting
                    // matrix[i][0] and matrix[0][j] to 0.
                    matrix[i][0] = 0; // row
                    matrix[0][j] = 0; // col
                }
            }
        }

        // Iterate through (excluding first row/col), and set cells 
        // to 0 if their row or column was already marked.
        for (int i = 1; i < m; i++)
        {
            for (int j = 1; j < n; j++)
            {
                if (matrix[i][0] == 0 || matrix[0][j] == 0)
                {
                    matrix[i][j] = 0;
                }
            }
        }

        // Handle the first row and first col separately.
        if (firstRowZero)
        {
            for (int j = 0; j < n; j++)
            {
                matrix[0][j] = 0;
            }
        }

        if (firstColZero)
        {
            for (int i = 0; i < m; i++)
            {
                matrix[i][0] = 0;
            }
        }
    }
}
