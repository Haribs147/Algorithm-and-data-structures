package pl.edu.pw.ee.aisd2023zlab7.data.outcome;

public class GraphBfsResult extends GraphSearchResult {

    private final int[] distance;

    public GraphBfsResult(int startId, int[] prevVertices, int[] distance) {
        super(startId, prevVertices);

        this.distance = distance;
    }

    public int FindPath(int endId) {
        if (distance[endId] == Integer.MAX_VALUE) {
            return 0;
        }
        return 1;
    }

    public int[] getDistance() {
        return distance;
    }

}
