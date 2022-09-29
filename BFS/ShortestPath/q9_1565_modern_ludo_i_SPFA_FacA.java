package BFS.ShortestPath;

import java.util.*;

public class q9_1565_modern_ludo_i_SPFA_FacA {
    public int modernLudo_1(int length, int[][] connections) {
        Map<Integer, Set<Integer>> graph = buildGraph(length, connections);

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        Map<Integer, Integer> distance = new HashMap<>();
        for (int i = 1; i <= length; i++) {
            distance.put(i, Integer.MAX_VALUE);
        }
        distance.put(1, 0);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int nextNode : graph.get(node)) {
                if (distance.get(nextNode) > distance.get(node)) {
                    distance.put(nextNode, distance.get(node));
                    queue.offer(nextNode);
                }
            }
            int limit = Math.min(node + 7, length + 1);
            for (int nextNode = node + 1; nextNode < limit; nextNode++) {
                if (distance.get(nextNode) > distance.get(node) + 1) {
                    distance.put(nextNode, distance.get(node) + 1);
                    queue.offer(nextNode);
                }
            }
        }

        return distance.get(length);
    }

    private Map<Integer, Set<Integer>> buildGraph(int length, int[][] connections) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= length; i++) {
            graph.put(i, new HashSet<>());
        }
        for (int i = 0; i < connections.length; i++) {
            int from = connections[i][0];
            int to = connections[i][1];
            graph.get(from).add(to);
        }
        return graph;
    }

    // version 2 ¶ÑÓÅ»¯
    class Pair {
        int distance, node;

        Pair(int distance, int node) {
            this.node = node;
            this.distance = distance;
        }
    }

    public int modernLudo_2(int length, int[][] connections) {
        Map<Integer, Set<Integer>> graph = buildGraph2(length, connections);

        Queue<Pair> queue = new PriorityQueue(
                new Comparator<Pair>() {
                    public int compare(Pair p1, Pair p2) {
                        return p1.distance - p2.distance;
                    }
                }
        );
        queue.offer(new Pair(0, 1));
        Map<Integer, Integer> distance = new HashMap<>();
        for (int i = 1; i <= length; i++) {
            distance.put(i, Integer.MAX_VALUE);
        }
        distance.put(1, 0);
        while (!queue.isEmpty()) {
            int dist = queue.peek().distance;
            int node = queue.peek().node;
            queue.poll();
            for (int nextNode : graph.get(node)) {
                if (distance.get(nextNode) > dist) {
                    distance.put(nextNode, dist);
                    queue.offer(new Pair(dist, nextNode));
                }
            }
            int limit = Math.min(node + 7, length + 1);
            for (int nextNode = node + 1; nextNode < limit; nextNode++) {
                if (distance.get(nextNode) > dist + 1) {
                    distance.put(nextNode, dist + 1);
                    queue.offer(new Pair(dist + 1, nextNode));
                }
            }
        }
        return distance.get(length);
    }

    private Map<Integer, Set<Integer>> buildGraph2(int length, int[][] connections) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= length; i++) {
            graph.put(i, new HashSet<>());
        }
        for (int i = 0; i < connections.length; i++) {
            int from = connections[i][0];
            int to = connections[i][1];
            graph.get(from).add(to);
        }
        return graph;
    }
}