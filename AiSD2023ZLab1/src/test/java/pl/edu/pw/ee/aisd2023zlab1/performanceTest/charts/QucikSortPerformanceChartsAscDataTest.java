package pl.edu.pw.ee.aisd2023zlab1.performanceTest.charts;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.Test;
import pl.edu.pw.ee.aisd2023zlab1.qsort.iterative.QuickSortIterativeMedian3;
import pl.edu.pw.ee.aisd2023zlab1.services.Sorting;
import static java.lang.String.format;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;
import org.junit.BeforeClass;
import static pl.edu.pw.ee.aisd2023zlab1.performanceTest.DataGenerator.generateAscNums;

public class QucikSortPerformanceChartsAscDataTest {

    private static final Logger LOG = Logger.getLogger(QucikSortPerformanceChartsAscDataTest.class.getName());

    private static Sorting sorter;
    private static String resultFilename;

    @BeforeClass
    public static void setup() {
        sorter = new QuickSortIterativeMedian3();

        String sorterName = sorter.getClass().getSimpleName();
        resultFilename = sorterName + "_charts_performance.txt";

        createOrClearResultFile();
    }

    @Test
    public void runPerformanceChartTest() {
        int step = 100;
        int maxSize = 1_000_000;
        double[] data;

        for (int i = 0; i < maxSize; i += step) {

            data = generateAscNums(i);

            measureTimeAndSaveToFile(sorter, data);
        }
    }

    private static void createOrClearResultFile() {
        File resultFile = new File(resultFilename);

        try {
            resultFile.createNewFile();

            new FileWriter(resultFile, false).close();

        } catch (IOException e) {
            LOG.log(SEVERE, "Caught exception during creating file", e);
        }
    }

    private void measureTimeAndSaveToFile(Sorting sorter, double[] data) {
        String sorterName = sorter.getClass().getSimpleName();
        int n = data.length;

        long measuredTime = measureTime(sorter, data);

        saveToFile(sorterName, n, measuredTime);
    }

    private long measureTime(Sorting sorter, double[] data) {
        long start = System.nanoTime();

        sorter.sort(data);

        long timeResult = System.nanoTime() - start;

        return timeResult;
    }

    private void printResult(String sorterName, int size, long measuredTime) {
        String message = format("%20s | %8d | %d", sorterName, size, measuredTime);

        System.out.println(message);
    }

    private void saveToFile(String sorterName, int size, long measuredTime) {
        String fileName = sorterName + "_charts_performance.txt";

        try (
                FileWriter fWriter = new FileWriter(fileName, true);
                BufferedWriter writer = new BufferedWriter(fWriter)) {

            writer.append(format("%8d | %d\n", size, measuredTime));

        } catch (IOException e) {
            LOG.log(SEVERE, "Caught exception during writing to file: " + sorterName, e);
        }
    }

}
