package pl.edu.pw.ee.aisd2023zlab7.data.outcome;

public abstract class GraphSearchResult {

    private int startId;
    private int[] prevVertices;

    public GraphSearchResult(int startId, int[] prevVertices) {
        this.startId = startId;
        this.prevVertices = prevVertices;
    }

    public int getStartId() {
        return startId;
    }

    public int[] getPrevVertices() {
        return prevVertices;
    }

}
