package pl.edu.pw.ee.aisd2023zlab1.performanceTest.table;

import static pl.edu.pw.ee.aisd2023zlab1.performanceTest.DataGenerator.generateRandomNums;

public class PerformanceRandDataTest extends PerformanceTest {

    @Override
    protected double[] generateNums(int size) {
        return generateRandomNums(size);
    }

}
