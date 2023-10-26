package pl.edu.pw.ee.aisd2023zlab2.performance.utils;

public class HashSizeGenerator {

    public static int[] generateHashSizesPowerOf2(int nVariants, int initialSize) {
        int[] hashSizes = new int[nVariants];
        int initSize = initialSize;
        int multiplier;

        for (int i = 0; i < nVariants; i++) {
            multiplier = (int) Math.pow(2, i);
            hashSizes[i] = initSize * multiplier;
        }

        return hashSizes;
    }

    public static int[] generateHashSizePrimeNums(int nVariants) {
        int[] hashSizes = {
            4099,
            8209,
            16411,
            32771,
            65537,
            131041,
            262147,
            524309,
            1048583,
            2097169
        };

        assert hashSizes.length == nVariants;

        return hashSizes;
    }
}
