public class Solution {
    public int ClosestMeetingNode(int[] edges, int node1, int node2) {
        
        // DFS, Graph

        // edges = integer array.
        // edges[i] = j means a directed edge from node i to node j.
        // edges[i] = -1 means node i has no outgoing edge.
        // Note: It may contain cycles!
        int n = edges.Length;

        // node1, node2 = integers.

        // Given a directed graph of n nodes, numbered [0; n-1]
        // where each node has at most one outgoing edge.

        // Return node index that can be reached from both node1 and node2,
        // such that the max. between distance from node1 to it, 
        // and from node2 to it, is minimised.
        // If multiple answers, return the node with smallest index.
        // If none, return -1.

        // Compute distances from node1 and node2 to every other node.
        int[] dist1 = GetDistances(edges, node1);
        int[] dist2 = GetDistances(edges, node2);

        int minDist = int.MaxValue;
        int result = -1;

        // Iterate over all nodes to find node reachable from both node1 and node2.
        for (int i = 0; i < n; i++)
        {
            // Check if current node is reachable from both node1 and node2.
            if (dist1[i] != -1 && dist2[i] != -1)
            {
                // For such a node, calculate max. of the two distances.
                int maxDist = Math.Max(dist1[i], dist2[i]);

                // Update result if a smaller max distance is found
                // or if the same max distance is found at a smaller index
                if (maxDist < minDist)
                {
                    minDist = maxDist;
                    result = i;
                }
            }
        }

        return result;
    }

    // Helper method to compute shortest distances from the start node to all reachable nodes.
    // Since each node has at most one outgoing edge, effectively a linear walk.
    private int[] GetDistances(int[] edges, int start)
    {
        int n = edges.Length;
        int[] dist = new int[n];

        // Initialise all distances as -1 (unreachable).
        Array.Fill(dist, -1);

        int current = start;
        int distance = 0;

        // Traverse graph, stopping if we revisit a node to prevent cycles.
        while (current != -1 && dist[current] == -1)
        {
            dist[current] = distance++;
            current = edges[current];
        }

        return dist;
    }
}