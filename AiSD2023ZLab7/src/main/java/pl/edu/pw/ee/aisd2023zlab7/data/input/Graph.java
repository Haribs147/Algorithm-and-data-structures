package pl.edu.pw.ee.aisd2023zlab7.data.input;

public interface Graph {

    int getNumOfVertices();

    int getNumOfEdges();

    int[] getNeighbours(int verticeId);

    int[] getVertices();
}
