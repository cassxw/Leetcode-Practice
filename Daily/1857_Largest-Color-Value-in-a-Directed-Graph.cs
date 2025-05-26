public class Solution {
    public int LargestPathValue(string colors, int[][] edges) {
        
        // Hash Table (Dynamic Programming, Graph, Topological Sort, Memoization, Counting)

        // colors = string array.
        // colors[i] is a lowercase letter, representing colour of ith node in grapj.
        int n = colors.Length;

        // edges = 2D integer array.
        // edges[j] = [aj, bj] indicates there is a directed edge from node a to node b.
        int m = edges.Length;

        // There exists a directed graph of n coloured nodes and m edges.
        
        // "Valid" Path is a sequence of nodes x1 -> xk,
        // such that there is a directed edge from xi to xi+1 for ever 1 <= i < k.

        // The path's colour value is the no. of nodes that are coloured
        // the most frequently occurring colour along that path.

        // Return the largest colour value of any "valid" path in given graph.
        // Otherwise, return -1 if the graph contains a cycle.

        // Adjacency list representation of the graph.
        List<int>[] graph = new List<int>[n];

        // indegree[i] = number of incoming edges to node i.
        int[] indegree = new int[n];

        // Initialise the graph.
        for (int i = 0; i < n; i++)
        {
            graph[i] = new List<int>();
        }

        // Build graph and compute indegrees.
        foreach (var edge in edges)
        {
            int from = edge[0], to = edge[1];
            graph[from].Add(to);
            indegree[to]++;
        }

        // Queue for Topological Sort, for nodes with zero indegree.
        Queue<int> queue = new Queue<int>();
        for (int i = 0; i < n; i++)
        {
            if (indegree[i] == 0)
            {
                queue.Enqueue(i);
            }
        }

        // dp[i][c] is max count of colour c at node i.
        int[][] dp = new int[n][];
        for (int i = 0; i < n; i++)
        {
            // 26 lowercase letters.
            dp[i] = new int[26];
        }

        // Count of nodes processed in topological order.
        int visited = 0;
        int maxColourValue = 0;

        // Process nodes in topological order.
        while (queue.Count > 0)
        {
            int node = queue.Dequeue();
            visited++;

            // Increment the count for this node's own colour.
            int colourIndex = colors[node] - 'a';
            dp[node][colourIndex]++;
            maxColourValue = Math.Max(maxColourValue, dp[node][colourIndex]);

            // Update neighbours' dp tables.
            foreach (int neighbor in graph[node])
            {
                for (int c = 0; c < 26; c++)
                {
                    dp[neighbor][c] = Math.Max(dp[neighbor][c], dp[node][c]);
                }

                // Decrease indegree and add to queue if zero.
                indegree[neighbor]--;
                if (indegree[neighbor] == 0)
                {
                    queue.Enqueue(neighbor);
                }
            }
        }

        // If not all nodes visited, there's a cycle.
        return visited == n ? maxColourValue : -1;
    }
}