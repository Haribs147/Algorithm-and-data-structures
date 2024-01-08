package pl.edu.pw.ee.aisd2023zlab7.graphsearch.dfs;

import pl.edu.pw.ee.aisd2023zlab7.data.input.Graph;
import pl.edu.pw.ee.aisd2023zlab7.data.outcome.GraphDfsResult;
import pl.edu.pw.ee.aisd2023zlab7.graphsearch.services.GraphSearch;

public class DepthFirstSearch implements GraphSearch {

    private static final int WHITE = 0;
    private static final int GRAY = 1;
    private static final int BLACK = 2;

    private final int initVal = -1;

    private int[] color;
    private int[] prev;
    private int[] inputOrder;
    private int[] outputOrder;
    private int orderCounter;

    private Graph graph;

    @Override
    public GraphDfsResult searchGraphPaths(Graph graph, int startId) {
        initData(graph);

        orderCounter = 0;
        dfsVisit(startId);

        visitNotConnectedVertices();

        GraphDfsResult result = new GraphDfsResult(startId, prev, inputOrder, outputOrder);

        return result;
    }

    private void initData(Graph graph) {
        this.graph = graph;

        initColors();
        initPrevVertices();
        initOrders();
    }

    private void initColors() {
        int nVertices = graph.getNumOfVertices();

        color = new int[nVertices];

        for (int i = 0; i < nVertices; i++) {
            color[i] = WHITE;
        }
    }

    private void initPrevVertices() {
        int nVertices = graph.getNumOfVertices();

        prev = new int[nVertices];

        for (int i = 0; i < nVertices; i++) {
            prev[i] = initVal;
        }
    }

    private void initOrders() {
        int nVertices = graph.getNumOfVertices();

        inputOrder = new int[nVertices];
        outputOrder = new int[nVertices];

        for (int i = 0; i < nVertices; i++) {
            inputOrder[i] = initVal;
            outputOrder[i] = initVal;
        }
    }

    private void visitNotConnectedVertices() {
        for (int verticeId : graph.getVertices()) {

            if (isWhite(verticeId)) {
                dfsVisit(verticeId);
            }
        }
    }

    private void dfsVisit(int startId) {
        color[startId] = GRAY;
        inputOrder[startId] = ++orderCounter;

        for (int verticeId : graph.getNeighbours(startId)) {

            if (isWhite(verticeId)) {
                prev[verticeId] = startId;

                dfsVisit(verticeId);
            }
        }

        color[startId] = BLACK;
        outputOrder[startId] = ++orderCounter;
    }

    private boolean isWhite(int verticeId) {
        return color[verticeId] == WHITE;
    }

}
