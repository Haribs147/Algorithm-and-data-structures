package pl.edu.pw.ee.aisd2023zlab2.performance;

import pl.edu.pw.ee.aisd2023zlab2.HashListChainingMultiplicativeHashing;

import static pl.edu.pw.ee.aisd2023zlab2.performance.utils.HashSizeGenerator.generateHashSizesPowerOf2;

public class PerformanceHashListChainingMultiplicativeHashingSizePowerOf2Test extends PerformanceTest {

    public PerformanceHashListChainingMultiplicativeHashingSizePowerOf2Test() {
        super(HashListChainingMultiplicativeHashing.class);
    }

    @Override
    int[] getAllHashSizes() {
        return generateHashSizesPowerOf2(N_VARIANTS, INIT_SIZE);
    }

}
