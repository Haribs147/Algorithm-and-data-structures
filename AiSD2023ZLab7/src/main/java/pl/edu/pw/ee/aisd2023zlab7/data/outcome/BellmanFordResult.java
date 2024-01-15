package pl.edu.pw.ee.aisd2023zlab7.data.outcome;

public class BellmanFordResult extends ShortestPathsResult {

    private final int[] distances;

    public BellmanFordResult(int srcId, int destId, int minDistance, int[] distances) {
        super(srcId, destId, minDistance);

        this.distances = distances;
    }

    public int[] getDistances() {
        return distances;
    }

}
