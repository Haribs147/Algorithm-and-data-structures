package pl.edu.pw.ee.aisd2023zlab7.data.outcome;

public class ShortestPathsResult {

    private final int srcId;
    private final int destId;
    private final int minDistance;

    public ShortestPathsResult(int srcId, int destId, int minDistance) {
        this.srcId = srcId;
        this.destId = destId;
        this.minDistance = minDistance;
    }

    public int getMinDistance() {
        return minDistance;
    }

}
