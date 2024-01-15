package pl.edu.pw.ee.aisd2023zlab7.data.input;

public class GraphUtils {

    public AdjacencyMatrix readMatrix(String pathToFile) {
        AdjacencyMatrix matrix = new AdjacencyMatrix(pathToFile);
        matrix.readGraph();

        return matrix;
    }

    public WeightedAdjacencyMatrix readWeightedMatrix(String pathToFile) {
        WeightedAdjacencyMatrix matrix = new WeightedAdjacencyMatrix(pathToFile);
        matrix.readGraph();

        return matrix;
    }

}
