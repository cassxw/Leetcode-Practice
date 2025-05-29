public class Solution {
    public int[] MaxTargetNodes(int[][] edges1, int[][] edges2) {
        
        // Tree (DFS, BFS)

        // There exists 2 undirected trees with n and m nodes,
        // with distinct labels in ranges [0; n-1] and [0; m-1] respectively.

        // edges1 = 2D integer array.
        // edges1[i] = [ai, bi] indicates an edge between nodes ai and bi in first tree.
        int n = edges1.Length + 1;

        // edges2 = 2D integer array.
        // edges[i] = [ui, vi] indicates an edge between nodes ui and vi in second tree.
        int m = edges2.Length + 1;

        // Node U is "target" to Node V if the no. of edges on the path from U is even.
        // Note: A node is always target to itself.

        // Return an array of n integers, answer, where
        // answer[i] is max. possible no. of nodes to "target" node i of first tree
        // if you have to connect one node from first tree to another in second tree.
        int[] answer = new int[n];

        // Colour Tree1 and get count of each parity.
        int[] colour1 = new int[n];
        int[] count1 = BuildTreeColoring(edges1, colour1);

        // Colour Tree2 and get count of each parity
        int[] colour2 = new int[m];
        int[] count2 = BuildTreeColoring(edges2, colour2);


        // For each node in Tree1, calculate max. possible targets
        // by adding its own parity group count in Tree1
        // and the larger group from Tree2 (either even or odd).
        for (int i = 0; i < n; i++)
        {
            answer[i] = count1[colour1[i]] + Math.Max(count2[0], count2[1]);
        }

        return answer;
    }

    // Builds tree and returns [evenCount, oddCount] after colouring nodes by depth parity.
    private int[] BuildTreeColoring(int[][] edges, int[] colour)
    {
        int n = edges.Length + 1;

        // Build adjacency list.
        List<int>[] graph = new List<int>[n];
        for (int i = 0; i < n; i++)
        {
            graph[i] = new List<int>();
        }

        foreach (var edge in edges)
        {
            graph[edge[0]].Add(edge[1]);
            graph[edge[1]].Add(edge[0]);
        }

        // DFS to assign parity coluor and count even/odd nodes.
        int evenCount = DFSColouring(0, -1, 0, graph, colour);
        int oddCount = n - evenCount;

        return new int[]
        {
            evenCount,
            oddCount
        };
    }

    // DFS traversal to assign depth parity (0 for even, 1 for odd) and count even nodes.
    private int DFSColouring(int node, int parent, int depth, List<int>[] graph, int[] colour)
    {
        int evenCount = 0;
        colour[node] = depth % 2;

        if (colour[node] == 0)
        {
            evenCount++;
        }

        foreach (int neighbour in graph[node])
        {
            if (neighbour != parent)
            {
                evenCount += DFSColouring(neighbour, node, depth + 1, graph, colour);
            }
        }

        return evenCount;
    }
}