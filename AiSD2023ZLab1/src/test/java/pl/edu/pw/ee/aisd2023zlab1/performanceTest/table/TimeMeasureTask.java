package pl.edu.pw.ee.aisd2023zlab1.performanceTest.table;

import java.util.Arrays;
import static pl.edu.pw.ee.aisd2023zlab1.performanceTest.DataGenerator.generateRandomNums;
import pl.edu.pw.ee.aisd2023zlab1.services.Sorting;

public class TimeMeasureTask implements Runnable {

    private final Sorting sorter;

    private final double[] orgDataToSort;

    private long averageTime;

    public TimeMeasureTask(Sorting sorter, double[] dataToSort) {
        this.sorter = sorter;
        this.orgDataToSort = dataToSort;

        this.averageTime = -1;
    }

    public long getAverageTime() {
        return averageTime;
    }

    @Override
    public void run() {
        averageTime = measureAvgTimeOfSorting();
    }

    private long measureAvgTimeOfSorting() {
        long[] timeResults = measureTimeInLoop();

        long avgTime = countAvgWithoutTenOutliers(timeResults);

        return avgTime;
    }

    private long[] measureTimeInLoop() {
        int nOfRepeat = 100;
        long[] timeResults = new long[nOfRepeat];

        int n = orgDataToSort.length;
        double[] dataToSort = new double[n];

        long currentResult;

        for (int i = 0; i < nOfRepeat; i++) {
            copyData(orgDataToSort, dataToSort);

            currentResult = measureTimeOfSingleSorting(sorter, dataToSort);
            timeResults[i] = currentResult;
        }

        return timeResults;
    }

    private void copyData(double[] src, double[] dest) {
        int n = dest.length;

        for (int i = 0; i < n; i++) {
            dest[i] = src[i];
        }
    }

    private long measureTimeOfSingleSorting(Sorting sorter, double[] dataToSort) {
        long start = System.nanoTime();

        sorter.sort(dataToSort);

        long result = System.nanoTime() - start;

        return result;
    }

    private long countAvgWithoutTenOutliers(long[] timeResults) {
        Arrays.sort(timeResults);

        int nOfOutliers = 10;

        int nOfResults = timeResults.length;
        int start = nOfOutliers;
        int end = nOfResults - nOfOutliers;

        int n = nOfResults - 2 * nOfOutliers;

        assert start < end;

        long avgResult = 0;

        for (int i = start; i < end; i++) {
            avgResult += timeResults[i];
        }

        avgResult /= n;

        return avgResult;
    }

}
