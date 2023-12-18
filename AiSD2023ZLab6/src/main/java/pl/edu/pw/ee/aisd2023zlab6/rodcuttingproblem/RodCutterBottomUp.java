package pl.edu.pw.ee.aisd2023zlab6.rodcuttingproblem;

public class RodCutterBottomUp extends RodCutter {

    @Override
    public int cutRod(int[] prices, int rodLength) {
        validateInput(prices, rodLength);

        int[] results = new int[rodLength + 1];

        int result;

        for (int i = 1; i <= rodLength; i++) {
            result = Integer.MIN_VALUE;

            for (int j = 1; j <= i; j++) {
                result = Integer.max(result, prices[j - 1] + results[i - j]);
            }

            results[i] = result;
        }

        return results[rodLength];
    }

}
