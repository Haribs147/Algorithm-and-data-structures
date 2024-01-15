package pl.edu.pw.ee.aisd2023zlab7.data.input;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.String.format;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;

public abstract class GraphMatrix implements Graph {

    private static final Logger LOG = Logger.getLogger(GraphMatrix.class.getName());

    static final int EDGE = 1;

    private int[][] matrix;

    private final String pathToGraphDataFile;

    public GraphMatrix(String pathToGraphDataFile) {
        this.pathToGraphDataFile = pathToGraphDataFile;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public String getPathToGraphDataFile() {
        return pathToGraphDataFile;
    }

    @Override
    public int getNumOfVertices() {
        int nRows = matrix.length;
        int nCols = matrix[0].length;

        int nOfVertices = Math.max(nRows, nCols);

        return nOfVertices;
    }

    @Override
    public int getNumOfEdges() {
        int nOfEdges = 0;
        int nRows = matrix.length;
        int nCols = matrix[0].length;

        for (int row = 0; row < nRows; row++) {

            for (int col = row; col < nCols; col++) {

                if (matrix[row][col] >= EDGE) {
                    nOfEdges++;
                }
            }
        }

        return nOfEdges;
    }

    @Override
    public int[] getNeighbours(int verticeId) {
        List<Integer> neighbours = new ArrayList<>();
        int nCols = matrix[0].length;

        if (verticeId >= matrix.length) {
            throw new IllegalArgumentException("Vertice ID does not exist!");
        }

        for (int col = 0; col < nCols; col++) {

            if (matrix[verticeId][col] >= EDGE) {
                neighbours.add(col);

            }
        }

        int[] neighboursArr = neighbours.stream()
                .mapToInt(Integer::intValue)
                .toArray();

        return neighboursArr;
    }

    @Override
    public int[] getVertices() {
        int nOfVertices = getNumOfVertices();

        int[] vertices = new int[nOfVertices];

        for (int i = 0; i < nOfVertices; i++) {
            vertices[i] = i;
        }

        return vertices;
    }

    public void readGraph() {
        try (FileReader fileReader = new FileReader(getPathToGraphDataFile());
                BufferedReader reader = new BufferedReader(fileReader);) {

            int[] colsRows = findShape(reader);

            initAdjacencyMatrix(colsRows);

            fillAdjacencyMatrix(reader);

        } catch (FileNotFoundException e) {
            logAndThrowError("Cannot read the file (file not found)!", e);

        } catch (IOException e) {
            logAndThrowError("Caught an exception while reading the file!", e);
        }
    }

    abstract void fillAdjacencyMatrix(BufferedReader reader);

    int[] parseLine(String line, int lineId, int expectedNumOfData) {
        String separator = " ";
        String[] dataArr = line.split(separator);

        int[] data = Arrays.stream(dataArr).mapToInt(Integer::parseInt).toArray();

        if (data.length != expectedNumOfData) {
            String errMsg = format("Incorrect result of parsing line (lineId: %d, data.length: %d)!", lineId, data.length);

            throw new RuntimeException(errMsg);
        }

        return data;
    }

    private int[] findShape(BufferedReader reader) throws IOException {
        String data = reader.readLine();
        int lineId = 1;
        int expectedNumOfData = 2;

        int[] shape = parseLine(data, lineId, expectedNumOfData);

        return shape;
    }

    private void initAdjacencyMatrix(int[] colsRows) {
        int cols = colsRows[0];
        int rows = colsRows[1];
        int minColsRows = 1;

        if (cols < minColsRows || rows < minColsRows) {
            throw new RuntimeException("The size of the adjacency matrix cannot be less than one!");
        }

        setMatrix(new int[rows][cols]);
    }

    private void logAndThrowError(String errMsg, Throwable cause) {
        LOG.log(SEVERE, errMsg/*, cause*/);
        throw new RuntimeException(errMsg, cause);
    }
}
