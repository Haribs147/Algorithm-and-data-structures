package pl.edu.pw.ee.aisd2023zlab2.performance;

import pl.edu.pw.ee.aisd2023zlab2.HashListChainingModularHashing;
import static pl.edu.pw.ee.aisd2023zlab2.performance.utils.HashSizeGenerator.generateHashSizesPowerOf2;

public class PerformanceHashListChainingModularHashingSizePowerOf2Test extends PerformanceTest {

    public PerformanceHashListChainingModularHashingSizePowerOf2Test() {
        super(HashListChainingModularHashing.class);
    }

    @Override
    int[] getAllHashSizes() {
        return generateHashSizesPowerOf2(N_VARIANTS, INIT_SIZE);
    }

}
