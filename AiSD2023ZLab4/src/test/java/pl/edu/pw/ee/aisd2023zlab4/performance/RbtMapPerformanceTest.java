package pl.edu.pw.ee.aisd2023zlab4.performance;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static java.lang.String.format;

import java.lang.reflect.Field;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.aisd2023zlab4.Node;
import pl.edu.pw.ee.aisd2023zlab4.RbtMap;
import pl.edu.pw.ee.aisd2023zlab4.services.MapInterface;

public class RbtMapPerformanceTest {

    private static final Logger LOG = Logger.getLogger(RbtMapPerformanceTest.class.getName());
    private final String filename = "rbtPerformanceResults.txt";

    @Before
    public void setup() {
        removeFileBeforeStart();
    }


    @Test
    public void countTreeHeightDependingOnDataSize() {
        MapInterface<Integer, String> map = new RbtMap<>();
        int currentSize = 0;
        int maxSize = 1_000_000;
        int step = 100;
        int counter = 0;
        int counterDesc = maxSize;
        int nOfPuts;

        while (currentSize < maxSize) {
            putRandomData(map, step);
            //putAscend(map, step, counter += step);
            //putDescend(map, step, counterDesc -= step);
            currentSize += step;

            nOfPuts = getNumOfPuts(map);
            saveResult(currentSize, nOfPuts);
        }

    }

    private void removeFileBeforeStart() {
        File f = new File(filename);

        f.delete();
    }

    private void putRandomData(MapInterface<Integer, String> map, int nOfData) {
        Random random = new Random();
        for (int i = 0; i < nOfData; i++) {
            int j = random.nextInt();
            String k = String.valueOf(j);
            map.setValue(j, k);
        }
    }

    private void putAscend(MapInterface<Integer, String> map, int nOfData, int counter) {
        String keyAndValue;

        for (int i = 0; i < nOfData; i++) {
            keyAndValue = String.valueOf(i + counter);
            map.setValue(i + counter, keyAndValue);
        }

    }

    private void putDescend(MapInterface<Integer, String> map, int nOfData, int counterDesc) {
        String keyAndValue;

        for (int i = nOfData; i > 0; i--) {
            keyAndValue = String.valueOf(i + counterDesc);
            map.setValue(i + counterDesc, keyAndValue);
        }
    }

    private void saveResult(int currentSize, int nOfPuts) {
        File f = new File(filename);

        try (FileWriter fw = new FileWriter(f, true); BufferedWriter writer = new BufferedWriter(fw)) {
            writer.append(format("size: %4d | nOfPuts: %d\n", currentSize, nOfPuts));

        } catch (IOException e) {
            LOG.severe("Caught an error while saving results.");
            throw new RuntimeException(e);
        }

    }

    private int getNumOfPuts(MapInterface<Integer, String> map) {
        String fieldTree = "tree";
        String fieldNumOfPuts = "currentNumOfPut";

        try {
            Field tree = map.getClass().getDeclaredField(fieldTree);
            tree.setAccessible(true);

            Object rbtTree = tree.get(map);

            Field currentNumOfPut = rbtTree.getClass().getDeclaredField(fieldNumOfPuts);
            currentNumOfPut.setAccessible(true);

            int numOfPuts = currentNumOfPut.getInt(rbtTree);

            return numOfPuts;

        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

}
