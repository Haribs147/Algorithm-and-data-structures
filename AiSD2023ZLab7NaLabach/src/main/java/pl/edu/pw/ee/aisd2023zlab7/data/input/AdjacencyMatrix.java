package pl.edu.pw.ee.aisd2023zlab7.data.input;

import java.io.BufferedReader;
import java.util.concurrent.atomic.AtomicInteger;

public class AdjacencyMatrix extends GraphMatrix {

    AdjacencyMatrix(String pathToGraphDataFile) {
        super(pathToGraphDataFile);
    }

    @Override
    void fillAdjacencyMatrix(BufferedReader reader) {
        int lineId = 2;
        AtomicInteger lineCounter = new AtomicInteger(lineId);

        int expectedNumOfData = 2;

        reader.lines().forEach(line -> {
            int[] data = parseLine(line, lineCounter.getAndIncrement(), expectedNumOfData);
            int srcId = data[0];
            int dstId = data[1];

            getMatrix()[srcId][dstId] = EDGE;
        });
    }

}
