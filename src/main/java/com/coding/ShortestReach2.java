package com.coding;

import java.util.*;

public class ShortestReach2 {

    static class Edge {
        int to, weight;

        Edge(int t, int w) {
            to = t;
            weight = w;
        }
    }

    /*
     * Complete the 'shortestReach' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. 2D_INTEGER_ARRAY edges
     *  3. INTEGER s
     */

    public static List<Integer> shortestReach(int n, List<List<Integer>> edges, int s) {

        // Step 1: Build adjacency list with manual edge compression
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

        // Use a temporary structure to compress edges
        int[][] best = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) Arrays.fill(best[i], Integer.MAX_VALUE);

        for (List<Integer> e : edges) {
            int u = e.get(0);
            int v = e.get(1);
            int w = e.get(2);

            if (w < best[u][v]) {
                best[u][v] = w;
                best[v][u] = w;
            }
        }

        // Build adjacency list from compressed matrix
        for (int u = 1; u <= n; u++) {
            for (int v = 1; v <= n; v++) {
                if (best[u][v] != Integer.MAX_VALUE) {
                    graph.get(u).add(new int[]{v, best[u][v]});
                }
            }
        }

        // Step 2: Dijkstra
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;

        boolean[] visited = new boolean[n + 1];

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{s, 0});

        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int node = top[0];

            if (visited[node]) continue;
            visited[node] = true;

            for (int[] edge : graph.get(node)) {
                int next = edge[0];
                int weight = edge[1];

                int newDist = dist[node] + weight;
                if (newDist < dist[next]) {
                    dist[next] = newDist;
                    pq.offer(new int[]{next, newDist});
                }
            }
        }
        // Step 3: Build result
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i == s) continue;
            result.add(dist[i] == Integer.MAX_VALUE ? -1 : dist[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 5;
        int s = 1;
        List<List<Integer>> edges = List.of(
                List.of(1, 2, 5),
                List.of(2, 3, 6),
                List.of(5, 5, 0),
                List.of(3, 4, 2),
                List.of(1, 3, 15)
        );

        int n2 = 4;
        List<List<Integer>> edges2 = List.of(
                List.of(1, 2, 24),
                List.of(1, 4, 20),
                List.of(1, 3, 3),
                List.of(3, 4, 12)
        );

        List<Integer> result = shortestReach(n, edges, s);
        List<Integer> result2 = shortestReach(n2, edges2, s);
        System.out.println(result); // Expected: [5, 11, 13, -1]
        System.out.println(result2); // Expected: [24, 3, 15]
    }
}

