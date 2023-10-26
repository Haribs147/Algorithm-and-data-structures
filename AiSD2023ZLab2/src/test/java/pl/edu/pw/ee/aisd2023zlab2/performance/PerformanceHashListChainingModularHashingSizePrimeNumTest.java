package pl.edu.pw.ee.aisd2023zlab2.performance;

import pl.edu.pw.ee.aisd2023zlab2.HashListChainingModularHashing;
import static pl.edu.pw.ee.aisd2023zlab2.performance.utils.HashSizeGenerator.generateHashSizePrimeNums;

public class PerformanceHashListChainingModularHashingSizePrimeNumTest extends PerformanceTest {

    public PerformanceHashListChainingModularHashingSizePrimeNumTest() {
        super(HashListChainingModularHashing.class);
    }

    @Override
    int[] getAllHashSizes() {
        return generateHashSizePrimeNums(N_VARIANTS);
    }

}
