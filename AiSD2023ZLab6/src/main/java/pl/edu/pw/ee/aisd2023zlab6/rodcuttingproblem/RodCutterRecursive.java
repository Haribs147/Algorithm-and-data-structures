package pl.edu.pw.ee.aisd2023zlab6.rodcuttingproblem;

import static java.lang.Math.max;

public class RodCutterRecursive extends RodCutter {

    @Override
    public int cutRod(int[] prices, int rodLength) {
        validateInput(prices, rodLength);

        int result = Integer.MIN_VALUE;

        if (rodLength == 0) {
            result = 0;

        } else {
            for (int i = 1; i <= rodLength; i++) {
                result = max(result, prices[i - 1] + cutRod(prices, rodLength - i));
            }
        }

        return result;
    }

}
