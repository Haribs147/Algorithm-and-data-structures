package pl.edu.pw.ee.aisd2023zlab1.performanceTest.table;

import static java.lang.String.format;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Level.WARNING;
import java.util.logging.Logger;
import org.junit.Test;
import pl.edu.pw.ee.aisd2023zlab1.InsertionSort;
import pl.edu.pw.ee.aisd2023zlab1.RefAlgSort;
import pl.edu.pw.ee.aisd2023zlab1.SelectionSort;
import pl.edu.pw.ee.aisd2023zlab1.heapSort.HeapSort;
import pl.edu.pw.ee.aisd2023zlab1.qsort.iterative.QuickSortIterativeMedian3;
import pl.edu.pw.ee.aisd2023zlab1.qsort.iterative.QuickSortIterativeRandom;
import pl.edu.pw.ee.aisd2023zlab1.qsort.iterative.QuickSortIterativeWithInSort;
import pl.edu.pw.ee.aisd2023zlab1.qsort.recursive.QuickSortRecursiveHoare;
import pl.edu.pw.ee.aisd2023zlab1.services.Sorting;

public abstract class PerformanceTest {

    private static final Logger LOG = Logger.getLogger(PerformanceTest.class.getName());

    @Test
    public void runPerformanceTest() {
        List<Sorting> sorters = getListOfSorters();
        int[] dataSizes = getDataSize();

        measureAndPrintAvgTimeOfAllSorters(sorters, dataSizes);
    }

    protected abstract double[] generateNums(int size);

    private List<Sorting> getListOfSorters() {
        List<Sorting> sorters = new ArrayList<>();
        sorters.add(new SelectionSort());
        sorters.add(new InsertionSort());
        sorters.add(new HeapSort());
        sorters.add(new QuickSortRecursiveHoare());
        sorters.add(new QuickSortIterativeRandom());
        sorters.add(new QuickSortIterativeMedian3());
        sorters.add(new QuickSortIterativeWithInSort());
        sorters.add(new RefAlgSort());
        // TODO: rest of algorithms

        return sorters;
    }

    private int[] getDataSize() {
        int n = 9;
        int startPower = 11;
        int[] dataSizes = new int[n];

        for (int i = 0; i < n; i++) {
            dataSizes[i] = (int) (Math.pow(2, (startPower + i)));
        }

        return dataSizes;
    }

    private void measureAndPrintAvgTimeOfAllSorters(List<Sorting> sorters, int[] dataSizes) {
        int n = dataSizes.length;

        for (Sorting sorter : sorters) {
            for (int size : dataSizes) {

                double[] dataToSort = generateNums(size);

                measureTimeAndPrintResultsOfSorting(sorter, dataToSort);
            }
        }
    }

    private void measureTimeAndPrintResultsOfSorting(Sorting sorter, double[] dataToSort) {
        String sorterName = getSorterName(sorter);
        int size = dataToSort.length;

        double avgTimeResult = measureAvgTimeAndStopAfterDuration(sorter, dataToSort);

        System.out.println(format("%20s | %4d | %g", sorterName, size, avgTimeResult));
    }

    private String getSorterName(Sorting sorter) {
        String result = sorter.getClass().getSimpleName();

        return result;
    }

    private double measureAvgTimeAndStopAfterDuration(Sorting sorter, double[] dataToSort) {
        TimeMeasureTask timeMeasureTask = new TimeMeasureTask(sorter, dataToSort);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future future = executor.submit(timeMeasureTask);
        long maxDurationInSeconds = 5;

        try {
            future.get(maxDurationInSeconds, TimeUnit.SECONDS);

        } catch (TimeoutException e) {
            future.cancel(true);
            LOG.log(WARNING, "A TIMEOUT interrupt was caught during average time measurement: {0}", e.getClass().getName());

        } catch (InterruptedException | ExecutionException e) {
            if (e.getCause() instanceof StackOverflowError) {
                logSevere("Caught StackOverflow!");
            } else {
                LOG.log(WARNING, "An exception was caught while measuring the average time: {0}", e.getClass().getName());
            }

        } finally {
            executor.shutdown();
        }

        double result = timeMeasureTask.getAverageTime();

        return result;
    }

    private void logSevere(String message) {
        String redColorPrefix = "\u001B[31m";
        String redColoerSuffix = "\u001B[0m";

        message = redColorPrefix + message + redColoerSuffix;

        LOG.log(SEVERE, message);
    }

}
