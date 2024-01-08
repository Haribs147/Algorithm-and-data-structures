package pl.edu.pw.ee.aisd2023zlab7.data.outcome;

public class GraphDfsResult extends GraphSearchResult {

    private final int[] inputOrder;
    private final int[] outputOrder;

    public GraphDfsResult(int startId, int[] prevVertices, int[] inputOrder, int[] outputOrder) {
        super(startId, prevVertices);
        
        this.inputOrder = inputOrder;
        this.outputOrder = outputOrder;
    }

    public int[] getInputOrder() {
        return inputOrder;
    }

    public int[] getOutputOrder() {
        return outputOrder;
    }

}
