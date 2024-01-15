package pl.edu.pw.ee.aisd2023zlab7.shortestpaths.bellmanford;

import pl.edu.pw.ee.aisd2023zlab7.data.input.Graph;
import pl.edu.pw.ee.aisd2023zlab7.data.input.WeightedGraph;
import pl.edu.pw.ee.aisd2023zlab7.data.outcome.BellmanFordResult;

public class BellmanFord {

    private final int unknownVerticeId = -1;

    private int[] distance;

    private int[] prev;

    private WeightedGraph graph;

    public BellmanFordResult findShortestPath(WeightedGraph graph, int srcId, int destId) {
        validateInput(graph, srcId, destId);

        initData(graph);

        int n = graph.getNumOfVertices();

        distance[srcId] = 0;

        boolean failOnUpdate;
        boolean leaveEarly;
        int newDistance;
        int edgeWeight;

        for (int i = 1; i <= n; i++) {
            failOnUpdate = (i == n);
            leaveEarly = true;

            for (int verticeId = 0; verticeId < n; verticeId++) {
                for (int neighbourId : graph.getNeighbours(verticeId)) {

                    edgeWeight = graph.getWeight(verticeId, neighbourId);
                    newDistance = distance[verticeId] + edgeWeight;

                    if (newDistance < distance[neighbourId]) {
                        if (failOnUpdate) {
                            throw new RuntimeException("The graph has a negative cycle!");
                        }
                        distance[neighbourId] = newDistance;
                        prev[neighbourId] = verticeId;
                        leaveEarly = false;
                    }

                }
            }

            if (leaveEarly) {
                break;
            }
        }

        BellmanFordResult result = new BellmanFordResult(srcId, destId, distance[destId], distance);

        return result;

    }

    private void validateInput(Graph graph, int srcId, int destId) {
        int n = graph.getNumOfVertices();

        if (srcId >= n || srcId < 0) {
            throw new IllegalArgumentException("The starting index (B-F alg.) should be positive and less than the num of vertices!");
        }

        if (destId >= n || destId < 0) {
            throw new IllegalArgumentException("The ending index (B-F alg.) should be positive and less than the num of vertices!");
        }
    }

    private void initData(WeightedGraph graph) {
        this.graph = graph;

        int n = graph.getNumOfVertices();
        int initVal = unknownVerticeId;

        distance = new int[n];
        prev = new int[n];

        for (int i = 0; i < n; i++) {
            distance[i] = Integer.MAX_VALUE;
            prev[i] = initVal;
        }
    }

}
