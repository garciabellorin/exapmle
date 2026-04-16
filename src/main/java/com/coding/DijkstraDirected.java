package com.coding;

import java.util.*;

public class DijkstraDirected {

    static class Edge {
        int to, weight;
        Edge(int to, int weight) { this.to = to; this.weight = weight; }
        public int getTo() { return to; }
        public int getWeight() { return weight; }
    }

    public static int[] dijkstra(int n, List<List<Edge>> graph, int src) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        PriorityQueue<int[]> pq =
                new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{src, 0});

        boolean[] visited = new boolean[n];

        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int node = top[0];

            if (visited[node]) continue;
            visited[node] = true;

            for (Edge e : graph.get(node)) {
                int next = e.to;
                int newDist = dist[node] + e.weight;

                if (newDist < dist[next]) {
                    dist[next] = newDist;
                    pq.offer(new int[]{next, newDist});
                }
            }
        }
        for (int i = 0; i < dist.length; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                dist[i] = -1;
            }
        }


        return dist;
    }

    public static void main(String[] args) {
        int n = 5;
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        // Directed edges
        graph.get(0).add(new Edge(1, 4));
        graph.get(0).add(new Edge(2, 1));
        graph.get(2).add(new Edge(1, 2));
        graph.get(1).add(new Edge(3, 1));
        graph.get(4).add(new Edge(3, 1));
        graph.get(2).add(new Edge(3, 5));

        int[] dist = dijkstra(n, graph, 0);
        System.out.println(Arrays.toString(dist));
    }
}

