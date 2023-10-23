package pl.edu.pw.ee.aisd2023zlab1.performanceTest.table;

import static pl.edu.pw.ee.aisd2023zlab1.performanceTest.DataGenerator.generateDescNums;

public class PerformanceDescDataTest extends PerformanceTest {

    @Override
    protected double[] generateNums(int size) {
        return generateDescNums(size);
    }

}
