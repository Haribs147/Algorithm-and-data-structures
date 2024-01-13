package pl.edu.pw.ee.aisd2023zlab7.graphsearch.bfs;

import java.util.ArrayDeque;
import java.util.Deque;

import pl.edu.pw.ee.aisd2023zlab7.data.input.Graph;
import pl.edu.pw.ee.aisd2023zlab7.data.outcome.GraphBfsResult;
import pl.edu.pw.ee.aisd2023zlab7.graphsearch.services.GraphSearch;
import pl.edu.pw.ee.aisd2023zlab7.graphsearch.utils.Kolor;

import static pl.edu.pw.ee.aisd2023zlab7.graphsearch.utils.Kolor.*;

public class BreadthFirstSeaerch implements GraphSearch {

    private final int initVal = -1;

    private Deque<Integer> queue;
    private Kolor[] color;
    private int[] prev;
    private int[] dist;

    private Graph graph;


    @Override
    public GraphBfsResult searchGraphPaths(Graph graph, int startId) {
        initData(graph);

        processFirstVertice(startId);

        processAllRestVertices();

        GraphBfsResult result = new GraphBfsResult(startId, prev, dist);

        return result;
    }

    private void initData(Graph graph) {
        this.graph = graph;

        initColors();
        initPrevVertices();
        initDist();
    }

    private void initColors() {
        int nVertices = graph.getNumOfVertices();

        color = new Kolor[nVertices];

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

    private void initDist() {
        int nVertices = graph.getNumOfVertices();

        dist = new int[nVertices];

        for (int i = 0; i < nVertices; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
    }

    private void processFirstVertice(int startId) {
        color[startId] = GRAY;
        dist[startId] = 0;
        queue = new ArrayDeque<>();

        queue.add(startId);
    }

    private void processAllRestVertices() {
        int currentVertice;

        while (!queue.isEmpty()) {
            currentVertice = queue.removeFirst();

            int[] neighbours = graph.getNeighbours(currentVertice);

            for (int neighbourId : neighbours) {

                if (isWhite(neighbourId)) {

                    dist[neighbourId] = dist[currentVertice] + 1;
                    prev[neighbourId] = currentVertice;
                    color[neighbourId] = GRAY;

                    queue.add(neighbourId);
                }
            }

            color[currentVertice] = BLACK;
        }
    }

    private boolean isWhite(int verticeId) {
        return color[verticeId] == WHITE;
    }

}
