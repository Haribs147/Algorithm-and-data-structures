package pl.edu.pw.ee.aisd2023zlab7.shortestpaths.dijkstra;

import pl.edu.pw.ee.aisd2023zlab7.data.input.Graph;
import pl.edu.pw.ee.aisd2023zlab7.data.input.WeightedGraph;
import pl.edu.pw.ee.aisd2023zlab7.data.outcome.DijkstraResult;

public class DijkstraV1 {

    private final int unknownVerticeId = -1;

    private WeightedGraph graph;

    private int[] distance;

    private int[] prev;

    private boolean[] visited;

    public DijkstraResult findShortestPath(WeightedGraph graph, int srcId, int destId) {
        validateInput(graph, srcId, destId);

        initData(graph);

        distance[srcId] = 0;

        int nearestVerticeId;

        while (true) {
            nearestVerticeId = findNearestNotVisitedVertice();

            if (nearestVerticeId == unknownVerticeId) {
                break;
            }

            visited[nearestVerticeId] = true;
            updateNearestDistances(nearestVerticeId);
        }

        DijkstraResult result = new DijkstraResult(srcId, destId, distance[destId], distance);

        return result;
    }

    private void validateInput(Graph graph, int srcId, int destId) {
        int n = graph.getNumOfVertices();

        if (srcId >= n || srcId < 0) {
            throw new IllegalArgumentException("The starting index should be positive and less than num of vertices!");

        } else if (destId >= n || destId < 0) {
            throw new IllegalArgumentException("The ending index should be positive and lower than num of vertices!");
        }

    }

    private void initData(WeightedGraph graph) {
        this.graph = graph;

        int n = graph.getNumOfVertices();
        int initVal = unknownVerticeId;

        distance = new int[n];
        prev = new int[n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            distance[i] = Integer.MAX_VALUE;
            prev[i] = initVal;
            visited[i] = false;
        }
    }

    private int findNearestNotVisitedVertice() {
        int n = visited.length;

        int nearestId = unknownVerticeId;
        int minDistance = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            if (!visited[i] && distance[i] < minDistance) {
                minDistance = distance[i];
                nearestId = i;
            }
        }

        return nearestId;
    }

    private void updateNearestDistances(int currentVerticeId) {
        int newDistance;
        int edgeWeight;

        for (int verticeId : graph.getNeighbours(currentVerticeId)) {

            edgeWeight = graph.getWeight(currentVerticeId, verticeId);
            newDistance = distance[currentVerticeId] + edgeWeight;

            if (newDistance < distance[verticeId]) {
                distance[verticeId] = newDistance;
                prev[verticeId] = currentVerticeId;
            }
        }
    }
}
