package pl.edu.pw.ee.aisd2023zlab7.data.input;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.String.format;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;

public class AdjacencyMatrix implements Graph {

    private static final Logger LOG = Logger.getLogger(AdjacencyMatrix.class.getName());

    private static final int EDGE = 1;

    private final String pathToGraphDataFile;

    private int[][] matrix;

    public AdjacencyMatrix(String pathToGraphDataFile) {
        this.pathToGraphDataFile = pathToGraphDataFile;

        readGraph();
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

                if (matrix[row][col] == EDGE) {
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

            if (matrix[verticeId][col] == EDGE) {
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

    private void readGraph() {
        try (FileReader fileReader = new FileReader(pathToGraphDataFile);
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

    private int[] findShape(BufferedReader reader) throws IOException {
        String data = reader.readLine();
        int lineId = 1;

        int[] shape = parseLine(data, lineId);

        return shape;
    }

    private void initAdjacencyMatrix(int[] colsRows) {
        int cols = colsRows[0];
        int rows = colsRows[1];
        int minColsRows = 1;

        if (cols < minColsRows || rows < minColsRows) {
            throw new RuntimeException("The size of the adjacency matrix cannot be less than one!");
        }

        matrix = new int[rows][cols];
    }

    private void fillAdjacencyMatrix(BufferedReader reader) {
        int lineId = 2;
        AtomicInteger lineCounter = new AtomicInteger(lineId);

        reader.lines().forEach(line -> {
            int[] data = parseLine(line, lineCounter.getAndIncrement());
            int srcId = data[0];
            int dstId = data[1];

            matrix[srcId][dstId] = EDGE;
        });
    }

    private int[] parseLine(String line, int lineId) {
        String separator = " ";
        String[] dataArr = line.split(separator);

        int[] data = Arrays.stream(dataArr).mapToInt(Integer::parseInt).toArray();

        int expectedNumOfData = 2;

        if (data.length != expectedNumOfData) {
            String errMsg = format("Incorrect result of parsing line (lineId: %d, data.length: %d)!", lineId, data.length);

            throw new RuntimeException(errMsg);
        }

        return data;
    }

    private void logAndThrowError(String errMsg, Throwable cause) {
        LOG.log(SEVERE, errMsg/*, cause*/);
        throw new RuntimeException(errMsg, cause);
    }
}
