public class Solution {
    public int[] MaxTargetNodes(int[][] edges1, int[][] edges2, int k) {
        
        // Tree (DFS, BFS)

        // There exists 2 undirected trees with n and m nodes,
        // with distinct labels in ranges [0; n-1] and [0; m-1] respectively.

        // edges1 = 2D integer array.
        // edges1[i] = [ai, bi] indicates an edge between nodes ai and bi in first tree.
        // Since tree1 have n-1 edges, number of nodes = edges + 1.
        int n = edges1.Length + 1;

        // edges2 = 2D integer array.
        // edges[i] = [ui, vi] indicates an edge between nodes ui and vi in second tree.
        // Since tree2 have m-1 edges, number of nodes = edges + 1.
        int m = edges2.Length + 1;

        // k = integer.

        // Node U is "target" to Node V if the no. of edges on the path from U to V <= k.
        // Note: A node is always target to itself.

        // Return an array of n integers, answer, where
        // answer[i] is max. possible no. of nodes to "target" node i of first tree
        // if you have to connect one node from first tree to another in second tree.
        int[] answer = new int[n];

        // Build adjacency lists for both trees.
        var tree1 = BuildAdjList(n+1, edges1);
        var tree2 = BuildAdjList(m+1, edges2);

        // Precompute for Tree2:
        // For each node in second tree, find how many nodes are reachable
        // within a distance of (k - 1).
        int[] tree2Reach = new int[m];
        for (int v = 0; v < m; v++)
        {
            tree2Reach[v] = CountReachable(v, tree2, k - 1);
        }

        // Compute for Tree1:
        // For each node in first tree, compute max number of "target" nodes
        // it could have if we connect it to any node in second tree.
        for (int u = 0; u < n; u++)
        {
            // Count how many nodes are reachable within a distance of k.
            int count1 = CountReachable(u, tree1, k);

            // Try connecting u to every node in Tree2 and calculate sum.
            int maxCount = 0;
            for (int v = 0; v < m; v++)
            {
                maxCount = Math.Max(maxCount, count1 + tree2Reach[v]);
            }

            // Store best possible result for node u.
            answer[u] = maxCount;
        }

        return answer;
    }

    // Helper to construct an adjacency list from edge list.
    List<int>[] BuildAdjList(int n, int[][] edges)
    {
        var tree = new List<int>[n];

        for (int i = 0; i < n; i++)
        {
            tree[i] = new List<int>();
        }

        foreach (var edge in edges)
        {
            int u = edge[0];
            int v = edge[1];
            
            tree[u].Add(v);
            tree[v].Add(u);
        }

        return tree;
    }

    // BFS to count nodes reachable within maxDist from start node.
    int CountReachable(int start, List<int>[] tree, int maxDist)
    {
        var visited = new bool[tree.Length];
        var queue = new Queue<(int node, int dist)>();
        visited[start] = true;
        queue.Enqueue((start, 0));
        int count = 0;

        while (queue.Count > 0)
        {
            var (node, dist) = queue.Dequeue();

            // Stop exploring paths that go beyond maxDist.
            if (dist > maxDist)
            {
                continue;
            }

            // Count current node
            count++;

            // Add unvisited neighbors to queue.
            foreach (var neighbor in tree[node])
            {
                if (!visited[neighbor])
                {
                    visited[neighbor] = true;
                    queue.Enqueue((neighbor, dist + 1));
                }
            }
        }

        return count;
    }
}