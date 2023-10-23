package pl.edu.pw.ee.aisd2023zlab1.performanceTest.table;

import static pl.edu.pw.ee.aisd2023zlab1.performanceTest.DataGenerator.generateAscNums;

public class PerformanceAscDataTest extends PerformanceTest {

    @Override
    protected double[] generateNums(int size) {
        return generateAscNums(size);
    }

}
