package pl.edu.pw.ee.aisd2023zlab6.rodcuttingproblem;

public class RodCutterResult {

    private int[] maxSumResults;
    private int[] solutions;

    public  RodCutterResult(int[] prices, int rodLength) {

        int[] maxSumResults = new int[rodLength + 1];
        int[] solutions = new int [rodLength + 1];
        int maxi = 0;
        int result;
        int index = 0;
        for (int i = 1; i <= rodLength; i++) {
            result = Integer.MIN_VALUE;
            index = 0;
            for (int j = 1; j <= i; j++) {
                int previousResult = result;
                result = Integer.max(result, prices[j - 1] + maxSumResults[i - j]);
                if ( result > previousResult){
                    index = i-j;
                }
            }
            maxSumResults[i] = result;
            solutions[i] = index;
        }
        this.maxSumResults = maxSumResults;
        this.solutions = solutions;
        for(int h = 0 ; h <= rodLength; h++){
            System.out.println(solutions[h]);
        }
    }
    public int [] getMaxSumResults(){
        return maxSumResults;
    }
    public int [] getSolutions(){
        return solutions;
    }
}
