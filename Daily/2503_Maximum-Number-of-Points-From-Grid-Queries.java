import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] maxPoints(int[][] grid, int[] queries) {
        
        // Array (Two Pointers, BFS, Union Find, Sorting, Heaps, Matrix)

        // grid = m x n integer matrix
        // queries = integer array of size k
        int k = queries.length;
        int m = grid.length;
        int n = grid[0].length;

        /*
        Return an array, answer, of size k, such that for every int queries[i],
        you start at the top left cell of grid ([0][0]) and repeat the following process:

        (1) If queries[i] > current grid cell, then you get +1 if it is your first time visiting this cell.
            Move to any adjacent cell in all 4 directions: up, down, left, and right.

        (2) If queries[i] <= current grid cell, no points, and process is ended.

        After, answer[i] is the maximum number of points you can get.
        */

        // Note: For each query, you are allowed to visit the same cell multiple times.

        // Set up the answer array and point counter.
        int [] answers = new int[k];

        // Create array of query indices to maintain original order.
        int[][] queryIndices = new int[k][2];
        for (int i = 0; i < k; i++) {
            queryIndices[i] = new int[]{queries[i], i};
        }

        // Sort queries while maintaining original indices.
        Arrays.sort(queryIndices, (a, b) -> a[0] - b[0]); // O(k.logk)

        // Directions for movement: down, right, up, left.
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        // Use a min-heap to track cells we can reach.
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        boolean[][] visited = new boolean[m][n];

        // Keeps track of the maximum points collected from the grid.
        int totalPoints = 0; 

        // Start from the top-left cell.
        minHeap.add(new int[]{grid[0][0], 0, 0});
        visited[0][0] = true;
        
        // Iterate through each query, in sorted order.
        for (int i = 0; i < k; i++) {
            int query = queryIndices[i][0];
            int originalIndex = queryIndices[i][1];

            // BFS to explore reachable cells: expand cells smaller than current query.
            while (!minHeap.isEmpty() && query > minHeap.peek()[0]) {
                int[] current = minHeap.poll();
                int x = current[1];
                int y = current[2];
                
                // Increment total points for visiting a new cell.
                totalPoints++; 

                // Try all four directions: up, right, down, left.
                for (int[] dir : directions) {
                    int newX = x + dir[0];
                    int newY = y + dir[1];

                    // Check if the new cell is within the grid, and if it has been visited.
                    if (
                        newX >= 0 &&
                        newX < m &&
                        newY >= 0 &&
                        newY < n &&
                        !visited[newX][newY]
                    ) {
                        // Mark the new cell as visited to avoid revisiting.
                        visited[newX][newY] = true;
                        // Add the new cell to the min-heap for further exploration
                        minHeap.offer(new int[]{grid[newX][newY], newX, newY});
                    }
                }
            }

            // Points is now the maximum amount possible for the current query.
            answers[originalIndex] = totalPoints;
        }

        // Return the array of answers, which contains the maximum points collected for each query.
        return answers;
    }
}