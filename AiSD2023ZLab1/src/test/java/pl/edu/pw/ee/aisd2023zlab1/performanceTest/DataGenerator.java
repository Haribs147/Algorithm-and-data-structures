package pl.edu.pw.ee.aisd2023zlab1.performanceTest;

import java.util.Random;

public class DataGenerator {

    private static final Random RAND;

    static {
        int seed = 42;
        RAND = new Random(seed);
    }

    public static double[] generateRandomNums(int size) {
        assert size >= 0;

        double[] nums = new double[size];

        for (int i = 0; i < size; i++) {
            nums[i] = RAND.nextDouble();
        }

        return nums;
    }

    public static double[] generateAscNums(int size) {
        assert size >= 0;

        double[] nums = new double[size];
        int start = 1_000_000;

        for (int i = 0; i < size; i++) {
            nums[i] = start + i;
        }

        return nums;
    }
    public static double[] generateDescNums( int size) {
        assert size >= 0;

        double[] nums = new double[size];
        int start = 1_000_000;

        for (int i = 0; i < size; i++) {
            nums[i] = start - i;
        }

        return nums;
    }

}
